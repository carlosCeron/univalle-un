package com.carlosceron.univalleun;

import com.carlosceron.univalleun.es.upm.ctb.midas.uncertainty.Annotator;
import com.carlosceron.univalleun.es.upm.ctb.midas.uncertainty.SpanishTokenizer;
import edu.stanford.nlp.util.CoreMap;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class UnivalleunApplication {

    static SpanishTokenizer tokenizer;
    static Annotator annotator1;

    public static void main(String[] args) {
        tokenizer =  new SpanishTokenizer();
        annotator1 = new Annotator();
        String text1 = "Posible cancer de pulmón en 2018, pero espero resultados, no dolor de cabeza.";
        List<CoreMap> sentences = tokenizer.tokenizeDocument(text1.toLowerCase());
        annotator1.process(sentences, "path");
        annotator1.printScopes();
        System.out.println("\n ============  Finished ok1============ \n");
        SpringApplication.run(UnivalleunApplication.class, args);
    }

}
