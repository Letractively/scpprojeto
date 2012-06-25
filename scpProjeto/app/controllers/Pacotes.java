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

public class Pacotes extends Controller {

	public static void index() {
		List<Pacote> pacotes = new ArrayList<Pacote>();
		try {
			BancoDados.conectar();
			Connection con = BancoDados.con;
			
			PreparedStatement pstm;
			ResultSet rs;
			List<Pacote> pacotelist = new ArrayList<Pacote>();
			
			String query = "select * from pacote ORDER BY (nome)";
			pstm = (PreparedStatement) con.prepareStatement(query);
			
			rs = pstm.executeQuery();
			
			while (rs.next()) {
			
				Pacote pacote = new Pacote(rs.getString("nome"), rs.getInt("internet"), rs.getInt("tv"), rs.getInt("telefone"));
			
				pacotelist.add(pacote);
			
			}
			pacotes = pacotelist;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		render(pacotes);
	}

	public static void inserir() {
		render();
	}

	public static void visualizar(String nome) {
		Pacote pacote = new Pacote();
		try {
			BancoDados.conectar();
			Connection con = BancoDados.con;
			PreparedStatement pstm;
			ResultSet rs;
			
			Pacote pacote1 = new Pacote();
			
			String query = "SELECT * FROM pacote where nome = ?";
			
			pstm = (PreparedStatement) con.prepareStatement(query);
			pstm.setString(1, nome);
			
			rs = pstm.executeQuery();
			
			while (rs.next()) {
			
				pacote1.nome = rs.getString("nome");
				pacote1.internet = rs.getInt("internet");
				pacote1.telefone = rs.getInt("telefone");
			
			}
			pacote = pacote1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		render(pacote);
	}

	public static void editar(String nome) {
		Pacote pacotes= new Pacote();
		try {
			BancoDados.conectar();
			Connection con = BancoDados.con;
			PreparedStatement pstm;
			ResultSet rs;
			
			Pacote pacote = new Pacote();
			
			String query = "SELECT * FROM pacote where nome = ?";
			
			pstm = (PreparedStatement) con.prepareStatement(query);
			pstm.setString(1, nome);
			
			rs = pstm.executeQuery();
			
			while (rs.next()) {
			
				pacote.nome = rs.getString("nome");
				pacote.internet = rs.getInt("internet");
				pacote.telefone = rs.getInt("telefone");
			
			}
			pacotes = pacote;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		render(pacotes);
	}

	public static void excluir(String nome) {
		try {
			BancoDados.conectar();
			Connection con = BancoDados.con;
			PreparedStatement pstm;
			
			String query = "DELETE FROM pacote where nome = ?";
			pstm = (PreparedStatement) con.prepareStatement(query);
			pstm.setString(1, nome);
			pstm.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		index();
	}

	public static void cadastrar_pacote(@Required String nome, int internet, int tv,
			int telefone) {
		Pacote pacote = new Pacote(nome, internet, tv, telefone);
		if (validation.hasErrors()) {
			render("Pacotes/inserir.html", pacote);
		}

		try {
			BancoDados.conectar();
			Connection con = BancoDados.con;
			PreparedStatement pstm;
			
			String query = "insert into pacote (nome, internet, tv, telefone) values (?,?,?,?)";
			pstm = (PreparedStatement) con.prepareStatement(query);
			
			
			pstm.setString(1, pacote.nome);
			pstm.setInt(2, pacote.internet);
			pstm.setInt(3, pacote.tv);
			pstm.setInt(4, pacote.telefone);
			
			
			
			pstm.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		index();
	}

	public static void editar_pacote(String nome) {
		validation.required(request.params.get("nome"));

		Pacote pacote = new Pacote();
		try {
			BancoDados.conectar();
			Connection con = BancoDados.con;
			PreparedStatement pstm;
			ResultSet rs;
			
			Pacote pacote1 = new Pacote();
			
			String query = "SELECT * FROM pacote where nome = ?";
			
			pstm = (PreparedStatement) con.prepareStatement(query);
			pstm.setString(1, nome);
			
			rs = pstm.executeQuery();
			
			while (rs.next()) {
			
				pacote1.nome = rs.getString("nome");
				pacote1.internet = rs.getInt("internet");
				pacote1.telefone = rs.getInt("telefone");
			
			}
			pacote = pacote1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (validation.hasErrors()) {
			render("Pacotes/editar.html", pacote);
		}

		pacote.nome = request.params.get("nome");

		try {
			BancoDados.conectar();
			Connection con = BancoDados.con;
			PreparedStatement pstm;
			
			String query = "insert into pacote (nome, internet, tv, telefone) values (?,?,?,?)";
			pstm = (PreparedStatement) con.prepareStatement(query);
			
			
			pstm.setString(1, pacote.nome);
			pstm.setInt(2, pacote.internet);
			pstm.setInt(3, pacote.tv);
			pstm.setInt(4, pacote.telefone);
			
			
			
			pstm.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		index();
	}

}
