package tn.esprit.medicaltourism.test;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.swingbinding.JTableBinding;
import org.jdesktop.swingbinding.SwingBindings;

import tn.esprit.medicaltourism.delegate.ConsultantServiceDelegate;
import tn.esprit.medicaltourism.domain.Consultant;

public class ConsultantUiBinding extends JPanel {
	private JTable TableConsultant;
	List<Consultant> consultants = new ArrayList<Consultant>();
	private JTextField txtLastName;
	private JTextField txtFirstName;
	private JTextField textField_2;

	/**
	 * Create the panel.
	 */
	public ConsultantUiBinding() {
		setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(73, 113, 387, 287);
		add(panel);
		panel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(348, 271, -329, -264);
		panel.add(scrollPane);

		TableConsultant = new JTable();
		TableConsultant.setBounds(294, 191, -245, -135);
		panel.add(TableConsultant);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(488, 105, 332, 337);
		add(panel_1);
		panel_1.setLayout(null);

		txtLastName = new JTextField();
		txtLastName.setColumns(10);
		txtLastName.setBounds(150, 11, 122, 20);
		panel_1.add(txtLastName);

		txtFirstName = new JTextField();
		txtFirstName.setColumns(10);
		txtFirstName.setBounds(150, 57, 122, 20);
		panel_1.add(txtFirstName);

		JButton button = new JButton("Update");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (TableConsultant.getSelectedRow() == -1) {
					if (TableConsultant.getSelectedRow() == 0) {
						JOptionPane.showMessageDialog(null,
								" Choose a Consultant Please !!!!! ",
								"Update Alerte ", 1);
					} else {
						JOptionPane.showMessageDialog(null,
								" Choose a Consultant Please !!!!! ",
								"Update Alerte ", 1);
					}
				} else {

					Consultant c1 = new Consultant();
					int i = TableConsultant.getSelectedRow();
					int s = (int) TableConsultant.getModel().getValueAt(i, 0);
					c1.setId(s);
					c1.setLastName(txtLastName.getText());
					c1.setFirstName(txtFirstName.getText());

					ConsultantServiceDelegate.updateConsultant(c1);
					JOptionPane.showMessageDialog(null,
							"Consultant Updated successfully   !!!!! ",
							"Update Alerte", 1);

					initDataBindings();

				}
			}
		});
		button.setBounds(174, 123, 89, 23);
		panel_1.add(button);

		JLabel label = new JLabel("Last Name");
		label.setBounds(25, 14, 104, 14);
		panel_1.add(label);

		JLabel label_1 = new JLabel("First Name");
		label_1.setBounds(25, 60, 115, 14);
		panel_1.add(label_1);

		JButton button_1 = new JButton("Delete");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (TableConsultant.getSelectedRow() == -1) {
					if (TableConsultant.getSelectedRow() == 0) {
						JOptionPane.showMessageDialog(null,
								" Choose a Consultant Please !!!!! ",
								"Delete Alerte ", 1);
					} else {
						JOptionPane.showMessageDialog(null,
								" Choose a Consultant Please !!!!! ",
								"Delete Alerte ", 1);
					}
				} else {

					Consultant c1 = new Consultant();
					int i = TableConsultant.getSelectedRow();
					int s = (int) TableConsultant.getModel().getValueAt(i, 0);
					c1.setId(s);
					ConsultantServiceDelegate.deleteConsultant(c1);

					JOptionPane.showMessageDialog(null,
							"Consultant Deleted successfully   !!!!! ",
							"Delete Alerte", 1);
					initDataBindings();

				}
			}
		});
		button_1.setBounds(22, 257, 89, 23);
		panel_1.add(button_1);

		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(145, 399, 86, 20);
		panel_1.add(textField_2);
		initDataBindings();

	}

	protected void initDataBindings() {
		JTableBinding<Consultant, List<Consultant>, JTable> jTableBinding = SwingBindings
				.createJTableBinding(UpdateStrategy.READ, consultants,
						TableConsultant);
		//
		BeanProperty<Consultant, Integer> consultantBeanProperty = BeanProperty
				.create("id");
		jTableBinding.addColumnBinding(consultantBeanProperty).setColumnName(
				"id");
		//
		BeanProperty<Consultant, String> consultantBeanProperty_1 = BeanProperty
				.create("lastName");
		jTableBinding.addColumnBinding(consultantBeanProperty_1).setColumnName(
				"Last Name");
		//
		BeanProperty<Consultant, String> consultantBeanProperty_2 = BeanProperty
				.create("firstName");
		jTableBinding.addColumnBinding(consultantBeanProperty_2).setColumnName(
				"First Name");
		//
		BeanProperty<Consultant, String> consultantBeanProperty_3 = BeanProperty
				.create("nickName");
		jTableBinding.addColumnBinding(consultantBeanProperty_3).setColumnName(
				"Nick Name");
		//
		jTableBinding.bind();
	}
}
