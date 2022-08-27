package repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entities.Pessoa;
import factories.ConnectionFactory;
import interfaces.IRepository;

public class PessoaRepository implements IRepository<Pessoa> {

	@Override
	public void create(Pessoa obj) throws Exception {

		//abrindo uma conexão com o banco de dados
		Connection connection = ConnectionFactory.createConnection();

		//executando um comando SQL no banco de dados
		PreparedStatement statement = connection.prepareStatement("INSERT INTO PESSOA(NOME, CPF, EMAIL) VALUES(?, ?, ?)");
		statement.setString(1, obj.getNome());
		statement.setString(2, obj.getCpf());
		statement.setString(3, obj.getEmail());
		statement.execute();
		
		//fechar a conexão
		connection.close();
	}

	@Override
	public void update(Pessoa obj) throws Exception {

		//abrindo uma conexão com o banco de dados
		Connection connection = ConnectionFactory.createConnection();
		
		//escrevendo um comando SQL
		PreparedStatement statement = connection.prepareStatement("UPDATE PESSOA SET NOME=?, EMAIL=?, CPF=? WHERE IDPESSOA=?");
		statement.setString(1, obj.getNome());
		statement.setString(2, obj.getEmail());
		statement.setString(3, obj.getCpf());
		statement.setInt(4, obj.getIdPessoa());
		statement.execute();
		
		//fechar a conexão
		connection.close();
	}

	@Override
	public void delete(Integer id) throws Exception {

		//abrindo uma conexão com o banco de dados
		Connection connection = ConnectionFactory.createConnection();

		//escrevendo um comando SQL
		PreparedStatement statement = connection.prepareStatement("DELETE FROM PESSOA WHERE IDPESSOA=?");
		statement.setInt(1, id);
		statement.execute();
		
		//fechar a conexão
		connection.close();
	}

	@Override
	public List<Pessoa> findAll() throws Exception {

		//abrindo uma conexão com o banco de dados
		Connection connection = ConnectionFactory.createConnection();
		
		//escrevendo um comando SQL
		PreparedStatement statement = connection.prepareStatement("SELECT * FROM PESSOA");
		ResultSet resultSet = statement.executeQuery(); //executando a consulta e capturando os resultados
		
		//abrindo uma lista de pessoa (sem elementos)
		List<Pessoa> lista = new ArrayList<Pessoa>();
		
		//percorrendo o ResultSet, varrendo cada registro obtido da consulta
		//aqui estamos lendo em loop cada linha da tabela obtida na consulta
		while(resultSet.next())
		{
			//Capturando os dados do registro que estamos lendo (pessoa)
			Pessoa pessoa = new Pessoa();
			
			pessoa.setIdPessoa(resultSet.getInt("IDPESSOA"));
			pessoa.setNome(resultSet.getString("NOME"));
			pessoa.setCpf(resultSet.getString("CPF"));
			pessoa.setEmail(resultSet.getString("EMAIL"));
			
			lista.add(pessoa); //adicionando pessoa na lista
		}
		
		connection.close(); //fechando conexão
		return lista; //retornando a lista
	}

	@Override
	public Pessoa findById(Integer id) throws Exception {

		//abrindo conexão com o banco de dados
		Connection connection = ConnectionFactory.createConnection();
		
		//executando comando SQL
		PreparedStatement statement = connection.prepareStatement("SELECT * FROM PESSOA WHERE IDPESSOA=?");
		statement.setInt(1, id);
		
		ResultSet resultSet = statement.executeQuery(); //executando a consulta e retornando os registros
		
		Pessoa pessoa = null; //null -> vazio (sem espaço de memória)
		
		//verificando se algum registro foi encontrado
		if(resultSet.next()) { //se algum registro foi encontrado
			
			pessoa = new Pessoa(); //instanciando a classe Pessoa
			
			pessoa.setIdPessoa(resultSet.getInt("IDPESSOA"));
			pessoa.setNome(resultSet.getString("NOME"));
			pessoa.setCpf(resultSet.getString("CPF"));
			pessoa.setEmail(resultSet.getString("EMAIL"));
		}
		
		connection.close(); //fechando conexão
		return pessoa; //retornando o objeto Pessoa
	}

}












