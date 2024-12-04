package controle.animal.controller;

import controle.animal.dao.PesquisaAnimalDBDAO;

import controle.animal.model.CadastroAnimal;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import controle.animal.dao.CadastroAnimalDAO;
import java.io.IOException;
import java.sql.SQLException;

public class PesquisaAnimalController {

    @FXML
    private Button btnCadastrarFazenda;

    @FXML
    private Button btnCadastroAnimal;

    @FXML
    private Button btnPesquisaAnimal;

    @FXML
    private Button btnPesquisar;

    @FXML
    private Button btnTelaInicial;

    @FXML
    private TextField nBrinco;

    @FXML
    public void initialize() {
        // Inicialização, se necessário
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

    @FXML
    private void handlePesquisar(ActionEvent event) {
        try {
            int codigoAnimal = Integer.parseInt(nBrinco.getText()); // Obtém o número do brinco do campo de texto

            PesquisaAnimalDBDAO pesquisaAnimalDAO = new PesquisaAnimalDBDAO();
            CadastroAnimal animal = pesquisaAnimalDAO.buscaPorCodigo(codigoAnimal);

            if (animal != null) {
                // Carregar a tela de CadastroAnimal e preencher os campos
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/controle/animal/view/CadastroAnimal.fxml"));
                Parent root = loader.load();
                CadastroAnimalController cadastroAnimalController = loader.getController(); // Obtém o controller da nova tela

                // Preencher os campos do CadastroAnimal com os dados do animal encontrado
                cadastroAnimalController.preencherCampos(animal);

                Stage newStage = new Stage();
                newStage.setScene(new Scene(root));
                newStage.setTitle("Cadastro de Animal");
                newStage.setResizable(false);
                newStage.show();
                // Fecha a tela atual
                ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
            } else {
                showAlert("Animal não encontrado", "Nenhum animal encontrado com o número do brinco informado.");
            }
        } catch (NumberFormatException e) {
            showErrorAlert("Por favor, insira um número de brinco válido!", e);
        } catch (SQLException e) {
            showErrorAlert("Erro ao buscar animal: " + e.getMessage(), e);
        } catch (IOException e) {
            showErrorAlert("Erro ao abrir a tela de Cadastro de Animal.", e);
        }
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

    private void showErrorAlert(String message, Exception e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.setResizable(true);
        alert.setHeight(150);
        alert.setWidth(300);
        alert.showAndWait();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }




}
