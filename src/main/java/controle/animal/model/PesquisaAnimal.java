package controle.animal.model;

public class PesquisaAnimal {
    private int animalId;

    public PesquisaAnimal(){}

    public PesquisaAnimal(int animalId){
        this.animalId = animalId;
    }

    public int getPesquisaAnimalId() {
        return animalId;
    }

    public void setPesquisaAnimalId(int animalId) {
        this.animalId = animalId;
    }
}
