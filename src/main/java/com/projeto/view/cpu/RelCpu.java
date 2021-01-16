package com.projeto.view.cpu;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.projeto.model.models.Cpu;
import com.projeto.model.models.PrintJasperReport;
import com.projeto.model.service.CpuService;
import com.projeto.model.service.JasperReportsService;

import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class RelCpu extends JDialog {

	private static final long serialVersionUID = -4043745303141489212L;
	
	private final JPanel contentPanel = new JPanel();

	public RelCpu(JFrame frame, boolean modal) {
		
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
						
						CpuService cpuService = new CpuService();
						PrintJasperReport printJasperReport = new PrintJasperReport();
						JasperReportsService jasperReportsService = new JasperReportsService();
						
						List<Cpu> listaCpu = cpuService.findAll();
						
						printJasperReport.setFile("rel_cpu");
						printJasperReport.setCollection(listaCpu);
						setVisible(false);
						jasperReportsService.generateListReports(printJasperReport);
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
