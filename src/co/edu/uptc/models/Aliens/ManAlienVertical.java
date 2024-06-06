package co.edu.uptc.models.aliens;

import co.edu.uptc.Utils.Values;
import co.edu.uptc.pojos.DirectionEnum;

public class ManAlienVertical extends ManagerAlien {

    public ManAlienVertical(){
        super();
    }

    @Override
    public void initialPosition() {
        getElement().setX((int)(Math.random()*((Values.widthWindow-getElement().getWidth())-1)));
        getElement().setY(0);
        setDirection(DirectionEnum.DOWN);
    }

    @Override
    public void move() {
        if(getDirection() == DirectionEnum.DOWN){
            down();
        }
        upDatePositions();
    }

    public void down(){
        if(getElement().getY()+getElement().getSpeed()>=Values.heightWindow){
            stopThread();
        }
        getElement().setY(getElement().getY()+getElement().getSpeed());
    }
    
}
