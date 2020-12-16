package com.projeto.view.gpu;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableRowSorter;

import com.projeto.estrutura.util.VariaveisProjeto;
import com.projeto.model.models.Gpu;
import com.projeto.model.service.GpuService;
import com.projeto.view.cpu.CpuGUI;
import com.projeto.view.cpu.TabelaCpuModel;

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

public class TabelaGpu extends JInternalFrame {

	private static final long serialVersionUID = 6415285702331704464L;

	private static final int CODIGO = 0;
	private static final int FABRICANTE = 1;
	private static final int MARCA = 2;
	private static final int MODELO = 3;
	private static final int PRECO = 4;
	private static final int QTDESTOQUE = 5;
	private static final int TIPOMEMORIA = 6;
	private static final int VRAM = 7;
	
	private JScrollPane scrollPane;
	private JTable tabelaGpu;
	private JPanel panel;
	private JButton btnPrimeiro;
	private JButton btnAnterior;
	private JButton btnProximo;
	private JButton btnUltimo;
	private JLabel lblNewLabel;
	private JComboBox comboBox;
	private JLabel lblPagina;
	private JLabel lblInicio;
	private JLabel lblNewLabel_1;
	private JLabel lblTotal;
	private JLabel lblNewLabel_2;
	private JLabel lblFinal;
	private JButton btnIncluir;
	private JButton btnAlterar;
	private JButton btnExcluir;
	private JButton btnSair;
	private JLabel lblNewLabel_3;
	private JTextField textFieldModelo;
	private JButton btnPesquisar;
	
	private TabelaGpuModel tabelaGpuModel;
	private TableRowSorter<TabelaGpuModel> sortTabelaGpu;
	
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
					TabelaGpu frame = new TabelaGpu();
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
	public TabelaGpu() {
		initComponents();
		iniciaPaginacao();
	}
	private void initComponents() {
		setBounds(100, 100, 733, 535);
		
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
		
		lblPagina = new JLabel("Página");
		
		lblInicio = new JLabel("5");
		
		lblNewLabel_1 = new JLabel("de");
		
		lblTotal = new JLabel("Total");
		
		lblNewLabel_2 = new JLabel("Total de Registros:");
		
		lblFinal = new JLabel("50");
		
		btnIncluir = new JButton("Incluir");
		btnIncluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				incluirGpu();
				iniciaPaginacao();
			}
		});
		btnIncluir.setMnemonic(KeyEvent.VK_I);
		btnIncluir.setIcon(new ImageIcon(TabelaGpu.class.getResource("/com/projeto/estrutura/imagens/book_add.png")));
		
		btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alterarGpu();
				iniciaPaginacao();
			}
		});
		btnAlterar.setMnemonic(KeyEvent.VK_A);
		btnAlterar.setIcon(new ImageIcon(TabelaGpu.class.getResource("/com/projeto/estrutura/imagens/book_edit.png")));
		
		btnExcluir = new JButton("Excluir");
		btnExcluir.setMnemonic(KeyEvent.VK_E);
		btnExcluir.setIcon(new ImageIcon(TabelaGpu.class.getResource("/com/projeto/estrutura/imagens/book_delete.png")));
		
		btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnSair.setIcon(new ImageIcon(TabelaGpu.class.getResource("/com/projeto/estrutura/imagens/sair.png")));
		
		lblNewLabel_3 = new JLabel("Pesquisar:");
		
		textFieldModelo = new JTextField();
		textFieldModelo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String filtro = textFieldModelo.getText();
				
				filtraModeloGpu(filtro);
			}
		});
		textFieldModelo.setColumns(10);
		
		btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.setIcon(new ImageIcon(TabelaGpu.class.getResource("/com/projeto/estrutura/imagens/search.png")));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(62)
							.addComponent(lblNewLabel)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
							.addGap(51)
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(39)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblPagina)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblInicio)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(lblNewLabel_1)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblTotal))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblNewLabel_2)
									.addGap(6)
									.addComponent(lblFinal))))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(148)
							.addComponent(btnIncluir)
							.addGap(18)
							.addComponent(btnAlterar)
							.addGap(18)
							.addComponent(btnExcluir)
							.addGap(18)
							.addComponent(btnSair))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(74)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblNewLabel_3)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(textFieldModelo)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnPesquisar))
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 577, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(66, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(33)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_3)
						.addComponent(textFieldModelo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnPesquisar))
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 316, GroupLayout.PREFERRED_SIZE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(29)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel)
								.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblPagina)
										.addComponent(lblInicio)
										.addComponent(lblNewLabel_1)
										.addComponent(lblTotal))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblNewLabel_2)
										.addComponent(lblFinal)))
								.addComponent(panel, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))))
					.addPreferredGap(ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
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
		btnPrimeiro.setIcon(new ImageIcon(TabelaGpu.class.getResource("/com/projeto/estrutura/imagens/go-first.png")));
		
		btnAnterior = new JButton("");
		btnAnterior.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(numeroPagina > 1) {
					numeroPagina = numeroPagina-1;
					iniciaPaginacao();
				}
			}
		});
		btnAnterior.setIcon(new ImageIcon(TabelaGpu.class.getResource("/com/projeto/estrutura/imagens/go-previous.png")));
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
		btnProximo.setIcon(new ImageIcon(TabelaGpu.class.getResource("/com/projeto/estrutura/imagens/go-next.png")));
		btnProximo.setToolTipText("Próximo");
		
		btnUltimo = new JButton("");
		btnUltimo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				numeroPagina = totalPagina;
				iniciaPaginacao();
			}
		});
		btnUltimo.setIcon(new ImageIcon(TabelaGpu.class.getResource("/com/projeto/estrutura/imagens/go-last.png")));
		btnUltimo.setToolTipText("Último");
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addComponent(btnPrimeiro)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnAnterior)
					.addPreferredGap(ComponentPlacement.UNRELATED)
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
					.addContainerGap(17, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		
		tabelaGpu = new JTable();
		scrollPane.setViewportView(tabelaGpu);
		getContentPane().setLayout(groupLayout);
	}
	
	protected void filtraModeloGpu(String filtro) {
		RowFilter<TabelaGpuModel, Object> rowFilter = null;
		try {
			rowFilter = RowFilter.regexFilter(filtro);
		} catch(PatternSyntaxException e) {
			return;
		}
		sortTabelaGpu.setRowFilter(rowFilter);
	}

	protected void alterarGpu() {
		if(tabelaGpu.getSelectedRow() != -1 && tabelaGpu.getSelectedRow() < tabelaGpuModel.getRowCount()) {
			int linha = tabelaGpu.getSelectedRow();
			GpuGUI gpu = new GpuGUI(new JFrame(), true, tabelaGpu, tabelaGpuModel, linha, VariaveisProjeto.ALTERACAO);
			gpu.setLocationRelativeTo(null);
			gpu.setVisible(true);
		}
	}
	
	protected void incluirGpu() {
		GpuGUI gpu = new GpuGUI(new JFrame(), true, tabelaGpu, tabelaGpuModel, 0, VariaveisProjeto.INCLUSAO);
		gpu.setLocationRelativeTo(null);
		gpu.setResizable(false);
		gpu.setVisible(true);
	}
	
	protected void iniciaPaginacao() {
		totalData = buscarTotalRegistroGpu();
		
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
		
		tabelaGpuModel = new TabelaGpuModel();
		
		tabelaGpuModel.setListaGpu(carregaListaGpu(numeroPagina, defaultPagina));
		
		tabelaGpu.setModel(tabelaGpuModel);
		
		tabelaGpu.setFillsViewportHeight(true);
		
		tabelaGpu.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		tabelaGpuModel.fireTableDataChanged();
		
		sortTabelaGpu = new TableRowSorter<TabelaGpuModel>(tabelaGpuModel);
		
		tabelaGpu.setRowSorter(sortTabelaGpu);
		
		tabelaGpu.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		
		tabelaGpu.getColumnModel().getColumn(CODIGO).setWidth(11);
		tabelaGpu.getColumnModel().getColumn(FABRICANTE).setWidth(100);
		tabelaGpu.getColumnModel().getColumn(MARCA).setWidth(20);
		tabelaGpu.getColumnModel().getColumn(MODELO).setWidth(50);
		tabelaGpu.getColumnModel().getColumn(PRECO).setWidth(50);
		tabelaGpu.getColumnModel().getColumn(QTDESTOQUE).setWidth(20);
		tabelaGpu.getColumnModel().getColumn(TIPOMEMORIA).setWidth(20);
		tabelaGpu.getColumnModel().getColumn(VRAM).setWidth(20);
		
		lblInicio.setText(String.valueOf(numeroPagina));
		lblTotal.setText(String.valueOf(totalPagina));
		lblFinal.setText(String.valueOf(totalData));
	}

	private List<Gpu> carregaListaGpu(Integer numeroPagina, Integer defaultPagina) {
		
		GpuService gpuService = new GpuService();
		List<Gpu> listaGpu = new ArrayList<Gpu>();
		
		listaGpu = gpuService.listGpuPaginacao(defaultPagina*(numeroPagina-1), defaultPagina);
		
		return listaGpu;
	}
	
	private Integer buscarTotalRegistroGpu() {
		
		Integer totalRegistro = 0;
		
		GpuService gpuService = new GpuService();
		
		totalRegistro = gpuService.countTotalRegister();
		
		return totalRegistro;
	}
}
