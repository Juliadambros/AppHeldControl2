package controle.animal.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

class CadastroFazendaTest {

    @Test
    void getNome() {
        CadastroFazenda fazenda = new CadastroFazenda();
        fazenda.setNome("Fazenda Vitória");
        String resultado = fazenda.getNome();
        assertEquals(resultado, fazenda.getNome(), "o resultado deve ser Fazenda Vitória ");
    }

    @Test
    void getEmail() {
        CadastroFazenda fazenda = new CadastroFazenda();
        fazenda.setEmail("email@gmail.com");
        assertEquals("email@gmail.com", fazenda.getEmail(), "Validação de email não implementada!");
    }

    @Test
    void testConstrutorSemParametros() {
        CadastroFazenda fazenda = new CadastroFazenda();
        assertNull(fazenda.getNome(), "O nome deve ser nulo ao usar o construtor sem parâmetros.");
        assertNull(fazenda.getCnpj(), "O CNPJ deve ser nulo ao usar o construtor sem parâmetros.");
        assertNull(fazenda.getEstado(), "O estado deve ser nulo ao usar o construtor sem parâmetros.");
    }

    @Test
    void testTelefone() {
        CadastroFazenda fazenda = new CadastroFazenda();
        fazenda.setTelefone("44999999999");
        assertEquals("44999999999", fazenda.getTelefone(), " Validação de telefone não implementada");
    }

    @Test
    void testCnpj() {
        CadastroFazenda fazenda = new CadastroFazenda();

        String cnpjValido = "1234567800019";
        fazenda.setCnpj(cnpjValido);

        assertEquals("123456789123", fazenda.getCnpj(), "cnpj invalido");
    }

    @Test
    void testCep() {
        CadastroFazenda fazenda = new CadastroFazenda();

        String cepValido = "9123456";
        fazenda.setCep(cepValido);

        assertEquals("6676767", fazenda.getCep(), "cep invalido");
    }
}