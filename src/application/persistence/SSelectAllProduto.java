package application.persistence;

import application.model.Produto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.ArrayList;

public class SSelectAllProduto implements ISelectAll<Produto> {

    private SessionFactory sf;

    public SSelectAllProduto(SessionFactory sf) {
        this.sf = sf;
    }

    @Override
    public List<Produto> selectAll() {
        List<Produto> produtos = new ArrayList<>();
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT id, item, marca, modelo, cor, preco ");
        sql.append("FROM produtos ");
        sql.append("ORDER BY item");
        EntityManager entityManager = sf.createEntityManager();
        Query query = entityManager.createNativeQuery(sql.toString());
        List<Object[]> lista = query.getResultList();
        for (Object[] obj : lista) {
            Produto produto = new Produto();
            produto.setId(obj[0].toString());
            produto.setItem(obj[1].toString());
            produto.setMarca(obj[2].toString());
            produto.setModelo(obj[3].toString());
            produto.setCor(obj[4].toString());
            produto.setPreco(obj[5].toString());
            produtos.add(produto);
        }
        return produtos;
    }
}
