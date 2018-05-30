
class GenericStack<E> {
	private java.util.ArrayList<E> list = new java.util.ArrayList<E>();

	public int getSize() {
		return list.size();
	}

	public E peek() {
		return list.get(getSize() - 1);
	}

	public void push(E o) {
		list.add(o);
	}

	public E pop() {
		E o = peek();// list.get(getSize()-1);
		list.remove(getSize() - 1);
		return o;
	}

	public boolean isEmpty() {
		return list.isEmpty();
	}
}

public class Test {

	public static void main(String[] args) {
		GenericStack<String> stack1 = new GenericStack<String>();
		stack1.push("London");
		stack1.push("Paris");
		stack1.push("Beijing");

		for (int i = 0; i <stack1.getSize(); i++) {
			System.out.println(stack1.peek());
			stack1.pop();
			System.out.println(stack1.isEmpty());

		}

		GenericStack<Integer> stack2 = new GenericStack<Integer>();
		stack2.push(7);
		stack2.push(1);
		stack2.push(5);

		for (int i = 0; i <stack2.getSize(); i++) {
			System.out.println(stack2.peek());
			stack2.pop();

		}
	}
}