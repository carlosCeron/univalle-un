package com.carlosceron.univalleun.umldb.repo;

import com.carlosceron.univalleun.umldb.domain.MrSty;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MrStyRepository extends CrudRepository<MrSty, String> {

    @Query("SELECT m from MrSty m WHERE m.atui = ?1")
    List<MrSty> encontrarStyPorCui(String atui);
}
