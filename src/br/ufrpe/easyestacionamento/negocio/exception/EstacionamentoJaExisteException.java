package br.ufrpe.easyestacionamento.negocio.exception;

public class EstacionamentoJaExisteException extends Exception {
	public EstacionamentoJaExisteException(String mensagem) {
		super(mensagem);
	}

}
