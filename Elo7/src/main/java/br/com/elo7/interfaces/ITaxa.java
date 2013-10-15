package br.com.elo7.interfaces;

import java.math.BigDecimal;

import br.com.elo7.model.Dinheiro;

public interface ITaxa {

	public void setValorTaxa(BigDecimal valor);
	public Dinheiro getTaxa();
	
}
