import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import com.toedter.calendar.JDateChooser;

public class AddRowExample {
    private JFrame frame;
    private JPanel panel;
    private JLabel label;
    private JDateChooser datePicker;
    private JButton applyButton;

    public AddRowExample() {
        frame = new JFrame("Date Picker Example");
        panel = new JPanel();
        label = new JLabel("Selected Date:");
        datePicker = new JDateChooser();
        applyButton = new JButton("Apply");

        panel.add(label);
        panel.add(datePicker);
        panel.add(applyButton);

        applyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Lấy ngày đã chọn
                java.util.Date selectedDate = datePicker.getDate();

                // Định dạng ngày thành dạng văn bản
                java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy");
                String formattedDate = sdf.format(selectedDate);

                // In ngày đã chọn lên giao diện
                label.setText("Selected Date: " + formattedDate);
                label.setForeground(Color.RED); // Đổi màu chữ thành đỏ
            }
        });

        frame.add(panel);
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new AddRowExample();
            }
        });
    }
}


//AddRowExample