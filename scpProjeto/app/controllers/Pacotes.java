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
		List<Pacote> pacotes = Pacote.getAllPacote();
		render(pacotes);
	}

	public static void inserir() {
		render();
	}

	public static void visualizar(String nome) {
		Pacote pacote = Pacote.encontrar_Pacote(nome);
		render(pacote);
	}

	public static void editar(String nome) {
		Pacote pacotes = Pacote.encontrar_Pacote(nome);
		render(pacotes);
	}

	public static void excluir(String nome) {
		Pacote.dellPacote(nome);
		index();
	}

	public static void cadastrar_pacote(@Required String nome, int net, int tv,
			int telefone) {
		Pacote pacote = new Pacote(nome, net, tv, telefone);
		if (validation.hasErrors()) {
			render("Pacotes/inserir.html", pacote);
		}

		pacote.savePacote();
		index();
	}

	public static void editar_pacote(String nome) {
		validation.required(request.params.get("nome"));

		Pacote pacote = Pacote.encontrar_Pacote(nome);

		if (validation.hasErrors()) {
			render("Pacotes/editar.html", pacote);
		}

		pacote.nome = request.params.get("nome");

		pacote.savePacote();
		index();
	}

	public static Pacote encontrar_Pacote(String nome) throws SQLException {
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
			pacote.net = rs.getInt("net");
			pacote.telefone = rs.getInt("telefone");

		}

		return pacote;

	}

	public static List<Pacote> getAllPacote() throws SQLException {
		BancoDados.conectar();
		Connection con = BancoDados.con;

		PreparedStatement pstm;
		ResultSet rs;
		List<Pacote> pacotelist = new ArrayList<Pacote>();

		String query = "select * from status ORDER BY (nome)";
		pstm = (PreparedStatement) con.prepareStatement(query);

		rs = pstm.executeQuery();

		while (rs.next()) {

			Pacote pacote = new Pacote(rs.getString("nome"), rs.getInt("net"), rs.getInt("tv"), rs.getInt("telefone"));

			pacotelist.add(pacote);

		}
		return pacotelist;

	}

	public static void dellPacote(String nome) throws SQLException {
		BancoDados.conectar();
		Connection con = BancoDados.con;
		PreparedStatement pstm;

		String query = "DELETE FROM pacote where nome = ?";
		pstm = (PreparedStatement) con.prepareStatement(query);
		pstm.setString(1, nome);
		pstm.execute();
	}

	public void savePacote(Pacote pacote) throws SQLException {
		BancoDados.conectar();
		Connection con = BancoDados.con;
		PreparedStatement pstm;

		String query = "insert into pacote (nome, net, tv, telefone) values (?,?,?,?)";
		pstm = (PreparedStatement) con.prepareStatement(query);

		
		pstm.setString(1, pacote.nome);
		pstm.setInt(2, pacote.net);
		pstm.setInt(3, pacote.tv);
		pstm.setInt(4, pacote.telefone);
		
		

		pstm.execute();
	}

}
