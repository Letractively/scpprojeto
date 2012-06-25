package controllers;

import play.*;
import play.mvc.*;
import play.data.validation.*;

import java.sql.SQLException;
import java.util.*;

import models.*;

public class Pedidos extends Controller {

	public static void index() {
    	List<Pedido> pedidos = Pedido.getAllPedido();
        render(pedidos);
    }

	public static void inserir() {
		List<Cliente> clientes = null;
		List<Pacote> pacotes = null;
		List<Status> statuses = null;
		try {
			clientes = Clientes.getAllCliente();
			pacotes = Pacote.getAllPacote();
			statuses = Status.getAllStatus();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//do controler cliente
		
		List<Funcionario> funcionarios = Funcionario.getAllFuncionario();
		render(clientes, pacotes, statuses, funcionarios);
	}

	public static void visualizar(String numPedido) {
		Pedido pedido = Pedido.encontrar_Pedido(numPedido);
		render(pedido);

	}

	public static void editar(String cliente) {
		Pedido pedidos = Pedido.encontrar_Pedido(cliente);
		List<Cliente> clientes = null;
		List<Pacote> pacotes = null;	
		List<Status> statuses = null;
		List<Funcionario> funcionarios = null;
		try {
			clientes = Clientes.getAllCliente();
			pacotes = Pacote.getAllPacote();
			statuses = Status.getAllStatus();
			funcionarios = Funcionario.getAllFuncionario();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		render(pedidos, clientes, pacotes, statuses, funcionarios);
	}

	public static void excluir(String numPedido) {
		Pedido.dellPedido(numPedido);
		index();
	}

	public static void cadastrar_pedido(String cliente, String pacote,
			String status, String formaPag, String funcionario,
			String numPedido, String dataInscricao, String dataCadastro) {
		Pedido pedido = new Pedido(cliente, pacote, status, formaPag,
				funcionario, numPedido, dataInscricao, dataCadastro);
		if (validation.hasErrors()) {
			render("Pedidos/inserir.html", pedido);
		}

		pedido.savePedido();
		index();
	}

	public static void editar_pedido(String numPedido) {
		validation.required(request.params.get("cliente"));
		validation.required(request.params.get("pacote"));

		Pedido pedido = Pedido.encontrar_Pedido(numPedido);

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

		pedido.savePedido();
		index();
	}
	
	public static Pedido encontrar_Pedido(String numPedido) {
		return null;//TODO
	}
	
	public static List<Pedido> getAllPedido() {
		List<Pedido> retorno = new ArrayList<Pedido>();
		return retorno;//TODO
	}
	
	public static void dellPedido(String nome) {
		//TODO
	}
	
	public void savePedido() {
		// TODO
	}

}
