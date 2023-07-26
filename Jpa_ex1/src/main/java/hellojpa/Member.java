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
    private Long id;
    @Column(name="name") // DB의 컬럼명, 자바에서의 변수명 다를경우  Column(name)으로 DB컬럼 명 지정 가능함
    private String name;

    private Integer age;







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
