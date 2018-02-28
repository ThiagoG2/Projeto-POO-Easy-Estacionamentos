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
import javafx.scene.control.ListView;

public class TelaListarEstacionamentos implements Initializable {
	Fachada fachada = Fachada.getInstance();

	@FXML
	private ListView<Estacionamento> listarEstacionamentos;

	private List<Estacionamento> arrayListEstacionamentos = new ArrayList<>();
	private ObservableList<Estacionamento> estacionamentos;

	public void listarEstacionamentos() {
		arrayListEstacionamentos.addAll(fachada.listarEstacionamentos());
		estacionamentos = FXCollections.observableArrayList(arrayListEstacionamentos);
		listarEstacionamentos.getItems().addAll(estacionamentos);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		listarEstacionamentos();
	}

}
