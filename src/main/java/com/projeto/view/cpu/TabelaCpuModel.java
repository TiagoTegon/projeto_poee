package com.projeto.view.cpu;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.projeto.model.models.Cpu;

public class TabelaCpuModel extends AbstractTableModel {

	private static final long serialVersionUID = 3037762408287002L;
	
	private final String colunas[] = {"Código", "Cache", "Marca"," Modelo", "Núcleos", "Preço", "Qtd Estoque", "Threads", "Velocidade"};

	private static final int CODIGO = 0;
	private static final int CACHE = 1;
	private static final int MARCA = 2;
	private static final int MODELO = 3;
	private static final int NUCLEOS = 4;
	private static final int PRECO = 5;
	private static final int QTDESTOQUE = 6;
	private static final int THREADS = 7;
	private static final int VELOCIDADE = 8;
	
	private List<Cpu> listaCpu;
	
	public List<Cpu> getListaCpu(){
		return listaCpu;
	}
	
	public void setListaCpu(List<Cpu> listaCpu) {
		this.listaCpu = listaCpu;
	}
	
	public Cpu getCpu(int rowIndex) {
		return getListaCpu().get(rowIndex);
	}
	
	public void saveCpu(Cpu cpu) {
		getListaCpu().add(cpu);
		fireTableRowsInserted(getRowCount()-1, getColumnCount()-1);
	}
	
	public void updateCpu(Cpu cpu, int rowIndex) {
		getListaCpu().set(rowIndex, cpu);
		fireTableRowsInserted(rowIndex, rowIndex);
	}
	
	public void removeCpu(int rowIndex) {
		getListaCpu().remove(rowIndex);
		fireTableRowsInserted(rowIndex, rowIndex);
	}
	
	public void removeAll() {
		getListaCpu().clear();
		fireTableDataChanged();
	}
	
	@Override
	public int getRowCount() {
		return getListaCpu().size();
	}

	@Override
	public int getColumnCount() {
		return getColunas().length;
	}
	
	public String getColumnName(int columnIndex) {
		return colunas[columnIndex];
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		
		Cpu cpu = getListaCpu().get(rowIndex);
		
		switch(columnIndex) {
		case CODIGO:
			return cpu.getId();
			
		case CACHE:
			return cpu.getCache();
			
		case MARCA:
			return cpu.getMarca();
			
		case MODELO:
			return cpu.getModelo();
			
		case NUCLEOS:
			return cpu.getNucleos();
			
		case PRECO:
			return cpu.getPreco();
			
		case QTDESTOQUE:
			return cpu.getQtd_estoque();
			
		case THREADS:
			return cpu.getThreads();
			
		case VELOCIDADE:
			return cpu.getVelocidade();
			
		default:
			return cpu;
		}
	}
	
	public Class<?> getColumnClass(int columnIndex){

		switch(columnIndex) {
		case CODIGO:
			return Integer.class;
			
		case CACHE:
			return Integer.class;
			
		case MARCA:
			return String.class;
			
		case MODELO:
			return String.class;
			
		case NUCLEOS:
			return Integer.class;
			
		case PRECO:
			return Integer.class;
			
		case QTDESTOQUE:
			return Integer.class;
			
		case THREADS:
			return Integer.class;
			
		case VELOCIDADE:
			return String.class;
			
		default: 
			return null;
		}
	}
	
	public String[] getColunas() {
		return colunas;
	}
}
