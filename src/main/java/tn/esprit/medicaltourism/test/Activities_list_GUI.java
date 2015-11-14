package tn.esprit.medicaltourism.test;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.DefaultRowSorter;
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
import javax.swing.RowFilter;
import javax.swing.LayoutStyle.ComponentPlacement;

import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.swingbinding.JTableBinding;
import org.jdesktop.swingbinding.SwingBindings;

import tn.esprit.medicaltourism.delegate.ActivityServiceDelegate;
import tn.esprit.medicaltourism.delegate.HotelServiceDelegate;
import tn.esprit.medicaltourism.domain.Activity;
import tn.esprit.medicaltourism.domain.Hotel;

import java.util.Date;
import java.util.regex.Pattern;

import com.toedter.calendar.JDayChooser;
import com.toedter.calendar.JDateChooser;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.SystemColor;

public class Activities_list_GUI extends JPanel {

	private static final long serialVersionUID = 1L;
	List<Activity> activities = new ArrayList<Activity>();
	Activity activity = new Activity();

	JTextField tf_name;
	JTextField tf_price;
	JDateChooser startdateChooser;
	JDateChooser enddateChooser;
	JTable table_activities;
	JScrollPane scrollPane_activities;
	GroupLayout gl_panel;
	JPanel panel_update;
	JTextArea ta_desc;
	private JTextArea ta_desc_1;
	JLabel label_filter;
	private JButton btn_delete;
	private JTextField tf_filter;

	public Activities_list_GUI() {
		setBackground(SystemColor.text);
		initComponent();
		initDataBindings();

		scrollPane_activities.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

			}
		});

		table_activities.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				activity = activities.get(table_activities.getSelectedRow());
				tf_name.setText(activity.getName());

				ta_desc.setText(activity.getDescription());
				label_filter.setText(table_activities.getColumnName(table_activities.columnAtPoint(e.getPoint())));
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
		table_activities = new JTable();
		table_activities.setAutoCreateRowSorter(true);
		activities = ActivityServiceDelegate.findAll();
		scrollPane_activities = new JScrollPane();
		scrollPane_activities.setViewportView(table_activities);
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.text);

		JLabel lbl_picture_update_activity = new JLabel("");
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(20)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 1209, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lbl_picture_update_activity, GroupLayout.DEFAULT_SIZE, 19, Short.MAX_VALUE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(30, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lbl_picture_update_activity, GroupLayout.PREFERRED_SIZE, 233, GroupLayout.PREFERRED_SIZE)
							.addGap(402))
						.addComponent(panel, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 635, GroupLayout.PREFERRED_SIZE)))
		);

		panel_update = new JPanel();
		panel_update.setBackground(SystemColor.text);
		
		 label_filter = new JLabel("Filter :");
		label_filter.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
		
		tf_filter = new JTextField();
		tf_filter.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				applyTableFilter(tf_filter.getText(),table_activities);
			}
		});
		tf_filter.setColumns(10);
		gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(scrollPane_activities, GroupLayout.PREFERRED_SIZE, 532, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
							.addComponent(panel_update, GroupLayout.PREFERRED_SIZE, 637, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(label_filter, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(tf_filter, GroupLayout.PREFERRED_SIZE, 202, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(22)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(20)
							.addComponent(label_filter, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(10)
							.addComponent(tf_filter, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(panel_update, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(scrollPane_activities, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE))
					.addGap(281))
		);

		JLabel label = new JLabel("Name");
		label.setFont(new Font("Source Sans Pro Semibold", Font.BOLD, 16));

		tf_name = new JTextField();
		tf_name.setColumns(10);

		JLabel lb_startdate = new JLabel("start date");
		lb_startdate
				.setFont(new Font("Source Sans Pro Semibold", Font.BOLD, 16));

		// tf_adress = new JTextField();
		// tf_adress.setColumns(10);

		JLabel label_3 = new JLabel("brief");
		label_3.setFont(new Font("Source Sans Pro Semibold", Font.BOLD, 16));

		ta_desc = new JTextArea();
		ta_desc.setRows(8);

		JButton btn_update = new JButton("update");
		btn_update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				activity.setName(tf_name.getText());
				// activity.setAddress(tf_adress.getText());
				java.util.Date utilDate = startdateChooser.getDate();
				java.sql.Date sqlDateS = new java.sql.Date(utilDate.getTime());
				activity.setStartDate(sqlDateS + "");
				java.util.Date utilDate1 = enddateChooser.getDate();
				java.sql.Date sqlDateE = new java.sql.Date(utilDate1.getTime());
				activity.setEndDate(sqlDateE + "");
				activity.setDescription(ta_desc.getText());
				tn.esprit.medicaltourism.domain.Image image_activity = new tn.esprit.medicaltourism.domain.Image();
				String url = ("http://localhost:18080/uploads/activity/"
						+ tf_name.getText() + ".jpg");
				image_activity.setUrl(url);
			//	activity.setPicture(image_activity);
				image_activity.setActivity(activity);
				ActivityServiceDelegate.update(activity);
				JOptionPane.showMessageDialog(null, "updated successfuly !");
				activities = ActivityServiceDelegate.findAll();
				initDataBindings();
			}
		});
		btn_update.setFont(new Font("Segoe Print", Font.PLAIN, 16));

		JButton btn_reset = new JButton("reset");
		btn_reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tf_name.setText("");
				tf_price.setText("");
				startdateChooser.setDate(new Date());
				enddateChooser.setDate(new Date());
				ta_desc.setText("");
			}
		});
		btn_reset.setFont(new Font("Segoe Print", Font.PLAIN, 16));

		btn_delete = new JButton("delete");
		btn_delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				activity.setName(tf_name.getText());

				// activity.setAddress(tf_adress.getText());
				// activity.setStar(Integer.parseInt(cmb_stars.getSelectedItem().toString()));
				activity.setDescription(ta_desc.getText());
				ActivityServiceDelegate.delete(activity);
				JOptionPane.showMessageDialog(null, "Delelted successfuly !");
				activities = ActivityServiceDelegate.findAll();
				initDataBindings();

			}
		});
		btn_delete.setFont(new Font("Segoe Print", Font.PLAIN, 16));

		JLabel lblEndDate = new JLabel("end date");
		lblEndDate.setFont(new Font("Source Sans Pro Semibold", Font.BOLD, 16));

		startdateChooser = new JDateChooser();
		startdateChooser.setDateFormatString("yyyy-MM-dd");

		enddateChooser = new JDateChooser();
		enddateChooser.setDateFormatString("yyyy-MM-dd");

		JButton button = new JButton("browse ...");
		button.addActionListener(new ActionListener() {
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
						File dst = new File(
								"C:/Users//firasniper//4BI2//JEE//servers//server 1//wildfly-9.0.1.Final//welcome-content//uploads//activity//"
										+ tf_name.getText() + ".jpg");

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
					lbl_picture_update_activity.setIcon(photoicon);

				}

			}

		});
		button.setFont(new Font("Segoe Print", Font.PLAIN, 16));

		ta_desc_1 = new JTextArea();
		GroupLayout gl_panel_update = new GroupLayout(panel_update);
		gl_panel_update.setHorizontalGroup(
			gl_panel_update.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_update.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_update.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_update.createSequentialGroup()
							.addGroup(gl_panel_update.createParallelGroup(Alignment.LEADING)
								.addComponent(label)
								.addComponent(lb_startdate)
								.addComponent(lblEndDate, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE))
							.addGap(26)
							.addGroup(gl_panel_update.createParallelGroup(Alignment.LEADING)
								.addComponent(enddateChooser, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panel_update.createSequentialGroup()
									.addGroup(gl_panel_update.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(tf_name, 0, 0, Short.MAX_VALUE)
										.addComponent(startdateChooser, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
									.addGap(53)
									.addComponent(label_3)))
							.addGap(18)
							.addComponent(ta_desc_1, GroupLayout.PREFERRED_SIZE, 231, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_update.createSequentialGroup()
							.addComponent(btn_update, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btn_reset)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_update.createParallelGroup(Alignment.TRAILING)
								.addComponent(btn_delete)
								.addGroup(gl_panel_update.createSequentialGroup()
									.addComponent(button, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)))))
					.addContainerGap(101, Short.MAX_VALUE))
		);
		gl_panel_update.setVerticalGroup(
			gl_panel_update.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel_update.createSequentialGroup()
					.addGroup(gl_panel_update.createParallelGroup(Alignment.TRAILING)
						.addComponent(ta_desc_1, GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
						.addGroup(gl_panel_update.createSequentialGroup()
							.addGap(26)
							.addGroup(gl_panel_update.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_panel_update.createSequentialGroup()
									.addComponent(label)
									.addGap(29)
									.addComponent(lb_startdate))
								.addGroup(gl_panel_update.createSequentialGroup()
									.addGroup(gl_panel_update.createParallelGroup(Alignment.BASELINE)
										.addComponent(tf_name, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(label_3))
									.addGap(18)
									.addComponent(startdateChooser, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
							.addGap(18)
							.addGroup(gl_panel_update.createParallelGroup(Alignment.LEADING)
								.addComponent(lblEndDate)
								.addComponent(enddateChooser, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_update.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_panel_update.createSequentialGroup()
									.addComponent(btn_delete)
									.addPreferredGap(ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
									.addGroup(gl_panel_update.createParallelGroup(Alignment.BASELINE)
										.addComponent(button, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
										.addComponent(btn_reset)))
								.addComponent(btn_update, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap())
		);
		panel_update.setLayout(gl_panel_update);
		panel.setLayout(gl_panel);
		setLayout(groupLayout);

	}
	protected void initDataBindings() {
		JTableBinding<Activity, List<Activity>, JTable> jTableBinding = SwingBindings.createJTableBinding(UpdateStrategy.READ, activities, table_activities);
		//
		BeanProperty<Activity, Integer> activityBeanProperty = BeanProperty.create("id");
		jTableBinding.addColumnBinding(activityBeanProperty).setColumnName("Id");
		//
		BeanProperty<Activity, String> activityBeanProperty_1 = BeanProperty.create("description");
		jTableBinding.addColumnBinding(activityBeanProperty_1).setColumnName("Description");
		//
		BeanProperty<Activity, String> activityBeanProperty_2 = BeanProperty.create("startDate");
		jTableBinding.addColumnBinding(activityBeanProperty_2).setColumnName("StartDate");
		//
		BeanProperty<Activity, String> activityBeanProperty_3 = BeanProperty.create("endDate");
		jTableBinding.addColumnBinding(activityBeanProperty_3).setColumnName("EndDate");
		//
		BeanProperty<Activity, String> activityBeanProperty_4 = BeanProperty.create("name");
		jTableBinding.addColumnBinding(activityBeanProperty_4).setColumnName("name");
		//
		jTableBinding.bind();
	}
}
