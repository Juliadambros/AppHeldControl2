package controle.animal.model;

import javafx.scene.control.ChoiceBox;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class CadastroAnimalTest {

    @Test
    void getAnimalId() {
        CadastroAnimal animal1 = new CadastroAnimal();
        CadastroAnimal animal2 = animal1;

        animal1.setAnimalId(123);

        assertSame(animal1, animal2, "Ambos devem referenciar a mesma instância de CadastroAnimal.");
    }

    @Test
    void setPeso() {
        CadastroAnimal animal = new CadastroAnimal();
        animal.setPeso(450.5f);

        assertTrue(animal.getPeso() == 450.5f, "O peso deveria ser 450.5.");

        animal.setPeso(-10f);
        assertFalse(animal.getPeso() < 0, "O peso não pode ser negativo.");
    }

    @Test
    void setRaca() {
        CadastroAnimal animal = new CadastroAnimal();
        animal.setRaca("Angus");
        assertTrue(animal.getRaca().equals("Angus"), "A raça deveria ser 'Angus'.");

        animal.setRaca(null);
        assertFalse(animal.getRaca() != null && !animal.getRaca().isEmpty(), "A raça não deve ser válida se for nula.");
    }

    @Test
    void getSexo() {
        CadastroAnimal animal = new CadastroAnimal();
        animal.setSexo("Macho");

        assertNotEquals("Femea", animal.getSexo(), "O valor 'Macho' não deve ser aceito como sexo válido.");
    }

    @Test
    void getProcedenciaTese(){
        CadastroAnimal animal = new CadastroAnimal();
        animal.setProcedencia("importado");
        String resultado = animal.getProcedencia();
        assertEquals(resultado, animal.getProcedencia(), "o resultado deve ser importado ");
    }

    @Test
    void setDataChegadaTest(){
        CadastroAnimal animal1 = new CadastroAnimal();
        CadastroAnimal animal2 = new CadastroAnimal();

        LocalDate dataChegada = LocalDate.of(2024, 11, 29);
        //LocalDate dataChegada1 = LocalDate.of(2023, 11, 29);
        animal1.setDataChegada(dataChegada);
        animal2.setDataChegada(dataChegada);
        //animal2.setDataChegada(dataChegada1);

        assertEquals(animal1.getDataChegada(), animal2.getDataChegada(), "As datas de chegada devem ser iguais.");

    }

}