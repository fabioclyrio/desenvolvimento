package br.com.elo7.calculo;

import java.math.BigDecimal;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.elo7.exceptions.SaldoInsuficienteException;
import br.com.elo7.exceptions.ValorInsuficienteException;
import br.com.elo7.factory.TipoOperacaoFactory;
import br.com.elo7.interfaces.IAgendamentoTransferencia;
import br.com.elo7.interfaces.IConta;
import br.com.elo7.interfaces.ITipoOperacaoTransferencia;
import br.com.elo7.model.AgendamentoTransferencia;
import br.com.elo7.model.Conta;
import br.com.elo7.model.Dinheiro;
import br.com.elo7.utils.DataUtils;

public class CalcularPorcentagemTransferenciaTest {

	private AgendamentoTransferencia transferencia;
	private IConta origem;
	private IConta destino;
	

	@Before
	public void iniciarTeste() {
		origem = criarContaOrigem();
		destino = criarContaDestino();
		transferencia = criarAgendamentoTransferencia();
	}
	
	@Test
	public void deveCalcularValorTotalCorreto() throws ValorInsuficienteException, SaldoInsuficienteException{
		
		//criando valor para transferencia
		transferencia.setValor(new Dinheiro("120.00"));
		IAgendamentoTransferencia iTransferencia = transferencia;
		
		BigDecimal saldoAtual = origem.obterSaldoAtual().getValor();
		BigDecimal valorEsperadoSaldoFinal = new BigDecimal("1074.40");
		BigDecimal valorEsperadoTaxaMaisTransferencia = new BigDecimal("125.60");
		BigDecimal valorEsperadoTaxa = new BigDecimal("5.60");
		
		//aplicando taxa
		iTransferencia.aplicarTaxaTransferencia();
		BigDecimal valorSaldoPosDebito = origem.obterSaldoAtual().getValor();
		
		//confere saldo após aplicar taxa e debitar valor transferencia 
		Assert.assertTrue(valorSaldoPosDebito.equals(valorEsperadoSaldoFinal));
		
		//Confere o valor resultante da taxa aplicada somada ao valor da transferencia
		BigDecimal valorSaldoMenosTaxa = saldoAtual.subtract(valorSaldoPosDebito);
		Assert.assertTrue(valorSaldoMenosTaxa.equals(valorEsperadoTaxaMaisTransferencia));
		
		//confere o total de taxa aplicada
		BigDecimal valorTaxaCalculada = valorSaldoMenosTaxa.subtract(new BigDecimal("120.00"));
		Assert.assertTrue(valorTaxaCalculada.equals(valorEsperadoTaxa));
		
	}
	
	@Test(expected = SaldoInsuficienteException.class)
	public void calcularValorTransferenciaComSaldoInsuficiente() throws ValorInsuficienteException, SaldoInsuficienteException{
		
		//criando valor de transferencia maior que saldo atual
		transferencia.setValor(new Dinheiro("1250.00"));
		IAgendamentoTransferencia iTransferencia = transferencia;
		
		BigDecimal saldoAtual = origem.obterSaldoAtual().getValor();
		BigDecimal valorEsperadoSaldoFinal = new BigDecimal("1074.40");
		BigDecimal valorEsperadoTaxaMaisTransferencia = new BigDecimal("125.60");
		BigDecimal valorEsperadoTaxa = new BigDecimal("5.60");
		
		//aplicando taxa
		iTransferencia.aplicarTaxaTransferencia();
		BigDecimal valorSaldoPosDebito = origem.obterSaldoAtual().getValor();
		
		//confere saldo após aplicar taxa e debitar valor transferencia 
		Assert.assertFalse(valorSaldoPosDebito.equals(valorEsperadoSaldoFinal));
		
		//Confere o valor resultante da taxa aplicada somada ao valor da transferencia
		BigDecimal valorSaldoMenosTaxa = saldoAtual.subtract(valorSaldoPosDebito);
		Assert.assertFalse(valorSaldoMenosTaxa.equals(valorEsperadoTaxaMaisTransferencia));
		
		//confere o total de taxa aplicada
		BigDecimal valorTaxaCalculada = valorSaldoMenosTaxa.subtract(new BigDecimal("120.00"));
		Assert.assertFalse(valorTaxaCalculada.equals(valorEsperadoTaxa));
	}
	
	@Test(expected = ValorInsuficienteException.class)
	public void gerarExceptionValorTransferenciaNull() throws ValorInsuficienteException, SaldoInsuficienteException{
		
		//criando valor de transferencia null
		transferencia.setValor(null);
		IAgendamentoTransferencia iTransferencia = transferencia;
		
		//aplicando taxa
		iTransferencia.aplicarTaxaTransferencia();
		
	}
	
	@Test(expected = NullPointerException.class)
	public void gerarExceptionTipoOperacaoNull() throws ValorInsuficienteException, SaldoInsuficienteException{
		
		//criando valor de transferencia null
		transferencia.setValor(new Dinheiro("120.00"));
		
		//setando null no tipo de operação
		transferencia.setOperacaoTransferencia(null);
		
		IAgendamentoTransferencia iTransferencia = transferencia;
		
		//aplicando taxa
		iTransferencia.aplicarTaxaTransferencia();
		
	}
	
	@After
	public void finalizarTeste(){
		origem = null;
		destino = null;
		transferencia = null;
	}
	
	private IConta criarContaOrigem(){
		Conta contaOrigem = new Conta();
		contaOrigem.setNumeroDaConta("12345-1");
		contaOrigem.depositarValor(new Dinheiro("1200.00"));
		return contaOrigem;
	}
	
	private IConta criarContaDestino(){
		Conta contaOrigem = new Conta();
		contaOrigem.setNumeroDaConta("12345-2");
		contaOrigem.depositarValor(new Dinheiro("1400.00"));
		return contaOrigem;
	}
	
	private AgendamentoTransferencia criarAgendamentoTransferencia(){
		AgendamentoTransferencia transferencia = new AgendamentoTransferencia();
		transferencia.setOrigem(origem);
		transferencia.setDestino(destino);
		transferencia.setDataAgendamento(DataUtils.adicionarData(5));
		
		ITipoOperacaoTransferencia tipoOperacao = TipoOperacaoFactory.getTipoOperacao("A", transferencia);
		transferencia.setOperacaoTransferencia(tipoOperacao);
		
		return transferencia;
	}
	

}
