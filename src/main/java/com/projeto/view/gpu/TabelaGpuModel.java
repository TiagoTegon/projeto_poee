package com.projeto.view.gpu;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.projeto.model.models.Cpu;
import com.projeto.model.models.Gpu;

public class TabelaGpuModel extends AbstractTableModel {

	private static final long serialVersionUID = 3305444231657153802L;

	private final String colunas[] = {"Código", "Fabricante", "Marca", "Modelo", "Preço", "Qtd Estoque", "Tipo Memória", "VRAM"};
	
	private static final int CODIGO = 0;
	private static final int FABRICANTE = 1;
	private static final int MARCA = 2;
	private static final int MODELO = 3;
	private static final int PRECO = 4;
	private static final int QTDESTOQUE = 5;
	private static final int TIPOMEMORIA = 6;
	private static final int VRAM = 7;
	
	private List<Gpu> listaGpu;
	
	public List<Gpu> getListaGpu(){
		return listaGpu;
	}
	
	public void setListaGpu(List<Gpu> listaGpu) {
		this.listaGpu = listaGpu;
	}
	
	public Gpu getGpu(int rowIndex) {
		return getListaGpu().get(rowIndex);
	}
	
	public void saveGpu(Gpu gpu) {
		getListaGpu().add(gpu);
		fireTableRowsInserted(getRowCount()-1, getColumnCount()-1);
	}
	
	public void updateGpu(Gpu gpu, int rowIndex) {
		getListaGpu().set(rowIndex, gpu);
		fireTableRowsInserted(rowIndex, rowIndex);
	}
	
	public void removeGpu(int rowIndex) {
		getListaGpu().remove(rowIndex);
		fireTableRowsInserted(rowIndex, rowIndex);
	}
	
	public void removeAll() {
		getListaGpu().clear();
		fireTableDataChanged();
	}
	
	@Override
	public int getRowCount() {
		return getListaGpu().size();
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
		
		Gpu gpu = getListaGpu().get(rowIndex);
		
		switch(columnIndex) {
		case CODIGO:
			return gpu.getId();
			
		case FABRICANTE:
			return gpu.getFabricante();
			
		case MARCA:
			return gpu.getMarca();
			
		case MODELO:
			return gpu.getModelo();

		case PRECO:
			return gpu.getPreco();
			
		case QTDESTOQUE:
			return gpu.getQtd_estoque();
			
		case TIPOMEMORIA:
			return gpu.getTipo_memoria();
			
		case VRAM:
			return gpu.getVram();
			
		default:
			return gpu;
		}
	}
	
	public Class<?> getColumnClass(int columnIndex){
		
		switch(columnIndex) {
		case CODIGO:
			return Integer.class;
			
		case FABRICANTE:
			return String.class;
			
		case MARCA:
			return String.class;
			
		case MODELO:
			return String.class;

		case PRECO:
			return Integer.class;
			
		case QTDESTOQUE:
			return Integer.class;
			
		case TIPOMEMORIA:
			return String.class;
			
		case VRAM:
			return Integer.class;
			
		default:
			return null;
		}
	}
	
	public String[] getColunas() {
		return colunas;
	}

}
