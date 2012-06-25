package models;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import play.db.jpa.GenericModel.JPAQuery;

import com.mysql.jdbc.PreparedStatement;

public class FuncionarioSQL extends BancoDados {
	private static PreparedStatement pstm;
	private static Connection con = BancoDados.conectar();
	private static ResultSet rs;

	public static void salvar_func(String username, String pnome, String unome,
			String cpf, String telefone, String endereco, String email,
			String password, String passwordHash, String rg)
			throws SQLException {
		String query = "insert into funcionario ( username, pnome, unome, cpf, telefone, endereco, email, password, passwordHash, rg) values (?,?,?,?,?,?,?,?,?,?)";

		pstm = (PreparedStatement) con.prepareStatement(query);

		pstm.setString(1, username);
		pstm.setString(2, pnome);
		pstm.setString(3, unome);
		pstm.setString(4, cpf);
		pstm.setString(5, telefone);
		pstm.setString(6, endereco);
		pstm.setString(7, email);
		pstm.setString(8, password);
		pstm.setString(9, passwordHash);
		pstm.setString(10, rg);

		pstm.execute();
		pstm.clearParameters();
	}

	public static void remover_func(String cpf) throws SQLException {
		String query = "DELETE FROM funcionario where cpf = ?";
		pstm = (PreparedStatement) con.prepareStatement(query);
		pstm.setString(1, cpf);
		pstm.execute();
		pstm.close();
	}

	public static JPAQuery find(String string) {
		// TODO Auto-generated method stub
		return null;
	}
/*
	public static List<Funcionario> obterTodos() throws SQLException {

		List<Funcionario> retorno = new ArrayList<Funcionario>();

		String query = "select * from funcionario ORDER BY (name)";
		rs = pstm.executeQuery();

		while (rs.next()) {

			Funcionario
			retorno. = rs.getLong("id");
			retorno.desc = rs.getString("desc");
			retorno.editora = rs.getString("editora");
			retorno.nome = rs.getString("nome");
			retorno.preco = rs.getFloat("preco");
			retorno.qtd = rs.getInt("qtd");
			retorno.viewCount = rs.getInt("viewCount");

		}

		System.out.println("\n\n\n\nn\n\nn\n\n\nn\n\n" + retorno.categoria
				+ " " + retorno.nome + " " + retorno.editora);
		return retorno;
	}
*/
}