package com.school.projetofinal;

public class Paciente extends Pessoa {
    //Atributos
    private String doenca; //doença que o Paciente possui

    /**
     * Construtor completo herdando atributos da superclasse Pessoa
     *
     * @param id     = classe herdada de Pessoa
     * @param nome   = classe herdada de Pessoa
     * @param idade  = classe herdada de Pessoa
     * @param doenca = recebe uma String com a doença do Paciente
     */
    public Paciente(int id, String nome, int idade, String doenca) {
        super(id, nome, idade);
        this.doenca = doenca;
    }

    /**
     * Obtém a doença
     *
     * @return = retorna a doença do Paciente
     */
    public String obterDoenca() {
        return doenca;
    }

    /**
     * Atribui um novo valor ao atributo doença do objeto
     *
     * @param doenca = novo valor para a doença do Paciente
     */
    public void setDoenca(String doenca) {
        this.doenca = doenca;
    }

}