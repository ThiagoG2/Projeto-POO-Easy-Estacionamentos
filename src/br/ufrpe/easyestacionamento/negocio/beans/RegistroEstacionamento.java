package br.ufrpe.easyestacionamento.negocio.beans;

import java.io.Serializable;
import java.time.LocalDateTime;

public class RegistroEstacionamento implements Serializable{
	
	private static final long serialVersionUID = 7680256643873513342L;
	private Veiculo veiculo;
	private LocalDateTime entrada;
	private LocalDateTime saida;

	public RegistroEstacionamento(Veiculo veiculo, LocalDateTime entrada) {
		this.veiculo = veiculo;
		this.entrada = entrada;
	}

	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}

	public LocalDateTime getEntrada() {
		return entrada;
	}

	public void setEntrada(LocalDateTime entrada) {
		this.entrada = entrada;
	}

	public LocalDateTime getSaida() {
		return saida;
	}

	public void setSaida(LocalDateTime saida) {
		this.saida = saida;
	}

	@Override
	public String toString() {
		return "Placa: " + veiculo.getPlaca() + ", Entrada: " + entrada + ", Saída: " + saida;
	}

}
