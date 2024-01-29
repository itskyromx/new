package com.school.projetofinal;

public class Medico extends Pessoa {
    //Atributos
    private String especialidade; //especialidade que o Médico tem

    /**
     * Construtor completo herdando atributos da superclasse Pessoa
     * @param id = classe herdada de Pessoa
     * @param nome = classe herdada de Pessoa
     * @param idade = classe herdada de Pessoa
     * @param especialidade = recebe uma String com a especialidade do Médico
     */
    public Medico(int id, String nome, int idade, String especialidade) {
        super(id, nome, idade);
        this.especialidade = especialidade;
    }

    /**
     * Obtém a especialidade
     * @return = retorna a especialidade do Médico
     */
    public String obterEspecialidade() {
        return especialidade;
    }

    /**
     * Atribui um novo valor ao atributo especialidade do objeto
     * @param especialidade = novo valor para a especialidade do Médico
     */
    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }
}
