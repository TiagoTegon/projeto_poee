package com.projeto.view.usuario;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.projeto.model.PrintJasperReport;
import com.projeto.model.models.Usuario;
import com.projeto.model.service.JasperReportsService;
import com.projeto.model.service.UsuarioService;

import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class RelUsuario extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JButton btnNewButton;
	
	public RelUsuario(JFrame frame, boolean modal) {
		
		super(frame, modal);
		
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		btnNewButton = new JButton("Relatório SQL");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				imprimeRelatorioPorSQL();
			}
		});
		btnNewButton.setActionCommand("");
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnNewButton)
					.addContainerGap(317, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPanel.createSequentialGroup()
					.addContainerGap(184, Short.MAX_VALUE)
					.addComponent(btnNewButton)
					.addContainerGap())
		);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						imprimeRelatorioPorLista();
						
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	private void imprimeRelatorioPorLista() {
		UsuarioService usuarioService = new UsuarioService();
		PrintJasperReport printJasperReport = new PrintJasperReport();
		JasperReportsService jasperReportsService = new JasperReportsService();
		
		List<Usuario> listaUsuario = usuarioService.findAll();
		
		printJasperReport.setFile("rel_usuario");
		printJasperReport.setCollection(listaUsuario);
		setVisible(false);
		jasperReportsService.generateListReports(printJasperReport);
	}
	
	private void imprimeRelatorioPorSQL() {
		PrintJasperReport printJasperReport = new PrintJasperReport();
		JasperReportsService jasperReportsService = new JasperReportsService();
		printJasperReport.setFile("rel_usuario_sql");
		setVisible(false);
		jasperReportsService.generateSQLReports(printJasperReport);
		
	}

}
