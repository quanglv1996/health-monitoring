import javax.swing.*;
import org.jfree.chart.*;
import org.jfree.chart.plot.*;
import org.jfree.data.xy.*;

public class LineChartExample extends JFrame {

    public LineChartExample() {
        super("Line Chart Example");

        // Tạo bộ dữ liệu
        DefaultXYDataset dataset = createDataset();

        // Tạo biểu đồ dạng đường
        JFreeChart chart = ChartFactory.createXYLineChart(
            "Line Chart Example", // Tiêu đề biểu đồ
            "X Axis", // Nhãn trục tung
            "Y Axis", // Nhãn trục hoành
            dataset, // Bộ dữ liệu
            PlotOrientation.VERTICAL,
            true, // Hiển thị legend
            true, // Enable tooltips
            false // Enable URLs
        );

        // Tùy chỉnh biểu đồ
        XYPlot plot = chart.getXYPlot();
        plot.setDomainPannable(true);
        plot.setRangePannable(true);

        // Tạo panel chứa biểu đồ
        ChartPanel panel = new ChartPanel(chart);
        setContentPane(panel);
    }

    // Tạo bộ dữ liệu với các điểm ngẫu nhiên
    private DefaultXYDataset createDataset() {
        DefaultXYDataset dataset = new DefaultXYDataset();

        // Tạo mảng chứa dữ liệu
        double[][] data1 = new double[2][100];
        double[][] data2 = new double[2][100];

        // Tạo dữ liệu ngẫu nhiên cho data1 (trục hoành từ 1 đến 100)
        for (int i = 0; i < 100; i++) {
            data1[0][i] = i + 1; // Trục tung
            data1[1][i] = Math.random() * 100; // Trục hoành
        }

        // Tạo dữ liệu ngẫu nhiên cho data2 (trục hoành từ 1 đến 1000)
        for (int i = 0; i < 100; i++) {
            data2[0][i] = i + 1; // Trục tung
            data2[1][i] = Math.random() * 1000; // Trục hoành
        }

        // Thêm dữ liệu vào bộ dữ liệu
        dataset.addSeries("Random Data 1", data1);
        dataset.addSeries("Random Data 2", data2);

        return dataset;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LineChartExample example = new LineChartExample();
            example.setSize(800, 600);
            example.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            example.setLocationRelativeTo(null);
            example.setVisible(true);
        });
    }
}
