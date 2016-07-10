package cn.tf.fy.entity;

public class Article {


	private  Integer id;  //索引号
	private  String title; //标题
	private  String content;  //内容
	public  Article(){}
	public Article(Integer id, String title, String content) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Override
	public String toString() {
		return "编号:"+id+"\t标题"+title+"\t内容:"+content;
	}
	
}
