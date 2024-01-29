package com.school.projetofinal;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;

public class Settings {
    private static Stage primaryStage;
    public static Stage getPrimaryStage(){
        return primaryStage;
    }
    public static void setPrimaryStage(Stage primaryStage){
        Settings.primaryStage = primaryStage;
    }
    protected static ObservableList<medicamento> listaMedicamento = FXCollections.observableArrayList();

    public static ObservableList<medicamento> obterListaMedicamento() {
        return listaMedicamento;
    }
    protected static ObservableList<Paciente> listaPaciente = FXCollections.observableArrayList();
    public static ObservableList<Paciente> obterListaPaciente() {
        return listaPaciente;
    }
    protected static ObservableList<Medico> listaMedico = FXCollections.observableArrayList();
    public static ObservableList<Medico> obterListaMedico() {
        return listaMedico;
    }
    private static medicamento medicamentoEdit;
    private static Paciente pacienteEdit;
    private static Medico medicoEdit;

    public static void setPacienteEdit(Paciente pacienteEdit){
        pacienteEdit = pacienteEdit;
    }

    public static void setMedicoEdit(Medico medicoEdit){
        medicoEdit = medicoEdit;
    }

    public static void setMedicamentoEdit(medicamento MedicamentoEdit){
        MedicamentoEdit = MedicamentoEdit;
    }
    public static void listaMedicamento(){
        listaMedicamento.add(new medicamento(1,"Paracetamol","Febre",250,40.00));
        listaMedicamento.add(new medicamento(2,"Ibuprofeno ","Inflamação",255,14.00));
        listaMedicamento.add(new medicamento(3,"Amoxicilina","Infecções bacterianas",63,10.60));
        listaMedicamento.add(new medicamento(4,"Omeprazol","Úlceras",500,17.90));
        listaMedicamento.add(new medicamento(5,"Liptor","Colestrol",700,15.90));
        listaMedicamento.add(new medicamento(6,"Lisinopril","Pressão arterial",129,20.99));
        listaMedicamento.add(new medicamento(7,"Metformina","Diabetes tipo 2",199,2.70));
        listaMedicamento.add(new medicamento(8,"Ventolin","Asma",345,2.70));
        listaMedicamento.add(new medicamento(9,"Diazepam","Distúrbios neuromusculares",646,4.50));
        listaMedicamento.add(new medicamento(10,"Prozac","Antidepressivo",643,2.40));
        listaMedicamento.add(new medicamento(11,"Coumadin","Coágulos sanguíneos",458,2.40));
        listaMedicamento.add(new medicamento(12,"Cipro","Infecções bacterianas",1525,2.70));
        listaMedicamento.add(new medicamento(13,"Zantac","Úlceras gástricas",756,4.30));
        listaMedicamento.add(new medicamento(14,"Lasix","Edema",757,1.59));
        listaMedicamento.add(new medicamento(15,"Tylenol com Codeína","Dor e tosse",474,4.30));
    }
    public static void listaPaciente(){
        listaPaciente.add(new Paciente(1,"João Gomes",46,"HIV"));
        listaPaciente.add(new Paciente(2,"Luiz Vasco",24,"Cancro da pele"));
        listaPaciente.add(new Paciente(3,"Rodrigo Pinto",25,"Pneumonia"));
        listaPaciente.add(new Paciente(4,"Inácio Vaz",21,"COVID"));
        listaPaciente.add(new Paciente(5,"Arnaldo Santos",68,"Gripe"));
        listaPaciente.add(new Paciente(6,"Maria Sousa",17,"Malária"));
        listaPaciente.add(new Paciente(7,"Joana Fernandes",40,"Bronquite"));
        listaPaciente.add(new Paciente(8,"Fernanda Pinto",35,"Cancro da Bexiga"));
        listaPaciente.add(new Paciente(9,"Mariana Luz",55,"Asma"));
        listaPaciente.add(new Paciente(10,"João Rodrigues",45,"Gastrite"));
    }
    public static void listaMedico(){
        listaMedico.add(new Medico(100,"António Fernandes",23,"Cardiologia"));
        listaMedico.add(new Medico(200,"Fátima Brito",20,"Traumatologia"));
        listaMedico.add(new Medico(300,"Ana Luis",43,"Ortopedia"));
        listaMedico.add(new Medico(400,"Patrícia Santos",30,"Anestesiologia"));
        listaMedico.add(new Medico(500,"Beatriz Alves",27,"Cirurgia Geral"));
        listaMedico.add(new Medico(600,"Baltazar Dias",50,"Clínica Médica"));
        listaMedico.add(new Medico(700,"Gonçalo Soares",37,"Pediatria"));
        listaMedico.add(new Medico(800,"Cristiano Sousa",32,"Medicina do Trabalho"));
        listaMedico.add(new Medico(900,"Emanuela Mêndes",22,"Radiologia"));
        listaMedico.add(new Medico(1000,"Rita Lima",34,"Oftalmologia"));



    }
}
