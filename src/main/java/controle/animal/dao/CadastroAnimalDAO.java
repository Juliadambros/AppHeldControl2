package controle.animal.dao;

import controle.animal.model.CadastroAnimal;

import java.sql.SQLException;
import java.util.List;

public interface CadastroAnimalDAO {
    public void insere(CadastroAnimal animal) throws SQLException;
    public void atualiza(CadastroAnimal animal) throws SQLException;
    public void remove(CadastroAnimal animal) throws SQLException;
    public CadastroAnimal buscaPorId(int animalId) throws SQLException;
}
