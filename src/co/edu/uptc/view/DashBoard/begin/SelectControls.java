package co.edu.uptc.view.DashBoard.begin;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import co.edu.uptc.Utils.Values;
import co.edu.uptc.view.DashBoard.ViewUtils.ShapedButtonProfile;
import co.edu.uptc.view.DashBoard.ViewUtils.ShapedPanelLoginUI;

public class SelectControls extends JPanel {
    private JLabel statement;
    private JButton btnKeyUp;
    private Icon imgKeyUp;
    private JButton btnKeySpace;
    private Icon imgKeySpace;
    private JButton btnKeyEnter;
    private Icon imgKeyEnter;

    public SelectControls(ActionListener listener){
        initComponents(listener);
        addComponents();
    }

    private void initComponents(ActionListener listener){
        this.setUI(new ShapedPanelLoginUI(new Color(85, 221, 255, 180)));
        this.setBorder(new EmptyBorder(new Insets(50, 100, 50, 100)));

        statement = new JLabel("Toque la tecla que quiera usar para disparar: ");
        statement.setFont(new Font(Font.DIALOG, Font.BOLD, 18));
        statement.setForeground(new Color(0x544D66));

        imgKeyUp = new ImageIcon(new ImageIcon(getClass().getResource(Values.pathImgUp)).getImage().getScaledInstance(Values.lengthPaceCraft, Values.lengthPaceCraft, 0));
        btnKeyUp = new JButton(imgKeyUp);
        styleButton(btnKeyUp, listener, "keyUp");
        
        imgKeySpace = new ImageIcon(new ImageIcon(getClass().getResource(Values.pathImgSpace)).getImage().getScaledInstance(Values.lengthPaceCraft, Values.lengthPaceCraft, 0));
        btnKeySpace = new JButton(imgKeySpace);
        styleButton(btnKeySpace, listener, "keySpace");
        
        imgKeyEnter = new ImageIcon(new ImageIcon(getClass().getResource(Values.pathImgEnter)).getImage().getScaledInstance(Values.lengthPaceCraft, Values.lengthPaceCraft, 0));
        btnKeyEnter = new JButton(imgKeyEnter);
        styleButton(btnKeyEnter, listener, "keyEnter");
    }
    
    private void addComponents() {
    	this.setLayout(new GridBagLayout());
    	GridBagConstraints gbc = new GridBagConstraints();
    	
        gbc.insets = new Insets(10, 8, 10, 8);
    	gbc.gridwidth = 4;
    	this.add(statement, gbc);
    	
    	gbc.gridwidth = 1;
    	gbc.gridy = 1;
    	this.add(btnKeyUp, gbc);
    	
    	gbc.gridx = 1;
    	this.add(btnKeySpace, gbc);
    	
    	gbc.gridx = 2;
    	this.add(btnKeyEnter, gbc);
    }
    private void styleButton(JButton btn, ActionListener listener, String comand){
        btn.addActionListener(listener);
        btn.setActionCommand(comand);
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setFocusPainted(false);
        btn.setFocusable(false);
        btn.setUI(new ShapedButtonProfile(new Color(0xFEF0B1)));
    }
}
