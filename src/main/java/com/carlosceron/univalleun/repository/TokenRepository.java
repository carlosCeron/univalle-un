package com.carlosceron.univalleun.repository;

import com.carlosceron.univalleun.dto.Token;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TokenRepository extends CrudRepository<Token, Integer> {

    @Query("SELECT t from Token t WHERE t.sentenceId = ?1")
    List<Token> encontrarTokenPorSentenceId(Integer sentenceId);

}
