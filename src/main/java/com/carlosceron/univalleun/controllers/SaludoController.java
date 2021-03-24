package com.carlosceron.univalleun.controllers;

import com.carlosceron.univalleun.dto.Uml;
import com.carlosceron.univalleun.repository.UmlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class SaludoController {

    @Autowired
    private UmlRepository umlRepository;

    @GetMapping("/hello-world")
    public ResponseEntity<String> get() {
        Optional<Uml> uml = umlRepository.findById(2);
        return ResponseEntity.ok(uml.get().getSentence());
    }

}
