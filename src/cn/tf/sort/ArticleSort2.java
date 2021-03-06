package cn.tf.sort;

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
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.RAMDirectory;
import org.junit.Test;

import cn.tf.entity.Article;
import cn.tf.util.LuceneUtil;


//按照相关度排名
public class ArticleSort2 {
	
	//增加document对象索引库中
	@Test
	public void add() throws Exception{
		//Article  at=new Article(1, "lucene","Lucene是apache软件基金会发布的一个开放源代码的全文检索引擎工具包",10);
		//Article  at=new Article(2, "lucene2","Lucene2是apache软件基金会发布的一个开放源代码的全文检索引擎工具包",20);
		Article  at=new Article(3, "新lucene3","Lucene3是apache软件基金会发布的一个开放源代码的全文检索引擎工具包",30);
		//Article  at=new Article(4, "lucene4","Lucene4是是软件基金会发布是时的一个开放源是代码的全文是检索引擎是工具包",30);
		//Article  at=new Article(5, "lucene5","是是是是是是是是是是是是是是是是是是是是是是是是是是是是是是是是是是是是是是",30);
		
		
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
	//	TopDocs td=is.search(query, 100);
		
		
		//创建排序的条件
		/*
		 * 参数1：id表示依据document对象中的那个字段排序，里如id
		 * 参数2：sortField.INT表示document对象中该字段的类型，
		 * 参数3：true表示降序，类似于order by
		 * 
		 * 
		 */
		Sort sort=new Sort(new SortField("id",SortField.INT,true));
		TopDocs td=is.search(query, null,100,sort);
		
		for(int i=0;i<td.scoreDocs.length;i++){
			ScoreDoc  sd=td.scoreDocs[i];
			int no=sd.doc;
		
			
			Document document = is.doc(no);
			Article at=(Article) LuceneUtil.document2Javabean(document, Article.class);
			atl.add(at);
		}
		for (Article article : atl) {
			System.out.println(article);
		}
		
	}

	
	
	
	
}
