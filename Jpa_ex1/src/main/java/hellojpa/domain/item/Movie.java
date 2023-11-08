package hellojpa.domain.item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity @Getter @Setter
//@DiscriminatorValue("M")
public class Movie  extends Item{
    private String director;
    private String actor;
}
