package com.carlosceron.univalleun.controllers;

import com.carlosceron.univalleun.dto.Uml;
import com.carlosceron.univalleun.es.upm.ctb.midas.uncertainty.Annotator;
import com.carlosceron.univalleun.es.upm.ctb.midas.uncertainty.SpanishTokenizer;
import com.carlosceron.univalleun.repository.TokenRepository;
import com.carlosceron.univalleun.repository.UmlRepository;
import com.sun.deploy.panel.ITreeNode;
import edu.stanford.nlp.util.CoreMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class SaludoController {

    private static final int LIMIT = 10000;

    static SpanishTokenizer tokenizer;
    static Annotator annotator1;

    @Autowired
    private UmlRepository umlRepository;

    @Autowired
    private TokenRepository tokenRepository;

    @GetMapping("/hello-world")
    public ResponseEntity<String> get() {
        tokenizer =  new SpanishTokenizer();
        annotator1 = new Annotator();
        int page = 0;
        Pageable pageable = PageRequest.of(page, LIMIT, Sort.unsorted());
        Page<Uml> modelPage = umlRepository.findAll(pageable);
        // List<Object> listadoElementos = umlRepository.findDistinctBySentenceId();
        while (true) {
            for (Uml model : modelPage.getContent()) {
                List<CoreMap> sentences = tokenizer.tokenizeDocument(model.getSentence().toLowerCase());
                annotator1.process(sentences, "path");
                annotator1.printScopes(model.getSentence(), model.getSentenceId(), tokenRepository);
                System.out.println(model.getSentenceId());
            }
            if (!modelPage.hasNext()) {
                break;
            }
            pageable = modelPage.nextPageable();
            modelPage = umlRepository.findAll(pageable);
        }

        return ResponseEntity.ok("============  Finished ok============");
    }

}
