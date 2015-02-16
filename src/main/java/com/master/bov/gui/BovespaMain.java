package com.master.bov.gui;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.WindowConstants;

import com.master.bov.gui.win.Tela;

public class BovespaMain extends JFrame {

	private final JDesktopPane pane;

	public BovespaMain() {
		super("Bovespa");
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.pane = new JDesktopPane();
		this.setJMenuBar(this.getMenu());
		this.setContentPane(this.pane);
		this.setVisible(true);
		this.setSize(800, 600);
	}

	private JMenuBar getMenu() {
		final JMenuBar menu = new JMenuBar();
		menu.add(this.getMenuPrincipal());
		return menu;
	}

	private JMenu getMenuPrincipal() {
		final JMenu menu = new JMenu("Principal");
		menu.add(this.getMenuPrincipalConfigurarConexao());
		return menu;
	}

	private JMenuItem getMenuPrincipalConfigurarConexao() {
		final JMenuItem item = new JMenuItem(new AbstractAction("Conexão") {

			@Override
			public void actionPerformed(final ActionEvent arg0) {
				new Tela(BovespaMain.this, "Conexaõ", new ConexaoConfigurar(null));
			}

		});
		return item;
	}

	public static void main(final String[] args) {
		final BovespaMain main = new BovespaMain();
	}

}
