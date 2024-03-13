import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import javax.swing.*;
import java.awt.*;
import org.jfree.chart.axis.CategoryLabelPositions;

public class Main extends JFrame {

    public Main() {
        setTitle("Line Chart Demo");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        // Tạo dataset
        DefaultCategoryDataset dataset = createDataset();

        // Tạo biểu đồ từ dataset
        JFreeChart chart = ChartFactory.createLineChart(
                "Line Chart Demo",     // Tiêu đề biểu đồ
                "Label",               // Tiêu đề trục hoành
                "Value",               // Tiêu đề trục tung
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        // Tùy chỉnh trục hoành (CategoryAxis) để hiển thị các nhãn tùy chỉnh
        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);

        // Tạo Panel biểu đồ và thêm vào JFrame
        ChartPanel chartPanel = new ChartPanel(chart);
        getContentPane().add(chartPanel, BorderLayout.CENTER);
    }

    // Phương thức tạo dataset
    private DefaultCategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        // Dữ liệu trục tung
        dataset.addValue(1, "Series 1", "Label 1");
        dataset.addValue(2, "Series 1", "Label 2");
        dataset.addValue(5, "Series 1", "Label 3");
        dataset.addValue(4, "Series 1", "Label 4");
        dataset.addValue(9, "Series 1", "Label 5");

        return dataset;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Main main = new Main();
            main.setVisible(true);
        });
    }
}
