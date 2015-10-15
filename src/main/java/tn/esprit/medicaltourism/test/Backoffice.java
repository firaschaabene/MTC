package tn.esprit.medicaltourism.test;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

public class Backoffice extends JFrame {

	private JPanel contentPane;
	private JPanel Acceuil;
	private JMenuBar menuBar;
	private JMenu menu;
	private JMenuItem mntmAddHotel;
	private JMenuItem mntmHotelList;
	private JPanel panel;
	private JScrollPane scrollPane;
	private JMenu mnManagesActivities;
	private JMenuItem mntmActivityList;
	private JMenu mnDashboard;
	private JMenu mnPacks;
	JLabel bg = new JLabel("New label");
	private JMenu mnNewMenu;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Backoffice frame = new Backoffice();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Backoffice() {
		initComponents();
		Acceuil.setLayout(null);
		Hotel_list_GUI hotel_list_GUI = new Hotel_list_GUI();
		hotel_list_GUI.setBackground(new Color(0, 0, 0, 0)); 
		hotel_list_GUI.setForeground(new java.awt.Color(153, 0, 2));
		hotel_list_GUI.setOpaque(false);
		scrollPane.setBackground(new Color(0, 0, 0, 0)); 
		scrollPane.setOpaque(false);
		scrollPane.setViewportView(hotel_list_GUI);
		panel.setLayout(null);
		panel.add(scrollPane);
		Acceuil.add(panel);
		
		menuBar = new JMenuBar();
		menuBar.setForeground(new java.awt.Color(153, 0, 2));
		
		//menuBar.setOpaque(false);
		menuBar.setBounds(5, 34, 1252, 49);
		contentPane.add(menuBar);
		menuBar.setFont(new Font("Segoe UI", Font.BOLD, 15));
		menuBar.setBackground(new Color(0, 0, 0, 0)); 
		
				
				
				menu = new JMenu("manage hotels");
				menu.setBackground(new Color(0, 0, 0, 0));
				menu.setForeground(new java.awt.Color(153, 0, 2));

				menu.setOpaque(false);
				menuBar.add(menu);
				
				mntmAddHotel = new JMenuItem("Add hotel");
				mntmAddHotel.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					scrollPane.setViewportView(new Hotel_add_GUI());
					}
				});
				
				mntmHotelList = new JMenuItem("Hotel list");
				mntmHotelList.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						bg.setIcon(new ImageIcon(Backoffice.class.getResource("/edu/esprit/medicaltourism/images/images/hotels.jpg")));
					
						scrollPane.setViewportView(new Hotel_list_GUI());
						
						
					}
				});
				menu.add(mntmHotelList);
				menu.add(mntmAddHotel);
				
				JMenuItem mntmAddServices = new JMenuItem("add services");
				mntmAddServices.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						
					}
				});
				menu.add(mntmAddServices);
				
				mnManagesActivities = new JMenu("manages activities");
				menuBar.add(mnManagesActivities);
				
				mntmActivityList = new JMenuItem("Activities list");
				mntmActivityList.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						contentPane.add(bg);
						bg.setIcon(new ImageIcon(Backoffice.class.getResource("/edu/esprit/medicaltourism/images/images/activities.jpg")));
						scrollPane.setViewportView(new Activities_list_GUI());
					}
				});
				mnManagesActivities.add(mntmActivityList);
				
				mnDashboard = new JMenu("Dashboard");
				mnDashboard.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						scrollPane.setViewportView(new Dashboard_GUI());
						
					}
				});
				
				mnPacks = new JMenu("Packs");
				mnPacks.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						scrollPane.setViewportView(new Packs_List_GUI());
						
					}
				});
				menuBar.add(mnPacks);
				
				menuBar.add(mnDashboard);
				
				JMenu mn_users = new JMenu("user managment");
				menuBar.add(mn_users);
				
				JMenuItem mntmNewMenuItem = new JMenuItem("consultants");
				mntmNewMenuItem.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						scrollPane.setViewportView(new PatientsUIBding());
					}
				});
				mn_users.add(mntmNewMenuItem);
				
				JMenuItem mntmNewMenuItem_1 = new JMenuItem("Doctors");
				mntmNewMenuItem_1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						scrollPane.setViewportView(new doctors_list_GUI());
						
					}
				});
				mn_users.add(mntmNewMenuItem_1);
				
				JMenuItem mntmPatients = new JMenuItem("patients");
				mntmPatients.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						scrollPane.setViewportView(new PatientsUIBding());
						
					}
				});
				mn_users.add(mntmPatients);
				
				mnNewMenu = new JMenu("medical records");
				mnNewMenu.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						
						scrollPane.setViewportView(new RechercheDossierMedicalPanl());
					}
				});
				mnNewMenu.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						
					}
				});
				menuBar.add(mnNewMenu);
				bg.setBounds(5, 0, 1340, 720);
		
		contentPane.add(bg);
		bg.setIcon(new ImageIcon(Backoffice.class.getResource("/edu/esprit/medicaltourism/images/images/hotels.jpg")));
	}

	private void initComponents() {
		// this.setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				setBounds(100, 100, 1280, 720);
				contentPane = new JPanel();
				contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
				setContentPane(contentPane);
				contentPane.setLayout(null);
				
				Acceuil = new JPanel();
				Acceuil.setBounds(5, 0, 1257, 673);
				Acceuil.setBackground(new Color(0, 0, 0, 0));
				contentPane.add(Acceuil);
				panel = new JPanel();
				panel.setBounds(79, 140, 1166, 520);
				panel.setBackground(new Color(0, 0, 0, 0));
				
				scrollPane = new JScrollPane();
				scrollPane.setBounds(0, 0, 1154, 520);
				scrollPane.setBackground(new Color(0, 0, 0, 0)); 
				scrollPane.setForeground(new java.awt.Color(153, 0, 2));
				scrollPane.setOpaque(false);
	}
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
