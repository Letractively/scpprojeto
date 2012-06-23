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
			List<Cliente> clientes = Cliente.findAll();
			render(clientes);
		}
		
		public static void visualizar(String numPedido) {
			Pedido pedido = Pedido.find("numPedido", numPedido).first();
			render(pedido);
			
		}
		
		public static void editar(String cliente) {
			Pedido pedidos = Pedido.find("cliente", cliente).first();
			List<Cliente> clientes = Cliente.findAll();
			render(pedidos,clientes);
		}
		
		public static void excluir(String cliente) {
			Pedido pedidos = Pedido.find("cliente", cliente).first();
			pedidos.delete();
			index();
		}
		
		public static void cadastrar_pedido(String cliente, String pacote, String status, String formaPag, String funcionario, String numPedido, String dataInscricao, String dataCadastro) { 
			Pedido pedido = new Pedido(cliente, pacote, status, formaPag, funcionario, numPedido, dataInscricao, dataCadastro);
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
			pedido.numPedido = request.params.get("numPedido");
			pedido.dataInscricao = request.params.get("dataInscricao");
			pedido.dataCadastro = request.params.get("dataCadastro");
			
			pedido.save();
			index();
		}
		

}
