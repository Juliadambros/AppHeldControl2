package controle.animal.model;

public class CadastroFazenda {
    private String nome;
    private String cnpj;
    private String ramo;
    private String telefone;
    private String email;
    private String cep;
    private String rua;
    private int nCasa;
    private String bairro;
    private String cidade;
    private String estado;

    public CadastroFazenda() {}

    public CadastroFazenda(String nome,String cnpj,String ramo,String telefone,String email,String cep,String rua,int nCasa, String bairro, String cidade, String estado){
        this.nome = nome;
        this.cnpj = cnpj;
        this.ramo = ramo;
        this.telefone = telefone;
        this.email = email;
        this.cep = cep;
        this.rua = rua;
        this.nCasa = nCasa;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
    }

    public String getNome() {
        return nome;
    }

    public int getnCasa() {
        return nCasa;
    }

    public String getBairro() {
        return bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public String getCnpj() {
        return cnpj;
    }

    public String getCep() {
        return cep;
    }

    public String getEmail() {
        return email;
    }

    public String getRamo() {
        return ramo;
    }

    public String getRua() {
        return rua;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public void setnCasa(int nCasa) {
        this.nCasa = nCasa;
    }

    public void setRamo(String ramo) {
        this.ramo = ramo;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
