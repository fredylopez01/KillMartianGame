package co.edu.uptc.models;

import java.util.ArrayList;

import co.edu.uptc.Utils.Values;
import co.edu.uptc.pojos.Element;
import co.edu.uptc.presenter.ContractPlay;
import co.edu.uptc.presenter.ContractPlay.Presenter;

public class ManagerModel implements ContractPlay.Model {
    public ContractPlay.Presenter presenter;
    private ArrayList<ManagerAlliens> managerElements;
    private ManagerPacecraft managerPacecraft;
    private ArrayList<ManagerBullets> managerBullets;

    public ManagerModel(){
        managerElements = new ArrayList<>();
        managerPacecraft = new ManagerPacecraft();
        managerBullets = new ArrayList<>();
    }

    @Override
    public void addAlliens(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while(managerPacecraft.isStatusThread()) {
                    try {
                        addAllien();
                        Thread.sleep((int)(Math.random()*(Values.maxSpeedTimeAdd-Values.minSpeedTimeAdd+1)+Values.minSpeedTimeAdd));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        System.out.println(e.getMessage());
                    }
                }
            }
        });
        thread.start();
    }
    public synchronized void addAllien() {
        ManagerAlliens managerElement = new ManagerAlliens();
        managerElement.bigMove();
        managerElements.add(managerElement);
    }
    @Override
    public void start(){
        moveAlliens();
        managerPacecraft.setStatusThread(true);
        for (ManagerBullets managerBullet : managerBullets) {
            managerBullet.bigMove();
        }
    }
    public synchronized void moveAlliens(){
        for (ManagerAlliens managerElement : managerElements) {
            managerElement.bigMove();
        }
    }
    @Override
    public void resume(){
        for (ManagerAlliens managerElement : managerElements) {
            managerElement.statusThread = true;
        }
        managerPacecraft.setStatusThread(true);
        for (ManagerBullets managerBullet : managerBullets) {
            managerBullet.statusThread = true;
        }
    }
    @Override
    public void stop(){
        for (ManagerAlliens managerElement : managerElements) {
            managerElement.statusThread = false;
        }
        managerPacecraft.setStatusThread(false);
        for (ManagerBullets managerBullet : managerBullets) {
            managerBullet.statusThread = false;
        }
    }
    @Override
    public synchronized void shoot(){
        if(presenter.isGameWorking()){
            int x = this.managerPacecraft.getPacecraft().getDx();
            ManagerBullets managerBullet = new ManagerBullets(x+5);
            managerBullets.add(managerBullet);
            ManagerBullets managerBullet1 = new ManagerBullets(x+85);
            managerBullets.add(managerBullet1);
            managerBullet.bigMove();
            managerBullet1.bigMove();
        }
    }
    public void threadVerifyPositions(){

    }
    public void verifyPositions(){
        for (ManagerBullets managerBullet : managerBullets) {
            for (ManagerAlliens managerAllien : managerElements) {
                if(managerBullet.getElement().getX() == managerAllien.getElement().getX()+managerAllien.getElement().getHeight()){
                    managerAllien.getElement().setActive(false);
                }
            }
        }
    }
    @Override
    public ArrayList<Element> getElements(){
        ArrayList<Element> elements = new ArrayList<>();
        for (ManagerAlliens managerElement : managerElements) {
            elements.add(managerElement.getElement());
        }
        return elements;
    }
    @Override
    public synchronized ArrayList<Element> getBullets(){
        while (!presenter.isPainted()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        presenter.setPainted(false);
        ArrayList<Element> bullets = new ArrayList<>();
        for (ManagerBullets managerBullet : managerBullets) {
            bullets.add(managerBullet.getElement());
        }
        notifyAll();
        return bullets;
    }
    @Override
    public synchronized ManagerPacecraft getManagerPacecraft(){
        return managerPacecraft;
    }
    @Override
    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }

}
