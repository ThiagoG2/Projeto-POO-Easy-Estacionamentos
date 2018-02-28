package br.ufrpe.easyestacionamento.dados;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;
import br.ufrpe.easyestacionamento.negocio.beans.Cliente;

public class RepositorioClientes<T> extends AbstractRepositorioGenerico<Cliente> implements Serializable {

	private static final long serialVersionUID = 3213315673632386582L;
	List<Cliente> clientes = this.elements;
	private static RepositorioClientes<Cliente> instance;

	public static RepositorioClientes<Cliente> getInstance() {
		if (instance == null) {
			instance = lerArquivo();
		}
		return instance;
	}

	private RepositorioClientes() {
		this.clientes = this.elements;
	}

	public void remover(Long cpf) {
		for (int i = 0; i < clientes.size(); i++) {
			if (this.clientes.get(i).getCpf().equals(cpf)) {
				this.clientes.remove(i);
				this.salvarArquivo();
			}
		}
	}

	public Cliente buscar(Long cpf) {
		Cliente clienteBuscado = null;
		for (Cliente c : clientes) {
			if (cpf.equals(c.getCpf())) {
				clienteBuscado = c;
			}
		}
		return clienteBuscado;
	}

	public boolean existe(Long cpf) {
		boolean existe = false;
		for (Cliente c : this.elements) {
			if (c.getCpf().equals(cpf)) {
				existe = true;
			}
		}
		return existe;
	}

	public void atualizar(Cliente c, Long cpf) {
		if (existe(cpf)) {
			for (int i = 0; i < clientes.size(); i++) {
				if (clientes.get(i).getCpf().equals(cpf)) {
					clientes.set(i, c);
					this.salvarArquivo();
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	public static RepositorioClientes<Cliente> lerArquivo() {
		RepositorioClientes<Cliente> instance = null;
		File in = new File("Clientes.dat");
		FileInputStream fis = null;
		ObjectInputStream ois = null;

		try {
			fis = new FileInputStream(in);
			ois = new ObjectInputStream(fis);

			Object o = ois.readObject();
			instance = (RepositorioClientes<Cliente>) o;

		} catch (Exception e) {
			instance = new RepositorioClientes<Cliente>();
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
		File out = new File("Clientes.dat");
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
