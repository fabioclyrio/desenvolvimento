package br.com.elo7.exceptions;

public class AgendamentoServiceException extends Exception {

	private static final long serialVersionUID = 1L;

	public AgendamentoServiceException(@SuppressWarnings("rawtypes") Class classe, String detailMessage) {
		super("FALHA: " + classe.getSimpleName() + "\n " + detailMessage);
	}
	
	@Override
	public String getMessage() {
		return super.getMessage();
	}
	
}
