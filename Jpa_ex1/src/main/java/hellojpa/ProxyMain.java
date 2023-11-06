package hellojpa;

import hellojpa.domain.Member;
import hellojpa.domain.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;


public class ProxyMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();

        tx.begin();
        try{
            Member member1 = new Member();
            member1.setUsername("member1");
            em.persist(member1);

            Member member2 = new Member();
            member2.setUsername("member2");
            em.persist(member2);

            em.flush();
            em.clear();

            /**
             * getReference는 프록시 객체를 가져온다(Id값만 있는 빈 껍데기임)
             * id는 이미 getId로 받았기 때문에 Id를 조회할 때는 실제 객체에 접근할 필요가 없지만
             * getUserName을 사용할 떄는 DB에 실제 값을 조회하게 되고 프록시 값이 실제값으로 초기화된다.
             * 값이 초기화 됐다고 프록시 객체가 실제 엔티티로 바뀌는 것은 아님
             */
            Member findMembmer = em.getReference(Member.class, member1.getId());
            System.out.println("findMembmer = " + findMembmer.getClass()); 
            System.out.println("id = " + findMembmer.getId());
            System.out.println("findMembmer.getUsername() = " + findMembmer.getUsername());
            System.out.println("findMembmer.getClass() = " + findMembmer.getClass());
            System.out.println("========================================");


            /**
             * 프록시 객체는 원본 엔티티를 상속받음,
             * 따라서 타입 체크시 주의해야함 (== 비교 실패, 대신 instance of 사용)
             */
            Member findMember2 = em.find(Member.class, member1.getId());
            Member findMembmer3 = em.getReference(Member.class, member2.getId());
            System.out.println("(findMember2==findMembmer3) = " + (findMember2==findMembmer3));
            System.out.println((findMembmer3 instanceof Member));

            em.flush();
            em.clear();
            /**
             * 이미 영속성 컨텍스트에 있는 엔티티를 getReference로 호출해도 프록시를 반환하는게 아닌
             * 실제 엔티티를 반환함
             * 이미 영속성 컨텍스트에 있기 때문에 당연한 결과
             */
            Member m1 = em.find(Member.class, member1.getId());
            System.out.println("m1 = " + m1.getClass());

            Member reference = em.getReference(Member.class, member1.getId());
            System.out.println("reference = " + reference.getClass());

            em.flush();
            em.clear();
            /**
             * 반대인 상황일경우 프록시객체를 반환했을 떄 find하면 여전히 프록시 객체 반환함
             * ==을 보장해줌
             */
            Member reference2 = em.getReference(Member.class, member1.getId());
            System.out.println(" reference2 = " + reference2.getClass());

            Member m3 = em.find(Member.class, member1.getId());
            System.out.println("m3 = " + m3.getClass());

            tx.commit();

        }
        catch(Exception e) {
            tx.rollback();
            e.printStackTrace();
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

