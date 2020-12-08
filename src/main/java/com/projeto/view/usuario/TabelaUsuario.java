package com.projeto.view.usuario;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableRowSorter;

import com.projeto.model.models.Usuario;
import com.projeto.model.service.UsuarioService;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.ImageIcon;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class TabelaUsuario extends JInternalFrame {
	
	private static final long serialVersionUID = -7375163899521475032L;
	
	private static final int CODIGO = 0;
	private static final int NOME = 1;
	private static final int EMAIL = 2;
	
	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JTable tabelaUsuario;
	private JButton btnIncluir;
	private JButton btnAlterar;
	private JButton btnExcluir;
	private JButton btnSair;
	private JPanel panel;
	private JButton btnPrimeiro;
	private JButton btnAnterior;
	private JButton btnProximo;
	private JButton btnUltimo;
	private JLabel lblNewLabel;
	private JComboBox<String> comboBox;
	private JTextField textFieldPesquisar;
	private JLabel lblInicio;
	private JLabel lblTotal;
	private JLabel lblFinal;
	
	private TabelaUsuarioModel tabelaUsuarioModel;
	private TableRowSorter<TabelaUsuarioModel> sortTabelaUsuario;
	
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
					TabelaUsuario frame = new TabelaUsuario();
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
	public TabelaUsuario() {
		initComponents();
		iniciaPaginacao();
	}
	
	private void initComponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 632, 468);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		scrollPane = new JScrollPane();
		
		btnIncluir = new JButton("Incluir");
		btnIncluir.setMnemonic(KeyEvent.VK_I);
		btnIncluir.setIcon(new ImageIcon(TabelaUsuario.class.getResource("/com/projeto/estrutura/imagens/book_add.png")));
		
		btnAlterar = new JButton("Alterar");
		btnAlterar.setMnemonic(KeyEvent.VK_A);
		btnAlterar.setIcon(new ImageIcon(TabelaUsuario.class.getResource("/com/projeto/estrutura/imagens/book_edit.png")));
		
		btnExcluir = new JButton("Excluir");
		btnExcluir.setMnemonic(KeyEvent.VK_E);
		btnExcluir.setIcon(new ImageIcon(TabelaUsuario.class.getResource("/com/projeto/estrutura/imagens/book_delete.png")));
		
		btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnSair.setMnemonic(KeyEvent.VK_S);
		btnSair.setIcon(new ImageIcon(TabelaUsuario.class.getResource("/com/projeto/estrutura/imagens/book_go.png")));
		
		panel = new JPanel();
		
		lblNewLabel = new JLabel("Página:");
		
		comboBox = new JComboBox<String>();
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"5", "10", "15", "20"}));
		comboBox.setSelectedIndex(0);
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				iniciaPaginacao();
			}
		});
		
		
		JLabel lblPesquisar = new JLabel("Pesquisar:");
		
		textFieldPesquisar = new JTextField();
		textFieldPesquisar.setColumns(10);
		
		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.setMnemonic(KeyEvent.VK_P);
		btnPesquisar.setToolTipText("Pesquisar usuário cadastrado");
		btnPesquisar.setIcon(new ImageIcon(TabelaUsuario.class.getResource("/com/projeto/estrutura/imagens/search.png")));
		
		JLabel lblPagina = new JLabel("Página");
		
		lblInicio = new JLabel("10");
		
		lblTotal = new JLabel("Total");
		
		lblFinal = new JLabel("50");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(31)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnIncluir)
							.addGap(18)
							.addComponent(btnAlterar)
							.addGap(18)
							.addComponent(btnExcluir)
							.addGap(17)
							.addComponent(btnSair))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblNewLabel)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)
							.addGap(58)
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblPagina)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblInicio)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblTotal)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblFinal))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblPesquisar)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textFieldPesquisar, 375, 375, 375)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnPesquisar))
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 528, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(9)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPesquisar)
						.addComponent(textFieldPesquisar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnPesquisar))
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 263, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(14)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel)
								.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(11)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
									.addComponent(lblPagina)
									.addComponent(lblInicio)
									.addComponent(lblTotal)
									.addComponent(lblFinal))
								.addComponent(panel, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))))
					.addGap(20)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnSair)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnIncluir)
							.addComponent(btnAlterar)
							.addComponent(btnExcluir)))
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
		btnPrimeiro.setIcon(new ImageIcon(TabelaUsuario.class.getResource("/com/projeto/estrutura/imagens/go-first.png")));
		
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
		btnAnterior.setIcon(new ImageIcon(TabelaUsuario.class.getResource("/com/projeto/estrutura/imagens/go-previous.png")));
		
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
		btnProximo.setIcon(new ImageIcon(TabelaUsuario.class.getResource("/com/projeto/estrutura/imagens/go-next.png")));
		
		btnUltimo = new JButton("");
		btnUltimo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				numeroPagina = totalPagina;
				iniciaPaginacao();
			}
		});
		btnUltimo.setToolTipText("Último\r\n");
		btnUltimo.setIcon(new ImageIcon(TabelaUsuario.class.getResource("/com/projeto/estrutura/imagens/go-last.png")));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addComponent(btnPrimeiro)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnAnterior)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnProximo)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnUltimo)
					.addContainerGap(48, Short.MAX_VALUE))
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
		
		tabelaUsuario = new JTable();
		scrollPane.setViewportView(tabelaUsuario);
		contentPane.setLayout(gl_contentPane);
	}
	
	protected void iniciaPaginacao() {
		
		totalData = buscaTotalRegistroUsuario();
		
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
		
		tabelaUsuarioModel = new TabelaUsuarioModel();
		
		tabelaUsuarioModel.setListaUsuario(carregaListaUsuario(numeroPagina, defaultPagina));
		
		tabelaUsuario.setModel(tabelaUsuarioModel);
		
		tabelaUsuario.setFillsViewportHeight(true);
		
		tabelaUsuario.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		tabelaUsuarioModel.fireTableDataChanged();
		
		sortTabelaUsuario = new TableRowSorter<TabelaUsuarioModel>(tabelaUsuarioModel);
		
		tabelaUsuario.setRowSorter(sortTabelaUsuario);
		
		tabelaUsuario.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		
		tabelaUsuario.getColumnModel().getColumn(CODIGO).setWidth(11);
		tabelaUsuario.getColumnModel().getColumn(NOME).setWidth(100);
		tabelaUsuario.getColumnModel().getColumn(EMAIL).setWidth(100);
		
		lblInicio.setText(String.valueOf(numeroPagina));
		lblTotal.setText(String.valueOf(totalPagina));
		lblFinal.setText(String.valueOf(totalData));
	}

	private List<Usuario> carregaListaUsuario(Integer numeroPagina, Integer defaultPagina) {
		
		UsuarioService usuarioService = new UsuarioService();
		List<Usuario> listaUsuario = new ArrayList<Usuario>();
		
		listaUsuario = usuarioService.listUsuarioPaginacao((defaultPagina * (numeroPagina - 1)), defaultPagina);
		
		return listaUsuario;
	}

	private Integer buscaTotalRegistroUsuario() {
		
		Integer totalRegistro = 0;
		
		UsuarioService usuarioService = new UsuarioService();
		
		totalRegistro = usuarioService.countTotalRegister();
		
		return totalRegistro;
	}

	public JTable getTable() {
		return tabelaUsuario;
	}
}
