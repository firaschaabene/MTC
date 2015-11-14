package tn.esprit.medicaltourism.test;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultRowSorter;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.RowFilter;
import javax.swing.border.LineBorder;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.swingbinding.JTableBinding;
import org.jdesktop.swingbinding.SwingBindings;

import tn.esprit.medicaltourism.delegate.ActivityServiceDelegate;
import tn.esprit.medicaltourism.delegate.HotelServiceDelegate;
import tn.esprit.medicaltourism.delegate.ImageServiceDelegate;
import tn.esprit.medicaltourism.delegate.ServicesOfHotelDelegate;
import tn.esprit.medicaltourism.domain.Activity;
import tn.esprit.medicaltourism.domain.Hotel;
import tn.esprit.medicaltourism.domain.Service_Hotel;

import com.mysql.jdbc.Connection;

public class Hotel_list_GUI extends JPanel {

	private static final long serialVersionUID = 1L;
	List<Hotel> hotels = new ArrayList<Hotel>();
	Hotel hotel = new Hotel();

	JTextField tf_name;
	JTextField tf_adress;
	JComboBox cmb_stars;
	JTable table_hotels;
	JScrollPane scrollPane_listhotels;
	GroupLayout gl_panel;
	JPanel panel_update;
	JTextArea ta_brief;
	private JButton btn_delete;
	private JButton btn_upload_update_hotel;
	private JLabel lbl_picture;
	private JLabel lblFilter;
	private JTextField tf_filtre;
	tn.esprit.medicaltourism.domain.Image image_hotel=null;

	public Hotel_list_GUI() {
		this.setBackground(new Color(0, 0, 0, 120));
		this.setForeground(new java.awt.Color(153, 0, 2));
	this.setOpaque(false);
		initComponent();
		initDataBindings();

		

		table_hotels.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				hotel = hotels.get(table_hotels.getSelectedRow());
				
				tf_name.setText(hotel.getName());
				cmb_stars.setSelectedIndex(hotel.getStar() - 1);
				tf_adress.setText(hotel.getAddress());
				ta_brief.setText(hotel.getDescription());
				lblFilter.setText(table_hotels.getColumnName(table_hotels.columnAtPoint(e.getPoint())));
				tn.esprit.medicaltourism.domain.Image img=new tn.esprit.medicaltourism.domain.Image();
				img=ImageServiceDelegate.findHotelImage(hotel);
				System.out.println(img);
				if(img!=null){
//					{ImageIcon photoicon = new ImageIcon(new ImageIcon(
//						hotel.getPicture().getUrl()).getImage().getScaledInstance(50,
//						50, Image.SCALE_AREA_AVERAGING));
//				lbl_picture.setIcon(photoicon);
				
					}
				else 
				lbl_picture.setIcon((new ImageIcon(Hotel_list_GUI.class.getResource("/edu/esprit/medicaltourism/images/images/default.png"))));
			
			}
		});

	}

	
	 private void applyTableFilter(String filterText,JTable x) {
			// On escape le texte afin que son contenu ne soit pas considéré comme
			// une regexp
			String escapedFilterText = Pattern.quote(filterText);
			// On ajoute les wildcards a gauche et a droite
			String completeFilterText = ".*" + escapedFilterText + ".*";
			// On applique le filtre a la JTable
			((DefaultRowSorter) x.getRowSorter())
					.setRowFilter(RowFilter.regexFilter(completeFilterText));
		}
	
	private void initComponent() {
		
		
		
		table_hotels = new JTable();
		hotels = HotelServiceDelegate.findAll();
		scrollPane_listhotels = new JScrollPane();
		
		
		scrollPane_listhotels.setBackground(new Color(0, 0, 0, 0));
		scrollPane_listhotels.setForeground(new java.awt.Color(153, 0, 2));

		scrollPane_listhotels.setOpaque(false);
		scrollPane_listhotels.setViewportView(table_hotels);
		table_hotels.setAutoCreateRowSorter(true);
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 4, true));
		panel.setBackground(new Color(0, 0, 0, 0));
		panel.setForeground(new java.awt.Color(153, 0, 2));
		panel.setOpaque(false);

		panel_update = new JPanel();
		panel_update.setBackground(new Color(0, 0, 0, 0));
		panel_update.setForeground(new java.awt.Color(153, 0, 2));
		panel_update.setOpaque(false);
		
		panel_update.setBorder(new LineBorder(Color.DARK_GRAY, 1, true));
		
		lblFilter = new JLabel("Filter :");
		lblFilter.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
		
		tf_filtre = new JTextField();
		tf_filtre.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				 applyTableFilter(tf_filtre.getText(),table_hotels);
			}
		});
		tf_filtre.setColumns(10);
		
		JButton btnPdf = new JButton("PDF");
		btnPdf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showreport();
			}
		});
		
		lbl_picture = new JLabel("");
		
		
		gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(21)
					.addComponent(lblFilter)
					.addGap(18)
					.addComponent(tf_filtre, GroupLayout.PREFERRED_SIZE, 202, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 428, Short.MAX_VALUE)
					.addComponent(btnPdf)
					.addGap(69))
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_update, GroupLayout.DEFAULT_SIZE, 820, Short.MAX_VALUE)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(scrollPane_listhotels, GroupLayout.PREFERRED_SIZE, 532, GroupLayout.PREFERRED_SIZE)
							.addGap(52)
							.addComponent(lbl_picture, GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblFilter)
						.addComponent(tf_filtre, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnPdf))
					.addGap(37)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lbl_picture, GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE)
						.addComponent(scrollPane_listhotels, GroupLayout.PREFERRED_SIZE, 264, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(panel_update, GroupLayout.PREFERRED_SIZE, 247, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);

		JLabel label = new JLabel("Name");
		label.setFont(new Font("Source Sans Pro Semibold", Font.BOLD, 16));

		tf_name = new JTextField();
		tf_name.setColumns(10);

		JLabel label_1 = new JLabel("Adress");
		label_1.setFont(new Font("Source Sans Pro Semibold", Font.BOLD, 16));

		tf_adress = new JTextField();
		tf_adress.setColumns(10);

		JLabel label_2 = new JLabel("Stars");
		label_2.setFont(new Font("Source Sans Pro Semibold", Font.BOLD, 16));

		cmb_stars = new JComboBox();
		cmb_stars.setModel(new DefaultComboBoxModel(new String[] { "1", "2",
				"3", "4", "5" }));

		JLabel label_3 = new JLabel("brief");
		label_3.setFont(new Font("Source Sans Pro Semibold", Font.BOLD, 16));

		ta_brief = new JTextArea();
		ta_brief.setRows(8);

		JButton btn_update = new JButton("update");
		btn_update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hotel.setName(tf_name.getText());
				hotel.setAddress(tf_adress.getText());
				hotel.setStar(Integer.parseInt(cmb_stars.getSelectedItem()
						.toString()));
				hotel.setDescription(ta_brief.getText());
			
				if(Service_hotel_add_GUI.service_hotel!=null)
				{Service_hotel_add_GUI.service_hotel.setHotel(hotel);
					List<Service_Hotel> services = new ArrayList<Service_Hotel>();
					services=ServicesOfHotelDelegate.findByhotel(hotel);
					services.add(Service_hotel_add_GUI.service_hotel);
				hotel.setServices(services);
				
				
				ServicesOfHotelDelegate.create(Service_hotel_add_GUI.service_hotel);
				}
				
				if(Activity_add_GUI.activity!=null)
				{Activity_add_GUI.activity.setHotel(hotel);
					List<Activity> activities = new ArrayList<Activity>();
				activities=ActivityServiceDelegate.findByhotel(hotel);
				activities.add(Activity_add_GUI.activity);
				hotel.setActivities(activities);
				
				ActivityServiceDelegate.create(Activity_add_GUI.activity);
				}
				if(image_hotel!=null)
				{
					System.out.println(image_hotel);
					System.out.println(hotel);
					image_hotel.setHotel(hotel);
				//hotel.setPicture(image_hotel);
				}
				HotelServiceDelegate.update(hotel);

				JOptionPane.showMessageDialog(null, "updated successfuly !");

				initDataBindings();
			}
		});
		btn_update.setFont(new Font("Segoe Print", Font.PLAIN, 16));

		JButton btn_reset = new JButton("reset");
		btn_reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tf_name.setText("");
				tf_adress.setText("");
				cmb_stars.setSelectedIndex(-1);
				ta_brief.setText("");
			}
		});
		btn_reset.setFont(new Font("Segoe Print", Font.PLAIN, 16));

		btn_delete = new JButton("delete");
		btn_delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				hotel.setName(tf_name.getText());
				hotel.setAddress(tf_adress.getText());
				hotel.setStar(Integer.parseInt(cmb_stars.getSelectedItem()
						.toString()));
				hotel.setDescription(ta_brief.getText());
				HotelServiceDelegate.delete(hotel);
				JOptionPane.showMessageDialog(null, "Delelted successfuly !");
				hotels = HotelServiceDelegate.findAll();
				initDataBindings();

			}
		});
		btn_delete.setFont(new Font("Segoe Print", Font.PLAIN, 16));

		JButton btn_add_activity = new JButton("add activity");
		btn_add_activity.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Activity_add_GUI activity_add_GUI = new Activity_add_GUI();
				activity_add_GUI.show();

			}
		});
		
		btn_upload_update_hotel = new JButton("browse ...");
		btn_upload_update_hotel.addActionListener(new ActionListener() {
			
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
						 image_hotel =new tn.esprit.medicaltourism.domain.Image();
						String url = ("http://localhost:18080/uploads/hotel/" +tf_name.getText() + ".jpg");
						
						
						image_hotel.setUrl(url);
						
						// Fermeture des flux
						in.close();
						out.close();
					} catch (FileNotFoundException e2) {
					} catch (IOException e3) {
					}
					
					ImageIcon photoicon = new ImageIcon(new ImageIcon(
							chemin_photo).getImage().getScaledInstance(116,
							116, Image.SCALE_DEFAULT));
					lbl_picture.setIcon(photoicon);

				}
				
				
			}
		});
		btn_upload_update_hotel.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		
		JButton btn_addservice = new JButton("add service");
		btn_addservice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Service_hotel_add_GUI service_hotel_add_GUI=new Service_hotel_add_GUI();
				service_hotel_add_GUI.show();
				
			}
		});
		GroupLayout gl_panel_update = new GroupLayout(panel_update);
		gl_panel_update.setHorizontalGroup(
			gl_panel_update.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_update.createSequentialGroup()
					.addGap(10)
					.addGroup(gl_panel_update.createParallelGroup(Alignment.LEADING)
						.addComponent(label)
						.addGroup(gl_panel_update.createSequentialGroup()
							.addGap(6)
							.addComponent(label_1))
						.addGroup(gl_panel_update.createSequentialGroup()
							.addGap(6)
							.addComponent(label_2)))
					.addGap(5)
					.addGroup(gl_panel_update.createParallelGroup(Alignment.LEADING)
						.addComponent(tf_name, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(tf_adress, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel_update.createSequentialGroup()
							.addGap(2)
							.addComponent(cmb_stars, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)))
					.addGap(18)
					.addComponent(label_3)
					.addGap(22)
					.addComponent(ta_brief, GroupLayout.PREFERRED_SIZE, 227, GroupLayout.PREFERRED_SIZE)
					.addGap(33)
					.addGroup(gl_panel_update.createParallelGroup(Alignment.LEADING)
						.addComponent(btn_add_activity, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
						.addComponent(btn_upload_update_hotel, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
						.addComponent(btn_addservice, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE))
					.addGap(52)
					.addGroup(gl_panel_update.createParallelGroup(Alignment.LEADING)
						.addComponent(btn_reset, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)
						.addComponent(btn_delete, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE)
						.addComponent(btn_update, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE)))
		);
		gl_panel_update.setVerticalGroup(
			gl_panel_update.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_update.createSequentialGroup()
					.addGroup(gl_panel_update.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_update.createSequentialGroup()
							.addGap(13)
							.addComponent(label)
							.addGap(40)
							.addComponent(label_1)
							.addGap(32)
							.addComponent(label_2))
						.addGroup(gl_panel_update.createSequentialGroup()
							.addGap(13)
							.addComponent(tf_name, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
							.addGap(23)
							.addComponent(tf_adress, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
							.addGap(13)
							.addComponent(cmb_stars, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_update.createSequentialGroup()
							.addGap(21)
							.addComponent(label_3))
						.addGroup(gl_panel_update.createSequentialGroup()
							.addGap(13)
							.addComponent(ta_brief, GroupLayout.PREFERRED_SIZE, 194, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_update.createSequentialGroup()
							.addGap(14)
							.addGroup(gl_panel_update.createParallelGroup(Alignment.BASELINE)
								.addComponent(btn_reset, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
								.addComponent(btn_upload_update_hotel, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE))
							.addGap(5)
							.addGroup(gl_panel_update.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_panel_update.createSequentialGroup()
									.addComponent(btn_delete, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(btn_update, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel_update.createSequentialGroup()
									.addComponent(btn_addservice, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(btn_add_activity, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)))))
					.addGap(38))
		);
		panel_update.setLayout(gl_panel_update);
		panel.setLayout(gl_panel);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(12)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(37)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 633, GroupLayout.PREFERRED_SIZE))
		);
		setLayout(groupLayout);

	}

	protected void initDataBindings() {
		JTableBinding<Hotel, List<Hotel>, JTable> jTableBinding = SwingBindings
				.createJTableBinding(UpdateStrategy.READ, hotels, table_hotels);
		//
		BeanProperty<Hotel, Integer> hotelBeanProperty = BeanProperty
				.create("id");
		jTableBinding.addColumnBinding(hotelBeanProperty).setColumnName("ID");
		//
		BeanProperty<Hotel, String> hotelBeanProperty_1 = BeanProperty
				.create("name");
		jTableBinding.addColumnBinding(hotelBeanProperty_1).setColumnName(
				"Name");
		//
		BeanProperty<Hotel, String> hotelBeanProperty_2 = BeanProperty
				.create("address");
		jTableBinding.addColumnBinding(hotelBeanProperty_2).setColumnName(
				"Adress");
		//
		BeanProperty<Hotel, Integer> hotelBeanProperty_3 = BeanProperty
				.create("star");
		jTableBinding.addColumnBinding(hotelBeanProperty_3).setColumnName(
				"Stars");
		//
		jTableBinding.bind();
	}
	
	private void showreport() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = (Connection) DriverManager
					.getConnection(
							"jdbc:mysql://localhost:3306/medicaltourismdb",
							"root", "");
			String sourceName = "src/main/java/tn/esprit/medicaltourism/report/hotels.jrxml";
			JasperReport report = JasperCompileManager
					.compileReport(sourceName);
			JasperPrint filedReport = JasperFillManager.fillReport(report,
					null, con);
			JasperViewer.viewReport(filedReport);
//			this.getContentPane().add(new JRViewer(filedReport));
//			this.pack();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
}
