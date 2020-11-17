package com.projeto.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.projeto.estrutura.util.VariaveisProjeto;
import com.projeto.model.models.Cliente;
import com.projeto.model.service.ClienteService;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class ClienteGUI extends JFrame {

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

	/**
	 * Launch the application.
	 */
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
	}

	/**
	 * Create the frame.
	 */
	public ClienteGUI() {
		setTitle("Cadastro de Cliente");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 548, 579);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblCodigo = new JLabel("Código:");
		
		textFieldCodigo = new JTextField();
		textFieldCodigo.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				buscarCliente();
			}
		});
		textFieldCodigo.setColumns(10);
		
		JLabel lblNome = new JLabel("Nome:");
		
		textFieldNome = new JTextField();
		textFieldNome.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email:");
		
		textFieldEmail = new JTextField();
		textFieldEmail.setColumns(10);
		
		JLabel lblCPF = new JLabel("CPF:");
		
		textFieldCPF = new JTextField();
		textFieldCPF.setColumns(10);
		
		JLabel lblTelefone = new JLabel("Telefone:");
		
		textFieldTelefone = new JTextField();
		textFieldTelefone.setColumns(10);
		
		btnIncluir = new JButton("Incluir");
		btnIncluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				incluirCliente();
			}
		});
		
		btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alterarCliente();
			}
		});
		
		btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluirCliente();
			}
		});
		
		btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fecharCliente();
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(47)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnIncluir)
							.addGap(18)
							.addComponent(btnAlterar)
							.addGap(18)
							.addComponent(btnExcluir)
							.addGap(18)
							.addComponent(btnSair)
							.addGap(468))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblCodigo)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(textFieldCodigo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(lblCPF)
										.addComponent(lblEmail)
										.addComponent(lblTelefone)
										.addComponent(lblNome))
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(textFieldTelefone, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
											.addComponent(textFieldEmail, GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE)
											.addComponent(textFieldNome, GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE)
											.addComponent(textFieldCPF, GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE)))))
							.addGap(472))))
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
						.addComponent(lblNome))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEmail)
						.addComponent(textFieldEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCPF)
						.addComponent(textFieldCPF, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTelefone)
						.addComponent(textFieldTelefone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(128)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnIncluir)
						.addComponent(btnAlterar)
						.addComponent(btnExcluir)
						.addComponent(btnSair))
					.addContainerGap(130, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
	
	protected void incluirCliente() {
		Cliente cliente = pegarDadosCliente();
		System.out.println(cliente.toString());
		
		ClienteService clienteService = new ClienteService();
		
		clienteService.save(cliente);
	}
	
	protected void alterarCliente() {
		Cliente cliente = pegarDadosCliente();
		
		ClienteService clienteService = new ClienteService();
		
		clienteService.update(cliente);
	}
	
	protected void excluirCliente() {
		Cliente cliente = pegarDadosCliente();
		
		ClienteService clienteService = new ClienteService();
		
		clienteService.remove(cliente);
	}
	
	private void fecharCliente() {
		dispose();
	}
	
	private void buscarCliente() {
		Cliente cliente = new Cliente();
		
		if(VariaveisProjeto.digitacaoCampo(textFieldCodigo.getText())) {
			textFieldCodigo.requestFocus();
			return;
		}
		
		Integer id = Integer.valueOf(textFieldCodigo.getText());
		
		ClienteService clienteService = new ClienteService();
		cliente = clienteService.findById(id);
		
		textFieldNome.setText(cliente.getNome());
		textFieldEmail.setText(cliente.getEmail());
		textFieldCPF.setText(cliente.getCpf());
		textFieldTelefone.setText(cliente.getTelefone());
	}
	
	public Cliente pegarDadosCliente() {
		Cliente cliente = new Cliente();
		
		if(!"".equals(textFieldCodigo.getText())) {
			cliente.setId(Integer.valueOf(textFieldCodigo.getText()));
		}
		
		cliente.setNome(textFieldNome.getText());
		cliente.setEmail(textFieldEmail.getText());
		cliente.setCpf(textFieldCPF.getText());
		cliente.setTelefone(textFieldTelefone.getText());
		return cliente;
	}
}
