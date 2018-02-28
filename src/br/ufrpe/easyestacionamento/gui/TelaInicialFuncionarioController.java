package br.ufrpe.easyestacionamento.gui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class TelaInicialFuncionarioController {
	@FXML
	private Button btnCadastrarCliente;
	@FXML
	private Button btnDeletarCliente;
	@FXML
	private Button btnAtualizarrCliente;
	@FXML
	private Button btnListarCliente;
	@FXML
	private Button btnBuscarCliente;
	@FXML
	private Button btnCadastrarVeiculo;
	@FXML
	private Button btnDeletarVeiculo;
	@FXML
	private Button btnAtualizarVeiculo;
	@FXML
	private Button btnListarVeiculo;
	@FXML
	private Button btnBuscarVeiculo;
	@FXML
	private Button btnSair;
	@FXML
	private Button btnSairTab2;
	@FXML
	private Button btnVisualizarCaixaEstacionamento;
	@FXML
	private Button btnListarEstacionamentos;
	@FXML
	private Button btnEntradaEstacionamento;
	@FXML
	private Button btnSaidaEstacionamento;
	@FXML
	private Button btnListarVeiculosEstacionados;
	@FXML
	private Button btnVisualizarVagasDisponiveis;

	@FXML
	public void abreTelaCadastrarCliente(ActionEvent event) throws IOException {
		BorderPane testPane = FXMLLoader
				.load(getClass().getResource("/br/ufrpe/easyestacionamento/gui/telaCadastrarCliente.fxml"));

		Scene scene = new Scene(testPane);
		Stage primaryStage = new Stage(StageStyle.DECORATED);
		primaryStage.setResizable(false);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Cadastro de cliente");
		primaryStage.show();
	}

	@FXML
	public void abreTelaDeletarCliente(ActionEvent event) throws IOException {
		Pane testPane = FXMLLoader
				.load(getClass().getResource("/br/ufrpe/easyestacionamento/gui/telaDeletarCliente.fxml"));

		Scene scene = new Scene(testPane);
		Stage primaryStage = new Stage(StageStyle.DECORATED);
		primaryStage.setResizable(false);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Deletar cliente");
		primaryStage.show();
	}

	@FXML
	public void abreTelaAtualizarCliente(ActionEvent event) throws IOException {
		Pane testPane = FXMLLoader
				.load(getClass().getResource("/br/ufrpe/easyestacionamento/gui/telaAtualizarCliente.fxml"));

		Scene scene = new Scene(testPane);
		Stage primaryStage = new Stage(StageStyle.DECORATED);
		primaryStage.setResizable(false);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Atualizar cliente");
		primaryStage.show();
	}

	@FXML
	public void abreTelaListarCliente(ActionEvent event) throws IOException {
		Pane testPane = FXMLLoader
				.load(getClass().getResource("/br/ufrpe/easyestacionamento/gui/telaListaClientes.fxml"));

		Scene scene = new Scene(testPane);
		Stage primaryStage = new Stage(StageStyle.DECORATED);
		primaryStage.setResizable(false);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Lista de clientes");
		primaryStage.show();
	}

	@FXML
	public void abreTelaBuscarCliente(ActionEvent event) throws IOException {
		Pane testPane = FXMLLoader
				.load(getClass().getResource("/br/ufrpe/easyestacionamento/gui/telaBuscarCliente.fxml"));

		Scene scene = new Scene(testPane);
		Stage primaryStage = new Stage(StageStyle.DECORATED);
		primaryStage.setResizable(false);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Buscar cliente");
		primaryStage.show();
	}

	@FXML
	public void abreTelaCadastrarVeiculo(ActionEvent event) throws IOException {
		BorderPane testPane = FXMLLoader
				.load(getClass().getResource("/br/ufrpe/easyestacionamento/gui/telaCadastrarVeiculo.fxml"));

		Scene scene = new Scene(testPane);
		Stage primaryStage = new Stage(StageStyle.DECORATED);
		primaryStage.setResizable(false);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Cadastro de Veículo");
		primaryStage.show();
	}

	@FXML
	public void abreTelaDeletarVeiculo(ActionEvent event) throws IOException {
		Pane testPane = FXMLLoader
				.load(getClass().getResource("/br/ufrpe/easyestacionamento/gui/telaDeletarVeiculo.fxml"));

		Scene scene = new Scene(testPane);
		Stage primaryStage = new Stage(StageStyle.DECORATED);
		primaryStage.setResizable(false);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Deletar Veículo");
		primaryStage.show();
	}

	@FXML
	public void abreTelaAtualizarVeiculo(ActionEvent event) throws IOException {
		Pane testPane = FXMLLoader
				.load(getClass().getResource("/br/ufrpe/easyestacionamento/gui/telaAtualizarVeiculo.fxml"));

		Scene scene = new Scene(testPane);
		Stage primaryStage = new Stage(StageStyle.DECORATED);
		primaryStage.setResizable(false);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Atualizar Veículo");
		primaryStage.show();
	}

	@FXML
	public void abreTelaListarVeiculo(ActionEvent event) throws IOException {
		Pane testPane = FXMLLoader
				.load(getClass().getResource("/br/ufrpe/easyestacionamento/gui/telaListaVeiculos.fxml"));

		Scene scene = new Scene(testPane);
		Stage primaryStage = new Stage(StageStyle.DECORATED);
		primaryStage.setResizable(false);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Lista de Veículos");
		primaryStage.show();
	}

	@FXML
	public void abreTelaBuscarVeiculo(ActionEvent event) throws IOException {
		Pane testPane = FXMLLoader
				.load(getClass().getResource("/br/ufrpe/easyestacionamento/gui/telaBuscarVeiculo.fxml"));

		Scene scene = new Scene(testPane);
		Stage primaryStage = new Stage(StageStyle.DECORATED);
		primaryStage.setResizable(false);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Buscar Veículo");
		primaryStage.show();
	}

	@FXML
	public void abreTelaLogin(ActionEvent event) throws IOException {
		Pane testPane = FXMLLoader.load(getClass().getResource("/br/ufrpe/easyestacionamento/gui/telaLogin.fxml"));

		Scene scene = new Scene(testPane);
		Stage primaryStage = new Stage(StageStyle.DECORATED);
		primaryStage.setResizable(false);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Easy System - Login");
		primaryStage.show();
		btnSair.getScene().getWindow().hide();
		btnSairTab2.getScene().getWindow().hide();

	}

	@FXML
	public void abreTelaVisualizarCaixaEstacionamento(ActionEvent event) throws IOException {
		Pane testPane = FXMLLoader
				.load(getClass().getResource("/br/ufrpe/easyestacionamento/gui/telaVisualizarCaixa.fxml"));

		Scene scene = new Scene(testPane);
		Stage primaryStage = new Stage(StageStyle.DECORATED);
		primaryStage.setResizable(false);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Valor em caixa");
		primaryStage.show();
	}

	@FXML
	public void abreTelaListarEstacionamento(ActionEvent event) throws IOException {
		Pane testPane = FXMLLoader
				.load(getClass().getResource("/br/ufrpe/easyestacionamento/gui/telaListaEstacionamentos.fxml"));

		Scene scene = new Scene(testPane);
		Stage primaryStage = new Stage(StageStyle.DECORATED);
		primaryStage.setResizable(false);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Lista de Estacionamentos");
		primaryStage.show();
	}

	@FXML
	public void abreTelaEntradaEstacionamento(ActionEvent event) throws IOException {
		Pane testPane = FXMLLoader
				.load(getClass().getResource("/br/ufrpe/easyestacionamento/gui/telaEntradaEstacionamento.fxml"));

		Scene scene = new Scene(testPane);
		Stage primaryStage = new Stage(StageStyle.DECORATED);
		primaryStage.setResizable(false);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Entrada Estacionamento");
		primaryStage.show();
	}

	@FXML
	public void abreTelaSaidaEstacionamento(ActionEvent event) throws IOException {
		Pane testPane = FXMLLoader
				.load(getClass().getResource("/br/ufrpe/easyestacionamento/gui/telaSaidaEstacionamento.fxml"));

		Scene scene = new Scene(testPane);
		Stage primaryStage = new Stage(StageStyle.DECORATED);
		primaryStage.setResizable(false);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Saída Estacionamento");
		primaryStage.show();
	}

	@FXML
	public void abreTelaListarVeiculosEstacionados(ActionEvent event) throws IOException {
		Pane testPane = FXMLLoader
				.load(getClass().getResource("/br/ufrpe/easyestacionamento/gui/telaListarVeiculosEstacionados.fxml"));

		Scene scene = new Scene(testPane);
		Stage primaryStage = new Stage(StageStyle.DECORATED);
		primaryStage.setResizable(false);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Veículos Estacionados");
		primaryStage.show();
	}

	@FXML
	public void abreTelaVisualizarVagasDisponiveis(ActionEvent event) throws IOException {
		Pane testPane = FXMLLoader
				.load(getClass().getResource("/br/ufrpe/easyestacionamento/gui/telaVisualizarVagasDisponiveis.fxml"));

		Scene scene = new Scene(testPane);
		Stage primaryStage = new Stage(StageStyle.DECORATED);
		primaryStage.setResizable(false);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Vagas Disponíveis");
		primaryStage.show();
	}
}
