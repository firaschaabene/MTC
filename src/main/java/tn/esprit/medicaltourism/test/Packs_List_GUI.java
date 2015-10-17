package tn.esprit.medicaltourism.test;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;

import tn.esprit.medicaltourism.delegate.PackServiceDelegate;
import tn.esprit.medicaltourism.domain.Pack;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;

public class Packs_List_GUI extends JPanel {

	/**
	 * Create the panel.
	 */
	public Packs_List_GUI() {
		setBackground(Color.WHITE);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE); 
		
		panel.setForeground(new java.awt.Color(153, 0, 2));
		panel.setOpaque(false);
		
		JLabel lbl_pack_classic = new JLabel("");
		lbl_pack_classic.setBounds(83, 126, 652, 200);
		lbl_pack_classic.setIcon(new ImageIcon(Packs_List_GUI.class.getResource("/edu/esprit/medicaltourism/images/images/classic.png")));
		lbl_pack_classic.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Pack pack=new Pack() ;
				pack=PackServiceDelegate.find(1);
			Pack_update_GUI  pack_update_GUI=new Pack_update_GUI(pack);
			
			pack_update_GUI.show();
			}
		});
		
		JLabel lbl_pack_confort = new JLabel("");
		lbl_pack_confort.setIcon(new ImageIcon(Packs_List_GUI.class.getResource("/edu/esprit/medicaltourism/images/images/comfort.png")));
		lbl_pack_confort.setBounds(85, 371, 650, 215);
		lbl_pack_confort.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Pack pack=new Pack() ;
				pack=PackServiceDelegate.find(2);
				Pack_update_GUI  pack_update_GUI=new Pack_update_GUI(pack);
				pack_update_GUI.show();
			}
		});
		
		JLabel lbl_pack_confort_plus = new JLabel("");
		lbl_pack_confort_plus.setIcon(new ImageIcon(Packs_List_GUI.class.getResource("/edu/esprit/medicaltourism/images/images/comfortplus.png")));
		lbl_pack_confort_plus.setBounds(61, 612, 650, 191);
		lbl_pack_confort_plus.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Pack pack=new Pack() ;
				pack=PackServiceDelegate.find(3);
				Pack_update_GUI  pack_update_GUI=new Pack_update_GUI(pack);
				pack_update_GUI.show();
			}
		});
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 988, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(31, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(5)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 816, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel.setLayout(null);
		panel.add(lbl_pack_confort_plus);
		panel.add(lbl_pack_confort);
		panel.add(lbl_pack_classic);
		setLayout(groupLayout);

	}
}
