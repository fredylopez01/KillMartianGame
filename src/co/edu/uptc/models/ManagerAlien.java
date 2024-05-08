package co.edu.uptc.models;

import co.edu.uptc.Utils.MyUtils;
import co.edu.uptc.Utils.Values;
import co.edu.uptc.pojos.DirectionEnum;
import co.edu.uptc.pojos.Element;

public class ManagerAlien {
    private Element element;
    private DirectionEnum direction;
    public boolean statusThread;

    public ManagerAlien(){
        createElement();
        direction();
        statusThread = true;
    }
    public void createElement(){
        this.element = new Element();
        int length = (int)(Math.random()*(Values.maxLengthAlien-Values.minLengthAlien)+Values.minLengthAlien);
        this.element.setWidth(length);
        this.element.setHeight(length);
        this.element.setType((int)(Math.random()*5));
        this.element.setSpeed((int)(Math.random()*(Values.maxSpeedTime-Values.minSpeedTime+1)+Values.minSpeedTime));
        this.element.setActive(true);
    }
    public void initialPositionRigth(){
        this.element.setX(Values.widthWindow-element.getWidth());
        this.element.setY((int)(Math.random()*Values.heightWindow/2));
        changeDirection(DirectionEnum.LEFT);
    }
    public void initialPositionLeft(){
        this.element.setX(0);
        this.element.setY((int)(Math.random()*Values.heightWindow/2));
        changeDirection(DirectionEnum.RIGHT);
    }
    public void direction(){
        int directionR = (int)(Math.random()*(2)+1);
        switch (directionR) {
            case 1 -> initialPositionRigth();
            case 2 -> initialPositionLeft();
            default -> initialPositionLeft();
        }
    }
    private void changeDirection(DirectionEnum direction) {
        this.direction = direction;
    }
    public void move(){
        if(direction==DirectionEnum.LEFT){
            left();
        }
        if (direction==DirectionEnum.RIGHT) {
            right();
        }
    }
    public void left(){
        if(element.getX()-element.getWidth()/2<=0-element.getWidth()){
            element.setActive(false);
        }
        element.setX(element.getX()-element.getWidth()/3);
    }
    public void right(){
        if(element.getX()+element.getWidth()/2>=Values.widthWindow){
            element.setActive(false);
        }
        element.setX(element.getX()+element.getWidth()/3);
    }
    public Element getElement(){
        return element;
    }
    public void bigMove(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while(statusThread && element.isActive()) {
                    move();
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
    public void impact(){
        for (int i = 5; i < 9; i++) {
            element.setType(i);
            MyUtils.sleep(10);
        }
    }
}
