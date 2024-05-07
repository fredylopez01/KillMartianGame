package co.edu.uptc.view.DashBoard.play;

import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import co.edu.uptc.Utils.Values;
import co.edu.uptc.pojos.Element;
import co.edu.uptc.pojos.Pacecraft;

public class PlayPanel extends JPanel {
    private ArrayList<Element> aliens;
    private Pacecraft pacecraft;
    private ArrayList<Element> bullets;
    
    public PlayPanel(){
        initComponents();
    }

    private void initComponents(){
        this.aliens = new ArrayList<>();
        this.pacecraft = new Pacecraft();
        this.bullets = new ArrayList<>();
    }

    public void start(ArrayList<Element> aliens){
        this.aliens = aliens;
    }
    public void movePaceCraft(Pacecraft pacecraft){
        this.pacecraft = pacecraft;
    }
    public void shoot(ArrayList<Element> bullets){
        this.bullets = bullets;
    }
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
        g.drawImage(new ImageIcon(getClass().getResource(Values.pathImgBackground)).getImage(),0, 0,this.getWidth(), this.getHeight(), this);
        paintAliens(g);
        paintPaceCraft(g);
        paintBullets(g);
	}
    public void paintAliens(Graphics g){
        for (Element alien : aliens) {
            if(alien.isActive()){
                g.drawImage(
                    new ImageIcon(getClass().getResource(typeAlien(alien.getType()))).getImage(),
                    alien.getX(), 
                    alien.getY(),
                    alien.getWidth(), 
                    alien.getHeight(), 
                    this
                );
            }
        }
    }
    private void paintPaceCraft(Graphics g) {
        g.drawImage(
            new ImageIcon(getClass().getResource(typePacecraft(pacecraft.getType()))).getImage(),
            pacecraft.getDx(), 
            this.getHeight()-pacecraft.getHeight(),
            pacecraft.getWidth(), 
            pacecraft.getHeight(), 
            this
        );
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
    public String typeAlien(int type){
        String alien = null;
        switch (type) {
            case 0 -> alien = Values.pathImgMartian1;
            case 1 -> alien = Values.pathImgMartian2;
            case 3 -> alien = Values.pathImgMartian3;
            case 4 -> alien = Values.pathImgMartian4;
            case 6 -> alien = Values.pathImgBurst;
            default -> alien = Values.pathImgMartian4;
        }
        return alien;
    }

    public String typePacecraft(int type){
        String pacecraft = null;
        switch (type) {
            case 0 -> pacecraft = Values.pathImgPacecraft1;
            case 1 -> pacecraft = Values.pathImgPacecraft2;
            case 2 -> pacecraft= Values.pathImgPacecraft3;
            case 3 -> pacecraft = Values.pathImgPacecraft4;
            default -> pacecraft = Values.pathImgPacecraft4;
        }
        return pacecraft;
    }

    public String typeBullet(int type){
        String pacecraft = null;
        switch (type) {
            case 0 -> pacecraft = Values.pathImgBullet1;
            case 1 -> pacecraft = Values.pathImgBullet2;
            default -> pacecraft = Values.pathImgBullet1;
        }
        return pacecraft;
    }

}
