package com.projeto.view.cliente;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import com.projeto.estrutura.util.VariaveisProjeto;
import com.projeto.estrutura.util.imagem.ImageFilter;
import com.projeto.estrutura.util.imagem.ImagePreview;
import com.projeto.model.models.Cliente;
import com.projeto.model.models.Foto;
import com.projeto.model.service.ClienteService;
import com.projeto.model.service.LocalFotoStorageService;

import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;

import javax.swing.border.BevelBorder;
import java.awt.Dimension;

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
	
	private String nomeFoto;
	private JLabel lblIconFoto;
	private JButton btnAddFoto;
	private JButton btnExcluirFoto;

	
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
		setBounds(100, 100, 832, 455);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel lblCodigo = new JLabel("Código:");

		textFieldCodigo = new JTextField();
		textFieldCodigo.setEditable(false);
		textFieldCodigo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					textFieldNome.requestFocus();
				}
			}
		});
		textFieldCodigo.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
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
		
		lblIconFoto = new JLabel("");
		lblIconFoto.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		
		btnAddFoto = new JButton("");
		btnAddFoto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				carregarFoto();
			}
		});
		btnAddFoto.setToolTipText("Carregar Foto");
		btnAddFoto.setIcon(new ImageIcon(ClienteGUI.class.getResource("/com/projeto/estrutura/imagens/iconfinder_icon-camera_211634.png")));
		
		btnExcluirFoto = new JButton("");
		btnExcluirFoto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluirFoto();
			}
		});
		btnExcluirFoto.setToolTipText("Excluir Foto");
		btnExcluirFoto.setIcon(new ImageIcon(ClienteGUI.class.getResource("/com/projeto/estrutura/imagens/iconFechar.png")));

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(197)
					.addComponent(btnIncluir)
					.addGap(26)
					.addComponent(btnAlterar)
					.addGap(18)
					.addComponent(btnExcluir)
					.addGap(18)
					.addComponent(btnSair)
					.addContainerGap(227, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(45)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblCPF)
						.addComponent(lblEmail)
						.addComponent(lblTelefone)
						.addComponent(lblNome)
						.addComponent(lblCodigo))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addComponent(textFieldCodigo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addComponent(textFieldEmail, 257, 257, 257)
									.addComponent(textFieldNome, GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)
									.addComponent(textFieldCPF, 257, 257, 257))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(18)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(checkEmail)
										.addComponent(checkNome)
										.addComponent(checkCPF)))))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(textFieldTelefone, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(checkTelefone)
							.addGap(211)))
					.addGap(82)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(lblIconFoto, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnAddFoto, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnExcluirFoto, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
					.addGap(56))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(82)
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
								.addComponent(checkTelefone)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(97)
							.addComponent(lblIconFoto, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(btnAddFoto)
								.addComponent(btnExcluirFoto))))
					.addGap(85)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAlterar)
						.addComponent(btnIncluir)
						.addComponent(btnExcluir)
						.addComponent(btnSair))
					.addContainerGap(32, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}

	protected void carregarFoto() {
		
		JFileChooser fc = new JFileChooser();
		fc.addChoosableFileFilter(new ImageFilter());
		fc.setAcceptAllFileFilterUsed(false);
		fc.setAccessory(new ImagePreview(fc));
		
		int returnVal = fc.showDialog(lblIconFoto, "Anexar");
		
		if(lblIconFoto.getIcon() != null) {
			excluirFoto();
		}
		
		if(returnVal == JFileChooser.APPROVE_OPTION) {
			try {
				File file = fc.getSelectedFile();
				FileInputStream fin = new FileInputStream(file);
				BufferedImage img = ImageIO.read(fin);
				ImageIcon icon = new ImageIcon(img);
				lblIconFoto.setIcon(icon);
				lblIconFoto.setHorizontalAlignment(SwingConstants.CENTER);
				LocalFotoStorageService localFotoStorageService = new LocalFotoStorageService();
				Foto foto = new Foto();
				foto.setNomeArquivo(file.getName());
				foto.setInputStream(fin);
				foto.setFile(file);
				nomeFoto = localFotoStorageService.armazenar(foto);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	protected void excluirFoto() {
		Cliente cliente = tabelaClienteModel.getCliente(this.linha);
		nomeFoto = cliente.getFoto();
		LocalFotoStorageService localFotoStorageService = new LocalFotoStorageService();
		localFotoStorageService.remover(nomeFoto);
		lblIconFoto.setIcon(null);
		lblIconFoto.revalidate();
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
			tabelaClienteModel.saveCliente(cliente);
			tabelaCliente.setModel(tabelaClienteModel);
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
			
			tabelaClienteModel.updateCliente(cliente, this.linha);
			tabelaCliente.setModel(tabelaClienteModel);
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
		
		cliente = tabelaClienteModel.getCliente(this.linha);

		textFieldCodigo.setText(String.valueOf(cliente.getId()));
		textFieldNome.setText(cliente.getNome());
		textFieldEmail.setText(cliente.getEmail());
		textFieldCPF.setText(cliente.getCpf());
		textFieldTelefone.setText(cliente.getTelefone());
		nomeFoto = cliente.getFoto();
		
		if(!Objects.isNull(nomeFoto)) {
			LocalFotoStorageService localFotoStorageService = new LocalFotoStorageService();
			String fileInput = localFotoStorageService.recuperar(nomeFoto);
			File file = new File(fileInput);
			FileInputStream fis;
			try {
				fis = new FileInputStream(file);
				BufferedImage img = ImageIO.read(fis);
				ImageIcon imagem = new ImageIcon(img);
				lblIconFoto.setIcon(imagem);
				lblIconFoto.setHorizontalAlignment(SwingConstants.CENTER);
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
		
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
		cliente.setFoto(nomeFoto);
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
