package gui.sandbox;

import javax.swing.JPanel;
import gui.GuiConstants;

public abstract class MyPanel extends JPanel {

    public MyPanel() {
        super();
        setFont(GuiConstants.FONT);
        initLayout();
        initComponents();
    }

    protected abstract void initLayout();
    protected abstract void initComponents();

}
