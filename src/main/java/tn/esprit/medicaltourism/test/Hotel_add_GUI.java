package tn.esprit.medicaltourism.test;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.PseudoColumnUsage;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import sun.awt.image.ImageAccessException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import org.apache.commons.net.ftp.FTPClient;

import tn.esprit.medicaltourism.delegate.HotelServiceDelegate;
import tn.esprit.medicaltourism.delegate.ImageServiceDelegate;
import tn.esprit.medicaltourism.domain.Hotel;
public class Hotel_add_GUI extends JPanel {
	private JTextField tf_name;
	private JTextField tf_adress;
	/**
	 * Create the panel.
	 */
	public Hotel_add_GUI() {

		JLabel Photo = new JLabel("");
		Photo.setIcon(new ImageIcon(
				Hotel_add_GUI.class
						.getResource("/edu/esprit/medicaltourism/images/images/Very-Basic-Picture-icon.png")));

		tf_name = new JTextField();
		tf_name.setColumns(10);

		tf_adress = new JTextField();
		tf_adress.setColumns(10);

		JTextArea ta_brief = new JTextArea();
		ta_brief.setRows(8);

		JComboBox cmb_stars = new JComboBox();
		cmb_stars.setModel(new DefaultComboBoxModel(new String[] { "1", "2",
				"3", "4", "5" }));

		
		
		JButton btn_ok = new JButton("OK");
		btn_ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
//				if (HotelServiceDelegate.create(hotel) != null) {
					
					tn.esprit.medicaltourism.domain.Image image =new tn.esprit.medicaltourism.domain.Image();
					Hotel	 hotel = new Hotel(tf_name.getText(), Integer
							.parseInt(cmb_stars.getSelectedItem().toString()),
							tf_adress.getText(), ta_brief.getText());
					String url = ("http://localhost:18080/uploads/hotel/" +tf_name.getText() + ".jpg");
					
					System.out.println(hotel);
					image.setUrl(url);
					image.setHotel(hotel);
					System.out.println(image);
					hotel.setPicture(image);
					HotelServiceDelegate.create(hotel);
					
					tf_name.setText("");
					tf_adress.setText("");
					ta_brief.setText("");
					cmb_stars.setSelectedIndex(0);
					JOptionPane.showMessageDialog(null,
							"the operation made with success");
//				}
			}
		});
		btn_ok.setFont(new Font("Segoe Print", Font.PLAIN, 16));

		JButton btn_cancel = new JButton("Cancel");
		btn_cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tf_adress.setText("");
				tf_name.setText("");
				ta_brief.setText("");
				cmb_stars.setSelectedIndex(0);

			}
		});
		btn_cancel.setFont(new Font("Segoe Print", Font.PLAIN, 16));

		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Source Sans Pro Semibold", Font.BOLD, 16));

		JLabel lblAdress = new JLabel("Adress");
		lblAdress.setFont(new Font("Source Sans Pro Semibold", Font.BOLD, 16));

		JLabel lblStars = new JLabel("Stars");
		lblStars.setFont(new Font("Source Sans Pro Semibold", Font.BOLD, 16));

		JLabel lblBrief = new JLabel("brief");
		lblBrief.setFont(new Font("Source Sans Pro Semibold", Font.BOLD, 16));

		JButton btnParcourir = new JButton("Parcourir");
		btnParcourir.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		btnParcourir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileopen = new JFileChooser();
				MonFiltre filtre_immage = new MonFiltre(new String[] { "gif",
						"tif", "jpeg", "jpg", "tiff" },
						"les fichiers image (*.gif, *.tif*.jpeg)");
				fileopen.addChoosableFileFilter(filtre_immage);
				int retour = fileopen.showDialog(null, "Charger photo");

				if (retour == JFileChooser.APPROVE_OPTION) {
					String nom_photo = fileopen.getSelectedFile().getName();
					String chemin_photo = fileopen.getSelectedFile()
							.getAbsolutePath();
					File file = fileopen.getSelectedFile();
					InputStream in;
					// FTPClient client = new FTPClient();
					
					try {
						// File_Image est mon FileChooser
						in = new FileInputStream(fileopen.getSelectedFile());
						//
						
						// Destination
						File dst = new File("C:/Users//firasniper//4BI2//JEE//servers//server 1//wildfly-9.0.1.Final//welcome-content//uploads//hotel//"
								+ tf_name.getText()+ ".jpg");

						// Création d'un nouveau fichier
						dst.createNewFile();
						OutputStream out = new FileOutputStream(dst);

						// Transfert
						byte[] buf = new byte[1024];
						int len;
						while ((len = in.read(buf)) > 0) {
							out.write(buf, 0, len);
						}

						// Fermeture des flux
						in.close();
						out.close();
					} catch (FileNotFoundException e2) {
					} catch (IOException e3) {
					}
					
					ImageIcon photoicon = new ImageIcon(new ImageIcon(
							chemin_photo).getImage().getScaledInstance(116,
							116, Image.SCALE_DEFAULT));
					Photo.setIcon(photoicon);

				}
			}
		});

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout
				.setHorizontalGroup(groupLayout
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								groupLayout
										.createSequentialGroup()
										.addGap(55)
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.LEADING)
														.addComponent(
																lblName,
																Alignment.TRAILING)
														.addComponent(
																lblStars,
																Alignment.TRAILING)
														.addComponent(
																lblBrief,
																Alignment.TRAILING)
														.addComponent(
																lblAdress,
																Alignment.TRAILING))
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addGroup(
																				groupLayout
																						.createSequentialGroup()
																						.addGap(33)
																						.addGroup(
																								groupLayout
																										.createParallelGroup(
																												Alignment.LEADING)
																										.addComponent(
																												cmb_stars,
																												GroupLayout.PREFERRED_SIZE,
																												76,
																												GroupLayout.PREFERRED_SIZE)
																										.addComponent(
																												ta_brief,
																												GroupLayout.PREFERRED_SIZE,
																												344,
																												GroupLayout.PREFERRED_SIZE)
																										.addGroup(
																												groupLayout
																														.createParallelGroup(
																																Alignment.TRAILING,
																																false)
																														.addComponent(
																																tf_name,
																																Alignment.LEADING)
																														.addComponent(
																																tf_adress,
																																Alignment.LEADING,
																																336,
																																336,
																																Short.MAX_VALUE)))))
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addGap(75)
																		.addComponent(
																				btnParcourir)
																		.addGap(27)
																		.addComponent(
																				btn_ok)
																		.addGap(26)
																		.addComponent(
																				btn_cancel)))
										.addGap(39)
										.addComponent(Photo,
												GroupLayout.PREFERRED_SIZE,
												392, Short.MAX_VALUE)
										.addContainerGap()));
		groupLayout
				.setVerticalGroup(groupLayout
						.createParallelGroup(Alignment.TRAILING)
						.addGroup(
								groupLayout
										.createSequentialGroup()
										.addContainerGap(130, Short.MAX_VALUE)
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(lblStars)
														.addComponent(
																cmb_stars,
																GroupLayout.PREFERRED_SIZE,
																35,
																GroupLayout.PREFERRED_SIZE))
										.addGap(18)
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(lblBrief)
														.addComponent(
																ta_brief,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addGap(81))
						.addGroup(
								groupLayout
										.createSequentialGroup()
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.TRAILING)
														.addGroup(
																Alignment.LEADING,
																groupLayout
																		.createSequentialGroup()
																		.addGap(21)
																		.addComponent(
																				Photo,
																				GroupLayout.PREFERRED_SIZE,
																				367,
																				Short.MAX_VALUE))
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addContainerGap()
																		.addGroup(
																				groupLayout
																						.createParallelGroup(
																								Alignment.BASELINE)
																						.addComponent(
																								lblName)
																						.addComponent(
																								tf_name,
																								GroupLayout.PREFERRED_SIZE,
																								32,
																								GroupLayout.PREFERRED_SIZE))
																		.addGap(16)
																		.addGroup(
																				groupLayout
																						.createParallelGroup(
																								Alignment.TRAILING)
																						.addComponent(
																								lblAdress)
																						.addComponent(
																								tf_adress,
																								GroupLayout.PREFERRED_SIZE,
																								32,
																								GroupLayout.PREFERRED_SIZE))
																		.addPreferredGap(
																				ComponentPlacement.RELATED,
																				36,
																				Short.MAX_VALUE)
																		.addGroup(
																				groupLayout
																						.createParallelGroup(
																								Alignment.BASELINE)
																						.addComponent(
																								btnParcourir,
																								GroupLayout.PREFERRED_SIZE,
																								38,
																								GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								btn_ok)
																						.addComponent(
																								btn_cancel))))
										.addGap(24)));
		setLayout(groupLayout);

	}
}
