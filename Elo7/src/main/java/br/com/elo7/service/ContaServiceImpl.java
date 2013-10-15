package br.com.elo7.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.elo7.exceptions.ServiceException;
import br.com.elo7.interfaces.IConta;
import br.com.elo7.model.AgendamentoTransferencia;

@Service("contaService")
@Transactional
public class ContaServiceImpl implements IContaService {

	protected static Logger logger = Logger.getLogger(ContaServiceImpl.class);

	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	@Override
	public void salvarConta(IConta conta) throws ServiceException {
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(conta);
		} catch (HibernateException e) {
			logger.error("ContaServiceImpl: salvarConta: " + e.getMessage());
			throw new ServiceException(ContaServiceImpl.class,
					"falha salvarConta: " + e.getMessage());
		}
	}

	@Override
	public void atualizarConta(IConta conta) throws ServiceException {
		try {
			sessionFactory.getCurrentSession().update(conta);
		} catch (HibernateException e) {
			logger.error("ContaServiceImpl: atualizarConta: " + e.getMessage());
			throw new ServiceException(ContaServiceImpl.class,
					"falha atualizarConta: " + e.getMessage());
		}
	}

	@Override
	public void salvarAgendamento(AgendamentoTransferencia agendamento)
			throws ServiceException {
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(agendamento);
		} catch (HibernateException e) {
			logger.error("ContaServiceImpl: salvarAgendamento: "
					+ e.getMessage());
			throw new ServiceException(ContaServiceImpl.class,
					"falha salvarAgendamento: " + e.getMessage());
		}

	}

	@Override
	public IConta obterContaPorNumero(String numero) throws ServiceException {
		try {
			return (IConta) sessionFactory.getCurrentSession()
					.createQuery("from Conta c where c.numeroDaConta like ?")
					.setParameter(0, numero).uniqueResult();
		} catch (HibernateException e) {
			logger.error("ContaServiceImpl: obterContaPorNumero: "
					+ e.getMessage());
			throw new ServiceException(ContaServiceImpl.class,
					"falha obterContaPorNumero: " + e.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<IConta> listaTodasContas() throws ServiceException {
		try {
			return (List<IConta>) sessionFactory.getCurrentSession()
					.createQuery("from Conta").list();
		} catch (HibernateException e) {
			logger.error("ContaServiceImpl: listaTodasContas: "
					+ e.getMessage());
			throw new ServiceException(ContaServiceImpl.class,
					"falha listaTodasContas: " + e.getMessage());
		}
	}

}
