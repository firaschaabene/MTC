package tn.esprit.medicaltourism.client;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;

import tn.esprit.medicaltourism.delegate.HotelServiceDelegate;
import tn.esprit.medicaltourism.domain.Hotel;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Hotel_add_GUI extends JPanel {
	private JTextField tf_name;
	private JTextField tf_adress;

	/**
	 * Create the panel.
	 */
	public Hotel_add_GUI() {
		
		tf_name = new JTextField();
		tf_name.setColumns(10);
		
		tf_adress = new JTextField();
		tf_adress.setColumns(10);
		
		JTextArea ta_brief = new JTextArea();
		ta_brief.setRows(8);
		
		JComboBox cmb_stars = new JComboBox();
		cmb_stars.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5"}));
		
		JButton btn_ok = new JButton("OK");
		btn_ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Hotel hotel=new Hotel(tf_name.getText(), Integer.parseInt(cmb_stars.getSelectedItem().toString()), tf_adress.getText(), ta_brief.getText());
				if(HotelServiceDelegate.create(hotel)!=null){
					tf_name.setText("");
					tf_adress.setText("");
					ta_brief.setText("");
					cmb_stars.setSelectedIndex(0);
				}
			}
		});
		btn_ok.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		
		JButton btn_cancel = new JButton("Cancel");
		btn_cancel.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		
		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Source Sans Pro Semibold", Font.BOLD, 16));
		
		JLabel lblAdress = new JLabel("Adress");
		lblAdress.setFont(new Font("Source Sans Pro Semibold", Font.BOLD, 16));
		
		JLabel lblStars = new JLabel("Stars");
		lblStars.setFont(new Font("Source Sans Pro Semibold", Font.BOLD, 16));
		
		JLabel lblBrief = new JLabel("brief");
		lblBrief.setFont(new Font("Source Sans Pro Semibold", Font.BOLD, 16));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(71)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblName)
								.addComponent(lblAdress)
								.addComponent(lblBrief)
								.addComponent(lblStars))
							.addGap(33)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(cmb_stars, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
									.addComponent(tf_name, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 333, Short.MAX_VALUE)
									.addComponent(tf_adress, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 333, Short.MAX_VALUE)
									.addComponent(ta_brief, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 333, Short.MAX_VALUE))))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btn_ok)
							.addGap(18)
							.addComponent(btn_cancel)))
					.addGap(83))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(tf_name, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblName))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(tf_adress, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblAdress))
					.addGap(32)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(cmb_stars, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblStars))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(ta_brief, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblBrief))
					.addPreferredGap(ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btn_ok)
						.addComponent(btn_cancel))
					.addGap(26))
		);
		setLayout(groupLayout);

	}
}
