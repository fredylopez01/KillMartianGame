package co.edu.uptc.presenter;

import java.util.ArrayList;

import co.edu.uptc.models.ManagerPacecraft;
import co.edu.uptc.pojos.Element;

public class ContractPlay {
    public interface Model {
        public void setPresenter(Presenter presenter);
        public void start();
        public void resume();
        public void stop();
        public void addAliens();
        public void threadVerifyPositions();
        public boolean shoot();
        public ArrayList<Element> getElements();
        public void restartGame();
        public ManagerPacecraft getManagerPacecraft();
        public ArrayList<Element> getBullets();
        public int getDeletedMartians();
        public int getActiveMartians();
        public void setTypePacecraft(int type);
    }

    public interface View {
        public void setPresenter(Presenter presenter);
        public void run();
    }

    public interface Presenter {
        public void setModel(Model model);
        public void setView(View view);
        public void run();

        public void start();
        public void stop();
        public boolean shoot();
        public ArrayList<Element> getElements();
        public void restartGame();
        public ManagerPacecraft getManagerPacecraft();
        public ArrayList<Element> getBullets();

        public boolean isPainted();
        public boolean isGameWorking();
        public void setIsGameWorking(boolean isGameWorking);
        public void setPainted(boolean isPainted);
        public int getDeletedMartians();
        public int getActiveMartians();
        public void setTypePacecraft(int type);
    }
}
