package com.projeto.view.pedido;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import com.projeto.estrutura.util.VariaveisProjeto;
import com.projeto.model.models.Pedido;
import com.projeto.model.service.PedidoService;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PedidoGUI extends JDialog {

	private static final long serialVersionUID = -1962685672039414834L;
	
	private JPanel contentPane;
	private JLabel lblCodigo;
	private JLabel lblData;
	private JLabel lblPrecoTotal;
	private JTextField textFieldCodigo;
	private JTextField textFieldData;
	private JTextField textFieldPrecoTotal;
	private JLabel lblCliente;
	private JTextField textFieldCliente;
	private JButton btnCliente;
	private JLabel lblPecas;
	private JLabel lblCpu;
	private JTextField textFieldCpu;
	private JButton btnCpu;
	private JLabel lblGpu;
	private JTextField textFieldGpu;
	private JButton btnGpu;
	private JButton btnIncluir;
	private JButton btnAlterar;
	private JButton btnExcluir;
	private JButton btnSair;
	private JLabel checkData;
	private JLabel checkPrecoTotal;
	
	private boolean status = true;
	
	private JTable tabelaPedido;
	private TabelaPedidoModel tabelaPedidoModel;
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
					PedidoGUI frame = new PedidoGUI();
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
	public PedidoGUI(JFrame frame, boolean modal, JTable tabelaPedido, TabelaPedidoModel tabelaPedidoModel, int linha, int acao) {
		
		super(frame, modal);
		
		initComponents();
		
		this.tabelaPedido = tabelaPedido;
		this.tabelaPedidoModel = tabelaPedidoModel;
		this.linha = linha;
		this.acao = acao;
		
		limpaTextoCampo();
		
		desabilitaCheck();
		
		configuraAcaoPedido();
	}
	
	private void configuraAcaoPedido() {
		if(this.acao == VariaveisProjeto.INCLUSAO) {
			btnIncluir.setVisible(true);
			btnAlterar.setVisible(false);
			btnExcluir.setVisible(false);
		}
		if(this.acao == VariaveisProjeto.ALTERACAO) {
			btnAlterar.setVisible(true);
			btnExcluir.setVisible(false);
			btnIncluir.setVisible(false);
			buscarPedido();
		}
		if(this.acao == VariaveisProjeto.EXCLUSAO) {
			btnExcluir.setVisible(true);
			btnAlterar.setVisible(false);
			btnIncluir.setVisible(false);
			buscarPedido();
		}
	}
	
	private void initComponents() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(PedidoGUI.class.getResource("/com/projeto/estrutura/imagens/book_open.png")));
		setTitle("Cadastro de Pedido");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 635, 520);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		lblCodigo = new JLabel("Código:");
		
		lblData = new JLabel("Data:");
		
		lblPrecoTotal = new JLabel("Preco Total:");
		
		textFieldCodigo = new JTextField();
		textFieldCodigo.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				//buscarPedido();
			}
		});
		textFieldCodigo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					//buscarPedido();
					textFieldData.requestFocus();
				}
			}
		});
		textFieldCodigo.setColumns(10);
		
		textFieldData = new JTextField();
		textFieldData.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if(verificaDigitacaoData()) {
					textFieldData.requestFocus();
				} else {
					digitacaoDataValida();
				}
			}
		});
		textFieldData.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					if(verificaDigitacaoData()) {
						textFieldData.requestFocus();
					} else {
						digitacaoDataValida();
					}
				}
			}
		});
		textFieldData.setColumns(10);
		
		textFieldPrecoTotal = new JTextField();
		textFieldPrecoTotal.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if(verificaDigitacaoPrecoTotal()) {
					textFieldPrecoTotal.requestFocus();
				} else {
					digitacaoPrecoTotalValido();
				}
			}
		});
		textFieldPrecoTotal.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					if(verificaDigitacaoPrecoTotal()) {
						textFieldPrecoTotal.requestFocus();
					} else {
						digitacaoPrecoTotalValido();
					}
				}
			}
		});
		textFieldPrecoTotal.setColumns(10);
		
		lblCliente = new JLabel("Cliente:");
		
		textFieldCliente = new JTextField();
		textFieldCliente.setEditable(false);
		textFieldCliente.setColumns(10);
		
		btnCliente = new JButton("Cliente");
		btnCliente.setIcon(new ImageIcon(PedidoGUI.class.getResource("/com/projeto/estrutura/imagens/search.png")));
		
		lblPecas = new JLabel("Peças");
		
		lblCpu = new JLabel("Cpu:");
		
		textFieldCpu = new JTextField();
		textFieldCpu.setEditable(false);
		textFieldCpu.setColumns(10);
		
		btnCpu = new JButton("Cpu");
		btnCpu.setIcon(new ImageIcon(PedidoGUI.class.getResource("/com/projeto/estrutura/imagens/search.png")));
		
		lblGpu = new JLabel("Gpu:");
		
		textFieldGpu = new JTextField();
		textFieldGpu.setEditable(false);
		textFieldGpu.setColumns(10);
		
		btnGpu = new JButton("Gpu");
		btnGpu.setIcon(new ImageIcon(PedidoGUI.class.getResource("/com/projeto/estrutura/imagens/search.png")));
		
		btnIncluir = new JButton("Incluir");
		btnIncluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				incluirPedido();
			}
		});
		btnIncluir.setIcon(new ImageIcon(PedidoGUI.class.getResource("/com/projeto/estrutura/imagens/application_add.png")));
		
		btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alterarPedido();
			}
		});
		btnAlterar.setIcon(new ImageIcon(PedidoGUI.class.getResource("/com/projeto/estrutura/imagens/application_edit.png")));
		
		btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluirPedido();
			}
		});
		btnExcluir.setIcon(new ImageIcon(PedidoGUI.class.getResource("/com/projeto/estrutura/imagens/application_delete.png")));
		
		btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fecharPedido();
			}
		});
		btnSair.setIcon(new ImageIcon(PedidoGUI.class.getResource("/com/projeto/estrutura/imagens/sair.png")));
		
		checkData = new JLabel("");
		checkData.setIcon(new ImageIcon(PedidoGUI.class.getResource("/com/projeto/estrutura/imagens/ok.png")));
		
		checkPrecoTotal = new JLabel("");
		checkPrecoTotal.setIcon(new ImageIcon(PedidoGUI.class.getResource("/com/projeto/estrutura/imagens/ok.png")));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(110)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblGpu)
						.addComponent(lblCpu)
						.addComponent(lblPecas)
						.addComponent(lblCliente)
						.addComponent(lblPrecoTotal)
						.addComponent(lblData)
						.addComponent(lblCodigo))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(textFieldCodigo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(textFieldData, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(checkData))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(textFieldPrecoTotal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(checkPrecoTotal))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(textFieldGpu, Alignment.LEADING)
								.addComponent(textFieldCpu, Alignment.LEADING)
								.addComponent(textFieldCliente, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(btnGpu)
								.addComponent(btnCpu)
								.addComponent(btnCliente))))
					.addContainerGap(120, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap(122, Short.MAX_VALUE)
					.addComponent(btnIncluir)
					.addGap(18)
					.addComponent(btnAlterar)
					.addGap(18)
					.addComponent(btnExcluir)
					.addGap(18)
					.addComponent(btnSair)
					.addGap(113))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(52)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCodigo)
						.addComponent(textFieldCodigo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblData)
						.addComponent(textFieldData, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(checkData))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPrecoTotal)
						.addComponent(textFieldPrecoTotal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(checkPrecoTotal))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCliente)
						.addComponent(textFieldCliente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnCliente))
					.addGap(46)
					.addComponent(lblPecas)
					.addGap(30)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCpu)
						.addComponent(textFieldCpu, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnCpu))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblGpu)
						.addComponent(textFieldGpu, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnGpu))
					.addPreferredGap(ComponentPlacement.RELATED, 98, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnIncluir)
						.addComponent(btnAlterar)
						.addComponent(btnExcluir)
						.addComponent(btnSair))
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
	}
	
	private void digitacaoDataValida() {
		status = true;
		mudaStatusCheckData();
		checkData.setVisible(true);
		textFieldPrecoTotal.requestFocus();
	}
	
	private boolean verificaDigitacaoData() {
		
		boolean toReturn = true;
		
		if(VariaveisProjeto.digitacaoCampo(textFieldData.getText())) {
			status = false;
			mudaStatusCheckData();
			return toReturn;
		}
		
		toReturn = false;
		return toReturn;
	}

	private void mudaStatusCheckData() {
		checkData.setVisible(true);
		if(status == false){
			checkData.setIcon(new ImageIcon(PedidoGUI.class.getResource("/com/projeto/estrutura/imagens/iconFechar.png")));
		} else {
			checkData.setIcon(new ImageIcon(PedidoGUI.class.getResource("/com/projeto/estrutura/imagens/ok.png")));
		}
	}
	
	private void digitacaoPrecoTotalValido() {
		status = true;
		mudaStatusCheckPrecoTotal();
		checkData.setVisible(true);
	}
	
	private boolean verificaDigitacaoPrecoTotal() {
		
		boolean toReturn = true;
		
		if(VariaveisProjeto.digitacaoCampo(textFieldPrecoTotal.getText())) {
			status = false;
			mudaStatusCheckPrecoTotal();
			return toReturn;
		}
		
		toReturn = false;
		return toReturn;
	}

	private void mudaStatusCheckPrecoTotal() {
		checkPrecoTotal.setVisible(true);
		if(status == false){
			checkPrecoTotal.setIcon(new ImageIcon(PedidoGUI.class.getResource("/com/projeto/estrutura/imagens/iconFechar.png")));
		} else {
			checkPrecoTotal.setIcon(new ImageIcon(PedidoGUI.class.getResource("/com/projeto/estrutura/imagens/ok.png")));
		}
	}
	
	private void desabilitaCheck() {
		checkData.setVisible(false);
		checkPrecoTotal.setVisible(false);
	}
	
	protected void incluirPedido() {
		Integer toReturn = 0;
		
		//Cliente cliente = new Cliente();
		
		//cliente.setId(1);
		
		Pedido pedido = pegarDadosPedido();
		
		//pedido.setCliente(cliente);
		
		PedidoService pedidoService = new PedidoService();
		
		toReturn = pedidoService.save(pedido);
		
		erroDigitacao(toReturn);
		
		if(toReturn == VariaveisProjeto.ERRO_INCLUSAO) {
			showMensagem("Erro na Inclusão do Registro, verifique com seu administrador",
							"Erro", JOptionPane.ERROR_MESSAGE);
		}
		if(toReturn == VariaveisProjeto.INCLUSAO_REALIZADA) {
			showMensagem("Inclusão do Registro realizada com sucesso!",
							"OK", JOptionPane.OK_OPTION);
			limpaTextoCampo();
			tabelaPedidoModel.fireTableDataChanged();
			pedido = new Pedido();
		}
	}
	
	protected void alterarPedido() {
		Integer toReturn = 0;
		
		Pedido pedido = pegarDadosPedido();
		
		PedidoService pedidoService = new PedidoService();
		
		toReturn = pedidoService.update(pedido);
		
		erroDigitacao(toReturn);
		
		if(toReturn == VariaveisProjeto.ERRO_ALTERACAO) {
			showMensagem("Erro na Alteração do Registro, verifique com seu administrador",
							"Erro", JOptionPane.ERROR_MESSAGE);
		}
		if(toReturn == VariaveisProjeto.ALTERACAO_REALIZADA) {
			showMensagem("Alteração do Registro realizada com sucesso!",
							"OK", JOptionPane.OK_OPTION);
			tabelaPedidoModel.fireTableDataChanged();
			limpaTextoCampo();
			pedido = new Pedido();
		}
	}
	
	private void erroDigitacao(Integer toReturn) {
		if(toReturn == VariaveisProjeto.PEDIDO_DATA) {
			status = false;
			mudaStatusCheckData();
			showMensagem("Erro na digitação da DATA, verifique!", "Erro", JOptionPane.ERROR_MESSAGE);
		}
		
		if(toReturn == VariaveisProjeto.PEDIDO_PRECO_TOTAL) {
			status = false;
			mudaStatusCheckPrecoTotal();
			showMensagem("Erro na digitação do PRECO TOTAL, verifique!", "Erro", JOptionPane.ERROR_MESSAGE);
		}	
	}
	
	protected void excluirPedido() {
		Integer toReturn = 0;
		
		Pedido pedido = pegarDadosPedido();
		
		PedidoService pedidoService = new PedidoService();
		
		toReturn = pedidoService.remove(pedido);
		
		if(toReturn == VariaveisProjeto.ERRO_EXCLUSAO) {
			showMensagem("Erro na Exclusão do Registro, verifique com seu administrador",
							"Erro", JOptionPane.ERROR_MESSAGE);
		}
		if(toReturn == VariaveisProjeto.EXCLUSAO_REALIZADA) {
			showMensagem("Alteração do Registro realizada com sucesso!",
							"OK", JOptionPane.OK_OPTION);
			limpaTextoCampo();
			tabelaPedidoModel.fireTableDataChanged();
			pedido = new Pedido();
		}
	}

	private void fecharPedido() {
		dispose();
	}
	
	private void buscarPedido() {
		Pedido pedido = new Pedido();
		
		/*
		if(VariaveisProjeto.digitacaoCampo(textFieldCodigo.getText())){
			textFieldCodigo.requestFocus();
			return;
		}
		 
		Integer id = Integer.valueOf(textFieldCodigo.getText());
		*/
		
		pedido = tabelaPedidoModel.getPedido(this.linha);
		
		System.out.println(pedido.toString());
		PedidoService pedidoService = new PedidoService();
		
		pedido = pedidoService.findById(pedido.getId());
		
		textFieldCodigo.setText(String.valueOf(pedido.getId()));
		textFieldData.setText(pedido.getData());
		textFieldPrecoTotal.setText(String.valueOf(pedido.getPreco_total()));
	}
	
	private Pedido pegarDadosPedido() {
		Pedido pedido = new Pedido();
		
		if(VariaveisProjeto.digitacaoCampo(textFieldCodigo.getText())) {
			textFieldCodigo.requestFocus();
		}
		
		if(VariaveisProjeto.digitacaoCampo(textFieldCodigo.getText()) == false) {
			pedido.setId(Integer.valueOf(textFieldCodigo.getText()));
		}
		
		pedido.setData(textFieldData.getText());
		pedido.setPreco_total(Integer.valueOf(textFieldPrecoTotal.getText()));
		
		return pedido;
	}
	
	private void limpaTextoCampo() {
		textFieldCodigo.setText(VariaveisProjeto.LIMPA_CAMPO);
		textFieldData.setText(VariaveisProjeto.LIMPA_CAMPO);
		textFieldPrecoTotal.setText(VariaveisProjeto.LIMPA_CAMPO);
	}
	
	private void showMensagem(String mensagem, String status, int option) {
		JOptionPane.showMessageDialog(null, mensagem, status, option);
	}
}
