package co.edu.uptc.view.DashBoard;

import javax.sound.sampled.Clip;
import javax.swing.JFrame;

import co.edu.uptc.Utils.Values;
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
    private Sound sound;

    public DashBoard(){
        initComponents();
    }

    private DashBoard getInstance(){
        return this;
    }

    private void initComponents() {
        setBounds(0, 0, Values.widthWindow, Values.heightWindow);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
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
        sound = new Sound(SoundFiles.loadClip("/co/edu/uptc/view/DashBoard/sound/background.wav"));
        sound.play();
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
        sound.stop();
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
            shoot();
        }
    }

    public void shoot(){
        presenter.shoot();
        if(presenter.getManagerPacecraft().isStatusThread()){
            Sound sound = new Sound(SoundFiles.loadClip("/co/edu/uptc/view/DashBoard/sound/boom.wav"));
            sound.play();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }

}
