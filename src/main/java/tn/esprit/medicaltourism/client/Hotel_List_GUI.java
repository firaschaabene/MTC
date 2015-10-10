package tn.esprit.medicaltourism.client;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import tn.esprit.medicaltourism.delegate.HotelServiceDelegate;
import tn.esprit.medicaltourism.domain.Hotel;
import org.jdesktop.swingbinding.JTableBinding;
import org.jdesktop.swingbinding.SwingBindings;
import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;
import org.jdesktop.beansbinding.BeanProperty;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;
import javax.swing.UIManager;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Hotel_List_GUI extends JFrame {

	private JPanel contentPane_hotels;
	private JTable table_hotels;
		List<Hotel> listHotel  ;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Hotel_List_GUI frame = new Hotel_List_GUI();
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
	public Hotel_List_GUI() {
		initComponents();
		
	}
	
/////////////////////////////////////////////////
	
	
	private void initComponents() {
		
		listHotel=new ArrayList<Hotel>();
		listHotel=HotelServiceDelegate.findAll();
		
		// this.setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				setBounds(100, 100, 1280, 720);
				contentPane_hotels = new JPanel();
				contentPane_hotels.setBorder(new EmptyBorder(5, 5, 5, 5));
				setContentPane(contentPane_hotels);

				JPanel panel_hotels = new JPanel();
				panel_hotels.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Hotels list", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(64, 64, 64)));
				panel_hotels.setBackground(new Color(0,0,0,125));
				
				
				JScrollPane scrollPane_hotels = new JScrollPane();
				scrollPane_hotels.setBackground(new Color(0,0,0,125));
				
				table_hotels = new JTable();
				table_hotels.setFont(new Font("Calibri", Font.PLAIN, 15));
				table_hotels.setModel(new DefaultTableModel(
					new Object[][] {
						{null, null, null},
						{null, null, null},
						{null, null, null},
						{null, null, null},
						{null, null, null},
						{null, null, null},
						{null, null, null},
						{null, null, null},
						{null, null, null},
						{null, null, null},
					},
					new String[] {
						"New column", "New column", "New column"
					}
				));
				table_hotels.getColumnModel().getColumn(0).setPreferredWidth(95);
				table_hotels.getColumnModel().getColumn(0).setMinWidth(30);
				
				JMenuBar menuBar_hotels = new JMenuBar();
				menuBar_hotels.setFont(new Font("Segoe UI", Font.BOLD, 15));
				
				table_hotels.setBounds(100, 100, 1280, 720);
				table_hotels.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Hotels list", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(64, 64, 64)));
				scrollPane_hotels.setColumnHeaderView(table_hotels);
				
				JMenu mnHotelsList = new JMenu("manage hotels");
				menuBar_hotels.add(mnHotelsList);
				
				JMenuItem mntmHotelList = new JMenuItem("add hotel");
				mntmHotelList.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					Hotel_add_GUI 	 hotel_add_GUI=new Hotel_add_GUI() ;
					scrollPane_hotels.remove(table_hotels);
					scrollPane_hotels.repaint();
					scrollPane_hotels.setViewportView(hotel_add_GUI);
						
					}
				});
				mnHotelsList.add(mntmHotelList);
				GroupLayout gl_panel_hotels = new GroupLayout(panel_hotels);
				gl_panel_hotels.setHorizontalGroup(
					gl_panel_hotels.createParallelGroup(Alignment.LEADING)
						.addComponent(menuBar_hotels, GroupLayout.PREFERRED_SIZE, 1240, GroupLayout.PREFERRED_SIZE)
						.addGroup(Alignment.TRAILING, gl_panel_hotels.createSequentialGroup()
							.addContainerGap()
							.addComponent(scrollPane_hotels, GroupLayout.DEFAULT_SIZE, 1216, Short.MAX_VALUE)
							.addGap(12))
				);
				gl_panel_hotels.setVerticalGroup(
					gl_panel_hotels.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_hotels.createSequentialGroup()
							.addGap(13)
							.addComponent(menuBar_hotels, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
							.addGap(26)
							.addComponent(scrollPane_hotels, GroupLayout.DEFAULT_SIZE, 527, Short.MAX_VALUE)
							.addContainerGap())
				);
				panel_hotels.setLayout(gl_panel_hotels);
				GroupLayout gl_contentPane_hotels = new GroupLayout(contentPane_hotels);
				gl_contentPane_hotels.setHorizontalGroup(
					gl_contentPane_hotels.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_hotels, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				);
				gl_contentPane_hotels.setVerticalGroup(
					gl_contentPane_hotels.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_hotels, GroupLayout.PREFERRED_SIZE, 663, GroupLayout.PREFERRED_SIZE)
				);
				contentPane_hotels.setLayout(gl_contentPane_hotels);
				initDataBindings();
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
	protected void initDataBindings() {
		JTableBinding<Hotel, List<Hotel>, JTable> jTableBinding = SwingBindings.createJTableBinding(UpdateStrategy.READ, listHotel, table_hotels);
		//
		BeanProperty<Hotel, Integer> hotelBeanProperty = BeanProperty.create("id");
		jTableBinding.addColumnBinding(hotelBeanProperty).setColumnName("id");
		//
		BeanProperty<Hotel, String> hotelBeanProperty_1 = BeanProperty.create("name");
		jTableBinding.addColumnBinding(hotelBeanProperty_1).setColumnName("name");
		//
		BeanProperty<Hotel, String> hotelBeanProperty_2 = BeanProperty.create("address");
		jTableBinding.addColumnBinding(hotelBeanProperty_2).setColumnName("Adress");
		//
		BeanProperty<Hotel, Integer> hotelBeanProperty_3 = BeanProperty.create("star");
		jTableBinding.addColumnBinding(hotelBeanProperty_3).setColumnName("star");
		//
		jTableBinding.bind();
	}
}
