package com.komyar.demonstration.db.repository;

import com.komyar.demonstration.db.entity.BaseEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.Optional;

@NoRepositoryBean
public interface BaseRepository<E extends BaseEntity, ID extends Serializable> extends CrudRepository<E, ID>  {
    Optional<E> findByUuid(String uuid);
}
