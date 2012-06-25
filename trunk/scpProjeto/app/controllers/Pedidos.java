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
		List<Pedido> pedidos = new ArrayList<Pedido>();
		try {
			pedidos = getAllPedido();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		render(pedidos);
	}

	public static void inserir() {
		List<Cliente> clientes = new ArrayList<Cliente>();
		List<Pacote> pacotes = new ArrayList<Pacote>();
		List<Status> statuses = new ArrayList<Status>();
		try {
			clientes = Clientes.getAllCliente();
			pacotes = Pacotes.getAllPacote();
			statuses = Statuses.getAllStatus();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}// do controler cliente

		List<Funcionario> funcionarios = new ArrayList<Funcionario>();
		try {
			funcionarios = Funcionarios.getAllFuncionario();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		render(clientes, pacotes, statuses, funcionarios);
	}

	public static void visualizar(String numPedido) {
		Pedido pedido = new Pedido();
		try {
			pedido = Pedidos.encontrar_Pedido(numPedido);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		render(pedido);

	}

	public static void editar(String cliente) {
		Pedido pedidos = null;
		try {
			pedidos = encontrar_Pedido(cliente);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		List<Cliente> clientes = new ArrayList<Cliente>();
		List<Pacote> pacotes = new ArrayList<Pacote>();
		List<Status> statuses = new ArrayList<Status>();
		List<Funcionario> funcionarios = new ArrayList<Funcionario>();
		try {
			clientes = Clientes.getAllCliente();
			pacotes = Pacotes.getAllPacote();
			statuses = Statuses.getAllStatus();
			funcionarios = Funcionarios.getAllFuncionario();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		render(pedidos, clientes, pacotes, statuses, funcionarios);
	}

	public static void excluir(String numPedido) {
		try {
			dellPedido(numPedido);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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

		try {
			savePedido(pedido);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		index();
	}

	public static void editar_pedido(String numPedido) {
		validation.required(request.params.get("cliente"));
		validation.required(request.params.get("pacote"));

		Pedido pedido = new Pedido();
		try {
			pedido = encontrar_Pedido(numPedido);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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

		try {
			savePedido(pedido);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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

	public static void savePedido(Pedido pedido) throws SQLException {
		
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
