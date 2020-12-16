package com.projeto.view.menu;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.projeto.main.Login;
import com.projeto.view.cliente.TabelaCliente;
import com.projeto.view.cpu.TabelaCpu;
import com.projeto.view.gpu.TabelaGpu;
import com.projeto.view.pedido.TabelaPedido;
import com.projeto.view.usuario.TabelaUsuario;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Menu extends JFrame {

	private static final long serialVersionUID = 473637651720341513L;
	private JPanel contentPane;
	private JMenuBar menuBar;
	private JMenu arquivo;
	private JMenuItem usuario;
	private JMenuItem logout;

	
	private Login login;
	private JMenu sair;
	private JMenuItem sair_sistema;
	private JMenuItem pedido;
	private JMenuItem cliente;
	private JMenuItem cpu;
	private JMenuItem gpu;
	/**
	 * Launch the application.
	 */
	
	/*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu frame = new Menu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	*/

	/**
	 * Create the frame.
	 */
	public Menu(Login login) {
		this.login = login;
		initComponents();
	}
	private void initComponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 872, 611);
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		arquivo = new JMenu("Arquivo");
		menuBar.add(arquivo);
		
		usuario = new JMenuItem("Usuário");
		usuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TabelaUsuario tabelaUsuario = new TabelaUsuario();
				centralizaForm(tabelaUsuario);
				contentPane.add(tabelaUsuario);
				tabelaUsuario.setVisible(true);
			}
		});
		arquivo.add(usuario);
		
		logout = new JMenuItem("Logout");
		logout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				login.setVisible(true);
				login.setLocationRelativeTo(null);
			}
		});
		
		pedido = new JMenuItem("Pedido");
		pedido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TabelaPedido tabelaPedido = new TabelaPedido();
				centralizaForm(tabelaPedido);
				contentPane.add(tabelaPedido);
				tabelaPedido.setVisible(true);
			}
		});
		arquivo.add(pedido);
		
		cliente = new JMenuItem("Cliente");
		cliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TabelaCliente tabelaCliente = new TabelaCliente();
				centralizaForm(tabelaCliente);
				contentPane.add(tabelaCliente);
				tabelaCliente.setVisible(true);
			}
		});
		arquivo.add(cliente);
		
		cpu = new JMenuItem("CPU");
		cpu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TabelaCpu tabelaCpu = new TabelaCpu();
				centralizaForm(tabelaCpu);
				contentPane.add(tabelaCpu);
				tabelaCpu.setVisible(true);
			}
		});
		arquivo.add(cpu);
		
		gpu = new JMenuItem("GPU");
		gpu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TabelaGpu tabelaGpu = new TabelaGpu();
				centralizaForm(tabelaGpu);
				contentPane.add(tabelaGpu);
				tabelaGpu.setVisible(true);
			}
		});
		arquivo.add(gpu);
		arquivo.add(logout);
		
		sair = new JMenu("Sair");
		menuBar.add(sair);
		
		sair_sistema = new JMenuItem("Sair");
		sair_sistema.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		sair.add(sair_sistema);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGap(0, 846, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGap(0, 562, Short.MAX_VALUE)
		);
		contentPane.setLayout(gl_contentPane);
	}
	
	private void centralizaForm(JInternalFrame frame) {
		Dimension desktopSize = this.getSize();
		Dimension internalFrameSize = frame.getSize();
		frame.setLocation((desktopSize.width - internalFrameSize.width) / 2, (desktopSize.height - internalFrameSize.height) / 2);
	}

}
