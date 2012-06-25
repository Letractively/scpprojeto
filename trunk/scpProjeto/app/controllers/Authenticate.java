package controllers;

import java.sql.Connection;
import java.sql.SQLException;

import com.mysql.jdbc.PreparedStatement;

import play.data.validation.Valid;
import play.mvc.Controller;
import models.*;

public class Authenticate extends Controller {
	private static void doLoginLogic(String username) {
		session.put("user", username);
	}

	public static void register() {
		render();
	}
	
	public static void doRegister(@Valid Funcionario funcionario) {
		// se der erros, mostra o formulario novamente, caso contrario salva o usuario
		if (!funcionario.isvalid()) {
			params.flash();
			validation.keep();
			register();
		}
		// se ñ der erro, loga o usuario e vai pra pagina inicial
		try {
			BancoDados.conectar();
			Connection con = BancoDados.con;
			PreparedStatement pstm;
			
			String query = "insert into funcionario ( username, pnome, unome, cpf, telefone, endereco, email, password, passwordHash, rg) values (?,?,?,?,?,?,?,?,?,?)";
			pstm = (PreparedStatement) con.prepareStatement(query);
			
			pstm.setString(1, funcionario.username);
			pstm.setString(2, funcionario.pnome);
			pstm.setString(3, funcionario.unome);
			pstm.setString(4, funcionario.cpf);
			pstm.setString(5, funcionario.telefone);
			pstm.setString(6, funcionario.endereco);
			pstm.setString(7, funcionario.email);
			pstm.setString(8, funcionario.password);
			pstm.setString(9, funcionario.passwordHash);
			pstm.setString(10, funcionario.rg);
			
			pstm.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		doLoginLogic(funcionario.username);
		Application.index();
	}

	public static void login() {
		render();
	}

	public static void doLogin(String username, String password) {
		try {
			if (Funcionarios.isValidLogin(username, password)) {
				doLoginLogic(username);
				Application.index();
			} else {
				validation.addError("username",
						"Combinação de Usuário/senha incorreta, tente novamente</br>");
				validation.keep();
				login();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void logout() {
		session.remove("user");
		Application.index();
	}
}