package hellojpa.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@Entity
public class Parent {
    @Id @GeneratedValue
    private Long id;
    private String name;

    @OneToMany(mappedBy = "parent",cascade = CascadeType.ALL)
    private List<Child> childList =new ArrayList<>();

    public void addChild(Child child){
        childList.add(child);
        System.out.println("this = "+this);
        child.setParent(this);
    }
}
