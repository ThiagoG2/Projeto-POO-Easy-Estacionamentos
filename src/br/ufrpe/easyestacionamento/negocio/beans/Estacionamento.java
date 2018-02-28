package br.ufrpe.easyestacionamento.negocio.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Estacionamento implements Serializable {
	private static final long serialVersionUID = 8613471677067890655L;
	private String nome;
	private List<RegistroEstacionamento> entradas;
	private double caixa;
	private int vagas;

	public Estacionamento(String nome, double caixa, int vagas) {
		this.nome = nome;
		this.caixa = caixa;
		this.vagas = vagas;
		this.entradas = new ArrayList<>();
	}

	public List<RegistroEstacionamento> getEntradas() {
		return entradas;
	}

	public void setEntradas(RegistroEstacionamento entradas) {
		this.entradas.add(entradas);
	}

	public double getCaixa() {
		return caixa;
	}

	public void setCaixa(double caixa) {
		this.caixa = caixa;
	}

	public int getVagas() {
		return vagas;
	}

	public void setVagas(int vagas) {
		this.vagas = vagas;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		return "Estacionamento: " + nome;
	}

}
