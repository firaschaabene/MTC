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

public class Packs_List_GUI extends JPanel {

	/**
	 * Create the panel.
	 */
	public Packs_List_GUI() {
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 5, 450, 295);
		panel.setBackground(new Color(0, 0, 0, 0)); 
		
		panel.setForeground(new java.awt.Color(153, 0, 2));
		panel.setOpaque(false);
		add(panel);
		
		JLabel lbl_pack_classic = new JLabel("New label");
		lbl_pack_classic.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Pack pack=new Pack() ;
				pack=PackServiceDelegate.find(1);
			Pack_update_GUI  pack_update_GUI=new Pack_update_GUI(pack);
			
			pack_update_GUI.show();
			}
		});
		
		JLabel lbl_pack_confort = new JLabel("New label");
		lbl_pack_confort.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Pack pack=new Pack() ;
				pack=PackServiceDelegate.find(2);
				Pack_update_GUI  pack_update_GUI=new Pack_update_GUI(pack);
				pack_update_GUI.show();
			}
		});
		
		JLabel lbl_pack_confort_plus = new JLabel("New label");
		lbl_pack_confort_plus.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Pack pack=new Pack() ;
				pack=PackServiceDelegate.find(3);
				Pack_update_GUI  pack_update_GUI=new Pack_update_GUI(pack);
				pack_update_GUI.show();
			}
		});
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(42)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lbl_pack_confort_plus)
						.addComponent(lbl_pack_confort)
						.addComponent(lbl_pack_classic))
					.addContainerGap(352, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(61)
					.addComponent(lbl_pack_classic)
					.addGap(61)
					.addComponent(lbl_pack_confort)
					.addPreferredGap(ComponentPlacement.RELATED, 78, Short.MAX_VALUE)
					.addComponent(lbl_pack_confort_plus)
					.addGap(47))
		);
		panel.setLayout(gl_panel);

	}
}
