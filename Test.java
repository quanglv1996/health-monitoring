import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTick;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.axis.TickUnitSource;
import org.jfree.chart.axis.TickUnits;
import org.jfree.data.xy.DefaultXYDataset;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.TextAnchor;

public class Test extends JFrame {

    public Test() {
        setTitle("Line Chart Demo");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        // Tạo dataset
        DefaultXYDataset dataset = createDataset();

        // Tạo biểu đồ từ dataset
        JFreeChart chart = ChartFactory.createXYLineChart(
                "Line Chart Demo",     // Tiêu đề biểu đồ
                "Label",               // Tiêu đề trục hoành
                "Value",               // Tiêu đề trục tung
                dataset,
                PlotOrientation.VERTICAL,
                true, // Hiển thị legend
                true, // Enable tooltips
                false // Enable URLs
        );

        // Tạo NumberAxis và ghi đè phương thức refreshTicks() để tùy chỉnh nhãn của trục hoành
        NumberAxis domainAxis = new NumberAxis() {
            @Override
            protected List refreshTicksVertical(Graphics2D g2, Rectangle2D dataArea, RectangleEdge edge) {
                List<NumberTick> ticks = new ArrayList<>();
                for (int i = 0; i < 5; i++) {
                    ticks.add(new NumberTick(i, "Label " + (i + 1), TextAnchor.CENTER, TextAnchor.CENTER, 0));
                }
                return ticks;
            }
        };

        // Thiết lập trục hoành mới cho biểu đồ
        chart.getXYPlot().setDomainAxis(domainAxis);

        // Tạo Panel biểu đồ và thêm vào JFrame
        ChartPanel chartPanel = new ChartPanel(chart);
        getContentPane().add(chartPanel, BorderLayout.CENTER);
    }

    // Phương thức tạo dataset
    private DefaultXYDataset createDataset() {
        DefaultXYDataset dataset = new DefaultXYDataset();

        // Dữ liệu trục hoành (label)
        String[] labels = {"Label 1", "Label 2", "Label 3", "Label 4", "Label 5"};

        // Dữ liệu trục tung
        double[] values = generateRandomValues(labels.length);

        // Chuẩn bị dữ liệu cho dataset
        double[][] xyData = new double[2][labels.length];
        for (int i = 0; i < labels.length; i++) {
            xyData[0][i] = i;      // Số thứ tự của dữ liệu
            xyData[1][i] = values[i];  // Giá trị dữ liệu
        }

        // Thêm dữ liệu vào dataset
        dataset.addSeries("Series 1", xyData);

        return dataset;
    }

    // Phương thức sinh dữ liệu ngẫu nhiên
    private double[] generateRandomValues(int length) {
        Random random = new Random();
        double[] values = new double[length];
        for (int i = 0; i < length; i++) {
            values[i] = random.nextInt(100) + 1; // Giá trị ngẫu nhiên từ 1 đến 100
        }
        return values;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Test main = new Test();
            main.setVisible(true);
        });
    }
}
