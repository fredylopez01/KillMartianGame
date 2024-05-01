package co.edu.uptc.models;

import co.edu.uptc.Utils.Values;
import co.edu.uptc.pojos.Element;

public class ManagerBullets {
    private Element element;
    public boolean statusThread;
    
    public ManagerBullets(int x){
        createElement(x);
        statusThread = true;
    }
    public void createElement(int x){
        this.element = new Element();
        this.element.setX(x);
        this.element.setY(Values.heightWindow-Values.lengthPaceCraft-60);
        this.element.setWidth(Values.lengthBullet);
        this.element.setHeight(Values.lengthBullet);
        this.element.setType((int)(Math.random()*5));
        this.element.setSpeed(Values.speedBullet);
        this.element.setActive(true);
    }
    public void up(){
        if(element.getY()<=0){
            element.setActive(false);
        }
        element.setY(element.getY()-element.getHeight()/3);
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
