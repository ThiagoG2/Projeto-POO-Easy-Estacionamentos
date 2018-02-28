package br.ufrpe.easyestacionamento.dados;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class AbstractRepositorioGenerico<T> implements IRepositorioGenerico<T>, Serializable {

	private static final long serialVersionUID = -7190631046961355897L;
	protected List<T> elements = new ArrayList<>();
	private static AbstractRepositorioGenerico<?> instance;

	public static AbstractRepositorioGenerico<?> getInstance() {
		if (instance == null) {
			instance = lerArquivo();
		}
		return instance;
	}

	protected AbstractRepositorioGenerico() {
		this.elements = new ArrayList<>();
	}

	@Override
	public void cadastrar(T newObj) {
		if (!this.elements.contains(newObj)) {
			this.elements.add(newObj);
			this.salvarArquivo();
		}
	}

	@Override
	public List<T> listar() {
		return Collections.unmodifiableList(this.elements);
	}

	@Override
	public void atualizar(T t, T u) {
		for (int i = 0; i < elements.size(); i++) {
			if (elements.get(i).equals(t)) {
				elements.set(i, u);
			}
		}
	}

	@Override
	public void remover(T t) {
		if (this.elements.contains(t)) {
			this.elements.remove(this.elements.indexOf(t));
			this.salvarArquivo();
		}
	}

	@Override
	public T buscar(T t) {
		T buscar = null;
		if (existe(t)) {
			for (T u : elements) {
				if (t.equals(u)) {
					buscar = u;
				}
			}
		}
		return buscar;
	}

	@Override
	public boolean existe(T t) {
		boolean existe = false;
		for (int i = 0; i < elements.size(); i++) {
			if (this.elements.get(i).equals(t)) {
				existe = true;
			}
		}
		return existe;
	}

	public static AbstractRepositorioGenerico<?> lerArquivo() {
		AbstractRepositorioGenerico<?> instance = null;
		File in = new File("Genericos.dat");
		FileInputStream fis = null;
		ObjectInputStream ois = null;

		try {
			fis = new FileInputStream(in);
			ois = new ObjectInputStream(fis);

			Object o = ois.readObject();
			instance = (AbstractRepositorioGenerico<?>) o;

		} catch (Exception e) {
			instance = AbstractRepositorioGenerico.getInstance();
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
		File out = new File("Genericos.dat");
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
