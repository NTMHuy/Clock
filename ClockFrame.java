package BtapDongHo;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class ClockFrame extends JFrame {
    private JLabel timeLabel;
    private int timezoneOffset;

    public ClockFrame(int timezoneOffset) {
        this.timezoneOffset = timezoneOffset;
        setTitle("Clock");
        setSize(215, 112);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        timeLabel = new JLabel();
        timeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        updateTime();

        getContentPane().add(timeLabel);
    }

    public void updateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        long currentTime = System.currentTimeMillis() + timezoneOffset * 3600 * 1000;
        timeLabel.setText(dateFormat.format(new Date(currentTime)));
    }
}