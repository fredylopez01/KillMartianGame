package co.edu.uptc;

import co.edu.uptc.presenter.ManagerPresenter;

public class App {
    public static void main(String[] args) throws Exception {
        ManagerPresenter managerPresenter = new ManagerPresenter();
        managerPresenter.run();
    }
}
