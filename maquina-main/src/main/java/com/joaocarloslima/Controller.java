package com.joaocarloslima;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import java.text.DecimalFormat;

public class Controller implements Initializable{

    @FXML Label lblSaldo;
    @FXML TextField txtInserirDinheiro;
    @FXML FlowPane painelPrateleira;
    
    @FXML Label lblTotalArrecadado;

    private Maquina maquina = new Maquina();

    public void inserirDinheiro() {
        Double valor = Double.valueOf(txtInserirDinheiro.getText());
        maquina.inserirDinheiro(valor);
        atualizarView();
    }
    
    public void sacarDinheiro(){
        maquina.sacarDinheiro();
        atualizarView();
    }
    
    public void atualizarView() {
    DecimalFormat formatadorDecimal = new DecimalFormat("#,##0.00");
    
    lblSaldo.setText("R$ " + formatadorDecimal.format(maquina.getSaldo()));
    lblTotalArrecadado.setText("R$ " + formatadorDecimal.format(maquina.getTotalArrecadado()));
    montarPrateleira();
}
    
    private void montarPrateleira() {
        painelPrateleira.getChildren().clear();
        maquina.getRefrigerante().forEach(refrigerante -> {
            ImageView img = getImage(refrigerante);
            Label nome = new Label(refrigerante.getNome());
            Label preco = new Label("R$ " + refrigerante.getPreco());
            var painelProduto = new VBox(img, nome, preco);
            painelProduto.setOnMouseClicked(event -> {
                try {
                    maquina.comprarRefrigerante(refrigerante);
                    atualizarView();
                } catch (Exception e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Erro");
                    alert.setHeaderText("Erro ao comprar refrigerante");
                    alert.setContentText(e.getMessage());
                    alert.showAndWait();
                }
            });
            painelPrateleira.setHgap(20);
            painelPrateleira.setVgap(20);
            painelProduto.setAlignment(Pos.CENTER);
            painelPrateleira.getChildren().add(painelProduto);
        });
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        montarPrateleira();
    }

    private ImageView getImage(Refrigerante refrigerante){
        return new ImageView(new Image("file:src/main/resources/com/joaocarloslima/images/" + refrigerante.getSabor() + ".jpg"));
    }
    
}
