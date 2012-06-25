package controllers;

import play.*;
import play.mvc.*;
import play.data.validation.*;

import java.util.*;

import models.*;

public class Clientes extends Controller {
	
	public static void index() {
		List<Cliente> clientes = Cliente.getAllCliente();
		render (clientes);
	}
	
	public static void inserir() {
		render();
	}
	
	public static void visualizar(String cpf) {
		Cliente cliente = Cliente.encontrar_Cliente(cpf);
		render(cliente);
	}
	
	public static void editar(String cpf) {
		Cliente cliente = Cliente.encontrar_Cliente(cpf);

		render(cliente);
	}
	
	public static void excluir(String cpf) {
		
		Cliente.dellCliente(cpf);
		index();
	}
	
	public static void cadastrar_cliente(@Required String pnome, @Required String unome, @Required String cpf,
											@Required String telefone, @Required String endereco) {
		Cliente cliente = new Cliente(pnome, unome, cpf, telefone, endereco);
		if (validation.hasErrors()) {
			render("Clientes/inserir.html", cliente);
		}
		cliente.saveCliente();
		index();
	}
	
	public static void editar_cliente(String cpf) {
		validation.required(request.params.get("pnome"));
		validation.required(request.params.get("cpf"));
		
		Cliente cliente = Cliente.encontrar_Cliente(cpf);
		
		if (validation.hasErrors()) {
			render("Clientes/editar.html", cliente);
		}
		
		cliente.pnome = request.params.get("pnome");
		cliente.unome = request.params.get("unome");
		cliente.telefone = request.params.get("telefone");
		cliente.cpf = request.params.get("cpf");
		cliente.endereco = request.params.get("endereco");
		
		Cliente.dellCliente(cpf);
		cliente.saveCliente();
		index();
	}
	
}