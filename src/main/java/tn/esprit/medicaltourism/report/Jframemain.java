package tn.esprit.medicaltourism.report;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mysql.jdbc.Connection;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JRViewer;
import net.sf.jasperreports.view.JasperViewer;
import tn.esprit.medicaltourism.delegate.HotelServiceDelegate;
import tn.esprit.medicaltourism.domain.Hotel;

public class Jframemain extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Jframemain frame = new Jframemain();
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
	public Jframemain() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 514, 345);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		showreport2();
	}

	private void showreport2() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = (Connection) DriverManager
					.getConnection(
							"jdbc:mysql://localhost:3306/medicaltourismdb",
							"root", "");
			String sourceName = "src/main/java/tn/esprit/medicaltourism/report/hotels.jrxml";
			JasperReport report = JasperCompileManager
					.compileReport(sourceName);
			JasperPrint filedReport = JasperFillManager.fillReport(report,
					null, con);
			//JasperViewer.viewReport(filedReport);
			this.getContentPane().add(new JRViewer(filedReport));
			this.pack();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	private void showreport() {
		try {
			List<Hotel> hotels = new ArrayList<Hotel>();
			hotels = HotelServiceDelegate.findAll();
			List<Map<String, ?>> datasource = new ArrayList<Map<String, ?>>();
			for (Hotel hotel : hotels) {
				Map<String, Object> m = new HashMap<String, Object>();
				m.put("id", hotel.getId());
				m.put("adresse", hotel.getAddress());
				m.put("Name", hotel.getName());
				m.put("stars", hotel.getStar());
				System.out.println(hotel);
				datasource.add(m);
			}
			JRDataSource JRdatasource = new JRBeanCollectionDataSource(
					datasource);
			String sourceName = "src/main/java/tn/esprit/medicaltourism/report/hotels.jrxml";
			JasperReport report = JasperCompileManager
					.compileReport(sourceName);
			JasperPrint filedReport = JasperFillManager.fillReport(report,
					null, JRdatasource);
			this.getContentPane().add(new JRViewer(filedReport));
			this.pack();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	//
	// public void runReport(JRDataSource ds, JRDataSource dsDetail){
	// try{
	// JasperDesign jasperDesign2 =
	// JRXmlLoader.load("/Users/marcomolteni/Documents/catchme/SwingAnalysis/resources/Benchmark_subreport1.jrxml");
	//
	// JasperDesign jasperDesign =
	// JRXmlLoader.load("/Users/marcomolteni/Documents/catchme/SwingAnalysis/resources/Benchmark.jrxml");
	// // JasperReport jasperReport2 =
	// JasperCompileManager.compileReport(jasperDesign2);//,
	// "/Users/marcomolteni/Documents/catchme/SwingAnalysis/resources/Benchmark.jasper");
	// JasperCompileManager.compileReportToFile(jasperDesign2,
	// "/Users/marcomolteni/Documents/catchme/SwingAnalysis/resources/Benchmark_subreport1.jasper");
	//
	// //JasperPrint jasperPrint2 = JasperFillManager.fillReport(jasperReport2,
	// parameters, dsDetail);
	//
	// JasperReport jasperReport =
	// JasperCompileManager.compileReport(jasperDesign);//,
	// "/Users/marcomolteni/Documents/catchme/SwingAnalysis/resources/Benchmark.jasper");
	// JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,
	// parameters, ds);
	// // JasperExportManager.exportReportToPdfFile(jasperPrint2,
	// "/Users/marcomolteni/Documents/ChartReportsub.pdf");
	//
	// JasperExportManager.exportReportToPdfFile(jasperPrint,
	// "/Users/marcomolteni/Documents/ChartReport.pdf");
	// JasperViewer.viewReport(jasperPrint);
	// }catch(Exception ex) {
	// String connectMsg = "Could not create the report " + ex.getMessage() +
	// " " + ex.getLocalizedMessage();
	// System.out.println(connectMsg);
	// }
	//
	// }

}
