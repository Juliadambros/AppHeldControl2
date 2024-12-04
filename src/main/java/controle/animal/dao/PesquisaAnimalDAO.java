package controle.animal.dao;

import controle.animal.model.CadastroAnimal;

import java.sql.SQLException;

public interface PesquisaAnimalDAO {
    public CadastroAnimal buscaPorCodigo(int codigoAnimal) throws SQLException;
}
