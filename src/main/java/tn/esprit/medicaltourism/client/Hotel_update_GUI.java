package tn.esprit.medicaltourism.client;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.DefaultComboBoxModel;

public class Hotel_update_GUI extends JPanel {
	 JTextField tf_adress;
	 JTextField tf_name;
	JComboBox cmb_stars ;
	JTextArea ta_brief;
	/**
	 * Create the panel.
	 */
	public Hotel_update_GUI() {
		
		JLabel label = new JLabel("Name");
		label.setFont(new Font("Source Sans Pro Semibold", Font.BOLD, 16));
		
		JLabel label_1 = new JLabel("Adress");
		label_1.setFont(new Font("Source Sans Pro Semibold", Font.BOLD, 16));
		
		JLabel label_2 = new JLabel("brief");
		label_2.setFont(new Font("Source Sans Pro Semibold", Font.BOLD, 16));
		
		JLabel label_3 = new JLabel("Stars");
		label_3.setFont(new Font("Source Sans Pro Semibold", Font.BOLD, 16));
		
		 cmb_stars = new JComboBox();
		cmb_stars.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5"}));
		
		tf_adress = new JTextField();
		tf_adress.setColumns(10);
		
		tf_name = new JTextField();
		tf_name.setColumns(10);
		
		 ta_brief = new JTextArea();
		ta_brief.setRows(8);
		
		JButton btn_update = new JButton("update");
		btn_update.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		
		JButton btnReset = new JButton("reset");
		btnReset.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(20)
					.addComponent(label)
					.addGap(62)
					.addComponent(tf_name, GroupLayout.PREFERRED_SIZE, 341, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(20)
					.addComponent(label_1)
					.addGap(55)
					.addComponent(tf_adress, GroupLayout.PREFERRED_SIZE, 341, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(20)
					.addComponent(label_3)
					.addGap(66)
					.addComponent(cmb_stars, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(20)
					.addComponent(label_2)
					.addGap(69)
					.addComponent(ta_brief, GroupLayout.PREFERRED_SIZE, 341, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(285)
					.addComponent(btn_update)
					.addGap(18)
					.addComponent(btnReset))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(37)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(10)
							.addComponent(label))
						.addComponent(tf_name, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(9)
							.addComponent(label_1))
						.addComponent(tf_adress, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
					.addGap(13)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(10)
							.addComponent(label_3))
						.addComponent(cmb_stars, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(15)
							.addComponent(label_2))
						.addComponent(ta_brief, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(31)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btn_update)
						.addComponent(btnReset)))
		);
		setLayout(groupLayout);

	}

}
