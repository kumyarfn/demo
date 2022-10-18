package com.komyar.demonstration.db.entity;


import com.komyar.demonstration.enums.Role;
import lombok.*;
import javax.persistence.*;
import java.util.Set;

import static com.komyar.demonstration.enums.DbConstants.*;

@Entity
@Table(name = PAGE_COLLECTION_NAME,
        indexes = {@Index(name = USERNAME_INDEX, columnList = USERNAME_COLUMN, unique = true),
                   @Index(name = PHONE_NUMBER_INDEX, columnList = PHONE_NUMBER_COLUMN, unique = true),
                   @Index(name = UUID_INDEX, columnList = UUID_COLUMN, unique = true)}
)
@Builder @Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class PageEntity extends BaseEntity<Long>{

    @Column
    private String username;

    @Column
    private String password;

    @Column(name = PHONE_NUMBER_COLUMN)
    private String phoneNumber;

    @Column(name = FULL_NAME_COLUMN)
    private String fullName;

    @Column
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = FOLLOWING_INFO_COLUMN,
              joinColumns = @JoinColumn(name = FOLLOWED_BY_ID_COLUMN),
              inverseJoinColumns = @JoinColumn(name = FOLLOWING_ID_COLUMN))
    private Set<PageEntity> following;

    @Column(name = FOLLOWED_BY_COLUMN)
    @ManyToMany(mappedBy = FOLLOWING_COLUMN, fetch = FetchType.EAGER)
    private Set<PageEntity> followedBy;

    @Column
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = IS_ACTIVE_COLUMN)
    private Boolean isActive;

    @Column(name = IS_NON_BLOCKED_COLUMN)
    private Boolean isNonLocked;


}
