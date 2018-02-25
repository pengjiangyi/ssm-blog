package com.ssm.util;

import java.io.StringReader;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.Fragmenter;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleFragmenter;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.search.highlight.SimpleSpanFragmenter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import com.ssm.Ikanalyzer.MyIkAnalyzer;
import com.ssm.pojo.Article;

public class LuceneIndex {
	private Directory dir = null;

	/**
	 * 获取IndexWriter实例
	 * 
	 * @return
	 * @throws Exception
	 */
	private IndexWriter getWriter() throws Exception {
		/**
		 * 生成的索引我放在了C盘，可以根据自己的需要放在具体位置
		 */
		dir = FSDirectory.open(Paths.get("C://lucene"));
		Analyzer analyzer = new MyIkAnalyzer();
		IndexWriterConfig iwc = new IndexWriterConfig(analyzer);
		IndexWriter writer = new IndexWriter(dir, iwc);
		return writer;
	}

	/**
	 * 添加博客索引
	 * 
	 * @param user
	 */
	public void addIndex(Article artilce) throws Exception {
		IndexWriter writer = getWriter();
		Document doc = new Document();
		doc.add(new StringField("id", String.valueOf(artilce.getId()), Field.Store.YES));
		/**
		 * yes是会将数据存进索引，如果查询结果中需要将记录显示出来就要存进去，如果查询结果 只是显示标题之类的就可以不用存，而且内容过长不建议存进去
		 * 使用TextField类是可以用于查询的。
		 */
		doc.add(new TextField("title", artilce.getTitle(), Field.Store.YES));
		doc.add(new TextField("text",artilce.getText(),Field.Store.YES));
		writer.addDocument(doc);
		writer.close();
	}

	/**
	 * 更新博客索引
	 * 
	 * @param user
	 * @throws Exception
	 */
	public void updateIndex(Article artilce) throws Exception {
		IndexWriter writer = getWriter();
		Document doc = new Document();
		doc.add(new StringField("id", String.valueOf(artilce.getId()), Field.Store.YES));
		doc.add(new TextField("title", artilce.getTitle(), Field.Store.YES));
		doc.add(new TextField("text", artilce.getText(), Field.Store.YES));
		writer.updateDocument(new Term("id", String.valueOf(artilce.getId())), doc);
		writer.close();
	}

	/**
	 * 删除指定博客的索引
	 * 
	 * @param userId
	 * @throws Exception
	 */
	public void deleteIndex(String articleid) throws Exception {
		IndexWriter writer = getWriter();
		writer.deleteDocuments(new Term("id", articleid));
		writer.forceMergeDeletes(); // 强制删除
		writer.commit();
		writer.close();
	}

	/**
	 * 查询博客
	 * 
	 * @param q
	 *            查询关键字
	 * @return
	 * @throws Exception
	 */
	public List<Article> searchBlog(String q) throws Exception {
		/**
		 * 注意的是查询索引的位置得是存放索引的位置，不然会找不到。
		 */
		dir = FSDirectory.open(Paths.get("C://lucene"));
		IndexReader reader = DirectoryReader.open(dir);
		IndexSearcher is = new IndexSearcher(reader);
		BooleanQuery.Builder booleanQuery = new BooleanQuery.Builder();
		Analyzer analyzer = new MyIkAnalyzer();
		/**
		 * username和description就是我们需要进行查找的两个字段 同时在存放索引的时候要使用TextField类进行存放。
		 */
		QueryParser parser = new QueryParser("title", analyzer);
		Query query = parser.parse(q);
		QueryParser parser2 = new QueryParser("text", analyzer);
		Query query2 = parser2.parse(q);
		booleanQuery.add(query, BooleanClause.Occur.SHOULD);
		booleanQuery.add(query2, BooleanClause.Occur.SHOULD);
		TopDocs hits = is.search(booleanQuery.build(), 100);
		QueryScorer scorer = new QueryScorer(query);

		// System.out.println(scorer);

		Fragmenter fragmenter = new SimpleSpanFragmenter(scorer);
		/**
		 * 这里可以根据自己的需要来自定义查找关键字高亮时的样式。
		 */
		SimpleHTMLFormatter simpleHTMLFormatter = new SimpleHTMLFormatter("<b><font color='red'>", "</font></b>");
		Highlighter highlighter = new Highlighter(simpleHTMLFormatter, scorer);

		// 设置返回的字符数
		//highlighter.setTextFragmenter(fragmenter);

		 highlighter.setTextFragmenter(new SimpleFragmenter(300));
		List<Article> articleList = new LinkedList<Article>();
		for (ScoreDoc scoreDoc : hits.scoreDocs) {

			Document doc = is.doc(scoreDoc.doc);

			Article article = new Article();
			article.setId((Integer.parseInt(doc.get(("id")))));
		
			String title = doc.get("title");
			String text = doc.get("text");
			

			if (title != null) {
				TokenStream tokenStream = analyzer.tokenStream("title", new StringReader(title));
				String htitle = highlighter.getBestFragment(tokenStream, title);
				if (htitle == null) {
					article.setTitle(title);
				} else {
					article.setTitle(htitle);
				}
			}
			if (text != null) {
				TokenStream tokenStream = analyzer.tokenStream("text", new StringReader(text));
				String htext = highlighter.getBestFragment(tokenStream, text);

				if (htext == null) {

					article.setText(text);

				} else {
					article.setText(htext);
				}
			}
			articleList.add(article);
		}
		return articleList;
	}
}
