package com.komyar.demonstration.db.repository;

import com.komyar.demonstration.db.entity.PageEntity;
import org.springframework.stereotype.Repository;
import java.util.Optional;
@Repository
public interface PageRepository extends BaseRepository<PageEntity, Long> {

    Optional<PageEntity> findByUsername(String username);

    Optional<PageEntity> findByPhoneNumber(String phoneNumber);



}
