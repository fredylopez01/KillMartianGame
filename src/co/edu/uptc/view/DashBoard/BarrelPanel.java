package co.edu.uptc.view.DashBoard;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class BarrelPanel extends JPanel implements KeyListener {
    private Image img;
    private int dx;
    
    public BarrelPanel(){
        initComponents();
    }

    private void initComponents(){
        img = new ImageIcon(getClass().getResource("/co/edu/uptc/view/DashBoard/img/spacecraft3.png")).
        getImage();
        this.setBackground(Color.BLACK);
        this.setPreferredSize(new Dimension(100, 100));
        dx = 100;
    }

    public void start(){
        repaint();
    }
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
        g.drawImage(img,dx, 0, 100, 100, this);
	}

    public void chooseType(Graphics g, int v){
        switch (v) {
            case 0 -> g.drawImage(img,dx, 0, 100, 100, this);
            case 1 -> g.drawImage(new ImageIcon(getClass().getResource("/co/edu/uptc/view/DashBoard/img/spacecraft2.png")).getImage(),dx, 0, 100, 100, this);
            case 3 -> g.drawImage(new ImageIcon(getClass().getResource("/co/edu/uptc/view/DashBoard/img/spacecraft3.png")).getImage(),dx, 0, 100, 100, this);
            case 4 -> g.drawImage(new ImageIcon(getClass().getResource("/co/edu/uptc/view/DashBoard/img/spacecraft4.png")).getImage(),dx, 0, 100, 100, this);
            default -> g.drawImage(new ImageIcon(getClass().getResource("/co/edu/uptc/view/DashBoard/img/spacecraft5.png")).getImage(),dx, 0, 100, 100, this);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
            if(dx>0)
                dx-=10;
        }
        if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
            if(dx<740)
                dx+=10;
        }
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
}
