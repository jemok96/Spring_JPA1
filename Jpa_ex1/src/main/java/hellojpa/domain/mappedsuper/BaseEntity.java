package hellojpa.domain.mappedsuper;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
public class BaseEntity {
    @Column(name = "INSERT_MEMBER")
    private String createdBy;
    private LocalDateTime caatedDate;
    private LocalDateTime lastModifiedDate;
}
