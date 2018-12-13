package list.linkedlist.implementation;

public class Main {

	public static void main(String[] args) {
		LinkedList numbers = new LinkedList();
		numbers.addFirst(10);
		numbers.addFirst(20);
		numbers.addFirst(30);
		numbers.add(3, 40);
		numbers.addLast(50);
		numbers.removeFirst();
		numbers.remove(0);
		numbers.removeLast();
		System.out.println(numbers);
		System.out.println(numbers.size());
		System.out.println(numbers.get(0));
		System.out.println(numbers.indexOf(10));
		LinkedList.ListIterator i = numbers.listIterator();
//		System.out.println(i.next());
//		System.out.println(i.hasNext());
//		System.out.println(i.next());
//		System.out.println(i.hasNext());
		System.out.println();
		while(i.hasNext()) {
			System.out.println(i.next());
		}
	}

}
