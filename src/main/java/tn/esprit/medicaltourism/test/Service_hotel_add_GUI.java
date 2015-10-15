package tn.esprit.medicaltourism.test;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import tn.esprit.medicaltourism.domain.Activity;
import tn.esprit.medicaltourism.domain.Services_Hotel;

public class Service_hotel_add_GUI extends JFrame {

	private JPanel contentPane;
	 JTextField tf_name_service_hotel;
	JTextArea ta_brief__service_hotel;
	public static Services_Hotel service_hotel;
	public static 	tn.esprit.medicaltourism.domain.Image image_service_hotel=null;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Service_hotel_add_GUI frame = new Service_hotel_add_GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Service_hotel_add_GUI() {
		service_hotel = new Services_Hotel();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 855, 449);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		
		JLabel label = new JLabel("Name");
		label.setFont(new Font("Source Sans Pro Semibold", Font.BOLD, 16));
		
		tf_name_service_hotel = new JTextField();
		tf_name_service_hotel.setColumns(10);
		
		JLabel label_3 = new JLabel("brief");
		label_3.setFont(new Font("Source Sans Pro Semibold", Font.BOLD, 16));
		
		JButton btnNext = new JButton("next");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				service_hotel.setName(tf_name_service_hotel.getText());
				service_hotel.setDescription(ta_brief__service_hotel.getText());
				dispose();
				
			}
		});
		btnNext.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		
		JButton button_1 = new JButton("reset");
		button_1.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		
		JButton btnBrowse = new JButton("browse ...");
		JLabel photoactivity = new JLabel("");
		
		
		btnBrowse.addActionListener(new ActionListener() {
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
								+ tf_name_service_hotel.getText()+ ".jpg");

						// Création d'un nouveau fichier
						dst.createNewFile();
						OutputStream out = new FileOutputStream(dst);

						// Transfert
						byte[] buf = new byte[1024];
						int len;
						while ((len = in.read(buf)) > 0) {
							out.write(buf, 0, len);
						}
						
						 image_service_hotel =new tn.esprit.medicaltourism.domain.Image();
							String url = ("http://localhost:18080/uploads/activity/" +tf_name_service_hotel.getText() + ".jpg");
							
							
							image_service_hotel.setUrl(url);
							if(image_service_hotel!=null){
							image_service_hotel.setService_hotel(service_hotel);
							service_hotel.setPicture(image_service_hotel);
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
					photoactivity.setIcon(photoicon);

				}
			}
		});
		btnBrowse.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		
		 ta_brief__service_hotel = new JTextArea();
		
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(label)
						.addComponent(label_3))
					.addGap(54)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(ta_brief__service_hotel, GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(btnNext)
							.addGap(5)
							.addComponent(button_1)
							.addGap(18)
							.addComponent(btnBrowse))
						.addComponent(tf_name_service_hotel))
					.addPreferredGap(ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
					.addComponent(photoactivity, GroupLayout.PREFERRED_SIZE, 392, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(label)
								.addComponent(tf_name_service_hotel, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
									.addGap(31)
									.addComponent(label_3))
								.addGroup(gl_panel.createSequentialGroup()
									.addGap(18)
									.addComponent(ta_brief__service_hotel, GroupLayout.PREFERRED_SIZE, 254, GroupLayout.PREFERRED_SIZE)))
							.addPreferredGap(ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
								.addComponent(btnNext, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
									.addComponent(button_1)
									.addComponent(btnBrowse))))
						.addComponent(photoactivity, GroupLayout.PREFERRED_SIZE, 367, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(26, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
	}
}
