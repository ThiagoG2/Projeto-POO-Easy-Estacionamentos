package br.ufrpe.easyestacionamento.dados;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;
import br.ufrpe.easyestacionamento.negocio.beans.Funcionario;

public class RepositorioFuncionarios<T> extends AbstractRepositorioGenerico<Funcionario> implements Serializable {
	private static final long serialVersionUID = 3213315673632386582L;
	List<Funcionario> funcionarios = this.elements;
	private static RepositorioFuncionarios<Funcionario> instance;

	public static RepositorioFuncionarios<Funcionario> getInstance() {
		if (instance == null) {
			instance = lerArquivo();
		}
		return instance;
	}

	private RepositorioFuncionarios() {
		this.funcionarios = this.elements;
	}

	public void remover(Long cpf) {
		for (int i = 0; i < funcionarios.size(); i++) {
			if (this.funcionarios.get(i).getCpf().equals(cpf)) {
				this.funcionarios.remove(i);
			}
		}
	}

	public Funcionario buscar(Long cpf) {
		Funcionario funcionarioBuscado = null;
		if (existeCPF(cpf)) {
			for (Funcionario f : funcionarios) {
				if (cpf.equals(f.getCpf())) {
					funcionarioBuscado = f;
				}
			}
		}
		return funcionarioBuscado;
	}

	public boolean autenticarLogin(String login, String senha) {
		for (Funcionario f : funcionarios) {
			if (login.equals(f.getLogin()) && senha.equals(f.getSenha())) {
				return true;
			}
		}
		return false;
	}

	public boolean existeCPF(Long cpf) {
		boolean existe = false;
		for (Funcionario f : this.elements) {
			if (f.getCpf().equals(cpf)) {
				existe = true;
			}
		}
		return existe;
	}

	public boolean existeLogin(String login) {
		boolean existe = false;
		for (Funcionario f : this.elements) {
			if (f.getLogin().equals(login)) {
				existe = true;
			}
		}
		return existe;
	}

	public void atualizar(Funcionario f, Long cpf) {
		if (existeCPF(cpf)) {
			for (int i = 0; i < funcionarios.size(); i++) {
				if (funcionarios.get(i).getCpf().equals(cpf)) {
					funcionarios.set(i, f);
					this.salvarArquivo();
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	public static RepositorioFuncionarios<Funcionario> lerArquivo() {
		RepositorioFuncionarios<Funcionario> instance = null;
		File in = new File("Funcionarios.dat");
		FileInputStream fis = null;
		ObjectInputStream ois = null;

		try {
			fis = new FileInputStream(in);
			ois = new ObjectInputStream(fis);

			Object o = ois.readObject();
			instance = (RepositorioFuncionarios<Funcionario>) o;

		} catch (Exception e) {
			instance = new RepositorioFuncionarios<Funcionario>();
		} finally {
			if (ois != null) {
				try {
					ois.close();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		}
		return instance;

	}

	public void salvarArquivo() {
		File out = new File("Funcionarios.dat");
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;

		try {
			fos = new FileOutputStream(out);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(instance);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (oos != null) {
				try {
					oos.close();
				} catch (IOException e) {

				}
			}
		}
	}
}
