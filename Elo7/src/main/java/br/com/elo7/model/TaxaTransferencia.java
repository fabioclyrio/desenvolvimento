package br.com.elo7.model;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.elo7.interfaces.ITaxa;

@Entity
@Table(name = "TaxaTransferencia")
public class TaxaTransferencia implements ITaxa {

	@Id
	@GeneratedValue
	private Integer id;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ID_TAXA")
	private Dinheiro taxa;
	
	public TaxaTransferencia() {
		taxa = new Dinheiro();
	}
	
	@Override
	public void setValorTaxa(BigDecimal valor) {
		getTaxa().setValor(valor);
	}
	
	@Override
	public Dinheiro getTaxa() {
		return taxa;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


}
