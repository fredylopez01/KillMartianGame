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
        g.drawImage((Values.adminImg.getImage("pathImgBackground")).getImage(),0, 0,this.getWidth(), this.getHeight(), this);
        paintAliens(g);
        paintPaceCraft(g);
        paintBullets(g);
	}
    public void paintAliens(Graphics g){
        for (Element alien : aliens) {
            g.drawImage(
                typeAlien(alien.getType()).getImage(),
                alien.getX(), 
                alien.getY(),
                alien.getWidth(), 
                alien.getHeight(), 
                this
            );
        }
    }
    private void paintPaceCraft(Graphics g) {
        g.drawImage(
            typePacecraft(pacecraft.getType()).getImage(),
            pacecraft.getDx(), 
            this.getHeight()-pacecraft.getHeight(),
            pacecraft.getWidth(), 
            pacecraft.getHeight(), 
            this
        );
    }
    public void paintBullets(Graphics g){
        for (Element bullet : bullets) {
            g.drawImage(
                typeBullet(bullet.getType()).getImage(),
                bullet.getX(), bullet.getY(),
                bullet.getWidth(), 
                bullet.getHeight(), 
                this
            );
        }
    }
    public ImageIcon typeAlien(int type){
        ImageIcon alien = null;
        switch (type) {
            case 0 -> alien = (Values.adminImg.getImage("pathImgMartian1"));
            case 1 -> alien = (Values.adminImg.getImage("pathImgMartian2"));
            case 3 -> alien = (Values.adminImg.getImage("pathImgMartian3"));
            case 4 -> alien = (Values.adminImg.getImage("pathImgMartian4"));
            case 5 -> alien = (Values.adminImg.getImage("pathImgBurst1"));
            case 6 -> alien = (Values.adminImg.getImage("pathImgBurst2"));
            case 7 -> alien = (Values.adminImg.getImage("pathImgBurst3"));
            case 8 -> alien = (Values.adminImg.getImage("pathImgBurst4"));
            default -> alien = (Values.adminImg.getImage("pathImgMartian4"));
        }
        return alien;
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

    public ImageIcon typeBullet(int type){
        ImageIcon pacecraft = null;
        switch (type) {
            case 0 -> pacecraft = (Values.adminImg.getImage("pathImgBullet1"));
            case 1 -> pacecraft = (Values.adminImg.getImage("pathImgBullet2"));
            default -> pacecraft = (Values.adminImg.getImage("pathImgBullet1"));
        }
        return pacecraft;
    }

}
