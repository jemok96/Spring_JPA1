package jpql.main;

import jpql.Member;

import javax.persistence.*;
import java.util.List;

public class MainPaging {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();

        tx.begin();
        try{
            for (int i = 0; i < 100; i++) {
                Member member = new Member();
                member.setAge(28);
                member.setUsername("Jemok"+i);
                em.persist(member);

            }
            em.flush();em.clear();


            List<Member> result = em.createQuery("select m from Member m order by m.id desc", Member.class)
                    .setFirstResult(1)
                    .setMaxResults(10)
                    .getResultList();
            System.out.println("result.size() = " + result.size());

            for (Member member1 : result) {
                System.out.println("member1 = " + member1);
            }

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
