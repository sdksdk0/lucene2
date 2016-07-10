package cn.tf.bean2json;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import net.sf.json.JSONArray;

public class TestJson {
	
	@Test
	public void javabean2Json(){
		User user=new User(1,"张三",6500);
		JSONArray  jsonArray=JSONArray.fromObject(user);
		String jsonJAVA=jsonArray.toString();
		System.out.println(jsonJAVA);
		//[{"id":1,"name":"张三","sal":6500}]
	}
	
	@Test
	public void list2Json(){
		List<User>  userList=new ArrayList<User>();
		userList.add(new User(1,"李明",8000));
		userList.add(new User(2,"王志",7000));
		userList.add(new User(3,"何春",10000));
		
		JSONArray jsonArray=JSONArray.fromObject(userList);
		String jsonJAVA=jsonArray.toString();
		System.out.println(jsonJAVA);
		//[{"id":1,"name":"李明","sal":8000},{"id":2,"name":"王志","sal":7000},{"id":3,"name":"何春","sal":10000}]

	}
	
	@Test
	public void map2Json(){
		List<User>  userList=new ArrayList<User>();
		userList.add(new User(1,"李明",8000));
		userList.add(new User(2,"王志",7000));
		userList.add(new User(3,"何春",10000));
		
		Map<String,Object>  map=new HashMap<String,Object>();
		//集合的长度
		map.put("total",userList.size());
		//集合的内容
		map.put("rows",userList);
		
		JSONArray jsonArray=JSONArray.fromObject(map);
		String jsonJAVA=jsonArray.toString();
		System.out.println(jsonJAVA);
		//[{"total":3,"rows":[{"id":1,"name":"李明","sal":8000},{"id":2,"name":"王志","sal":7000},{"id":3,"name":"何春","sal":10000}]}]

	}

}
