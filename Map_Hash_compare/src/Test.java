import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Test {
	public static void main(String[] args) {
		Map<String, String> map1 = new HashMap<String, String>();
		Map<String, String> map2 = new HashMap<String, String>();
		map1.put("a", "aa");
		map1.put("b", "bb");
		map1.put("c", "cc");
		map2.put("1", "11");
		map2.put("b", "22");
		map2.put("3", "34");
		
		Iterator<String> it = map1.keySet().iterator();
		while(it.hasNext()){
			Object key = it.next();
			if(map2.containsKey(key)){
				System.out.println(map1.get(key));
				System.out.println(map2.get(key));
			}
		}
		
}
}