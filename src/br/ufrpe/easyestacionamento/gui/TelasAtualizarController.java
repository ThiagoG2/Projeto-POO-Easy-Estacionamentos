package br.ufrpe.easyestacionamento.gui;

import java.io.IOException;

import br.ufrpe.easyestacionamento.negocio.Fachada;
import br.ufrpe.easyestacionamento.negocio.beans.Cliente;
import br.ufrpe.easyestacionamento.negocio.beans.Funcionario;
import br.ufrpe.easyestacionamento.negocio.beans.Veiculo;
import br.ufrpe.easyestacionamento.negocio.exception.CpfNaoExisteException;
import br.ufrpe.easyestacionamento.negocio.exception.LoginJaExisteException;
import br.ufrpe.easyestacionamento.negocio.exception.PlacaNaoExisteException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class TelasAtualizarController {
	Fachada fachada = Fachada.getInstance();

	// Atualizar Cliente
	@FXML
	private Button btnAtualizarCliente;
	@FXML
	private TextField nomeCompletoCliente;
	@FXML
	private TextField cpfCliente;
	@FXML
	private TextField telefoneCliente;
	@FXML
	private CheckBox mensalista;

	@FXML
	public void atualizarCliente(ActionEvent event) throws IOException, CpfNaoExisteException {
		try {
			String nome = nomeCompletoCliente.getText().toString();
			String telefone = telefoneCliente.getText().toString();
			boolean clienteMensalista = mensalista.selectedProperty().getValue();
			if (!(nome.equals("") || cpfCliente.getText().toString().equals("") || telefone.equals(""))) {
				Long cpf = Long.parseLong(cpfCliente.getText().toString());
				Cliente cliente = new Cliente(nome, cpf, telefone, clienteMensalista);
				fachada.atualizarCliente(cliente, cpf);
				Alert alert = new Alert(AlertType.INFORMATION, "Cliente atualizado com sucesso!");
				alert.setTitle("Atualização de clientes");
				alert.setHeaderText("Cliente atualizado");
				alert.showAndWait();
			} else {
				Alert alert = new Alert(AlertType.ERROR, "Preencha todos os campos!");
				alert.show();
			}
		} catch (CpfNaoExisteException cne) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Falha ao atualizar");
			alert.setHeaderText("Tente novamente");
			alert.setContentText("Não existe um cliente cadastrado com esse CPF para que possa atualizar!");
			alert.showAndWait();
		} catch (NumberFormatException nfe) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Falha ao atualizar");
			alert.setHeaderText("CPF inválido");
			alert.setContentText("Informe apenas os números do CPF!");
			alert.showAndWait();
		} finally {
			limparCamposCliente();
		}

	}

	private void limparCamposCliente() {
		nomeCompletoCliente.setText("");
		cpfCliente.setText("");
		telefoneCliente.setText("");
	}

	// Atualizar Funcionario
	@FXML
	private Button btnAtualizarFuncionario;
	@FXML
	private TextField nomeCompletoFuncionario;
	@FXML
	private TextField cpfFuncionario;
	@FXML
	private TextField login;
	@FXML
	private PasswordField senha;
	@FXML
	private TextField telefoneFuncionario;

	@FXML
	public void atualizarFuncionario(ActionEvent event)
			throws IOException, CpfNaoExisteException, LoginJaExisteException {
		try {
			String nome = nomeCompletoFuncionario.getText().toString();
			String logiN = login.getText().toString();
			String senhA = senha.getText().toString();
			String tel = telefoneFuncionario.getText().toString();
			if (!(nome.equals("") || logiN.equals("") || senhA.equals("")
					|| cpfFuncionario.getText().toString().equals("") || tel.equals(""))) {
				Long cPF = Long.parseLong(cpfFuncionario.getText().toString());
				Funcionario funcionario = new Funcionario(nome, cPF, tel, logiN, senhA);
				fachada.atualizarFuncionario(funcionario, cPF);
				Alert alert = new Alert(AlertType.INFORMATION, "Funcionário atualizado com sucesso!");
				alert.setTitle("Atualização de funcionários");
				alert.setHeaderText("Funcionário atualizado");
				alert.showAndWait();
			} else {
				Alert alert = new Alert(AlertType.ERROR, "Preencha todos os campos!");
				alert.show();
			}
		} catch (CpfNaoExisteException cne) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Falha ao atualizar");
			alert.setHeaderText("Tente novamente");
			alert.setContentText("Não existe um funcionário cadastrado com esse CPF para que possa atualizar!");
			alert.showAndWait();
		} catch (LoginJaExisteException lje) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Falha ao atualizar");
			alert.setHeaderText("Tente novamente");
			alert.setContentText("Já existe um funcionário cadastrado com esse login!");
			alert.showAndWait();
		} catch (NumberFormatException nfe) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Falha ao atualizar");
			alert.setHeaderText("CPF inválido");
			alert.setContentText("Informe apenas os números do CPF!");
			alert.showAndWait();
		} finally {
			limparCamposFuncionario();
		}

	}

	private void limparCamposFuncionario() {
		nomeCompletoFuncionario.setText("");
		login.setText("");
		senha.setText("");
		cpfFuncionario.setText("");
		telefoneFuncionario.setText("");
	}

	// Atualizar Veiculo
	@FXML
	private Button btnAtualizarVeiculo;
	@FXML
	private TextField nomeVeiculo;
	@FXML
	private TextField cpfProprietarioVeiculo;
	@FXML
	private TextField placa;
	@FXML
	private TextField marca;
	@FXML
	private TextField corVeiculo;

	@FXML
	public void atualizarVeiculo(ActionEvent event) throws IOException, PlacaNaoExisteException, CpfNaoExisteException {
		try {
			String nome = nomeVeiculo.getText().toString();
			String placA = placa.getText().toString();
			String marcA = marca.getText().toString();
			String cor = corVeiculo.getText().toString();
			if (!(nome.equals("") || cpfProprietarioVeiculo.getText().toString().equals("") || placA.equals("")
					|| marcA.equals("") || cor.equals(""))) {
				Long cpf = Long.parseLong(cpfProprietarioVeiculo.getText().toString());
				Cliente proprietarioVeiculo = fachada.buscarCliente(cpf);
				if (proprietarioVeiculo != null) {
					Veiculo veiculo = new Veiculo(proprietarioVeiculo, marcA, placA, cor, nome);
					fachada.atualizarVeiculo(veiculo, placA);
					Alert alert = new Alert(AlertType.INFORMATION, "Veículo atualizado com sucesso!");
					alert.setTitle("Atualização de veículos");
					alert.setHeaderText("Veículo atualizado");
					alert.showAndWait();
				} else {
					throw new CpfNaoExisteException("Não existe cliente cadastrado para esse cpf!");
				}
			} else {
				Alert alert = new Alert(AlertType.ERROR, "Preencha todos os campos!");
				alert.show();
			}
		} catch (PlacaNaoExisteException pne) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Falha ao atualizar");
			alert.setHeaderText("Tente novamente");
			alert.setContentText("Não existe um veículo cadastrado com essa Placa para que possa atualizar!");
			alert.showAndWait();

		} catch (CpfNaoExisteException cne) {
			Alert alert = new Alert(AlertType.ERROR, "Não existe um cliente cadastrado com o CPF fornecido!");
			alert.show();
		} finally {
			limparCamposVeiculo();
		}

	}

	private void limparCamposVeiculo() {
		nomeVeiculo.setText("");
		placa.setText("");
		cpfProprietarioVeiculo.setText("");
		marca.setText("");
		corVeiculo.setText("");
	}
}
