package br.ufrpe.easyestacionamento.gui;

import java.io.IOException;
import br.ufrpe.easyestacionamento.negocio.Fachada;
import br.ufrpe.easyestacionamento.negocio.beans.Estacionamento;
import br.ufrpe.easyestacionamento.negocio.exception.EstacionamentoJaExisteException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class TelaCadastrarEstacionamentoController {
	Fachada fachada = Fachada.getInstance();
	// Cadastro de Estacionamento
	@FXML
	private Button btnCadastrarEstacionamentos;
	@FXML
	private TextField nomeEstacionamento;
	@FXML
	private TextField qtdVagasEstacionamento;
	@FXML
	private TextField caixaEstacionamento;

	@FXML
	public void cadastrarEstacionamentos(ActionEvent event) throws IOException, EstacionamentoJaExisteException {
		try {
			String nome = nomeEstacionamento.getText().toString();
			double caixa = Double.parseDouble(caixaEstacionamento.getText());
			int vagas = Integer.parseInt(qtdVagasEstacionamento.getText());
			if (!(nome.equals(""))) {
				if (vagas >= 10) {
					if (caixa >= 0) {
						Estacionamento estacionamento = new Estacionamento(nome, caixa, vagas);
						fachada.abrirEstacionamento(estacionamento);
						Alert alert = new Alert(AlertType.INFORMATION, "Estacionamento cadastrado com sucesso!");
						alert.setTitle("Cadastro de estacionamentos");
						alert.setHeaderText("Estacionamento cadastrado");
						alert.showAndWait();
					} else {
						Alert alert = new Alert(AlertType.ERROR,
								"Não é possível abrir um estacionamento com caixa negativo!");
						alert.show();
					}
				} else {
					Alert alert = new Alert(AlertType.ERROR,
							"Não é possível abrir um estacionamento com menos de 10 vagas!");
					alert.show();
				}
			} else {
				Alert alert = new Alert(AlertType.ERROR, "Preencha o campo nome estacionamento!");
				alert.show();
			}
		} catch (EstacionamentoJaExisteException eje) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Falha ao cadastrar");
			alert.setHeaderText("Tente novamente");
			alert.setContentText("Já existe um estacionamento cadastrado com esse nome!");
			alert.showAndWait();
		} catch (NumberFormatException nfe) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Falha ao cadastrar");
			alert.setHeaderText("Campos preenchidos incorretamente ou não preenchidos");
			alert.setContentText(
					"Coloque valores inteiro no campo quantidade de vagas e valores reais em valor inicial do caixa!");
			alert.showAndWait();
		} finally {
			limparCamposCadastrarEstacionamento();
		}

	}

	private void limparCamposCadastrarEstacionamento() {
		nomeEstacionamento.setText("");
		qtdVagasEstacionamento.setText("");
		caixaEstacionamento.setText("");
	}

}
