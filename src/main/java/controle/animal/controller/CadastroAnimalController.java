package controle.animal.controller;

import controle.animal.model.CadastroAnimal;
import controle.animal.dao.CadastroAnimalDAO;
import controle.animal.dao.CadastroAnimalDBDAO;
import controle.animal.dao.CadastroFazendaDAO;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

public class CadastroAnimalController {

    @FXML
    private ChoiceBox<String> ChoiceBoxAlimentary;

    @FXML
    private ChoiceBox<String> ChoiceBoxEspecial;

    @FXML
    private ChoiceBox<String> ChoiceBoxSpecification;

    @FXML
    private ChoiceBox<String> ChoiceBoxSex;

    @FXML
    private Button btnCadastrar;

    @FXML
    private Button btnCadastrarFazenda;

    @FXML
    private Button btnCadastroAnimal;

    @FXML
    private Button btnEditar;

    @FXML
    private Button btnExcluir;

    @FXML
    private Button btnTelaInicial;

    @FXML
    private Button btnPesquisaAnimal;

    @FXML
    private TextField fazendaId;

    @FXML
    private DatePicker datePickerChegada;

    @FXML
    private DatePicker datePickerNascimento;

    @FXML
    private TextField nBrinco;

    @FXML
    private TextField peso;

    @FXML
    private TextField procedencia;

    @FXML
    private TextField raca;

    @FXML
    private TextField rebanho;

    @FXML
    public void initialize() {
        ChoiceBoxEspecial.getItems().addAll("Bovino", "Equino", "Suíno");
        ChoiceBoxSex.getItems().addAll("Macho", "Fêmea");
        ChoiceBoxAlimentary.getItems().addAll("Pasto", "Concentrado");
        ChoiceBoxSpecification.getItems().addAll("Leiteiro", "Corte");
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
    private void handleCadastrar(ActionEvent event) {
        try {
            CadastroAnimalDAO CadastroAnimalDAO = new CadastroAnimalDBDAO();
            CadastroAnimal animal = new CadastroAnimal();
            animal.setAnimalId(Integer.parseInt(nBrinco.getText()));
            animal.setFazendaId(fazendaId.getText());
            animal.setRaca(raca.getText());
            animal.setPeso(Float.parseFloat(peso.getText()));
            animal.setRebanho(rebanho.getText());
            animal.setProcedencia(procedencia.getText());
            animal.setEspecie(ChoiceBoxEspecial.getValue());
            animal.setSexo(ChoiceBoxSex.getValue());
            animal.setAlimentacao(ChoiceBoxAlimentary.getValue());
            animal.setEspecificacao(ChoiceBoxSpecification.getValue());
            animal.setDataNascimento(datePickerNascimento.getValue());
            animal.setDataChegada(datePickerChegada.getValue());


            CadastroAnimalDAO.insere(animal);

            showAlert("Cadastro de Animal", "Animal cadastrado com sucesso!");

        } catch (SQLException e) {
            showErrorAlert("Erro ao cadastrar animal: " + e.getMessage(), e);
        } catch (NumberFormatException e) {
            showErrorAlert("Por favor, insira valores numéricos válidos!", e);
        }
    }

    @FXML
    private void handleEditar(ActionEvent event) {
        try {
            CadastroAnimalDAO CadastroAnimalDAO = new CadastroAnimalDBDAO();
            CadastroAnimal animal = new CadastroAnimal();
            animal.setAnimalId(Integer.parseInt(nBrinco.getText()));
            animal.setFazendaId(fazendaId.getText());
            animal.setRaca(raca.getText());
            animal.setPeso(Float.parseFloat(peso.getText()));
            animal.setRebanho(rebanho.getText());
            animal.setProcedencia(procedencia.getText());
            animal.setEspecie(ChoiceBoxEspecial.getValue());
            animal.setSexo(ChoiceBoxSex.getValue());
            animal.setAlimentacao(ChoiceBoxAlimentary.getValue());
            animal.setEspecificacao(ChoiceBoxSpecification.getValue());
            animal.setDataNascimento(datePickerNascimento.getValue());
            animal.setDataChegada(datePickerChegada.getValue());


            CadastroAnimalDAO.atualiza(animal);

            showAlert("Atualiza Animal", "Animal atualizado com sucesso!");

        } catch (SQLException e) {
            showErrorAlert("Erro ao cadastrar animal: " + e.getMessage(), e);
        } catch (NumberFormatException e) {
            showErrorAlert("Por favor, insira valores numéricos válidos!", e);
        }
    }

    @FXML
    private void handleExcluir(ActionEvent event) {
        try{
        int animalId = Integer.parseInt(nBrinco.getText()); // Obtém o ID do animal
        CadastroAnimal animal = new CadastroAnimal();
        animal.setAnimalId(animalId);

        CadastroAnimalDAO cadastroAnimalDAO = new CadastroAnimalDBDAO();
        cadastroAnimalDAO.remove(animal);

        showAlert("Exclusão de Animal", "Animal excluído com sucesso!");

        } catch (SQLException e) {
            showErrorAlert("Erro ao editar animal: " + e.getMessage(), e);
        } catch (NumberFormatException e) {
            showErrorAlert("Por favor, insira valores numéricos válidos!", e);
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

    public void preencherCampos(CadastroAnimal animal) {
        fazendaId.setText(animal.getFazendaId());
        raca.setText(animal.getRaca());
        peso.setText(String.valueOf(animal.getPeso()));
        rebanho.setText(animal.getRebanho());
        procedencia.setText(animal.getProcedencia());
        ChoiceBoxEspecial.setValue(animal.getEspecie());
        ChoiceBoxSex.setValue(animal.getSexo());
        ChoiceBoxAlimentary.setValue(animal.getAlimentacao());
        ChoiceBoxSpecification.setValue(animal.getEspecificacao());
        datePickerNascimento.setValue(animal.getDataNascimento());
        datePickerChegada.setValue(animal.getDataChegada());
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


