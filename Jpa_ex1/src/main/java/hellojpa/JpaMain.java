package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try{
            //팀 저장
            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);
            //회원 저장
            Member member = new Member();
            member.setUsername("member1");
            member.setTeam(team); //연관관계 설정, 참조 저장
            em.persist(member);

            em.flush();
            em.clear();
            //조회
            Member findMember = em.find(Member.class, member.getId());
            Team findTeam = findMember.getTeam();

            System.out.println("findTeam = " + findTeam.getName());

            List<Member> members = findTeam.getMembers();
            for (Member m: members) {
                System.out.println("m = " + m.getUsername());
            }


            tx.commit();
        }catch (Exception e) {
            tx.rollback();
        }finally{
            em.close();
        }
        emf.close();


    }
}

/**  Team저장, Member저장 상태
 * Team team = new Team();
 * team.setName("TeamA");
 * em.persist(team);
 *
 * Member member = new Member();
 * member.setUsername("member1");
 * member.setTeamId(team.getId());
 * em.persist(member);
 *
 * 어떤 Member의 팀 정보를 가져오려면 이 만큼이나 계속 찾아야함
 * 테이블은 외래 키로 조인을 사용해서 연관된 테이블을 찾는다
 * 객체는 참조를 사용해서 연관된 객체를 찾음       둘 사이에는 큰 간격이 있음
 * 
 * Member findMember = em.find(Member.class, member.getId());
 * Long findTeamId = findMember.getTeamId();
 * Team findTeam = em.find(Team.class, findTeamId);
 */

/**
 * 양방향 관계일 경우
 * 외래키를 가지고 있는 쪽을 주인으로 정해라
 * 주인이 아닌곳은 mappedBy로 지정, 주인이 아닌쪽은 읽기만 가능
 */