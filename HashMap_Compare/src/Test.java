import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

public class Test {
	public static void main(String[] args) {

		@SuppressWarnings("rawtypes")
		Map<String, Comparable> map = new HashMap<String, Comparable>(); // ����Map����
		map.put("apple", "���ʵ�ƻ��"); // �򼯺�����Ӷ���
		map.put("computer", "���������ļ����");
		map.put("book", "�ѻ���ɽ��ͼ��");
		map.put("time", new Date(0));
		String key = "book3";
		//boolean contains = ; // �ж��Ƿ����ָ���ļ�ֵ
		if (map.containsKey(key)) { // �������Ϊ��
			System.out.println("��Map�����а�������" + key); // �����Ϣ
		} else {
			System.out.println("��Map�����в���������" + key);
		}

	}
}