package co.edu.uptc.presenter;

import java.util.ArrayList;

import co.edu.uptc.pojos.Element;

public class ContractPlay {
    public interface Model {
        public void setPresenter(Presenter presenter);
        public void start();
        public void resume();
        public void stop();
        public void addAliens();
        public void threadVerifyPositions();
        public void shoot();
        public ArrayList<Element> getElements();
        public void restartGame();
        public int getDeletedMartians();
        public int getActiveMartians();
        public void setTypePacecraft(int type);
        public void paceCraftLeft();
        public void paceCraftRight();
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
        public void shoot();
        public ArrayList<Element> getElements();
        public void restartGame();

        public boolean isPainted();
        public boolean isGameWorking();
        public void setIsGameWorking(boolean isGameWorking);
        public void setPainted(boolean isPainted);
        public int getDeletedMartians();
        public int getActiveMartians();
        public void setTypePacecraft(int type);
        public void paceCraftLeft();
        public void paceCraftRight();
    }
}
