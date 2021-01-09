package com.projeto.view.cliente;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.projeto.model.PrintJasperReport;
import com.projeto.model.models.Cliente;
import com.projeto.model.service.ClienteService;
import com.projeto.model.service.JasperReportsService;

import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class RelCliente extends JDialog {

	private static final long serialVersionUID = -653090797393408936L;

	private final JPanel contentPanel = new JPanel();

	public RelCliente(JFrame frame, boolean modal) {
		
		super(frame, modal);
		
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						ClienteService clienteService = new ClienteService();
						PrintJasperReport printJasperReport = new PrintJasperReport();
						JasperReportsService jasperReportService = new JasperReportsService();
						
						List<Cliente> listaCliente = clienteService.findAll();
						
						printJasperReport.setFile("rel_cliente");
						printJasperReport.setCollection(listaCliente);
						setVisible(false);
						jasperReportService.generateListReports(printJasperReport);
						
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
