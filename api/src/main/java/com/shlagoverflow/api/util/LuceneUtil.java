package com.shlagoverflow.api.util;

import com.shlagoverflow.core.dto.TopicDto;
import com.shlagoverflow.core.model.Question;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
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
import org.apache.lucene.store.RAMDirectory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Leboc Philippe.
 */
public class LuceneUtil {

    private static Logger LOGGER = LoggerFactory.getLogger(LuceneUtil.class);

    private static LuceneUtil ourInstance = new LuceneUtil();
    public static LuceneUtil getInstance() {
        return ourInstance;
    }

    // Lucene
    private StandardAnalyzer analyzer = new StandardAnalyzer();
    private Directory index = new RAMDirectory();

    private LuceneUtil() {}

    public void index(Question question) {
        final IndexWriterConfig config = new IndexWriterConfig(analyzer);
        final IndexWriter w;
        try
        {
            w = new IndexWriter(index, config);
            final Document doc = new Document();
            doc.add(new TextField("title", question.getTitle(), Field.Store.YES));
            doc.add(new TextField("answer", question.getAnswer(), Field.Store.YES));
            w.addDocument(doc);
            w.close();
        } catch (IOException e) {
            LOGGER.error("Lucene Indexing exception: ", e);
        }
    }

    public List<TopicDto> search(String queryStr) {
        List<TopicDto> questions = new ArrayList<>();
        try {
            // the "title" arg specifies the default field to use
            // when no field is explicitly specified in the query.
            final Query q = new QueryParser("title", analyzer).parse(queryStr);
            final Query a = new QueryParser("answer", analyzer).parse(queryStr);

            // 3. search
            int hitsPerPage = 10;
            final IndexReader reader = DirectoryReader.open(index);
            final IndexSearcher searcher = new IndexSearcher(reader);

            // Recherche dans les titres
            final TopDocs docs = searcher.search(q, hitsPerPage);
            final ScoreDoc[] hits = docs.scoreDocs;

            // Recherche dans les réponses aux questions
            final TopDocs docsAnswers = searcher.search(a, hitsPerPage);
            final ScoreDoc[] hitsAnswers = docsAnswers.scoreDocs;

            // 4. display results
            // LOGGER.info(String.format("%s %d %s", "Found ", hits.length, " questions hits."));

            for (int i = 0; i < hits.length; i++) {
                final int docId = hits[i].doc;
                final Document d = searcher.doc(docId);
                questions.add(new TopicDto(d.get("title"), d.get("answer"), hits[i].score));
            }

            //LOGGER.info(String.format("%s %d %s", "Found ", hits.length, " answers hits."));
            for (int i = 0; i < hitsAnswers.length; i++) {
                final int docId = hitsAnswers[i].doc;
                final Document d = searcher.doc(docId);
                questions.add(new TopicDto(d.get("title"), d.get("answer"), hitsAnswers[i].score));
            }

            // reader can only be closed when there
            // is no need to access the documents any more.
            reader.close();
        }catch(Exception e) {
            LOGGER.error("Search Exception: " + questions.size(), e);
        }

        return questions;
    }
}
