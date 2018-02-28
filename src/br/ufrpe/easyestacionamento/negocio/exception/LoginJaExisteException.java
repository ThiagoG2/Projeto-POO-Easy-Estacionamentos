package br.ufrpe.easyestacionamento.negocio.exception;

public class LoginJaExisteException extends Exception {
	public LoginJaExisteException(String mensagem) {
		super(mensagem);
	}

}
