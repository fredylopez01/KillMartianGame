package co.edu.uptc.models;

import java.util.ArrayList;

import co.edu.uptc.Utils.MyUtils;
import co.edu.uptc.Utils.Values;
import co.edu.uptc.models.aliens.ManAlienHorizontal;
import co.edu.uptc.models.aliens.ManAlienVertical;
import co.edu.uptc.models.aliens.ManagerAlien;
import co.edu.uptc.pojos.Element;
import co.edu.uptc.presenter.ContractPlay;
import co.edu.uptc.presenter.ContractPlay.Presenter;
import co.edu.uptc.view.DashBoard.ViewUtils.Sounds;

public class ManagerModel implements ContractPlay.Model {
    public ContractPlay.Presenter presenter;
    private ArrayList<ManagerAlien> managerElements;
    private ManagerPacecraft managerPacecraft;
    private ArrayList<ManagerBullet> managerBullets;
    private int maxAliens;
    private int maxBullets;
    private int deletedMartians;
    private int amountAlien;
    private int lastBullet;
    Sounds sounds;

    public ManagerModel(){
        managerElements = new ArrayList<>();
        managerPacecraft = new ManagerPacecraft();
        managerBullets = new ArrayList<>();
        deletedMartians = 0;
        amountAlien = 0;
        maxAliens = 5;
        maxBullets = 2;
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
    private synchronized void addAlien() {
        for (int i = 0; i < managerElements.size(); i++) {
            if(!managerElements.get(i).isActive()){
                ManagerAlien managerElement = new ManAlienHorizontal();
                managerElements.set(i, managerElement);
                amountAlien++;
            }
            managerElements.get(i).move();
        }
        createAliens();
    }
    private void createAliens(){
        if(managerElements.size() < maxAliens){
            ManagerAlien managerElement = new ManAlienHorizontal();
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
    private void verifyPositions(ManagerBullet managerBullet){
        for (ManagerAlien managerAlien : managerElements) {
            if(managerAlien.isActive() && managerBullet.intersects(managerAlien)){
                efectImpact(managerAlien, managerBullet);
            }
        }
    }
    private void efectImpact(ManagerAlien managerAlien, ManagerBullet managerBullet){
        sounds.playSoundBurst();
        managerAlien.impact();
        managerAlien.stopThread();
        managerBullet.setActive(false);
        deletedMartians++;
        MyUtils.sleep(20);
        sounds.stopSoundBurst();
    }
    @Override
    public synchronized void shoot(){
        if(presenter.isGameWorking()){
            if(lastBullet >= maxBullets){
                lastBullet=0;
            }
            if(managerBullets.size() < maxBullets){
                sounds.playSoundShoot();
                managerBullets.add(createBullet(lastBullet));
            } else if(!managerBullets.get(lastBullet).isActive()) {
                sounds.playSoundShoot();
                managerBullets.set(lastBullet, createBullet(lastBullet));
            }
            lastBullet++;
        }
    }
    private ManagerBullet createBullet(int i){
        int postion = MyUtils.positionBullet(i, managerPacecraft.getX(), managerPacecraft.getType());
        return new ManagerBullet(postion);
    }
    @Override
    public void paceCraftLeft() {
        managerPacecraft.left();
    }
    @Override
    public void paceCraftRight() {
        managerPacecraft.right();
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
        for (ManagerBullet managerBullet : managerBullets) {
            if(managerBullet.isActive()){
                elements.add(managerBullet.getElement());
            }
        }
        elements.add(managerPacecraft.getPacecraft());
        return elements;
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
