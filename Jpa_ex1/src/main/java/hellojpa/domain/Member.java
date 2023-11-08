package hellojpa.domain;

import hellojpa.domain.embeddable.Address;
import hellojpa.domain.embeddable.Period;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter

public class Member{

    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String username;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    @Embedded
    private Period period;
    @Embedded
    private Address address;

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", team=" + team +
                ", period=" + period +
                ", address=" + address +
                '}';
    }

    public void changeTeam(Team team){
        this.team = team;
        System.out.println("this = " + this);
        team.getMembers().add(this);
    }
    
    

    public Member() {
    }


}
