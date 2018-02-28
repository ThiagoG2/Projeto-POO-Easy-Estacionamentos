package br.ufrpe.easyestacionamento.gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import br.ufrpe.easyestacionamento.negocio.Fachada;
import br.ufrpe.easyestacionamento.negocio.beans.Estacionamento;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Alert.AlertType;

public class TelaVisualizarVagasController implements Initializable {
	Fachada fachada = Fachada.getInstance();

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

	@FXML
	private Button btnVisualizarVagasDisponiveis;

	@FXML
	public void visualizarVagasDisponiveis() {
		try {
			Estacionamento estacionamento = listaEstacionamento.getSelectionModel().getSelectedItem();
			Alert alert = new Alert(AlertType.INFORMATION, "Quantidade de vagas disponíveis no estacionamento "
					+ estacionamento.getNome() + ": " + estacionamento.getVagas());
			alert.setTitle("Visualizar vagas disponíveis");
			alert.setHeaderText("Vagas Disponíveis");
			alert.show();
		} catch (NullPointerException npe) {
			Alert alert = new Alert(AlertType.ERROR, "Você não selecionou um estacionamento!");
			alert.show();
		}
	}

}
