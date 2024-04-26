package co.edu.uptc.models;

import java.util.ArrayList;

import co.edu.uptc.pojos.Element;
import co.edu.uptc.pojos.Values;
import co.edu.uptc.presenter.ContractPlay;
import co.edu.uptc.presenter.ContractPlay.Presenter;

public class ManagerModel implements ContractPlay.Model {
    public ContractPlay.Presenter presenter;
    private ArrayList<ManagerAlliens> managerElements;
    private ManagerPacecraft managerPacecraft;
    private ArrayList<ManagerBullets> managerBullets;
    private boolean isAddAllien;

    public ManagerModel(){
        managerElements = new ArrayList<>();
        managerPacecraft = new ManagerPacecraft();
        managerBullets = new ArrayList<>();
        isAddAllien = true;
    }

    @Override
    public void addAlliens(){
        // Thread thread = new Thread(new Runnable() {
        //     @Override
        //     public void run() {
        //         while(managerPacecraft.isStatusThread()) {
        //             try {
        //                 addAllien();
        //                 Thread.sleep((int)(Math.random()*(Values.maxSpeedTime-Values.minSpeedTime+1)+Values.minSpeedTime));
        //             } catch (InterruptedException e) {
        //                 e.printStackTrace();
        //                 System.out.println(e.getMessage());
        //             }
        //         }
        //     }
        // });
        // thread.start();
        for (int i = 0; i < 200; i++) {
            try {
                addAllien();
                Thread.sleep((int)(Math.random()*(Values.maxSpeedTime-Values.minSpeedTime+1)+Values.minSpeedTime));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public synchronized void addAllien() throws InterruptedException{
        ManagerAlliens managerElement = new ManagerAlliens();
        while(isAddAllien){
            wait();
        }
        isAddAllien = true;
        managerElements.add(managerElement);
        System.out.println("Add:"+managerElements.size());
        notifyAll();
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
        while(!isAddAllien){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        isAddAllien = false;
        for (ManagerAlliens managerElement : managerElements) {
            managerElement.bigMove();
        }
        System.out.println(managerElements.size());
        notifyAll();
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
    public void shoot(){
        ManagerBullets managerBullet = new ManagerBullets();
        managerBullets.add(managerBullet);
        for (ManagerBullets manaBullet : managerBullets) {
            manaBullet.bigMove();
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
    public ArrayList<Element> getBullets(){
        ArrayList<Element> bullets = new ArrayList<>();
        for (ManagerBullets managerBullet : managerBullets) {
            bullets.add(managerBullet.getElement());
        }
        return bullets;
    }
    @Override
    public ManagerPacecraft getManagerPacecraft(){
        return managerPacecraft;
    }
    @Override
    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }

}
