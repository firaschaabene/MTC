package tn.esprit.medicaltourism.test;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.naming.NamingException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;



import javax.swing.JButton;








import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;

import tn.esprit.medicaltourism.adapters.DoctorsAdapter;
import tn.esprit.medicaltourism.delegate.DoctorServiceDelegate;
import tn.esprit.medicaltourism.domain.Doctor;
import tn.esprit.medicaltourism.mail.EmailSender;
import tn.esprit.medicaltourism.mail.Message;

public class DoctorsUi extends JFrame {

	private JPanel contentPane;
	private JTable tableDoctors;
	private JTextField txtLastName;
	private JTextField txtFirstName;
	private JTextField txtNickName;
	private JTextField txtSpeciality;
	private JTextField specialityFinder;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DoctorsUi frame = new DoctorsUi();
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
	public DoctorsUi() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1280, 720);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(66, 47, 450, 506);
		contentPane.add(panel);

		tableDoctors = new JTable(new DoctorsAdapter());
		panel.setLayout(new BorderLayout());
		panel.add(tableDoctors, BorderLayout.CENTER);
		panel.add(tableDoctors.getTableHeader(), BorderLayout.NORTH);

		tableDoctors.setBackground(new Color(0, 0, 0, 0));
		tableDoctors.setFont(new java.awt.Font("Avenir Next Condensed", 0, 14)); // NOI18N

		tableDoctors.setForeground(new java.awt.Color(153, 0, 2));

		tableDoctors.setOpaque(false);

		panel.add(tableDoctors);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(826, 76, 408, 572);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		txtLastName = new JTextField();
		txtLastName.setBounds(222, 102, 122, 20);
		panel_1.add(txtLastName);
		txtLastName.setColumns(10);

		txtFirstName = new JTextField();
		txtFirstName.setBounds(222, 148, 122, 20);
		panel_1.add(txtFirstName);
		txtFirstName.setColumns(10);

		JButton btnValider = new JButton("Update");
		btnValider.addActionListener(new ActionListener() {
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

					DoctorsUi frame = new DoctorsUi();
					frame.setVisible(true);
					dispose();

				}
			}
		});
		btnValider.setBounds(255, 310, 89, 23);
		panel_1.add(btnValider);

		JLabel lblNewLabel = new JLabel("Last Name");
		lblNewLabel.setBounds(97, 105, 104, 14);
		panel_1.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("First Name");
		lblNewLabel_1.setBounds(97, 151, 115, 14);
		panel_1.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("NickName");
		lblNewLabel_2.setBounds(97, 204, 104, 14);
		panel_1.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Speciality");
		lblNewLabel_3.setBounds(97, 247, 89, 14);
		panel_1.add(lblNewLabel_3);

		txtNickName = new JTextField();
		txtNickName.setBounds(222, 201, 122, 20);
		panel_1.add(txtNickName);
		txtNickName.setColumns(10);

		txtSpeciality = new JTextField();
		txtSpeciality.setBounds(222, 244, 122, 20);
		panel_1.add(txtSpeciality);
		txtSpeciality.setColumns(10);

		JButton btnNewButton = new JButton("Delete");
		btnNewButton.setBounds(72, 310, 89, 23);
		panel_1.add(btnNewButton);

		JButton btnMailHim = new JButton("MAil Him");
		btnMailHim.addActionListener(new ActionListener() {
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
		btnMailHim.setBounds(10, 11, 89, 23);
		panel_1.add(btnMailHim);
		
		specialityFinder = new JTextField();
		specialityFinder.setBounds(217, 490, 86, 20);
		panel_1.add(specialityFinder);
		specialityFinder.setColumns(10);
		
		JButton FindDoctor = new JButton("Find doctor");
		FindDoctor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			
			}
		});
		FindDoctor.setBounds(309, 489, 89, 23);
		panel_1.add(FindDoctor);
		
		JLabel lblFindSpeciality = new JLabel("Find Speciality");
		lblFindSpeciality.setBounds(104, 493, 108, 14);
		panel_1.add(lblFindSpeciality);
		btnNewButton.addActionListener(new ActionListener() {
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

					DoctorsUi frame = new DoctorsUi();
					frame.setVisible(true);
					dispose();
				}
			}
		});
	}
}
