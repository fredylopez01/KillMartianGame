package co.edu.uptc.view.DashBoard.play;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import co.edu.uptc.Utils.Values;
import co.edu.uptc.view.DashBoard.ViewUtils.ShapedButtonProfile;

public class MenuPanel extends JPanel {
    private JButton btnPlay;
    private ImageIcon imgPlay;
    private ImageIcon imgPause;
    private JButton btnAbandon;
    private ImageIcon imgAbandon;
    private Insets insetsBtn;
    private MyChronometer chronometer;
    private JLabel lblDeletedMartians;
    private JLabel lblActiveMartians;
    private ImageIcon imgAlien;
    private ImageIcon imgDeletedMartians;

    public MenuPanel(ActionListener listener) {
        initComponents(listener);
    }

    private void initComponents(ActionListener listener) {
       setBounds(100, 100, 100, 50);
       insetsBtn = new Insets(2, 6, 2, 6);

       imgPlay = (Values.adminImg.getImage("pathImgPlay"));
       imgPause = (Values.adminImg.getImage("pathImgResume"));
       btnPlay = new JButton();
       styleBtn(btnPlay, insetsBtn, listener);
       changeButton(false);

       chronometer = new MyChronometer(listener);
       add(chronometer);

       imgAlien = (Values.adminImg.getImage("pathImgAlien"));
       lblActiveMartians = new JLabel(imgAlien);
       lblActiveMartians.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
       lblActiveMartians.setFont(new Font(Font.DIALOG, Font.BOLD, 15));
       lblActiveMartians.setForeground(new Color(0x230443));
       updateActiveMartians(0);
       add(lblActiveMartians);

       imgDeletedMartians = (Values.adminImg.getImage("pathImgDeletedMartians"));
       lblDeletedMartians = new JLabel();
       lblDeletedMartians.setIcon(imgDeletedMartians);
       lblDeletedMartians.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
       lblDeletedMartians.setFont(new Font(Font.DIALOG, Font.BOLD, 15));
       updateDeletedMartians(0);
       add(lblDeletedMartians);

       imgAbandon = (Values.adminImg.getImage("pathImgExit"));
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
    public void restartChronometer(){
        chronometer.restartChronometer();
    }
    public void updateDeletedMartians(int deletedMartians){
        lblDeletedMartians.setText(""+deletedMartians);
    }
    public void updateActiveMartians(int activeMartians){
        lblActiveMartians.setText(""+activeMartians);
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
        btn.setActionCommand(comand);
        btn.repaint();
    }

}
