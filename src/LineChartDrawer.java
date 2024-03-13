/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package src;

import java.awt.BorderLayout;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.DefaultXYDataset;

/**
 *
 * @author quanglv
 */
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class LineChartDrawer extends JPanel {

    private RecordManager recordManager;
    private int numberOfRecordsToShow;

    public LineChartDrawer(RecordManager recordManager, String typeRecord, int numberOfRecordsToShow) {
        this.recordManager = recordManager;
        this.numberOfRecordsToShow = numberOfRecordsToShow;

        DefaultCategoryDataset dataset = createDataset(typeRecord);
        createAndAddChartPanel(dataset, typeRecord);
    }

    private void createAndAddChartPanel(DefaultCategoryDataset dataset, String typeRecord) {
        JFreeChart chart = ChartFactory.createLineChart(
                "Statistical line chart",
                "Date",
                typeRecord,
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);

        ChartPanel chartPanel = new ChartPanel(chart);
        setLayout(new BorderLayout());
        add(chartPanel);
    }

    private DefaultCategoryDataset createDataset(String typeRecord) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        List<Record> records = recordManager.getAllRecords();
        List<Record> subRecords;
        if (records.size() > numberOfRecordsToShow) {
            int startIndex = records.size() - numberOfRecordsToShow;
            subRecords = records.subList(startIndex, records.size());
        } else {
            subRecords = records;
        }

        for (Record record : subRecords) {
            String date = record.getDate();
            float value = 0;
            switch (typeRecord) {
                case "Speed":
                    value = record.getSpeed();
                    break;
                case "Time":
                    value = record.getTime();
                    break;
                case "Distance":
                    value = record.getDistance();
                    break;
            }
            dataset.addValue(value, typeRecord, date);
        }
        return dataset;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LineChartDrawer example = new LineChartDrawer(new RecordManager("admin"), "admin", 30);
            example.setSize(800, 600);
            example.setVisible(true);
        });
    }
}
