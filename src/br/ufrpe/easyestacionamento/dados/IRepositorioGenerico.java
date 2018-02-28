package br.ufrpe.easyestacionamento.dados;

import java.util.List;

public interface IRepositorioGenerico<T> {
	void cadastrar(T entidade);
	T buscar(T entidade);
	List<T> listar();
	void remover(T entidade);
	void atualizar(T t, T u);
	boolean existe(T entidade);
}