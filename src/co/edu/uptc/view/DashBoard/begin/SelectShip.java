package co.edu.uptc.view.DashBoard.begin;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import co.edu.uptc.Utils.Values;
import co.edu.uptc.view.DashBoard.ViewUtils.ShapedPanelLoginUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

public class SelectShip extends JPanel{
    private JLabel statement;
    private JButton shipOne;
    private Icon imgShipOne;
    private JButton shipTwo;
    private Icon imgShipTwo;
    private JButton shipThree;
    private Icon imgShipThree;
    private JButton shipFour;
    private Icon imgShipFour;
    private JButton btnSelectShip;

    public SelectShip(ActionListener listener){
        initComponents(listener);
        addComponents();
    }

    private void initComponents(ActionListener listener){
        this.setUI(new ShapedPanelLoginUI(new Color(85, 221, 255, 180)));
        this.setBorder(new EmptyBorder(new Insets(50, 100, 50, 100)));

        statement = new JLabel("Seleccione la nave");

        imgShipOne = new ImageIcon(new ImageIcon(getClass().getResource(Values.pathImgPacecraft1)).getImage().getScaledInstance(Values.lengthPaceCraft, Values.lengthPaceCraft, 0));
        shipOne = new JButton(imgShipOne);
        shipOne.setPreferredSize(new Dimension(Values.lengthPaceCraft, Values.lengthPaceCraft));
        
        imgShipTwo = new ImageIcon(new ImageIcon(getClass().getResource(Values.pathImgPacecraft2)).getImage().getScaledInstance(Values.lengthPaceCraft, Values.lengthPaceCraft, 0));
        shipTwo = new JButton(imgShipTwo);
        shipTwo.setPreferredSize(new Dimension(Values.lengthPaceCraft, Values.lengthPaceCraft));
        
        imgShipThree = new ImageIcon(new ImageIcon(getClass().getResource(Values.pathImgPacecraft3)).getImage().getScaledInstance(Values.lengthPaceCraft, Values.lengthPaceCraft, 0));
        shipThree = new JButton(imgShipThree);
        shipThree.setPreferredSize(new Dimension(Values.lengthPaceCraft, Values.lengthPaceCraft));
        
        imgShipFour = new ImageIcon(new ImageIcon(getClass().getResource(Values.pathImgPacecraft4)).getImage().getScaledInstance(Values.lengthPaceCraft, Values.lengthPaceCraft, 0));
        shipFour = new JButton(imgShipFour);
        shipFour.setPreferredSize(new Dimension(Values.lengthPaceCraft, Values.lengthPaceCraft));
        
        btnSelectShip = new JButton("Seleccionar");
    }
    
    private void addComponents() {
    	this.setLayout(new GridBagLayout());
    	GridBagConstraints gbc = new GridBagConstraints();
    	
    	gbc.gridwidth = 4;
    	this.add(statement, gbc);
    	
    	gbc.gridwidth = 1;
    	gbc.gridy = 1;
    	this.add(shipOne, gbc);
    	
    	gbc.gridx = 1;
    	this.add(shipTwo, gbc);
    	
    	gbc.gridx = 2;
    	this.add(shipThree, gbc);
    	
    	gbc.gridx = 3;
    	this.add(shipFour, gbc);
    	
    	gbc.gridy = 2;
    	gbc.gridx = 1;
    	gbc.gridwidth = 2;
    	this.add(btnSelectShip, gbc);
    }
}
