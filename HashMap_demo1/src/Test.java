import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

public class Test {
	public static void main(String[] args) {

		@SuppressWarnings("rawtypes")
		Map<String, Comparable> map_a = new HashMap<String, Comparable>(); // ����Map����
		map_a.put("apple", "201701051407"); // �򼯺�����Ӷ���
		map_a.put("computer", "���������ļ����");
		map_a.put("book", "�ѻ���ɽ��ͼ��");
		map_a.put("time", new Date(0));
		
		Map<String, Comparable> map_b = new HashMap<String, Comparable>(); // ����Map����
		map_b.put("apple", "201701051407"); // �򼯺�����Ӷ���
		map_b.put("computer", "���������ļ����");
		map_b.put("book", "�ѻ���ɽ��ͼ��");
		map_b.put("time", new Date(0));
		
		
		String key = "apple1", value="2017010514079";
		//boolean contains = ; // �ж��Ƿ����ָ���ļ�ֵ
		if (map_a.containsKey(key)) { // �������Ϊ��
			System.out.println("��Map�����а�������" + key); // �����Ϣ
			System.out.println(map_a.get(key));
			if(map_a.get(key)==value) {
				System.out.println("They are same!");
			}
			else {
				System.out.println("They are different:"+map_a.get(key)+" the candidate value is :"+value);
			}
		} else {
			System.out.println("��Map�����в���������" + key);
			System.out.println(map_a.get(key));
		}

	}
}