package com.projeto.model.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="CPU")
public class Cpu {

	private Integer id;
	private String marca;
	private String modelo;
	private Integer qtd_estoque;
	private Integer preco;
	private Integer nucleos;
	private Integer threads;
	private Integer cache;
	private String velocidade;
	
	private List<Pedido> pedidos;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CPU_ID")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name = "CPU_MARCA", length = 50, nullable = false)
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	
	@Column(name = "CPU_MODELO", length = 50, nullable = false)
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	
	@Column(name = "CPU_QTD_ESTOQUE", nullable = false)
	public Integer getQtd_estoque() {
		return qtd_estoque;
	}
	public void setQtd_estoque(Integer qtd_estoque) {
		this.qtd_estoque = qtd_estoque;
	}
	
	@Column(name = "CPU_PRECO", nullable = false)
	public Integer getPreco() {
		return preco;
	}
	public void setPreco(Integer preco) {
		this.preco = preco;
	}
	
	@Column(name = "CPU_NUCLEOS", nullable = false)
	public Integer getNucleos() {
		return nucleos;
	}
	public void setNucleos(Integer nucleos) {
		this.nucleos = nucleos;
	}
	
	@Column(name = "CPU_THREADS", nullable = false)
	public Integer getThreads() {
		return threads;
	}
	public void setThreads(Integer threads) {
		this.threads = threads;
	}
	
	@Column(name = "CPU_CACHE", nullable = false)
	public Integer getCache() {
		return cache;
	}
	public void setCache(Integer cache) {
		this.cache = cache;
	}
	
	@Column(name = "CPU_VELOCIDADE", nullable = false)
	public String getVelocidade() {
		return velocidade;
	}
	public void setVelocidade(String velocidade) {
		this.velocidade = velocidade;
	}
	
	// Muitos para muitos
	
	@ManyToMany(mappedBy = "cpus")
	public List<Pedido> getPedidos() {
		return pedidos;
	}
	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
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
		Cpu other = (Cpu) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Cpu [id=" + id + ", marca=" + marca + ", modelo=" + modelo + ", qtd_estoque=" + qtd_estoque + ", preco="
				+ preco + ", nucleos=" + nucleos + ", threads=" + threads + ", cache=" + cache + ", velocidade="
				+ velocidade + "]";
	}
	
	
}
