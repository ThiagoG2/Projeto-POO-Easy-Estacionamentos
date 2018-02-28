package br.ufrpe.easyestacionamento.negocio;

import java.util.List;

import br.ufrpe.easyestacionamento.dados.RepositorioVeiculos;
import br.ufrpe.easyestacionamento.negocio.beans.Veiculo;
import br.ufrpe.easyestacionamento.negocio.exception.PlacaNaoExisteException;
import br.ufrpe.easyestacionamento.negocio.exception.VeiculoJaExisteException;

public class CadastroVeiculo {
	private RepositorioVeiculos<Veiculo> repositorio;

	public CadastroVeiculo() {
		this.repositorio = RepositorioVeiculos.getInstance();
	}

	public void cadastrar(Veiculo v) throws VeiculoJaExisteException {
		if (!this.existe(v.getPlaca())) {
			this.repositorio.cadastrar(v);
		} else {
			throw new VeiculoJaExisteException("Já existe um veículo cadastrado com essa placa.");
		}
	}

	public void remover(String placa) throws PlacaNaoExisteException {
		Veiculo v = this.repositorio.buscar(placa);
		if (v != null && v.getPlaca().equals(placa)) {
			this.repositorio.remover(placa);
		} else {
			throw new PlacaNaoExisteException("Placa não existe, veículo não encontrado para remover!");
		}
	}

	public List<Veiculo> listarVeiculo() {
		return repositorio.listar();
	}

	public void atualizar(Veiculo novo, String placa) throws PlacaNaoExisteException {
		if (this.existe(placa)) {
			this.repositorio.atualizar(novo, placa);
		} else {
			throw new PlacaNaoExisteException("Placa não existe, veículo não encontrado para atualizar!");
		}
	}

	public Veiculo procurar(String placa) {
		return this.repositorio.buscar(placa);
	}

	private boolean existe(String placa) {
		return this.repositorio.existe(placa);
	}

}
