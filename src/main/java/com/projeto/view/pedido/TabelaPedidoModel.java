package com.projeto.view.pedido;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.projeto.model.models.Pedido;

public class TabelaPedidoModel extends AbstractTableModel {
	
	private static final long serialVersionUID = -3858495915822615515L;

	private final String colunas[] = {"Código", "Data", "Preço Total"};
	
	private static final int CODIGO = 0;
	private static final int DATA = 1;
	private static final int PRECO_TOTAL = 2;
	
	private List<Pedido> listaPedido;
	
	public List<Pedido> getListaPedido(){
		return listaPedido;
	}
	
	public void setListaPedido(List<Pedido> listaPedido) {
		this.listaPedido = listaPedido;
	}
	
	public Pedido getPedido(int rowIndex) {
		return getListaPedido().get(rowIndex);
	}
	
	public void savePedido(Pedido pedido) {
		getListaPedido().add(pedido);
		fireTableRowsInserted(getRowCount()-1, getColumnCount()-1);
	}
	
	public void updatePedido(Pedido pedido, int rowIndex) {
		getListaPedido().set(rowIndex, pedido);
		fireTableRowsInserted(rowIndex, rowIndex);
	}
	
	public void removePedido(int rowIndex) {
		getListaPedido().remove(rowIndex);
		fireTableRowsInserted(rowIndex, rowIndex);
	}
	
	public void removeAll() {
		getListaPedido().clear();
		fireTableDataChanged();
	}
	
	@Override
	public int getRowCount() {
		return getListaPedido().size();
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
		
		Pedido pedido = getListaPedido().get(rowIndex);
		
		switch(columnIndex) {
		case CODIGO:
			return pedido.getId();
		
		case DATA:
			return pedido.getData();
			
		case PRECO_TOTAL:
			return pedido.getPreco_total();
		
		default:
			return pedido;
		}
	}
	
	public Class<?> getColumnClass(int columnIndex){
		switch(columnIndex) {
		case CODIGO:
			return Integer.class;
		
		case DATA:
			return String.class;
			
		case PRECO_TOTAL:
			return Integer.class;
		
		default:
			return null;
		}
	}
	
	public String[] getColunas() {
		return colunas;
	}
}
