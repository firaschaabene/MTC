package tn.esprit.medicaltourism.test;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.knowm.xchart.Chart;
import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;

import java.awt.Button;
import java.awt.Color;

public class Dashboard_GUI extends JPanel {

	/**
	 * Create the panel.
	 */
	public Dashboard_GUI() {

		this.setBackground(new Color(0, 0, 0, 0));
		this.setForeground(new java.awt.Color(153, 0, 2));
	this.setOpaque(false);
		JPanel panel = new JPanel();

		panel.setBackground(new Color(0, 0, 0, 0));
		panel.setForeground(new java.awt.Color(153, 0, 2));
		panel.setOpaque(false);
		Button button = new Button("New button");

		Button button_1 = new Button("New button");

		Button button_2 = new Button("New button");

		Button button_3 = new Button("New button");

		Button button_4 = new Button("New button");

		Button button_5 = new Button("New button");
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(
				Alignment.LEADING).addComponent(panel,
				GroupLayout.DEFAULT_SIZE, 484, Short.MAX_VALUE));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(
				Alignment.LEADING).addComponent(panel,
				GroupLayout.DEFAULT_SIZE, 319, Short.MAX_VALUE));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel
				.createParallelGroup(Alignment.LEADING)
				.addGroup(
						gl_panel.createSequentialGroup()
								.addGap(69)
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.LEADING)
												.addGroup(
														gl_panel.createSequentialGroup()
																.addComponent(
																		button_5,
																		GroupLayout.PREFERRED_SIZE,
																		GroupLayout.DEFAULT_SIZE,
																		GroupLayout.PREFERRED_SIZE)
																.addGap(102)
																.addComponent(
																		button_4,
																		GroupLayout.PREFERRED_SIZE,
																		GroupLayout.DEFAULT_SIZE,
																		GroupLayout.PREFERRED_SIZE))
												.addGroup(
														gl_panel.createSequentialGroup()
																.addComponent(
																		button,
																		GroupLayout.PREFERRED_SIZE,
																		GroupLayout.DEFAULT_SIZE,
																		GroupLayout.PREFERRED_SIZE)
																.addGap(102)
																.addComponent(
																		button_3,
																		GroupLayout.PREFERRED_SIZE,
																		GroupLayout.DEFAULT_SIZE,
																		GroupLayout.PREFERRED_SIZE))
												.addGroup(
														gl_panel.createSequentialGroup()
																.addComponent(
																		button_1,
																		GroupLayout.PREFERRED_SIZE,
																		GroupLayout.DEFAULT_SIZE,
																		GroupLayout.PREFERRED_SIZE)
																.addGap(102)
																.addComponent(
																		button_2,
																		GroupLayout.PREFERRED_SIZE,
																		GroupLayout.DEFAULT_SIZE,
																		GroupLayout.PREFERRED_SIZE)))));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(
				Alignment.LEADING).addGroup(
				gl_panel.createSequentialGroup()
						.addGap(56)
						.addGroup(
								gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(button_5,
												GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(button_4,
												GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(
								gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(button,
												GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(button_3,
												GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE))
						.addGap(24)
						.addGroup(
								gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(button_1,
												GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(button_2,
												GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE))));
		panel.setLayout(gl_panel);
		setLayout(groupLayout);

	}
}
