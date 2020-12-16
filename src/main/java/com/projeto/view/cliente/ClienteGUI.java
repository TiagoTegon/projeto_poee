package com.projeto.view.cliente;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import com.projeto.estrutura.util.VariaveisProjeto;
import com.projeto.model.models.Cliente;
import com.projeto.model.service.ClienteService;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ClienteGUI extends JDialog {

	private static final long serialVersionUID = -5509604983623751167L;
	
	private JPanel contentPane;
	private JTextField textFieldCodigo;
	private JTextField textFieldNome;
	private JTextField textFieldEmail;
	private JTextField textFieldCPF;
	private JTextField textFieldTelefone;
	private JButton btnIncluir;
	private JButton btnAlterar;
	private JButton btnExcluir;
	private JButton btnSair;

	private JLabel checkNome;
	private JLabel checkEmail;
	private JLabel checkCPF;
	private JLabel checkTelefone;

	private boolean status = true;
	
	private JTable tabelaCliente;
	private TabelaClienteModel tabelaClienteModel;
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
					ClienteGUI frame = new ClienteGUI();
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
	public ClienteGUI(JFrame frame, boolean modal, JTable tabelaCliente, TabelaClienteModel tabelaClienteModel, int linha, int acao) {
		
		super(frame, modal);
		
		initComponents();

		this.tabelaCliente = tabelaCliente;
		this.tabelaClienteModel = tabelaClienteModel;
		this.linha = linha;
		this.acao = acao;
		
		limpaTextoCampo();

		desabilitaCheck();

		configuraAcaoCliente();
	}

	private void configuraAcaoCliente() {
		if(this.acao == VariaveisProjeto.INCLUSAO) {
			btnIncluir.setVisible(true);
			btnAlterar.setVisible(false);
			btnExcluir.setVisible(false);
		}
		if(this.acao == VariaveisProjeto.ALTERACAO) {
			btnAlterar.setVisible(true);
			btnExcluir.setVisible(false);
			btnIncluir.setVisible(false);
			buscarCliente();
		}
		if(this.acao == VariaveisProjeto.EXCLUSAO) {
			btnExcluir.setVisible(true);
			btnAlterar.setVisible(false);
			btnIncluir.setVisible(false);
			buscarCliente();
		}
	}

	private void initComponents() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ClienteGUI.class.getResource("/com/projeto/estrutura/imagens/user.png")));
		setTitle("Cadastro de Cliente");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 548, 455);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel lblCodigo = new JLabel("Código:");

		textFieldCodigo = new JTextField();
		textFieldCodigo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					//buscarCliente();
					textFieldNome.requestFocus();
				}
			}
		});
		textFieldCodigo.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				//buscarCliente();
			}
		});
		textFieldCodigo.setColumns(10);

		JLabel lblNome = new JLabel("Nome:");

		textFieldNome = new JTextField();
		textFieldNome.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {

				if(verificaDigitacaoNome()) {
					textFieldNome.requestFocus();
				} else {
					digitacaoNomeValido();
				}
			}
		});
		textFieldNome.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				if(e.getKeyCode() == KeyEvent.VK_ENTER) {

					if(verificaDigitacaoNome()) {
						textFieldNome.requestFocus();
					} else {
						digitacaoNomeValido();
					}
				}
			}
		});
		textFieldNome.setColumns(10);

		JLabel lblEmail = new JLabel("Email:");

		textFieldEmail = new JTextField();
		textFieldEmail.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {

				if(verificaDigitacaoEmail()) {
					textFieldEmail.requestFocus();
				} else {
					digitacaoEmailValido();
				}
			}
		});
		textFieldEmail.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				if(e.getKeyCode() == KeyEvent.VK_ENTER) {

					if(verificaDigitacaoEmail()) {
						textFieldEmail.requestFocus();
					} else {
						digitacaoEmailValido();
					}
				}
			}
		});
		textFieldEmail.setColumns(10);

		JLabel lblCPF = new JLabel("CPF:");

		textFieldCPF = new JTextField();
		textFieldCPF.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {

				if(verificaDigitacaoCPF()) {
					textFieldCPF.requestFocus();
				} else {
					digitacaoCPFValido();
				}
			}
		});
		textFieldCPF.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					if(verificaDigitacaoCPF()) {
						textFieldCPF.requestFocus();
					} else {
						digitacaoCPFValido();
					}
				}
			}
		});
		textFieldCPF.setColumns(10);

		JLabel lblTelefone = new JLabel("Telefone:");

		textFieldTelefone = new JTextField();
		textFieldTelefone.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {

				if(verificaDigitacaoTelefone()) {
					textFieldTelefone.requestFocus();
				} else {
					digitacaoTelefoneValido();
				}				

			}
		});
		textFieldTelefone.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					if(verificaDigitacaoTelefone()) {
						textFieldTelefone.requestFocus();
					} else {
						digitacaoTelefoneValido();
					}
				}
			}
		});
		textFieldTelefone.setColumns(10);

		btnIncluir = new JButton("Incluir");
		btnIncluir.setIcon(new ImageIcon(ClienteGUI.class.getResource("/com/projeto/estrutura/imagens/application_add.png")));
		btnIncluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				incluirCliente();
			}
		});

		btnAlterar = new JButton("Alterar");
		btnAlterar.setIcon(new ImageIcon(ClienteGUI.class.getResource("/com/projeto/estrutura/imagens/application_edit.png")));
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alterarCliente();
			}
		});

		btnExcluir = new JButton("Excluir");
		btnExcluir.setIcon(new ImageIcon(ClienteGUI.class.getResource("/com/projeto/estrutura/imagens/application_delete.png")));
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluirCliente();
			}
		});

		btnSair = new JButton("Sair");
		btnSair.setIcon(new ImageIcon(ClienteGUI.class.getResource("/com/projeto/estrutura/imagens/sair.png")));
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fecharCliente();
			}
		});

		checkNome = new JLabel("");
		checkNome.setIcon(new ImageIcon(ClienteGUI.class.getResource("/com/projeto/estrutura/imagens/ok.png")));

		checkEmail = new JLabel("");
		checkEmail.setIcon(new ImageIcon(ClienteGUI.class.getResource("/com/projeto/estrutura/imagens/ok.png")));

		checkCPF = new JLabel("");
		checkCPF.setIcon(new ImageIcon(ClienteGUI.class.getResource("/com/projeto/estrutura/imagens/ok.png")));

		checkTelefone = new JLabel("");
		checkTelefone.setIcon(new ImageIcon(ClienteGUI.class.getResource("/com/projeto/estrutura/imagens/ok.png")));

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addContainerGap(48, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblCPF)
								.addComponent(lblEmail)
								.addComponent(lblTelefone)
								.addComponent(lblNome)
								.addComponent(lblCodigo)
								.addComponent(btnIncluir))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
										.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
												.addComponent(textFieldCodigo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addGroup(gl_contentPane.createSequentialGroup()
														.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
																.addComponent(textFieldEmail)
																.addComponent(textFieldNome, GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)
																.addComponent(textFieldCPF)
																.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
																		.addGap(31)
																		.addComponent(btnAlterar)
																		.addPreferredGap(ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
																		.addComponent(btnExcluir)
																		.addGap(27)))
														.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
																.addGroup(gl_contentPane.createSequentialGroup()
																		.addGap(11)
																		.addComponent(btnSair))
																.addGroup(gl_contentPane.createSequentialGroup()
																		.addGap(18)
																		.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
																				.addComponent(checkEmail)
																				.addComponent(checkNome)
																				.addComponent(checkCPF))))
														.addGap(15)))
										.addContainerGap(33, Short.MAX_VALUE))
								.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(textFieldTelefone, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(checkTelefone)
										.addGap(206))))
				);
		gl_contentPane.setVerticalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addGap(83)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblCodigo)
								.addComponent(textFieldCodigo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(textFieldNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNome)
								.addComponent(checkNome))
						.addGap(18)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblEmail)
								.addComponent(textFieldEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(checkEmail))
						.addGap(18)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblCPF)
								.addComponent(textFieldCPF, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(checkCPF))
						.addGap(18)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblTelefone)
								.addComponent(textFieldTelefone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(checkTelefone))
						.addGap(94)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnIncluir)
								.addComponent(btnAlterar)
								.addComponent(btnExcluir)
								.addComponent(btnSair))
						.addContainerGap(32, Short.MAX_VALUE))
				);
		contentPane.setLayout(gl_contentPane);
	}

	private void digitacaoNomeValido() {
		status = true;
		mudaStatusCheckNome();
		checkNome.setVisible(true);
		textFieldEmail.requestFocus();

	}

	private boolean verificaDigitacaoNome() {
		boolean toReturn = true;

		if(VariaveisProjeto.digitacaoCampo(textFieldNome.getText())) {
			status = false;
			mudaStatusCheckNome();
			return toReturn;
		}

		toReturn = false;
		return toReturn;
	}

	private void mudaStatusCheckNome() {
		checkNome.setVisible(true);

		if(status == false) {
			checkNome.setIcon(new ImageIcon(ClienteGUI.class.getResource("/com/projeto/estrutura/imagens/iconFechar.png")));
		} else {
			checkNome.setIcon(new ImageIcon(ClienteGUI.class.getResource("/com/projeto/estrutura/imagens/ok.png")));
		}

	}

	private void digitacaoEmailValido() {
		status = true;
		mudaStatusCheckEmail();
		checkEmail.setVisible(true);
		textFieldCPF.requestFocus();

	}

	private boolean verificaDigitacaoEmail() {
		boolean toReturn = true;

		if(VariaveisProjeto.digitacaoCampo(textFieldEmail.getText())) {
			status = false;
			mudaStatusCheckEmail();
			return toReturn;
		}

		toReturn = false;
		return toReturn;
	}

	private void mudaStatusCheckEmail() {
		checkEmail.setVisible(true);

		if(status == false) {
			checkEmail.setIcon(new ImageIcon(ClienteGUI.class.getResource("/com/projeto/estrutura/imagens/iconFechar.png")));
		} else {
			checkEmail.setIcon(new ImageIcon(ClienteGUI.class.getResource("/com/projeto/estrutura/imagens/ok.png")));
		}

	}

	private void digitacaoCPFValido() {
		status = true;
		mudaStatusCheckCPF();
		checkCPF.setVisible(true);
		textFieldTelefone.requestFocus();

	}

	private boolean verificaDigitacaoCPF() {
		boolean toReturn = true;

		if(VariaveisProjeto.digitacaoCampo(textFieldCPF.getText())) {
			status = false;
			mudaStatusCheckCPF();
			return toReturn;
		}

		toReturn = false;
		return toReturn;
	}

	private void mudaStatusCheckCPF() {
		checkCPF.setVisible(true);

		if(status == false) {
			checkCPF.setIcon(new ImageIcon(ClienteGUI.class.getResource("/com/projeto/estrutura/imagens/iconFechar.png")));
		} else {
			checkCPF.setIcon(new ImageIcon(ClienteGUI.class.getResource("/com/projeto/estrutura/imagens/ok.png")));
		}

	}

	private void digitacaoTelefoneValido() {
		status = true;
		mudaStatusCheckTelefone();
		checkTelefone.setVisible(true);

	}

	private boolean verificaDigitacaoTelefone() {
		boolean toReturn = true;

		if(VariaveisProjeto.digitacaoCampo(textFieldTelefone.getText())) {
			status = false;
			mudaStatusCheckTelefone();
			return toReturn;
		}

		toReturn = false;
		return toReturn;
	}

	private void mudaStatusCheckTelefone() {
		checkTelefone.setVisible(true);

		if(status == false) {
			checkTelefone.setIcon(new ImageIcon(ClienteGUI.class.getResource("/com/projeto/estrutura/imagens/iconFechar.png")));
		} else {
			checkTelefone.setIcon(new ImageIcon(ClienteGUI.class.getResource("/com/projeto/estrutura/imagens/ok.png")));
		}

	}

	private void desabilitaCheck() {
		checkNome.setVisible(false);
		checkEmail.setVisible(false);
		checkCPF.setVisible(false);
		checkTelefone.setVisible(false);
	}

	protected void incluirCliente() {
		Integer toReturn = 0;
		
		Cliente cliente = pegarDadosCliente();
		System.out.println(cliente.toString());

		ClienteService clienteService = new ClienteService();

		toReturn = clienteService.save(cliente);
		
		erroDigitacao(toReturn);
		
		if(toReturn == VariaveisProjeto.ERRO_INCLUSAO) {
			showMensagem("Erro na Inclusão do Registro, verifique com seu administrador",
							"Erro", JOptionPane.ERROR_MESSAGE);
		}
		if(toReturn == VariaveisProjeto.INCLUSAO_REALIZADA) {
			showMensagem("Inclusão do Registro realizada com sucesso!",
							"OK", JOptionPane.OK_OPTION);
			limpaTextoCampo();
			tabelaClienteModel.fireTableDataChanged();
			cliente = new Cliente();
		}
	}

	protected void alterarCliente() {
		Integer toReturn = 0;
		
		Cliente cliente = pegarDadosCliente();

		ClienteService clienteService = new ClienteService();

		toReturn = clienteService.update(cliente);
		
		erroDigitacao(toReturn);
		
		if(toReturn == VariaveisProjeto.ERRO_ALTERACAO) {
			showMensagem("Erro na Alteração do Registro, verifique com seu administrador",
							"Erro", JOptionPane.ERROR_MESSAGE);
		}
		if(toReturn == VariaveisProjeto.ALTERACAO_REALIZADA) {
			showMensagem("Alteração do Registro realizada com sucesso!",
							"OK", JOptionPane.OK_OPTION);
			
			tabelaClienteModel.fireTableDataChanged();
			limpaTextoCampo();
			cliente = new Cliente();
		}
	}

	private void erroDigitacao(Integer toReturn) {
		if(toReturn == VariaveisProjeto.CLIENTE_CPF) {
			status = false;
			mudaStatusCheckCPF();
			showMensagem("Erro na digitação no CPF, verifique!", "Erro", JOptionPane.ERROR_MESSAGE);
		}
		if(toReturn == VariaveisProjeto.CLIENTE_NOME) {
			status = false;
			mudaStatusCheckNome();
			showMensagem("Erro na digitação no NOME, verifique!", "Erro", JOptionPane.ERROR_MESSAGE);
		}
		if(toReturn == VariaveisProjeto.CLIENTE_EMAIL) {
			status = false;
			mudaStatusCheckEmail();
			showMensagem("Erro na digitação no EMAIL, verifique!", "Erro", JOptionPane.ERROR_MESSAGE);
		}
		if(toReturn == VariaveisProjeto.CLIENTE_TELEFONE) {
			status = false;
			mudaStatusCheckTelefone();
			showMensagem("Erro na digitação no TELEFONE, verifique!", "Erro", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	protected void excluirCliente() {
		Integer toReturn = 0;
		
		Cliente cliente = pegarDadosCliente();

		ClienteService clienteService = new ClienteService();

		toReturn = clienteService.remove(cliente);
		
		if(toReturn == VariaveisProjeto.ERRO_EXCLUSAO) {
			showMensagem("Erro na Exclusão do Registro, verifique com seu administrador",
							"Erro", JOptionPane.ERROR_MESSAGE);
		}
		if(toReturn == VariaveisProjeto.EXCLUSAO_REALIZADA) {
			showMensagem("Exclusão do Registro realizada com sucesso!",
							"OK", JOptionPane.OK_OPTION);
			
			limpaTextoCampo();
			tabelaClienteModel.fireTableDataChanged();
			cliente = new Cliente();
		}
	}

	private void fecharCliente() {
		dispose();
	}

	private void buscarCliente() {
		Cliente cliente = new Cliente();
		
		/*
		if(VariaveisProjeto.digitacaoCampo(textFieldCodigo.getText())) {
			textFieldCodigo.requestFocus();
			return;
		}
		
		Integer id = Integer.valueOf(textFieldCodigo.getText());
		*/
		
		cliente = tabelaClienteModel.getCliente(this.linha);
		
		System.out.println(cliente.toString());
		
		//ClienteService clienteService = new ClienteService();
		
		//cliente = clienteService.findById(cliente.getId());

		textFieldCodigo.setText(String.valueOf(cliente.getId()));
		textFieldNome.setText(cliente.getNome());
		textFieldEmail.setText(cliente.getEmail());
		textFieldCPF.setText(cliente.getCpf());
		textFieldTelefone.setText(cliente.getTelefone());
	}

	public Cliente pegarDadosCliente() {
		Cliente cliente = new Cliente();

		if(VariaveisProjeto.digitacaoCampo(textFieldCodigo.getText())) {
			textFieldCodigo.requestFocus();
		}
		if(VariaveisProjeto.digitacaoCampo(textFieldCodigo.getText()) == false) {
			cliente.setId(Integer.valueOf(textFieldCodigo.getText()));
		}
		cliente.setNome(textFieldNome.getText());
		cliente.setEmail(textFieldEmail.getText());
		cliente.setCpf(textFieldCPF.getText());
		cliente.setTelefone(textFieldTelefone.getText());
		return cliente;
	}

	private void limpaTextoCampo() {
		textFieldCodigo.setText(VariaveisProjeto.LIMPA_CAMPO);
		textFieldNome.setText(VariaveisProjeto.LIMPA_CAMPO);
		textFieldEmail.setText(VariaveisProjeto.LIMPA_CAMPO);
		textFieldTelefone.setText(VariaveisProjeto.LIMPA_CAMPO);

	}
	
	private void showMensagem(String mensagem, String status, int option) {
		JOptionPane.showMessageDialog(null, mensagem, status, option);
		
	}
}
