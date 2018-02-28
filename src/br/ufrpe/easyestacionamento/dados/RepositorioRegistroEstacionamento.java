package br.ufrpe.easyestacionamento.dados;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;
import br.ufrpe.easyestacionamento.negocio.beans.RegistroEstacionamento;

public class RepositorioRegistroEstacionamento<T> extends AbstractRepositorioGenerico<RegistroEstacionamento>
		implements Serializable {
	private static final long serialVersionUID = -7790945168495083781L;
	List<RegistroEstacionamento> registros = this.elements;
	private static RepositorioRegistroEstacionamento<RegistroEstacionamento> instance;

	public static RepositorioRegistroEstacionamento<RegistroEstacionamento> getInstance() {
		if (instance == null) {
			instance = lerArquivo();
		}
		return instance;
	}

	private RepositorioRegistroEstacionamento() {
		this.registros = this.elements;
	}

	public RegistroEstacionamento buscar(String placa) {
		RegistroEstacionamento registroBuscado = null;
		for (RegistroEstacionamento r : registros) {
			if (placa.equals(r.getVeiculo().getPlaca())) {
				registroBuscado = r;
			}
		}
		return registroBuscado;
	}

	public boolean existe(String placa) {
		boolean existe = false;
		for (RegistroEstacionamento r : this.elements) {
			if (r.getVeiculo().getPlaca().equals(placa)) {
				existe = true;
			}
		}
		return existe;
	}

	@SuppressWarnings("unchecked")
	public static RepositorioRegistroEstacionamento<RegistroEstacionamento> lerArquivo() {
		RepositorioRegistroEstacionamento<RegistroEstacionamento> instance = null;
		File in = new File("RegistroEstacionamentos.dat");
		FileInputStream fis = null;
		ObjectInputStream ois = null;

		try {
			fis = new FileInputStream(in);
			ois = new ObjectInputStream(fis);

			Object o = ois.readObject();
			instance = (RepositorioRegistroEstacionamento<RegistroEstacionamento>) o;

		} catch (Exception e) {
			instance = new RepositorioRegistroEstacionamento<RegistroEstacionamento>();
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
		File out = new File("RegistroEstacionamentos.dat");
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
