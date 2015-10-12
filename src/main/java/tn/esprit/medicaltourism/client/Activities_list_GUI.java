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
import com.toedter.calendar.JDayChooser;
import com.toedter.calendar.JDateChooser;

public class Activities_list_GUI extends JPanel {

	private static final long serialVersionUID = 1L;
	List<Activity> activities = new ArrayList<Activity>();
	Activity activity = new Activity();

	JTextField tf_name;
	JTextField tf_price;
	JDateChooser startdateChooser ;
	JDateChooser enddateChooser ;
	JTable table_activities;
	JScrollPane scrollPane_activities;
	GroupLayout gl_panel;
	JPanel panel_update;
	JTextArea ta_desc;
	private JButton btn_delete;

	public Activities_list_GUI() {
		initComponent();
		initDataBindings();

		scrollPane_activities.addMouseListener(new MouseAdapter() {
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
														scrollPane_activities,
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
						.addComponent(scrollPane_activities,
								GroupLayout.PREFERRED_SIZE, 264,
								GroupLayout.PREFERRED_SIZE)
						.addGap(35)
						.addComponent(panel_update, GroupLayout.DEFAULT_SIZE,
								230, Short.MAX_VALUE).addContainerGap()));

		table_activities.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				activity = activities.get(table_activities.getSelectedRow());
				tf_name.setText(activity.getName());

				ta_desc.setText(activity.getDescription());

			}
		});

	}

	private void initComponent() {
		table_activities = new JTable();
		activities =ActivityServiceDelegate.findAll();
		scrollPane_activities = new JScrollPane();
		scrollPane_activities.setViewportView(table_activities);
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

		JLabel lb_startdate = new JLabel("start date");
		lb_startdate.setFont(new Font("Source Sans Pro Semibold", Font.BOLD, 16));

//		tf_adress = new JTextField();
//		tf_adress.setColumns(10);

	

		JLabel label_3 = new JLabel("brief");
		label_3.setFont(new Font("Source Sans Pro Semibold", Font.BOLD, 16));

		 ta_desc = new JTextArea();
		 ta_desc.setRows(8);

		JButton btn_update = new JButton("update");
		btn_update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				activity.setName(tf_name.getText());
//				activity.setAddress(tf_adress.getText());
				 java.util.Date utilDate = startdateChooser.getDate();
				 java.sql.Date sqlDateS = new java.sql.Date(utilDate.getTime());
				activity.setStartDate(sqlDateS+"");
				java.util.Date utilDate1 = enddateChooser.getDate();
				 java.sql.Date sqlDateE = new java.sql.Date(utilDate1.getTime());
				activity.setEndDate(sqlDateE+"");
				activity.setDescription(ta_desc.getText());
				ActivityServiceDelegate.update(activity);
				JOptionPane.showMessageDialog(null, "updated successfuly !");
				activities=ActivityServiceDelegate.findAll();
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
			
//				activity.setAddress(tf_adress.getText());
//				activity.setStar(Integer.parseInt(cmb_stars.getSelectedItem().toString()));
				activity.setDescription(ta_desc.getText());
				ActivityServiceDelegate.delete(activity);
				JOptionPane.showMessageDialog(null, "Delelted successfuly !");
				activities=ActivityServiceDelegate.findAll();
				initDataBindings();
				
			}
		});
		btn_delete.setFont(new Font("Segoe Print", Font.PLAIN, 16));
		
		JTextArea textArea = new JTextArea();
		
		JLabel lblEndDate = new JLabel("end date");
		lblEndDate.setFont(new Font("Source Sans Pro Semibold", Font.BOLD, 16));
		
		 startdateChooser = new JDateChooser();
		 startdateChooser.setDateFormatString("yyyy-MM-dd");
		
		 enddateChooser = new JDateChooser();
		 enddateChooser.setDateFormatString("yyyy-MM-dd");
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
									.addComponent(label_3))))
						.addGroup(gl_panel_update.createSequentialGroup()
							.addComponent(btn_update)
							.addGap(5)
							.addGroup(gl_panel_update.createSequentialGroup()
								.addComponent(btn_reset)
								.addGap(18)
								.addComponent(btn_delete))))
					.addPreferredGap(ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
					.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 216, GroupLayout.PREFERRED_SIZE)
					.addGap(21))
		);
		gl_panel_update.setVerticalGroup(
			gl_panel_update.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_panel_update.createSequentialGroup()
					.addGap(26)
					.addGroup(gl_panel_update.createParallelGroup(Alignment.LEADING)
						.addComponent(textArea, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)
						.addGroup(gl_panel_update.createSequentialGroup()
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
							.addPreferredGap(ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
							.addGroup(gl_panel_update.createParallelGroup(Alignment.LEADING, false)
								.addComponent(btn_update, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addGroup(gl_panel_update.createParallelGroup(Alignment.BASELINE)
									.addComponent(btn_reset)
									.addComponent(btn_delete)))
							.addContainerGap())))
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
		BeanProperty<Activity, Date> activityBeanProperty_2 = BeanProperty.create("startDate");
		jTableBinding.addColumnBinding(activityBeanProperty_2).setColumnName("StartDate");
		//
		BeanProperty<Activity, Date> activityBeanProperty_3 = BeanProperty.create("endDate");
		jTableBinding.addColumnBinding(activityBeanProperty_3).setColumnName("EndDate");
		//
		jTableBinding.bind();
	}
}
