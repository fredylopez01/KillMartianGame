package co.edu.uptc.view.DashBoard;

import javax.swing.JFrame;

import co.edu.uptc.presenter.ContractPlay;
import co.edu.uptc.presenter.ContractPlay.Presenter;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class DashBoard extends JFrame implements ActionListener, KeyListener, ContractPlay.View {
    private ContractPlay.Presenter presenter;
    private MenuPanel menuPanel;
    private WorkPanel workPanel;

    public DashBoard(){
        this.setLayout(new BorderLayout());
        initComponents();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }

    private DashBoard getInstance(){
        return this;
    }

    private void initComponents() {
        setBounds(1, 1, 850, 600);
        menuPanel = new MenuPanel(getInstance());
        this.add(menuPanel, BorderLayout.NORTH);
        workPanel = new WorkPanel();
        this.add(workPanel, BorderLayout.CENTER);
        this.addKeyListener(this);
    }

    public void run(){
        setVisible(true);
    }

    public void setWorkPanel(WorkPanel workPanel) {
        this.workPanel = workPanel;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String comand = e.getActionCommand();
        switch (comand) {
            case "Start" -> start();
            case "Stop" -> stop();
            default -> System.out.println(comand);
        }
    }
    public void start(){
        presenter.start();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    repaintComponents();
                }
            }
        });
        thread.start();
    }
    public void stop(){
        presenter.stop();
    }
    @Override
    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }
    public MenuPanel getMenuPanel() {
        return menuPanel;
    }
    public WorkPanel getWorkPanel() {
        return workPanel;
    }
    public synchronized void repaintComponents(){
        while (presenter.isPainted()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        presenter.setPainted(true);
        workPanel.start(presenter.getElements());
        workPanel.movePaceCraft(presenter.getManagerPacecraft().getPacecraft());
        workPanel.shoot(presenter.getBullets());
        workPanel.repaint();
        notifyAll();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
            presenter.getManagerPacecraft().left();
            
        }
        if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
            presenter.getManagerPacecraft().right();
            
        }
        if(key == KeyEvent.VK_ENTER || key == KeyEvent.VK_SPACE){
            presenter.shoot();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }

}
