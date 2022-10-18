package com.komyar.demonstration.db.entity;

import com.komyar.demonstration.util.DateUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import static com.komyar.demonstration.enums.DbConstants.CREATION_DATE_COLUMN;
import static com.komyar.demonstration.enums.DbConstants.UPDATE_DATE_COLUMN;

@MappedSuperclass
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public abstract class BaseEntity<ID extends Serializable>{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private ID id;

    @Column(name = CREATION_DATE_COLUMN)
    private LocalDate creationDate;

    @Column(name = UPDATE_DATE_COLUMN)
    private LocalDateTime updateDate;

    @Column
    private String uuid;

    @PrePersist
    public void setDate(){
        this.uuid = UUID.randomUUID().toString();
        this.creationDate = DateUtil.localDateOfNow();
        this.updateDate = DateUtil.localDateTimeOfNow();
    }
}
