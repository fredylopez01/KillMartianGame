package co.edu.uptc.view.DashBoard;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import co.edu.uptc.Utils.Values;
import co.edu.uptc.view.DashBoard.ViewUtils.ShapedButtonProfile;

public class MenuPanel extends JPanel {
    private JButton btnPlay;
    private ImageIcon imgPlay;
    private ImageIcon imgPause;
    private Chronometer chronometer;

    public MenuPanel(ActionListener listener) {
        initComponents(listener);
    }

    private void initComponents(ActionListener listener) {
       setBounds(100, 100, 100, 50);

       imgPlay = new ImageIcon(getClass().getResource(Values.pathImgPlay));
       imgPause = new ImageIcon(getClass().getResource(Values.pathImgResume));
       btnPlay = new JButton();
       btnPlay.addActionListener(listener);
       changButton(false);
       this.add(btnPlay);

       chronometer = new Chronometer(listener);
       add(chronometer);

       this.setBackground(new Color(0x55ddff));
    }

    public void changButton(boolean isGameWorking){
        if(isGameWorking){
            btnPlay.setIcon(imgPause);
            addBtn("Pause", new Insets(2, 6, 2, 6));
        } else{
            btnPlay.setIcon(imgPlay);
            addBtn("Play", new Insets(2, 6, 2, 6));
        }
    }

    public void updateChronometer(){
        chronometer.updateTime();
        chronometer.updateLabel();
    }

    public void initChronometer(){
        chronometer.initChronometer();
    }

    public void pauseChronometer(){
        chronometer.pauseChronometer();
    }

    public void addBtn(String comand, Insets insets){
        btnPlay.setText(comand);
        btnPlay.setActionCommand(comand);
        btnPlay.setFont(new Font(Font.DIALOG, Font.BOLD, 15));
        btnPlay.setForeground(Color.WHITE);
        btnPlay.setContentAreaFilled(false);
        btnPlay.setBorderPainted(false);
        btnPlay.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnPlay.setMargin(insets);
        btnPlay.setFocusPainted(false);
        btnPlay.setFocusable(false);
        btnPlay.setUI(new ShapedButtonProfile(new Color(0x230443)));
        repaint();
    }

}
