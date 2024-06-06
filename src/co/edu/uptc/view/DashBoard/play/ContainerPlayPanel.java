package co.edu.uptc.view.DashBoard.play;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JPanel;

import co.edu.uptc.pojos.Element;

public class ContainerPlayPanel extends JPanel {
    private MenuPanel menuPanel;
    private PlayPanel playPanel;

    public ContainerPlayPanel(ActionListener listener){
        initComponents(listener);
    }
    private void initComponents(ActionListener listener) {
        this.setLayout(new BorderLayout());
        menuPanel = new MenuPanel(listener);
        this.add(menuPanel, BorderLayout.NORTH);
        playPanel = new PlayPanel();
        this.add(playPanel, BorderLayout.CENTER);
    }
    public void changeButton(boolean isGameWorking){
        menuPanel.changeButton(isGameWorking);
    }
    public void updateChronometer(){
        menuPanel.updateChronometer();
    }
    public void initChronometer(){
       menuPanel.initChronometer();
    }
    public void pauseChronometer(){
        menuPanel.pauseChronometer();
    }
    public void restartChronometer(){
        menuPanel.restartChronometer();
    }
    public void updateDeletedMartians(int deletedMartians){
        menuPanel.updateDeletedMartians(deletedMartians);
    }
    public void updateActiveMartians(int activeMartians){
        menuPanel.updateActiveMartians(activeMartians);
    }
    public void start(ArrayList<Element> alliens){
        playPanel.start(alliens);
    }
    public void repaintPlay(){
        playPanel.repaint();
    }
}
