import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

public class Test {
	public static void main(String[] args) {

		@SuppressWarnings("rawtypes")
		Map<String, Comparable> map = new HashMap<String, Comparable>(); // 定义Map对象
		map.put("apple", "新鲜的苹果"); // 向集合中添加对象
		map.put("computer", "配置优良的计算机");
		map.put("book", "堆积成山的图书");
		map.put("time", new Date(0));
		String key = "book3";
		//boolean contains = ; // 判断是否包含指定的键值
		if (map.containsKey(key)) { // 如果条件为真
			System.out.println("在Map集合中包含键名" + key); // 输出信息
		} else {
			System.out.println("在Map集合中不包含键名" + key);
		}

	}
}