package BtapDongHo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class Main extends JFrame {
    private JTextField textField;
    private JLabel timeLabel;

    public Main() {
        setTitle("Clock");
        setSize(300, 150);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        timeLabel = new JLabel();
        timeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        JLabel label = new JLabel("Timezone:");
        textField = new JTextField(10);
        JButton addButton = new JButton("Upgrade");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addClock();
            }
        });

        JPanel panel1 = new JPanel();
        panel1.add(timeLabel);
        
        JPanel panel = new JPanel();
        panel.add(label);
        panel.add(textField);
        panel.add(addButton);

        getContentPane().add(panel1);
        getContentPane().add(panel);
        setVisible(true);
        
        Thread updateTimeThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    updateTime();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        updateTimeThread.start();
    }

    private void addClock() {
        String timezoneStr = textField.getText().trim();
        try {
            int timezone = Integer.parseInt(timezoneStr);
            if (timezone < -12 || timezone > 12) {
                throw new NumberFormatException();
            }
            ClockFrame clockFrame = new ClockFrame(timezone);
            clockFrame.setVisible(true);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Error");
        }
    }
    private void updateTime() {
        ZonedDateTime currentTime = ZonedDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        timeLabel.setText(currentTime.format(formatter));
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main();
                
            }
        });
    }
}
