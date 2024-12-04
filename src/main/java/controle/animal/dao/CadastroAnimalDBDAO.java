package controle.animal.dao;

import controle.animal.model.CadastroAnimal;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CadastroAnimalDBDAO implements CadastroAnimalDAO {
    private String sql;
    private Connection connection;
    private PreparedStatement statement;
    private ResultSet result;

    private void open() throws SQLException {
        connection = Conexao.getConnection(Conexao.url, Conexao.usuario, Conexao.senha);
    }

    private void close() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

    @Override
    public void insere(CadastroAnimal animal) throws SQLException {
        open();
        sql = "INSERT INTO CadastroAnimal (animalId, fazendaId, raca, peso, rebanho, " +
                "procedencia, especie, sexo, alimentacao, especificacao, dataNascimento, dataChegada) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        statement = connection.prepareStatement(sql);
        statement.setInt(1, animal.getAnimalId()); // Definindo o número do brinco como animalId
        statement.setString(2, animal.getFazendaId());
        statement.setString(3, animal.getRaca());
        statement.setFloat(4, animal.getPeso());
        statement.setString(5, animal.getRebanho());
        statement.setString(6, animal.getProcedencia());
        statement.setString(7, animal.getEspecie());
        statement.setString(8, animal.getSexo());
        statement.setString(9, animal.getAlimentacao());
        statement.setString(10, animal.getEspecificacao());
        statement.setDate(11, Date.valueOf(animal.getDataNascimento()));
        statement.setDate(12, Date.valueOf(animal.getDataChegada()));
        statement.executeUpdate();
        close();
    }

    @Override
    public void atualiza(CadastroAnimal animal) throws SQLException {
        open();
        sql = "UPDATE CadastroAnimal SET fazendaId=?, raca=?, peso=?, rebanho=?, " +
                "procedencia=?, especie=?, sexo=?, alimentacao=?, especificacao=?, dataNascimento=?, dataChegada=? " +
                "WHERE animalId=?";
        statement = connection.prepareStatement(sql);
        statement.setString(1, animal.getFazendaId());
        statement.setString(2, animal.getRaca());
        statement.setFloat(3, animal.getPeso());
        statement.setString(4, animal.getRebanho());
        statement.setString(5, animal.getProcedencia());
        statement.setString(6, animal.getEspecie());
        statement.setString(7, animal.getSexo());
        statement.setString(8, animal.getAlimentacao());
        statement.setString(9, animal.getEspecificacao());
        statement.setDate(10, Date.valueOf(animal.getDataNascimento()));
        statement.setDate(11, Date.valueOf(animal.getDataChegada()));
        statement.setInt(12, animal.getAnimalId());

        statement.executeUpdate();
        close();
    }

    @Override
    public void remove(CadastroAnimal animal) throws SQLException {
        open();
        sql = "DELETE FROM CadastroAnimal WHERE animalId=?";
        statement = connection.prepareStatement(sql);
        statement.setInt(1, animal.getAnimalId());
        statement.executeUpdate();
        close();
    }

    @Override
    public CadastroAnimal buscaPorId(int animalId) throws SQLException {
        open();
        sql = "SELECT * FROM CadastroAnimal WHERE animalId=?";
        statement = connection.prepareStatement(sql);
        statement.setInt(1, animalId);
        result = statement.executeQuery();

        CadastroAnimal animal = null;
        if (result.next()) {
            animal = new CadastroAnimal();
            animal.setAnimalId(result.getInt("animalId"));
            animal.setFazendaId(result.getString("fazendaId"));
            animal.setRaca(result.getString("raca"));
            animal.setPeso(result.getFloat("peso"));
            animal.setRebanho(result.getString("rebanho"));
            animal.setProcedencia(result.getString("procedencia"));
            animal.setEspecie(result.getString("especie"));
            animal.setSexo(result.getString("sexo"));
            animal.setAlimentacao(result.getString("alimentacao"));
            animal.setEspecificacao(result.getString("especificacao"));
            animal.setDataNascimento(result.getDate("dataNascimento").toLocalDate());
            animal.setDataChegada(result.getDate("dataChegada").toLocalDate());
        }
        close();
        return animal;
    }

}
