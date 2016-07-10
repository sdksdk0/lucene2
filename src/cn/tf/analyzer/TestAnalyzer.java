package cn.tf.analyzer;

import java.io.StringReader;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.cjk.CJKAnalyzer;
import org.apache.lucene.analysis.fr.FrenchAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.TermAttribute;
import org.wltea.analyzer.lucene.IKAnalyzer;

import cn.tf.util.LuceneUtil;


//测试Lucene内置和第三方分词器的分词效果
public class TestAnalyzer {
	
	private static void testAnalyzer(Analyzer analyzer, String text) throws Exception {
		System.out.println("当前使用的分词器：" + analyzer.getClass());
		TokenStream tokenStream = analyzer.tokenStream("content",new StringReader(text));
		tokenStream.addAttribute(TermAttribute.class);
		while (tokenStream.incrementToken()) {
			TermAttribute termAttribute = tokenStream.getAttribute(TermAttribute.class);
			System.out.println(termAttribute.term());
		}
	}

	public static void main(String[] args) throws Exception {
		testAnalyzer(new StandardAnalyzer(LuceneUtil.getVersion()), "采用一种算法aa，将中英文本中的字符拆分开来，形成词汇，以待用户输入关健字后搜索");
		testAnalyzer(new FrenchAnalyzer(LuceneUtil.getVersion()), "采用一种算法aa，将中英文本中的字符拆分开来，形成词汇，以待用户输入关健字后搜索");
		testAnalyzer(new CJKAnalyzer(LuceneUtil.getVersion()), "采用一种算法aa，将中英文本中的字符拆分开来，形成词汇，以待用户输入关健字后搜索");
		testAnalyzer(new IKAnalyzer(), "上海自来水来自海上");
		testAnalyzer(new IKAnalyzer(), "指令汇科技实业呀");
		
	}
	

}
