import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class Array_Test {
	

	public static void print(Collection<? extends Object> c) {
		Iterator<? extends Object> it = c.iterator();
		while (it.hasNext()) {
			Object object = (Object) it.next();
			System.out.println(object);
		}
	}


	public  Array_Test() {
		Set<String> set = new HashSet<String>();
		set.add("AAA");
		set.add("BBB");
		set.add("CCC");
		print(set);

		// Set�ĵ�һ�ֱ�����ʽ:����Iterator
		Iterator<String> it1 = set.iterator();
		for (String ss : set) {
			System.out.println(ss);
		}
		// Set�ĵ�һ�ֱ�����ʽ:����foreach
		for (String sss : set) {
			System.out.println(sss);
		}

		List<String> list = new ArrayList<String>();
		list.add("DDDDD");
		list.add("EEEEE");
		list.add("FFFFF");
		print(list);

		// List�ĵ�һ�ֱ�����ʽ:��Ϊlist��˳������size()��get()������ȡ
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
		// List�ĵڶ��ֱ�����ʽ������Iterator
		Iterator<String> it = list.iterator();
		while (it.hasNext()) {
			System.out.println(it.next());
		}
		// List�ĵ����ֱ�����ʽ:����foreach
		for (String s2 : list) {
			System.out.println(s2);
		}

		Map<String, String> map = new TreeMap<String, String>();
		map.put("Jerry", "10000");
		map.put("shellway", "20000");
		map.put("Kizi", "30000");
		print(map.entrySet());
		// Map�ĵ�һ�ֱ�����ʽ���Ȼ��key,�ٻ��ֵvalue
		Set<String> sett = map.keySet();
		System.out.println("------------------Print Map:");
		for (String s : sett) {
			System.out.println(s + ":" + map.get(s));
		}
		// Map�ĵڶ��ֱ�����ʽ����ü�ֵ��
		System.out.println("------------------Print Map:K-V");
		for (Map.Entry<String, String> entry : map.entrySet()) {
			System.out.println(entry.getKey() + " : " + entry.getValue());
		}
	}
	
	public static void main(String[] args) {
		new Array_Test();
	}
	

}