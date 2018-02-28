package br.ufrpe.easyestacionamento.dados;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;
import br.ufrpe.easyestacionamento.negocio.beans.Veiculo;

public class RepositorioVeiculos<T> extends AbstractRepositorioGenerico<Veiculo> implements Serializable {

	private static final long serialVersionUID = -29387230815919845L;
	private List<Veiculo> veiculos = this.elements;
	private static RepositorioVeiculos<Veiculo> instance;

	public static RepositorioVeiculos<Veiculo> getInstance() {
		if (instance == null) {
			instance = lerArquivo();
		}
		return instance;
	}

	private RepositorioVeiculos() {
		this.veiculos = this.elements;
	}

	public void remover(String placa) {
		for (int i = 0; i < veiculos.size(); i++) {
			if (this.veiculos.get(i).getPlaca().equals(placa)) {
				this.veiculos.remove(i);
				this.salvarArquivo();
			}
		}
	}

	public Veiculo buscar(String placa) {
		Veiculo veiculoBuscado = null;
		for (Veiculo v : veiculos) {
			if (placa.equals(v.getPlaca())) {
				veiculoBuscado = v;
			}
		}
		return veiculoBuscado;
	}

	public boolean existe(String placa) {
		boolean existe = false;
		for (Veiculo v : this.elements) {
			if (v.getPlaca().equals(placa)) {
				existe = true;
			}
		}
		return existe;
	}

	public void atualizar(Veiculo v, String placa) {
		if (existe(placa)) {
			for (int i = 0; i < veiculos.size(); i++) {
				if (veiculos.get(i).getPlaca().equals(placa)) {
					veiculos.set(i, v);
					this.salvarArquivo();
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	public static RepositorioVeiculos<Veiculo> lerArquivo() {
		RepositorioVeiculos<Veiculo> instance = null;
		File in = new File("Veiculos.dat");
		FileInputStream fis = null;
		ObjectInputStream ois = null;

		try {
			fis = new FileInputStream(in);
			ois = new ObjectInputStream(fis);

			Object o = ois.readObject();
			instance = (RepositorioVeiculos<Veiculo>) o;

		} catch (Exception e) {
			instance = new RepositorioVeiculos<Veiculo>();
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
		File out = new File("Veiculos.dat");
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
