package com.projeto.model.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="GPU")
public class Gpu {
	
	private Integer id;
	private String marca;
	private String modelo;
	private Integer qtd_estoque;
	private Integer preco;
	private Integer vram;
	private String tipo_memoria;
	private String fabricante;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "GPU_ID")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name = "GPU_MARCA", length = 50, nullable = false)
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	
	@Column(name = "GPU_MODELO", length = 50, nullable = false)
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	
	@Column(name = "GPU_QTD_ESTOQUE", nullable = false)
	public Integer getQtd_estoque() {
		return qtd_estoque;
	}
	public void setQtd_estoque(Integer qtd_estoque) {
		this.qtd_estoque = qtd_estoque;
	}
	
	@Column(name = "GPU_PRECO", nullable = false)
	public Integer getPreco() {
		return preco;
	}
	public void setPreco(Integer preco) {
		this.preco = preco;
	}
	
	@Column(name = "GPU_VRAM", nullable = false)
	public Integer getVram() {
		return vram;
	}
	public void setVram(Integer vram) {
		this.vram = vram;
	}
	
	@Column(name = "GPU_TIPO_MEMORIA", length = 10, nullable = false)
	public String getTipo_memoria() {
		return tipo_memoria;
	}
	public void setTipo_memoria(String tipo_memoria) {
		this.tipo_memoria = tipo_memoria;
	}
	
	@Column(name = "GPU_FRABICANTE", length = 50, nullable = false)
	public String getFabricante() {
		return fabricante;
	}
	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
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
		Gpu other = (Gpu) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Gpu [id=" + id + ", marca=" + marca + ", modelo=" + modelo + ", qtd_estoque=" + qtd_estoque + ", preco="
				+ preco + ", vram=" + vram + ", tipo_memoria=" + tipo_memoria + ", fabricante=" + fabricante + "]";
	}
	
	
	
}
