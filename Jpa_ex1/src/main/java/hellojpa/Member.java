package hellojpa;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
public class Member extends BaseEntity{

    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String username;

    @ManyToOne
    @JoinColumn(name = "TEAM_ID")
    private Team team;

//    @Column(name = "TEAM_ID")
//    private Long teamId;

    public void changeTeam(Team team){
        this.team = team;
        team.getMembers().add(this);
    }
    public Member() {
    }


}
