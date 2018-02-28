package br.ufrpe.easyestacionamento.negocio.exception;

public class ClienteJaExisteException extends Exception {
	public ClienteJaExisteException(String mensagem) {
		super(mensagem);
	}

}
