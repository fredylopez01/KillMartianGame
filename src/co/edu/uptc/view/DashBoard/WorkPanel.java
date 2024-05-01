package co.edu.uptc.view.DashBoard;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import co.edu.uptc.pojos.Element;
import co.edu.uptc.pojos.Pacecraft;

public class WorkPanel extends JPanel {
    private ArrayList<Element> alliens;
    private Pacecraft pacecraft;
    private ArrayList<Element> bullets;
    
    public WorkPanel(){
        initComponents();
    }

    private void initComponents(){
        this.alliens = new ArrayList<>();
        this.pacecraft = new Pacecraft();
        this.bullets = new ArrayList<>();
        this.setBackground(Color.BLACK);
    }

    public void start(ArrayList<Element> alliens){
        this.alliens = alliens;
    }
    public void movePaceCraft(Pacecraft pacecraft){
        this.pacecraft = pacecraft;
    }
    public void shoot(ArrayList<Element> bullets){
        this.bullets = bullets;
    }
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
        g.drawImage(new ImageIcon(getClass().getResource("/co/edu/uptc/view/DashBoard/img/background.jpg")). getImage(),0, 0,this.getWidth(), this.getHeight(), this);
        paintAlliens(g);
        paintPaceCraft(g);
        paintBullets(g);
	}
    public void paintAlliens(Graphics g){
        for (Element allien : alliens) {
            if(allien.isActive()){
                g.drawImage(
                    new ImageIcon(getClass().getResource(typeAllien(allien.getType()))).getImage(),
                    allien.getX(), 
                    allien.getY(),
                    allien.getWidth(), 
                    allien.getHeight(), 
                    this
                );
            }
        }
    }
    private void paintPaceCraft(Graphics g) {
        if(pacecraft.getDx() != 0){
            g.drawImage(
            new ImageIcon(getClass().getResource(typePacecraft(pacecraft.getType()))).getImage(),
            pacecraft.getDx(), 
            this.getHeight()-pacecraft.getHeight(),
            pacecraft.getWidth(), 
            pacecraft.getHeight(), 
            this
            );
        }
    }
    public void paintBullets(Graphics g){
        for (Element bullet : bullets) {
            if(bullet.isActive()){
                g.drawImage(
                    new ImageIcon(getClass().getResource(typeBullet(bullet.getType()))).getImage(),
                    bullet.getX(), bullet.getY(),
                    bullet.getWidth(), 
                    bullet.getHeight(), 
                    this
                );
            }
        }
    }
    public String typeAllien(int type){
        String allien = null;
        switch (type) {
            case 0 -> allien = "/co/edu/uptc/view/DashBoard/img/martian2.png";
            case 1 -> allien = "/co/edu/uptc/view/DashBoard/img/martian1.png";
            case 3 -> allien = "/co/edu/uptc/view/DashBoard/img/martian2.png";
            case 4 -> allien ="/co/edu/uptc/view/DashBoard/img/martian1.png";
            default -> allien = "/co/edu/uptc/view/DashBoard/img/martian2.png";
        }
        return allien;
    }

    public String typePacecraft(int type){
        String pacecraft = null;
        switch (type) {
            case 0 -> pacecraft = "/co/edu/uptc/view/DashBoard/img/pacecraft2.png";
            case 1 -> pacecraft = "/co/edu/uptc/view/DashBoard/img/pacecraft3.png";
            case 2 -> pacecraft= "/co/edu/uptc/view/DashBoard/img/spacecraft4.png";
            default -> pacecraft = "/co/edu/uptc/view/DashBoard/img/spacecraft5.png";
        }
        return pacecraft;
    }

    public String typeBullet(int type){
        String pacecraft = null;
        switch (type) {
            case 0 -> pacecraft = "/co/edu/uptc/view/DashBoard/img/bullet1.png";
            case 1 -> pacecraft = "/co/edu/uptc/view/DashBoard/img/bullet2.png";
            case 2 -> pacecraft= "/co/edu/uptc/view/DashBoard/img/bullet1.png";
            default -> pacecraft = "/co/edu/uptc/view/DashBoard/img/bullet2.png";
        }
        return pacecraft;
    }

}
