package co.edu.uptc.view.DashBoard.play;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import co.edu.uptc.Utils.Values;
import co.edu.uptc.view.DashBoard.Chronometer;
import co.edu.uptc.view.DashBoard.ViewUtils.ShapedButtonProfile;

public class MenuPanel extends JPanel {
    private JButton btnPlay;
    private ImageIcon imgPlay;
    private ImageIcon imgPause;
    private JButton btnAbandon;
    private ImageIcon imgAbandon;
    private Insets insetsBtn;
    private Chronometer chronometer;
    private JLabel lblDeletedMartians;
    private JLabel lblActiveMartians;
    private ImageIcon imgDeletedMartians;

    public MenuPanel(ActionListener listener) {
        initComponents(listener);
    }

    private void initComponents(ActionListener listener) {
       setBounds(100, 100, 100, 50);
       insetsBtn = new Insets(2, 6, 2, 6);

       imgPlay = new ImageIcon(getClass().getResource(Values.pathImgPlay));
       imgPause = new ImageIcon(getClass().getResource(Values.pathImgResume));
       btnPlay = new JButton();
       styleBtn(btnPlay, insetsBtn, listener);
       changeButton(false);

       chronometer = new Chronometer(listener);
       add(chronometer);

       lblActiveMartians = new JLabel();
       lblActiveMartians.setFont(new Font(Font.DIALOG, Font.BOLD, 15));
       lblActiveMartians.setForeground(new Color(0x230443));
       updateActiveMartians(0);
       add(lblActiveMartians);

       imgDeletedMartians = new ImageIcon(getClass().getResource(Values.pathImgDeletedMartians));
       lblDeletedMartians = new JLabel();
       lblDeletedMartians.setIcon(imgDeletedMartians);
       lblDeletedMartians.setFont(new Font(Font.DIALOG, Font.BOLD, 15));
       updateDeletedMartians(0);
       add(lblDeletedMartians);

       imgAbandon = new ImageIcon(getClass().getResource(Values.pathImgExit));
       btnAbandon = new JButton();
       btnAbandon.setIcon(imgAbandon);
       btnAbandon.setActionCommand("abandon");
       styleBtn(btnAbandon, insetsBtn, listener);

       this.setBackground(new Color(0x55ddff));
    }

    public void changeButton(boolean isGameWorking){
        if(isGameWorking){
            changeComand(btnPlay, "Pause", imgPause);
        } else{
            changeComand(btnPlay,"Resume", imgPlay);
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

    public void updateDeletedMartians(int deletedMartians){
        lblDeletedMartians.setText("Deleted Martians: "+deletedMartians);
    }
    public void updateActiveMartians(int activeMartians){
        lblActiveMartians.setText("Martians: "+activeMartians);
    }

    public void styleBtn(JButton btn, Insets insets, ActionListener listener){
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

    public void changeComand(JButton btn, String comand, ImageIcon icon){
        btn.setIcon(icon);
        btn.setText(comand);
        btn.setActionCommand(comand);
        btn.repaint();
    }

}
