package co.edu.uptc.presenter;

import java.util.ArrayList;

import co.edu.uptc.pojos.Element;

public class ContractPlay {
    public interface Model {
        public void setPresenter(Presenter presenter);
        public void start();
        public void resume();
        public void stop();
        public void run(int numberElements);
        public ArrayList<Element> getElements();
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
        public ArrayList<Element> getElements();
    }
}
