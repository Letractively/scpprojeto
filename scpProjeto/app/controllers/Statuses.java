package controllers;

import play.*;
import play.mvc.*;
import play.data.validation.*;

import java.util.*;

import models.*;

public class Statuses extends Controller {

	public static void index() {
		List<Status> statuses = Status.find("order by nome asc").fetch();
		render(statuses);
	}

	public static void inserir() {
		render();
	}

	public static void visualizar(String nome) {
		Status status = Status.find("nome", nome).first();
		render(status);
	}

	public static void editar(String nome) {
		Status statuses = Status.find("nome", nome).first();
		render(statuses);
	}

	public static void excluir(String nome) {
		Status statuses = Status.find("nome", nome).first();
		statuses.delete();
		index();
	}

	public static void cadastrar_Status(@Required String nome) {
		Status status = new Status(nome);
		if (validation.hasErrors()) {
			render("Statuses/inserir.html", status);
		}

		status.save();
		index();
	}

	public static void editar_status(long id) {
		validation.required(request.params.get("nome"));
		
		Status status = Status.find("id", id).first();
		
		if (validation.hasErrors()) {
			render("Statuses/editar.html", status);
		}
		
		status.nome = request.params.get("nome");
		
		status.save();
		index();
	}
}
