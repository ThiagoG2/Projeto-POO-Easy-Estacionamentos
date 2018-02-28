package br.ufrpe.easyestacionamento.gui;

import java.io.IOException;

import br.ufrpe.easyestacionamento.negocio.Fachada;
import br.ufrpe.easyestacionamento.negocio.beans.Cliente;
import br.ufrpe.easyestacionamento.negocio.beans.Funcionario;
import br.ufrpe.easyestacionamento.negocio.beans.Veiculo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class TelasBuscarController {
	Fachada fachada = Fachada.getInstance();

	// Buscar Clientes
	@FXML
	private Button btnBuscarCliente;
	@FXML
	private TextField cpf;

	@FXML
	public void buscarCliente(ActionEvent event) throws IOException {
		try {
			Long cPF = Long.parseLong(cpf.getText().toString());
			Cliente cliente = fachada.buscarCliente(cPF);
			if (cliente != null) {
				Alert alert = new Alert(AlertType.INFORMATION, "" + cliente);
				alert.setTitle("Cliente Buscado");
				alert.setHeaderText("Cliente encontrado");
				alert.showAndWait();
			} else {
				Alert alert = new Alert(AlertType.INFORMATION, "Informe o CPF válido de um cliente!");
				alert.setTitle("Falha ao encontrar");
				alert.setHeaderText("Cliente não encontrado");
				alert.showAndWait();
			}
		} catch (NumberFormatException nfe) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Falha ao encontrar");
			alert.setHeaderText("CPF inválido");
			alert.setContentText("Informe apenas os números do CPF!");
			alert.showAndWait();
		} finally {
			limparCamposBuscarCliente();
		}

	}

	private void limparCamposBuscarCliente() {
		cpf.setText("");
	}

	// Buscar Veículos
	@FXML
	private Button btnBuscarVeiculo;
	@FXML
	private TextField placa;

	@FXML
	public void buscarVeiculo(ActionEvent event) throws IOException {
		String placA = placa.getText().toString();
		Veiculo veiculo = fachada.buscarVeiculo(placA);
		if (veiculo != null) {
			Alert alert = new Alert(AlertType.INFORMATION, "" + veiculo);
			alert.setTitle("Veículo Buscado");
			alert.setHeaderText("Veículo encontrado");
			alert.showAndWait();
			limparCamposBuscarVeiculo();
		} else {
			Alert alert = new Alert(AlertType.INFORMATION, "Informe uma Placa válida de um Veículo!");
			alert.setTitle("Falha ao encontrar");
			alert.setHeaderText("Veículo não encontrado");
			alert.showAndWait();
			limparCamposBuscarVeiculo();
		}

	}

	private void limparCamposBuscarVeiculo() {
		placa.setText("");
	}

	// Buscar Funcionários
	@FXML
	private Button btnBuscarFuncionario;
	@FXML
	private TextField cpfFuncionario;

	@FXML
	public void buscarFuncionario(ActionEvent event) throws IOException {
		try {
			Long cpf = Long.parseLong(cpfFuncionario.getText().toString());
			Funcionario funcionario = fachada.buscarFuncionario(cpf);
			if (funcionario != null) {
				Alert alert = new Alert(AlertType.INFORMATION, "" + funcionario);
				alert.setTitle("Funcionário Buscado");
				alert.setHeaderText("Funcionário encontrado");
				alert.showAndWait();
			} else {
				Alert alert = new Alert(AlertType.INFORMATION, "Informe o CPF válido de um funcionário!");
				alert.setTitle("Falha ao encontrar");
				alert.setHeaderText("Funcionário não encontrado");
				alert.showAndWait();
			}
		} catch (NumberFormatException nfe) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Falha ao encontrar");
			alert.setHeaderText("CPF inválido");
			alert.setContentText("Informe apenas os números do CPF!");
			alert.showAndWait();
		} finally {
			limparCamposBuscarFuncionario();
		}

	}

	private void limparCamposBuscarFuncionario() {
		cpfFuncionario.setText("");
	}

}
