package co.edu.uptc.view.DashBoard;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import co.edu.uptc.Utils.Values;
import co.edu.uptc.presenter.ContractPlay;
import co.edu.uptc.presenter.ContractPlay.Presenter;
import co.edu.uptc.view.DashBoard.ViewUtils.Sounds;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class DashBoard extends JFrame implements ActionListener, KeyListener, ContractPlay.View {
    private ImageIcon icon;
    private ContractPlay.Presenter presenter;
    private Sounds sounds;
    private ManagerPanels managerPanels;
    private CardLayout layoutMainPanel;

    public DashBoard(){
        initComponents();
    }
    private void initComponents() {
        setBounds(0, 0, Values.widthWindow, Values.heightWindow);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        managerPanels = new ManagerPanels(this);
        this.add(managerPanels, BorderLayout.CENTER);
        layoutMainPanel = (CardLayout) managerPanels.getLayout();
        sounds = new Sounds();
        this.setTitle("Martian Eliminator");
        icon = new ImageIcon(getClass().getResource(Values.pathImgIcon));
		setIconImage(icon.getImage());
        this.addKeyListener(this);
    }

    public void run(){
        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String comand = e.getActionCommand();
        switch (comand) {
            case "begin" -> start();
            case "exit" -> System.exit(0);
            case "Resume" -> start();
            case "Pause" -> stop();
            case "abandon" -> abandon();
            case "chronometer" -> managerPanels.getPlayPanel().updateChronometer();
            default -> System.out.println(comand);
        }
    }
    public void start(){
        if(!presenter.isGameWorking()){
            layoutMainPanel.show(managerPanels, "play");
            presenter.setIsGameWorking(true);
            presenter.start();
            sounds.playSoundBackground();
            managerPanels.getPlayPanel().initChronometer();
            managerPanels.getPlayPanel().changeButton(presenter.isGameWorking());
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    while(presenter.isGameWorking()){
                        repaintComponents();
                    }
                }
            });
            thread.start();
        }
    }
    public void stop(){
        if(presenter.isGameWorking()){
            presenter.setIsGameWorking(false);
            presenter.stop();
            sounds.stopSoundBackground();
            managerPanels.getPlayPanel().pauseChronometer();
            managerPanels.getPlayPanel().changeButton(presenter.isGameWorking());
        }
    }
    public void abandon(){
        stop();
        layoutMainPanel.show(managerPanels, "begin");
    }
    @Override
    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
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
        managerPanels.getPlayPanel().start(presenter.getElements());
        managerPanels.getPlayPanel().movePaceCraft(presenter.getManagerPacecraft().getPacecraft());
        managerPanels.getPlayPanel().shoot(presenter.getBullets());
        managerPanels.getPlayPanel().repaintPlay();
        managerPanels.getPlayPanel().updateActiveMartians(presenter.getActiveMartians());
        managerPanels.getPlayPanel().updateDeletedMartians(presenter.getDeletedMartians());
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
        if(key == KeyEvent.VK_ENTER || key == KeyEvent.VK_SPACE || key == KeyEvent.VK_UP){
            shoot();
        }
    }

    public void shoot(){
        presenter.shoot();
        if(presenter.isGameWorking()){
            sounds.playSoundShoot();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
}
