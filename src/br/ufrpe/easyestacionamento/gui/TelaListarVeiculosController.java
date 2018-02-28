package br.ufrpe.easyestacionamento.gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import br.ufrpe.easyestacionamento.negocio.Fachada;
import br.ufrpe.easyestacionamento.negocio.beans.Veiculo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

public class TelaListarVeiculosController implements Initializable {
	Fachada fachada = Fachada.getInstance();

	@FXML
	private ListView<Veiculo> listarVeiculos;

	private List<Veiculo> arrayListVeiculos = new ArrayList<>();
	private ObservableList<Veiculo> veiculos;

	public void listarVeiculos() {
		arrayListVeiculos.addAll(fachada.listarVeiculos());
		veiculos = FXCollections.observableArrayList(arrayListVeiculos);
		listarVeiculos.getItems().addAll(veiculos);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		listarVeiculos();
	}
}
