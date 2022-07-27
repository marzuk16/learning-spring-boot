package self.learning.learningspringboot.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import self.learning.learningspringboot.enums.RecordStatus;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@MappedSuperclass
public class BaseEntity implements Serializable {
    private static final Long serialVersionUID = 1L;

    @JsonIgnore
//    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATED_AT", updatable = false)
    protected LocalDateTime createdAt;

    @JsonIgnore
//    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UPDATED_AT")
    protected LocalDateTime updatedAt;

    @Version
    @JsonIgnore
    @Column(name = "RECORD_VERSION")
    private Integer recordVersion;

    @JsonIgnore
    @Enumerated(EnumType.STRING)
    @Column(name = "RECORD_STATUS")
    private RecordStatus recordStatus;

    //    @Type(type = "uuid-char")
    @Column(name = "CREATOR", updatable = false)
    private Long createdBy;

    //    @Type(type = "uuid-char")
    @Column(name = "UPDATOR")
    private Long updatedBy;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
