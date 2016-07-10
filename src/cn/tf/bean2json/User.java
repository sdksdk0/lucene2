package cn.tf.bean2json;

public class User {
	private Integer id;  
	private String name;
	private Integer  sal;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getSal() {
		return sal;
	}
	public void setSal(Integer sal) {
		this.sal = sal;
	}
	public User(Integer id, String name, Integer sal) {
		super();
		this.id = id;
		this.name = name;
		this.sal = sal;
	}
	public User() {
		super();
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", sal=" + sal + "]";
	}
	
	
	
	
	
	

}
