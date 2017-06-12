package com.shlagoverflow.api.util;

import com.shlagoverflow.core.dto.TopicDto;
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

    public void indexTitle(String title) {
        IndexWriterConfig config = new IndexWriterConfig(analyzer);
        IndexWriter w;
        try
        {
            w = new IndexWriter(index, config);
            addDoc(w, title);
            w.close();
        } catch (IOException e) {
            LOGGER.error("Lucene Indexing exception: ", e);
        }
    }

    public List<TopicDto> search(String queryStr) {
        List<TopicDto> correspondance = new ArrayList<>();
        try {
            // the "title" arg specifies the default field to use
            // when no field is explicitly specified in the query.
            final Query q = new QueryParser("title", analyzer).parse(queryStr);

            // 3. search
            int hitsPerPage = 10;
            IndexReader reader = DirectoryReader.open(index);
            IndexSearcher searcher = new IndexSearcher(reader);
            TopDocs docs = searcher.search(q, hitsPerPage);
            ScoreDoc[] hits = docs.scoreDocs;

            // 4. display results
            LOGGER.info(String.format("%s %d %s", "Found ", hits.length, " hits."));
            for (int i = 0; i < hits.length; ++i) {
                int docId = hits[i].doc;
                final Document d = searcher.doc(docId);
                correspondance.add(new TopicDto(d.get("title"), null, hits[0].score));

            }

            // reader can only be closed when there
            // is no need to access the documents any more.
            reader.close();

        }catch(Exception e) {
            e.printStackTrace();
        }

        return correspondance;
    }

    private static void addDoc(IndexWriter w, String title) throws IOException {
        Document doc = new Document();
        doc.add(new TextField("title", title, Field.Store.YES));
        w.addDocument(doc);
    }
}
