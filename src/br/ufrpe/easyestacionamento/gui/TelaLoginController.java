package br.ufrpe.easyestacionamento.gui;

import java.io.IOException;

import br.ufrpe.easyestacionamento.negocio.Fachada;
import br.ufrpe.easyestacionamento.negocio.beans.Funcionario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class TelaLoginController {
	Fachada f = Fachada.getInstance();

	@FXML
	private Button btnEntrar;
	@FXML
	private TextField login;
	@FXML
	private PasswordField senha;

	@FXML
	public void abreTelaGereciamentoSystemAdmin(ActionEvent event) throws IOException {
		TabPane testPane = FXMLLoader
				.load(getClass().getResource("/br/ufrpe/easyestacionamento/gui/telaGerenciamentoCadastrosAdmin.fxml"));

		Scene scene = new Scene(testPane);
		Stage primaryStage = new Stage(StageStyle.DECORATED);
		primaryStage.setResizable(false);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Tela de Gerenciamento - Administrador");
		primaryStage.show();
		btnEntrar.getScene().getWindow().hide();
	}

	@FXML
	public void abreTelaGereciamentoSystemFuncionario(ActionEvent event) throws IOException {
		TabPane tabPanetest = FXMLLoader.load(
				getClass().getResource("/br/ufrpe/easyestacionamento/gui/telaGerenciamentoCadastrosFuncionarios.fxml"));
		Scene scene = new Scene(tabPanetest);
		Stage primaryStage = new Stage(StageStyle.DECORATED);
		primaryStage.setResizable(false);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Tela de Gerenciamento - Funcionário");
		primaryStage.show();
		btnEntrar.getScene().getWindow().hide();
	}

	@FXML
	public void logar(ActionEvent event) throws IOException {
		String logar = login.getText().toString();
		String password = senha.getText().toString();
		Funcionario loginAdmin = new Funcionario();
		if (logar.equals(loginAdmin.getLogin()) && password.equals(loginAdmin.getSenha())) {
			Alert alert = new Alert(AlertType.INFORMATION, "Bem-vindo ao Easy System Estacionamentos!");
			alert.setTitle("Bem-Vindo");
			alert.showAndWait();
			abreTelaGereciamentoSystemAdmin(event);
		} else if (f.logar(logar, password)) {
			Alert alert = new Alert(AlertType.INFORMATION, "Bem-vindo ao Easy System Estacionamentos!");
			alert.setTitle("Bem-Vindo");
			alert.showAndWait();
			abreTelaGereciamentoSystemFuncionario(event);
		} else {
			Alert alert = new Alert(AlertType.INFORMATION, "Login ou senha errada!");
			alert.setTitle("Falha ao entrar!");
			alert.showAndWait();
		}
	}

}
