package application.controller;

import application.model.Produto;
import application.persistence.SCreateProduto;
import application.util.HibernateUtil;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.hibernate.SessionFactory;

public class SInserirProduto implements IInserir<Produto> {

    private TextField fieldId;
    private TextField fieldItem;
    private TextField fieldMarca;
    private TextField fieldModelo;
    private TextField fieldCor;
    private TextField fieldPreco;
    private TextArea textAreaList;

    public SInserirProduto(TextField fieldId, TextField fieldItem, TextField fieldMarca, TextField fieldModelo, TextField fieldCor, TextField fieldPreco, TextArea textAreaList) {
        this.fieldId = fieldId;
        this.fieldItem = fieldItem;
        this.fieldMarca = fieldMarca;
        this.fieldModelo = fieldModelo;
        this.fieldCor = fieldCor;
        this.fieldPreco = fieldPreco;
        this.textAreaList = textAreaList;
    }

    @Override
    public void inserir(Produto produto) {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        SCreateProduto dao = new SCreateProduto(sf);
        dao.create(produto);

        SLimparProduto cl = new SLimparProduto(fieldId, fieldItem, fieldMarca, fieldModelo, fieldCor, fieldPreco);
        SListarProdutos ls = new SListarProdutos(fieldId, fieldItem, fieldMarca, fieldModelo, fieldCor, fieldPreco, textAreaList);
        cl.limpar();
        ls.listar();
    }
}
