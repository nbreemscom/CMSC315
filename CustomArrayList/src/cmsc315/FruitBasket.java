package cmsc315;

import java.util.List;

public class FruitBasket {

	public static void main(String[] args) {
		
		List<String> x = new CustomArrayList<String>();

		x.add("Apple");
		x.add("Banana");
		x.add("Date");
		x.add(2, "Cherry");
		
		System.out.println("How many items? " + x.size());
	}

}
