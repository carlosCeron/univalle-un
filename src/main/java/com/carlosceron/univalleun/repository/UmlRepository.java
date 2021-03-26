package com.carlosceron.univalleun.repository;

import com.carlosceron.univalleun.dto.Uml;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface UmlRepository extends PagingAndSortingRepository<Uml, Integer> {

    @Query("SELECT distinct d.sentenceId, d.sentence FROM Uml d")
    Page<Object> findDistinctBySentenceId(Pageable pageable);

    Page<Uml> findAll(Pageable pageable);

}
