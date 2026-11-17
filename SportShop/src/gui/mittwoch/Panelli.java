package gui.mittwoch;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.JPanel;
import javax.swing.JTextField;

public class Panelli extends JPanel {

	private Knopka button;

	/**
	 * Konstruktor
	 */
	public Panelli() {
		super();
		this.setBackground(new Color(90, 160, 220));
		this.setLayout(null);
		initComponents();

		Component[] alle = getComponents();
		for (Component comp : alle) {
			IO.println(comp.getName());
		}
	}

	/**
	 * Initialisiert Komponenten, die zum Pannel hinzugefügt werden
	 */
	private void initComponents() {
		initLabel();

		initTextField();

		initButton();
	}

	private JTextField textField;

	private void initTextField() {

		textField = new JTextField();

		textField.setName("TF");
		textField.setFont(new Font("Arial", Font.ROMAN_BASELINE, 32));
		add(textField);
		textField.setText("Hallo");
		textField.setBounds(100, 120, 400, 40);

	}

	/**
	 * Initialisiert und addet einen Button
	 */
	private void initButton() {
		button = new Knopka("Speichern");

		button.setName("B");
		button.setActionCommand("POPUP");
		this.add(button);

		button.addActionListener(_-> sagHallo());
		
		Knopka knoppille = new Knopka("Button2");
		knoppille.addActionListener(e-> sagHallo(e));
		knoppille.setBounds(100, 300, 400, 40);
		add(knoppille);

		Knopka knoppi = new Knopka("Button3");
		knoppi.addActionListener(this:: sagHallo);
		knoppi.setBounds(100, 400, 400, 40);
		add(knoppi);
	}

	
	void sagHallo() {
		IO.println("Hallo");
	}

	void sagHallo(ActionEvent e) {
		IO.println("Hallo");
	}

	/**
	 * Initialisiert und addet ein Label
	 */
	private void initLabel() {
		Schild shildik = new Schild("Halli Hallo");
		this.add(shildik);

		shildik.setName("L");
	}

//	@Override
//	public void actionPerformed(ActionEvent e) {
//
//		if (e.getSource() == button) {

//		if (e.getActionCommand().equals("POPUP")) {
//			String text = textField.getText();
//
//			JOptionPane.showMessageDialog(this, text);
//		}
//
//	}

}
