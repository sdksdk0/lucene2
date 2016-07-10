package cn.tf.search;

import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.junit.Test;

import cn.tf.entity.Article;
import cn.tf.util.LuceneUtil;




public class ArticleDao {

	
	@Test
	public void add() throws Exception{
		//Article  at=new Article(1, "lucene","Lucene是apache软件基金会发布的一个开放源代码的全文检索引擎工具包",10);
		//Article  at=new Article(2, "lucene2","Lucene2是apache软件基金会发布的一个开放源代码的全文检索引擎工具包",20);
		//Article  at=new Article(3, "新lucene3","Lucene3是apache软件基金会发布的一个开放源代码的全文检索引擎工具包",30);
		Article  at=new Article(4, "lucene4","Lucene4是是软件基金会发布是时的一个开放源是代码的全文是检索引擎是工具包",30);
		//Article  at=new Article(5, "lucene5","是是是是是是是是是是是是是是是是是是是是是是是是是是是是是是是是是是是是是是",30);
		
		Document document = LuceneUtil.javabean2Document(at);
		IndexWriter indexWriter = new IndexWriter(LuceneUtil.getD()	,LuceneUtil.getAnalyzer(),LuceneUtil.getMfl());
		indexWriter.addDocument(document);
		indexWriter.close();
	}
	
	
	
	
	@Test
	public void findAll() throws Exception{
		String keywords = "是";
		List<Article> articleList = new ArrayList<Article>();
		
	
		//QueryParser queryParser = new QueryParser(LuceneUtil.getVersion(),"title",LuceneUtil.getAnalyzer());
		
		
		QueryParser queryParser = 
			new MultiFieldQueryParser(
					LuceneUtil.getVersion(),
					new String[]{"content","title"},
					LuceneUtil.getAnalyzer());
		
		
		Query query = queryParser.parse(keywords);
		IndexSearcher indexSearcher = new IndexSearcher(LuceneUtil.getD());
		
		TopDocs topDocs = indexSearcher.search(query,100);
		
		for(int i=0;i<topDocs.scoreDocs.length;i++){
			ScoreDoc scoreDoc = topDocs.scoreDocs[i];
			int no = scoreDoc.doc;
			Document document = indexSearcher.doc(no);
			Article article = (Article) LuceneUtil.document2Javabean(document,Article.class);
			articleList.add(article);
		}
		for(Article a : articleList){
			System.out.println(a);
		}
	}
}





