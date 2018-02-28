package br.ufrpe.easyestacionamento.negocio;

import java.util.List;
import br.ufrpe.easyestacionamento.dados.RepositorioClientes;
import br.ufrpe.easyestacionamento.negocio.beans.Cliente;
import br.ufrpe.easyestacionamento.negocio.exception.ClienteJaExisteException;
import br.ufrpe.easyestacionamento.negocio.exception.CpfNaoExisteException;

public class CadastroCliente {
	private RepositorioClientes<Cliente> repositorio;

	public CadastroCliente() {
		this.repositorio = RepositorioClientes.getInstance();
	}

	public void cadastrar(Cliente c) throws ClienteJaExisteException {
		if (!this.existe(c.getCpf())) {
			this.repositorio.cadastrar(c);
		} else {
			throw new ClienteJaExisteException("Já existe um cliente cadastrado com esse cpf.");
		}
	}

	public void remover(Long cpf) throws CpfNaoExisteException {
		Cliente c = this.repositorio.buscar(cpf);
		if (c != null && c.getCpf().equals(cpf)) {
			this.repositorio.remover(cpf);
		} else {
			throw new CpfNaoExisteException("Cpf não existe, cliente não encontrado!");
		}
	}

	public void atualizar(Cliente novo, Long cpf) throws CpfNaoExisteException {
		if (this.existe(cpf)) {
			this.repositorio.atualizar(novo, cpf);
		} else {
			throw new CpfNaoExisteException("Cpf não existe, cliente não encontrado!");
		}
	}

	public List<Cliente> listarClientes() {
		return repositorio.listar();
	}

	public Cliente procurar(Long cpf) {
		return this.repositorio.buscar(cpf);
	}

	private boolean existe(Long cpf) {
		return this.repositorio.existe(cpf);
	}

}
