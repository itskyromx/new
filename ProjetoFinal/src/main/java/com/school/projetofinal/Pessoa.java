package com.school.projetofinal;

public class Pessoa {
    //Atributos
    private int id; //id do paciente ou médico
    private String nome; //nome do paciente ou médico
    private int idade; //idade do paciente ou médico

    //Métodos construtores

    /**
     * Construtor padrão
     * @param id = recebe um inteiro
     */
    public Pessoa(int id) {
        this.id = id;
    }


    /**
     * Construtor completo
     * @param nome = recebe uma String com o nome do paciente ou médico
     * @param idade = recebe um inteiro com a idade do paciente ou médico
     * @param id = recebe um inteiro com o id do paciente/médico
     */
    public Pessoa(int id, String nome, int idade) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
    }

    //MÉTODOS GETTER E SETTER

    /**
     * Obtém o id
     * @return = retorna o id do paciente/médico
     */
    public int obterID() {
        return id;
    }

    /**
     * Obtém o nome
     * @return = retorna o nome do paciente/médico
     */
    public String obterNome() {
        return nome;
    }

    /**
     * Obtém a idade
     * @return = retorna a idade do paciente/médico
     */
    public int obterIdade() {
        return idade;
    }

    /**
     * Atribui um novo valor ao atributo id do objeto
     * @param id = novo valor para o id de Pessoa
     */
    public void setID(int id) {
        this.id = id;
    }

    /**
     * Atribui um novo valor ao atributo nome do objeto
     * @param nome = novo valor para o nome de Pessoa
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Atribui um novo valor ao atributo idade do objeto
     * @param idade = novo valor para a idade de Pessoa
     */
    public void setIdade(int idade) {
        this.idade = idade;
    }

}
