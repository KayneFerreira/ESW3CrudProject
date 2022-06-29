package application.persistence;

import application.model.Produto;
import jakarta.persistence.EntityManager;
import org.hibernate.SessionFactory;

public class SSelectOne implements ISelectOne {

    private SessionFactory sf;

    public SSelectOne(SessionFactory sf) {
        this.sf = sf;
    }

    @Override
    public Produto selectOne(Produto p) {
        EntityManager entityManager = sf.createEntityManager();
        p = entityManager.find(Produto.class, p.getItem());
        return p;
    }
}
