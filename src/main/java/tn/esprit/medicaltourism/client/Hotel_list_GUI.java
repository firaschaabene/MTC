package tn.esprit.medicaltourism.client;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.swingbinding.JTableBinding;
import org.jdesktop.swingbinding.SwingBindings;

import tn.esprit.medicaltourism.delegate.HotelServiceDelegate;
import tn.esprit.medicaltourism.domain.Hotel;
import javax.swing.LayoutStyle.ComponentPlacement;

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
	
	
	public Hotel_list_GUI() {
		initComponent();
		initDataBindings();
		
		scrollPane_listhotels.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

			}
		});
		
		
		gl_panel.setHorizontalGroup(gl_panel
				.createParallelGroup(Alignment.LEADING)
				.addGroup(
						gl_panel.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.LEADING)
												.addComponent(
														scrollPane_listhotels,
														GroupLayout.PREFERRED_SIZE,
														532,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(
														panel_update,
														GroupLayout.DEFAULT_SIZE,
														556, Short.MAX_VALUE))
								.addContainerGap()));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(
				Alignment.LEADING).addGroup(
				gl_panel.createSequentialGroup()
						.addContainerGap()
						.addComponent(scrollPane_listhotels,
								GroupLayout.PREFERRED_SIZE, 264,
								GroupLayout.PREFERRED_SIZE)
						.addGap(35)
						.addComponent(panel_update, GroupLayout.DEFAULT_SIZE,
								230, Short.MAX_VALUE).addContainerGap()));

	
		table_hotels.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				hotel = hotels.get(table_hotels.getSelectedRow());
				tf_name.setText(hotel.getName());
				cmb_stars.setSelectedIndex(hotel.getStar()-1);
				tf_adress.setText(hotel.getAddress());
				ta_brief.setText(hotel.getDescription());
				
				
				
			}
		});
		

	}

	private void initComponent() {
		table_hotels = new JTable();
		hotels = HotelServiceDelegate.findAll();
		scrollPane_listhotels = new JScrollPane();
		scrollPane_listhotels.setViewportView(table_hotels);

		JPanel panel = new JPanel();
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(
				Alignment.LEADING).addGroup(
				groupLayout
						.createSequentialGroup()
						.addContainerGap()
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 580,
								GroupLayout.PREFERRED_SIZE).addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(
				Alignment.LEADING).addGroup(
				groupLayout
						.createSequentialGroup()
						.addContainerGap()
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 555,
								GroupLayout.PREFERRED_SIZE)
						.addContainerGap(76, Short.MAX_VALUE)));

		 panel_update = new JPanel();
		 gl_panel = new GroupLayout(panel);
		
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
		 cmb_stars.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5"}));

		JLabel label_3 = new JLabel("brief");
		label_3.setFont(new Font("Source Sans Pro Semibold", Font.BOLD, 16));

		 ta_brief = new JTextArea();
		ta_brief.setRows(8);

		JButton btn_update = new JButton("update");
		btn_update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hotel.setName(tf_name.getText());
				hotel.setAddress(tf_adress.getText());
				hotel.setStar(Integer.parseInt(cmb_stars.getSelectedItem().toString()));
				hotel.setDescription(ta_brief.getText());
				HotelServiceDelegate.update(hotel);
				JOptionPane.showMessageDialog(null, "updated successfuly !");
				hotels=HotelServiceDelegate.findAll();
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
				hotel.setStar(Integer.parseInt(cmb_stars.getSelectedItem().toString()));
				hotel.setDescription(ta_brief.getText());
				HotelServiceDelegate.delete(hotel);
				JOptionPane.showMessageDialog(null, "Delelted successfuly !");
				hotels=HotelServiceDelegate.findAll();
				initDataBindings();
				
			}
		});
		btn_delete.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		GroupLayout gl_panel_update = new GroupLayout(panel_update);
		gl_panel_update.setHorizontalGroup(
			gl_panel_update.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_update.createSequentialGroup()
					.addGroup(gl_panel_update.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_update.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panel_update.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_panel_update.createSequentialGroup()
									.addComponent(label)
									.addGap(18)
									.addComponent(tf_name, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel_update.createParallelGroup(Alignment.LEADING)
									.addGroup(gl_panel_update.createSequentialGroup()
										.addComponent(label_2)
										.addGap(18)
										.addComponent(cmb_stars, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addGroup(gl_panel_update.createSequentialGroup()
										.addComponent(label_1)
										.addGap(5)
										.addComponent(tf_adress, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
							.addGap(35)
							.addComponent(label_3)
							.addGap(26)
							.addComponent(ta_brief, GroupLayout.PREFERRED_SIZE, 227, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_update.createSequentialGroup()
							.addGap(196)
							.addComponent(btn_update)
							.addGap(5)
							.addComponent(btn_reset)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btn_delete)))
					.addContainerGap(49, Short.MAX_VALUE))
		);
		gl_panel_update.setVerticalGroup(
			gl_panel_update.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_update.createSequentialGroup()
					.addGap(26)
					.addGroup(gl_panel_update.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_update.createSequentialGroup()
							.addGroup(gl_panel_update.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_update.createParallelGroup(Alignment.BASELINE)
									.addComponent(tf_name, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addComponent(label_3))
								.addComponent(label))
							.addGap(23)
							.addGroup(gl_panel_update.createParallelGroup(Alignment.LEADING)
								.addComponent(tf_adress, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panel_update.createSequentialGroup()
									.addGap(3)
									.addComponent(label_1)))
							.addGap(21)
							.addGroup(gl_panel_update.createParallelGroup(Alignment.BASELINE)
								.addComponent(label_2)
								.addComponent(cmb_stars, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addComponent(ta_brief, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE))
					.addGap(25)
					.addGroup(gl_panel_update.createParallelGroup(Alignment.LEADING, false)
						.addComponent(btn_delete, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btn_update, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btn_reset, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addContainerGap())
		);
		panel_update.setLayout(gl_panel_update);
		panel.setLayout(gl_panel);
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
}
