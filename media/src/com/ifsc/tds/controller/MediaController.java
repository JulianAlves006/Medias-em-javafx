package com.ifsc.tds.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;

public class MediaController {

    @FXML
    private Label lblNota1;

    @FXML
    private Label lblNota2;

    @FXML
    private Label lblNota3;

    @FXML
    private Label lblNota4;

    @FXML
    private Label lblMedia;

    @FXML
    private Label lblResultado;

    @FXML
    private TextField txtNota1;

    @FXML
    private TextField txtNota2;

    @FXML
    private TextField txtNota3;

    @FXML
    private TextField txtNota4;

    @FXML
    private Button btnCalcular;

    @FXML
    private Button btnLimpar;

    @FXML
    private Label lblRecebeMedia;

    @FXML
    private Label lblRecebeResultado;

    @FXML
    void OnClickBtnCalcular(ActionEvent event) {
    	try {
			double numero1 = Double.parseDouble(this.txtNota1.getText());
			double numero2 = Double.parseDouble(this.txtNota2.getText());
			double numero3 = Double.parseDouble(this.txtNota3.getText());
			double numero4 = Double.parseDouble(this.txtNota4.getText());
			
			double resultado = (numero1 + numero2 + numero3 + numero4) / 4;
			
			this.lblRecebeMedia.setText(String.format("%.2f", resultado));
			if (resultado >= 7) {
				this.lblRecebeResultado.setText("Aprovado!!");
				this.lblRecebeResultado.setTextFill(Color.web("#0000ff"));
			}
			else if(resultado < 7 && resultado >= 5) {
				this.lblRecebeResultado.setText("Exame");
				this.lblRecebeResultado.setTextFill(Color.web("#FF8C00"));
			}
			else {
				this.lblRecebeResultado.setText("Reprovado!");
				this.lblRecebeResultado.setTextFill(Color.web("#ff0000"));
			}
    		
		} catch (NumberFormatException e) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);

            String textoErro = sw.toString();
            //
            Alert alerta = new Alert(AlertType.ERROR);
            alerta.setTitle("Erro");
            alerta.setHeaderText("Aconteceu um erro de conversão numerica!");

            Label label = new Label("Segue a pilha de exceção:");

            TextArea textArea = new TextArea(textoErro);
            textArea.setEditable(false);
            textArea.setWrapText(true);

            textArea.setMaxWidth(Double.MAX_VALUE);
            textArea.setMaxHeight(Double.MAX_VALUE);

            GridPane.setVgrow(textArea, Priority.ALWAYS);
            GridPane.setHgrow(textArea, Priority.ALWAYS);

            GridPane expConteudo = new GridPane();
            expConteudo.setMaxWidth(Double.MAX_VALUE);
            expConteudo.add(label, 0, 0);
            expConteudo.add(textArea, 0, 1);
            alerta.getDialogPane().setExpandableContent(expConteudo);
            alerta.showAndWait();
        }    	
    }

    @FXML
    void OnClickBtnLimpar(ActionEvent event) {
    	this.txtNota1.clear();
    	this.txtNota2.clear();
    	this.txtNota3.clear();
    	this.txtNota4.clear();
    	this.lblRecebeMedia.setText("0");
    	this.txtNota1.requestFocus();
    	this.lblRecebeResultado.setText("-");
    	this.lblRecebeResultado.setTextFill(Color.web("000"));
    }
    
    public boolean onCloseQuery() {
    	Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
    	alerta.setTitle("'Sair'");
    	alerta.setHeaderText("Deseja sair do programa?");
    	alerta.setContentText("Esta seguro que você deseja sair?");
    	ButtonType botaoNao = ButtonType.NO;
    	ButtonType botaoSim = ButtonType.YES;
    	
    	alerta.getButtonTypes().setAll(botaoSim, botaoNao);
    	Optional<ButtonType> opcaoClicada = alerta.showAndWait();
    	
    	return opcaoClicada.get() == botaoSim ? true : false;
    }

}
