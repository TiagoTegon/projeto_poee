package com.projeto.view.cliente;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.projeto.model.models.Cliente;

public class TabelaClienteModel extends AbstractTableModel {

	private static final long serialVersionUID = -445245047816016658L;

	private final String colunas[] = {"Código", "Nome", "CPF", "Email", "Telefone"};
	
	private static final int CODIGO = 0;
	private static final int NOME = 1;
	private static final int CPF = 2;
	private static final int EMAIL = 3;
	private static final int TELEFONE = 4;
	
	private List<Cliente> listaCliente;
	
	public List<Cliente> getListaCliente(){
		return listaCliente;
	}
	
	public void setListaCliente(List<Cliente> listaCliente) {
		this.listaCliente = listaCliente;
	}
	
	public Cliente getCliente(int rowIndex) {
		return getListaCliente().get(rowIndex);
	}
	
	public void saveCliente(Cliente cliente) {
		getListaCliente().add(cliente);
		fireTableRowsInserted(getRowCount()-1, getColumnCount()-1);
	}
	
	public void updateCliente(Cliente cliente, int rowIndex) {
		getListaCliente().set(rowIndex, cliente);
		fireTableRowsInserted(rowIndex, rowIndex);
	}
	
	public void removeCliente(int rowIndex) {
		getListaCliente().remove(rowIndex);
		fireTableRowsInserted(rowIndex, rowIndex);
	}
	
	public void removeAll() {
		getListaCliente().clear();
		fireTableDataChanged();
	}
	
	@Override
	public int getRowCount() {
		return getListaCliente().size();
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
		
		Cliente cliente = getListaCliente().get(rowIndex);
		
		switch(columnIndex) {
		case CODIGO:
			return cliente.getId();
			
		case NOME:
			return cliente.getNome();
			
		case CPF:
			return cliente.getCpf();
			
		case EMAIL:
			return cliente.getEmail();
		
		case TELEFONE:
			return cliente.getTelefone();
		
		default:
			return cliente;
		}
	}
	
	public Class<?> getColumnClass(int columnIndex){
		
		switch(columnIndex) {
		case CODIGO:
			return Integer.class;
			
		case NOME:
			return String.class;
			
		case CPF:
			return String.class;
			
		case EMAIL:
			return String.class;
			
		case TELEFONE:
			return String.class;
		
		default:
			return null;
		}
	}
	
	public String[] getColunas() {
		return colunas;
	}
}
