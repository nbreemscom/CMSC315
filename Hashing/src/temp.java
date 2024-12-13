import java.util.Random;

public class temp {

	public static void main(String[] args) {
		HashSet<Integer> hs = new HashSet<Integer>();
		Random rng = new Random();
		
		for (int i = 0; i < 25; i++) {
			hs.add(rng.nextInt(50));
		}
		
		for (Integer i : hs) {
			System.out.print(i + " ");
		}
		System.out.println();
		
		System.out.println(hs);
		
		for (int i = 10; i < 15; i++) {
			System.out.println("Does the set have a " + i + "?  " 
					+ hs.contains(i));
		}
		
		System.out.println("Hello there".hashCode());
	}

}
