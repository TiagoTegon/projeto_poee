package com.projeto.view.cpu;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableRowSorter;

import com.projeto.estrutura.util.VariaveisProjeto;
import com.projeto.model.models.Cpu;
import com.projeto.model.service.CpuService;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.PatternSyntaxException;

import javax.swing.JTextField;
import javax.swing.RowFilter;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;

public class TabelaCpu extends JInternalFrame {

	private static final long serialVersionUID = -6374554527128409112L;

	private static final int CODIGO = 0;
	private static final int CACHE = 1;
	private static final int MARCA = 2;
	private static final int MODELO = 3;
	private static final int NUCLEOS = 4;
	private static final int PRECO = 5;
	private static final int QTDESTOQUE = 6;
	private static final int THREADS = 7;
	private static final int VELOCIDADE = 8;
	
	private JScrollPane scrollPane;
	private JTable tabelaCpu;
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
	private JTextField textFieldModelo;
	private JButton btnPesquisar;
	
	private TabelaCpuModel tabelaCpuModel;
	private TableRowSorter<TabelaCpuModel> sortTabelaCpu;
	
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
					TabelaCpu frame = new TabelaCpu();
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
	public TabelaCpu() {
		initComponents();
		iniciaPaginacao();
	}
	private void initComponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 791, 515);
		
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
				incluirCpu();
				iniciaPaginacao();
			}
		});
		btnIncluir.setMnemonic(KeyEvent.VK_I);
		btnIncluir.setIcon(new ImageIcon(TabelaCpu.class.getResource("/com/projeto/estrutura/imagens/book_add.png")));
		
		btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alterarCpu();
				iniciaPaginacao();
			}
		});
		btnAlterar.setMnemonic(KeyEvent.VK_A);
		btnAlterar.setIcon(new ImageIcon(TabelaCpu.class.getResource("/com/projeto/estrutura/imagens/book_edit.png")));
		
		btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnExcluir.setMnemonic(KeyEvent.VK_E);
		btnExcluir.setIcon(new ImageIcon(TabelaCpu.class.getResource("/com/projeto/estrutura/imagens/book_delete.png")));
		
		btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnSair.setMnemonic(KeyEvent.VK_S);
		btnSair.setIcon(new ImageIcon(TabelaCpu.class.getResource("/com/projeto/estrutura/imagens/sair.png")));
		
		lblPagina = new JLabel("Página");
		
		lblInicio = new JLabel("10");
		
		lblNewLabel_1 = new JLabel("de");
		
		lblTotal = new JLabel("Total");
		
		lblNewLabel_2 = new JLabel("Total de Registros:");
		
		lblFinal = new JLabel("50");
		
		lblNewLabel_3 = new JLabel("Pesquisar:");
		
		textFieldModelo = new JTextField();
		textFieldModelo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String filtro = textFieldModelo.getText();
				
				filtraModeloCpu(filtro);
			}
		});
		textFieldModelo.setColumns(10);
		
		btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnPesquisar.setIcon(new ImageIcon(TabelaCpu.class.getResource("/com/projeto/estrutura/imagens/search.png")));
		
		btnRelatorio = new JButton("Relatório");
		btnRelatorio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				imprimeRelatorio();
			}
		});
		btnRelatorio.setIcon(new ImageIcon(TabelaCpu.class.getResource("/com/projeto/estrutura/imagens/pdf.png")));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(79)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblNewLabel)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
									.addGap(56)
									.addComponent(panel, GroupLayout.PREFERRED_SIZE, 223, GroupLayout.PREFERRED_SIZE)
									.addGap(42)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(lblNewLabel_2)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(lblFinal))
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(lblPagina)
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addComponent(lblInicio)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(lblNewLabel_1)
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addComponent(lblTotal))))
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
									.addGroup(groupLayout.createSequentialGroup()
										.addComponent(lblNewLabel_3)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(textFieldModelo)
										.addGap(18)
										.addComponent(btnPesquisar))
									.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 609, GroupLayout.PREFERRED_SIZE))))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(52)
							.addComponent(btnRelatorio)
							.addGap(18)
							.addComponent(btnIncluir)
							.addGap(18)
							.addComponent(btnAlterar)
							.addGap(18)
							.addComponent(btnExcluir)
							.addGap(18)
							.addComponent(btnSair)))
					.addContainerGap(87, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(27)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_3)
						.addComponent(textFieldModelo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnPesquisar))
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 312, GroupLayout.PREFERRED_SIZE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(20)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
									.addComponent(lblNewLabel)
									.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
									.addComponent(lblPagina)
									.addComponent(lblInicio)
									.addComponent(lblNewLabel_1)
									.addComponent(lblTotal)))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_2)
								.addComponent(lblFinal)))
						.addGroup(groupLayout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnIncluir)
						.addComponent(btnAlterar)
						.addComponent(btnExcluir)
						.addComponent(btnSair)
						.addComponent(btnRelatorio))
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
		btnPrimeiro.setIcon(new ImageIcon(TabelaCpu.class.getResource("/com/projeto/estrutura/imagens/go-first.png")));
		
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
		btnAnterior.setIcon(new ImageIcon(TabelaCpu.class.getResource("/com/projeto/estrutura/imagens/go-previous.png")));
		
		btnProximo = new JButton("");
		btnProximo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(numeroPagina < totalPagina) {
					numeroPagina = numeroPagina+1;
					iniciaPaginacao();
				}
			}
		});
		btnProximo.setToolTipText("Próximo");
		btnProximo.setIcon(new ImageIcon(TabelaCpu.class.getResource("/com/projeto/estrutura/imagens/go-next.png")));
		
		btnUltimo = new JButton("");
		btnUltimo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				numeroPagina = totalPagina;
				iniciaPaginacao();
			}
		});
		btnUltimo.setToolTipText("Último");
		btnUltimo.setIcon(new ImageIcon(TabelaCpu.class.getResource("/com/projeto/estrutura/imagens/go-last.png")));
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
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(btnPrimeiro)
						.addComponent(btnAnterior)
						.addComponent(btnProximo)
						.addComponent(btnUltimo))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		
		tabelaCpu = new JTable();
		scrollPane.setViewportView(tabelaCpu);
		getContentPane().setLayout(groupLayout);
	}
	
	protected void filtraModeloCpu(String filtro) {
		RowFilter<TabelaCpuModel, Object> rowFilter = null;
		try {
			rowFilter = RowFilter.regexFilter(filtro);
		} catch(PatternSyntaxException e) {
			return;
		}
		sortTabelaCpu.setRowFilter(rowFilter);
	}
	
	protected void alterarCpu() {
		if(tabelaCpu.getSelectedRow() != -1 && tabelaCpu.getSelectedRow() < tabelaCpuModel.getRowCount()) {
			int linha = tabelaCpu.getSelectedRow();
			CpuGUI cpu = new CpuGUI(new JFrame(), true, tabelaCpu, tabelaCpuModel, linha, VariaveisProjeto.ALTERACAO);
			cpu.setLocationRelativeTo(null);
			cpu.setVisible(true);
		}
	}
	
	protected void incluirCpu() {
		CpuGUI cpu = new CpuGUI(new JFrame(), true, tabelaCpu, tabelaCpuModel, 0, VariaveisProjeto.INCLUSAO);
		cpu.setLocationRelativeTo(null);
		cpu.setResizable(false);
		cpu.setVisible(true);
	}
	
	protected void iniciaPaginacao() {
		totalData = buscarTotalRegistroCpu();
		
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
		
		tabelaCpuModel = new TabelaCpuModel();
		
		tabelaCpuModel.setListaCpu(carregaListaCpu(numeroPagina, defaultPagina));
		
		tabelaCpu.setModel(tabelaCpuModel);
		
		tabelaCpu.setFillsViewportHeight(true);
		
		tabelaCpu.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		tabelaCpuModel.fireTableDataChanged();
		
		sortTabelaCpu = new TableRowSorter<TabelaCpuModel>(tabelaCpuModel);
		
		tabelaCpu.setRowSorter(sortTabelaCpu);
		
		tabelaCpu.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		
		tabelaCpu.getColumnModel().getColumn(CODIGO).setWidth(11);
		tabelaCpu.getColumnModel().getColumn(CACHE).setWidth(20);
		tabelaCpu.getColumnModel().getColumn(MARCA).setWidth(20);
		tabelaCpu.getColumnModel().getColumn(MODELO).setWidth(50);
		tabelaCpu.getColumnModel().getColumn(NUCLEOS).setWidth(20);
		tabelaCpu.getColumnModel().getColumn(PRECO).setWidth(50);
		tabelaCpu.getColumnModel().getColumn(QTDESTOQUE).setWidth(20);
		tabelaCpu.getColumnModel().getColumn(THREADS).setWidth(20);
		tabelaCpu.getColumnModel().getColumn(VELOCIDADE).setWidth(20);
		
		lblInicio.setText(String.valueOf(numeroPagina));
		lblTotal.setText(String.valueOf(totalPagina));
		lblFinal.setText(String.valueOf(totalData));
	}

	private List<Cpu> carregaListaCpu(Integer numeroPagina, Integer defaultPagina) {
		
		CpuService cpuService = new CpuService();
		List<Cpu> listaCpu = new ArrayList<Cpu>();
		
		listaCpu = cpuService.listCpuPaginacao((defaultPagina*(numeroPagina-1)), defaultPagina);
		
		return listaCpu;
	}
	
	private Integer buscarTotalRegistroCpu() {

		Integer totalRegistro = 0;
		
		CpuService cpuService = new CpuService();
		
		totalRegistro = cpuService.countTotalRegister();
		
		return totalRegistro;
	}
	
	private void imprimeRelatorio() {
		
		RelCpu relCpu = new RelCpu(new JFrame(), true);
		relCpu.setLocationRelativeTo(null);
		relCpu.setVisible(true);
		
	}
}
