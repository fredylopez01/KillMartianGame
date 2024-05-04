package co.edu.uptc.view.DashBoard.begin;

import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import co.edu.uptc.Utils.Values;

public class ContainerBeginPanel extends JPanel {
    private BeginPanel beginPanel;

    public ContainerBeginPanel(ActionListener listener){
        initComponents(listener);
    }

    private void initComponents(ActionListener listener) {
        beginPanel = new BeginPanel(listener);
        
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        add(beginPanel, gbc);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponents(g);
        g.drawImage(new ImageIcon(getClass().getResource(Values.pathImgBackgroundMain)).getImage(),0, 0,this.getWidth(), this.getHeight(), this);
    }
}
