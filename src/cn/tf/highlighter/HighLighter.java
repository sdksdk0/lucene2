package cn.tf.highlighter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.Formatter;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.Scorer;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.RAMDirectory;
import org.junit.Test;

import cn.tf.entity.Article;
import cn.tf.util.LuceneUtil;

public class HighLighter {
	
	@Test
	public void add() throws Exception{
		Article  at=new Article(1, "lucene","Lucene是apache软件基金会发布的一个开放源代码的全文检索引擎工具包",10);
		//创建Document对象
		Document doc=LuceneUtil.javabean2Document(at);
		IndexWriter  iw=new IndexWriter(LuceneUtil.getD(),LuceneUtil.getAnalyzer(),LuceneUtil.getMfl());
		iw.addDocument(doc);
		iw.close();
	}
	
	@Test
	public void findAll() throws Exception {
		String keywords="是";
		List<Article>  atl=new ArrayList<Article>();
		
		QueryParser  queryParser=new QueryParser(LuceneUtil.getVersion(), "content", LuceneUtil.getAnalyzer());
		Query query=queryParser.parse(keywords);
		
		IndexSearcher  is=new IndexSearcher(LuceneUtil.getD());
		TopDocs td=is.search(query, 100);
		
		
		//以下代码中含有关键字的字符串高亮显示
		
		//格式对象
		Formatter formatter = new SimpleHTMLFormatter("<font color='red'>","</font>");
		//关键字对象
		Scorer scorer = new QueryScorer(query);
		//高亮对象
		Highlighter highlighter = new Highlighter(formatter,scorer);
		
		
		for(int i=0;i<td.scoreDocs.length;i++){
			ScoreDoc  sd=td.scoreDocs[i];
			int no=sd.doc;
			Document document = is.doc(no);
			
			//关键字高亮
			//关键字高亮
			String titleHighlighter = highlighter.getBestFragment(LuceneUtil.getAnalyzer(),"title",document.get("title"));
			String contentHighlighter = highlighter.getBestFragment(LuceneUtil.getAnalyzer(),"content",document.get("content"));
			
			//将高亮后的结果再次封装到document对象中
			document.getField("title").setValue(titleHighlighter);
			document.getField("content").setValue(contentHighlighter);
			
			
			Article at=(Article) LuceneUtil.document2Javabean(document, Article.class);
			atl.add(at);
		}
		for (Article article : atl) {
			System.out.println(article);
		}
		
	}

	
	
	
	
}
