package com.projeto.model.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="PEDIDO")
public class Pedido {

	private Integer id;
	private String data;
	private Integer preco_total;
	
	private Cliente cliente;
	private List<Cpu> cpus;
	private List<Gpu> gpus;
	
	
	public Pedido() {
	}

	public Pedido(Integer id, String data, Integer preco_total) {
		this.id = id;
		this.data = data;
		this.preco_total = preco_total;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="PEDIDO_ID")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name="PEDIDO_DATA", length = 60, nullable = false)
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	
	@Column(name="PEDIDO_PRECO_TOTAL", nullable = false)
	public Integer getPreco_total() {
		return preco_total;
	}
	public void setPreco_total(Integer preco_total) {
		this.preco_total = preco_total;
	}

	// Muitos para um
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CLIENTE_ID", nullable = false)
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	// Muitos para muitos
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "PEDIDO_CPU",
					joinColumns = @JoinColumn(name = "PEDIDO_ID"),
					inverseJoinColumns = @JoinColumn(name = "CPU_ID"))
	public List<Cpu> getCpus() {
		return cpus;
	}

	public void setCpus(List<Cpu> cpus) {
		this.cpus = cpus;
	}

	// Muitos para muitos
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "PEDIDO_GPU",
					joinColumns = @JoinColumn(name = "PEDIDO_ID"),
					inverseJoinColumns = @JoinColumn(name = "GPU_ID"))	
	public List<Gpu> getGpus() {
		return gpus;
	}

	public void setGpus(List<Gpu> gpus) {
		this.gpus = gpus;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pedido other = (Pedido) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Pedido [id=" + id + ", data=" + data + ", preco_total=" + preco_total + "]";
	}
	
	
}
