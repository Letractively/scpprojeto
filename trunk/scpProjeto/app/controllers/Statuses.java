package controllers;

import play.*;
import play.mvc.*;
import play.data.validation.*;

import java.util.*;

import models.*;

public class Statuses extends Controller {

	public static void index() {
		List<Status> statuses = Status.getAllStatus();
		render(statuses);
	}

	public static void inserir() {
		render();
	}

	public static void visualizar(String nome) {
		Status status = Status.encontrar_Status(nome);
		render(status);
	}

	public static void editar(String nome) {
		Status statuses = Status.encontrar_Status(nome);
		render(statuses);
	}

	public static void excluir(String nome) {
		Status.dellStatus(nome);
		index();
	}

	public static void cadastrar_Status(@Required String nome) {
		Status status = new Status(nome);
		if (validation.hasErrors()) {
			render("Statuses/inserir.html", status);
		}

		status.saveStatus();
		index();
	}

	public static void editar_status(String nome) {
		validation.required(request.params.get("nome"));
		
		Status status = Status.encontrar_Status(nome);
		
		if (validation.hasErrors()) {
			render("Statuses/editar.html", status);
		}
		
		status.nome = request.params.get("nome");
		
		status.saveStatus();
		index();
	}
}
