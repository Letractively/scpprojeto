package controllers;

import play.*;
import play.mvc.*;
import play.data.validation.*;

import java.util.*;

import models.*;

public class Clientes extends Controller {
	
	public static void index() {
		List<Cliente> clientes = Cliente.find("order by pnome asc").fetch();
		render (clientes);
	}
	
	public static void inserir() {
		render();
	}
	
	public static void visualizar(String cpf) {
		Cliente cliente = Cliente.find("cpf", cpf).first();
		render(cliente);
	}
	
	public static void editar(String cpf) {
		Cliente cliente = Cliente.find("cpf", cpf).first();
		render(cliente);
	}
	
	public static void excluir(String cpf) {
		Cliente cliente = Cliente.find("cpf", cpf).first();
		cliente.delete();
		index();
	}
	
	public static void cadastrar_cliente(@Required String pnome, @Required String unome, @Required String cpf,
											@Required String telefone, @Required String endereco) {
		Cliente cliente = new Cliente(pnome, unome, cpf, telefone, endereco);
		if (validation.hasErrors()) {
			render("Clientes/inserir.html", cliente);
		}
		
		cliente.save();
		index();
	}
	
	public static void editar_cliente(long id) {
		validation.required(request.params.get("pnome"));
		validation.required(request.params.get("cpf"));
		
		Cliente cliente = Cliente.find("id", id).first();
		
		if (validation.hasErrors()) {
			render("Clientes/editar.html", cliente);
		}
		
		cliente.pnome = request.params.get("pnome");
		cliente.unome = request.params.get("unome");
		cliente.telefone = request.params.get("telefone");
		cliente.cpf = request.params.get("cpf");
		cliente.endereco = request.params.get("endereco");
		
		cliente.save();
		index();
	}
	
}