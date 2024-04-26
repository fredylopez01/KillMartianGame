package co.edu.uptc.models;

import co.edu.uptc.pojos.Pacecraft;
import co.edu.uptc.pojos.Values;

public class ManagerPacecraft {
    private Pacecraft pacecraft;
    private boolean statusThread;

    public ManagerPacecraft(){
        createPacecraft();
    }
    public void createPacecraft(){
        pacecraft = new Pacecraft();
        pacecraft.setWidth(100);
        pacecraft.setHeight(100);
        pacecraft.setDx(Values.widthWindow/2-pacecraft.getWidth()/2);
        pacecraft.setType(0);
    }

    public void right(){
        if(pacecraft.getDx()<Values.widthWindow-pacecraft.getWidth() && statusThread){
            pacecraft.setDx(pacecraft.getDx()+10);
        }
    }

    public void left(){
        if(pacecraft.getDx()>0 && statusThread){
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
