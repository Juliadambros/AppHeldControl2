package controle.animal.dao;

import controle.animal.model.CadastroAnimal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PesquisaAnimalDBDAO implements PesquisaAnimalDAO {
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
    public CadastroAnimal buscaPorCodigo(int codigoAnimal) throws SQLException {
        open();
        sql = "SELECT * FROM CadastroAnimal WHERE animalId = ?";
        statement = connection.prepareStatement(sql);
        statement.setInt(1, codigoAnimal); // Usando o código do animal
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
