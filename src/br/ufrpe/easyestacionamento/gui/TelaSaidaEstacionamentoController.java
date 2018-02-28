package br.ufrpe.easyestacionamento.gui;

import java.io.IOException;
import br.ufrpe.easyestacionamento.negocio.Fachada;
import br.ufrpe.easyestacionamento.negocio.beans.Estacionamento;
import br.ufrpe.easyestacionamento.negocio.exception.EstacionamentoErradoException;
import br.ufrpe.easyestacionamento.negocio.exception.VeiculoNaoEstacionadoException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class TelaSaidaEstacionamentoController {
	Fachada fachada = Fachada.getInstance();

	// Saida Estacionamento
	@FXML
	private Button btnSaidaEstacionamentos;
	@FXML
	private TextField nomeEstacionamento;
	@FXML
	private TextField placaVeiculo;

	@FXML
	public void saidaEstacionamentos(ActionEvent event)
			throws IOException, VeiculoNaoEstacionadoException, EstacionamentoErradoException {
		try {
			String nome = nomeEstacionamento.getText().toString();
			String placa = placaVeiculo.getText().toString();
			Estacionamento e = fachada.buscar(nome);
			if (!(placa.equals("") || nome.equals(""))) {
				if (e != null) {
					double valorEstacionamento = fachada.saidaDeVeiculos(placa, e);
					if (valorEstacionamento != 0) {
						Alert alert = new Alert(AlertType.INFORMATION, "Receba o pagemento do cliente!");
						alert.setTitle("Saída de veículos");
						alert.setHeaderText("Valor a pagar pelo estacionamento: R$" + valorEstacionamento);
						alert.showAndWait();
					} else {
						Alert alert = new Alert(AlertType.INFORMATION, "Cliente Mensalista!");
						alert.setTitle("Saída de veículos");
						alert.setHeaderText("Valor a pagar pelo estacionamento: R$" + valorEstacionamento);
						alert.showAndWait();
					}
				} else {
					Alert alert = new Alert(AlertType.ERROR, "Não existe estacionamento com esse nome!");
					alert.show();
				}
			} else {
				Alert alert = new Alert(AlertType.ERROR, "Preencha todos os campos!");
				alert.show();
			}
		} catch (VeiculoNaoEstacionadoException vne) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Falha ao sair");
			alert.setHeaderText("Placa não encontrada");
			alert.setContentText("Não existe veículo estacionado no estacionamento com essa placa!");
			alert.showAndWait();
		} catch (EstacionamentoErradoException eee) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Falha ao sair");
			alert.setHeaderText("Estacionamento errado");
			alert.setContentText("O veículo não pertence ao estacionamento informado!");
			alert.showAndWait();
		} finally {
			limparCamposSaidaEstacionamento();
		}

	}

	private void limparCamposSaidaEstacionamento() {
		nomeEstacionamento.setText("");
		placaVeiculo.setText("");

	}

}
