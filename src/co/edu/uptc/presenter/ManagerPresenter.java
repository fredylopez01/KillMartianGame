package co.edu.uptc.presenter;

import java.util.ArrayList;

import co.edu.uptc.models.ManagerModel;
import co.edu.uptc.models.ManagerPacecraft;
import co.edu.uptc.pojos.Element;
import co.edu.uptc.presenter.ContractPlay.Model;
import co.edu.uptc.presenter.ContractPlay.View;
import co.edu.uptc.view.DashBoard.DashBoard;

public class ManagerPresenter implements ContractPlay.Presenter {
    private ContractPlay.Model model;
    private ContractPlay.View view;
    private boolean isPainted;
    private boolean isGameWorking;

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
    }

    @Override
    public void start() {
        model.addAlliens();
        model.resume();
        model.start();
        model.threadVerifyPositions();
    }

    @Override
    public void stop(){
        model.stop();
    }
    @Override
    public void shoot() {
        model.shoot();
    }
    @Override
    public ArrayList<Element> getElements(){
        return model.getElements();
    }
    @Override
    public ManagerPacecraft getManagerPacecraft() {
        return model.getManagerPacecraft();
    }
    @Override
    public ArrayList<Element> getBullets() {
        return model.getBullets();
    }
    @Override
    public boolean isPainted(){
        return this.isPainted;
    }
    @Override
    public void setPainted(boolean isPainted){
        this.isPainted = isPainted;
    }
    @Override
    public boolean isGameWorking() {
        return isGameWorking;
    }
    @Override
    public void setIsGameWorking(boolean isGameWorking) {
        this.isGameWorking = isGameWorking;
    }
    
}
