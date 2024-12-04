package controle.animal.controller;

import controle.animal.dao.CadastroFazendaDAO;
import controle.animal.dao.CadastroFazendaDBDAO;
import controle.animal.model.CadastroAnimal;
import controle.animal.model.CadastroFazenda;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CadastroFazendaController {

    @FXML
    private ChoiceBox<String> choiceBoxEstado;

    @FXML
    private TextField bairro;

    @FXML
    private Button btnCadastrar;

    @FXML
    private Button btnCadastroAnimal;

    @FXML
    private Button btnCadastroFazenda;

    @FXML
    private Button btnTelaInicial;

    @FXML
    private Button btnEditar;

    @FXML
    private Button btnExcluir;

    @FXML
    private Button btnPesquisaAnimal;

    @FXML
    private TextField cep;

    @FXML
    private TextField cidade;

    @FXML
    private TextField cnpj;

    @FXML
    private TextField email;

    @FXML
    private TextField fone;

    @FXML
    private TextField nCasa;

    @FXML
    private TextField nomeFazenda;

    @FXML
    private TextField ramo;

    @FXML
    private TextField rua;

    @FXML
    public void initialize() {
        choiceBoxEstado.getItems().addAll("AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO");
    }

    //depois da refatoração:
    @FXML
    private void handleTelaInicial(ActionEvent event) {
        handleTela(event, "/controle/animal/view/TelaInicial.fxml", "Tela Inicial");
    }

    @FXML
    private void handleCadastroAnimal(ActionEvent event) {
        handleTela(event, "/controle/animal/view/CadastroAnimal.fxml", "Cadastro de Animal");
    }

    @FXML
    private void handleCadastroFazenda(ActionEvent event) {
        handleTela(event, "/controle/animal/view/CadastroFazenda.fxml", "Cadastro de Fazenda");
    }

    @FXML
    private void handlePesquisaAnimal(ActionEvent event) {
        handleTela(event, "/controle/animal/view/PesquisaAnimal.fxml", "Pesquisa de Animal");
    }


    private void handleTela(ActionEvent event, String caminhoFXML, String titulo) {
        ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
        abrirNovaTela(caminhoFXML, titulo);
    }

    private void abrirNovaTela(String caminhoFXML, String titulo) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(caminhoFXML));
            Parent root = loader.load();
            Stage newStage = new Stage();
            Scene newScene = new Scene(root);
            newStage.setScene(newScene);
            newStage.setTitle(titulo);
            newStage.setResizable(false);
            newStage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showErrorAlert("Erro ao abrir a tela: " + titulo, e);
        }
    }

    // depois da refatoração:
    @FXML
    private void handleCadastrar(ActionEvent event) {
        try {
            //acrescentou o nomeFazenda na refatoração e o if de verificação
            if (!validarCadastro(nomeFazenda.getText(), cnpj.getText().replaceAll("[^0-9]", ""), fone.getText(), cep.getText(), email.getText())) {
                return;
            }

            CadastroFazenda fazenda = criarFazenda();

            CadastroFazendaDAO cadastroFazendaDAO = new CadastroFazendaDBDAO();
            cadastroFazendaDAO.insere(fazenda);

            showAlert("Cadastro de Fazenda", "Fazenda cadastrada com sucesso!");
        } catch (SQLException e) {
            showErrorAlert("Erro ao cadastrar fazenda: " + e.getMessage(), e);
        } catch (NumberFormatException e) {
            showErrorAlert("Por favor, insira valores numéricos válidos!", e);
        }
    }

    private boolean validarCadastro(String nomeFazenda,String cnpj, String telefone, String cep, String email) throws SQLException {
        if (!isValidNomeFazenda(nomeFazenda)) {
            showErrorAlert("Nome da Fazenda inválida: Digite o nome da Fazenda.", null);
            return false;
        }
        if (!isValidCNPJ(cnpj)) {
            showErrorAlert("CNPJ inválido: O CNPJ deve ter 14 dígitos.", null);
            return false;
        }
        if (!isValidTelefone(telefone)) {
            showErrorAlert("Telefone inválido: O telefone deve ter entre 10 e 11 dígitos.", null);
            return false;
        }
        if (CadastroFazendaDAO.cnpjExists(cnpj)) {
            showErrorAlert("CNPJ inválido: Este CNPJ já está cadastrado!", null);
            return false;
        }
        if (!isValidCep(cep)) {
            showErrorAlert("CEP inválido: O CEP deve ter 8 dígitos.", null);
            return false;
        }
        if (!isValidEmail(email)) {
            showErrorAlert("E-mail inválido: Por favor, insira um e-mail válido.", null);
            return false;
        }
        return true;
    }

    private CadastroFazenda criarFazenda() {
        CadastroFazenda fazenda = new CadastroFazenda();
        fazenda.setCnpj(cnpj.getText());
        fazenda.setNome(nomeFazenda.getText());
        fazenda.setRamo(ramo.getText());
        fazenda.setTelefone(fone.getText());
        fazenda.setEmail(email.getText());
        fazenda.setCep(cep.getText());
        fazenda.setRua(rua.getText());
        fazenda.setnCasa(Integer.parseInt(nCasa.getText()));
        fazenda.setBairro(bairro.getText());
        fazenda.setCidade(cidade.getText());
        fazenda.setEstado(choiceBoxEstado.getValue());
        return fazenda;
    }

    // depois da refatoração:
    @FXML
    private void handleEditar(ActionEvent event) {
        try {
            //acrescentou o nomeFazenda na refatoração e o if de verificação
            if (!validarCadastro(nomeFazenda.getText(), cnpj.getText().replaceAll("[^0-9]", ""), fone.getText(), cep.getText(), email.getText())) {
                return;
            }

            CadastroFazenda fazenda = criarFazenda();

            CadastroFazendaDAO cadastroFazendaDAO = new CadastroFazendaDBDAO();
            cadastroFazendaDAO.atualiza(fazenda);

            showAlert("Edição de Fazenda", "Fazenda atualizada com sucesso!");
        } catch (SQLException e) {
            showErrorAlert("Erro ao editar fazenda: " + e.getMessage(), e);
        } catch (NumberFormatException e) {
            showErrorAlert("Por favor, insira valores numéricos válidos!", e);
        }
    }

    @FXML
    private void handleExcluir(ActionEvent event) {
        try {
            CadastroFazenda fazenda = new CadastroFazenda();
            fazenda.setCnpj(cnpj.getText()); // CNPJ necessário para identificar a fazenda

            CadastroFazendaDAO cadastroFazendaDAO = new CadastroFazendaDBDAO();
            cadastroFazendaDAO.remove(fazenda);

            showAlert("Exclusão de Fazenda", "Fazenda excluída com sucesso!");
        } catch (SQLException e) {
            showErrorAlert("Erro ao excluir fazenda: " + e.getMessage(), e);
        }
    }


    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showErrorAlert(String message, Exception e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
        e.printStackTrace();
    }

    //novo código após refatorar
    private boolean isValidNomeFazenda(String nomeFazenda) {
        return nomeFazenda != null && !nomeFazenda.trim().isEmpty();
    }

    private boolean isValidCep(String cep) {
        return cep.matches("\\d{5}-?\\d{3}");
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private boolean isValidCNPJ(String cnpj) {
        String cnpjNumerico = cnpj.replaceAll("[^0-9]", "");
        return cnpjNumerico.length() == 14;
    }

    private boolean isValidTelefone(String telefone) {
        String telefoneNumerico = telefone.replaceAll("[^0-9]", "");
        return telefoneNumerico.length() >= 10 && telefoneNumerico.length() <= 11;
    }

}



