package co.edu.uptc.view.DashBoard.begin;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import co.edu.uptc.Utils.Values;
import co.edu.uptc.view.DashBoard.ViewUtils.ShapedPanelLoginUI;

public class ContainerBeginPanel extends JPanel {
    private JPanel adminPanels;
    private CardLayout layoutManager;
    private BeginPanel beginPanel;
    private SelectShip selectShipPanel;
    private SelectControls selectControls;
    private GridBagConstraints gbc;

    public ContainerBeginPanel(ActionListener listener){
        initComponents(listener);
    }

    private void initComponents(ActionListener listener) {
        adminPanels = new JPanel(new CardLayout());
        adminPanels.setUI(new ShapedPanelLoginUI(new Color(85, 221, 255, 0)));
        layoutManager = (CardLayout) adminPanels.getLayout();

        beginPanel = new BeginPanel(listener);
        adminPanels.add(beginPanel, "beginPanel");

        selectShipPanel = new SelectShip(listener);
        adminPanels.add(selectShipPanel, "selectShip");

        selectControls = new SelectControls(listener);
        adminPanels.add(selectControls, "settingsPanel");
        
        this.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        add(adminPanels, gbc);
        
    }
    public void panelSettings(){
        layoutManager.show(adminPanels, "settingsPanel");
    }
    public void panelBegin(){
        layoutManager.show(adminPanels, "beginPanel");
    }
    public void panelSelectPacecraft(){
        layoutManager.show(adminPanels, "selectShip");
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponents(g);
        g.drawImage((Values.adminImg.getImage("pathImgBackgroundMain")).getImage(),0, 0,this.getWidth(), this.getHeight(), this);
    }
}
