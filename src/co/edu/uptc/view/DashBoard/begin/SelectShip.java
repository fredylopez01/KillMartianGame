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
import java.awt.Font;
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

    public SelectShip(ActionListener listener){
        initComponents(listener);
        addComponents();
    }

    private void initComponents(ActionListener listener){
        this.setUI(new ShapedPanelLoginUI(new Color(85, 221, 255, 180)));
        this.setBorder(new EmptyBorder(new Insets(50, 70, 50, 70)));

        statement = new JLabel("Select the ship");
        statement.setFont(new Font(Font.DIALOG, Font.BOLD, 18));
        statement.setForeground(new Color(0x544D66));

        imgShipOne = new ImageIcon((Values.adminImg.getImage("pathImgPacecraft1")).getImage().getScaledInstance(Values.lengthPaceCraft, Values.lengthPaceCraft, 0));
        shipOne = new JButton(imgShipOne);
        shipOne.setMargin(new Insets(1, 1, 1, 1));
        SelectControls.styleButton(shipOne, listener, "pacecraft1", Color.WHITE);
        
        imgShipTwo = new ImageIcon((Values.adminImg.getImage("pathImgPacecraft2")).getImage().getScaledInstance(Values.lengthPaceCraft, Values.lengthPaceCraft, 0));
        shipTwo = new JButton(imgShipTwo);
        shipTwo.setMargin(new Insets(1, 1, 1, 1));
        SelectControls.styleButton(shipTwo, listener, "pacecraft2", Color.WHITE);
        
        imgShipThree = new ImageIcon((Values.adminImg.getImage("pathImgPacecraft3")).getImage().getScaledInstance(Values.lengthPaceCraft, Values.lengthPaceCraft, 0));
        shipThree = new JButton(imgShipThree);
        shipThree.setMargin(new Insets(1, 1, 1, 1));
        SelectControls.styleButton(shipThree, listener, "pacecraft3", Color.WHITE);
        
        imgShipFour = new ImageIcon((Values.adminImg.getImage("pathImgPacecraft4")).getImage().getScaledInstance(Values.lengthPaceCraft, Values.lengthPaceCraft, 0));
        shipFour = new JButton(imgShipFour);
        shipFour.setMargin(new Insets(1, 1, 1, 1));
        SelectControls.styleButton(shipFour, listener, "pacecraft4", Color.WHITE);
        
    }
    
    private void addComponents() {
    	this.setLayout(new GridBagLayout());
    	GridBagConstraints gbc = new GridBagConstraints();
    	
        gbc.insets = new Insets(10, 8, 10, 8);
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
    }
}
