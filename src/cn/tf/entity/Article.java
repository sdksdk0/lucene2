package cn.tf.entity;

public class Article {
	
	private  Integer id;  //编号
	private  String title; //标题
	private  String content;  //内容
	private  Integer count;  //字数
	
	public  Article(){}

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

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "编号:"+id+"\t标题:"+title+"\t内容:"+content+"\t字数:"+count;
	}

	public Article(Integer id, String title, String content, Integer count) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.count = count;
	}
	

}
