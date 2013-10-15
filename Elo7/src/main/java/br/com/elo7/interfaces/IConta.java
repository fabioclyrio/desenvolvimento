package br.com.elo7.interfaces;

import java.util.List;

import br.com.elo7.model.Dinheiro;

public interface IConta {

	public Dinheiro obterSaldoAtual();

	public void sacarValor(Dinheiro valor);
	
	public void debitarValor(Dinheiro valor);

	public void depositarValor(Dinheiro valor);
	
	public void adicionarAgendamentoTransferencia(IAgendamentoTransferencia agendamentoTransferencia);
	
	public List<IAgendamentoTransferencia> verificarAgendamentosTransferencias();

}
