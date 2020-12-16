package com.projeto.view.gpu;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import com.projeto.estrutura.util.VariaveisProjeto;
import com.projeto.model.models.Gpu;
import com.projeto.model.service.GpuService;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GpuGUI extends JDialog {

	private static final long serialVersionUID = -133199759741905469L;

	private JPanel contentPane;
	private JLabel lbCodigo;
	private JLabel lblFabricante;
	private JLabel lblMarca;
	private JLabel lblModelo;
	private JLabel lblPreco;
	private JLabel lblEstoque;
	private JLabel lblTipoMemoria;
	private JLabel lblVram;
	private JTextField textFieldCodigo;
	private JTextField textFieldFabricante;
	private JTextField textFieldMarca;
	private JTextField textFieldModelo;
	private JTextField textFieldPreco;
	private JTextField textFieldQtdEstoque;
	private JTextField textFieldTipoMemoria;
	private JTextField textFieldVram;
	private JLabel checkFabricante;
	private JLabel checkMarca;
	private JLabel checkModelo;
	private JLabel checkPreco;
	private JLabel checkQtdEstoque;
	private JLabel checkTipoMemoria;
	private JLabel checkVram;
	private JButton btnIncluir;
	private JButton btnAlterar;
	private JButton btnExcluir;
	private JButton btnSair;

	private boolean status = true;
	
	private JTable tabelaGpu;
	private TabelaGpuModel tabelaGpuModel;
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
					GpuGUI frame = new GpuGUI();
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
	public GpuGUI(JFrame frame, boolean modal, JTable tabelaGpu, TabelaGpuModel tabelaGpuModel, int linha, int acao) {
		
		super(frame, modal);
		
		initComponents();
		
		this.tabelaGpu = tabelaGpu;
		this.tabelaGpuModel = tabelaGpuModel;
		this.linha = linha;
		this.acao = acao;

		limpaTextoCampo();

		desabilitaCheck();
		
		configuraAcaoGpu();
	}
	
	private void configuraAcaoGpu() {
		if(this.acao == VariaveisProjeto.INCLUSAO) {
			btnIncluir.setVisible(true);
			btnAlterar.setVisible(false);
			btnExcluir.setVisible(false);
		}
		if(this.acao == VariaveisProjeto.ALTERACAO) {
			btnAlterar.setVisible(true);
			btnExcluir.setVisible(false);
			btnIncluir.setVisible(false);
			buscarGpu();
		}
		if(this.acao == VariaveisProjeto.EXCLUSAO) {
			btnExcluir.setVisible(true);
			btnAlterar.setVisible(false);
			btnIncluir.setVisible(false);
			buscarGpu();
		}
	}	
	
	private void initComponents() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(GpuGUI.class.getResource("/com/projeto/estrutura/imagens/book.png")));
		setTitle("Cadastro de Gpu");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 651, 564);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		lbCodigo = new JLabel("Código:");

		lblFabricante = new JLabel("Fabricante:");

		lblMarca = new JLabel("Marca:");

		lblModelo = new JLabel("Modelo:");

		lblPreco = new JLabel("Preço:");

		lblEstoque = new JLabel("Estoque:");

		lblTipoMemoria = new JLabel("Tipo Memória:");

		lblVram = new JLabel("VRAM:");

		textFieldCodigo = new JTextField();
		textFieldCodigo.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				//buscarGpu();
			}
		});
		textFieldCodigo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					//buscarGpu();
					textFieldFabricante.requestFocus();
				}
			}
		});
		textFieldCodigo.setColumns(10);

		textFieldFabricante = new JTextField();
		textFieldFabricante.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if(verificaDigitacaoFabricante()) {
					textFieldFabricante.requestFocus();
				} else {
					digitacaoFabricanteValida();
				}
			}
		});
		textFieldFabricante.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					if(verificaDigitacaoFabricante()) {
						textFieldFabricante.requestFocus();
					} else {
						digitacaoFabricanteValida();
					}
				}
			}
		});
		textFieldFabricante.setColumns(10);

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

		textFieldTipoMemoria = new JTextField();
		textFieldTipoMemoria.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if(verificaDigitacaoTipoMemoria()) {
					textFieldTipoMemoria.requestFocus();
				} else {
					digitacaoTipoMemoriaValida();
				}
			}
		});
		textFieldTipoMemoria.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					if(verificaDigitacaoTipoMemoria()) {
						textFieldTipoMemoria.requestFocus();
					} else {
						digitacaoTipoMemoriaValida();
					}
				}
			}
		});
		textFieldTipoMemoria.setColumns(10);

		textFieldVram = new JTextField();
		textFieldVram.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if(verificaDigitacaoVram()) {
					textFieldVram.requestFocus();
				} else {
					digitacaoVramValida();
				}
			}
		});
		textFieldVram.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					if(verificaDigitacaoVram()) {
						textFieldVram.requestFocus();
					} else {
						digitacaoVramValida();
					}
				}
			}
		});
		textFieldVram.setColumns(10);

		checkFabricante = new JLabel("");
		checkFabricante.setIcon(new ImageIcon(GpuGUI.class.getResource("/com/projeto/estrutura/imagens/ok.png")));

		checkMarca = new JLabel("");
		checkMarca.setIcon(new ImageIcon(GpuGUI.class.getResource("/com/projeto/estrutura/imagens/ok.png")));

		checkModelo = new JLabel("");
		checkModelo.setIcon(new ImageIcon(GpuGUI.class.getResource("/com/projeto/estrutura/imagens/ok.png")));

		checkPreco = new JLabel("");
		checkPreco.setIcon(new ImageIcon(GpuGUI.class.getResource("/com/projeto/estrutura/imagens/ok.png")));

		checkQtdEstoque = new JLabel("");
		checkQtdEstoque.setIcon(new ImageIcon(GpuGUI.class.getResource("/com/projeto/estrutura/imagens/ok.png")));

		checkTipoMemoria = new JLabel("");
		checkTipoMemoria.setIcon(new ImageIcon(GpuGUI.class.getResource("/com/projeto/estrutura/imagens/ok.png")));

		checkVram = new JLabel("");
		checkVram.setIcon(new ImageIcon(GpuGUI.class.getResource("/com/projeto/estrutura/imagens/ok.png")));

		btnIncluir = new JButton("Incluir");
		btnIncluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				incluirGpu();
			}
		});
		btnIncluir.setIcon(new ImageIcon(GpuGUI.class.getResource("/com/projeto/estrutura/imagens/application_add.png")));

		btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alterarGpu();
			}
		});
		btnAlterar.setIcon(new ImageIcon(GpuGUI.class.getResource("/com/projeto/estrutura/imagens/application_edit.png")));

		btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluirGpu();
			}
		});
		btnExcluir.setIcon(new ImageIcon(GpuGUI.class.getResource("/com/projeto/estrutura/imagens/application_delete.png")));

		btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fecharGpu();
			}
		});
		btnSair.setIcon(new ImageIcon(GpuGUI.class.getResource("/com/projeto/estrutura/imagens/sair.png")));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
										.addGap(130)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
												.addComponent(lblVram)
												.addComponent(lblTipoMemoria)
												.addComponent(lblEstoque)
												.addComponent(lblPreco)
												.addComponent(lblModelo)
												.addComponent(lblMarca)
												.addComponent(lblFabricante)
												.addComponent(lbCodigo))
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
												.addComponent(textFieldCodigo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addGroup(gl_contentPane.createSequentialGroup()
														.addComponent(textFieldPreco, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(ComponentPlacement.UNRELATED)
														.addComponent(checkPreco))
												.addGroup(gl_contentPane.createSequentialGroup()
														.addComponent(textFieldQtdEstoque, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(ComponentPlacement.UNRELATED)
														.addComponent(checkQtdEstoque))
												.addGroup(gl_contentPane.createSequentialGroup()
														.addComponent(textFieldTipoMemoria, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(ComponentPlacement.UNRELATED)
														.addComponent(checkTipoMemoria))
												.addGroup(gl_contentPane.createSequentialGroup()
														.addComponent(textFieldVram, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(ComponentPlacement.UNRELATED)
														.addComponent(checkVram))
												.addComponent(textFieldFabricante, GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
												.addComponent(textFieldMarca)
												.addComponent(textFieldModelo))
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
												.addComponent(checkFabricante)
												.addComponent(checkMarca)
												.addComponent(checkModelo)))
								.addGroup(gl_contentPane.createSequentialGroup()
										.addGap(100)
										.addComponent(btnIncluir)
										.addGap(18)
										.addComponent(btnAlterar)
										.addGap(18)
										.addComponent(btnExcluir)
										.addGap(18)
										.addComponent(btnSair)))
						.addContainerGap(133, Short.MAX_VALUE))
				);
		gl_contentPane.setVerticalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addGap(81)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lbCodigo)
								.addComponent(textFieldCodigo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblFabricante)
								.addComponent(textFieldFabricante, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(checkFabricante))
						.addGap(18)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblMarca)
								.addComponent(textFieldMarca, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(checkMarca))
						.addGap(18)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblModelo)
								.addComponent(textFieldModelo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(checkModelo))
						.addGap(18)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblPreco)
								.addComponent(textFieldPreco, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(checkPreco))
						.addGap(18)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblEstoque)
								.addComponent(textFieldQtdEstoque, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(checkQtdEstoque))
						.addGap(18)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblTipoMemoria)
								.addComponent(textFieldTipoMemoria, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(checkTipoMemoria))
						.addGap(18)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblVram)
								.addComponent(textFieldVram, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(checkVram))
						.addPreferredGap(ComponentPlacement.RELATED, 114, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnIncluir)
								.addComponent(btnAlterar)
								.addComponent(btnExcluir)
								.addComponent(btnSair))
						.addContainerGap())
				);
		contentPane.setLayout(gl_contentPane);
	}

	private void digitacaoFabricanteValida() {
		status = true;
		mudaStatusCheckFabricante();
		checkFabricante.setVisible(true);
		textFieldMarca.requestFocus();
	}

	private boolean verificaDigitacaoFabricante() {

		boolean toReturn = true;

		if(VariaveisProjeto.digitacaoCampo(textFieldFabricante.getText())) {
			status = false;
			mudaStatusCheckFabricante();
			return toReturn;
		}

		toReturn = false;
		return toReturn;
	}

	private void mudaStatusCheckFabricante() {
		checkFabricante.setVisible(true);
		if(status == false){
			checkFabricante.setIcon(new ImageIcon(GpuGUI.class.getResource("/com/projeto/estrutura/imagens/iconFechar.png")));
		} else {
			checkFabricante.setIcon(new ImageIcon(GpuGUI.class.getResource("/com/projeto/estrutura/imagens/ok.png")));
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
			checkMarca.setIcon(new ImageIcon(GpuGUI.class.getResource("/com/projeto/estrutura/imagens/iconFechar.png")));
		} else {
			checkMarca.setIcon(new ImageIcon(GpuGUI.class.getResource("/com/projeto/estrutura/imagens/ok.png")));
		}
	}

	private void digitacaoModeloValida() {
		status = true;
		mudaStatusCheckModelo();
		checkModelo.setVisible(true);
		textFieldPreco.requestFocus();
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
			checkModelo.setIcon(new ImageIcon(GpuGUI.class.getResource("/com/projeto/estrutura/imagens/iconFechar.png")));
		} else {
			checkModelo.setIcon(new ImageIcon(GpuGUI.class.getResource("/com/projeto/estrutura/imagens/ok.png")));
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
			checkPreco.setIcon(new ImageIcon(GpuGUI.class.getResource("/com/projeto/estrutura/imagens/iconFechar.png")));
		} else {
			checkPreco.setIcon(new ImageIcon(GpuGUI.class.getResource("/com/projeto/estrutura/imagens/ok.png")));
		}
	}

	private void digitacaoQtdEstoqueValida() {
		status = true;
		mudaStatusCheckQtdEstoque();
		checkQtdEstoque.setVisible(true);
		textFieldTipoMemoria.requestFocus();
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
			checkQtdEstoque.setIcon(new ImageIcon(GpuGUI.class.getResource("/com/projeto/estrutura/imagens/iconFechar.png")));
		} else {
			checkQtdEstoque.setIcon(new ImageIcon(GpuGUI.class.getResource("/com/projeto/estrutura/imagens/ok.png")));
		}
	}

	private void digitacaoTipoMemoriaValida() {
		status = true;
		mudaStatusCheckTipoMemoria();
		checkTipoMemoria.setVisible(true);
		textFieldVram.requestFocus();
	}

	private boolean verificaDigitacaoTipoMemoria() {

		boolean toReturn = true;

		if(VariaveisProjeto.digitacaoCampo(textFieldTipoMemoria.getText())) {
			status = false;
			mudaStatusCheckTipoMemoria();
			return toReturn;
		}

		toReturn = false;
		return toReturn;
	}

	private void mudaStatusCheckTipoMemoria() {
		checkTipoMemoria.setVisible(true);
		if(status == false){
			checkTipoMemoria.setIcon(new ImageIcon(GpuGUI.class.getResource("/com/projeto/estrutura/imagens/iconFechar.png")));
		} else {
			checkTipoMemoria.setIcon(new ImageIcon(GpuGUI.class.getResource("/com/projeto/estrutura/imagens/ok.png")));
		}
	}

	private void digitacaoVramValida() {
		status = true;
		mudaStatusCheckVram();
		checkVram.setVisible(true);
	}

	private boolean verificaDigitacaoVram() {

		boolean toReturn = true;

		if(VariaveisProjeto.digitacaoCampo(textFieldVram.getText())) {
			status = false;
			mudaStatusCheckVram();
			return toReturn;
		}

		toReturn = false;
		return toReturn;
	}

	private void mudaStatusCheckVram() {
		checkVram.setVisible(true);
		if(status == false){
			checkVram.setIcon(new ImageIcon(GpuGUI.class.getResource("/com/projeto/estrutura/imagens/iconFechar.png")));
		} else {
			checkVram.setIcon(new ImageIcon(GpuGUI.class.getResource("/com/projeto/estrutura/imagens/ok.png")));
		}
	}

	protected void incluirGpu() {
		Integer toReturn = 0;

		Gpu gpu = pegarDadosGpu();

		GpuService gpuService = new GpuService();

		toReturn = gpuService.save(gpu);

		erroDigitacao(toReturn);

		if(toReturn == VariaveisProjeto.ERRO_INCLUSAO) {
			showMensagem("Erro na Inclusão do Registro, verifique com seu administrador",
					"Erro", JOptionPane.ERROR_MESSAGE);
		}
		if(toReturn == VariaveisProjeto.INCLUSAO_REALIZADA) {
			showMensagem("Inclusão do Registro realizada com sucesso!",
					"OK", JOptionPane.OK_OPTION);
			limpaTextoCampo();
			tabelaGpuModel.fireTableDataChanged();
			gpu = new Gpu();
		}
	}

	protected void alterarGpu() {
		Integer toReturn = 0;

		Gpu gpu = pegarDadosGpu();

		GpuService gpuService = new GpuService();

		toReturn = gpuService.update(gpu);

		erroDigitacao(toReturn);

		if(toReturn == VariaveisProjeto.ERRO_ALTERACAO) {
			showMensagem("Erro na Alteração do Registro, verifique com seu administrador",
					"Erro", JOptionPane.ERROR_MESSAGE);
		}
		if(toReturn == VariaveisProjeto.ALTERACAO_REALIZADA) {
			showMensagem("Alteração do Registro realizada com sucesso!",
					"OK", JOptionPane.OK_OPTION);
			tabelaGpuModel.fireTableDataChanged();
			limpaTextoCampo();
			gpu = new Gpu();
		}
	}

	private void erroDigitacao(Integer toReturn) {
		if(toReturn == VariaveisProjeto.GPU_FABRICANTE) {
			status = false;
			mudaStatusCheckFabricante();
			showMensagem("Erro na digitação da FABRICANTE, verifique!", "Erro", JOptionPane.ERROR_MESSAGE);
		}

		if(toReturn == VariaveisProjeto.GPU_MARCA) {
			status = false;
			mudaStatusCheckMarca();
			showMensagem("Erro na digitação da MARCA, verifique!", "Erro", JOptionPane.ERROR_MESSAGE);
		}	

		if(toReturn == VariaveisProjeto.GPU_MODELO) {
			status = false;
			mudaStatusCheckModelo();
			showMensagem("Erro na digitação do MODELO, verifique!", "Erro", JOptionPane.ERROR_MESSAGE);
		}

		if(toReturn == VariaveisProjeto.GPU_PRECO) {
			status = false;
			mudaStatusCheckPreco();
			showMensagem("Erro na digitação do PREÇO, verifique!", "Erro", JOptionPane.ERROR_MESSAGE);
		}	

		if(toReturn == VariaveisProjeto.GPU_QTDESTOQUE) {
			status = false;
			mudaStatusCheckQtdEstoque();
			showMensagem("Erro na digitação do ESTOQUE, verifique!", "Erro", JOptionPane.ERROR_MESSAGE);
		}	

		if(toReturn == VariaveisProjeto.GPU_TIPOMEMORIA) {
			status = false;
			mudaStatusCheckTipoMemoria();
			showMensagem("Erro na digitação do TIPO DE MEMÓRIA, verifique!", "Erro", JOptionPane.ERROR_MESSAGE);
		}	

		if(toReturn == VariaveisProjeto.GPU_VRAM) {
			status = false;
			mudaStatusCheckVram();
			showMensagem("Erro na digitação da VRAM, verifique!", "Erro", JOptionPane.ERROR_MESSAGE);
		}	
	}

	protected void excluirGpu() {
		Integer toReturn = 0;

		Gpu gpu = pegarDadosGpu();

		GpuService gpuService = new GpuService();

		toReturn = gpuService.remove(gpu);

		if(toReturn == VariaveisProjeto.ERRO_EXCLUSAO) {
			showMensagem("Erro na Exclusão do Registro, verifique com seu administrador",
					"Erro", JOptionPane.ERROR_MESSAGE);
		}
		if(toReturn == VariaveisProjeto.EXCLUSAO_REALIZADA) {
			showMensagem("Alteração do Registro realizada com sucesso!",
					"OK", JOptionPane.OK_OPTION);
			limpaTextoCampo();
			tabelaGpuModel.fireTableDataChanged();
			gpu = new Gpu();
		}
	}

	private void buscarGpu() {
		Gpu gpu = new Gpu();

		/*
		if(VariaveisProjeto.digitacaoCampo(textFieldCodigo.getText())){
			textFieldCodigo.requestFocus();
			return;
		}

		Integer id = Integer.valueOf(textFieldCodigo.getText());
		*/
		
		gpu = tabelaGpuModel.getGpu(this.linha);

		System.out.println(gpu.toString());
		
		//GpuService gpuService = new GpuService();

		//gpu = gpuService.findById(id);

		textFieldCodigo.setText(String.valueOf(gpu.getId()));
		textFieldFabricante.setText(gpu.getFabricante());
		textFieldMarca.setText(gpu.getMarca());
		textFieldModelo.setText(gpu.getModelo());
		textFieldPreco.setText(String.valueOf(gpu.getPreco()));
		textFieldQtdEstoque.setText(String.valueOf(gpu.getQtd_estoque()));
		textFieldTipoMemoria.setText(gpu.getTipo_memoria());
		textFieldVram.setText(String.valueOf(gpu.getVram()));
	}

	private Gpu pegarDadosGpu() {
		Gpu gpu = new Gpu();

		if(VariaveisProjeto.digitacaoCampo(textFieldCodigo.getText())) {
			textFieldCodigo.requestFocus();
		}

		if(VariaveisProjeto.digitacaoCampo(textFieldCodigo.getText()) == false) {
			gpu.setId(Integer.valueOf(textFieldCodigo.getText()));
		}

		gpu.setFabricante(textFieldFabricante.getText());
		gpu.setMarca(textFieldMarca.getText());
		gpu.setModelo(textFieldModelo.getText());
		gpu.setPreco(Integer.valueOf(textFieldPreco.getText()));
		gpu.setQtd_estoque(Integer.valueOf(textFieldQtdEstoque.getText()));
		gpu.setTipo_memoria(textFieldTipoMemoria.getText());
		gpu.setVram(Integer.valueOf(textFieldVram.getText()));

		return gpu;
	}

	private void fecharGpu() {
		dispose();
	}

	private void desabilitaCheck() {
		checkFabricante.setVisible(false);
		checkMarca.setVisible(false);
		checkModelo.setVisible(false);
		checkPreco.setVisible(false);
		checkQtdEstoque.setVisible(false);
		checkTipoMemoria.setVisible(false);
		checkVram.setVisible(false);
	}

	private void limpaTextoCampo() {
		textFieldCodigo.setText(VariaveisProjeto.LIMPA_CAMPO);
		textFieldFabricante.setText(VariaveisProjeto.LIMPA_CAMPO);
		textFieldMarca.setText(VariaveisProjeto.LIMPA_CAMPO);
		textFieldModelo.setText(VariaveisProjeto.LIMPA_CAMPO);
		textFieldPreco.setText(VariaveisProjeto.LIMPA_CAMPO);
		textFieldQtdEstoque.setText(VariaveisProjeto.LIMPA_CAMPO);
		textFieldTipoMemoria.setText(VariaveisProjeto.LIMPA_CAMPO);
		textFieldVram.setText(VariaveisProjeto.LIMPA_CAMPO);

	}

	private void showMensagem(String mensagem, String status, int option) {
		JOptionPane.showMessageDialog(null, mensagem, status, option);
	}
}
