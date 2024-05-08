package co.edu.uptc.view.DashBoard.play;

import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import co.edu.uptc.Utils.Values;

public class MyChronometer extends JPanel {
    private Timer timer;
    private JLabel lblChronometer;
    private ImageIcon imgChronometer;
    private int hours;
    private int minuts;
    private int seconds;
    private int secondsValue;

    public MyChronometer(ActionListener listener){
        initComponents(listener);
    }

    public void initComponents(ActionListener listener){
        timer = new Timer(1000, listener);
        timer.setActionCommand("chronometer");
        hours = 0;
        minuts = 0;
        seconds = 0;
        secondsValue = 0;

        lblChronometer = new JLabel(hours+"h:"+minuts+"m:"+seconds+"s");
        imgChronometer = (Values.adminImg.getImage("pathImgChronometer"));
        lblChronometer.setIcon(imgChronometer);
        lblChronometer.setFont(new Font(Font.DIALOG, Font.BOLD, 15));
        this.add(lblChronometer);
    }

    public void updateTime(){
        seconds++;
        hours = seconds/3600;
        minuts = (seconds%3600)/60;
        secondsValue = ((seconds%3600)%60);
    }

    public void updateLabel(){
        lblChronometer.setText(hours+"h:"+minuts+"m:"+secondsValue+"s");
    }

    public void initChronometer(){
        timer.start();
    }

    public void pauseChronometer(){
        timer.stop();
    }

    public void restartChronometer(){
        timer.restart();
        seconds = -1;
    }
}
