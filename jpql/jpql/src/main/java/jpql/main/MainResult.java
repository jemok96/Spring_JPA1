package jpql.main;

import jpql.Member;

import javax.persistence.*;
import java.util.List;

public class MainResult {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();

        tx.begin();
        try{
            Member member = new Member();
            member.setAge(28);
            member.setUsername("Jemok");
            em.persist(member);

            Query query1 = em.createQuery("select m.username,m.age FROM Member m");
            TypedQuery<Member> query2 = em.createQuery("select m From Member m", Member.class);
            TypedQuery<String> query3 = em.createQuery("select m.username From Member m", String.class);

            System.out.println("query1 = " + query1);
            System.out.println("query2 = " + query2);
            System.out.println("query3 = " + query3);

            List resultList = query2.getResultList();
            for (Object o : resultList) {
                System.out.println("o = " + o);
            }

            Member singleResult = em.createQuery("select m from Member m where m.username = :username ", Member.class)
                    .setParameter("username", "Jemok")
                    .getSingleResult();
            System.out.println("singleResult = " + singleResult);

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