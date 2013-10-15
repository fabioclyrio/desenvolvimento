package br.com.elo7.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import br.com.elo7.exceptions.SaldoInsuficienteException;
import br.com.elo7.exceptions.ValorInsuficienteException;
import br.com.elo7.interfaces.IAgendamentoTransferencia;
import br.com.elo7.interfaces.IConta;
import br.com.elo7.interfaces.ITaxa;
import br.com.elo7.interfaces.ITipoOperacaoTransferencia;

@Entity
@Table(name = "AgendamentoTransferencia")
public class AgendamentoTransferencia implements IAgendamentoTransferencia {

	@Id
	@GeneratedValue
	private Integer id;

	@ManyToOne(targetEntity = Conta.class, fetch = FetchType.EAGER)
	@JoinColumn(name="ID_CONTA", referencedColumnName="id")
	@Fetch(FetchMode.JOIN)
	private IConta origem;

	@OneToOne(targetEntity = Conta.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "ID_CONTA_DESTINO")
	private IConta destino;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ID_VALOR")
	private Dinheiro valor;

	@Column(name = "DATA_AGENDAMENTO")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataAgendamento;

	@Column(name = "TIPO_OPERACAO")
	@Enumerated(EnumType.STRING)
	private TipoOperacao tipoOperacao;

	@OneToOne(targetEntity = TaxaTransferencia.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "ID_TAXA")
	private ITaxa taxas;
	
	public AgendamentoTransferencia() {
		taxas = new TaxaTransferencia();
		valor = new Dinheiro();
	}
	
	@Transient 
	ITipoOperacaoTransferencia operacaoTransferencia;
	public ITipoOperacaoTransferencia getOperacaoTransferencia() {
		return operacaoTransferencia;
	}
	public void setOperacaoTransferencia(ITipoOperacaoTransferencia operacaoTransferencia) {
		this.operacaoTransferencia = operacaoTransferencia;
	}

	@Override
	public void aplicarTaxaTransferencia() throws ValorInsuficienteException, SaldoInsuficienteException {
		if(operacaoTransferencia != null){
			if(Dinheiro.valorForMaior(this.getValor(), new Dinheiro("0.00"))){
				if(Dinheiro.valorForMaior(origem.obterSaldoAtual(), this.getValor())){
					origem.debitarValor(operacaoTransferencia.calcularTaxaTransferencia(this));
					origem.debitarValor(getValor());
				} else {
					throw new SaldoInsuficienteException(AgendamentoTransferencia.class, "falha aplicarTaxaTransferencia");
				}
			} else {
				throw new ValorInsuficienteException(AgendamentoTransferencia.class, "falha aplicarTaxaTransferencia");
			}
		} else {
			throw new NullPointerException("falha aplicarTaxaTransferencia");
		}
	}

	@Override
	public void setTipoOperacao(TipoOperacao tipoOperacao) {
		this.tipoOperacao = tipoOperacao;
	}

	public TipoOperacao getTipoOperacao() {
		return tipoOperacao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@JsonIgnore
	public IConta getOrigem() {
		return origem;
	}

	public void setOrigem(IConta origem) {
		this.origem = origem;
	}

	@JsonIgnore
	public IConta getDestino() {
		return destino;
	}

	public void setDestino(IConta destino) {
		this.destino = destino;
	}

	public Dinheiro getValor() {
		return valor;
	}

	public void setValor(Dinheiro valor) {
		if(valor != null){
			this.valor = valor;
		} else {
			this.valor = new Dinheiro();
		}
	}

	public Date getDataAgendamento() {
		return dataAgendamento;
	}

	public void setDataAgendamento(Date dataAgendamento) {
		this.dataAgendamento = dataAgendamento;
	}

	public ITaxa getTaxas() {
		return taxas;
	}

	public void setTaxas(ITaxa taxas) {
		this.taxas = taxas;
	}
	
}
