package tn.esprit.medicaltourism.test;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import tn.esprit.medicaltourism.delegate.PatientServiceDelegate;
import tn.esprit.medicaltourism.domain.Patient;

public class PatientsUIBding extends JFrame {

	private JPanel contentPane;
	private JTable tablePatients;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JTextField txtLastName;
	private JTextField txtfirstName;
	private JButton btnNewButton;
	private JButton btnDelete;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PatientsUIBding frame = new PatientsUIBding();
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
	public PatientsUIBding() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1280, 720);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(70, 84, 321, 309);
		contentPane.add(panel);

		tablePatients = new JTable();
		panel.setLayout(new BorderLayout());
		panel.add(tablePatients, BorderLayout.CENTER);
		panel.add(tablePatients.getTableHeader(), BorderLayout.NORTH);

		tablePatients.setBackground(new Color(0, 0, 0, 0));
		tablePatients
				.setFont(new java.awt.Font("Avenir Next Condensed", 0, 14)); // NOI18N

		tablePatients.setForeground(new java.awt.Color(153, 0, 2));

		tablePatients.setOpaque(false);
		panel.add(tablePatients);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(435, 84, 346, 309);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		lblNewLabel = new JLabel("Last Name");
		lblNewLabel.setBounds(50, 47, 71, 14);
		panel_1.add(lblNewLabel);

		lblNewLabel_1 = new JLabel("First Name");
		lblNewLabel_1.setBounds(63, 89, 90, 14);
		panel_1.add(lblNewLabel_1);

		txtLastName = new JTextField();
		txtLastName.setBounds(131, 44, 86, 20);
		panel_1.add(txtLastName);
		txtLastName.setColumns(10);

		txtfirstName = new JTextField();
		txtfirstName.setBounds(131, 86, 86, 20);
		panel_1.add(txtfirstName);
		txtfirstName.setColumns(10);

		btnNewButton = new JButton("update");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (tablePatients.getSelectedRow() == -1) {
					if (tablePatients.getSelectedRow() == 0) {
						JOptionPane.showMessageDialog(null,
								" Choose a Patient Please !!!!! ",
								"Update Alerte ", 1);
					} else {
						JOptionPane.showMessageDialog(null,
								" Choose a Patient Please !!!!! ",
								"Update Alerte ", 1);
					}
				} else {

					Patient p = new Patient();
					int i = tablePatients.getSelectedRow();
					int s = (int) tablePatients.getModel().getValueAt(i, 0);
					p.setId(s);

					p.setLastName(txtLastName.getText());
					p.setFirstName(txtfirstName.getText());
					PatientServiceDelegate.updatePatient(p);
					JOptionPane.showMessageDialog(null,
							"Patient Updated successfully   !!!!! ",
							"Update Alerte", 1);
					PatientsUIBding frame = new PatientsUIBding();
					frame.setVisible(true);
					dispose();

				}

			}
		});
		btnNewButton.setBounds(143, 146, 89, 23);
		panel_1.add(btnNewButton);

		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (tablePatients.getSelectedRow() == -1) {
					if (tablePatients.getSelectedRow() == 0) {
						JOptionPane.showMessageDialog(null,
								" Choose a doctor Please !!!!! ",
								"Delete Alerte ", 1);
					} else {
						JOptionPane.showMessageDialog(null,
								" Choose a doctor Please !!!!! ",
								"Delete Alerte ", 1);
					}
				} else {
					Patient p = new Patient();
					int i = tablePatients.getSelectedRow();
					int s = (int) tablePatients.getModel().getValueAt(i, 0);
					p.setId(s);
					PatientServiceDelegate.deletePatient(p);

					JOptionPane.showMessageDialog(null,
							"Patient Deleted successfully   !!!!! ",
							"Delete Alerte", 1);
					PatientsUIBding frame = new PatientsUIBding();
					frame.setVisible(true);
					dispose();
				}
			}
		});
		btnDelete.setBounds(32, 259, 89, 23);
		panel_1.add(btnDelete);
	}
}
