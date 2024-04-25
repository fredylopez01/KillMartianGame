package co.edu.uptc.models;

import java.util.ArrayList;

import co.edu.uptc.pojos.Element;
import co.edu.uptc.presenter.ContractPlay;
import co.edu.uptc.presenter.ContractPlay.Presenter;

public class ManagerModel implements ContractPlay.Model {
    public ContractPlay.Presenter presenter;
    private ArrayList<ManagerElement> managerElements;

    public ManagerModel(){
        managerElements = new ArrayList<>();
    }

    @Override
    public void run(int numberElements){
        for (int i = 0; i < numberElements; i++) {
            ManagerElement managerElement = new ManagerElement();
            managerElements.add(managerElement);
        }
    }
    @Override
    public void start(){
        for (ManagerElement managerElement : managerElements) {
            managerElement.bigMove();
        }
    }
    @Override
    public void resume(){
        for (ManagerElement managerElement : managerElements) {
            managerElement.statusThread = true;
        }
    }
    @Override
    public void stop(){
        for (ManagerElement managerElement : managerElements) {
            managerElement.statusThread = false;
        }
    }
    @Override
    public ArrayList<Element> getElements(){
        ArrayList<Element> elements = new ArrayList<>();
        for (ManagerElement managerElement : managerElements) {
            elements.add(managerElement.getElement());
        }
        return elements;
    }
    

    @Override
    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }

}
