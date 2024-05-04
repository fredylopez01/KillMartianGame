package co.edu.uptc.models;

import java.util.ArrayList;

import co.edu.uptc.Utils.MyUtils;
import co.edu.uptc.Utils.Values;
import co.edu.uptc.pojos.Element;
import co.edu.uptc.presenter.ContractPlay;
import co.edu.uptc.presenter.ContractPlay.Presenter;
import co.edu.uptc.view.DashBoard.ViewUtils.Sounds;

public class ManagerModel implements ContractPlay.Model {
    public ContractPlay.Presenter presenter;
    private ArrayList<ManagerAllien> managerElements;
    private ManagerPacecraft managerPacecraft;
    private ArrayList<ManagerBullet> managerBullets;
    private int deletedMartians;
    Sounds sounds = new Sounds();

    public ManagerModel(){
        managerElements = new ArrayList<>();
        managerPacecraft = new ManagerPacecraft();
        managerBullets = new ArrayList<>();
        deletedMartians = 0;
    }

    @Override
    public void addAlliens(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while(presenter.isGameWorking()) {
                    addAllien();
                    MyUtils.sleep((int)(Math.random()*(Values.maxSpeedTimeAdd-Values.minSpeedTimeAdd+1)+Values.minSpeedTimeAdd));
                }
            }
        });
        thread.start();
    }
    public synchronized void addAllien() {
        ManagerAllien managerElement = new ManagerAllien();
        managerElement.bigMove();
        managerElements.add(managerElement);
    }
    @Override
    public void start(){
        moveAlliens();
        managerPacecraft.setStatusThread(true);
        for (ManagerBullet managerBullet : managerBullets) {
            managerBullet.bigMove();
        }
    }
    public synchronized void moveAlliens(){
        while (!presenter.isPainted()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        presenter.setPainted(false);
        for (ManagerAllien managerAllien : managerElements) {
            managerAllien.bigMove();
        }
        notifyAll();
    }
    @Override
    public void resume(){
        for (ManagerAllien managerElement : managerElements) {
            managerElement.statusThread = true;
        }
        managerPacecraft.setStatusThread(true);
        for (ManagerBullet managerBullet : managerBullets) {
            managerBullet.statusThread = true;
        }
    }
    @Override
    public void stop(){
        for (ManagerAllien managerElement : managerElements) {
            managerElement.statusThread = false;
        }
        managerPacecraft.setStatusThread(false);
        for (ManagerBullet managerBullet : managerBullets) {
            managerBullet.statusThread = false;
        }
    }
    @Override
    public synchronized void shoot(){
        if(presenter.isGameWorking()){
            int x = this.managerPacecraft.getPacecraft().getDx();
            ManagerBullet managerBullet = new ManagerBullet(x+5);
            ManagerBullet managerBullet1 = new ManagerBullet(x+85);
            managerBullet.bigMove();
            managerBullet1.bigMove();
            managerBullets.add(managerBullet);
            managerBullets.add(managerBullet1);
        }
    }
    @Override
    public void threadVerifyPositions(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while(presenter.isGameWorking()) {
                    verifyPositions();
                    MyUtils.sleep(102);
                }
            }
        });
        thread.start();
    }
    public synchronized void verifyPositions(){
        for (ManagerBullet managerBullet : managerBullets) {
            for (ManagerAllien managerAllien : managerElements) {
                if(isBurst(managerBullet, managerAllien)){
                    managerAllien.getElement().setActive(false);
                    deletedMartians++;
                    sounds.playSoundBurst();
                    managerBullet.getElement().setActive(false);
                }
            }
        }
    }
    public boolean isBurst(ManagerBullet bullet, ManagerAllien allien){
        boolean isBoom = false;
        Element b = bullet.getElement();
        Element a = allien.getElement();
        if(b.isActive() && a.isActive()){
            if(b.getX()>=a.getX() && b.getX()<a.getX()+a.getWidth()
                && b.getY()>=a.getY() && b.getY()<a.getY()+a.getHeight()){
                isBoom = true;
            }
        }
        return isBoom;
    }
    @Override
    public synchronized ArrayList<Element> getElements(){
        ArrayList<Element> elements = new ArrayList<>();
        for (ManagerAllien managerElement : managerElements) {
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
        for (ManagerBullet managerBullet : managerBullets) {
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
    @Override
    public int getDeletedMartians() {
        return deletedMartians;
    }
    @Override
    public int getActiveMartians(){
        int activeMartians = 0;
        if(presenter.isGameWorking()){
            for (ManagerAllien manaAlliens : managerElements) {
                if(manaAlliens.getElement().isActive()){
                    activeMartians++;
                }
            }
        }
        return activeMartians;
    }
}
