package hellojpa;



import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;


@Getter
@Setter
@Entity
//@Table //엔티티와 매핑할 테이블 지정
public class Member {

    @Id  //PK매핑
    @GeneratedValue//AUTO_INCREMENT
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name="USERNAME") // DB의 컬럼명, 자바에서의 변수명 다를경우  Column(name)으로 DB컬럼 명 지정 가능함
    private String username;

    @ManyToOne //Member입장에서 N  TEAM입장에서 1
    @JoinColumn(name = "TEAM_ID") //어떤 값으로 조인할 것인지
    private Team team;

    public void setTeam(Team team) {
        this.team = team;
        team.getMembers().add(this);  // 순수 객체 상태를 고려해서 항상 양쪽에 값을 설정하자
    }

    //외래키 값을 테이블에 맞춰 그대로 가져옴 객체지향적이지 않음
//    @Column(name = "TEAM_ID")
//    private Long teamId;







//    @Enumerated(EnumType.STRING)//EnumType.STRING 써야함 EnumType.ORDINAL은 enum추가 시 순서에 의한 문제가 생길 수 있음
//    private RoleType roleType;
//
//    @Temporal(TemporalType.TIMESTAMP)
//    private Date createdDate;
//
//    @Temporal(TemporalType.TIMESTAMP) //날짜 타입 매핑
//    private Date lastModifiedDate;
//
//    //@Temporal(TemporalType.TIMESTAMP)이거 대신 그냥 LocalDateTiem(년월일 시분초)이나 LocalDate(년월일)쓰면됨
////    private LocalDateTime testLocal;
//    @Lob // BLOB, CLOB 매핑
//    private String description;

    public Member() {
    }



}
