package com.projeto.view.cliente;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.PatternSyntaxException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableRowSorter;

import com.projeto.model.models.Cliente;
import com.projeto.model.service.ClienteService;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.RowFilter;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BuscaCliente extends JDialog {

	private static final long serialVersionUID = -8704623160103851353L;
	
	private static final int CODIGO = 0;
	private static final int NOME = 1;
	private static final int CPF = 2;
	private static final int EMAIL = 3;
	private static final int TELEFONE = 4;
	
	private final JPanel contentPanel = new JPanel();
	private JScrollPane scrollPane;
	private JTable tableCliente;
	private JButton btnInserirCliente;
	private JLabel lblPesquisaCliente;
	private JTextField textField;
	
	private TabelaClienteModel tabelaClienteModel;
	private TableRowSorter<TabelaClienteModel> sortTabelaCliente;
	private List<Cliente> listaCliente;
	
	private Integer codigoCliente;
	private String nomeCliente;
	private String emailCliente;
	private String cpfCliente;
	private String telefoneCliente; 
	private boolean selectCliente;

//	public static void main(String[] args) {
//		try {
//			BuscaCliente dialog = new BuscaCliente();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	public BuscaCliente(JFrame frame, boolean modal) {
		
		super(frame, modal);
		
		initComponents();
		setResizable(false);
		iniciarDados();
	}

	private void iniciarDados() {
		listaCliente = new ArrayList<Cliente>();
	}

	private void initComponents() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 606, 451);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		scrollPane = new JScrollPane();
		
		btnInserirCliente = new JButton("Cadastrar Cliente");
		btnInserirCliente.setIcon(new ImageIcon(BuscaCliente.class.getResource("/com/projeto/estrutura/imagens/application_form_add.png")));
		
		lblPesquisaCliente = new JLabel("Pesquisar Cliente:");
		
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				String filtro = textField.getText();
				filtraNomeCliente(filtro);
			}

		});
		textField.setColumns(10);
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(45)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(lblPesquisaCliente)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField))
						.addComponent(btnInserirCliente)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 484, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(51, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(25)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPesquisaCliente)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 278, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
					.addComponent(btnInserirCliente)
					.addContainerGap())
		);
		
		tableCliente = new JTable();
		
		tabelaClienteModel = new TabelaClienteModel();
		tabelaClienteModel.setListaCliente(carregarListaCliente());
		tableCliente.setModel(tabelaClienteModel);
		scrollPane.setViewportView(tableCliente);
		
		tableCliente.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		sortTabelaCliente = new TableRowSorter<TabelaClienteModel>(tabelaClienteModel);
		tableCliente.setRowSorter(sortTabelaCliente);
		tableCliente.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		
		scrollPane.setViewportView(tableCliente);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						selecionaCliente();
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
						setSelectCliente(false);
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	protected void selecionaCliente() {
		if(tableCliente.getSelectedRow() != -1 &&
				tableCliente.getSelectedRow() < tabelaClienteModel.getRowCount()) {
			setCodigoCliente(Integer.valueOf(tableCliente.getValueAt(tableCliente.getSelectedRow(), CODIGO).toString()));
			setNomeCliente(tableCliente.getValueAt(tableCliente.getSelectedRow(), NOME).toString());
			setCpfCliente(tableCliente.getValueAt(tableCliente.getSelectedRow(), CPF).toString());
			setEmailCliente(tableCliente.getValueAt(tableCliente.getSelectedRow(), EMAIL).toString());
			setTelefoneCliente(tableCliente.getValueAt(tableCliente.getSelectedRow(), TELEFONE).toString());
			setSelectCliente(true);
			dispose();
		} else {
			setSelectCliente(false);
		}
	}
	
	private List<Cliente> carregarListaCliente(){
		ClienteService clienteService = new ClienteService();
		return clienteService.findAll();
	}
	
	private void filtraNomeCliente(String filtro) {

		RowFilter<TabelaClienteModel, Object> rowFilter = null;
		try {
			rowFilter = RowFilter.regexFilter(filtro);
		} catch(PatternSyntaxException e) {
			return;
		}
		sortTabelaCliente.setRowFilter(rowFilter);
	}
	
	public Integer getCodigoCliente() {
		return codigoCliente;
	}

	public void setCodigoCliente(Integer codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getEmailCliente() {
		return emailCliente;
	}

	public void setEmailCliente(String emailCliente) {
		this.emailCliente = emailCliente;
	}

	public String getCpfCliente() {
		return cpfCliente;
	}

	public void setCpfCliente(String cpfCliente) {
		this.cpfCliente = cpfCliente;
	}

	public String getTelefoneCliente() {
		return telefoneCliente;
	}

	public void setTelefoneCliente(String telefoneCliente) {
		this.telefoneCliente = telefoneCliente;
	}

	public boolean isSelectCliente() {
		return selectCliente;
	}

	public void setSelectCliente(boolean selectCliente) {
		this.selectCliente = selectCliente;
	}
	
	
}
