package co.edu.uptc.presenter;

import java.util.ArrayList;

import co.edu.uptc.models.ManagerModel;
import co.edu.uptc.pojos.Element;
import co.edu.uptc.presenter.ContractPlay.Model;
import co.edu.uptc.presenter.ContractPlay.View;
import co.edu.uptc.view.DashBoard.DashBoard;

public class ManagerPresenter implements ContractPlay.Presenter {
    private ContractPlay.Model model;
    private ContractPlay.View view;

    @Override
    public void setModel(Model model) {
        this.model = model;
    }

    @Override
    public void setView(View view) {
       this.view = view;
    }

    public void makeMVP(){
        ManagerModel managerModel = new ManagerModel();
        DashBoard dashBoard = new DashBoard();
        this.model = managerModel;
        this.view = dashBoard;
        managerModel.setPresenter(this);
        dashBoard.setPresenter(this);
    }

    @Override
    public void run() {
        makeMVP();
        view.run();
        model.run(30);
    }

    @Override
    public void start() {
        model.resume();
        model.start();
    }

    @Override
    public void stop(){
        model.stop();
    }

    @Override
    public ArrayList<Element> getElements(){
        return model.getElements();
    }
    
    
}
