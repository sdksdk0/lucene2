package cn.tf.optimize;

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
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.RAMDirectory;
import org.junit.Test;

import cn.tf.entity.Article;
import cn.tf.util.LuceneUtil;

public class ArticleDao {
	
/*	//增加document对象索引库中
	@Test
	public void add() throws Exception{
		Article  at=new Article(1, "lucene","Lucene是apache软件基金会发布的一个开放源代码的全文检索引擎工具包",10);
		//创建Document对象
		Document doc=LuceneUtil.javabean2Document(at);
		IndexWriter  iw=new IndexWriter(LuceneUtil.getD(),LuceneUtil.getAnalyzer(),LuceneUtil.getMfl());
		iw.addDocument(doc);
		iw.close();
	}
	
	//合并.cfs文件,解决文件的大小和数量问题
	@Test
	public void type1() throws Exception{
		Article  at=new Article(1, "lucene","Lucene是apache软件基金会发布的一个开放源代码的全文检索引擎工具包",10);
		//创建Document对象
		Document doc=LuceneUtil.javabean2Document(at);
		IndexWriter  iw=new IndexWriter(LuceneUtil.getD(),LuceneUtil.getAnalyzer(),LuceneUtil.getMfl());
		iw.optimize();
		iw.close();
	}
	
	//合并.cfs文件,解决文件的大小和数量问题
	@Test
	public void type2() throws Exception{
		Article  at=new Article(2, "lucene","Lucene是apache软件基金会发布的一个开放源代码的全文检索引擎工具包",10);
		//创建Document对象
		Document doc=LuceneUtil.javabean2Document(at);
		IndexWriter  iw=new IndexWriter(LuceneUtil.getD(),LuceneUtil.getAnalyzer(),LuceneUtil.getMfl());
		
		//设置合并因子，即满足3个cfs文件合并
		iw.addDocument(doc);
		iw.setMergeFactor(3);
		iw.close();
	}*/
	
	//合并.cfs文件,解决文件的大小和数量问题
	@Test
	public void type4() throws Exception{
		Article  at=new Article(2, "lucene","Lucene是apache软件基金会发布的一个开放源代码的全文检索引擎工具包",10);
		Document doc=LuceneUtil.javabean2Document(at);
		
		//硬盘索引库
		Directory fsDirectory=FSDirectory.open(new File("D:/IndexDB"));
		
		
		//内存索引库
		Directory ramDirectory=new RAMDirectory(fsDirectory);
		
		//指向硬盘索引库的字符流,true表示如果内存索引库中和硬盘索引库中有系统的document对象时，先删除硬盘索引库的值
		IndexWriter fsIw=new IndexWriter(fsDirectory,LuceneUtil.getAnalyzer(),true,LuceneUtil.getMfl());
		
		//指向内存索引库的字符流
		IndexWriter ramIw=new IndexWriter(ramDirectory,LuceneUtil.getAnalyzer(),LuceneUtil.getMfl());
		
		//将document对象写入内存索引库
		ramIw.addDocument(doc);
		ramIw.close();
		
		//将内存索引库中所有的document对象同步到硬盘索引中
		fsIw.addIndexesNoOptimize(ramDirectory);
		fsIw.close();
	}
	
	
	
	@Test
	public void findAll() throws Exception {
		String keywords="是";
		List<Article>  atl=new ArrayList<Article>();
		
		
		
		QueryParser  queryParser=new QueryParser(LuceneUtil.getVersion(), "content", LuceneUtil.getAnalyzer());
		Query query=queryParser.parse(keywords);
		
		IndexSearcher  is=new IndexSearcher(LuceneUtil.getD());
		TopDocs td=is.search(query, 100);
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
