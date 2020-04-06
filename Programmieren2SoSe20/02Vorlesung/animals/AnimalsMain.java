package animals;

public class AnimalsMain {

	public static void main(String[] args) {
		Animal [] animals = new Animal[2];
		animals[0] = new Lion(true, false);
		animals[1] = new CommonBlackBird(false, true);
		
		System.out.println(animals[0].getFeed());
		System.out.println(animals[1].getFeed());

	}

}
