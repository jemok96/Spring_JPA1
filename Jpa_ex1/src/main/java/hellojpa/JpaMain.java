package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.transaction.TransactionManager;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();

        tx.begin();
        try{

            Child child = new Child();
            Child child1 = new Child();

            Parent parent = new Parent();
            parent.addChild(child);
            parent.addChild(child1);

            em.persist(parent);


            tx.commit();

        }
        catch(Exception e) {
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();


    }
}

