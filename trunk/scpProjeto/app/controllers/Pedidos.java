package controllers;

import play.*;
import play.mvc.*;
import play.data.validation.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import com.mysql.jdbc.PreparedStatement;

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
		}// do controler cliente

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

	public static Pedido encontrar_Pedido(String numPedido) throws SQLException {
		BancoDados.conectar();
		Connection con = BancoDados.con;
		PreparedStatement pstm;
		ResultSet rs;

		Pedido pedido = new Pedido();

		String query = "Select * from pedido where numPedido = ?";

		pstm = (PreparedStatement) con.prepareStatement(query);
		pstm.setString(1, numPedido);

		rs = pstm.executeQuery();

		while (rs.next()) {

			pedido.cliente = rs.getString("cliente");
			pedido.pacote = rs.getString("pacote");
			pedido.status = rs.getString("status");
			pedido.formaPag = rs.getString("formaPag");
			pedido.funcionario = rs.getString("funcionario");
			pedido.numPedido = rs.getString("numPedido");
			pedido.dataInscricao = rs.getString("dataInscricao");
			pedido.dataCadastro = rs.getString("dataCadastro");

		}

		return pedido;
	}

	public static List<Pedido> getAllPedido() throws SQLException {
		BancoDados.conectar();
		Connection con = BancoDados.con;

		PreparedStatement pstm;
		ResultSet rs;
		List<Pedido> pedidolist = new ArrayList<Pedido>();

		String query = "select * from pedido ORDER BY (pnome)";
		pstm = (PreparedStatement) con.prepareStatement(query);

		rs = pstm.executeQuery();

		while (rs.next()) {

			Pedido pedido = new Pedido(rs.getString("cliente"), rs.getString("pacote"), rs.getString("status"),
					rs.getString("formaPag"), rs.getString("funcionario"), rs.getString("numPedido"),
					rs.getString("dataInscricao"), rs.getString("dataCadastro"));

			pedidolist.add(pedido);

		}
		return pedidolist;

	}

	public static void dellPedido(String numPedido) throws SQLException {
		BancoDados.conectar();
		Connection con = BancoDados.con;
		PreparedStatement pstm;

		String query = "DELETE FROM pedido where numPedido = ?";
		pstm = (PreparedStatement) con.prepareStatement(query);
		pstm.setString(1, numPedido);
		pstm.execute();
	}

	public void savePedido(Pedido pedido) throws SQLException {
		
		BancoDados.conectar();
		Connection con = BancoDados.con;
		PreparedStatement pstm;

		String query = "insert into pedido (cliente, pacote, status,  formaPag, funcionario, numPedido, dataInscricao,  dataCadastro) values (?,?,?,?,?,?,?,?)";
		pstm = (PreparedStatement) con.prepareStatement(query);

		pstm.setString(1, pedido.cliente);
		pstm.setString(2, pedido.pacote);
		pstm.setString(3, pedido.status);
		pstm.setString(4, pedido.formaPag);
		pstm.setString(5, pedido.funcionario);
		pstm.setString(6, pedido.numPedido);
		pstm.setString(7, pedido.dataInscricao);
		pstm.setString(8, pedido.dataCadastro);

		pstm.execute();

		
	}

}
