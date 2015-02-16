package com.master.bov.gui.win;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import com.master.bov.gui.BovespaMain;

public class Tela extends JDialog {

	private final BovespaMain owner;

	public Tela(final BovespaMain owner, final String title, final JPanel content) {
		super(owner, title, true);
		this.owner = owner;
		this.setContentPane(content);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.pack();
		this.setVisible(true);
	}

}
