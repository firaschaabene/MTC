package tn.esprit.medicaltourism.test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.knowm.xchart.Chart;
import org.knowm.xchart.ChartBuilder;
import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.demo.charts.ExampleChart;
import org.knowm.xchart.demo.charts.date.DateChart02;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	    ExampleChart exampleChart = new DateChart02();
	    Chart chart = exampleChart.getChart();
	    new SwingWrapper(chart).displayChart();
	  } 
	 
	 
	  public Chart getChart() { 
	 
	 
	    // Create Chart 
	    Chart chart = new ChartBuilder().width(800).height(600).title("Second Scale").build();
	    chart.getStyleManager().setLegendVisible(false);
	 
	 
	    // generate data 
	    List<Date> xData = new ArrayList<Date>();
	    List<Double> yData = new ArrayList<Double>();
	 
	 
	    Random random = new Random();
	 
	 
	    DateFormat sdf = new SimpleDateFormat("HH:mm:ss.SSS");
	    Date date = null;
	    for (int i = 1; i <= 14; i++) {
	      try { 
	        date = sdf.parse("23:45:" + (5 * i + random.nextInt(2)) + "." + random.nextInt(1000));
	      } catch (ParseException e) {
	        e.printStackTrace();
	      } 
	      xData.add(date);
	      yData.add(Math.random() * i);
	    } 
	 
	 
	    chart.addSeries("blah", xData, yData);
	 
	 
	    return chart;
	 
	 
	  } 

	}


