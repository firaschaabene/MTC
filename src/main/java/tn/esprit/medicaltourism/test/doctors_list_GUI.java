package tn.esprit.medicaltourism.test;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.BorderLayout;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableModel;

import java.awt.Color;
import java.awt.Font;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;
import org.jdesktop.swingbinding.JTableBinding;
import org.jdesktop.swingbinding.SwingBindings;

import tn.esprit.medicaltourism.delegate.DoctorServiceDelegate;
import tn.esprit.medicaltourism.domain.Doctor;
import tn.esprit.medicaltourism.domain.Hotel;
import tn.esprit.medicaltourism.mail.EmailSender;
import tn.esprit.medicaltourism.mail.Message;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JScrollPane;


public class doctors_list_GUI extends JPanel {
	private JTextField txtLastName;
	private JTextField txtFirstName;
	private JTextField txtNickName;
	private JTextField txtSpeciality;
	private JTextField specialityFinder;
	List<Doctor> doctors = new ArrayList<Doctor>();
	Doctor doctor = new Doctor();
	private JTable tableDoctors;
	/**
	 * Create the panel.
	 */
	public doctors_list_GUI() {
		
		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		
		txtLastName = new JTextField();
		txtLastName.setColumns(10);
		txtLastName.setBounds(222, 102, 122, 20);
		panel_1.add(txtLastName);
		
		txtFirstName = new JTextField();
		txtFirstName.setColumns(10);
		txtFirstName.setBounds(222, 148, 122, 20);
		panel_1.add(txtFirstName);
		
		JButton button = new JButton("Update");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tableDoctors.getSelectedRow() == -1) {
					if (tableDoctors.getSelectedRow() == 0) {
						JOptionPane.showMessageDialog(null,
								" Choose a doctor Please !!!!! ",
								"Update Alerte ", 1);
					} else {
						JOptionPane.showMessageDialog(null,
								" Choose a doctor Please !!!!! ",
								"Update Alerte ", 1);
					}
				} else {

					Doctor dr = new Doctor();
					int i = tableDoctors.getSelectedRow();
					int s = (int) tableDoctors.getModel().getValueAt(i, 0);
					dr.setId(s);
					dr.setLastName(txtLastName.getText());
					dr.setFirstName(txtFirstName.getText());
					dr.setNickName(txtNickName.getText());
					dr.setSpeciality(txtSpeciality.getText());

					DoctorServiceDelegate.updateDoctor(dr);
					JOptionPane.showMessageDialog(null,
							"Doctor Updated successfully   !!!!! ",
							"Update Alerte", 1);

					
					initDataBindings();

				}
			}
		});
		button.setBounds(255, 310, 89, 23);
		panel_1.add(button);
		
		JLabel label = new JLabel("Last Name");
		label.setBounds(97, 105, 104, 14);
		panel_1.add(label);
		
		JLabel label_1 = new JLabel("First Name");
		label_1.setBounds(97, 151, 115, 14);
		panel_1.add(label_1);
		
		JLabel label_2 = new JLabel("NickName");
		label_2.setBounds(97, 204, 104, 14);
		panel_1.add(label_2);
		
		JLabel label_3 = new JLabel("Speciality");
		label_3.setBounds(97, 247, 89, 14);
		panel_1.add(label_3);
		
		txtNickName = new JTextField();
		txtNickName.setColumns(10);
		txtNickName.setBounds(222, 201, 122, 20);
		panel_1.add(txtNickName);
		
		txtSpeciality = new JTextField();
		txtSpeciality.setColumns(10);
		txtSpeciality.setBounds(222, 244, 122, 20);
		panel_1.add(txtSpeciality);
		
		JButton button_1 = new JButton("Delete");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (tableDoctors.getSelectedRow() == -1) {
					if (tableDoctors.getSelectedRow() == 0) {
						JOptionPane.showMessageDialog(null,
								" Choose a doctor Please !!!!! ",
								"Delete Alerte ", 1);
					} else {
						JOptionPane.showMessageDialog(null,
								" Choose a doctor Please !!!!! ",
								"Delete Alerte ", 1);
					}
				} else {

					Doctor dr = new Doctor();
					int i = tableDoctors.getSelectedRow();
					int s = (int) tableDoctors.getModel().getValueAt(i, 0);
					dr.setId(s);
					DoctorServiceDelegate.deleteDoctor(dr);

					JOptionPane.showMessageDialog(null,
							"Doctor Deleted successfully   !!!!! ",
							"Delete Alerte", 1);

					
					initDataBindings();
				}
			}
		});
		button_1.setBounds(72, 310, 89, 23);
		panel_1.add(button_1);
		
		JButton button_2 = new JButton("MAil Him");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tableDoctors.getSelectedRow() == -1) {
					if (tableDoctors.getSelectedRow() == 0) {
						JOptionPane.showMessageDialog(null,
								" Choose a doctor Please !!!!! ",
								"Delete Alerte ", 1);
					} else {
						JOptionPane.showMessageDialog(null,
								" Choose a doctor Please !!!!! ",
								"Delete Alerte ", 1);
					}
				} else {

					Doctor dr = new Doctor();
					int i = tableDoctors.getSelectedRow();
					String email = (String) tableDoctors.getValueAt(i, 5);
					System.out.println("mail         " + email);
					String message = Message.sms;

					String[] to = { email.toString() };

					if (EmailSender.sendEmail("pigaleriedesarts@gmail.com",
							"ScrumMaster", message, to))
						System.out.println("email sent successfully");
					else
						System.out.println("some error occured");

				}
			}
		});
		button_2.setBounds(10, 11, 89, 23);
		panel_1.add(button_2);
		
		specialityFinder = new JTextField();
		specialityFinder.setColumns(10);
		specialityFinder.setBounds(217, 490, 86, 20);
		panel_1.add(specialityFinder);
		
		JButton button_3 = new JButton("Find doctor");
		button_3.setBounds(309, 489, 89, 23);
		panel_1.add(button_3);
		
		JLabel label_4 = new JLabel("Find Speciality");
		label_4.setBounds(104, 493, 108, 14);
		panel_1.add(label_4);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 602, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 408, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(58, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(5)
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 654, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(23)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 572, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(64, Short.MAX_VALUE))
		);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 13, 578, 628);
		panel.add(scrollPane);
		
		tableDoctors = new JTable();
		scrollPane.setViewportView(tableDoctors);
		setLayout(groupLayout);
		initDataBindings();

	}
	protected void initDataBindings() {
		JTableBinding<Doctor, List<Doctor>, JTable> jTableBinding = SwingBindings.createJTableBinding(UpdateStrategy.READ, doctors, tableDoctors);
		//
		BeanProperty<Doctor, Integer> doctorBeanProperty = BeanProperty.create("id");
		jTableBinding.addColumnBinding(doctorBeanProperty).setColumnName("id");
		//
		BeanProperty<Doctor, String> doctorBeanProperty_1 = BeanProperty.create("firstName");
		jTableBinding.addColumnBinding(doctorBeanProperty_1).setColumnName("first name");
		//
		BeanProperty<Doctor, String> doctorBeanProperty_2 = BeanProperty.create("lastName");
		jTableBinding.addColumnBinding(doctorBeanProperty_2).setColumnName("last name");
		//
		BeanProperty<Doctor, String> doctorBeanProperty_3 = BeanProperty.create("nickName");
		jTableBinding.addColumnBinding(doctorBeanProperty_3).setColumnName("nickname");
		//
		BeanProperty<Doctor, String> doctorBeanProperty_4 = BeanProperty.create("email");
		jTableBinding.addColumnBinding(doctorBeanProperty_4).setColumnName("email");
		//
		BeanProperty<Doctor, String> doctorBeanProperty_5 = BeanProperty.create("speciality");
		jTableBinding.addColumnBinding(doctorBeanProperty_5).setColumnName("speciality");
		//
		jTableBinding.bind();
	}
}
