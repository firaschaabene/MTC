package tn.esprit.medicaltourism.test;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import tn.esprit.medicaltourism.delegate.HotelServiceDelegate;
import tn.esprit.medicaltourism.delegate.PackServiceDelegate;
import tn.esprit.medicaltourism.domain.Pack;

import com.toedter.calendar.JDateChooser;
import javax.swing.JComboBox;
import java.awt.Color;

public class Pack_update_GUI extends JFrame {

	private JPanel contentPane;
	private JTextField tf_name;
	private JTextField tf_price;
	public static Pack pack = new Pack();
	JLabel lb_state_value;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Pack_update_GUI frame = new Pack_update_GUI(pack);
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
	public Pack_update_GUI(Pack p) {
		pack=p;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 732, 517);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblName = new JLabel("Name");
		lblName.setBounds(5, 5, 79, 26);
		contentPane.add(lblName);

		JLabel lbl_StartDate = new JLabel("Start Date");
		lbl_StartDate.setBounds(5, 44, 84, 16);
		contentPane.add(lbl_StartDate);

		JDateChooser dateChooser_endDate = new JDateChooser();
		dateChooser_endDate.setDateFormatString("yyyy-MM-dd");
		dateChooser_endDate.setBounds(118, 85, 116, 22);
		contentPane.add(dateChooser_endDate);

		JDateChooser dateChooser_start = new JDateChooser();
		dateChooser_start.setDateFormatString("yyyy-MM-dd");
		dateChooser_start.setBounds(118, 44, 116, 22);
		contentPane.add(dateChooser_start);

		JLabel lbl_End_Date = new JLabel("End Date");
		lbl_End_Date.setBounds(5, 85, 56, 16);
		contentPane.add(lbl_End_Date);

		JLabel lbl_price = new JLabel("Price");
		lbl_price.setBounds(12, 135, 56, 16);
		contentPane.add(lbl_price);

		tf_name = new JTextField();
		tf_name.setBounds(118, 7, 116, 22);
		contentPane.add(tf_name);
		tf_name.setColumns(10);

		tf_price = new JTextField();
		tf_price.setBounds(118, 132, 116, 22);
		contentPane.add(tf_price);
		tf_price.setColumns(10);

		JTextArea ta_desc = new JTextArea();
		ta_desc.setBounds(118, 315, 209, 142);
		contentPane.add(ta_desc);

		JLabel lbl_desc = new JLabel("description");
		lbl_desc.setBounds(5, 360, 79, 50);
		contentPane.add(lbl_desc);

		JButton btn_save = new JButton("Save");
		btn_save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pack.setName(tf_name.getText());
				pack.setPrice(Float.parseFloat((tf_price.getText())));
				pack.setDescription(ta_desc.getText());
				java.util.Date utilDate = dateChooser_start.getDate();
				java.sql.Date sqlDateS = new java.sql.Date(utilDate.getTime());
				pack.setStartDate(sqlDateS + "");
				java.util.Date utilDate1 = dateChooser_endDate.getDate();
				java.sql.Date sqlDateE = new java.sql.Date(utilDate1.getTime());
				pack.setEndDate(sqlDateE + "");

				PackServiceDelegate.update(pack);
				JOptionPane.showMessageDialog(null, "update successfuly !");
				dispose();
			}
		});
		btn_save.setBounds(471, 400, 97, 25);
		contentPane.add(btn_save);

		JButton btn_cancel = new JButton("Cancel");
		btn_cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btn_cancel.setBounds(591, 400, 97, 25);
		contentPane.add(btn_cancel);

		JButton btnDisable = new JButton("disable/enable");
		btnDisable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (lb_state_value.getText() == "disabled") {
					lb_state_value.setText("enabled");
					pack.setState(true);
				} else {
					lb_state_value.setText("disabled");
					pack.setState(false);
				}
			}

		});
		btnDisable.setBounds(356, 400, 97, 25);
		contentPane.add(btnDisable);

		JLabel lb_state = new JLabel("State");
		lb_state.setBounds(12, 182, 72, 26);
		contentPane.add(lb_state);

		lb_state_value = new JLabel("enabled");
		lb_state_value.setForeground(Color.GRAY);
		lb_state_value.setBounds(123, 187, 56, 16);
		contentPane.add(lb_state_value);
	}
}
