package com.komyar.demonstration.db.repository;

import com.komyar.demonstration.db.entity.PostEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends BaseRepository<PostEntity, Long> {

    Page<PostEntity> findByPageUUID(String uuid, Pageable pageable);

}
