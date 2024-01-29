package com.school.projetofinal;

public class medicamento {

    private int id;
    private String Nome;
    private String Tipo;
    private int qtd;
    private double preco;

    public medicamento(int id, String Nome, String Tipo, int qtd, double preco) {
        this.id = id;
        this.Nome = Nome;
        this.Tipo = Tipo;
        this.qtd = qtd;
        this.preco = preco;
    }
    public int obterID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String Nome) {
        this.Nome = Nome;
    }

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String tipo) {
        this.Tipo = tipo;
    }

    public int getQuantidade() {
        return qtd;
    }

    public void setQuantidade(int qtd) {
        this.qtd = qtd;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }


}
