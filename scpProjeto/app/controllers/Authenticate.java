package controllers;

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
		if (!funcionario.validateAndSave()) {
			params.flash();
			validation.keep();
			register();
		}
		// se ñ der erro, loga o usuario e vai pra pagina inicial
		doLoginLogic(funcionario.username);
		Application.index();
	}

	public static void login() {
		render();
	}

	public static void doLogin(String username, String password) {
		if (Funcionario.isValidLogin(username, password)) {
			doLoginLogic(username);
			Application.index();
		} else {
			validation.addError("username",
					"Combinação de Usuário/senha incorreta, tente novamente</br>");
			validation.keep();
			login();
		}
	}

	public static void logout() {
		session.remove("user");
		Application.index();
	}
}