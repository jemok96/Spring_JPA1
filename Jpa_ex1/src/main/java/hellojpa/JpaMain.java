package hellojpa;

import hellojpa.domain.Child;
import hellojpa.domain.Member;
import hellojpa.domain.Parent;
import hellojpa.domain.Team;
import hellojpa.domain.embeddable.Address;
import hellojpa.domain.item.Item;
import hellojpa.domain.item.Movie;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;


public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();

        tx.begin();
        try{
            Member member = new Member();
            member.setUsername("JM");
            member.setAddress(new Address("city","Street","zipcode"));

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

