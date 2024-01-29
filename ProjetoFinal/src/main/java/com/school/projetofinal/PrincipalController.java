package com.school.projetofinal;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import static com.school.projetofinal.Settings.*;


public class PrincipalController implements Initializable {
    @FXML
    public Button btnAddMedico;
    @FXML
    public Button btnEditMedico;
    @FXML
    public Button btnDeleteMedico;
    @FXML
    public Button btnMenu;
    @FXML
    public Button btnPaciente;
    @FXML
    public Button btnMedico;
    @FXML
    public Button btnAbout;
    @FXML
    public Button btnSaida;
    @FXML
    public Button btnDelete;
    @FXML
    public Button btnEdit;
    @FXML
    public Button btnAdd;
    @FXML
    public Button btnDeletePaciente;
    @FXML
    public Button btnEditPaciente;
    @FXML
    public Button btnAddPaciente;
    @FXML
    public Hyperlink HyperLink;
    @FXML
    private TextField PesquisaMedico;
    @FXML
    private TextField PesquisaPaciente;
    @FXML
    private TextField PesquisaMedicamento;

    @FXML
    private AnchorPane ScreenAbout;
    @FXML
    private AnchorPane ScreenMedico;
    @FXML
    private TableColumn <Medico, Integer> TableCellIDMedico;
    @FXML
    private TableColumn <Medico, String> TableCellNomeMedico;
    @FXML
    private TableColumn <Medico, Integer> TableCellIdadeMedico;
    @FXML
    private TableColumn <Medico, String> TableCellEspecialidadeMedico;
    @FXML
    private TableView <Medico> TableViewMedico;
    @FXML
    private ComboBox<String> EspecialidadeMedico;
    @FXML
    private ComboBox<String> DoencaPaciente;
    @FXML
    private AnchorPane ScreenPaciente;

    @FXML
    private AnchorPane ScreenMedicamento;

    @FXML
    private TextField precoMedicamentoView;
    @FXML
    private TextField qtdMedicamentoView;
    @FXML
    private TextField nomeMedicamentoView;
    @FXML
    private TextField idMedicamentoView;
    @FXML
    private ComboBox<String> TipoMedicamento;

    @FXML
    private TableColumn <medicamento, Integer> TableCellID;

    @FXML
    private TableColumn <medicamento, String> TableCellNome;

    @FXML
    private TableColumn <medicamento, Double> TableCellPreco;
    @FXML
    private TableColumn <medicamento, Integer> TableCellQtd;
    @FXML
    private TableColumn <medicamento, String>  TableCellTipo;

    @FXML
    private TableView <medicamento> TableViewMedicamento;

    @FXML
    private TableView <Paciente> TableViewPaciente;

    @FXML
    private TableColumn <Paciente,Integer> TableCellIDPaciente;

    @FXML
    private TableColumn <Paciente, String> TableCellNomePaciente;

    @FXML
    private TableColumn <Paciente,Integer> TableCellIdadePaciente;

    @FXML
    private TableColumn <Paciente,String> TableCellDoencaPaciente;

    @FXML
    private TextField txtIDPaciente;

    @FXML
    private TextField txtNomePaciente;

    @FXML
    private TextField txtIdadePaciente;

    @FXML
    private TextField txtIDMedico;
    @FXML
    private TextField txtNomeMedico;
    @FXML
    private TextField txtIdadeMedico;

    //Ao pesquisar irá mostrar na tabela os respetivos medicamentos
    public void PesquisaMedicamentoKey(KeyEvent keyEvent) {
        FilteredList<medicamento> filter = new FilteredList<>(listaMedicamento, e -> true);

        PesquisaMedicamento.textProperty().addListener((Observable, oldValue, newValue) -> filter.setPredicate(predicatemedicamento ->{
            String ProcurarMedicamento = newValue.toLowerCase();
            if (String.valueOf(predicatemedicamento.obterID()).contains(ProcurarMedicamento)){
                return true;
            }else return predicatemedicamento.getNome().toLowerCase().contains(ProcurarMedicamento);
        }));
        SortedList<medicamento> sortList =  new SortedList<>(filter);
        sortList.comparatorProperty().bind(TableViewMedicamento.comparatorProperty());
        TableViewMedicamento.setItems(sortList);
    }
    public void TabelaMedicamento() {
        TableViewMedicamento.setItems(Settings.obterListaMedicamento());
        TableCellID.setCellValueFactory(new PropertyValueFactory<medicamento, Integer>("id"));
        TableCellNome.setCellValueFactory(new PropertyValueFactory<medicamento, String>("Nome"));
        TableCellTipo.setCellValueFactory(new PropertyValueFactory<medicamento, String>("Tipo"));
        TableCellQtd.setCellValueFactory(new PropertyValueFactory<medicamento, Integer>("qtd"));
        TableCellPreco.setCellValueFactory(new PropertyValueFactory<medicamento, Double>("preco"));
    }
    //Ao clicar retorna os valores do medicamento (String,int,double) às text fields que permite editar ou eliminar
    public void MedicamentoViewInfo() {
        medicamento medData = (medicamento)TableViewMedicamento.getSelectionModel().getSelectedItem();
        idMedicamentoView.setText(String.valueOf(medData.obterID()));
        nomeMedicamentoView.setText(medData.getNome());
        qtdMedicamentoView.setText(String.valueOf(medData.getQuantidade()));
        precoMedicamentoView.setText(String.valueOf(medData.getPreco()));
        TipoMedicamento.setValue(medData.getTipo());
    }

    //Este método serve para adicionar um novo medicamento à lista
    public void AddOnAction() {
        // Isso é para ver se algum TextFild está vazio e se estiver avisa com um alerta
        if ( nomeMedicamentoView.getText().isEmpty()
                || TipoMedicamento.getSelectionModel().getSelectedItem() == null
                || qtdMedicamentoView.getText().isEmpty()
                || precoMedicamentoView.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText(null);
            alert.setContentText("Por favor, preencha todos os campos!");
            alert.showAndWait();
        } else {
            // pega o Id da textFild do id
            int novoId = Integer.parseInt(idMedicamentoView.getText());
            // Verificar se o ID já existe na lista
            if (listaMedicamento.stream().anyMatch(m -> m.obterID() == novoId)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Esse ID já foi inserido");
                alert.showAndWait();
            } else {
                //Se não tiver ele pega o que está escrito e selecionado de cada caixa e mete num variavel
                String novoNome = nomeMedicamentoView.getText();
                String novoTipo = String.valueOf(TipoMedicamento.getSelectionModel().getSelectedItem());
                int novoQtd = Integer.parseInt(qtdMedicamentoView.getText());
                double novoPreco = Double.parseDouble(precoMedicamentoView.getText());
                // Pergunta se quer mesmo adicionar através de um alerta
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Adicionar");
                //Mostra o que vai ser adicionado no alerta
                alert.setHeaderText("Deseja mesmo Adicionar?"+"\n"+"ID: " + novoId + "\n" + "Nome: " + novoNome + "\n" + "Tipo: " + novoTipo + "\n" + "Quantidade: " + novoQtd + "\n" + "Preço: " + novoPreco);
                // Adiciona botões personalizados em português
                ButtonType botaoSim = new ButtonType("Sim");
                ButtonType botaoNao = new ButtonType("Não");
                alert.getButtonTypes().setAll(botaoSim, botaoNao);
                Optional<ButtonType> choose = alert.showAndWait();
                if (choose.get() == botaoSim) {
                    //Se sim for a opção será adicionado à lista e avisa que foi inserido
                    listaMedicamento.add(new medicamento(novoId, novoNome, novoTipo, novoQtd, novoPreco));
                    Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                    alert1.setTitle("Information");
                    alert1.setHeaderText(null);
                    alert1.setContentText("O medicamento foi inserido");
                    alert1.showAndWait();
                }
            }
        }

    }
    //Este método serve para editar um medicamento da lista
    public void EditOnAction() {
        if ( nomeMedicamentoView.getText().isEmpty()
                || TipoMedicamento.getSelectionModel().getSelectedItem() == null
                || qtdMedicamentoView.getText().isEmpty()
                || precoMedicamentoView.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Por favor, preencha todos os campos");
            alert.showAndWait();
        } else {
            medicamento medicamentoEdit = null;
            int novoId = Integer.parseInt(idMedicamentoView.getText());
            for (medicamento m : Settings.obterListaMedicamento()) {
                if (m.obterID() == novoId) {
                    medicamentoEdit = m;
                    break;
                }
            }
            if (medicamentoEdit != null) {
                medicamentoEdit.setNome(nomeMedicamentoView.getText());
                medicamentoEdit.setTipo(TipoMedicamento.getSelectionModel().getSelectedItem());
                medicamentoEdit.setQuantidade(Integer.parseInt(qtdMedicamentoView.getText()));
                medicamentoEdit.setPreco(Double.parseDouble(precoMedicamentoView.getText()));
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Editar");
                alert.setHeaderText("Deseja mesmo Editar?");
                ButtonType botaoSim = new ButtonType("Sim");
                ButtonType botaoNao = new ButtonType("Não");
                alert.getButtonTypes().setAll(botaoSim, botaoNao);
                Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                alert1.setTitle("Informação");
                alert1.setHeaderText(null);
                Optional<ButtonType> choose = alert.showAndWait();
                if (choose.get() == botaoSim) {
                    setMedicamentoEdit(medicamentoEdit);
                    TableViewMedicamento.refresh();
                    alert1.setContentText("Edição bem-sucedida");
                    alert1.showAndWait();
                }
            }
            else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Esse ID não foi encontrado");
                alert.showAndWait();}
        }
    }
    public void DeleteOnAction(){
        if ( nomeMedicamentoView.getText().isEmpty()
                || TipoMedicamento.getSelectionModel().getSelectedItem() == null
                || qtdMedicamentoView.getText().isEmpty()
                || precoMedicamentoView.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText(null);
            alert.setContentText("Por favor selecione algum medicamento da tabela");
            alert.showAndWait();}
        else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Eliminar");
            alert.setHeaderText("Deseja mesmo Eliminar?"+"\n"+"ID: " +idMedicamentoView.getText() + "\n" + "Nome: " + nomeMedicamentoView.getText() + "\n" + "Tipo: " + TipoMedicamento.getSelectionModel().getSelectedItem() + "\n" + "\n" + "Quantidade: " + qtdMedicamentoView.getText() + "\n" + "Preço: " + precoMedicamentoView.getText());
            ButtonType botaoSim = new ButtonType("Sim");
            ButtonType botaoNao = new ButtonType("Não");
            alert.getButtonTypes().setAll(botaoSim, botaoNao);
            Optional<ButtonType> choose = alert.showAndWait();
            if (choose.get() == botaoSim) {
                int novoId = Integer.parseInt(idMedicamentoView.getText());
                for (medicamento m : listaMedicamento) {
                    if (m.obterID() == novoId) {
                        Settings.obterListaMedicamento().remove(m);
                        Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                        alert1.setTitle("Informação");
                        alert1.setHeaderText(null);
                        alert1.setContentText("O seu medicamento foi Eliminado");
                        alert1.showAndWait();
                        break;
                    }
                }
            }
        }
    }
    //Ao pesquisar irá mostrar na tabela os respetivos pacientes
    public void PesquisaPacienteKey(KeyEvent keyEvent) {
        FilteredList<Paciente> filter = new FilteredList<>(listaPaciente, e -> true);

        PesquisaPaciente.textProperty().addListener((Observable, oldValue, newValue) ->{

            filter.setPredicate(predicatePaciente ->{
                if(newValue == null && newValue.isEmpty()){
                    return true;
                }
                String ProcurarPaciente = newValue.toLowerCase();
                if (String.valueOf(predicatePaciente.obterID()).contains(ProcurarPaciente)){
                    return true;
                }else if (predicatePaciente.obterNome().toLowerCase().contains(ProcurarPaciente)) {
                    return true;
                }else if(String.valueOf(predicatePaciente.obterIdade()).contains(ProcurarPaciente)){
                    return true;
                }else if(String.valueOf(predicatePaciente.obterDoenca()).contains(ProcurarPaciente)) {
                    return true;
                }
                return false;
            });
        });
        SortedList<Paciente> sortList =  new SortedList<>(filter);
        sortList.comparatorProperty().bind(TableViewPaciente.comparatorProperty());
        TableViewPaciente.setItems(sortList);
    }
    public void TabelaPaciente(){
        TableViewPaciente.setItems(Settings.obterListaPaciente());
        TableCellIDPaciente.setCellValueFactory(new PropertyValueFactory<Paciente, Integer>("ID"));
        TableCellNomePaciente.setCellValueFactory(new PropertyValueFactory<Paciente, String>("Nome"));
        TableCellIdadePaciente.setCellValueFactory(new PropertyValueFactory<Paciente, Integer>("Idade"));
        TableCellDoencaPaciente.setCellValueFactory(new PropertyValueFactory<Paciente, String>("Doença"));
    }
    //Ao clicar retorna os valores do paciente (String,int,double) às text fields que permite editar ou eliminar
    public void PacienteViewInfo(){
        Paciente PacienteData = (Paciente) TableViewPaciente.getSelectionModel().getSelectedItem();
        txtIDPaciente.setText(String.valueOf(PacienteData.obterID()));
        txtNomePaciente.setText(PacienteData.obterNome());
        txtIdadePaciente.setText(String.valueOf(PacienteData.obterIdade()));
        DoencaPaciente.setValue(String.valueOf(PacienteData.obterDoenca()));
    }
    //Este método serve para adicionar um novo paciente à lista
    public void AddPacienteOnAction() {
        if ( txtNomePaciente.getText().isEmpty()
                || txtIdadePaciente.getText().isEmpty()
                || DoencaPaciente.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Por favor, preencha todos os campos");
            alert.showAndWait();
        } else {
            // pega o Id da textFild do id
            int novoId = Integer.parseInt(txtIDPaciente.getText());
            // Verificar se o ID já existe na lista
            if (listaPaciente.stream().anyMatch(p -> p.obterID() == novoId)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Esse ID já foi inserido");
                alert.showAndWait();
            } else {
                //Se não tiver ele pega o que está escrito e selecionado de cada caixa e mete num variavel
                String novoNome = txtNomePaciente.getText();
                int novaIdade = txtIdadePaciente.getPrefColumnCount();
                String novaDoenca = DoencaPaciente.getSelectionModel().getSelectedItem();
                // Pergunta se quer mesmo adicionar através de um alerta
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Adicionar");
                //Mostra o que vai ser adicionado no alerta
                alert.setHeaderText("Deseja mesmo Adicionar?"+"\n"+"ID: " + novoId + "\n" + "Nome: " + novoNome + "\n" + "Idade: " + novaIdade + "\n" + "Doença: " + novaDoenca );
                // Adiciona botões personalizados em português
                ButtonType botaoSim = new ButtonType("Sim");
                ButtonType botaoNao = new ButtonType("Não");
                alert.getButtonTypes().setAll(botaoSim, botaoNao);
                Optional<ButtonType> choose = alert.showAndWait();
                if (choose.get() == botaoSim) {
                    //Se sim for a opção será adicionado à lista e avisa que foi inserido
                    listaPaciente.add(new Paciente(novoId, novoNome, novaIdade, novaDoenca));
                    Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                    alert1.setTitle("Informação");
                    alert1.setHeaderText(null);
                    alert1.setContentText("O Paciente foi inserido");
                    alert1.showAndWait();
                }
            }
        }
    }
    //Este método serve para editar um paciente da lista
    public void EditPacienteOnAction() {
        if ( txtNomePaciente.getText().isEmpty()
                || txtIdadePaciente.getText().isEmpty()
                || DoencaPaciente.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Por favor, preencha todos os campos");
            alert.showAndWait();
        } else {
            Paciente PacienteEdit = null;
            int novoId = Integer.parseInt(txtIDPaciente.getText());
            for (Paciente p : Settings.obterListaPaciente()) {
                if (p.obterID() == novoId) {
                    PacienteEdit = p;
                    break;
                }
            }
            if (PacienteEdit != null) {
                PacienteEdit.setNome(txtNomePaciente.getText());
                PacienteEdit.setIdade(Integer.parseInt(txtIdadePaciente.getText()));
                PacienteEdit.setDoenca(DoencaPaciente.getSelectionModel().getSelectedItem());
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Editar");
                alert.setHeaderText("Deseja mesmo Editar?");
                ButtonType botaoSim = new ButtonType("Sim");
                ButtonType botaoNao = new ButtonType("Não");
                alert.getButtonTypes().setAll(botaoSim, botaoNao);
                Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                alert1.setTitle("Informação");
                alert1.setHeaderText(null);
                Optional<ButtonType> choose = alert.showAndWait();
                if (choose.get() == botaoSim) {
                    setPacienteEdit(PacienteEdit);
                    TableViewPaciente.refresh();
                    alert1.setContentText("Edição bem-sucedida");
                    alert1.showAndWait();
                }
            }
            else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Esse ID não foi encontrado");
                alert.showAndWait();}
        }
    }
    //Este método serve para remover um paciente da lista
    public void DeletePacienteOnAction() {
        if ( txtNomePaciente.getText().isEmpty()
                || txtIdadePaciente.getText().isEmpty()
                || DoencaPaciente.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Por favor, preencha todos os campos");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Eliminar");
            alert.setHeaderText("Deseja mesmo Eliminar?"+"\n"+"ID: " +txtIDPaciente.getText() + "\n" + "Nome: " + txtNomePaciente.getText() + "\n" + "Idade: " + txtIDPaciente.getText() + "\n" + "Doença: " + DoencaPaciente.getSelectionModel().getSelectedItem());
            ButtonType botaoSim = new ButtonType("Sim");
            ButtonType botaoNao = new ButtonType("Não");
            alert.getButtonTypes().setAll(botaoSim, botaoNao);
            Optional<ButtonType> choose = alert.showAndWait();
            if (choose.get() == botaoSim) {
                int novoId = Integer.parseInt(txtIDPaciente.getText());
                for (Paciente p : listaPaciente) {
                    if (p.obterID() == novoId) {
                        Settings.obterListaPaciente().remove(p);
                        Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                        alert1.setTitle("Informação");
                        alert1.setHeaderText(null);
                        alert1.setContentText("O Paciente foi Eliminado");
                        alert1.showAndWait();
                        break;
                    }
                }
            }
        }
    }
    //Ao pesquisar irá mostrar na tabela os respetivos médicos
    public void PesquisaMedicoKey(KeyEvent keyEvent) {
        FilteredList<Medico> filter = new FilteredList<>(listaMedico, e -> true);

        PesquisaMedico.textProperty().addListener((Observable, oldValue, newValue) ->{

            filter.setPredicate(predicateMedico ->{
                if(newValue == null && newValue.isEmpty()){
                    return true;
                }
                String ProcurarMed = newValue.toLowerCase();
                if (String.valueOf(predicateMedico.obterID()).contains(ProcurarMed)){
                    return true;
                }else if (predicateMedico.obterNome().toLowerCase().contains(ProcurarMed)) {
                    return true;
                }else if(String.valueOf(predicateMedico.obterIdade()).contains(ProcurarMed)){
                    return true;
                }else if(predicateMedico.obterEspecialidade().contains(ProcurarMed)) {
                    return true;
                }
                return false;
            });
        });
        SortedList<Medico> sortList =  new SortedList<>(filter);
        sortList.comparatorProperty().bind(TableViewMedico.comparatorProperty());
        TableViewMedico.setItems(sortList);

    }
    public void TabelaMedico(){
        TableViewMedico.setItems(Settings.obterListaMedico());
        TableCellIDMedico.setCellValueFactory(new PropertyValueFactory<Medico, Integer>("ID"));
        TableCellNomeMedico.setCellValueFactory(new PropertyValueFactory<Medico, String>("Nome"));
        TableCellIdadeMedico.setCellValueFactory(new PropertyValueFactory<Medico, Integer>("Idade"));
        TableCellEspecialidadeMedico.setCellValueFactory(new PropertyValueFactory<Medico, String>("Especialidade"));
    }
    //Ao clicar retorna os valores do médico (String,int,double) às text fields que permite editar ou eliminar
    public void MedicoViewInfo(){
        Medico MedicoData = (Medico) TableViewMedico.getSelectionModel().getSelectedItem();
        txtIDMedico.setText(String.valueOf(MedicoData.obterID()));
        txtNomeMedico.setText(MedicoData.obterNome());
        txtIdadeMedico.setText(String.valueOf(MedicoData.obterIdade()));
        EspecialidadeMedico.setValue(MedicoData.obterEspecialidade());
    }
    //Este método serve para adicionar um novo médico à lista
    public void AddMedicoOnAction(){
        if ( txtNomeMedico.getText().isEmpty()
                || txtIdadeMedico.getText().isEmpty()
                || EspecialidadeMedico.getSelectionModel().getSelectedItem() == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERRO");
            alert.setHeaderText(null);
            alert.setContentText("Por favor, preencha os campos");
            alert.showAndWait();
        } else {
            // pega o Id da textFild do id
            int novoId = Integer.parseInt(txtIDMedico.getText());
            // Verificar se o ID já existe na lista
            if (listaMedico.stream().anyMatch(f -> f.obterID() == novoId)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Esse ID já foi inserido");
                alert.showAndWait();
            } else {
                //Se não tiver ele pega o que está escrito e selecionado de cada caixa e mete num variavel
                String novoNome = txtNomeMedico.getText();
                int novaIdade = txtIdadeMedico.getPrefColumnCount();
                String novaEspecialidade = String.valueOf(EspecialidadeMedico.getSelectionModel().getSelectedItem());
                // Pergunta se quer mesmo adicionar através de um alerta
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Adicionar");
                //Mostra o que vai ser adicionado no alerta
                alert.setHeaderText("Deseja mesmo Adicionar?"+"\n"+"ID: " + novoId + "\n" + "Nome: " + novoNome + "\n" + "Idade: " + novaIdade + "\n" + "Especialidade: " + novaEspecialidade);
                // Adiciona botões personalizados em português
                ButtonType botaoSim = new ButtonType("Sim");
                ButtonType botaoNao = new ButtonType("Não");
                alert.getButtonTypes().setAll(botaoSim, botaoNao);
                Optional<ButtonType> choose = alert.showAndWait();
                if (choose.get() == botaoSim) {
                    //Se sim for a opção será adicionado à lista e avisa que foi inserido
                    listaMedico.add(new Medico(novoId, novoNome, novaIdade, novaEspecialidade));
                    Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                    alert1.setTitle("Informação");
                    alert1.setHeaderText(null);
                    alert1.setContentText("O Médico foi inserido");
                    alert1.showAndWait();
                }
            }
        }
    }
    //Este método serve para editar um médico da lista
    public void EditMedicoOnAction(){
        if ( txtNomeMedico.getText().isEmpty()
                || txtIdadeMedico.getText().isEmpty()
                || EspecialidadeMedico.getSelectionModel().getSelectedItem() == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText(null);
            alert.setContentText("Por favor, preencha todos os campos");
            alert.showAndWait();
        } else {
            Medico MedicoEdit = null;
            int novoId = Integer.parseInt(txtIDMedico.getText());
            for (Medico m : Settings.obterListaMedico()) {
                if (m.obterID() == novoId) {
                    MedicoEdit = m;
                    break;
                }
            }
            if (MedicoEdit != null) {
                MedicoEdit.setNome(txtNomeMedico.getText());
                MedicoEdit.setIdade(txtIdadeMedico.getPrefColumnCount());
                MedicoEdit.setEspecialidade(String.valueOf(EspecialidadeMedico.getSelectionModel().getSelectedItem()));
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Editar");
                alert.setHeaderText("Deseja mesmo Editar?");
                ButtonType botaoSim = new ButtonType("Sim");
                ButtonType botaoNao = new ButtonType("Não");
                alert.getButtonTypes().setAll(botaoSim, botaoNao);
                Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                alert1.setTitle("Informação");
                alert1.setHeaderText(null);
                Optional<ButtonType> choose = alert.showAndWait();
                if (choose.get() == botaoSim) {
                    setMedicoEdit(MedicoEdit);
                    TableViewMedico.refresh();
                    alert1.setContentText("Edição bem-sucedida");
                    alert1.showAndWait();
                }
            }
            else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erro");
                alert.setHeaderText(null);
                alert.setContentText("Esse ID não foi encontrado");
                alert.showAndWait();}
        }
    }
    //Este método serve para remover um médico da lista
    public void DeleteMedicoOnAction(){
        if ( txtNomeMedico.getText().isEmpty()
                || txtIdadeMedico.getText().isEmpty()
                || EspecialidadeMedico.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText(null);
            alert.setContentText("Por favor selecione algum Médico da tabela");
            alert.showAndWait();}
        else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Eliminar");
            alert.setHeaderText("Deseja mesmo Eliminar?"+"\n"+"ID: " +txtIDMedico.getText() + "\n" + "Nome: " + txtNomeMedico.getText() + "\n" + "Idade: " + txtIdadePaciente.getText() + "\n" + "Especialidade: " + EspecialidadeMedico.getSelectionModel().getSelectedItem());
            ButtonType botaoSim = new ButtonType("Sim");
            ButtonType botaoNao = new ButtonType("Não");
            alert.getButtonTypes().setAll(botaoSim, botaoNao);
            Optional<ButtonType> choose = alert.showAndWait();
            if (choose.get() == botaoSim) {
                int novoId = Integer.parseInt(txtIDMedico.getText());
                for (Medico m : listaMedico) {
                    if (m.obterID() == novoId) {
                        Settings.obterListaMedico().remove(m);
                        Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                        alert1.setTitle("Informação");
                        alert1.setHeaderText(null);
                        alert1.setContentText("O Médico foi Eliminado!");
                        alert1.showAndWait();
                        break;
                    }
                }
            }
        }
    }
    //Método para o butão de saída
    public void saida() throws Exception {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Sair");
        alert.setHeaderText("Deseja mesmo Sair?");
        // Adiciona botões personalizados em português
        ButtonType botaoSim = new ButtonType("Sim");
        ButtonType botaoNao = new ButtonType("Não");
        alert.getButtonTypes().setAll(botaoSim, botaoNao);
        Optional<ButtonType> choose = alert.showAndWait();
        if (choose.get() == botaoSim) {
            Platform.exit();
        }
    }
    public void btnPacienteOnAction() {
        ScreenPaciente.setVisible(true);
        ScreenMedicamento.setVisible(false);
        ScreenMedico.setVisible(false);
        ScreenAbout.setVisible(false);
    }
    public void btnMenuOnAction() {
        ScreenPaciente.setVisible(false);
        ScreenMedicamento.setVisible(true);
        ScreenMedico.setVisible(false);
        ScreenAbout.setVisible(false);
    }
    public void btnMedicoOnAction() {
        ScreenPaciente.setVisible(false);
        ScreenMedicamento.setVisible(false);
        ScreenMedico.setVisible(true);
        ScreenAbout.setVisible(false);
    }
    public void btnAboutOnAction(){
        ScreenPaciente.setVisible(false);
        ScreenMedicamento.setVisible(false);
        ScreenMedico.setVisible(false);
        ScreenAbout.setVisible(true);
    }
    //Hyperlink que leva ao github no google
    @FXML
    private void HyperlinkOnAction(ActionEvent actionEvent) throws IOException {
        String url = "https://github.com/itskyromx";
        java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));
    }
    //Lista dos tipos de medicamentos
    public void TipoMedicamentoLista() {
        ArrayList<String> ListaTipo = new ArrayList<>();
        ListaTipo.add("Febre");
        ListaTipo.add("Inflamação");
        ListaTipo.add("Infecções bacterianas");
        ListaTipo.add("Colestrol");
        ListaTipo.add("Diabetes tipo 2");
        ListaTipo.add("Pressão arterial");
        ListaTipo.add("Asma");
        ListaTipo.add("Antidepressivo");
        ListaTipo.add("Coágulos sanguíneos");
        ListaTipo.add("Distúrbios neuromusculares");
        ListaTipo.add("Edema");
        ListaTipo.add("Dor e tosse");
        ListaTipo.add("Úlceras gástricas");
        ListaTipo.add("Úlceras");
        ObservableList<String> listaTipo = FXCollections.observableArrayList(ListaTipo);
        TipoMedicamento.setItems(listaTipo);
    }
    //Lista das doenças dos pacientes
    public void DoencasLista() {
        ArrayList<String> ListaDoenca = new ArrayList<>();
        ListaDoenca.add("Pneumonia");
        ListaDoenca.add("HIV");
        ListaDoenca.add("Cancro da pele");
        ListaDoenca.add("COVID");
        ListaDoenca.add("Cancro da bexiga");
        ListaDoenca.add("Gastrite");
        ListaDoenca.add("Asma");
        ListaDoenca.add("Bronquite");
        ListaDoenca.add("Gripe");
        ListaDoenca.add("Malária");
        ObservableList<String> listaTipo = FXCollections.observableArrayList(ListaDoenca);
        DoencaPaciente.setItems(listaTipo);
    }
    //Lista das especialidades
    public void EspecialidadeLista(){
        ArrayList<String> listaEspecialidade = new ArrayList<>();
        listaEspecialidade.add("Cardiologia");
        listaEspecialidade.add("Traumatologia");
        listaEspecialidade.add("Anestesiologia");
        listaEspecialidade.add("Cirurgia Geral");
        listaEspecialidade.add("Clínica Médica");
        listaEspecialidade.add("Ortopedia");
        listaEspecialidade.add("Pediatria");
        listaEspecialidade.add("Radiologia");
        listaEspecialidade.add("Medicina do Trabalho");
        listaEspecialidade.add("Oftalmologia");
        ObservableList<String> EspecialidadeLista = FXCollections.observableArrayList(listaEspecialidade);
        EspecialidadeMedico.setItems(EspecialidadeLista);
    }
    @Override
    public void initialize (URL url, ResourceBundle resourceBundle){
        TipoMedicamentoLista();
        DoencasLista();
        EspecialidadeLista();
        TabelaMedicamento();
        TabelaPaciente();
        TabelaMedico();
    }
}