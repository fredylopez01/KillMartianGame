package co.edu.uptc.view.DashBoard;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import co.edu.uptc.pojos.Element;

public class WorkPanel extends JPanel {
    private ArrayList<Element> elements;
    
    public WorkPanel(){
        initComponents();
    }

    private void initComponents(){
        this.elements = new ArrayList<>();
        this.setBackground(Color.BLACK);
    }

    public void start(ArrayList<Element> elementsP){
        this.elements = elementsP;
        repaint();
    }
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
        for (Element element : elements) {
            chooseType(g, 0, element);
        }
	}

    public void chooseType(Graphics g, int v, Element b){
        switch (v) {
            case 0 -> g.drawImage(new ImageIcon(getClass().getResource("/co/edu/uptc/view/DashBoard/img/spacecraft4.png")).getImage(), b.getX(), b.getY(), b.getWidth(), b.getHeight(), this);
            case 1 -> g.drawImage(new ImageIcon(getClass().getResource("/co/edu/uptc/view/DashBoard/img/spacecraft2.png")).getImage(), b.getX(), b.getY(), b.getWidth(), b.getHeight(), this);
            case 3 -> g.drawImage(new ImageIcon(getClass().getResource("/co/edu/uptc/view/DashBoard/img/spacecraft3.png")).getImage(), b.getX(), b.getY(), b.getWidth(), b.getHeight(), this);
            case 4 -> g.drawImage(new ImageIcon(getClass().getResource("/co/edu/uptc/view/DashBoard/img/spacecraft4.png")).getImage(), b.getX(), b.getY(), b.getWidth(), b.getHeight(), this);
            default -> g.drawImage(new ImageIcon(getClass().getResource("/co/edu/uptc/view/DashBoard/img/spacecraft5.png")).getImage(), b.getX(), b.getY(), b.getWidth(), b.getHeight(), this);
        }
    }

}
