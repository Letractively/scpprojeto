package controllers;

import play.*;
import play.mvc.*;
import play.data.validation.*;

import java.util.*;

import models.*;

public class Pacotes extends Controller {

	public static void index() {
		List<Pacote> pacotes = Pacote.find("order by nome asc").fetch();
		render(pacotes);
	}

	public static void inserir() {
		render();
	}

	public static void visualizar(String nome) {
		Pacote pacote = Pacote.find("nome", nome).first();
		render(pacote);
	}

	public static void editar(String nome) {
		Pacote pacotes = Pacote.find("nome", nome).first();
		render(pacotes);
	}

	public static void excluir(String nome) {
		Pacote pacotes = Pacote.find("nome", nome).first();
		pacotes.delete();
		index();
	}

	public static void cadastrar_pacote(@Required String nome, boolean net, boolean tv, boolean telefone) {
		Pacote pacote = new Pacote(nome, net, tv, telefone);
		if (validation.hasErrors()) {
			render("Pacotes/inserir.html", pacote);
		}

		pacote.save();
		index();
	}
	
	public static void editar_pacote(long id) {
		validation.required(request.params.get("nome"));
		
		Pacote pacote = Pacote.find("id", id).first();
		
		if (validation.hasErrors()) {
			render("Pacotes/editar.html", pacote);
		}
		
		pacote.nome = request.params.get("nome");
		
		pacote.save();
		index();
	}

}
