package controle.animal.model;

import java.time.LocalDate;

public class CadastroAnimal {
    private int animalId;
    private String fazendaId;
    private String raca;
    private float peso;
    private String rebanho;
    private String procedencia;
    private String especie;
    private String sexo;
    private String alimentacao;
    private String especificacao;
    private LocalDate dataNascimento;
    private LocalDate dataChegada;

    public CadastroAnimal(){}

    public CadastroAnimal(int animalId, String fazendaId, String raca, float peso, String rebanho, String procedencia, String especie, String sexo, String alimentacao, String especificacao,
                          LocalDate dataNascimento, LocalDate dataChegada){
        this.animalId = animalId;
        this.fazendaId = fazendaId;
        this.peso = peso;
        this.raca = raca;
        this.rebanho = rebanho;
        this.procedencia = procedencia;
        this.especie = especie;
        this.sexo = sexo;
        this.alimentacao = alimentacao;
        this.especificacao = especificacao;
        this.dataNascimento = dataNascimento;
        this.dataChegada = dataChegada;
    }

    public int getAnimalId() {
        return animalId;
    }

    public String getFazendaId() {
        return fazendaId;
    }

    public String getRaca() {
        return raca;
    }

    public float getPeso() {
        return peso;
    }

    public String getProcedencia() {
        return procedencia;
    }

    public String getRebanho() {
        return rebanho;
    }

    public void setAnimalId(int animalId) {
        this.animalId = animalId;
    }

    public void setFazendaId(String fazendaId) {
        this.fazendaId = fazendaId;
    }

    public void setProcedencia(String procedencia) {
        this.procedencia = procedencia;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public void setRebanho(String rebanho) {
        this.rebanho = rebanho;
    }

    public LocalDate getDataChegada() {
        return dataChegada;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public String getEspecie() {
        return especie;
    }

    public String getAlimentacao() {
        return alimentacao;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getEspecificacao() {
        return especificacao;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public void setAlimentacao(String alimentacao) {
        this.alimentacao = alimentacao;
    }

    public void setEspecificacao(String especificacao) {
        this.especificacao = especificacao;
    }

    public void setDataChegada(LocalDate dataChegada) {
        this.dataChegada = dataChegada;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
}
