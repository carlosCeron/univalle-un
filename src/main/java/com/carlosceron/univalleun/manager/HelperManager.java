package com.carlosceron.univalleun.manager;

import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class HelperManager {


    public static String removeStopWords(String sentence) throws IOException {

//        ClassLoader classLoader = HelperManager.class.getClassLoader();
//        InputStream inputStream = classLoader.getResourceAsStream("spanish.txt");

        File file = ResourceUtils.getFile("classpath:spanish.txt");

        ArrayList<String> stopWords = new ArrayList<>(Files.readAllLines(file.toPath()));

        ArrayList<String> allWords =
                Stream.of(sentence.toLowerCase().split(" "))
                        .collect(Collectors.toCollection(ArrayList<String>::new));

        // save stop words
        allWords.removeAll(stopWords);

        /** Se une la oracion nuevamente y se envia de retorno sin los stopwords **/
        String result = allWords.stream().collect(Collectors.joining(" "));

        return result;
    }

    public static String[] removeSpecialCharacter(String texto){
        String[] arregloElementos = new String[0];
        String textoUpdate = texto.replaceAll("\\[", "").replaceAll("\\]","");
        textoUpdate  = textoUpdate.replaceAll("'", "");
        if(textoUpdate.length() > 0) return textoUpdate.split(",");
        return arregloElementos;

    }

}
