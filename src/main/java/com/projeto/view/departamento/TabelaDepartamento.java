package com.projeto.view.departamento;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;

public class TabelaDepartamento extends JInternalFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TabelaDepartamento frame = new TabelaDepartamento();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TabelaDepartamento() {
		setBounds(100, 100, 450, 300);

	}

}
