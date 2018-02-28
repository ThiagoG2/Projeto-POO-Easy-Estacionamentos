package br.ufrpe.easyestacionamento.gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import br.ufrpe.easyestacionamento.negocio.Fachada;
import br.ufrpe.easyestacionamento.negocio.beans.Funcionario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

public class TelaListarFuncionariosController implements Initializable {
	Fachada fachada = Fachada.getInstance();

	@FXML
	private ListView<Funcionario> listarFuncionarios;

	private List<Funcionario> arrayListFuncionarios = new ArrayList<>();
	private ObservableList<Funcionario> funcionarios;

	public void listarFuncionarios() {
		arrayListFuncionarios.addAll(fachada.listarFuncionarios());
		funcionarios = FXCollections.observableArrayList(arrayListFuncionarios);
		listarFuncionarios.getItems().addAll(funcionarios);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		listarFuncionarios();
	}

}
