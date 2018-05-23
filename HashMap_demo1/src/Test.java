import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

public class Test {
	public static void main(String[] args) {

		@SuppressWarnings("rawtypes")
		Map<String, Comparable> map_a = new HashMap<String, Comparable>(); // 定义Map对象
		map_a.put("apple", "201701051407"); // 向集合中添加对象
		map_a.put("computer", "配置优良的计算机");
		map_a.put("book", "堆积成山的图书");
		map_a.put("time", new Date(0));
		
		Map<String, Comparable> map_b = new HashMap<String, Comparable>(); // 定义Map对象
		map_b.put("apple", "201701051407"); // 向集合中添加对象
		map_b.put("computer", "配置优良的计算机");
		map_b.put("book", "堆积成山的图书");
		map_b.put("time", new Date(0));
		
		
		String key = "apple1", value="2017010514079";
		//boolean contains = ; // 判断是否包含指定的键值
		if (map_a.containsKey(key)) { // 如果条件为真
			System.out.println("在Map集合中包含键名" + key); // 输出信息
			System.out.println(map_a.get(key));
			if(map_a.get(key)==value) {
				System.out.println("They are same!");
			}
			else {
				System.out.println("They are different:"+map_a.get(key)+" the candidate value is :"+value);
			}
		} else {
			System.out.println("在Map集合中不包含键名" + key);
			System.out.println(map_a.get(key));
		}

	}
}