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
    private int amountAlien;
    Sounds sounds;

    public ManagerModel(){
        managerElements = new ArrayList<>();
        managerPacecraft = new ManagerPacecraft();
        managerBullets = new ArrayList<>();
        deletedMartians = 0;
        amountAlien = 0;
        sounds = new Sounds();
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
        for (int i = 0; i < managerElements.size(); i++) {
            if(!managerElements.get(i).isActive()){
                ManagerAlien managerElement = new ManagerAlien();
                managerElements.set(i, managerElement);
                amountAlien++;
            }
            managerElements.get(i).move();
        }
        if(managerElements.size() < 10){
            ManagerAlien managerElement = new ManagerAlien();
            managerElements.add(managerElement);
            amountAlien++;
        }
    }
    @Override
    public void threadVerifyPositions(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while(presenter.isGameWorking()) {
                    for (ManagerBullet managerBullet: managerBullets) {
                        if(managerBullet.isActive()){
                            verifyPositions(managerBullet);
                            managerBullet.up();
                        }
                    }
                    MyUtils.sleep(Values.speedBullet*2);
                }
            }
        });
        thread.start();
    }
    public void verifyPositions(ManagerBullet managerBullet){
        for (ManagerAlien managerAlien : managerElements) {
            if(isBurst(managerBullet, managerAlien)){
                efectImpact(managerAlien, managerBullet);
            }
        }
    }
    public boolean isBurst(ManagerBullet bullet, ManagerAlien alien){
        boolean isBoom = false;
        Element b = bullet.getElement();
        Element a = alien.getElement();
        if(b.getX()>=a.getX() && b.getX()<a.getX()+a.getWidth()
            && b.getY()>=a.getY() && b.getY()<a.getY()+a.getHeight()){
            isBoom = true;
        }
        return isBoom;
    }
    public void efectImpact(ManagerAlien managerAlien, ManagerBullet managerBullet){
        sounds.playSoundBurst();
        managerAlien.impact();
        managerAlien.stopThread();
        managerBullet.setActive(false);
        deletedMartians++;
        MyUtils.sleep(20);
        sounds.stopSoundBurst();
    }
    @Override
    public synchronized boolean shoot(){
        if(presenter.isGameWorking()){
            for (int i = 0; i < 2; i++) {
                if(managerBullets.size() < 2){
                    addFirstBullets(i);
                    return true;
                }
                if(!managerBullets.get(i).isActive()) {
                    addBullet(i);
                    return true;
                }
            }
        }
        return false;
    }
    public void addFirstBullets(int i){
        int postion = MyUtils.positionBullet(i, managerPacecraft.getDx(), managerPacecraft.getType());
        ManagerBullet managerBullet = new ManagerBullet(postion);
        managerBullets.add(managerBullet);
    }
    public void addBullet(int i){
        int postion = MyUtils.positionBullet(i, managerPacecraft.getDx(), managerPacecraft.getType());
        ManagerBullet managerBullet = new ManagerBullet(postion);
        managerBullets.set(i, managerBullet);
    }
    @Override
    public synchronized void start(){
        managerPacecraft.setStatusThread(true);
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
    public void restartGame() {
        managerElements = new ArrayList<>();
        managerBullets = new ArrayList<>();
        deletedMartians = 0;
        amountAlien = 0;
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
    public synchronized ArrayList<Element> getBullets(){
        ArrayList<Element> bullets = new ArrayList<>();
        for (ManagerBullet managerBullet : managerBullets) {
            if(managerBullet.isActive()){
                bullets.add(managerBullet.getElement());
            }
        }
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
        return amountAlien;
    }
    @Override
    public void setTypePacecraft(int type){
        managerPacecraft.setType(type);
    }
}
