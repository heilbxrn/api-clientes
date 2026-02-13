package br.com.cotiinformatica.api_clientes.repositories;

import br.com.cotiinformatica.api_clientes.entities.Cliente;
import br.com.cotiinformatica.api_clientes.factories.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ClienteRepository {

    @Autowired
    private ConnectionFactory connectionFactory;

    public void inserir(Cliente cliente) throws Exception {

        var sql = """
                    INSERT INTO clientes(nome, email, telefone)
                    VALUES(?, ?, ?)
                """;

        var connection = connectionFactory.getConnection();

        var statement = connection.prepareStatement(sql);

        statement.setString(1, cliente.getNome());
        statement.setString(2, cliente.getEmail());
        statement.setString(3, cliente.getTelefone());
        statement.execute();

        connection.close();
    }

    public boolean atualizar(Cliente cliente) throws Exception {


        var sql = """
                    UPDATE clientes 
                    SET
                        nome = ?, email = ?, telefone = ?
                    WHERE
                        id = ? 
                """;


        var connection = connectionFactory.getConnection();


        var statement = connection.prepareStatement(sql);
        statement.setString(1, cliente.getNome());
        statement.setString(2, cliente.getEmail());
        statement.setString(3, cliente.getTelefone());
        statement.setInt(4, cliente.getId());
        var result = statement.executeUpdate();


        connection.close();


        return result > 0;
    }

    public boolean excluir(Integer id) throws Exception {

        var sql = """
                    UPDATE clientes
                    SET
                        ativo = 0
                    WHERE
                        id = ?
                """;

        var connection = connectionFactory.getConnection();

        var statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        var result = statement.executeUpdate();

        connection.close();

        return result > 0;

    }


    public List<Cliente> obterPorNome(String nome) throws Exception {

        var sql = """
                    SELECT id, nome, email, telefone, datacadastro, ativo
                    FROM clientes
                    WHERE ativo = 1 AND nome ILIKE ?
                """;

        var connection = connectionFactory.getConnection();

        var statement = connection.prepareStatement(sql);

        statement.setString(1, "%" + nome + "%");

        var result = statement.executeQuery();

        var lista = new ArrayList<Cliente>();

        while(result.next()) {
            var cliente = new Cliente();

            cliente.setId(result.getInt("id"));
            cliente.setNome(result.getString("nome"));
            cliente.setEmail(result.getString("email"));
            cliente.setTelefone(result.getString("telefone"));
            cliente.setDataCadastro(result.getTimestamp("datacadastro"));
            cliente.setAtivo(result.getInt("ativo"));

            lista.add(cliente);
        }

        connection.close();

        return lista;
    }



}
