package co.edu.uptc.view.DashBoard;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class MenuPanel extends JPanel {
    private JButton btnStart;
    private JButton btnStop;
    private ActionListener listener;

    public MenuPanel(ActionListener listener) {
        initComponents(listener);
    }

    private void initComponents(ActionListener listener) {
        this.listener = listener;
       setBounds(100, 100, 100, 50);

       addBtn(btnStart, "Start");
       addBtn(btnStop, "Stop");
    }

    public void addBtn(JButton btn, String text){
        btn = new JButton();
        btn.setText(text);
        btn.setActionCommand(text);
        btn.addActionListener(listener);
        btn.setFocusPainted(false);
        btn.setFocusable(false);
        this.add(btn);
    }

}
