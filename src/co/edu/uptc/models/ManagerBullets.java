package co.edu.uptc.models;

import co.edu.uptc.pojos.Element;
import co.edu.uptc.pojos.Values;

public class ManagerBullets {
    private Element element;
    public boolean statusThread;
    private int height;
    
    public ManagerBullets(){
        createElement();
        statusThread = true;
    }
    public void createElement(){
        this.element = new Element();
        this.element.setX((int)(Math.random()*Values.widthWindow));
        this.element.setY((int)(Math.random()*Values.heightWindow/2));
        height = (int)(Math.random()*(50-20)+20);
        this.element.setWidth(20);
        this.element.setHeight(20);
        this.element.setType((int)(Math.random()*5));
        this.element.setSpeed((int)(Math.random()*(Values.maxSpeedTime-Values.minSpeedTime+1)+Values.minSpeedTime));
        this.element.setActive(true);
    }
    public void up(){
        if(element.getY()-height/2<=0){
            element.setActive(false);
        }
        element.setY(element.getY()-height/3);
    }
    public void bigMove(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while(statusThread) {
                    up();
                    try {
                        Thread.sleep(element.getSpeed());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        System.out.println(e.getMessage());
                    }
                }
            }
        });
        thread.start();
    }
    public Element getElement(){
        return element;
    }
}
