package com.projeto.view.cpu;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import com.projeto.estrutura.util.VariaveisProjeto;
import com.projeto.model.models.Cpu;
import com.projeto.model.models.Pedido;
import com.projeto.model.service.CpuService;
import com.projeto.model.service.PedidoService;
import com.projeto.view.pedido.PedidoGUI;

import java.awt.Toolkit;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ImageIcon;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CpuGUI extends JDialog {

	private static final long serialVersionUID = -1001988248171185209L;
	
	private JPanel contentPane;
	private JLabel lblCodigo;
	private JLabel lblCache;
	private JLabel lblMarca;
	private JLabel lblModelo;
	private JLabel lblNucleos;
	private JLabel lblPreco;
	private JLabel lblQtdEstoque;
	private JLabel lblThreads;
	private JLabel lblVelocidade;
	private JTextField textFieldCodigo;
	private JTextField textFieldCache;
	private JTextField textFieldMarca;
	private JTextField textFieldModelo;
	private JTextField textFieldNucleos;
	private JTextField textFieldPreco;
	private JTextField textFieldQtdEstoque;
	private JTextField textFieldThreads;
	private JTextField textFieldVelocidade;
	private JButton btnIncluir;
	private JButton btnAlterar;
	private JButton btnExcluir;
	private JButton btnSair;
	private JLabel checkCache;
	private JLabel checkMarca;
	private JLabel checkModelo;
	private JLabel checkNucleos;
	private JLabel checkPreco;
	private JLabel checkQtdEstoque;
	private JLabel checkThreads;
	private JLabel checkVelocidade;

	private boolean status = true;
	
	private JTable tabelaCpu;
	private TabelaCpuModel tabelaCpuModel;
	private int linha = 0;
	private int acao = 0;
	
	/**
	 * Launch the application.
	 */
	/*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CpuGUI frame = new CpuGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	public CpuGUI(JFrame frame, boolean modal, JTable tabelaCpu, TabelaCpuModel tabelaCpuModel, int linha, int acao) {
		
		super(frame, modal);
		
		initComponents();
		
		this.tabelaCpu = tabelaCpu;
		this.tabelaCpuModel = tabelaCpuModel;
		this.linha = linha;
		this.acao = acao;
		
		limpaTextoCampo();
		
		desabilitaCheck();
		
		configuraAcaoCpu();
	}
	
	private void configuraAcaoCpu() {
		if(this.acao == VariaveisProjeto.INCLUSAO) {
			btnIncluir.setVisible(true);
			btnAlterar.setVisible(false);
			btnExcluir.setVisible(false);
		}
		if(this.acao == VariaveisProjeto.ALTERACAO) {
			btnAlterar.setVisible(true);
			btnExcluir.setVisible(false);
			btnIncluir.setVisible(false);
			buscarCpu();
		}
		if(this.acao == VariaveisProjeto.EXCLUSAO) {
			btnExcluir.setVisible(true);
			btnAlterar.setVisible(false);
			btnIncluir.setVisible(false);
			buscarCpu();
		}
	}
	
	private void initComponents() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(CpuGUI.class.getResource("/com/projeto/estrutura/imagens/book.png")));
		setTitle("Cadastro de CPU");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 623, 521);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		lblCodigo = new JLabel("Código:");
		
		lblCache = new JLabel("Cache:");
		
		lblMarca = new JLabel("Marca:");
		
		lblModelo = new JLabel("Modelo:");
		
		lblNucleos = new JLabel("Núcleos:");
		
		lblPreco = new JLabel("Preço:");
		
		lblQtdEstoque = new JLabel("Estoque:");
		
		lblThreads = new JLabel("Threads:");
		
		lblVelocidade = new JLabel("Velocidade:");
		
		textFieldCodigo = new JTextField();
		textFieldCodigo.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				//buscarCpu();
			}
		});
		textFieldCodigo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					//buscarCpu();
					textFieldCache.requestFocus();
				}
			}
		});
		textFieldCodigo.setColumns(10);
		
		textFieldCache = new JTextField();
		textFieldCache.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if(verificaDigitacaoCache()) {
					textFieldCache.requestFocus();
				} else {
					digitacaoCacheValida();
				}
			}
		});
		textFieldCache.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					if(verificaDigitacaoCache()) {
						textFieldCache.requestFocus();
					} else {
						digitacaoCacheValida();
					}
				}
			}
		});
		textFieldCache.setColumns(10);
		
		textFieldMarca = new JTextField();
		textFieldMarca.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if(verificaDigitacaoMarca()) {
					textFieldMarca.requestFocus();
				} else {
					digitacaoMarcaValida();
				}
			}
		});
		textFieldMarca.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					if(verificaDigitacaoMarca()) {
						textFieldMarca.requestFocus();
					} else {
						digitacaoMarcaValida();
					}
				}
			}
		});
		textFieldMarca.setColumns(10);
		
		textFieldModelo = new JTextField();
		textFieldModelo.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if(verificaDigitacaoModelo()) {
					textFieldModelo.requestFocus();
				} else {
					digitacaoModeloValida();
				}
			}
		});
		textFieldModelo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					if(verificaDigitacaoModelo()) {
						textFieldModelo.requestFocus();
					} else {
						digitacaoModeloValida();
					}
				}
			}
		});
		textFieldModelo.setColumns(10);
		
		textFieldNucleos = new JTextField();
		textFieldNucleos.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if(verificaDigitacaoNucleos()) {
					textFieldNucleos.requestFocus();
				} else {
					digitacaoNucleosValida();
				}
			}
		});
		textFieldNucleos.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					if(verificaDigitacaoNucleos()) {
						textFieldNucleos.requestFocus();
					} else {
						digitacaoNucleosValida();
					}
				}
			}
		});
		textFieldNucleos.setColumns(10);
		
		textFieldPreco = new JTextField();
		textFieldPreco.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if(verificaDigitacaoPreco()) {
					textFieldPreco.requestFocus();
				} else {
					digitacaoPrecoValida();
				}
			}
		});
		textFieldPreco.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					if(verificaDigitacaoPreco()) {
						textFieldPreco.requestFocus();
					} else {
						digitacaoPrecoValida();
					}
				}
			}
		});
		textFieldPreco.setColumns(10);
		
		textFieldQtdEstoque = new JTextField();
		textFieldQtdEstoque.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if(verificaDigitacaoQtdEstoque()) {
					textFieldQtdEstoque.requestFocus();
				} else {
					digitacaoQtdEstoqueValida();
				}
			}
		});
		textFieldQtdEstoque.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					if(verificaDigitacaoQtdEstoque()) {
						textFieldQtdEstoque.requestFocus();
					} else {
						digitacaoQtdEstoqueValida();
					}
				}
			}
		});
		textFieldQtdEstoque.setColumns(10);
		
		textFieldThreads = new JTextField();
		textFieldThreads.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if(verificaDigitacaoThreads()) {
					textFieldThreads.requestFocus();
				} else {
					digitacaoThreadsValida();
				}
			}
		});
		textFieldThreads.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					if(verificaDigitacaoThreads()) {
						textFieldThreads.requestFocus();
					} else {
						digitacaoThreadsValida();
					}
				}
			}
		});
		textFieldThreads.setColumns(10);
		
		textFieldVelocidade = new JTextField();
		textFieldVelocidade.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if(verificaDigitacaoVelocidade()) {
					textFieldVelocidade.requestFocus();
				} else {
					digitacaoVelocidadeValida();
				}
			}
		});
		textFieldVelocidade.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					if(verificaDigitacaoVelocidade()) {
						textFieldVelocidade.requestFocus();
					} else {
						digitacaoVelocidadeValida();
					}
				}
			}
		});
		textFieldVelocidade.setColumns(10);
		
		btnIncluir = new JButton("Incluir");
		btnIncluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				incluirCpu();
			}
		});
		btnIncluir.setIcon(new ImageIcon(CpuGUI.class.getResource("/com/projeto/estrutura/imagens/application_add.png")));
		
		btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alterarCpu();
			}
		});
		btnAlterar.setIcon(new ImageIcon(CpuGUI.class.getResource("/com/projeto/estrutura/imagens/application_edit.png")));
		
		btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluirCpu();
			}
		});
		btnExcluir.setIcon(new ImageIcon(CpuGUI.class.getResource("/com/projeto/estrutura/imagens/application_delete.png")));
		
		btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fecharCpu();
			}
		});
		btnSair.setIcon(new ImageIcon(CpuGUI.class.getResource("/com/projeto/estrutura/imagens/sair.png")));
		
		checkCache = new JLabel("");
		checkCache.setIcon(new ImageIcon(CpuGUI.class.getResource("/com/projeto/estrutura/imagens/ok.png")));
		
		checkMarca = new JLabel("");
		checkMarca.setIcon(new ImageIcon(CpuGUI.class.getResource("/com/projeto/estrutura/imagens/ok.png")));
		
		checkModelo = new JLabel("");
		checkModelo.setIcon(new ImageIcon(CpuGUI.class.getResource("/com/projeto/estrutura/imagens/ok.png")));
		
		checkNucleos = new JLabel("");
		checkNucleos.setIcon(new ImageIcon(CpuGUI.class.getResource("/com/projeto/estrutura/imagens/ok.png")));
		
		checkPreco = new JLabel("");
		checkPreco.setIcon(new ImageIcon(CpuGUI.class.getResource("/com/projeto/estrutura/imagens/ok.png")));
		
		checkQtdEstoque = new JLabel("");
		checkQtdEstoque.setIcon(new ImageIcon(CpuGUI.class.getResource("/com/projeto/estrutura/imagens/ok.png")));
		
		checkThreads = new JLabel("");
		checkThreads.setIcon(new ImageIcon(CpuGUI.class.getResource("/com/projeto/estrutura/imagens/ok.png")));
		
		checkVelocidade = new JLabel("");
		checkVelocidade.setIcon(new ImageIcon(CpuGUI.class.getResource("/com/projeto/estrutura/imagens/ok.png")));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(99)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblVelocidade)
								.addComponent(lblThreads)
								.addComponent(lblQtdEstoque)
								.addComponent(lblPreco)
								.addComponent(lblNucleos)
								.addComponent(lblModelo)
								.addComponent(lblMarca)
								.addComponent(lblCache)
								.addComponent(lblCodigo))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
									.addComponent(textFieldCodigo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(textFieldCache, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addGap(18)
										.addComponent(checkCache))
									.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
										.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
											.addComponent(textFieldModelo, GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE)
											.addComponent(textFieldMarca, GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE))
										.addPreferredGap(ComponentPlacement.RELATED)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
											.addComponent(checkMarca)
											.addComponent(checkModelo))))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(textFieldNucleos, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(checkNucleos))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(textFieldPreco, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(checkPreco))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(textFieldQtdEstoque, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(checkQtdEstoque))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(textFieldThreads, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(checkThreads))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(textFieldVelocidade, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(checkVelocidade))))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(87)
							.addComponent(btnIncluir)
							.addGap(18)
							.addComponent(btnAlterar)
							.addGap(18)
							.addComponent(btnExcluir)
							.addGap(18)
							.addComponent(btnSair)))
					.addContainerGap(136, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(75)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCodigo)
						.addComponent(textFieldCodigo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCache)
						.addComponent(textFieldCache, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(checkCache))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblMarca)
							.addComponent(textFieldMarca, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(checkMarca))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblModelo)
						.addComponent(textFieldModelo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(checkModelo))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNucleos)
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblPreco)
								.addComponent(textFieldPreco, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(checkPreco))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblQtdEstoque)
								.addComponent(textFieldQtdEstoque, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(checkQtdEstoque))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblThreads)
								.addComponent(textFieldThreads, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(checkThreads))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblVelocidade)
								.addComponent(textFieldVelocidade, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(checkVelocidade)))
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(textFieldNucleos, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(checkNucleos)))
					.addPreferredGap(ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnIncluir)
						.addComponent(btnAlterar)
						.addComponent(btnExcluir)
						.addComponent(btnSair))
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
	}
	
	private void digitacaoCacheValida() {
		status = true;
		mudaStatusCheckCache();
		checkCache.setVisible(true);
		textFieldMarca.requestFocus();
	}
	
	private boolean verificaDigitacaoCache() {
		
		boolean toReturn = true;
		
		if(VariaveisProjeto.digitacaoCampo(textFieldCache.getText())) {
			status = false;
			mudaStatusCheckCache();
			return toReturn;
		}
		
		toReturn = false;
		return toReturn;
	}

	private void mudaStatusCheckCache() {
		checkCache.setVisible(true);
		if(status == false){
			checkCache.setIcon(new ImageIcon(CpuGUI.class.getResource("/com/projeto/estrutura/imagens/iconFechar.png")));
		} else {
			checkCache.setIcon(new ImageIcon(CpuGUI.class.getResource("/com/projeto/estrutura/imagens/ok.png")));
		}
	}
	
	private void digitacaoMarcaValida() {
		status = true;
		mudaStatusCheckMarca();
		checkMarca.setVisible(true);
		textFieldModelo.requestFocus();
	}
	
	private boolean verificaDigitacaoMarca() {
		
		boolean toReturn = true;
		
		if(VariaveisProjeto.digitacaoCampo(textFieldMarca.getText())) {
			status = false;
			mudaStatusCheckMarca();
			return toReturn;
		}
		
		toReturn = false;
		return toReturn;
	}

	private void mudaStatusCheckMarca() {
		checkMarca.setVisible(true);
		if(status == false){
			checkMarca.setIcon(new ImageIcon(CpuGUI.class.getResource("/com/projeto/estrutura/imagens/iconFechar.png")));
		} else {
			checkMarca.setIcon(new ImageIcon(CpuGUI.class.getResource("/com/projeto/estrutura/imagens/ok.png")));
		}
	}
	
	private void digitacaoModeloValida() {
		status = true;
		mudaStatusCheckModelo();
		checkModelo.setVisible(true);
		textFieldNucleos.requestFocus();
	}
	
	private boolean verificaDigitacaoModelo() {
		
		boolean toReturn = true;
		
		if(VariaveisProjeto.digitacaoCampo(textFieldModelo.getText())) {
			status = false;
			mudaStatusCheckModelo();
			return toReturn;
		}
		
		toReturn = false;
		return toReturn;
	}

	private void mudaStatusCheckModelo() {
		checkModelo.setVisible(true);
		if(status == false){
			checkModelo.setIcon(new ImageIcon(CpuGUI.class.getResource("/com/projeto/estrutura/imagens/iconFechar.png")));
		} else {
			checkModelo.setIcon(new ImageIcon(CpuGUI.class.getResource("/com/projeto/estrutura/imagens/ok.png")));
		}
	}
	
	private void digitacaoNucleosValida() {
		status = true;
		mudaStatusCheckNucleos();
		checkNucleos.setVisible(true);
		textFieldPreco.requestFocus();
	}
	
	private boolean verificaDigitacaoNucleos() {
		
		boolean toReturn = true;
		
		if(VariaveisProjeto.digitacaoCampo(textFieldNucleos.getText())) {
			status = false;
			mudaStatusCheckNucleos();
			return toReturn;
		}
		
		toReturn = false;
		return toReturn;
	}

	private void mudaStatusCheckNucleos() {
		checkNucleos.setVisible(true);
		if(status == false){
			checkNucleos.setIcon(new ImageIcon(CpuGUI.class.getResource("/com/projeto/estrutura/imagens/iconFechar.png")));
		} else {
			checkNucleos.setIcon(new ImageIcon(CpuGUI.class.getResource("/com/projeto/estrutura/imagens/ok.png")));
		}
	}
	
	private void digitacaoPrecoValida() {
		status = true;
		mudaStatusCheckPreco();
		checkPreco.setVisible(true);
		textFieldQtdEstoque.requestFocus();
	}
	
	private boolean verificaDigitacaoPreco() {
		
		boolean toReturn = true;
		
		if(VariaveisProjeto.digitacaoCampo(textFieldPreco.getText())) {
			status = false;
			mudaStatusCheckPreco();
			return toReturn;
		}
		
		toReturn = false;
		return toReturn;
	}

	private void mudaStatusCheckPreco() {
		checkPreco.setVisible(true);
		if(status == false){
			checkPreco.setIcon(new ImageIcon(CpuGUI.class.getResource("/com/projeto/estrutura/imagens/iconFechar.png")));
		} else {
			checkPreco.setIcon(new ImageIcon(CpuGUI.class.getResource("/com/projeto/estrutura/imagens/ok.png")));
		}
	}
	
	private void digitacaoQtdEstoqueValida() {
		status = true;
		mudaStatusCheckQtdEstoque();
		checkQtdEstoque.setVisible(true);
		textFieldThreads.requestFocus();
	}
	
	private boolean verificaDigitacaoQtdEstoque() {
		
		boolean toReturn = true;
		
		if(VariaveisProjeto.digitacaoCampo(textFieldQtdEstoque.getText())) {
			status = false;
			mudaStatusCheckQtdEstoque();
			return toReturn;
		}
		
		toReturn = false;
		return toReturn;
	}

	private void mudaStatusCheckQtdEstoque() {
		checkQtdEstoque.setVisible(true);
		if(status == false){
			checkQtdEstoque.setIcon(new ImageIcon(CpuGUI.class.getResource("/com/projeto/estrutura/imagens/iconFechar.png")));
		} else {
			checkQtdEstoque.setIcon(new ImageIcon(CpuGUI.class.getResource("/com/projeto/estrutura/imagens/ok.png")));
		}
	}
	
	private void digitacaoThreadsValida() {
		status = true;
		mudaStatusCheckThreads();
		checkThreads.setVisible(true);
		textFieldVelocidade.requestFocus();
	}
	
	private boolean verificaDigitacaoThreads() {
		
		boolean toReturn = true;
		
		if(VariaveisProjeto.digitacaoCampo(textFieldThreads.getText())) {
			status = false;
			mudaStatusCheckThreads();
			return toReturn;
		}
		
		toReturn = false;
		return toReturn;
	}

	private void mudaStatusCheckThreads() {
		checkThreads.setVisible(true);
		if(status == false){
			checkThreads.setIcon(new ImageIcon(CpuGUI.class.getResource("/com/projeto/estrutura/imagens/iconFechar.png")));
		} else {
			checkThreads.setIcon(new ImageIcon(CpuGUI.class.getResource("/com/projeto/estrutura/imagens/ok.png")));
		}
	}
	
	private void digitacaoVelocidadeValida() {
		status = true;
		mudaStatusCheckVelocidade();
		checkVelocidade.setVisible(true);
	}
	
	private boolean verificaDigitacaoVelocidade() {
		
		boolean toReturn = true;
		
		if(VariaveisProjeto.digitacaoCampo(textFieldVelocidade.getText())) {
			status = false;
			mudaStatusCheckVelocidade();
			return toReturn;
		}
		
		toReturn = false;
		return toReturn;
	}

	private void mudaStatusCheckVelocidade() {
		checkVelocidade.setVisible(true);
		if(status == false){
			checkVelocidade.setIcon(new ImageIcon(CpuGUI.class.getResource("/com/projeto/estrutura/imagens/iconFechar.png")));
		} else {
			checkVelocidade.setIcon(new ImageIcon(CpuGUI.class.getResource("/com/projeto/estrutura/imagens/ok.png")));
		}
	}
	
	protected void incluirCpu() {
		Integer toReturn = 0;
		
		Cpu cpu = pegarDadosCpu();
		
		CpuService cpuService = new CpuService();
		
		toReturn = cpuService.save(cpu);
		
		erroDigitacao(toReturn);
		
		if(toReturn == VariaveisProjeto.ERRO_INCLUSAO) {
			showMensagem("Erro na Inclusão do Registro, verifique com seu administrador",
							"Erro", JOptionPane.ERROR_MESSAGE);
		}
		if(toReturn == VariaveisProjeto.INCLUSAO_REALIZADA) {
			showMensagem("Inclusão do Registro realizada com sucesso!",
							"OK", JOptionPane.OK_OPTION);
			limpaTextoCampo();
			tabelaCpuModel.fireTableDataChanged();
			cpu = new Cpu();
		}
	}
	
	protected void alterarCpu() {
		Integer toReturn = 0;
		
		Cpu cpu = pegarDadosCpu();
		
		CpuService cpuService = new CpuService();
		
		toReturn = cpuService.update(cpu);
		
		erroDigitacao(toReturn);
		
		if(toReturn == VariaveisProjeto.ERRO_ALTERACAO) {
			showMensagem("Erro na Alteração do Registro, verifique com seu administrador",
							"Erro", JOptionPane.ERROR_MESSAGE);
		}
		if(toReturn == VariaveisProjeto.ALTERACAO_REALIZADA) {
			showMensagem("Alteração do Registro realizada com sucesso!",
							"OK", JOptionPane.OK_OPTION);
			tabelaCpuModel.fireTableDataChanged();
			limpaTextoCampo();
			cpu = new Cpu();
		}
	}
	
	private void erroDigitacao(Integer toReturn) {
		if(toReturn == VariaveisProjeto.CPU_CACHE) {
			status = false;
			mudaStatusCheckCache();
			showMensagem("Erro na digitação do CACHE, verifique!", "Erro", JOptionPane.ERROR_MESSAGE);
		}
		
		if(toReturn == VariaveisProjeto.CPU_MARCA) {
			status = false;
			mudaStatusCheckMarca();
			showMensagem("Erro na digitação da MARCA, verifique!", "Erro", JOptionPane.ERROR_MESSAGE);
		}	
		
		if(toReturn == VariaveisProjeto.CPU_MODELO) {
			status = false;
			mudaStatusCheckModelo();
			showMensagem("Erro na digitação do MODELO, verifique!", "Erro", JOptionPane.ERROR_MESSAGE);
		}
		
		if(toReturn == VariaveisProjeto.CPU_NUCLEOS) {
			status = false;
			mudaStatusCheckNucleos();
			showMensagem("Erro na digitação dos NÚCLEOS, verifique!", "Erro", JOptionPane.ERROR_MESSAGE);
		}	
		
		if(toReturn == VariaveisProjeto.CPU_PRECO) {
			status = false;
			mudaStatusCheckPreco();
			showMensagem("Erro na digitação do PREÇO, verifique!", "Erro", JOptionPane.ERROR_MESSAGE);
		}	
		
		if(toReturn == VariaveisProjeto.CPU_QTDESTOQUE) {
			status = false;
			mudaStatusCheckQtdEstoque();
			showMensagem("Erro na digitação do ESTOQUE, verifique!", "Erro", JOptionPane.ERROR_MESSAGE);
		}	
		
		if(toReturn == VariaveisProjeto.CPU_THREADS) {
			status = false;
			mudaStatusCheckThreads();
			showMensagem("Erro na digitação dos THREADS, verifique!", "Erro", JOptionPane.ERROR_MESSAGE);
		}	
		
		if(toReturn == VariaveisProjeto.CPU_VELOCIDADE) {
			status = false;
			mudaStatusCheckVelocidade();
			showMensagem("Erro na digitação da VELOCIDADE, verifique!", "Erro", JOptionPane.ERROR_MESSAGE);
		}	
	}
	
	protected void excluirCpu() {
		Integer toReturn = 0;
		
		Cpu cpu = pegarDadosCpu();
		
		CpuService cpuService = new CpuService();
		
		toReturn = cpuService.remove(cpu);
		
		if(toReturn == VariaveisProjeto.ERRO_EXCLUSAO) {
			showMensagem("Erro na Exclusão do Registro, verifique com seu administrador",
							"Erro", JOptionPane.ERROR_MESSAGE);
		}
		if(toReturn == VariaveisProjeto.EXCLUSAO_REALIZADA) {
			showMensagem("Alteração do Registro realizada com sucesso!",
							"OK", JOptionPane.OK_OPTION);
			limpaTextoCampo();
			tabelaCpuModel.fireTableDataChanged();
			cpu = new Cpu();
		}
	}
	
	private void buscarCpu() {
		Cpu cpu = new Cpu();
		
		/*
		if(VariaveisProjeto.digitacaoCampo(textFieldCodigo.getText())){
			textFieldCodigo.requestFocus();
			return;
		}
		 
		Integer id = Integer.valueOf(textFieldCodigo.getText());
		*/
		
		cpu = tabelaCpuModel.getCpu(this.linha);
		
		System.out.println(cpu.toString());
		
		//CpuService cpuService = new CpuService();
		
		//cpu = cpuService.findById(id);
		
		textFieldCodigo.setText(String.valueOf(cpu.getId()));
		textFieldCache.setText(String.valueOf(cpu.getCache()));
		textFieldMarca.setText(cpu.getMarca());
		textFieldModelo.setText(cpu.getModelo());
		textFieldNucleos.setText(String.valueOf(cpu.getNucleos()));
		textFieldPreco.setText(String.valueOf(cpu.getPreco()));
		textFieldQtdEstoque.setText(String.valueOf(cpu.getQtd_estoque()));
		textFieldThreads.setText(String.valueOf(cpu.getThreads()));
		textFieldVelocidade.setText(cpu.getVelocidade());
	}
	
	private Cpu pegarDadosCpu() {
		Cpu cpu = new Cpu();
		
		if(VariaveisProjeto.digitacaoCampo(textFieldCodigo.getText())) {
			textFieldCodigo.requestFocus();
		}
		
		if(VariaveisProjeto.digitacaoCampo(textFieldCodigo.getText()) == false) {
			cpu.setId(Integer.valueOf(textFieldCodigo.getText()));
		}
		
		cpu.setCache(Integer.valueOf(textFieldCache.getText()));
		cpu.setMarca(textFieldMarca.getText());
		cpu.setModelo(textFieldModelo.getText());
		cpu.setNucleos(Integer.valueOf(textFieldNucleos.getText()));
		cpu.setPreco(Integer.valueOf(textFieldPreco.getText()));
		cpu.setQtd_estoque(Integer.valueOf(textFieldQtdEstoque.getText()));
		cpu.setThreads(Integer.valueOf(textFieldThreads.getText()));
		cpu.setVelocidade(textFieldVelocidade.getText());
		
		return cpu;
	}
	
	private void fecharCpu() {
		dispose();
	}
	
	private void desabilitaCheck() {
		checkCache.setVisible(false);
		checkMarca.setVisible(false);
		checkModelo.setVisible(false);
		checkNucleos.setVisible(false);
		checkPreco.setVisible(false);
		checkQtdEstoque.setVisible(false);
		checkThreads.setVisible(false);
		checkVelocidade.setVisible(false);
	}
	
	private void limpaTextoCampo() {
		textFieldCodigo.setText(VariaveisProjeto.LIMPA_CAMPO);
		textFieldCache.setText(VariaveisProjeto.LIMPA_CAMPO);
		textFieldMarca.setText(VariaveisProjeto.LIMPA_CAMPO);
		textFieldModelo.setText(VariaveisProjeto.LIMPA_CAMPO);
		textFieldNucleos.setText(VariaveisProjeto.LIMPA_CAMPO);
		textFieldPreco.setText(VariaveisProjeto.LIMPA_CAMPO);
		textFieldQtdEstoque.setText(VariaveisProjeto.LIMPA_CAMPO);
		textFieldThreads.setText(VariaveisProjeto.LIMPA_CAMPO);
		textFieldVelocidade.setText(VariaveisProjeto.LIMPA_CAMPO);
	}
	
	private void showMensagem(String mensagem, String status, int option) {
		JOptionPane.showMessageDialog(null, mensagem, status, option);
	}

}
