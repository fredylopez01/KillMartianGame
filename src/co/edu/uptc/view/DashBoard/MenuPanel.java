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
    private JButton btnPause;
    private ImageIcon imgPause;
    private Chronometer chronometer;

    public MenuPanel(ActionListener listener) {
        initComponents(listener);
    }

    private void initComponents(ActionListener listener) {
       setBounds(100, 100, 100, 50);

       btnPlay = new JButton();
       imgPlay = new ImageIcon(getClass().getResource(Values.pathImgPlay));
	    btnPlay.setIcon(imgPlay);
       addBtn(btnPlay, "Play", listener, new Insets(2, 6, 2, 6));

       btnPause = new JButton();
       imgPause = new ImageIcon(getClass().getResource(Values.pathImgResume));
        btnPause.setIcon(imgPause);
       addBtn(btnPause, "Pause", listener, new Insets(2, 6, 2, 6));

       chronometer = new Chronometer(listener);
       add(chronometer);

       this.setBackground(new Color(0x55ddff));
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

    public void addBtn(JButton btn, String comand, ActionListener listener, Insets insets){
        btn.setText(comand);
        btn.setActionCommand(comand);
        btn.setFont(new Font(Font.DIALOG, Font.BOLD, 15));
        btn.setForeground(Color.WHITE);
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setMargin(insets);
        btn.setFocusPainted(false);
        btn.setFocusable(false);
        btn.setUI(new ShapedButtonProfile(new Color(0x230443)));
        btn.addActionListener(listener);
        this.add(btn);
    }

}
