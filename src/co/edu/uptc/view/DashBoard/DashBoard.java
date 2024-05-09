package co.edu.uptc.view.DashBoard;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import co.edu.uptc.Utils.AdminProperties;
import co.edu.uptc.Utils.MyUtils;
import co.edu.uptc.Utils.Values;
import co.edu.uptc.presenter.ContractPlay;
import co.edu.uptc.presenter.ContractPlay.Presenter;
import co.edu.uptc.view.DashBoard.ViewUtils.Sounds;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class DashBoard extends JFrame implements ActionListener, KeyListener, ContractPlay.View {
    private ImageIcon icon;
    private ContractPlay.Presenter presenter;
    private Sounds sounds;
    private ManagerPanels managerPanels;
    private CardLayout layoutMainPanel;
    private int keyToShoot;

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
        icon = (Values.adminImg.getImage("pathImgIcon"));
		setIconImage(icon.getImage());
        this.addKeyListener(this);
        addListenerWindow();
        keyToShoot = KeyEvent.VK_ENTER;
    }

    public void run(){
        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String comand = e.getActionCommand();
        switch (comand) {
            case "begin" -> start();
            case "settings" -> managerPanels.getBeginPanel().panelSettings();
            case "exit" -> System.exit(0);
            case "Resume" -> start();
            case "Pause" -> stop();
            case "abandon" -> abandon();
            case "keyUp" -> changeKeyToShoot(KeyEvent.VK_UP);
            case "keySpace" -> changeKeyToShoot(KeyEvent.VK_SPACE);
            case "keyEnter" -> changeKeyToShoot(KeyEvent.VK_ENTER);
            case "pacecraft1" -> selectShip(0);
            case "pacecraft2" -> selectShip(1);
            case "pacecraft3" -> selectShip(2);
            case "pacecraft4" -> selectShip(3);
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
                        MyUtils.sleep(10);
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
        managerPanels.getPlayPanel().restartChronometer();
        managerPanels.getPlayPanel().updateChronometer();
        layoutMainPanel.show(managerPanels, "begin");
        presenter.restartGame();
    }
    public void changeKeyToShoot(int keyToShoot){
        this.keyToShoot = keyToShoot;
        managerPanels.getBeginPanel().panelSelectPacecraft();;
    }
    public void selectShip(int type){
        presenter.setTypePacecraft(type);
        managerPanels.getBeginPanel().panelBegin();
    }
    @Override
    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }
    public synchronized void repaintComponents(){
        managerPanels.getPlayPanel().start(presenter.getElements());
        managerPanels.getPlayPanel().movePaceCraft(presenter.getManagerPacecraft().getPacecraft());
        managerPanels.getPlayPanel().shoot(presenter.getBullets());
        managerPanels.getPlayPanel().repaintPlay();
        managerPanels.getPlayPanel().updateActiveMartians(presenter.getActiveMartians());
        managerPanels.getPlayPanel().updateDeletedMartians(presenter.getDeletedMartians());
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
        if(key == keyToShoot){
            shoot();
        }
    }

    public void shoot(){
        if(presenter.isGameWorking() && presenter.shoot()){
            sounds.playSoundShoot();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }

    public void addListenerWindow(){
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                AdminProperties.write("widthWindow", String.valueOf(e.getComponent().getWidth()));
                AdminProperties.write("heightWindow", String.valueOf(e.getComponent().getHeight()));
            }
        });
    }
}
