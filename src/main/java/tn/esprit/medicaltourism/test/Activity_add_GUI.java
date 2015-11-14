package tn.esprit.medicaltourism.test;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;

import java.awt.Font;

import com.toedter.calendar.JDateChooser;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle.ComponentPlacement;

import tn.esprit.medicaltourism.delegate.ActivityServiceDelegate;
import tn.esprit.medicaltourism.domain.Activity;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Activity_add_GUI extends JFrame {

	private JPanel contentPane;
	 JTextField tf_name_activity;
	JDateChooser dc_startdc_activity;
	JDateChooser dc_enddc_activity;
	JTextArea ta_brief__activity;
	public static	Activity activity=null;
	tn.esprit.medicaltourism.domain.Image image_activity ;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Activity_add_GUI frame = new Activity_add_GUI();
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
	public Activity_add_GUI() {
		activity=new Activity();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 761, 463);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		
		JLabel label = new JLabel("Name");
		label.setFont(new Font("Source Sans Pro Semibold", Font.BOLD, 16));
		
		JLabel label_1 = new JLabel("start date");
		label_1.setFont(new Font("Source Sans Pro Semibold", Font.BOLD, 16));
		
		JLabel label_2 = new JLabel("end date");
		label_2.setFont(new Font("Source Sans Pro Semibold", Font.BOLD, 16));
		
		 dc_enddc_activity = new JDateChooser();
		dc_enddc_activity.setDateFormatString("yyyy-MM-dd");
		
		tf_name_activity = new JTextField();
		tf_name_activity.setColumns(10);
		
		 dc_startdc_activity = new JDateChooser();
		dc_startdc_activity.setDateFormatString("yyyy-MM-dd");
		
		JLabel label_3 = new JLabel("brief");
		label_3.setFont(new Font("Source Sans Pro Semibold", Font.BOLD, 16));
		
		JButton btnNext = new JButton("next");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				activity.setName(tf_name_activity.getText());
				
				 java.util.Date utilDate = dc_startdc_activity.getDate();
				 java.sql.Date sqlDateS = new java.sql.Date(utilDate.getTime());
				activity.setStartDate(sqlDateS+"");
				java.util.Date utilDate1 = dc_enddc_activity.getDate();
				 java.sql.Date sqlDateE = new java.sql.Date(utilDate1.getTime());
				activity.setEndDate(sqlDateE+"");
				activity.setDescription(ta_brief__activity.getText());
				
			
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
								+ tf_name_activity.getText()+ ".jpg");

						// Création d'un nouveau fichier
						dst.createNewFile();
						OutputStream out = new FileOutputStream(dst);

						// Transfert
						byte[] buf = new byte[1024];
						int len;
						while ((len = in.read(buf)) > 0) {
							out.write(buf, 0, len);
						}
						image_activity =new tn.esprit.medicaltourism.domain.Image();
						String url = ("http://localhost:18080/uploads/activity/" +tf_name_activity.getText() + ".jpg");
						image_activity.setUrl(url);
						if(image_activity!=null)
						{image_activity.setActivity(activity);
						//activity.setPicture(image_activity);
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
		
		 ta_brief__activity = new JTextArea();
		
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(label)
								.addComponent(label_1)
								.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE))
							.addGap(26)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(dc_enddc_activity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
									.addComponent(tf_name_activity, 0, 0, Short.MAX_VALUE)
									.addComponent(dc_startdc_activity, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(btnNext)
							.addGap(5)
							.addComponent(button_1)
							.addGap(18)
							.addComponent(btnBrowse))
						.addComponent(label_3)
						.addComponent(ta_brief__activity, GroupLayout.PREFERRED_SIZE, 279, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
					.addComponent(photoactivity, GroupLayout.PREFERRED_SIZE, 392, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(26)
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(label)
									.addGap(29)
									.addComponent(label_1))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(tf_name_activity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(dc_startdc_activity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
							.addGap(18)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(label_2)
								.addComponent(dc_enddc_activity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addComponent(label_3)
							.addGap(18)
							.addComponent(ta_brief__activity, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
								.addComponent(btnNext, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
									.addComponent(button_1)
									.addComponent(btnBrowse))))
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addComponent(photoactivity, GroupLayout.PREFERRED_SIZE, 367, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		panel.setLayout(gl_panel);
	}
}
