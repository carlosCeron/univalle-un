package com.carlosceron.univalleun.repository;

import com.carlosceron.univalleun.dto.Uml;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface UmlRepository extends PagingAndSortingRepository<Uml, Integer> {

    @Query("SELECT distinct u from Uml u")
    Page<Uml> encontrarElementosDistintos(Pageable pageable);

    @Query("SELECT distinct u.document_id from Uml u WHERE u.cui = ?1")
    List<Integer> encontrarDocumentoIdUnicoPorCUI(String cui);

}
