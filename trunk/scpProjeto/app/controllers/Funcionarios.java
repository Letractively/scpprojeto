package controllers;

import java.util.List;

import models.Cliente;
import models.Funcionario;
import play.data.validation.Required;
import play.libs.F;
import play.mvc.Controller;

public class Funcionarios extends Controller {
	
	public static void index() {
		List<Funcionario> funcionarios = Funcionario.find("order by pnome asc").fetch();
		render (funcionarios);
	}
	
	public static void inserir() {
		render();
	}
	
	public static void visualizar(String cpf) {
		Funcionario funcionario = Funcionario.find("cpf", cpf).first();
		render(funcionario);
	}
	
	public static void editar(String cpf) {
		Funcionario funcionario = Funcionario.find("cpf", cpf).first();
		render(funcionario);
	}
	
	public static void excluir(String cpf) {
		Funcionario funcionario = Funcionario.find("cpf", cpf).first();
		funcionario.delete();
		index();
	}
	
	public static void cadastrar_funcionario(@Required String username, @Required String pnome, @Required String unome, @Required String cpf,
											@Required String telefone, @Required String endereco, @Required String email, @Required String password, @Required String rg) {
		Funcionario funcionario = new Funcionario(username, pnome, unome, cpf, telefone, endereco, email, password, rg);
		if (validation.hasErrors()) {
			render("Clientes/inserir.html", funcionario);
		}
		
		funcionario.save();
		index();
	}
	
	public static void editar_funcionario(long id) {
		validation.required(request.params.get("pnome"));
		validation.required(request.params.get("cpf"));
		
		Funcionario funcionario = Funcionario.find("id", id).first();
		
		if (validation.hasErrors()) {
			render("Funcionario/editar.html", funcionario);
		}
		
		funcionario.username = request.params.get("username");
		funcionario.pnome = request.params.get("pnome");
		funcionario.unome = request.params.get("unome");
		funcionario.email = request.params.get("email");
		funcionario.password = request.params.get("password");
		funcionario.rg = request.params.get("rg");
		funcionario.telefone = request.params.get("telefone");
		funcionario.cpf = request.params.get("cpf");
		funcionario.endereco = request.params.get("endereco");
		
		funcionario.save();
		index();
	}
	

}
