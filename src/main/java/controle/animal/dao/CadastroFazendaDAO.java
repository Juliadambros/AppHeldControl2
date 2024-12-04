package controle.animal.dao;

import controle.animal.model.CadastroFazenda;

import java.sql.SQLException;
import java.util.List;

public interface CadastroFazendaDAO {
    public void insere(CadastroFazenda fazenda) throws SQLException;
    public void atualiza(CadastroFazenda fazenda) throws SQLException;
    public void remove(CadastroFazenda fazenda) throws SQLException;
    public CadastroFazenda buscaPorCNPJ(String cnpj) throws SQLException;


    public static boolean cnpjExists(String cnpj) throws SQLException {
        return false;
    }
}
