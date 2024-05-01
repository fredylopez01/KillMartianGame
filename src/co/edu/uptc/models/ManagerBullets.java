package co.edu.uptc.models;

import co.edu.uptc.pojos.Element;
import co.edu.uptc.pojos.Values;

public class ManagerBullets {
    private Element element;
    public boolean statusThread;
    private int height;
    
    public ManagerBullets(int x){
        createElement(x);
        statusThread = true;
    }
    public void createElement(int x){
        this.element = new Element();
        this.element.setX(x);
        this.element.setY(Values.heightWindow-70);
        height = (int)(Math.random()*(50-20)+20);
        this.element.setWidth(20);
        this.element.setHeight(20);
        this.element.setType((int)(Math.random()*5));
        this.element.setSpeed((int)(Math.random()*(Values.maxSpeedTime-Values.minSpeedTime+1)+Values.minSpeedTime));
        this.element.setActive(true);
    }
    public void up(){
        if(element.getY()<=0){
            element.setActive(false);
        }
        element.setY(element.getY()-height/3);
    }
    public void bigMove(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while(statusThread  && element.isActive()) {
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
