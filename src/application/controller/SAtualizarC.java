package application.controller;

import application.model.Produto;
import application.persistence.SUpdate;
import application.util.HibernateUtil;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.hibernate.SessionFactory;

public class SAtualizarC implements IAtualizarC {

    private TextField fieldId;
    private TextField fieldItem;
    private TextField fieldMarca;
    private TextField fieldModelo;
    private TextField fieldCor;
    private TextField fieldPreco;
    private TextArea textAreaList;

    public SAtualizarC(TextField fieldId, TextField fieldItem, TextField fieldMarca, TextField fieldModelo, TextField fieldCor, TextField fieldPreco, TextArea textAreaList) {
        this.fieldId = fieldId;
        this.fieldItem = fieldItem;
        this.fieldMarca = fieldMarca;
        this.fieldModelo = fieldModelo;
        this.fieldCor = fieldCor;
        this.fieldPreco = fieldPreco;
        this.textAreaList = textAreaList;
    }

    @Override
    public void atualizar(Produto produto) {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        SUpdate dao = new SUpdate(sf);
        dao.update(produto);

        SLimparC cl = new SLimparC(fieldId, fieldItem, fieldMarca, fieldModelo, fieldCor, fieldPreco);
        SListarC ls = new SListarC(fieldId, fieldItem, fieldMarca, fieldModelo, fieldCor, fieldPreco, textAreaList);
        cl.limpar();
        ls.listar();
    }
}
