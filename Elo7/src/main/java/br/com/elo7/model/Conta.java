package br.com.elo7.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.elo7.interfaces.IAgendamentoTransferencia;
import br.com.elo7.interfaces.IConta;

@Entity
@Table(name = "Conta")
public class Conta implements IConta {

	@Id
	@GeneratedValue
	private Integer id;

	@Column(name = "NUMERO_CONTA")
	private String numeroDaConta;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ID_SALDO")
	private Dinheiro saldoAtual = new Dinheiro();

	@OneToMany(targetEntity = AgendamentoTransferencia.class, mappedBy = "origem", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<IAgendamentoTransferencia> agendamentos = new ArrayList<IAgendamentoTransferencia>();

	@Override
	public Dinheiro obterSaldoAtual() {
		return saldoAtual;
	}

	@Override
	public void sacarValor(Dinheiro valor) {
		saldoAtual.setValor(Dinheiro.subtrairValor(saldoAtual, valor));
	}
	
	@Override
	public void debitarValor(Dinheiro valor) {
		saldoAtual.setValor(Dinheiro.subtrairValor(saldoAtual, valor));
	}

	@Override
	public void depositarValor(Dinheiro valor) {
		saldoAtual.setValor(Dinheiro.somarValor(saldoAtual, valor));
	}
	
	@Override
	public void adicionarAgendamentoTransferencia(IAgendamentoTransferencia agendamentoTransferencia) {
		agendamentos.add(agendamentoTransferencia);
	}

	@Override
	public List<IAgendamentoTransferencia> verificarAgendamentosTransferencias() {
		return getAgendamentos();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNumeroDaConta() {
		return numeroDaConta;
	}

	public void setNumeroDaConta(String numeroDaConta) {
		this.numeroDaConta = numeroDaConta;
	}

	public List<IAgendamentoTransferencia> getAgendamentos() {
		return agendamentos;
	}

}
