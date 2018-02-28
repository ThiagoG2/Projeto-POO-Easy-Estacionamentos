package br.ufrpe.easyestacionamento.gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import br.ufrpe.easyestacionamento.negocio.Fachada;
import br.ufrpe.easyestacionamento.negocio.beans.Cliente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

public class TelaListarClientesController implements Initializable {
	Fachada fachada = Fachada.getInstance();

	@FXML
	private ListView<Cliente> listarClientes;

	private List<Cliente> arrayListClientes = new ArrayList<>();
	private ObservableList<Cliente> clientes;

	public void listarClientes() {
		arrayListClientes.addAll(fachada.listarClientes());
		clientes = FXCollections.observableArrayList(arrayListClientes);
		listarClientes.getItems().addAll(clientes);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		listarClientes();
	}

}
