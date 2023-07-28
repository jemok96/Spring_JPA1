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

   private String city;
   private String street;
   private String zipcode;



}
