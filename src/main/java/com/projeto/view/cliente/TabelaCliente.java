package com.projeto.view.cliente;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.TableRowSorter;

import com.projeto.estrutura.util.VariaveisProjeto;
import com.projeto.model.models.Cliente;
import com.projeto.model.service.ClienteService;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.PatternSyntaxException;

public class TabelaCliente extends JInternalFrame {

	private static final long serialVersionUID = -5423496758561658914L;
	
	private static final int CODIGO = 0;
	private static final int NOME = 1;
	private static final int CPF = 2;
	private static final int EMAIL = 3;
	private static final int TELEFONE = 4;
	
	private JScrollPane scrollPane;
	private JTable tabelaCliente;
	private JPanel panel;
	private JButton btnPrimeiro;
	private JButton btnAnterior;
	private JButton btnProximo;
	private JButton btnUltimo;
	private JLabel lblNewLabel;
	private JComboBox comboBox;
	private JButton btnIncluir;
	private JButton btnAlterar;
	private JButton btnExcluir;
	private JButton btnSair;
	private JLabel lblPagina;
	private JLabel lblInicio;
	private JLabel lblNewLabel_1;
	private JLabel lblTotal;
	private JLabel lblNewLabel_2;
	private JLabel lblFinal;
	private JLabel lblNewLabel_3;
	private JTextField textFieldNome;
	private JButton btnPesquisar;
	
	private TabelaClienteModel tabelaClienteModel;
	private TableRowSorter<TabelaClienteModel> sortTabelaCliente;
	
	private Integer totalData = 0;
	private Integer defaultPagina = 5;
	private Integer totalPagina = 1;
	private Integer numeroPagina = 1;
	private JButton btnRelatorio;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TabelaCliente frame = new TabelaCliente();
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
	public TabelaCliente() {
		initComponents();
		iniciaPaginacao();
	}
	private void initComponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 705, 563);
		
		scrollPane = new JScrollPane();
		
		panel = new JPanel();
		
		lblNewLabel = new JLabel("Página:");
		
		comboBox = new JComboBox();
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				iniciaPaginacao();
			}
		});
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"5", "10", "15", "20"}));
		
		btnIncluir = new JButton("Incluir");
		btnIncluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				incluirCliente();
				iniciaPaginacao();
			}
		});
		btnIncluir.setIcon(new ImageIcon(TabelaCliente.class.getResource("/com/projeto/estrutura/imagens/book_add.png")));
		
		btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alterarCliente();
				iniciaPaginacao();
			}
		});
		btnAlterar.setIcon(new ImageIcon(TabelaCliente.class.getResource("/com/projeto/estrutura/imagens/book_edit.png")));
		
		btnExcluir = new JButton("Excluir");
		btnExcluir.setIcon(new ImageIcon(TabelaCliente.class.getResource("/com/projeto/estrutura/imagens/book_delete.png")));
		
		btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnSair.setIcon(new ImageIcon(TabelaCliente.class.getResource("/com/projeto/estrutura/imagens/sair.png")));
		
		lblPagina = new JLabel("Página");
		
		lblInicio = new JLabel("10");
		
		lblNewLabel_1 = new JLabel("de");
		
		lblTotal = new JLabel("Total");
		
		lblNewLabel_2 = new JLabel("Total de Registros:");
		
		lblFinal = new JLabel("50");
		
		lblNewLabel_3 = new JLabel("Pesquisar:");
		
		textFieldNome = new JTextField();
		textFieldNome.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String filtro = textFieldNome.getText();
				
				filtraNomeCliente(filtro);
			}
		});
		textFieldNome.setColumns(10);
		
		btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnPesquisar.setIcon(new ImageIcon(TabelaCliente.class.getResource("/com/projeto/estrutura/imagens/search.png")));
		
		btnRelatorio = new JButton("Relatório");
		btnRelatorio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				imprimeRelatorio();
			}
		});
		btnRelatorio.setIcon(new ImageIcon(TabelaCliente.class.getResource("/com/projeto/estrutura/imagens/pdf.png")));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(66)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblNewLabel)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
									.addGap(49)
									.addComponent(panel, GroupLayout.PREFERRED_SIZE, 225, GroupLayout.PREFERRED_SIZE)
									.addGap(17)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(lblPagina)
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addComponent(lblInicio)
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addComponent(lblNewLabel_1)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(lblTotal))
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(lblNewLabel_2)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(lblFinal))))
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
									.addGroup(groupLayout.createSequentialGroup()
										.addComponent(lblNewLabel_3)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(textFieldNome)
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(btnPesquisar))
									.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 563, GroupLayout.PREFERRED_SIZE))))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(25)
							.addComponent(btnRelatorio)
							.addGap(18)
							.addComponent(btnIncluir)
							.addGap(18)
							.addComponent(btnAlterar)
							.addGap(18)
							.addComponent(btnExcluir)
							.addGap(18)
							.addComponent(btnSair)))
					.addContainerGap(60, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(32)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_3)
						.addComponent(textFieldNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnPesquisar))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 355, GroupLayout.PREFERRED_SIZE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(24)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel)
								.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblPagina)
								.addComponent(lblInicio)
								.addComponent(lblNewLabel_1)
								.addComponent(lblTotal))
							.addGap(9)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_2)
								.addComponent(lblFinal)))
						.addGroup(groupLayout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)))
					.addGap(17)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnIncluir)
						.addComponent(btnAlterar)
						.addComponent(btnExcluir)
						.addComponent(btnSair)
						.addComponent(btnRelatorio))
					.addContainerGap(12, Short.MAX_VALUE))
		);
		
		btnPrimeiro = new JButton("");
		btnPrimeiro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				numeroPagina = 1;
				iniciaPaginacao();
			}
		});
		btnPrimeiro.setToolTipText("Primeiro");
		btnPrimeiro.setIcon(new ImageIcon(TabelaCliente.class.getResource("/com/projeto/estrutura/imagens/go-first.png")));
		
		btnAnterior = new JButton("");
		btnAnterior.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(numeroPagina > 1) {
					numeroPagina = numeroPagina-1;
					iniciaPaginacao();
				}
			}
		});
		btnAnterior.setIcon(new ImageIcon(TabelaCliente.class.getResource("/com/projeto/estrutura/imagens/go-previous.png")));
		btnAnterior.setToolTipText("Anterior");
		
		btnProximo = new JButton("");
		btnProximo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(numeroPagina < totalPagina) {
					numeroPagina = numeroPagina+1;
					iniciaPaginacao();
				}
			}
		});
		btnProximo.setIcon(new ImageIcon(TabelaCliente.class.getResource("/com/projeto/estrutura/imagens/go-next.png")));
		
		btnUltimo = new JButton("");
		btnUltimo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				numeroPagina = totalPagina;
				iniciaPaginacao();
			}
		});
		btnUltimo.setIcon(new ImageIcon(TabelaCliente.class.getResource("/com/projeto/estrutura/imagens/go-last.png")));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addComponent(btnPrimeiro)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnAnterior)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnProximo)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnUltimo)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
					.addContainerGap(11, Short.MAX_VALUE)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(btnUltimo)
						.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
							.addComponent(btnProximo)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(btnAnterior)
								.addComponent(btnPrimeiro))))
					.addContainerGap())
		);
		panel.setLayout(gl_panel);
		
		tabelaCliente = new JTable();
		scrollPane.setViewportView(tabelaCliente);
		getContentPane().setLayout(groupLayout);
	}

	protected void filtraNomeCliente(String filtro) {
		RowFilter<TabelaClienteModel, Object> rowFilter = null;
		try {
			rowFilter = RowFilter.regexFilter(filtro);
		} catch(PatternSyntaxException e) {
			return;
		}
		sortTabelaCliente.setRowFilter(rowFilter);
	}
	
	protected void alterarCliente() {
		if(tabelaCliente.getSelectedRow() != -1 && tabelaCliente.getSelectedRow() < tabelaClienteModel.getRowCount()) {
			int linha = tabelaCliente.getSelectedRow();
			ClienteGUI cliente = new ClienteGUI(new JFrame(), true, tabelaCliente, tabelaClienteModel, linha, VariaveisProjeto.ALTERACAO);
			cliente.setLocationRelativeTo(null);
			cliente.setVisible(true);
		}
	}
	
	protected void incluirCliente() {
			ClienteGUI cliente = new ClienteGUI(new JFrame(), true, tabelaCliente, tabelaClienteModel, 0, VariaveisProjeto.INCLUSAO);
			cliente.setLocationRelativeTo(null);
			cliente.setResizable(false);
			cliente.setVisible(true);
	}
	
	protected void iniciaPaginacao() {
		
		totalData = buscaTotalRegistroCliente();
		
		defaultPagina = Integer.valueOf(comboBox.getSelectedItem().toString());
		
		Double totalPaginasExistentes = Math.ceil(totalData.doubleValue() / defaultPagina.doubleValue());
		
		totalPagina = totalPaginasExistentes.intValue();
		
		if(numeroPagina.equals(1)) {
			btnPrimeiro.setEnabled(false);
			btnProximo.setEnabled(false);
		} else {
			btnPrimeiro.setEnabled(true);
			btnProximo.setEnabled(true);
		}
		
		if(numeroPagina.equals(totalPagina)) {
			btnUltimo.setEnabled(false);
			btnProximo.setEnabled(false);
		} else {
			btnUltimo.setEnabled(true);
			btnProximo.setEnabled(true);
		}
		
		if(numeroPagina > totalPagina) {
			numeroPagina = 1;
		}
		
		tabelaClienteModel = new TabelaClienteModel();
		
		tabelaClienteModel.setListaCliente(carregaListaCliente(numeroPagina, defaultPagina));
		
		tabelaCliente.setModel(tabelaClienteModel);
		
		tabelaCliente.setFillsViewportHeight(true);
		
		tabelaCliente.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		tabelaClienteModel.fireTableDataChanged();
		
		sortTabelaCliente = new TableRowSorter<TabelaClienteModel>(tabelaClienteModel);
		
		tabelaCliente.setRowSorter(sortTabelaCliente);
		
		tabelaCliente.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		
		tabelaCliente.getColumnModel().getColumn(CODIGO).setWidth(11);
		tabelaCliente.getColumnModel().getColumn(NOME).setWidth(100);
		tabelaCliente.getColumnModel().getColumn(CPF).setWidth(11);
		tabelaCliente.getColumnModel().getColumn(EMAIL).setWidth(100);
		tabelaCliente.getColumnModel().getColumn(TELEFONE).setWidth(13);
		
		lblInicio.setText(String.valueOf(numeroPagina));
		lblTotal.setText(String.valueOf(totalPagina));
		lblFinal.setText(String.valueOf(totalData));
	}
	
	private List<Cliente> carregaListaCliente(Integer numeroPagina, Integer defaultPagina){
		
		ClienteService clienteService = new ClienteService();
		List<Cliente> listaCliente = new ArrayList<Cliente>();
		
		listaCliente = clienteService.listClientePaginacao((defaultPagina*(numeroPagina-1)), defaultPagina);
		
		return listaCliente;
	}
	
	private Integer buscaTotalRegistroCliente() {
		
		Integer totalRegistro = 0;
		
		ClienteService clienteService = new ClienteService();
		
		totalRegistro = clienteService.countTotalRegister();
		
		return totalRegistro;
	}
	
	public JTable getTable() {
		return tabelaCliente;
	}
	
	protected void imprimeRelatorio() {
		
		RelCliente relCliente = new RelCliente(new JFrame(), true);
		relCliente.setLocationRelativeTo(null);
		relCliente.setVisible(true);
	}
}
