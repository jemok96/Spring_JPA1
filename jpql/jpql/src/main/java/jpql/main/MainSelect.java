package jpql.main;

import jpql.*;

import javax.persistence.*;
import java.util.List;

public class MainSelect {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();

        tx.begin();
        try{
            Team team = new Team();
            team.setName("Title");
            em.persist(team);

            Member member = new Member();
            member.setUsername("JM");member.setAge(28);
            member.setTeam(team);
            em.persist(member);

            //프로젝션: SELECT절에 조회할 대상을 지정하는 것
            //엔티티 프로젝션
            List<Team> result = em.createQuery("select m.team from Member m", Team.class).getResultList();
            System.out.println("result = " + result);

            Order order = new Order();
            order.setAddress(new Address("city","street","1000"));
            em.persist(order);

            //임베디드 타입 프로젝션
            List<Address> resultList = em.createQuery("select m.address from Order m").getResultList();
            System.out.println("resultList = " + resultList);

            //스칼라 타입 프로젝션, 생성자 필요
            List<MemberDTO> list = em.createQuery("select new jpql.MemberDTO(m.username,m.age) from Member m", MemberDTO.class)
                    .getResultList();
            MemberDTO memberDTO = list.get(0);
            System.out.println("memberDTO = " + memberDTO.getUsername());
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