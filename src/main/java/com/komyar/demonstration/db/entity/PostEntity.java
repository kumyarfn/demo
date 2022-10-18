package com.komyar.demonstration.db.entity;

import lombok.*;
import javax.persistence.*;
import static com.komyar.demonstration.enums.DbConstants.*;

@Entity
@Table(name = POST_COLLECTION_NAME,
      indexes = {@Index(name = UUID_INDEX, columnList = UUID_COLUMN, unique = true)}
)
@Builder @Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class PostEntity extends BaseEntity<Long>{

    @Column(name = PAGE_UUID_COLUMN)
    private String pageUUID;

    @Column
    private String text;

    @Column
    private Long likes;

    @Column
    private Long reposts;


}
