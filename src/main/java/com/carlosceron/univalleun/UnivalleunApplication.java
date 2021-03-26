package com.carlosceron.univalleun;

import com.carlosceron.univalleun.es.upm.ctb.midas.uncertainty.Annotator;
import com.carlosceron.univalleun.es.upm.ctb.midas.uncertainty.SpanishTokenizer;
import edu.stanford.nlp.util.CoreMap;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class UnivalleunApplication {



    public static void main(String[] args) {

        /*String text1 = "Posible cancer de pulmón en 2018, pero espero resultados, no dolor de cabeza.";
        List<CoreMap> sentences = tokenizer.tokenizeDocument(text1.toLowerCase());
        annotator1.process(sentences, "path");
        annotator1.printScopes(); */
        SpringApplication.run(UnivalleunApplication.class, args);
    }

}
