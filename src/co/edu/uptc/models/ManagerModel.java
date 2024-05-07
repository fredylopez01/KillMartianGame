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
    private ArrayList<ManagerAlien> managerElements;
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
    public void addAliens(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while(presenter.isGameWorking()) {
                    addAlien();
                    MyUtils.sleep((int)(Math.random()*(Values.maxSpeedTimeAdd-Values.minSpeedTimeAdd+1)+Values.minSpeedTimeAdd));
                }
            }
        });
        thread.start();
    }
    public synchronized void addAlien() {
        ManagerAlien managerElement = new ManagerAlien();
        managerElement.bigMove();
        managerElements.add(managerElement);
    }
    @Override
    public void start(){
        moveAliens();
        managerPacecraft.setStatusThread(true);
        for (ManagerBullet managerBullet : managerBullets) {
            managerBullet.bigMove();
        }
    }
    public synchronized void moveAliens(){
        while (!presenter.isPainted()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        presenter.setPainted(false);
        for (ManagerAlien managerAlien : managerElements) {
            managerAlien.bigMove();
        }
        notifyAll();
    }
    @Override
    public void resume(){
        for (ManagerAlien managerElement : managerElements) {
            managerElement.statusThread = true;
        }
        managerPacecraft.setStatusThread(true);
        for (ManagerBullet managerBullet : managerBullets) {
            managerBullet.statusThread = true;
        }
    }
    @Override
    public void stop(){
        for (ManagerAlien managerElement : managerElements) {
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
            ManagerBullet managerBullet = new ManagerBullet(positionBullet(1));
            ManagerBullet managerBullet1 = new ManagerBullet(positionBullet(2));
            managerBullet.bigMove();
            managerBullet1.bigMove();
            managerBullets.add(managerBullet);
            managerBullets.add(managerBullet1);
        }
    }
    public  int positionBullet(int idBullet){
        int position = 0;
        int x = this.managerPacecraft.getPacecraft().getDx();
        int typePacecraft = managerPacecraft.getPacecraft().getType();
            if(typePacecraft == 0 || typePacecraft == 1){
                if(idBullet == 1) position = x+5;
                else position = x+85;
            } else if(typePacecraft == 2 || typePacecraft == 3){
                position= x+45;
            } 
        return position;
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
            for (ManagerAlien managerAlien : managerElements) {
                if(isBurst(managerBullet, managerAlien)){
                    managerAlien.getElement().setActive(false);
                    deletedMartians++;
                    sounds.playSoundBurst();
                    managerBullet.getElement().setActive(false);
                }
            }
        }
    }
    public boolean isBurst(ManagerBullet bullet, ManagerAlien alien){
        boolean isBoom = false;
        Element b = bullet.getElement();
        Element a = alien.getElement();
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
        for (ManagerAlien managerElement : managerElements) {
            elements.add(managerElement.getElement());
        }
        return elements;
    }
    @Override
    public void restartGame() {
        managerElements = new ArrayList<>();
        managerBullets = new ArrayList<>();
        deletedMartians = 0;
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
            for (ManagerAlien manaAliens : managerElements) {
                if(manaAliens.getElement().isActive()){
                    activeMartians++;
                }
            }
        }
        return activeMartians;
    }
    @Override
    public void setTypePacecraft(int type){
        managerPacecraft.getPacecraft().setType(type);
    }
}
