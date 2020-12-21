package com.projeto.view.pedido;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableRowSorter;

import com.projeto.estrutura.util.VariaveisProjeto;
import com.projeto.model.models.Pedido;
import com.projeto.model.service.PedidoService;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.ImageIcon;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.PatternSyntaxException;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.KeyAdapter;

public class TabelaPedido extends JInternalFrame {
	
	private static final long serialVersionUID = 248687372615496542L;
	
	private static final int CODIGO = 0;
	private static final int DATA = 1;
	private static final int PRECO_TOTAL = 2;
	
	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JTable tabelaPedido;
	private JButton btnIncluir;
	private JButton btnAlterar;
	private JButton btnExcluir;
	private JButton btnSair;
	private JPanel panel;
	private JButton btnPrimeiro;
	private JButton btnAnterior;
	private JButton btnProximo;
	private JButton btnUltimo;
	private JLabel lblNewLabel_1;
	private JComboBox<String> comboBox;
	private JLabel lblPesquisar;
	private JTextField textFieldData;
	private JButton btnPesquisar;
	private JLabel lblPagina;
	private JLabel lblInicio;
	private JLabel lblNewLabel;
	private JLabel lblTotal;
	private JLabel lblNewLabel_2;
	private JLabel lblFinal;

	private TabelaPedidoModel tabelaPedidoModel;
	private TableRowSorter<TabelaPedidoModel> sortTabelaPedido;
	
	private Integer totalData = 0;
	private Integer defaultPagina = 5;
	private Integer totalPagina = 1;
	private Integer numeroPagina = 1;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TabelaPedido frame = new TabelaPedido();
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
	public TabelaPedido() {
		initComponents();
		iniciaPaginacao();
	}
	private void initComponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 672, 492);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		scrollPane = new JScrollPane();
		
		btnIncluir = new JButton("Incluir");
		btnIncluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				incluirPedido();
				iniciaPaginacao();
			}
		});
		btnIncluir.setMnemonic(KeyEvent.VK_I);
		btnIncluir.setIcon(new ImageIcon(TabelaPedido.class.getResource("/com/projeto/estrutura/imagens/book_add.png")));
		
		btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alterarPedido();
				iniciaPaginacao();
			}
		});
		btnAlterar.setMnemonic(KeyEvent.VK_A);
		btnAlterar.setIcon(new ImageIcon(TabelaPedido.class.getResource("/com/projeto/estrutura/imagens/book_edit.png")));
		
		btnExcluir = new JButton("Excluir");
		btnExcluir.setMnemonic(KeyEvent.VK_E);
		btnExcluir.setIcon(new ImageIcon(TabelaPedido.class.getResource("/com/projeto/estrutura/imagens/book_delete.png")));
		
		btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnSair.setMnemonic(KeyEvent.VK_S);
		btnSair.setIcon(new ImageIcon(TabelaPedido.class.getResource("/com/projeto/estrutura/imagens/sair.png")));
		
		panel = new JPanel();
		
		lblNewLabel_1 = new JLabel("Página:");
		
		comboBox = new JComboBox<String>();
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				iniciaPaginacao();
			}
		});
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"5", "10", "15", "20"}));
		
		lblPesquisar = new JLabel("Pesquisar:");
		
		textFieldData = new JTextField();
		textFieldData.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String filtro = textFieldData.getText();
				
				filtraDataPedido(filtro);
			}
		});
		textFieldData.setColumns(10);
		
		btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.setIcon(new ImageIcon(TabelaPedido.class.getResource("/com/projeto/estrutura/imagens/search.png")));
		
		lblPagina = new JLabel("Página");
		
		lblInicio = new JLabel("10");
		
		lblNewLabel = new JLabel("de");
		
		lblTotal = new JLabel("Total");
		
		lblNewLabel_2 = new JLabel("Total de Registros:");
		
		lblFinal = new JLabel("50");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(138, Short.MAX_VALUE)
					.addComponent(btnIncluir)
					.addGap(18)
					.addComponent(btnAlterar)
					.addGap(18)
					.addComponent(btnExcluir)
					.addGap(18)
					.addComponent(btnSair)
					.addGap(134))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(53)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel_1)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
							.addGap(49)
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 255, GroupLayout.PREFERRED_SIZE)
							.addGap(46)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblPagina)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblInicio)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblNewLabel)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblTotal))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblNewLabel_2)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblFinal))))
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(lblPesquisar)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(textFieldData)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(btnPesquisar))
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 546, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(35, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(17)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPesquisar)
						.addComponent(textFieldData, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnPesquisar))
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 270, GroupLayout.PREFERRED_SIZE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(36)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_1)
								.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(17)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblPagina)
								.addComponent(lblInicio)
								.addComponent(lblNewLabel)
								.addComponent(lblTotal))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_2)
								.addComponent(lblFinal)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(19)
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnIncluir)
						.addComponent(btnAlterar)
						.addComponent(btnExcluir)
						.addComponent(btnSair))
					.addContainerGap())
		);
		
		btnPrimeiro = new JButton("");
		btnPrimeiro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				numeroPagina = 1;
				iniciaPaginacao();
			}
		});
		btnPrimeiro.setToolTipText("Primeiro");
		btnPrimeiro.setIcon(new ImageIcon(TabelaPedido.class.getResource("/com/projeto/estrutura/imagens/go-first.png")));
		
		btnAnterior = new JButton("");
		btnAnterior.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(numeroPagina > 1) {
					numeroPagina = numeroPagina-1;
					iniciaPaginacao();
				}
			}
		});
		btnAnterior.setToolTipText("Anterior");
		btnAnterior.setIcon(new ImageIcon(TabelaPedido.class.getResource("/com/projeto/estrutura/imagens/go-previous.png")));
		
		btnProximo = new JButton("");
		btnProximo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(numeroPagina < totalPagina) {
					numeroPagina = numeroPagina+1;
					iniciaPaginacao();
				}
			}
		});
		btnProximo.setIcon(new ImageIcon(TabelaPedido.class.getResource("/com/projeto/estrutura/imagens/go-next.png")));
		btnProximo.setToolTipText("Próximo");
		
		btnUltimo = new JButton("");
		btnUltimo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				numeroPagina = totalPagina;
				iniciaPaginacao();
			}
		});
		btnUltimo.setToolTipText("Último");
		btnUltimo.setIcon(new ImageIcon(TabelaPedido.class.getResource("/com/projeto/estrutura/imagens/go-last.png")));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addComponent(btnPrimeiro)
					.addGap(18)
					.addComponent(btnAnterior)
					.addGap(18)
					.addComponent(btnProximo)
					.addGap(18)
					.addComponent(btnUltimo)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnUltimo)
						.addComponent(btnProximo)
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
							.addComponent(btnPrimeiro)
							.addComponent(btnAnterior)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		
		tabelaPedido = new JTable();
		scrollPane.setViewportView(tabelaPedido);
		contentPane.setLayout(gl_contentPane);
	}
	
	protected void filtraDataPedido(String filtro) {
		RowFilter<TabelaPedidoModel, Object> rowFilter = null;
		try {
			rowFilter = RowFilter.regexFilter(filtro);
		} catch(PatternSyntaxException e) {
			return;
		}
		sortTabelaPedido.setRowFilter(rowFilter);
	}
	
	protected void alterarPedido() {
		if(tabelaPedido.getSelectedColumn() != -1 && tabelaPedido.getSelectedRow() < tabelaPedidoModel.getRowCount()) {
			int linha = tabelaPedido.getSelectedRow();
			PedidoGUI pedido = new PedidoGUI(new JFrame(), true, tabelaPedido, tabelaPedidoModel, linha, VariaveisProjeto.ALTERACAO);
			pedido.setLocationRelativeTo(null);
			pedido.setVisible(true);
		}
	}
	
	protected void incluirPedido() {
		PedidoGUI pedido = new PedidoGUI(new JFrame(), true, tabelaPedido, tabelaPedidoModel, 0, VariaveisProjeto.INCLUSAO);
		pedido.setLocationRelativeTo(null);
		pedido.setResizable(false);
		pedido.setVisible(true);
	}
	
	protected void iniciaPaginacao() {
		
		totalData = buscaTotalRegistroPedido();
		
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
		
		tabelaPedidoModel = new TabelaPedidoModel();
		
		tabelaPedidoModel.setListaPedido(carregaListaPedido(numeroPagina, defaultPagina));
		
		tabelaPedido.setModel(tabelaPedidoModel);
		
		tabelaPedido.setFillsViewportHeight(true);
		
		tabelaPedido.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		tabelaPedidoModel.fireTableDataChanged();
		
		sortTabelaPedido = new TableRowSorter<TabelaPedidoModel>(tabelaPedidoModel);
		
		tabelaPedido.setRowSorter(sortTabelaPedido);
		
		tabelaPedido.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		
		tabelaPedido.getColumnModel().getColumn(CODIGO).setWidth(11);
		tabelaPedido.getColumnModel().getColumn(DATA).setWidth(100);
		tabelaPedido.getColumnModel().getColumn(PRECO_TOTAL).setWidth(50);
		
		lblInicio.setText(String.valueOf(numeroPagina));
		lblTotal.setText(String.valueOf(totalPagina));
		lblFinal.setText(String.valueOf(totalData));
	}
	
	private List<Pedido> carregaListaPedido(Integer numeroPagina, Integer defaultPagina) {
		
		PedidoService pedidoService = new PedidoService();
		List<Pedido> listaPedido = new ArrayList<Pedido>();
		
		listaPedido = pedidoService.listPedidoPaginacao((defaultPagina * (numeroPagina - 1)), defaultPagina);
		
		return listaPedido;
	}
	
	private Integer buscaTotalRegistroPedido() {
		
		Integer totalRegistro = 0;
		
		PedidoService pedidoService = new PedidoService();
		
		totalRegistro = pedidoService.countTotalRegister();
		
		return totalRegistro;
	}
	
	public JTable getTable() {
		return tabelaPedido;
	}
}
