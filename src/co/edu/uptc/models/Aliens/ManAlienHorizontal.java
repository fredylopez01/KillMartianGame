package co.edu.uptc.models.Aliens;

import co.edu.uptc.Utils.Values;
import co.edu.uptc.pojos.DirectionEnum;

public class ManAlienHorizontal extends ManagerAlien {

    public ManAlienHorizontal(){
        super();
    }
    @Override
    public void initialPosition(){
        int directionR = (int)(Math.random()*(2)+1);
        switch (directionR) {
            case 1 -> initialPositionRigth();
            case 2 -> initialPositionLeft();
            default -> initialPositionLeft();
        }
    }
    public void initialPositionRigth(){
        getElement().setX(Values.widthWindow-getElement().getWidth());
        getElement().setY((int)(Math.random()*Values.heightWindow/2));
        setDirection(DirectionEnum.LEFT);
    }
    public void initialPositionLeft(){
        getElement().setX(0);
        getElement().setY((int)(Math.random()*Values.heightWindow/2));
        setDirection(DirectionEnum.RIGHT);
    }
    @Override
    public void move(){
        if(getDirection()==DirectionEnum.LEFT){
            left();
        }else if (getDirection()==DirectionEnum.RIGHT) {
            right();
        }
        super.upDatePositions();
    }
    public void left(){
        if(getElement().getX()-getElement().getWidth()/2<=0-getElement().getWidth()){
            stopThread();
        }
        getElement().setX(getElement().getX()-getElement().getSpeed());
    }
    public void right(){
        if(getElement().getX()+getElement().getWidth()/2>=Values.widthWindow){
            stopThread();
        }
        getElement().setX(getElement().getX()+getElement().getSpeed());
    }
}
