package application.controller;

import application.model.Produto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import javax.swing.*;
import java.sql.SQLException;

public class PrincipalController {

    @FXML private Button btnAdicionar;
    @FXML private Button btnExcluir;
    @FXML private Button btnListar;
    @FXML private Button btnAtualizar;
    @FXML private Button btnProcurar;
    @FXML private TextField fieldCor;
    @FXML private TextField fieldId;
    @FXML private TextField fieldItem;
    @FXML private TextField fieldMarca;
    @FXML private TextField fieldModelo;
    @FXML private TextField fieldPreco;
    @FXML private Label labelCor;
    @FXML private Label labelId;
    @FXML private Label labelItem;
    @FXML private Label labelMarca;
    @FXML private Label labelModelo;
    @FXML private Label labelPreco;
    @FXML private TextArea textAreaList;
    @FXML private TextArea itemDescricao;


    @FXML void acaoCliente(ActionEvent event) throws SQLException {
        String cmd = event.getSource().toString();
        System.out.println(cmd);

        ProdutoController produtoController =
                new ProdutoController(fieldId, fieldItem, fieldMarca,
                        fieldModelo, fieldCor, fieldPreco, textAreaList);

        if ((cmd.contains("Adicionar") || cmd.contains("Atualizar"))
                && (fieldId.getText().isEmpty()
                || fieldItem.getText().isEmpty()
                || fieldMarca.getText().isEmpty()
                || fieldModelo.getText().isEmpty()
                || fieldCor.getText().isEmpty()
                || fieldPreco.getText().isEmpty()
                )) {
            JOptionPane.showMessageDialog(null,
                    "É necessário o preenchimento completo do produto *",
                    "Erro ao adicionar/atualizar", JOptionPane.WARNING_MESSAGE);
        }

        else if (cmd.contains("Excluir")
                && (fieldId.getText().isEmpty()
                || fieldItem.getText().isEmpty()
                || fieldMarca.getText().isEmpty()
                || fieldModelo.getText().isEmpty()
                || fieldCor.getText().isEmpty()
                || fieldPreco.getText().isEmpty()
                )) {
            JOptionPane.showMessageDialog(null,
                    "É necessário os dados completos do produto para exclusão *",
                    "Erro ao excluir", JOptionPane.WARNING_MESSAGE);
        }

        else if (cmd.contains("Procurar") && fieldItem.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "É necessário o preenchimento do campo 'item' para a busca *",
                    "Erro ao procurar", JOptionPane.WARNING_MESSAGE);
        }

        else if (cmd.contains("Listar")) {
            produtoController.listarProdutos();

        } else {
            Produto p = new Produto();
            p.setId(Integer.parseInt(fieldId.getText()));
            p.setItem(fieldItem.getText().toUpperCase());
            p.setModelo(fieldModelo.getText().toUpperCase());
            p.setMarca(fieldMarca.getText().toUpperCase());
            p.setCor(fieldCor.getText().toUpperCase());
            p.setPreco(Float.parseFloat(fieldPreco.getText().toUpperCase()));
            if (cmd.contains("Adicionar")) {
                produtoController.inserirProduto(p);
            } else if (cmd.contains("Atualizar")) {
                produtoController.atualizarProduto(p);
            } else if (cmd.contains("Excluir")) {
                produtoController.excluirProduto(p);
            } else if (cmd.contains("Procurar")) {
                produtoController.procurarProduto(p);
            }
        }
    }
}