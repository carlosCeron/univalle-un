package com.carlosceron.univalleun.controllers;

import com.carlosceron.univalleun.dto.*;
import com.carlosceron.univalleun.manager.HelperManager;
import com.carlosceron.univalleun.repository.*;
import com.carlosceron.univalleun.umldb.domain.MrSty;
import com.carlosceron.univalleun.umldb.repo.MrStyRepository;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Paths;
import java.util.*;

@RestController
public class HomeController {

    @Autowired
    private NGramRepository nGramRepository;

    @Autowired
    private MRConsoRepository mrConsoRepository;

    @Autowired
    private UmlRepository umlRepository;

    @Autowired
    private NgramRankRepository ngramRankRepository;

    @Autowired
    private MrStyRepository mrStyRepository;

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private ReporteRepository reporteRepository;

    private static final String INDEX_DIR = "C:/lucene/documents";

    @GetMapping("/buscar")
    public void buscar() throws Exception {

        IndexSearcher searcher = createSearcher();

        List<NGrama> listadoNgramas = (List<NGrama>) nGramRepository.findAll();

        for(NGrama elemento : listadoNgramas){

            boolean calculado = false;
            List<String> listadoBigrams = Arrays.asList(HelperManager.removeSpecialCharacter(elemento.getBigrams()));
            TopDocs topDocsBigrams = null;

            for(String valor : listadoBigrams){
                if(valor != null) topDocsBigrams = searchBySrc(valor, searcher);
            }

            List<String> listadoTrigrams = Arrays.asList(HelperManager.removeSpecialCharacter(elemento.getTrigrams()));
            TopDocs topDocsTrigrams = null;

            for(String valor : listadoTrigrams){
                if(valor != null) topDocsTrigrams = searchBySrc(valor, searcher);
            }

            List<String> listadoFourgrams = Arrays.asList(HelperManager.removeSpecialCharacter(elemento.getFourgrams()));

            TopDocs topDocsFourgrams = null;

            for(String valor : listadoFourgrams){
                if(valor != null) topDocsFourgrams = searchBySrc(valor, searcher);
            }

            List<String> listadoFivegrams = Arrays.asList(HelperManager.removeSpecialCharacter(elemento.getFivegrams()));

            TopDocs topDocsFivegrams = null;

            for(String valor : listadoFivegrams){
                if(valor != null) topDocsFivegrams = searchBySrc(valor, searcher);
            }

            if(topDocsFivegrams != null) {

                NgramRank ngramRank = null;
                for(ScoreDoc sd : topDocsFourgrams.scoreDocs) {
                    Document d = searcher.doc(sd.doc);

                    List<Integer> umlList = umlRepository.encontrarDocumentoIdUnicoPorCUI(d.get("cui"));
                    Integer documentId = umlList.get(0);

                    ngramRank = new NgramRank();
                    ngramRank.setCui(d.get("cui"));
                    if(documentId != null) {
                        ngramRank.setDocumentId(String.valueOf(documentId));
                    }
                    ngramRank.setScore(Long.parseLong("" + Math.round(sd.score)));
                    ngramRank.setSentenceId(elemento.getSentenceId());

                    String ngrama = String.valueOf(listadoFivegrams.stream().reduce((acumulador, nuevo) -> {
                        return acumulador + " - " + nuevo;
                    }));

                    ngramRank.setNgrama(ngrama);

                    ngramRankRepository.save(ngramRank);
                    ngramRankRepository.flush();
                    calculado = true;
                }

            }

            if(topDocsFourgrams != null && !calculado) {
                NgramRank ngramRank = null;
                for(ScoreDoc sd : topDocsFourgrams.scoreDocs) {
                    Document d = searcher.doc(sd.doc);
                    List<Integer> umlList = new ArrayList<>();
                    if(elemento != null && elemento.getSentenceId() != null){
                        umlList = umlRepository.encontrarDocumentoIdUnicoPorCUI(d.get("cui"));
                    }

                    Integer documentId = umlList.get(0);

                    ngramRank = new NgramRank();
                    ngramRank.setCui(d.get("cui"));
                    if(documentId != null) {
                        ngramRank.setDocumentId(String.valueOf(documentId));
                    }
                    ngramRank.setScore(Long.parseLong("" + Math.round(sd.score)));
                    ngramRank.setSentenceId(elemento.getSentenceId());

                    String ngrama = String.valueOf(listadoFourgrams.stream().reduce((acumulador, nuevo) -> {
                        return acumulador + " - " + nuevo;
                    }));

                    ngramRank.setNgrama(ngrama);

                    ngramRankRepository.save(ngramRank);
                    ngramRankRepository.flush();
                    calculado = true;
                }

            }

            if(topDocsTrigrams != null && !calculado) {

                NgramRank ngramRank = null;
                for(ScoreDoc sd : topDocsTrigrams.scoreDocs) {
                    Document d = searcher.doc(sd.doc);
                    List<Integer> umlList = umlRepository.encontrarDocumentoIdUnicoPorCUI(d.get("cui"));

                    Integer documentId = umlList.get(0);

                    ngramRank = new NgramRank();
                    ngramRank.setCui(d.get("cui"));
                    if(documentId != null) {
                        ngramRank.setDocumentId(String.valueOf(documentId));
                    }
                    ngramRank.setScore(Long.parseLong("" + Math.round(sd.score)));
                    ngramRank.setSentenceId(elemento.getSentenceId());

                    String ngrama = String.valueOf(listadoTrigrams.stream().reduce((acumulador, nuevo) -> {
                        return acumulador + " - " + nuevo;
                    }));

                    ngramRank.setNgrama(ngrama);

                    ngramRankRepository.save(ngramRank);
                    ngramRankRepository.flush();
                    calculado = true;
                }
            }

            if(topDocsBigrams != null && !calculado) {

                NgramRank ngramRank = null;
                for(ScoreDoc sd : topDocsBigrams.scoreDocs) {
                    Document d = searcher.doc(sd.doc);
                    List<Integer> umlList = umlRepository.encontrarDocumentoIdUnicoPorCUI(d.get("cui"));
                    Integer documentId = umlList.get(0);

                    ngramRank = new NgramRank();
                    ngramRank.setCui(d.get("cui"));
                    if(documentId != null) {
                        ngramRank.setDocumentId(String.valueOf(documentId));
                    }
                    ngramRank.setScore(Long.parseLong("" + Math.round(sd.score)));
                    ngramRank.setSentenceId(elemento.getSentenceId());

                    String ngrama = String.valueOf(listadoBigrams.stream().reduce((acumulador, nuevo) -> {
                        return acumulador + " - " + nuevo;
                    }));

                    ngramRank.setNgrama(ngrama);

                    ngramRankRepository.save(ngramRank);
                    ngramRankRepository.flush();
                }

            }

        }
    }

    @GetMapping("/cruce")
    public void cruce(){


        this.ngramRankRepository.findAll().stream().forEach(ngramRank -> {

            List<Token> tokenList = this.tokenRepository.encontrarTokenPorSentenceId(Integer.parseInt(ngramRank.getSentenceId()));

            tokenList.stream().forEach(token -> {
                List<MrSty> mrStyList = this.mrStyRepository.encontrarStyPorCui(ngramRank.getCui());

                mrStyList.stream().forEach(mrSty -> {
                    Reporte reporte = new Reporte();
                    reporte.setCui(ngramRank.getCui());
                    //TODO: ajustar este campo
                    reporte.setNgrama(token.getScope());
                    reporte.setTipo(token.getType());
                    reporte.setSty(mrSty.getSty());
                    reporte.setStn(mrSty.getStn());
                    reporte.setSentenceID(String.valueOf(token.getSentenceId()));
                    this.reporteRepository.save(reporte);
                });

            });

        });

    }

    @GetMapping("/lucene")
    public void home() throws Exception {

        /* Proceso de indexar */

//        IndexWriter writer = createWriter();
//        List<Document> documentsList = new ArrayList<>();
//
//        mrConsoRepository.findAll()
//                .forEach(mRconso -> {
//                    System.out.println("Procesando el mRconso No = " + mRconso.getId() );
//                    Document document = createDocument(mRconso);
//                    documentsList.add(document);
//                });
//
//        writer.deleteAll();
//
//        writer.addDocuments(documentsList);
//        writer.commit();
//        writer.close();

        /** Proceso de busqueda **/

        IndexSearcher searcher = createSearcher();
        TopDocs topDocs = searchBySrc("carcinoma pulmon", searcher);

        System.out.println("Resultado total: " + topDocs.totalHits);

        for(ScoreDoc sd : topDocs.scoreDocs) {
            Document d = searcher.doc(sd.doc);
            System.out.println(String.format(d.get("cui")) + " - " + d.get("str") + " | " + "Score = " + sd.score);
        }

    }

    private static Document createDocument(MRconso mRconso)
    {
        Document document = new Document();
        document.add(new StringField("id", String.valueOf(mRconso.getId()) , Field.Store.YES));
        document.add(new StringField("cui", mRconso.getCui() , Field.Store.YES));
        document.add(new StringField("str", mRconso.getStr() , Field.Store.YES));


//        document.add(new StringField("id", nGrama.getId().toString() , Field.Store.YES));
//        document.add(new StringField("sentenceId", nGrama.getSentenceId() , Field.Store.YES));
//        document.add(new StringField("sentence", nGrama.getSentence() , Field.Store.YES));
//        document.add(new StringField("cue", nGrama.getCue() , Field.Store.YES));
//        document.add(new TextField("bigrams", nGrama.getBigrams() , Field.Store.YES));
//        document.add(new TextField("trigrams", nGrama.getTrigrams() , Field.Store.YES));
//        document.add(new TextField("fourgrams", nGrama.getFourgrams() , Field.Store.YES));
//        document.add(new TextField("fivegrams", nGrama.getFivegrams() , Field.Store.YES));

        return document;
    }

    private static IndexWriter createWriter() throws IOException
    {
        FSDirectory dir = FSDirectory.open(Paths.get(INDEX_DIR));
        IndexWriterConfig config = new IndexWriterConfig(new StandardAnalyzer());
        IndexWriter writer = new IndexWriter(dir, config);
        return writer;
    }

    private static IndexSearcher createSearcher() throws IOException {
        Directory dir = FSDirectory.open(Paths.get(INDEX_DIR));
        IndexReader reader = DirectoryReader.open(dir);
        IndexSearcher searcher = new IndexSearcher(reader);
        return searcher;
    }


    /** Buscadores **/

//    private static TopDocs searchByFivegram(String fivegram, IndexSearcher searcher) throws Exception
//    {
//        QueryParser qp = new QueryParser("fivegrams", new StandardAnalyzer());
//        Query firstNameQuery = qp.parse(fivegram);
//        TopDocs hits = searcher.search(firstNameQuery, 10);
//        return hits;
//    }

    private static TopDocs searchBySrc(String src, IndexSearcher searcher) throws Exception
    {
        QueryParser qp = new QueryParser("str", new StandardAnalyzer());
        Query firstNameQuery = qp.parse(src);
        TopDocs hits = searcher.search(firstNameQuery, 10);
        return hits;
    }


}
