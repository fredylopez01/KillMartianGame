package co.edu.uptc.view.DashBoard.begin;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import co.edu.uptc.view.DashBoard.ViewUtils.ShapedButtonProfile;
import co.edu.uptc.view.DashBoard.ViewUtils.ShapedPanelLoginUI;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

public class BeginPanel extends JPanel {
    private JButton btnPlay;
    private JButton btnExit;

    public BeginPanel(ActionListener listener){
        initComponents(listener);
    }

    private void initComponents(ActionListener listener) {
        this.setUI(new ShapedPanelLoginUI(new Color(85, 221, 255, 180)));
        this.setBorder(new EmptyBorder(new Insets(50, 100, 50, 100)));

        btnPlay = new JButton("JUGAR");
        styleButton(btnPlay, new Insets(10, 30, 10, 30));
        btnPlay.setActionCommand("begin");
        btnPlay.addActionListener(listener);

        btnExit = new JButton("SALIR");
        styleButton(btnExit, new Insets(10, 30, 10, 30));
        btnExit.setActionCommand("exit");
        btnExit.addActionListener(listener);
        addComponents();
    }

    private void addComponents(){
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(20, 0, 20, 0);
        this.add(btnPlay, gbc);

        gbc.gridy = 1;
        this.add(btnExit, gbc);
    }

    private void styleButton(JButton btn, Insets insets){
        // btn.setActionCommand(comand);
        btn.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
        btn.setForeground(Color.WHITE);
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setMargin(insets);
        btn.setFocusPainted(false);
        btn.setFocusable(false);
        btn.setUI(new ShapedButtonProfile(new Color(0x230443)));
    }
}
