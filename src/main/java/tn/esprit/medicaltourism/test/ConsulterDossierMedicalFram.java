package tn.esprit.medicaltourism.test;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import tn.esprit.medicaltourism.delegate.AllergyinformationServiceDelegate;
import tn.esprit.medicaltourism.delegate.HealthProblemServiceDelegate;
import tn.esprit.medicaltourism.delegate.MedicalRecordsServiceDelegate;
import tn.esprit.medicaltourism.delegate.MedicationServiceDelegate;
import tn.esprit.medicaltourism.delegate.PatientServiceDelegate;
import tn.esprit.medicaltourism.domain.Allergyinformation;
import tn.esprit.medicaltourism.domain.HealthProblem;
import tn.esprit.medicaltourism.domain.MedicalRecords;
import tn.esprit.medicaltourism.domain.Medication;
import tn.esprit.medicaltourism.domain.Patient;

public class ConsulterDossierMedicalFram extends JFrame {

	private JPanel contentPane;
	private JTextField fname_tf;
	private JTextField lname_tf;
	private JTextField address_tf;
	private JTextField cin_tf;
	private JTextField email_tf;
	private static Patient p;
	private static MedicalRecords mr;
	private static JTable table_allergy;
	private JTextField adv_tf;
	private JTextField diet_tf;
	private JTextField envT_tf;
	private JTextField intol_tf;
	private JTextField brandName_tf;
	private JTextField dossageLevel_tf;
	private JTextField sourceName_tf;
	private static JTable medication_table;
	private static JTable hp_table;
	private JTextField start_tf;
	private JTextField dateR_tf;
	
	private JTextField herbal_tf;
	private JTextField priority_tf;
	private Allergyinformation al;
	private Medication m;
	private HealthProblem hp;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConsulterDossierMedicalFram frame = new ConsulterDossierMedicalFram(
							p, mr);
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
	public ConsulterDossierMedicalFram(Patient p, MedicalRecords mr) {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 944, 585);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "Medical Record",
				TitledBorder.CENTER, TitledBorder.BELOW_TOP, null, new Color(0,
						204, 153)));
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(0, 0, 943, 553);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(10, 43, 405, 213);
		panel_1.add(panel);
		panel.setLayout(null);
		panel.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "General Information",
				TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 204,
						153)));
		panel.setBackground(Color.WHITE);

		JLabel label = new JLabel("First Name :");
		label.setBounds(21, 35, 69, 14);
		panel.add(label);

		JLabel label_1 = new JLabel("Last Name :");
		label_1.setBounds(21, 73, 69, 14);
		panel.add(label_1);

		JLabel label_2 = new JLabel("Address :");
		label_2.setBounds(21, 108, 46, 14);
		panel.add(label_2);

		JLabel label_3 = new JLabel("Cin Number : ");
		label_3.setBounds(21, 144, 69, 14);
		panel.add(label_3);

		JLabel label_4 = new JLabel("Email :");
		label_4.setBounds(21, 178, 46, 14);
		panel.add(label_4);

		fname_tf = new JTextField();
		fname_tf.setEditable(false);
		fname_tf.setColumns(10);
		fname_tf.setBounds(131, 32, 86, 20);
		panel.add(fname_tf);

		lname_tf = new JTextField();
		lname_tf.setEditable(false);
		lname_tf.setColumns(10);
		lname_tf.setBounds(131, 70, 86, 20);
		panel.add(lname_tf);

		address_tf = new JTextField();
		address_tf.setEditable(false);
		address_tf.setColumns(10);
		address_tf.setBounds(131, 105, 86, 20);
		panel.add(address_tf);

		cin_tf = new JTextField();
		cin_tf.setEditable(false);
		cin_tf.setColumns(10);
		cin_tf.setBounds(131, 142, 86, 17);
		panel.add(cin_tf);

		email_tf = new JTextField();
		email_tf.setEditable(false);
		email_tf.setColumns(10);
		email_tf.setBounds(131, 175, 86, 20);
		panel.add(email_tf);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "List Of Allergies",
				TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 204,
						153)));
		panel_2.setBackground(Color.WHITE);
		panel_2.setBounds(425, 43, 508, 213);
		panel_1.add(panel_2);
		panel_2.setLayout(null);

		table_allergy = new JTable();
		table_allergy.setFillsViewportHeight(true);
		table_allergy.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
	DefaultTableModel model_table = (DefaultTableModel) table_allergy.getModel();
				
				int id = (int) model_table.getValueAt(table_allergy.getSelectedRow(), 0);
				al=AllergyinformationServiceDelegate.find(id);
				diet_tf.setText(al.getDietaryTriggers());
				envT_tf.setText(al.getEnvironmentalTriggers());
				intol_tf.setText(al.getIntolerances());
				adv_tf.setText(al.getAdverseReaction());
				
				
			}
		});
		table_allergy.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"New column", "New column"
			}
		));
		table_allergy.getColumnModel().getColumn(0).setPreferredWidth(0);
		table_allergy.getColumnModel().getColumn(0).setMinWidth(0);
		table_allergy.getColumnModel().getColumn(0).setMaxWidth(0);
		table_allergy.setBorder(new LineBorder(new Color(0, 0, 0)));
		table_allergy.setBounds(10, 33, 109, 156);
		panel_2.add(table_allergy);

		JLabel lblNewLabel = new JLabel("Adverse Reaction : ");
		lblNewLabel.setBounds(144, 33, 109, 14);
		panel_2.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Dietary Triggers :");
		lblNewLabel_1.setBounds(144, 76, 96, 14);
		panel_2.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Environmental Triggers :");
		lblNewLabel_2.setBounds(144, 116, 121, 14);
		panel_2.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Intolerances :");
		lblNewLabel_3.setBounds(144, 156, 96, 14);
		panel_2.add(lblNewLabel_3);

		adv_tf = new JTextField();
		adv_tf.setEditable(false);
		adv_tf.setBounds(308, 30, 109, 20);
		panel_2.add(adv_tf);
		adv_tf.setColumns(10);

		diet_tf = new JTextField();
		diet_tf.setEditable(false);
		diet_tf.setBounds(308, 73, 109, 20);
		panel_2.add(diet_tf);
		diet_tf.setColumns(10);

		envT_tf = new JTextField();
		envT_tf.setEditable(false);
		envT_tf.setBounds(308, 113, 109, 20);
		panel_2.add(envT_tf);
		envT_tf.setColumns(10);

		intol_tf = new JTextField();
		intol_tf.setEditable(false);
		intol_tf.setBounds(308, 153, 109, 20);
		panel_2.add(intol_tf);
		intol_tf.setColumns(10);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.WHITE);
		panel_3.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Medication", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 204, 153)));
		panel_3.setBounds(10, 274, 405, 211);
		panel_1.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblNewLabel_4 = new JLabel("Brand Name :");
		lblNewLabel_4.setBounds(98, 32, 86, 14);
		panel_3.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Dossage Level :");
		lblNewLabel_5.setBounds(98, 76, 86, 14);
		panel_3.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Source Name :");
		lblNewLabel_6.setBounds(98, 130, 86, 14);
		panel_3.add(lblNewLabel_6);
		
		brandName_tf = new JTextField();
		brandName_tf.setEditable(false);
		brandName_tf.setBounds(232, 29, 111, 20);
		panel_3.add(brandName_tf);
		brandName_tf.setColumns(10);
		
		dossageLevel_tf = new JTextField();
		dossageLevel_tf.setEditable(false);
		dossageLevel_tf.setBounds(232, 73, 111, 20);
		panel_3.add(dossageLevel_tf);
		dossageLevel_tf.setColumns(10);
		
		sourceName_tf = new JTextField();
		sourceName_tf.setEditable(false);
		sourceName_tf.setBounds(232, 127, 111, 20);
		panel_3.add(sourceName_tf);
		sourceName_tf.setColumns(10);
		
		medication_table = new JTable();
		medication_table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
DefaultTableModel model_table = (DefaultTableModel) medication_table.getModel();
				
				int id = (int) model_table.getValueAt(medication_table.getSelectedRow(), 0);
				m=MedicationServiceDelegate.find(id);
				brandName_tf.setText(m.getBrandName());
				dossageLevel_tf.setText(m.getDossageLevel());
				sourceName_tf.setText(m.getSourceName());
				if(m.getHerbalMedication())
				herbal_tf.setText("Yes");
				else
				herbal_tf.setText("No");
				 											
				
			}
		});
		medication_table.setBorder(new LineBorder(new Color(0, 0, 0)));
		medication_table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"New column", "New column"
			}
		));
		medication_table.getColumnModel().getColumn(0).setPreferredWidth(0);
		medication_table.getColumnModel().getColumn(0).setMinWidth(0);
		medication_table.getColumnModel().getColumn(0).setMaxWidth(0);
		medication_table.setBounds(10, 31, 78, 169);
		panel_3.add(medication_table);
		
		herbal_tf = new JTextField();
		herbal_tf.setEditable(false);
		herbal_tf.setBounds(232, 165, 111, 20);
		panel_3.add(herbal_tf);
		herbal_tf.setColumns(10);
		
		JLabel lblNewLabel_7 = new JLabel("Herbal Medication :");
		lblNewLabel_7.setBounds(98, 168, 111, 14);
		panel_3.add(lblNewLabel_7);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new TitledBorder(null, "Health Problems", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 204, 153)));
		panel_4.setBackground(Color.WHITE);
		panel_4.setBounds(425, 272, 508, 213);
		panel_1.add(panel_4);
		panel_4.setLayout(null);
		
		hp_table = new JTable();
		hp_table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
DefaultTableModel model_table = (DefaultTableModel) hp_table.getModel();
				
				int id = (int) model_table.getValueAt(hp_table.getSelectedRow(), 0);
				hp=HealthProblemServiceDelegate.find(id);
				start_tf.setText(hp.getStartDate()+"");
				dateR_tf.setText(hp.getDateOfResolution()+"");
				priority_tf.setText(hp.getPriority()+"");
				
			}
		});
		hp_table.setBorder(new LineBorder(new Color(0, 0, 0)));
		hp_table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"New column", "New column"
			}
		));
		hp_table.getColumnModel().getColumn(0).setPreferredWidth(0);
		hp_table.getColumnModel().getColumn(0).setMinWidth(0);
		hp_table.getColumnModel().getColumn(0).setMaxWidth(0);
		hp_table.setBounds(10, 37, 111, 165);
		panel_4.add(hp_table);
		
		JLabel lblNewLabel_8 = new JLabel("Start Date :");
		lblNewLabel_8.setBounds(161, 29, 80, 14);
		panel_4.add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel("Date Of Resolution :");
		lblNewLabel_9.setBounds(161, 91, 111, 14);
		panel_4.add(lblNewLabel_9);
		
		JLabel lblNewLabel_10 = new JLabel("Priority :");
		lblNewLabel_10.setBounds(161, 154, 65, 14);
		panel_4.add(lblNewLabel_10);
		
		start_tf = new JTextField();
		start_tf.setEditable(false);
		start_tf.setBounds(302, 26, 111, 20);
		panel_4.add(start_tf);
		start_tf.setColumns(10);
		
		dateR_tf = new JTextField();
		dateR_tf.setEditable(false);
		dateR_tf.setBounds(302, 88, 111, 20);
		panel_4.add(dateR_tf);
		dateR_tf.setColumns(10);
		
		priority_tf = new JTextField();
		priority_tf.setEditable(false);
		priority_tf.setBounds(341, 151, 72, 20);
		panel_4.add(priority_tf);
		priority_tf.setColumns(10);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(new Color(0, 204, 153));
		panel_5.setBounds(0, 496, 942, 76);
		panel_1.add(panel_5);
		panel_5.setLayout(null);
		
		JButton exit_bt = new JButton("Exit");
		exit_bt.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				ImageIcon im = new ImageIcon("C:\\ressources\\collections_icone\\collection_petit_noir\\Close Filled.png");
				exit_bt.setIcon(im);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				ImageIcon im = new ImageIcon("C:\\ressources\\collections_icone\\collection_petit_noir\\Close.png");
				exit_bt.setIcon(im);
			}
		});
		exit_bt.setBounds(790, 11, 95, 34);
		ImageIcon im = new ImageIcon("C:\\ressources\\collections_icone\\collection_petit_noir\\Close.png");
		exit_bt.setIcon(im);
		panel_5.add(exit_bt);
		
		Patient pf = PatientServiceDelegate.findPatient(p.getId());
		
		initIformationGeneral(pf);
		initTableAllergy(mr);
		initTableHealthProblm(mr);
		initTableMedication(mr);

	}

	public void initIformationGeneral(Patient p) {
		fname_tf.setText(p.getFirstName());
		lname_tf.setText(p.getLastName());
		address_tf.setText(p.getAdress());
		cin_tf.setText(p.getCinNumber() + "");
		email_tf.setText(p.getEmail());
	}

	public static void initTableAllergy(MedicalRecords mr) {
		DefaultTableModel model = (DefaultTableModel) table_allergy.getModel();
		List<Allergyinformation> lp = MedicalRecordsServiceDelegate.findAllByMr(mr.getId());
		for (Allergyinformation p : lp)
		{
			model.addRow(new Object[] { 
					p.getId(),p.getAllergens(),
					
			});
			System.out.println("allergy: "+p.getAllergens());
		}
	}

	public static void initTableMedication(MedicalRecords mr) {
		DefaultTableModel model = (DefaultTableModel) medication_table.getModel();
		List<Medication> lm = MedicalRecordsServiceDelegate.findMedByMr(mr.getId());
		for (Medication p : lm)
		{
			model.addRow(new Object[] { 
					p.getId(),p.getGenericName(),
			});
			System.out.println("Medication: "+p.getBrandName());
		}
	}
	public static void initTableHealthProblm(MedicalRecords mr) {
		DefaultTableModel model = (DefaultTableModel) hp_table.getModel();
		List<HealthProblem> lm = MedicalRecordsServiceDelegate.findHpByMr(mr.getId());
		for (HealthProblem p : lm)
		{
			model.addRow(new Object[] { 
					p.getId(),p.getDiagnostic(),
			});
			System.out.println("HealthProblem: "+p.getDiagnostic());
		}
	}
	
	}
