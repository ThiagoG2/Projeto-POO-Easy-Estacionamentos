package br.ufrpe.easyestacionamento.negocio;

import java.util.List;

import br.ufrpe.easyestacionamento.dados.RepositorioEstacionamentos;
import br.ufrpe.easyestacionamento.negocio.beans.Estacionamento;
import br.ufrpe.easyestacionamento.negocio.exception.EstacionamentoJaExisteException;
import br.ufrpe.easyestacionamento.negocio.exception.EstacionamentoNaoExisteException;

public class CadastroEstacionamento {
	private RepositorioEstacionamentos<Estacionamento> repositorio;

	public CadastroEstacionamento() {
		this.repositorio = RepositorioEstacionamentos.getInstance();
	}

	public void abrirEstacionamento(Estacionamento e) throws EstacionamentoJaExisteException {
		if (!this.existe(e.getNome())) {
			this.repositorio.cadastrar(e);
		} else {
			throw new EstacionamentoJaExisteException("Já existe um estacionamento cadastrado com esse nome!");
		}
	}

	public void fecharEstacionamento(String nomeEstacionamento) throws EstacionamentoNaoExisteException {
		if (this.existe(nomeEstacionamento)) {
			this.repositorio.fecharEstacionamento(nomeEstacionamento);
		} else {
			throw new EstacionamentoNaoExisteException("Não existe um estacionamento cadastrado com esse nome!");
		}
	}

	public List<Estacionamento> listarEstacionamentos() {
		return repositorio.listar();
	}

	public int VagasDisponiveis(Estacionamento e) {
		return this.repositorio.getVagas(e);
	}

	public void setVagas(Estacionamento estacionamento, int vagas) {
		this.repositorio.setVagas(estacionamento, vagas);
	}

	private boolean existe(String nomeEstacionamento) {
		return this.repositorio.existe(nomeEstacionamento);
	}

	public Estacionamento buscar(String nomeEstacionamento) {
		return this.repositorio.buscar(nomeEstacionamento);
	}
}
