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
			pedidos = pedidolist;
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
			BancoDados.conectar();
			Connection con = BancoDados.con;
			
			PreparedStatement pstm;
			ResultSet rs;
			List<Cliente> retorno = new ArrayList<Cliente>();
			
			String query = "select * from cliente ORDER BY (pnome)";
			pstm = (PreparedStatement) con.prepareStatement(query);
			
			rs = pstm.executeQuery();
			
			while (rs.next()) {
			
				Cliente func = new Cliente(rs.getString("pnome"),
						rs.getString("unome"), rs.getString("cpf"),
						rs.getString("telefone"), rs.getString("endereco"));
			
				retorno.add(func);
			
			}
			clientes = retorno;
			BancoDados.conectar();
			Connection con1 = BancoDados.con;
			
			PreparedStatement pstm1;
			ResultSet rs1;
			List<Pacote> pacotelist = new ArrayList<Pacote>();
			
			String query1 = "select * from status ORDER BY (nome)";
			pstm1 = (PreparedStatement) con1.prepareStatement(query1);
			
			rs1 = pstm1.executeQuery();
			
			while (rs1.next()) {
			
				Pacote pacote = new Pacote(rs1.getString("nome"), rs1.getInt("net"), rs1.getInt("tv"), rs1.getInt("telefone"));
			
				pacotelist.add(pacote);
			
			}
			pacotes = pacotelist;
			BancoDados.conectar();
			Connection con2 = BancoDados.con;
			
			PreparedStatement pstm2;
			ResultSet rs2;
			List<Status> statuses1 = new ArrayList<Status>();
			
			String query2 = "select * from status ORDER BY (nome)";
			pstm2 = (PreparedStatement) con2.prepareStatement(query2);
			
			rs2 = pstm2.executeQuery();
			
			while (rs2.next()) {
			
				Status status = new Status(rs2.getString("nome"));
			
				statuses1.add(status);
			
			}
			statuses = statuses1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}// do controler cliente

		List<Funcionario> funcionarios = new ArrayList<Funcionario>();
		try {
			BancoDados.conectar();
			Connection con = BancoDados.con;
			
			PreparedStatement pstm;
			ResultSet rs;
			List<Funcionario> retorno = new ArrayList<Funcionario>();
			
			String query = "select * from funcionario ORDER BY pnome";
			pstm = (PreparedStatement) con.prepareStatement(query);
			
			rs = pstm.executeQuery();
			
			while (rs.next()) {
			
				Funcionario func = new Funcionario(rs.getString("username"),
						rs.getString("pnome"), rs.getString("unome"),
						rs.getString("cpf"), rs.getString("telefone"),
						rs.getString("endereco"), rs.getString("email"),
						rs.getString("password"), rs.getString("rg"));
			
				retorno.add(func);
			
			}
			funcionarios = retorno;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		render(clientes, pacotes, statuses, funcionarios);
	}

	public static void visualizar(String numPedido) {
		Pedido pedido = new Pedido();
		try {
			BancoDados.conectar();
			Connection con = BancoDados.con;
			PreparedStatement pstm;
			ResultSet rs;
			
			Pedido pedido1 = new Pedido();
			
			String query = "Select * from pedido where numPedido = ?";
			
			pstm = (PreparedStatement) con.prepareStatement(query);
			pstm.setString(1, numPedido);
			
			rs = pstm.executeQuery();
			
			while (rs.next()) {
			
				pedido1.cliente = rs.getString("cliente");
				pedido1.pacote = rs.getString("pacote");
				pedido1.status = rs.getString("status");
				pedido1.formaPag = rs.getString("formaPag");
				pedido1.funcionario = rs.getString("funcionario");
				pedido1.numPedido = rs.getString("numPedido");
				pedido1.dataInscricao = rs.getString("dataInscricao");
				pedido1.dataCadastro = rs.getString("dataCadastro");
			
			}
			pedido = pedido1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		render(pedido);

	}

	public static void editar(String cliente) {
		Pedido pedidos = new Pedido();
		try {
			BancoDados.conectar();
			Connection con = BancoDados.con;
			PreparedStatement pstm;
			ResultSet rs;
			
			Pedido pedido = new Pedido();
			
			String query = "Select * from pedido where numPedido = ?";
			
			pstm = (PreparedStatement) con.prepareStatement(query);
			pstm.setString(1, cliente);
			
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
			pedidos = pedido;
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		List<Cliente> clientes = new ArrayList<Cliente>();
		List<Pacote> pacotes = new ArrayList<Pacote>();
		List<Status> statuses = new ArrayList<Status>();
		List<Funcionario> funcionarios = new ArrayList<Funcionario>();
		try {
			BancoDados.conectar();
			Connection con = BancoDados.con;
			
			PreparedStatement pstm;
			ResultSet rs;
			List<Cliente> retorno = new ArrayList<Cliente>();
			
			String query = "select * from cliente ORDER BY (pnome)";
			pstm = (PreparedStatement) con.prepareStatement(query);
			
			rs = pstm.executeQuery();
			
			while (rs.next()) {
			
				Cliente func = new Cliente(rs.getString("pnome"),
						rs.getString("unome"), rs.getString("cpf"),
						rs.getString("telefone"), rs.getString("endereco"));
			
				retorno.add(func);
			
			}
			clientes = retorno;
			BancoDados.conectar();
			Connection con2 = BancoDados.con;
			
			PreparedStatement pstm2;
			ResultSet rs2;
			List<Pacote> pacotelist = new ArrayList<Pacote>();
			
			String query2 = "select * from status ORDER BY (nome)";
			pstm2 = (PreparedStatement) con2.prepareStatement(query2);
			
			rs2 = pstm2.executeQuery();
			
			while (rs2.next()) {
			
				Pacote pacote = new Pacote(rs2.getString("nome"), rs2.getInt("net"), rs2.getInt("tv"), rs2.getInt("telefone"));
			
				pacotelist.add(pacote);
			
			}
			pacotes = pacotelist;
			BancoDados.conectar();
			Connection con3 = BancoDados.con;
			
			PreparedStatement pstm3;
			ResultSet rs3;
			List<Status> statuses1 = new ArrayList<Status>();
			
			String query3 = "select * from status ORDER BY (nome)";
			pstm3 = (PreparedStatement) con3.prepareStatement(query3);
			
			rs3 = pstm3.executeQuery();
			
			while (rs3.next()) {
			
				Status status = new Status(rs3.getString("nome"));
			
				statuses1.add(status);
			
			}
			statuses = statuses1;
			BancoDados.conectar();
			Connection con1 = BancoDados.con;
			
			PreparedStatement pstm1;
			ResultSet rs1;
			List<Funcionario> retorno1 = new ArrayList<Funcionario>();
			
			String query1 = "select * from funcionario ORDER BY pnome";
			pstm1 = (PreparedStatement) con1.prepareStatement(query1);
			
			rs1 = pstm1.executeQuery();
			
			while (rs1.next()) {
			
				Funcionario func = new Funcionario(rs1.getString("username"),
						rs1.getString("pnome"), rs1.getString("unome"),
						rs1.getString("cpf"), rs1.getString("telefone"),
						rs1.getString("endereco"), rs1.getString("email"),
						rs1.getString("password"), rs1.getString("rg"));
			
				retorno1.add(func);
			
			}
			funcionarios = retorno1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		render(pedidos, clientes, pacotes, statuses, funcionarios);
	}

	public static void excluir(String numPedido) {
		try {
			BancoDados.conectar();
			Connection con = BancoDados.con;
			PreparedStatement pstm;
			
			String query = "DELETE FROM pedido where numPedido = ?";
			pstm = (PreparedStatement) con.prepareStatement(query);
			pstm.setString(1, numPedido);
			pstm.execute();
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
			BancoDados.conectar();
			Connection con = BancoDados.con;
			PreparedStatement pstm;
			ResultSet rs;
			
			Pedido pedido1 = new Pedido();
			
			String query = "Select * from pedido where numPedido = ?";
			
			pstm = (PreparedStatement) con.prepareStatement(query);
			pstm.setString(1, numPedido);
			
			rs = pstm.executeQuery();
			
			while (rs.next()) {
			
				pedido1.cliente = rs.getString("cliente");
				pedido1.pacote = rs.getString("pacote");
				pedido1.status = rs.getString("status");
				pedido1.formaPag = rs.getString("formaPag");
				pedido1.funcionario = rs.getString("funcionario");
				pedido1.numPedido = rs.getString("numPedido");
				pedido1.dataInscricao = rs.getString("dataInscricao");
				pedido1.dataCadastro = rs.getString("dataCadastro");
			
			}
			pedido = pedido1;
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
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		index();
	}

}
