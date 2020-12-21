package com.projeto.view.usuario;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.projeto.estrutura.util.VariaveisProjeto;
import com.projeto.model.models.Departamento;
import com.projeto.model.models.Usuario;
import com.projeto.model.service.DepartamentoService;
import com.projeto.model.service.UsuarioService;
import com.projeto.view.departamento.BuscaDepartamento;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

public class UsuarioGUI extends JDialog {

	private static final long serialVersionUID = -2638637223036799312L;
	private JPanel contentPane;
	private JTextField textFieldNome;
	private JTextField textFieldEmail;
	private JButton btnIncluir;
	private JButton btnAlterar;
	private JButton btnExcluir;
	private JRadioButton rdbtnAtivo;
	private JRadioButton rdbtnAdmin;
	private JButton btnSair;
	private JPasswordField passwordFieldSenha;
	private JLabel lblCodigo;
	private JTextField textFieldCodigo;
	
	private JLabel checkNome;
	private JLabel checkEmail;
	private JLabel checkSenha;
	
	private boolean status = true;
	
	private JTable tabelaUsuario;
	private TabelaUsuarioModel tabelaUsuarioModel;
	private int linha = 0;
	private int acao = 0; 
	private JLabel lblDepartamento;
	private JTextField textFieldNomeDepartamento;
	private JButton btnNewButton;
	
	private Departamento departamento;
	
	/**
	 * Launch the application.
	 */
	/*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UsuarioGUI frame = new UsuarioGUI();
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
	public UsuarioGUI(JFrame frame, boolean modal, JTable tabelaUsuario, TabelaUsuarioModel tabelaUsuarioModel, int linha, int acao) {
		
		super(frame, modal);
		
		initComponents();
		
		this.tabelaUsuario = tabelaUsuario;
		this.tabelaUsuarioModel = tabelaUsuarioModel;
		this.linha = linha;
		this.acao = acao;
		
		limpaTextoCampo();
		
		desabilitaCheckCampos();
		
		configuraAcaoUsuario();
	}
	
	private void configuraAcaoUsuario() {
		if(this.acao == VariaveisProjeto.INCLUSAO) {
			btnIncluir.setVisible(true);
			btnAlterar.setVisible(false);
			btnExcluir.setVisible(false);
		}
		if(this.acao == VariaveisProjeto.ALTERACAO) {
			btnAlterar.setVisible(true);
			btnExcluir.setVisible(false);
			btnIncluir.setVisible(false);
			buscarUsuario();
		}
		if(this.acao == VariaveisProjeto.EXCLUSAO) {
			btnExcluir.setVisible(true);
			btnAlterar.setVisible(false);
			btnIncluir.setVisible(false);
			buscarUsuario();
		}
	}

	private void initComponents() {
		setTitle("Cadastro de Usuário");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 635, 393);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
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
		
		JLabel lblSenha = new JLabel("Senha:");
		
		rdbtnAtivo = new JRadioButton("Ativo");
		rdbtnAtivo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					rdbtnAdmin.requestFocus();
				}
			}
		});
		
		rdbtnAdmin = new JRadioButton("Admin");

		
		btnIncluir = new JButton("Incluir");
		btnIncluir.setIcon(new ImageIcon(UsuarioGUI.class.getResource("/com/projeto/estrutura/imagens/application_add.png")));
		btnIncluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				incluirUsuario();
			}
		});
		
		btnAlterar = new JButton("Alterar");
		btnAlterar.setIcon(new ImageIcon(UsuarioGUI.class.getResource("/com/projeto/estrutura/imagens/application_edit.png")));
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alterarUsuario();
			}
		});
		
		btnExcluir = new JButton("Excluir");
		btnExcluir.setIcon(new ImageIcon(UsuarioGUI.class.getResource("/com/projeto/estrutura/imagens/application_delete.png")));
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluirUsuario();
			}
		});
		
		btnSair = new JButton("Sair");
		btnSair.setIcon(new ImageIcon(UsuarioGUI.class.getResource("/com/projeto/estrutura/imagens/sair.png")));
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fecharUsuario();
			}
		});
		
		passwordFieldSenha = new JPasswordField();
		passwordFieldSenha.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if(verificaDigitacaoSenha()) {
					passwordFieldSenha.requestFocus();
				} else {
					digitacaoSenhaValida();
				}
			}
		});
		passwordFieldSenha.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					if(verificaDigitacaoSenha()) {
						passwordFieldSenha.requestFocus();
					} else {
						digitacaoSenhaValida();
					}
				}
			}
		});
		
		lblCodigo = new JLabel("Código:");
		
		textFieldCodigo = new JTextField();
		textFieldCodigo.setEditable(false);
		textFieldCodigo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					//buscarUsuario();
					textFieldNome.requestFocus();
				}
			}
		});
		textFieldCodigo.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				//buscarUsuario();
			}
		});
		textFieldCodigo.setColumns(10);
		
		checkNome = new JLabel("");
		checkNome.setIcon(new ImageIcon(UsuarioGUI.class.getResource("/com/projeto/estrutura/imagens/ok.png")));
		
		checkEmail = new JLabel("");
		checkEmail.setIcon(new ImageIcon(UsuarioGUI.class.getResource("/com/projeto/estrutura/imagens/ok.png")));
		
		checkSenha = new JLabel("");
		checkSenha.setIcon(new ImageIcon(UsuarioGUI.class.getResource("/com/projeto/estrutura/imagens/ok.png")));
		
		lblDepartamento = new JLabel("Departamento:");
		
		textFieldNomeDepartamento = new JTextField();
		textFieldNomeDepartamento.setEditable(false);
		textFieldNomeDepartamento.setColumns(10);
		
		btnNewButton = new JButton("Departamento");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscaDepartamento();
			}
		});
		btnNewButton.setMnemonic(KeyEvent.VK_D);
		btnNewButton.setToolTipText("Buscar Departamento");
		btnNewButton.setIcon(new ImageIcon(UsuarioGUI.class.getResource("/com/projeto/estrutura/imagens/search.png")));
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(72)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblCodigo)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(textFieldCodigo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblEmail)
								.addComponent(lblNome)
								.addComponent(lblSenha))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
										.addComponent(passwordFieldSenha, Alignment.LEADING)
										.addComponent(textFieldEmail, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 396, Short.MAX_VALUE)
										.addComponent(textFieldNome, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 396, Short.MAX_VALUE))
									.addGap(13))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(btnIncluir)
									.addGap(18)
									.addComponent(btnAlterar)
									.addGap(18)
									.addComponent(btnExcluir)
									.addGap(18)
									.addComponent(btnSair))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(rdbtnAtivo)
									.addGap(18)
									.addComponent(rdbtnAdmin)))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(checkNome, GroupLayout.PREFERRED_SIZE, 38, Short.MAX_VALUE)
									.addGap(1))
								.addComponent(checkSenha, 0, 0, Short.MAX_VALUE)
								.addComponent(checkEmail, 0, 0, Short.MAX_VALUE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblDepartamento)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textFieldNomeDepartamento, GroupLayout.PREFERRED_SIZE, 272, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE)))
					.addGap(36))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(24, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblCodigo)
						.addComponent(textFieldCodigo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(17)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNome, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
								.addComponent(textFieldNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblEmail)
								.addComponent(textFieldEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(checkNome)
							.addGap(24)
							.addComponent(checkEmail, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(21)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblSenha)
								.addComponent(passwordFieldSenha, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(18)
							.addComponent(checkSenha, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDepartamento)
						.addComponent(textFieldNomeDepartamento, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton))
					.addGap(38)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(rdbtnAdmin)
						.addComponent(rdbtnAtivo))
					.addGap(27)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnIncluir)
						.addComponent(btnAlterar)
						.addComponent(btnExcluir)
						.addComponent(btnSair))
					.addGap(21))
		);
		contentPane.setLayout(gl_contentPane);
	}
	
	protected void buscaDepartamento() {
		
		departamento = new Departamento();
		
		BuscaDepartamento buscaDepartamento = new BuscaDepartamento(new JFrame(), true);
		buscaDepartamento.setLocationRelativeTo(null);
		buscaDepartamento.setVisible(true);
		
		if(buscaDepartamento.isSelectDepartamento()) {
			DepartamentoService departamentoService = new DepartamentoService();
			departamento = departamentoService.findById(buscaDepartamento.getCodigoDepartamento());
			textFieldNomeDepartamento.setText(departamento.getNome());
		}
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
			checkNome.setIcon(new ImageIcon(UsuarioGUI.class.getResource("/com/projeto/estrutura/imagens/iconFechar.png")));			
		} else {
			checkNome.setIcon(new ImageIcon(UsuarioGUI.class.getResource("/com/projeto/estrutura/imagens/ok.png")));
		}

	}
	
	private void digitacaoEmailValido() {
		status = true;
		mudaStatusCheckEmail();
		checkEmail.setVisible(true);
		passwordFieldSenha.requestFocus();		
		
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
			checkEmail.setIcon(new ImageIcon(UsuarioGUI.class.getResource("/com/projeto/estrutura/imagens/iconFechar.png")));			
		} else {
			checkEmail.setIcon(new ImageIcon(UsuarioGUI.class.getResource("/com/projeto/estrutura/imagens/ok.png")));
		}

	}
	
	private void digitacaoSenhaValida() {
		status = true;
		mudaStatusCheckSenha();
		checkSenha.setVisible(true);
		rdbtnAtivo.requestFocus();		
		
	}
	
	private boolean verificaDigitacaoSenha() {
		
		boolean toReturn = true;
		
		if(VariaveisProjeto.digitacaoCampo(passwordFieldSenha.getText())) {
			status = false;
			mudaStatusCheckSenha();
			return toReturn;
		}
		
		toReturn = false;
		return toReturn;
	}
	
	private void mudaStatusCheckSenha() {
		checkSenha.setVisible(true);
		if(status == false) {
			checkSenha.setIcon(new ImageIcon(UsuarioGUI.class.getResource("/com/projeto/estrutura/imagens/iconFechar.png")));			
		} else {
			checkSenha.setIcon(new ImageIcon(UsuarioGUI.class.getResource("/com/projeto/estrutura/imagens/ok.png")));
		}

	}
	
	private void desabilitaCheckCampos() {
		checkNome.setVisible(false);
		checkEmail.setVisible(false);
		checkSenha.setVisible(false);
	}

	protected void incluirUsuario() {
		Integer toReturn = 0;
		
		Usuario usuario = pegarDadosUsuario();
	
		usuario.setDepartamento(departamento);
		
		UsuarioService usuarioService = new UsuarioService();
		
		toReturn = usuarioService.save(usuario);
		
		erroDigitacao(toReturn);
		
		if(toReturn == VariaveisProjeto.ERRO_INCLUSAO) {
			showMensagem("Erro na Inclusão do Registro, verifique com seu administrador",
					     "Erro", JOptionPane.ERROR_MESSAGE);
		}
		
		if(toReturn == VariaveisProjeto.INCLUSAO_REALIZADA) {
			showMensagem("Inclusão do Registro realizada com sucesso!", 
					     "OK", JOptionPane.OK_OPTION);
			
			limpaTextoCampo();
			tabelaUsuarioModel.fireTableDataChanged();
			usuario = new Usuario();
		}
		
		
	}

	protected void alterarUsuario() {
		Integer toReturn = 0;
		
		Usuario usuario = pegarDadosUsuario();
		
		usuario.setDepartamento(departamento);
			
		UsuarioService usuarioService = new UsuarioService();
		
		toReturn = usuarioService.update(usuario);

		
		erroDigitacao(toReturn);
		
		if(toReturn == VariaveisProjeto.ERRO_ALTERACAO) {
			showMensagem("Erro na Alteração do Registro, verifique com seu administrador",
					     "Erro", JOptionPane.ERROR_MESSAGE);
		}
		
		if(toReturn == VariaveisProjeto.ALTERACAO_REALIZADA) {
			showMensagem("Alteração do Registro realizada com sucesso!",
					     "OK", JOptionPane.OK_OPTION);
			
			tabelaUsuarioModel.fireTableDataChanged();
			
			limpaTextoCampo();
			usuario = new Usuario();
		}
		
	}

	private void erroDigitacao(Integer toReturn) {
		if(toReturn == VariaveisProjeto.USUARIO_USER_NAME) {
			status = false;
			mudaStatusCheckNome();
			showMensagem("Erro na digitação do Nome, verifique!", "Erro", JOptionPane.ERROR_MESSAGE);
		}
		
		if(toReturn == VariaveisProjeto.USUARIO_EMAIL) {
			status = false;
			mudaStatusCheckNome();
			showMensagem("Erro na digitação do Email, verifique!", "Erro", JOptionPane.ERROR_MESSAGE);
		}
		
		if(toReturn == VariaveisProjeto.USUARIO_PASSWORD) {
			status = false;
			mudaStatusCheckNome();
			showMensagem("Erro na digitação da Senha, verifique!", "Erro", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	protected void excluirUsuario() {
		Integer toReturn = 0;
		
		Usuario usuario = pegarDadosUsuario();
		
		UsuarioService usuarioService = new UsuarioService();
		
		toReturn = usuarioService.remove(usuario);	
		
		if(toReturn == VariaveisProjeto.ERRO_EXCLUSAO) {
			showMensagem("Erro na Exclusão do Registro, verifique com seu administrador",
					     "Erro", JOptionPane.ERROR_MESSAGE);
		}
		
		if(toReturn == VariaveisProjeto.EXCLUSAO_REALIZADA) {
			showMensagem("Exclusão do Registro realizada com sucesso!",
					     "OK", JOptionPane.OK_OPTION);
				
			limpaTextoCampo();
			tabelaUsuarioModel.fireTableDataChanged();
			usuario = new Usuario();
		}
	}
	
	private void fecharUsuario() {
		dispose();
	}
	
	private void buscarUsuario() {
		Usuario usuario = new Usuario();
		
		usuario = tabelaUsuarioModel.getUsuario(this.linha);
		
		textFieldCodigo.setText(String.valueOf(usuario.getId()));
		textFieldNome.setText(usuario.getUsername());
		textFieldEmail.setText(usuario.getEmail());
		passwordFieldSenha.setText(usuario.getPassword());
		
		textFieldNomeDepartamento.setText(usuario.getDepartamento().getNome());
		
		if(usuario.isAdmin()) {
			rdbtnAdmin.setSelected(true);
		}
		
		if(usuario.isAtivo()) {
			rdbtnAtivo.setSelected(true);
		}
		
	}
	
	@SuppressWarnings("deprecation")
	public Usuario pegarDadosUsuario() {
		
		Usuario usuario = new Usuario();
		
		if(VariaveisProjeto.digitacaoCampo(textFieldCodigo.getText())) {
			textFieldCodigo.requestFocus();
		}
		
		if(VariaveisProjeto.digitacaoCampo(textFieldCodigo.getText()) == false) {
			usuario.setId(Integer.valueOf(textFieldCodigo.getText()));
		}
		
		usuario.setUsername(textFieldNome.getText());
		usuario.setEmail(textFieldEmail.getText());
		usuario.setPassword(passwordFieldSenha.getText());
		usuario.setDepartamento(departamento);
		
		if(rdbtnAtivo.isSelected()) {
			usuario.setAtivo(true);
		} else {
			usuario.setAtivo(false);
		}
		
		if(rdbtnAdmin.isSelected()) {
			usuario.setAdmin(true);
		} else {
			usuario.setAdmin(false);
		}
		return usuario;
	}
	
	private void limpaTextoCampo() {
		textFieldCodigo.setText(VariaveisProjeto.LIMPA_CAMPO);
		textFieldNome.setText(VariaveisProjeto.LIMPA_CAMPO);
		textFieldEmail.setText(VariaveisProjeto.LIMPA_CAMPO);
		passwordFieldSenha.setText(VariaveisProjeto.LIMPA_CAMPO);
		textFieldNomeDepartamento.setText(VariaveisProjeto.LIMPA_CAMPO);
		rdbtnAtivo.setSelected(false);
		rdbtnAdmin.setSelected(false);
	}
	
	private void showMensagem(String mensagem, String status, int option) {
		JOptionPane.showMessageDialog(null,	mensagem, status, option);
	}
}
