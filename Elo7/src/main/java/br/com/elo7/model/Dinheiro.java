package br.com.elo7.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "Saldo")
public class Dinheiro {

	@Id
	@GeneratedValue
	private Integer id;

	@Column(name = "SALDO_CONTA", precision = 8, scale = 2, columnDefinition = "DECIMAL(8,2)")
	private BigDecimal valor = new BigDecimal("0.00");

	public Dinheiro() {
		valor = new BigDecimal("0.00");
	}

	public Dinheiro(String valor) {
		this.valor = new BigDecimal(valor).setScale(2, RoundingMode.HALF_EVEN);
	}

	public Dinheiro(BigDecimal valor) {
		this.valor = valor;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public static BigDecimal aplicarPorcentagem(String porcentagem, Dinheiro valorTransferencia) {
		BigDecimal valorPorcentagem = new BigDecimal(porcentagem).setScale(2, RoundingMode.HALF_EVEN);
		return valorTransferencia.getValor().multiply(valorPorcentagem).setScale(2, RoundingMode.HALF_EVEN);
	}

	public static BigDecimal subtrairValor(Dinheiro valor1, Dinheiro valor2) {
		if (valor1.getValor().compareTo(new BigDecimal(0)) > 0) {
			return valor1.getValor().subtract(valor2.getValor()).setScale(2, RoundingMode.HALF_EVEN);
		} else {
			return valor1.getValor();
		}
	}

	public static BigDecimal somarValor(Dinheiro valor1, Dinheiro valor2) {
		return valor1.getValor().add(valor2.getValor()).setScale(2, RoundingMode.HALF_EVEN);
	}

	public int comparar(Dinheiro valor) {
		return this.getValor().compareTo(valor.getValor());
	}
	
	public static boolean valorForMaior(Dinheiro valor1, Dinheiro valor2) {
		return valor1.getValor().compareTo(valor2.getValor()) == 1;
	}
	
	@Transient
	private String valorFormatado;
	public String getValorFormatado() {
		if(this.getValor() != null){
			DecimalFormat formatter = new java.text.DecimalFormat("\u00A4 #,###,##0.00"); 
			return formatter.format(this.getValor());
		} else {
			return "0,00";
		}
	}

}
