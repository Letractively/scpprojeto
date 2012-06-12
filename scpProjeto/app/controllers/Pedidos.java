package controllers;

import play.*;
import play.mvc.*;
import play.data.validation.*;

import java.util.*;

import models.*;

public class Pedidos extends Controller {

    public static void index() {
				List<Pedido> pedidos = Pedido.find("order by cliente asc").fetch();
        render(pedidos);
    }

		public static void inserir() {
			render();
		}
		
		public static void visualizar(String cliente) {
			Pedido pedido = Pedido.find("cliente", cliente).first();
			render(pedido);
		}
		
		public static void editar(String cliente) {
			Pedido pedidos = Pedido.find("cliente", cliente).first();
			render(pedidos);
		}
		
		public static void excluir(String cliente) {
			Pedido pedidos = Pedido.find("cliente", cliente).first();
			pedidos.delete();
			index();
		}
		
		public static void cadastrar_pedido(String cliente, String pacote, String status, String formaPag, String funcionario) { 
			Pedido pedido = new Pedido(cliente, pacote, status, formaPag, funcionario);
			if (validation.hasErrors()) {
				render("Pedidos/inserir.html", pedido);
			}
			
			pedido.save();
			index();
		}
		
		public static void editar_pedido(long id) {
			validation.required(request.params.get("cliente"));
			validation.required(request.params.get("pacote"));
			
			Pedido pedido = Pedido.find("id", id).first();
			
			if (validation.hasErrors()) {
				render("Pedidos/editar.html", pedido);
			}
			
			pedido.cliente = request.params.get("cliente");
			pedido.pacote = request.params.get("pacote");
			pedido.status = request.params.get("status");
			pedido.formaPag = request.params.get("formaPag");
			pedido.formaPag = request.params.get("funcionario");
			
			pedido.save();
			index();
		}
		

}
