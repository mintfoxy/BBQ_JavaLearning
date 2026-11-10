package gui.sandbox;

import java.awt.GridLayout;
import java.awt.HeadlessException;
import javax.swing.JFrame;
import gui.GuiConstants;

public class MyFrame extends JFrame{

    public MyFrame() throws HeadlessException{

        super(GuiConstants.APP_NAME);
        setBounds(GuiConstants.FRAME_BOUNDS);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(0, 2));

        initComponents();

        setVisible(true);
    }

    private void initComponents(){

        add(new LabelPanel());

    }

    public static void main(String[] args) {
        
        new MyFrame();
    }

}
