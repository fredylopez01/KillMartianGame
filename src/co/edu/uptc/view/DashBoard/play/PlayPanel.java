package co.edu.uptc.view.DashBoard.play;

import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import co.edu.uptc.Utils.Values;
import co.edu.uptc.pojos.Element;

public class PlayPanel extends JPanel {
    private ArrayList<Element> elements;
    
    public PlayPanel(){
        initComponents();
    }

    private void initComponents(){
        this.elements = new ArrayList<>();
    }
    public void start(ArrayList<Element> elements){
        this.elements = elements;
    }
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
        g.drawImage((Values.adminImg.getImage("pathImgBack")).getImage(),0, 0,this.getWidth(), this.getHeight(), this);
        paintElements(g);
	}
    public void paintElements(Graphics g){
        for (Element alien : elements) {
            g.drawImage(
                elementImg(alien.getType(), alien.getPathImg()).getImage(),
                alien.getX(), 
                alien.getY(),
                alien.getWidth(), 
                alien.getHeight(), 
                this
            );
        }
    }
    public ImageIcon elementImg(int type, String pathImg){
        return (Values.adminImg.getImage(pathImg));
    }

    public ImageIcon typePacecraft(int type){
        ImageIcon pacecraft = null;
        switch (type) {
            case 0 -> pacecraft = (Values.adminImg.getImage("pathImgPacecraft1"));
            case 1 -> pacecraft = (Values.adminImg.getImage("pathImgPacecraft2"));
            case 2 -> pacecraft= (Values.adminImg.getImage("pathImgPacecraft3"));
            case 3 -> pacecraft = (Values.adminImg.getImage("pathImgPacecraft4"));
            default -> pacecraft = (Values.adminImg.getImage("pathImgPacecraft4"));
        }
        return pacecraft;
    }

}
