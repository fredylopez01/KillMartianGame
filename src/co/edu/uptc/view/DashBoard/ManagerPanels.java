package co.edu.uptc.view.DashBoard;

import javax.swing.JPanel;

import co.edu.uptc.view.DashBoard.begin.ContainerBeginPanel;
import co.edu.uptc.view.DashBoard.play.ContainerPlayPanel;

import java.awt.CardLayout;
import java.awt.event.ActionListener;

public class ManagerPanels extends JPanel {
    private ContainerBeginPanel beginPanel;
    private ContainerPlayPanel playPanel;

    public ManagerPanels(ActionListener listener){
        initComponents(listener);
    }

    public void initComponents(ActionListener listener){
        this.setLayout(new CardLayout());

        beginPanel = new ContainerBeginPanel(listener);
        add(beginPanel, "begin");

        playPanel = new ContainerPlayPanel(listener);
        add(playPanel, "play");
    }

    public ContainerPlayPanel getPlayPanel() {
        return playPanel;
    }
    public void setPlayPanel(ContainerPlayPanel playPanel) {
        this.playPanel = playPanel;
    }
    public ContainerBeginPanel getBeginPanel() {
        return beginPanel;
    }
    public void setBeginPanel(ContainerBeginPanel beginPanel) {
        this.beginPanel = beginPanel;
    }
}
