package br.ufrpe.easyestacionamento.gui;

import java.io.IOException;

import br.ufrpe.easyestacionamento.negocio.Fachada;
import br.ufrpe.easyestacionamento.negocio.beans.Estacionamento;
import br.ufrpe.easyestacionamento.negocio.exception.EstacionamentoSemVagasException;
import br.ufrpe.easyestacionamento.negocio.exception.VeiculoJaEstacionadoException;
import br.ufrpe.easyestacionamento.negocio.exception.VeiculoNaoExisteException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class TelaEntradaEstacionamentoController {
	Fachada fachada = Fachada.getInstance();
	// Entrada Estacionamento
	@FXML
	private Button btnEntradaEstacionamentos;
	@FXML
	private TextField nomeEstacionamento;
	@FXML
	private TextField placaVeiculo;

	@FXML
	public void entradaEstacionamentos(ActionEvent event) throws IOException, VeiculoNaoExisteException,
			EstacionamentoSemVagasException, VeiculoJaEstacionadoException {
		try {
			String nome = nomeEstacionamento.getText().toString();
			String placa = placaVeiculo.getText().toString();
			Estacionamento e = fachada.buscar(nome);
			if (!(placa.equals("") || nome.equals(""))) {
				if (e != null) {
					fachada.entradaDeVeiculos(placa, e);
					Alert alert = new Alert(AlertType.INFORMATION, "Ve�culo cadastrado no estacionamento!");
					alert.setTitle("Entrada de ve�culos");
					alert.setHeaderText("Sucesso");
					alert.showAndWait();
				} else {
					Alert alert = new Alert(AlertType.ERROR, "N�o existe estacionamento com esse nome!");
					alert.show();
				}
			} else {
				Alert alert = new Alert(AlertType.ERROR, "Preencha todos os campos!");
				alert.show();
			}
		} catch (VeiculoNaoExisteException vne) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Falha ao entrar");
			alert.setHeaderText("Placa n�o encontrada");
			alert.setContentText("N�o existe ve�culo cadastrado no sistema com essa placa!");
			alert.showAndWait();
		} catch (EstacionamentoSemVagasException esve) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Falha ao entrar");
			alert.setHeaderText("Estacionamento lotado");
			alert.setContentText("N�o existem vagas dispon�veis para a entrada de ve�culos no estacionamento!");
			alert.showAndWait();
		} catch (VeiculoJaEstacionadoException vje) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Falha ao entrar");
			alert.setHeaderText("Ve�culo j� estacionado");
			alert.setContentText("J� existe um ve�culo estacionado no sistema com essa placa!");
			alert.showAndWait();
		} finally {
			limparCamposEntradaEstacionamento();
		}

	}

	private void limparCamposEntradaEstacionamento() {
		nomeEstacionamento.setText("");
		placaVeiculo.setText("");

	}
}
