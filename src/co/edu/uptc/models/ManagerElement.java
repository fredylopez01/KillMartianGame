package co.edu.uptc.models;

import co.edu.uptc.pojos.Element;
import co.edu.uptc.pojos.Values;
import co.edu.uptc.view.DashBoard.DirectionEnum;

public class ManagerElement {
    private Element element;
    private DirectionEnum direction;
    private DirectionEnum direction1;
    public boolean statusThread;
    private int width;
    private int height;

    public ManagerElement(){
        createElement();
        direction();
        statusThread = true;
    }
    public void createElement(){
        this.element = new Element();
        this.element.setX((int)(Math.random()*Values.widthWindow));
        this.element.setY((int)(Math.random()*Values.heightWindow));
        width = (int)(Math.random()*(50-20)+20);
        height = (int)(Math.random()*(50-20)+20);
        this.element.setWidth(50);
        this.element.setHeight(50);
        this.element.setType((int)(Math.random()*5));
        this.element.setSpeed((int)(Math.random()*(Values.maxSpeedTime-Values.minSpeedTime+1)+Values.minSpeedTime));
    }
    public void direction(){
        int directionR = (int)(Math.random()*3);
        switch (directionR) {
            case 0 -> changeDirection(DirectionEnum.LEFT, DirectionEnum.UP);
            case 1 -> changeDirection(DirectionEnum.RIGHT, DirectionEnum.UP);
            case 2 -> changeDirection(DirectionEnum.RIGHT, DirectionEnum.DOWN);
            case 3 -> changeDirection(DirectionEnum.LEFT, DirectionEnum.DOWN);
            default -> changeDirection(DirectionEnum.LEFT, DirectionEnum.DOWN);
        }
    }
    private void changeDirection(DirectionEnum left, DirectionEnum down) {
        direction = left;
        direction1 = down;
    }
    public void move(){
        if(direction==DirectionEnum.LEFT){
            left();
        }
        if (direction==DirectionEnum.RIGHT) {
            right();
        }
        if(direction1==DirectionEnum.UP){
            up();
        }
        if(direction1==DirectionEnum.DOWN){
            down();
        }
    }

    public void down(){
        if(element.getY()+height>=Values.heightWindow){
            direction1=DirectionEnum.UP;
        }
        element.setY(element.getY()+height/3);
    }
    public void up(){
        if(element.getY()-height/2<=0){
            direction1=DirectionEnum.DOWN;
        }
        element.setY(element.getY()-height/3);
    }
    public void left(){
        if(element.getX()-width/2<=0){
            direction=DirectionEnum.RIGHT;
        }
        element.setX(element.getX()-width/3);
    }
    public void right(){
        if(element.getX()+width/2>=Values.widthWindow){
            direction=DirectionEnum.LEFT;
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
