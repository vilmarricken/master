package com.master.bov.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JToolBar;

import com.master.bov.entity.Conexao;

public class ConexaoConfigurar extends JPanel {

	private JTextField fieldUrl;

	private JTextField fieldUser;

	private JPasswordField fieldPassword;

	public ConexaoConfigurar(final Conexao conexao) {
		this.setLayout(new BorderLayout());
		this.add(this.getToolBar(), BorderLayout.NORTH);
		this.add(this.getBody(), BorderLayout.CENTER);
	}

	private Component getToolBar() {
		final JToolBar bar = new JToolBar();
		bar.add(this.getButtonSave());
		return bar;
	}

	private JButton getButtonSave() {
		final JButton button = new JButton();
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(final ActionEvent e) {
				// TODO Auto-generated method stub

			}
		});
		button.setToolTipText("Salvar");

		final String imgLocation = "/disk_blue.png";
		final URL imageURL = this.getClass().getResource(imgLocation);

		button.setIcon(new ImageIcon(imageURL, "Salvar"));
		return button;
	}

	private JPanel getBody() {
		final JPanel body = new JPanel(new GridBagLayout());
		final GridBagConstraints cons = new GridBagConstraints();
		cons.anchor = GridBagConstraints.WEST;
		body.add(this.getLabelUrl(cons), cons);
		body.add(this.getFieldUrl(cons), cons);
		body.add(this.getLabelUser(cons), cons);
		body.add(this.getFieldUser(cons), cons);
		body.add(this.getLabelPassword(cons), cons);
		body.add(this.getFieldPassword(cons), cons);
		return body;
	}

	private Component getLabelUser(final GridBagConstraints cons) {
		cons.gridy++;
		return new JLabel("Usuário:");
	}

	private Component getLabelPassword(final GridBagConstraints cons) {
		cons.gridy++;
		return new JLabel("Senha:");
	}

	private Component getLabelUrl(final GridBagConstraints cons) {
		cons.gridy++;
		return new JLabel("URL:");
	}

	private JTextField getFieldUrl(final GridBagConstraints cons) {
		if (this.fieldUrl == null) {
			cons.gridy++;
			this.fieldUrl = new JTextField(50);
		}
		return this.fieldUrl;
	}

	private JTextField getFieldPassword(final GridBagConstraints cons) {
		if (this.fieldPassword == null) {
			cons.gridy++;
			this.fieldPassword = new JPasswordField(20);
		}
		return this.fieldPassword;
	}

	private JTextField getFieldUser(final GridBagConstraints cons) {
		if (this.fieldUser == null) {
			cons.gridy++;
			this.fieldUser = new JTextField(20);
		}
		return this.fieldUser;
	}

}
