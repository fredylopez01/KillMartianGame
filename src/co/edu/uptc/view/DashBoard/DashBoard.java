package co.edu.uptc.view.DashBoard;

import javax.swing.JFrame;

import co.edu.uptc.presenter.ContractPlay;
import co.edu.uptc.presenter.ContractPlay.Presenter;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DashBoard extends JFrame implements ActionListener, ContractPlay.View {
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
        BarrelPanel barrelPanel = new BarrelPanel();
        this.add(barrelPanel, BorderLayout.SOUTH);
        this.addKeyListener(barrelPanel);
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
                    workPanel.start(presenter.getElements());
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

}
