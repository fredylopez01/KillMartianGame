package co.edu.uptc.models.Aliens;

import java.awt.Rectangle;

import co.edu.uptc.Utils.MyUtils;
import co.edu.uptc.Utils.Values;
import co.edu.uptc.pojos.DirectionEnum;
import co.edu.uptc.pojos.Element;

public abstract class ManagerAlien extends Rectangle {
    private Element element;
    private DirectionEnum direction;
    public boolean statusThread;

    public ManagerAlien(){
        createElement();
        initialPosition();
        statusThread = true;
    }
    public void createElement(){
        this.element = new Element();
        int length = (int)(Math.random()*(Values.maxLengthAlien-Values.minLengthAlien)+Values.minLengthAlien);
        this.element.setWidth(length);
        this.element.setHeight(length);
        super.setSize(length, length);
        this.element.setType((int)(Math.random()*5));
        this.element.setSpeed((int)(Math.random()*(Values.maxSpeedTime-Values.minSpeedTime+1)+Values.minSpeedTime));
        this.element.setActive(true);
    }
    public abstract void move();
    public abstract void initialPosition();
    public void impact(){
        for (int i = 5; i < 9; i++) {
            element.setType(i);
            MyUtils.sleep(5);
        }
    }
    public void upDatePositions(){
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
    public void setElement(Element element) {
        this.element = element;
    }
    public DirectionEnum getDirection() {
        return direction;
    }
    public void setDirection(DirectionEnum direction) {
        this.direction = direction;
    }
    public boolean isStatusThread() {
        return statusThread;
    }
    public void setStatusThread(boolean statusThread) {
        this.statusThread = statusThread;
    }
    
}
