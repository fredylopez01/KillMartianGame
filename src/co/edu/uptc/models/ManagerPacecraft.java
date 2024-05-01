package co.edu.uptc.models;

import co.edu.uptc.Utils.Values;
import co.edu.uptc.pojos.Pacecraft;

public class ManagerPacecraft {
    private Pacecraft pacecraft;
    private boolean statusThread;

    public ManagerPacecraft(){
        createPacecraft();
    }
    public void createPacecraft(){
        pacecraft = new Pacecraft();
        pacecraft.setWidth(Values.lengthPaceCraft);
        pacecraft.setHeight(Values.lengthPaceCraft);
        pacecraft.setDx(Values.widthWindow/2-pacecraft.getWidth()/2);
        pacecraft.setType(0);
    }

    public void right(){
        if(pacecraft.getDx()+10<Values.widthWindow-10 && statusThread){
            pacecraft.setDx(pacecraft.getDx()+10);
        }
    }

    public void left(){
        if(pacecraft.getDx()-10>0 && statusThread){
            pacecraft.setDx(pacecraft.getDx()-10);
        }
    }
    public Pacecraft getPacecraft() {
        return pacecraft;
    }
    public void setPacecraft(Pacecraft pacecraft) {
        this.pacecraft = pacecraft;
    }
    public boolean isStatusThread() {
        return statusThread;
    }
    public void setStatusThread(boolean statusThread) {
        this.statusThread = statusThread;
    }
}
