package tn.esprit.medicaltourism.test;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.regex.Pattern;

import javax.swing.DefaultRowSorter;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import tn.esprit.medicaltourism.delegate.MedicalRecordsServiceDelegate;
import tn.esprit.medicaltourism.delegate.PatientServiceDelegate;
import tn.esprit.medicaltourism.domain.MedicalRecords;
import tn.esprit.medicaltourism.domain.Patient;
import org.jdesktop.swingbinding.JTableBinding;
import org.jdesktop.swingbinding.SwingBindings;
import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;
import org.jdesktop.beansbinding.BeanProperty;

public class RechercheDossierMedicalPanl extends JPanel {
	private JTextField med_tf;
	private JTextField cin_tf;
	private static JTable table;
	private Patient p;
	private MedicalRecords mr;
	   List<Patient> lp = PatientServiceDelegate.findAllPatient();
	/**
	 * Create the panel.
	 */
	public RechercheDossierMedicalPanl() {
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 1015, 597);
		add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Search", TitledBorder.LEFT, TitledBorder.BELOW_TOP, null, new Color(0, 204, 153)));
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(21, 11, 695, 64);
		panel.add(panel_1);
		
		JLabel label = new JLabel("By N\u00B0 Medical Record :");
		label.setBounds(10, 26, 134, 14);
		panel_1.add(label);
		
		med_tf = new JTextField();
		med_tf.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				applyTableFilter(med_tf.getText(),table);
			}
		});
		med_tf.setColumns(10);
		med_tf.setBounds(154, 23, 101, 20);
		panel_1.add(med_tf);
		
		JLabel label_1 = new JLabel("By Cin N\u00B0 :");
		label_1.setBounds(425, 26, 86, 14);
		panel_1.add(label_1);
		
		cin_tf = new JTextField();
		cin_tf.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				applyTableFilter(cin_tf.getText(),table);
			}
		});
		cin_tf.setColumns(10);
		cin_tf.setBounds(521, 23, 110, 20);
		panel_1.add(cin_tf);
		JLabel lmsg = new JLabel("");
		lmsg.setBounds(68, 366, 129, 14);
		panel.add(lmsg);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(300, 137, 416, 235);
		panel.add(scrollPane);
		table = new JTable();
		table.setAutoCreateRowSorter(true);
		scrollPane.setViewportView(table);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
DefaultTableModel model_table = (DefaultTableModel) table.getModel();
				
				int id = (int) model_table.getValueAt(table.getSelectedRow(), 0);
			p=PatientServiceDelegate.findPatient(id);
//			    p=	lp.get(table.getSelectedRow());
	        	System.out.println(p);
				int id1 = (int) model_table.getValueAt(table.getSelectedRow(), 4);
				mr=MedicalRecordsServiceDelegate.find(id1);
				lmsg.setText("");
			}
		});
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
			},
			new String[] {
				"id", "First Name", "Last Name", "CIN N\u00B0", "Medical Record N\u00B0"
			}
		));
		
		JLabel label_2 = new JLabel("Medical Record");
		label_2.setBounds(75, 155, 107, 14);
		panel.add(label_2);
		
		JButton view_bt = new JButton("View");
		view_bt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 if (table.getSelectedRow() == -1) {
			            if (table.getRowCount() == 0) {
			            	lmsg.setText("Select Vide");
			            } else {
			            	lmsg.setText("You should select a Patient");
			            }
			        } else {
			    
			        	ConsulterDossierMedicalFram panl = new ConsulterDossierMedicalFram(p,mr);
						panl.setVisible(true);
			        }
			}
		});
		view_bt.setBounds(67, 223, 99, 23);
		panel.add(view_bt);
		
		JButton btnPrint = new JButton("print");
		btnPrint.setBounds(64, 291, 89, 23);
		panel.add(btnPrint);
		table.getColumnModel().getColumn(0).setPreferredWidth(0);
		table.getColumnModel().getColumn(0).setMinWidth(0);
		table.getColumnModel().getColumn(0).setMaxWidth(0);
		table.getColumnModel().getColumn(4).setPreferredWidth(98);
		
		

//		initTablePatint();
		initDataBindings();
	}
	////
	 public static void initTablePatint()
	    {
	        DefaultTableModel model = (DefaultTableModel) table.getModel();             
	  //      List<Patient> lp = PatientServiceDelegate.findAllPatient();
//	        for (Patient p : lp)
//	            
//	        {
//	            model.addRow(new Object[]
//	            {
//	            		p.getId(),p.getFirstName(),p.getLastName(),p.getCinNumber(),p.getMedical().getId(),
//	                    
//	            });
//		        System.out.println(""+p.getFirstName());
//
//	        }
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
	protected void initDataBindings() {
		JTableBinding<Patient, List<Patient>, JTable> jTableBinding = SwingBindings.createJTableBinding(UpdateStrategy.READ, lp, table);
		//
		BeanProperty<Patient, String> patientBeanProperty = BeanProperty.create("firstName");
		jTableBinding.addColumnBinding(patientBeanProperty).setColumnName("first name");
		//
		BeanProperty<Patient, Integer> patientBeanProperty_1 = BeanProperty.create("id");
		jTableBinding.addColumnBinding(patientBeanProperty_1).setColumnName("Id");
		//
		BeanProperty<Patient, String> patientBeanProperty_2 = BeanProperty.create("lastName");
		jTableBinding.addColumnBinding(patientBeanProperty_2).setColumnName("last name");
		//
		BeanProperty<Patient, Integer> patientBeanProperty_3 = BeanProperty.create("cinNumber");
		jTableBinding.addColumnBinding(patientBeanProperty_3).setColumnName("cin");
		//
		BeanProperty<Patient, MedicalRecords> patientBeanProperty_4 = BeanProperty.create("medical");
		jTableBinding.addColumnBinding(patientBeanProperty_4).setColumnName("n\u00B0 medical record");
		//
		jTableBinding.bind();
	}
}
