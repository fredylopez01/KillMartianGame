package co.edu.uptc.models;

import co.edu.uptc.Utils.Values;
import co.edu.uptc.pojos.Element;

public class ManagerPacecraft {
    private Element pacecraft;
    private boolean statusThread;

    public ManagerPacecraft(){
        createPacecraft();
    }
    public void createPacecraft(){
        pacecraft = new Element();
        pacecraft.setWidth(Values.lengthPaceCraft);
        pacecraft.setHeight(Values.lengthPaceCraft);
        pacecraft.setX(Values.widthWindow/2-pacecraft.getWidth()/2);
        pacecraft.setY(Values.heightWindow-pacecraft.getHeight()-83);
        pacecraft.setType(2);
        pacecraft.setPathImg("pathImgPacecraft"+(pacecraft.getType()+1));
    }

    public void right(){
        if(pacecraft.getX()+pacecraft.getWidth()<Values.widthWindow-10 && statusThread){
            pacecraft.setX(pacecraft.getX()+10);
        }
    }

    public void left(){
        if(pacecraft.getX()>0 && statusThread){
            pacecraft.setX(pacecraft.getX()-10);
        }
    }
    public int getType(){
        return pacecraft.getType();
    }
    public void setType(int type){
        pacecraft.setType(type);
        pacecraft.setPathImg("pathImgPacecraft"+(type+1));
    }
    public int getX(){
        return pacecraft.getX();
    }
    public Element getPacecraft() {
        return pacecraft;
    }
    public void setPacecraft(Element pacecraft) {
        this.pacecraft = pacecraft;
    }
    public boolean isStatusThread() {
        return statusThread;
    }
    public void setStatusThread(boolean statusThread) {
        this.statusThread = statusThread;
    }
}
