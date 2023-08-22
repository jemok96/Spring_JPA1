package hellojpa;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Member {

    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String username;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    @Embedded
    private Period workPeriod;
    @Embedded
    private Address homeAddress;


    public void changeTeam(Team team){
        this.team = team;
        team.getMembers().add(this);
    }
    public Member() {
    }

    @Builder
    public Member(Long id, String username, Team team, Period workPeriod, Address homeAddress) {
        this.id = id;
        this.username = username;
        this.team = team;
        this.workPeriod = workPeriod;
        this.homeAddress = homeAddress;
    }
}
