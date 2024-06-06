package co.edu.uptc.models;

import java.awt.Rectangle;

import co.edu.uptc.Utils.Values;
import co.edu.uptc.pojos.Element;

public class ManagerBullet extends Rectangle {
    private Element element;
    public boolean statusThread;
    
    public ManagerBullet(int x){
        createElement(x);
        statusThread = true;
    }
    public void createElement(int x){
        this.element = new Element();
        this.element.setX(x);
        this.element.setY(Values.heightWindow-Values.lengthPaceCraft-70);
        this.element.setWidth(Values.lengthBullet);
        this.element.setHeight(Values.lengthBullet);
        super.setBounds(element.getX(), element.getY(), element.getWidth(), element.getHeight());
        this.element.setType(0);
        this.element.setPathImg("pathImgBullet1");
        this.element.setSpeed(Values.speedBullet);
        this.element.setActive(true);
    }
    public void up(){
        if(element.getY()<=0){
            element.setActive(false);
        }
        element.setY(element.getY()-element.getSpeed());
        super.setLocation(element.getX(), element.getY());
    }
    public boolean isActive(){
        return element.isActive();
    }
    public void setActive(boolean isActive){
        element.setActive(isActive);
    }
    public void stopThread(){
        element.setActive(false);
        statusThread = false;
    }
    public Element getElement(){
        return element;
    }
}
