import javax.swing.*;
import java.awt.*;

public class MainFrameExample {
    public static void main(String[] args) {
        // Tạo JFrame
        JFrame frame = new JFrame("Main Frame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        // Tạo JPanel chứa các thành phần
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // Tạo JPanel lớn
        JPanel bigPanel = new JPanel();
        bigPanel.setBorder(BorderFactory.createTitledBorder("Big Panel"));
        bigPanel.setPreferredSize(new Dimension(300, 150));
        bigPanel.setBackground(Color.lightGray);

        // Tạo JPanel chứa 2 checkbox
        JPanel checkBoxPanel = new JPanel();
        checkBoxPanel.setBorder(BorderFactory.createTitledBorder("Check Box Panel"));
        checkBoxPanel.setPreferredSize(new Dimension(150, 100));
        checkBoxPanel.setBackground(Color.white);

        JCheckBox checkBox1 = new JCheckBox("Checkbox 1");
        JCheckBox checkBox2 = new JCheckBox("Checkbox 2");
        checkBoxPanel.add(checkBox1);
        checkBoxPanel.add(checkBox2);

        // Tạo JPanel chứa 2 button
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(BorderFactory.createTitledBorder("Button Panel"));
        buttonPanel.setPreferredSize(new Dimension(150, 100));
        buttonPanel.setBackground(Color.white);

        JButton button1 = new JButton("Button 1");
        JButton button2 = new JButton("Button 2");
        buttonPanel.add(button1);
        buttonPanel.add(button2);

        // Thêm các JPanel nhỏ vào JPanel lớn
        bigPanel.add(checkBoxPanel, BorderLayout.WEST);
        bigPanel.add(buttonPanel, BorderLayout.EAST);

        // Thêm JPanel lớn vào JPanel chính của frame
        mainPanel.add(bigPanel);

        // Thêm JPanel chính vào JFrame và hiển thị frame
        frame.add(mainPanel);
        frame.setVisible(true);
    }
}
