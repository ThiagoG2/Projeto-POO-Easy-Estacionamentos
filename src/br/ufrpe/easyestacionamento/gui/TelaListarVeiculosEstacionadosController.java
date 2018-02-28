package br.ufrpe.easyestacionamento.gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import br.ufrpe.easyestacionamento.negocio.Fachada;
import br.ufrpe.easyestacionamento.negocio.beans.Estacionamento;
import br.ufrpe.easyestacionamento.negocio.beans.RegistroEstacionamento;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;

public class TelaListarVeiculosEstacionadosController implements Initializable {
	Fachada fachada = Fachada.getInstance();

	@FXML
	private ListView<RegistroEstacionamento> listarVeiculosEstacionados;
	private List<RegistroEstacionamento> arrayListEstacionados;
	private ObservableList<RegistroEstacionamento> estacionados;

	@FXML
	public void listarVeiculosEstacionados() {
		try {
			listarVeiculosEstacionados.getItems().clear();
			arrayListEstacionados = new ArrayList<>();
			Estacionamento estacionamento = listaEstacionamento.getSelectionModel().getSelectedItem();
			for (int i = 0; i < estacionamento.getEntradas().size(); i++) {
				if (estacionamento.getEntradas().get(i).getSaida() == null) {
					arrayListEstacionados.add(estacionamento.getEntradas().get(i));
				}
			}
			estacionados = FXCollections.observableArrayList(arrayListEstacionados);
			listarVeiculosEstacionados.getItems().addAll(estacionados);

		} catch (NullPointerException npe) {
			Alert alert = new Alert(AlertType.ERROR, "Você não selecionou um estacionamento!");
			alert.show();
		}
	}

	// Listando os estacionamentos no combobox
	@FXML
	private ComboBox<Estacionamento> listaEstacionamento;
	private List<Estacionamento> listarEstacionamentos = new ArrayList<>();
	private ObservableList<Estacionamento> obsLista;

	@FXML
	public void listarEstacionamentos() {
		listarEstacionamentos.addAll(fachada.listarEstacionamentos());
		obsLista = FXCollections.observableArrayList(listarEstacionamentos);

		listaEstacionamento.getItems().addAll(obsLista);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		listarEstacionamentos();
	}

}
