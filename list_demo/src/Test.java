import java.util.ArrayList;

class Test {
	public static void main(String[] args) {
		ArrayList<String> list = new ArrayList<String>();
		ArrayList<Integer> intList = new ArrayList<Integer>();
		list.add("RED");
		list.add("GREEN");
		intList.add(2);
		System.out.println(list.size());
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}
}
