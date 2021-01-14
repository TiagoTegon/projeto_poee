package com.projeto.estrutura.util;

import java.util.Objects;

public class VariaveisProjeto {

	
	public static final String PERSISTENCE_UNIT_NAME = "projeto";
	
	//Configurações para a geração de relatório
	public static final String DIRETORIO_RELATORIO = "reports/";
	public static final String SUFIXO_RELATORIO_COMPILADO = ".jasper";
	public static final String SUFIXO_RELATORIO_FONTE = ".jrxml";
	public static final String UPLOAD_DIR = "/nds/upload";
	
	public static final Integer INCLUSAO = 1;
	public static final Integer ALTERACAO = 2;
	public static final Integer EXCLUSAO = 3;
	public static final Integer CONSULTA = 4;
	
	public static final Integer ERRO_INCLUSAO = 10;
	public static final Integer ERRO_ALTERACAO = 20;
	public static final Integer ERRO_EXCLUSAO = 30;
	
	public static final Integer INCLUSAO_REALIZADA = 1;
	public static final Integer ALTERACAO_REALIZADA = 2;
	public static final Integer EXCLUSAO_REALIZADA = 3;
	
	public static final Integer DIGITACAO_OK = 100;
	public static final Integer NOME_CAMPO_VAZIO = 200;
	
	// classe de usuário
	public static final Integer USUARIO_USER_NAME = 201;
	public static final Integer USUARIO_EMAIL = 202;
	public static final Integer USUARIO_PASSWORD = 203;
	
	// classe de departamento
	public static final Integer DEPARTAMENTO_NOME = 300;
	
	// classe de cliente
	public static final Integer CAMPO_VAZIO = 250;
	public static final Integer CLIENTE_CPF = 501;
	public static final Integer CLIENTE_NOME = 502;
	public static final Integer CLIENTE_EMAIL = 503;
	public static final Integer CLIENTE_TELEFONE = 504;
	
	// classe de pedido
	public static final Integer PEDIDO_DATA = 601;
	public static final Integer PEDIDO_PRECO_TOTAL = 602;
	
	// classe de cpu
	public static final Integer CPU_CACHE = 701;
	public static final Integer CPU_MARCA = 702;
	public static final Integer CPU_MODELO = 703;
	public static final Integer CPU_NUCLEOS = 704;
	public static final Integer CPU_PRECO = 705;
	public static final Integer CPU_QTDESTOQUE = 706;
	public static final Integer CPU_THREADS = 707;
	public static final Integer CPU_VELOCIDADE = 708;
	
	// classe  de gpu
	public static final Integer GPU_FABRICANTE = 801;
	public static final Integer GPU_MARCA = 802;
	public static final Integer GPU_MODELO = 803;
	public static final Integer GPU_PRECO = 804;
	public static final Integer GPU_QTDESTOQUE = 805;
	public static final Integer GPU_TIPOMEMORIA = 806;
	public static final Integer GPU_VRAM = 806;
	
	public static final String LIMPA_CAMPO = "";
	
	
	public static boolean digitacaoCampo(Integer texto) {
		
		if (Objects.isNull(texto)) {
			return true;
		}
		
		if("".equals(String.valueOf(texto))) {
			return true;
		}
		
		return false;
	}
	
	public static boolean digitacaoCampo(String texto) {
		
		if (Objects.isNull(texto)) {
			return true;
		}
		
		if("".equals(texto.trim())) {
			return true;
		}

		return false;
	}
	
	public static boolean digitacaoCampo(float texto) {
		
		if (Objects.isNull(texto)) {
			return true;
		}
		
		if("".equals(String.valueOf(texto))) {
			return true;
		}

		return false;
	}
	
	public static Integer converteToInteger(String texto) {
		return Integer.parseInt(texto);
	}
}
