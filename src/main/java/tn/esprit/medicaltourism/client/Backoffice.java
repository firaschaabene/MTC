package tn.esprit.medicaltourism.client;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JDesktopPane;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Font;
import javax.swing.JScrollPane;

public class Backoffice extends JFrame {

	private JPanel contentPane;
	private JPanel Acceuil;
	private JMenuBar menuBar;
	private JMenu menu;
	private JMenuItem menuItem;
	private JMenuItem mntmHotelList;


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
		
	}

	private void initComponents() {
		Hotel_list_GUI hotel_Pane=new  Hotel_list_GUI();
		// this.setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				setBounds(100, 100, 1280, 720);
				contentPane = new JPanel();
				contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
				contentPane.setLayout(new BorderLayout(0, 0));
				setContentPane(contentPane);
				
				Acceuil = new JPanel();
				Acceuil.setBackground(new Color(0, 0, 0, 125));
				contentPane.add(Acceuil, BorderLayout.NORTH);
				
				menuBar = new JMenuBar();
				menuBar.setFont(new Font("Segoe UI", Font.BOLD, 15));
				
				JScrollPane scrollPane = new JScrollPane();
//				scrollPane.setBackground(new Color(0, 0, 0, 25));
				scrollPane.setViewportView(hotel_Pane);
				GroupLayout gl_Acceuil = new GroupLayout(Acceuil);
				gl_Acceuil.setHorizontalGroup(
					gl_Acceuil.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_Acceuil.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_Acceuil.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(scrollPane, Alignment.LEADING)
								.addComponent(menuBar, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 1228, Short.MAX_VALUE))
							.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				);
				gl_Acceuil.setVerticalGroup(
					gl_Acceuil.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_Acceuil.createSequentialGroup()
							.addContainerGap()
							.addComponent(menuBar, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 353, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(220, Short.MAX_VALUE))
				);
				
				menu = new JMenu("manage hotels");
				menuBar.add(menu);
				
				menuItem = new JMenuItem("add hotel");
				menuItem.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					scrollPane.setViewportView(new Hotel_add_GUI());
						
					}
				});
				
				mntmHotelList = new JMenuItem("hotel list");
				mntmHotelList.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						scrollPane.setViewportView(new Hotel_list_GUI());
					}
				});
				menu.add(mntmHotelList);
				menu.add(menuItem);
				Acceuil.setLayout(gl_Acceuil);
				
				JLabel bg = new JLabel("New label");
				contentPane.add(bg, BorderLayout.SOUTH);
				bg.setIcon(new ImageIcon("C:\\Users\\firasniper\\Documents\\128011.jpg"));
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
