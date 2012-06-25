package controllers;

import play.*;
import play.mvc.*;
import play.data.validation.*;

import java.util.*;

import models.*;

public class Pacotes extends Controller {

	public static void index() {
		List<Pacote> pacotes = Pacote.getAllPacote();
		render(pacotes);
	}

	public static void inserir() {
		render();
	}

	public static void visualizar(String nome) {
		Pacote pacote = Pacote.encontrar_Pacote(nome);
		render(pacote);
	}

	public static void editar(String nome) {
		Pacote pacotes = Pacote.encontrar_Pacote(nome);
		render(pacotes);
	}

	public static void excluir(String nome) {
		Pacote.dellPacote(nome);
		index();
	}

	public static void cadastrar_pacote(@Required String nome, int net, int tv, int telefone) {
		Pacote pacote = new Pacote(nome, net, tv, telefone);
		if (validation.hasErrors()) {
			render("Pacotes/inserir.html", pacote);
		}
		
		pacote.savePacote();
		index();
	}
	
	public static void editar_pacote(String nome) {
		validation.required(request.params.get("nome"));
		
		Pacote pacote = Pacote.encontrar_Pacote(nome);
		
		if (validation.hasErrors()) {
			render("Pacotes/editar.html", pacote);
		}
		
		pacote.nome = request.params.get("nome");
		
		pacote.savePacote();
		index();
	}
	
	public static Pacote encontrar_Pacote(String nome) {
		return null;//TODO
	}
	
	public static List<Pacote> getAllPacote() {
		List<Pacote> retorno = new ArrayList<Pacote>();
		return retorno;//TODO
	}
	
	public static void dellPacote(String nome) {
		//TODO
	}
	
	public void savePacote() {
		// TODO
	}

}
