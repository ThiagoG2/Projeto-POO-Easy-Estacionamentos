package br.ufrpe.easyestacionamento.dados;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import br.ufrpe.easyestacionamento.negocio.beans.Estacionamento;
import br.ufrpe.easyestacionamento.negocio.beans.RegistroEstacionamento;
import br.ufrpe.easyestacionamento.negocio.exception.EstacionamentoErradoException;

public class RepositorioEstacionamentos<T> extends AbstractRepositorioGenerico<Estacionamento> implements Serializable {

	private static final long serialVersionUID = -2941979684801704230L;
	List<Estacionamento> estacionamentos = elements;
	private static RepositorioEstacionamentos<Estacionamento> instance;

	public static RepositorioEstacionamentos<Estacionamento> getInstance() {
		if (instance == null) {
			instance = lerArquivo();
		}
		return instance;
	}

	private RepositorioEstacionamentos() {
		this.estacionamentos = this.elements;
	}

	public void entrada(RegistroEstacionamento registro, Estacionamento estacionamento) {
		if (existe(estacionamento.getNome())) {
			estacionamento.setEntradas(registro);
			this.salvarArquivo();
		}
	}

	public void saida(RegistroEstacionamento r, Estacionamento estacionamento) throws EstacionamentoErradoException {
		boolean verificar = false;
		if (existe(estacionamento.getNome())) {
			for (int i = 0; i < estacionamento.getEntradas().size(); i++) {
				if (r.getEntrada().equals(estacionamento.getEntradas().get(i).getEntrada())) {
					estacionamento.getEntradas().get(i).setSaida(LocalDateTime.now());
					verificar = true;
					this.salvarArquivo();
				}
			}
		}
		if (!verificar) {
			throw new EstacionamentoErradoException("O veículo estacionado não pertence ao estacionamento fornecido");
		}
	}

	public void fecharEstacionamento(String nomeEstacionamento) {
		for (int i = 0; i < estacionamentos.size(); i++) {
			if (this.estacionamentos.get(i).getNome().equals(nomeEstacionamento)) {
				this.estacionamentos.remove(i);
				this.salvarArquivo();
			}
		}
	}

	public Estacionamento buscar(String nomeEstacionamento) {
		Estacionamento estacionamentoBuscado = null;
		for (Estacionamento e : estacionamentos) {
			if (nomeEstacionamento.equals(e.getNome())) {
				estacionamentoBuscado = e;
			}
		}
		return estacionamentoBuscado;
	}

	public boolean existe(String nomeEstacionamento) {
		boolean existe = false;
		for (Estacionamento e : this.elements) {
			if (e.getNome().equals(nomeEstacionamento)) {
				existe = true;
			}
		}
		return existe;
	}

	public int getVagas(Estacionamento e) {
		int vagas = -1;
		if (existe(e.getNome())) {
			return e.getVagas();
		}
		return vagas;
	}

	public void setVagas(Estacionamento estacionamento, int vagas) {
		if (existe(estacionamento.getNome())) {
			estacionamento.setVagas(vagas);
			this.salvarArquivo();
		}
	}

	public void setCaixa(double valorEstacionamento, Estacionamento estacionamento) {
		if (existe(estacionamento.getNome())) {
			estacionamento.setCaixa(estacionamento.getCaixa() + valorEstacionamento);
			this.salvarArquivo();
		}
	}

	@SuppressWarnings("unchecked")
	public static RepositorioEstacionamentos<Estacionamento> lerArquivo() {
		RepositorioEstacionamentos<Estacionamento> instance = null;
		File in = new File("Estacionamentos.dat");
		FileInputStream fis = null;
		ObjectInputStream ois = null;

		try {
			fis = new FileInputStream(in);
			ois = new ObjectInputStream(fis);

			Object o = ois.readObject();
			instance = (RepositorioEstacionamentos<Estacionamento>) o;

		} catch (Exception e) {
			instance = new RepositorioEstacionamentos<Estacionamento>();
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
		File out = new File("Estacionamentos.dat");
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
