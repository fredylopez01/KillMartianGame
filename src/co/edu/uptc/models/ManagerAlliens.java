package co.edu.uptc.models;

import co.edu.uptc.pojos.Element;
import co.edu.uptc.pojos.Values;
import co.edu.uptc.view.DashBoard.DirectionEnum;

public class ManagerAlliens {
    private Element element;
    private DirectionEnum direction;
    public boolean statusThread;
    private int width;

    public ManagerAlliens(){
        createElement();
        direction();
        statusThread = true;
    }
    public void createElement(){
        this.element = new Element();
        this.element.setX((int)(Math.random()*Values.widthWindow));
        this.element.setY((int)(Math.random()*Values.heightWindow/2));
        width = (int)(Math.random()*(50-20)+20);
        this.element.setWidth(50);
        this.element.setHeight(50);
        this.element.setType((int)(Math.random()*5));
        this.element.setSpeed((int)(Math.random()*(Values.maxSpeedTime-Values.minSpeedTime+1)+Values.minSpeedTime));
        this.element.setActive(true);
    }
    public void direction(){
        int directionR = (int)(Math.random()*(2)+1);
        switch (directionR) {
            case 1 -> changeDirection(DirectionEnum.LEFT);
            case 2 -> changeDirection(DirectionEnum.RIGHT);
            default -> changeDirection(DirectionEnum.RIGHT);
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
        if(element.getX()-width/2<=0){
            element.setActive(false);
        }
        element.setX(element.getX()-width/3);
    }
    public void right(){
        if(element.getX()+width/2>=Values.widthWindow){
            element.setActive(false);
        }
        element.setX(element.getX()+width/3);
    }
    public Element getElement(){
        return element;
    }
    public void bigMove(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while(statusThread) {
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
}
