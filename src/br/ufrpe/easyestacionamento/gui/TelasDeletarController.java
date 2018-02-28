package br.ufrpe.easyestacionamento.gui;

import java.io.IOException;

import br.ufrpe.easyestacionamento.negocio.Fachada;
import br.ufrpe.easyestacionamento.negocio.exception.CpfNaoExisteException;
import br.ufrpe.easyestacionamento.negocio.exception.EstacionamentoNaoExisteException;
import br.ufrpe.easyestacionamento.negocio.exception.PlacaNaoExisteException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class TelasDeletarController {
	Fachada fachada = Fachada.getInstance();

	// Deletar Clientes
	@FXML
	private Button btnDeletarCliente;
	@FXML
	private TextField cpf;

	@FXML
	public void deletarCliente(ActionEvent event) throws IOException, CpfNaoExisteException {
		try {
			if (!(cpf.getText().toString().equals(""))) {
				Long cPF = Long.parseLong(cpf.getText().toString());
				fachada.removerCliente(cPF);
				Alert alert = new Alert(AlertType.INFORMATION, "Cliente removido com sucesso!");
				alert.setTitle("Cliente removido");
				alert.setHeaderText("Removido");
				alert.showAndWait();
			} else {
				Alert alert = new Alert(AlertType.ERROR, "Informe um CPF para remover!");
				alert.show();
			}
		} catch (CpfNaoExisteException cne) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Falha ao remover");
			alert.setHeaderText("Tente novamente");
			alert.setContentText("Não existe um cliente cadastrado com esse CPF!");
			alert.showAndWait();
		} catch (NumberFormatException nfe) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Falha ao remover");
			alert.setHeaderText("CPF inválido");
			alert.setContentText("Informe apenas os números do CPF!");
			alert.showAndWait();
		} finally {
			limparCamposCliente();
		}

	}

	private void limparCamposCliente() {
		cpf.setText("");
	}

	// Deletar Veiculo
	@FXML
	private Button btnDeletarVeiculo;
	@FXML
	private TextField placa;

	@FXML
	public void deletarVeiculo(ActionEvent event) throws IOException, PlacaNaoExisteException {
		try {
			String placA = placa.getText().toString();
			if (!(placA.equals(""))) {
				fachada.removerVeiculo(placA);
				Alert alert = new Alert(AlertType.INFORMATION, "Veículo removido com sucesso!");
				alert.setTitle("Veículo removido");
				alert.setHeaderText("Removido");
				alert.showAndWait();
			} else {
				Alert alert = new Alert(AlertType.ERROR, "Informe uma Placa para remover um veículo!");
				alert.show();
			}
		} catch (PlacaNaoExisteException pne) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Falha ao deletar");
			alert.setHeaderText("Tente novamente");
			alert.setContentText("Não existe um veículo cadastrado com essa Placa!");
			alert.showAndWait();
		} finally {
			limparCamposVeiculo();
		}
	}

	private void limparCamposVeiculo() {
		placa.setText("");
	}

	// Deletar Funcionarios
	@FXML
	private Button btnDeletarFuncionario;
	@FXML
	private TextField cpfFuncionario;

	@FXML
	public void deletarFuncionario(ActionEvent event) throws IOException, CpfNaoExisteException {
		try {
			if (!(cpfFuncionario.getText().toString().equals(""))) {
				Long cPF = Long.parseLong(cpfFuncionario.getText().toString());
				fachada.removerFuncionario(cPF);
				Alert alert = new Alert(AlertType.INFORMATION, "Funcionário removido com sucesso!");
				alert.setTitle("Funcionário removido");
				alert.setHeaderText("Removido");
				alert.showAndWait();
			} else {
				Alert alert = new Alert(AlertType.ERROR, "Informe um CPF para remover!");
				alert.show();
			}
		} catch (CpfNaoExisteException cne) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Falha ao deletar");
			alert.setHeaderText("Tente novamente");
			alert.setContentText("Não existe um funcionário cadastrado com esse CPF!");
			alert.showAndWait();
		} catch (NumberFormatException nfe) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Falha ao remover");
			alert.setHeaderText("CPF inválido");
			alert.setContentText("Informe apenas os números do CPF!");
			alert.showAndWait();
		} finally {
			limparCamposFuncionario();
		}

	}

	private void limparCamposFuncionario() {
		cpfFuncionario.setText("");
	}

	// Deletar Estacionamentos
	@FXML
	private Button btnDeletarEstacionamento;
	@FXML
	private TextField nomeEstacionamento;

	@FXML
	public void deletarEstacionamento(ActionEvent event) throws IOException, EstacionamentoNaoExisteException {
		try {
			String nome = nomeEstacionamento.getText().toString();
			if (!(nome.equals(""))) {
				fachada.fecharEstacionamento(nome);
				Alert alert = new Alert(AlertType.INFORMATION, "Estacionamento removido com sucesso!");
				alert.setTitle("Estacionamento removido");
				alert.setHeaderText("Removido");
				alert.showAndWait();
			} else {
				Alert alert = new Alert(AlertType.ERROR, "Informe o Nome do estacionamento para remover!");
				alert.show();
			}
		} catch (EstacionamentoNaoExisteException ene) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Falha ao deletar");
			alert.setHeaderText("Tente novamente");
			alert.setContentText("Não existe um estacionamento cadastrado com esse Nome!");
			alert.showAndWait();
		} finally {
			limparCamposEstacionamento();
		}

	}

	private void limparCamposEstacionamento() {
		nomeEstacionamento.setText("");
	}
}
