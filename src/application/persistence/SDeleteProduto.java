package application.persistence;

import application.model.Produto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.hibernate.SessionFactory;

public class SDeleteProduto implements IDelete<Produto> {

    private SessionFactory sf;

    public SDeleteProduto(SessionFactory sf) {
        this.sf = sf;
    }

    @Override
    public void delete(Produto p) {
        EntityManager entityManager = sf.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.remove(p);
        transaction.commit();
    }
}
