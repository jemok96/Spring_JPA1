package hellojpa;

import hellojpa.domain.Member;
import hellojpa.domain.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;


public class LazyMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();

        tx.begin();
        try{
            Team team = new Team();
            team.setName("A");
            em.persist(team);

            Member member = new Member();
            member.setUsername("member1");
            member.setTeam(team);
            em.persist(member);



            em.flush();
            em.clear();
            /**
             * Member class에서 Team에 FetchType.LAZY 설정
             * Member를 조회할 때 불필요하게 Team까지 한번에 조회하지 않음
             * Team클래스 자체도 프록시객체임
             */
            Member findMembmer = em.find(Member.class, member.getId());
            System.out.println("findMembmer = " + findMembmer.getTeam().getClass());
            System.out.println("============================");
            /**
             * 실제 Team을 사용하는 시점에 프록시 객체에 있는 값들이 초기화 됨
             * 실제 쿼리가 나감
             * 근데 Member와 Team 둘이 같이 필요할 때는 EARER를 사용하는게 더 좋을 수도 있지만
             * 여러가지 문제가 많음(JPQL N+1문제)
             * 실무에서 모든 연관관계에 지연 로딩을 사용해라
             * JPQL fetch 조인이나, 엔티티 그래프 기능을 사용해라
             */
            String name = findMembmer.getTeam().getName();
            System.out.println("name = " + name);
            tx.commit();

        }
        catch(Exception e) {
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();


    }

    private static void printMemberAndTeam(Member member) {
        String username = member.getUsername();
        System.out.println("username = " + username);

        Team team = member.getTeam();
        System.out.println("team = " + team);
    }
}

