package cmsc315;

import java.util.List;

public class FruitBasket {

	public static void main(String[] args) {
		
		List<String> x = new CustomLinkedList<String>();

		x.add("Apple");
		x.add("Banana");
		x.add("Date");
		x.add(2, "Cherry");
		
		System.out.println("How many items? " + x.size());
		System.out.println("Does it have grapefruit? " + 
				x.contains("Grapefruit"));
		System.out.println("Does it contain Cherry? " + 
				x.contains("Cherry"));
		
		System.out.println("--------------------------");

		for (String s : x) {
			System.out.println(s);
		}
		
	}

}
