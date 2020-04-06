package animals;

public class Lion extends Animal {
	
	private boolean wild;
	private boolean dunkel;
	
	public Lion(boolean wild, boolean dunkel) {
		super();
		this.wild = wild;
		this.dunkel = dunkel;
	}

	public boolean isWild() {
		return wild;
	}

	public void setWild(boolean wild) {
		this.wild = wild;
	}

	public boolean isDunkel() {
		return dunkel;
	}

	public void setDunkel(boolean dunkel) {
		this.dunkel = dunkel;
	}
	
	@Override
	public String getFeed() {
		if(isDunkel() && !isWild()) {
			return "it's hunting time!";
		}
		else if(!isDunkel() && isWild()) {
			return "there is some game out there to hunt";
		}
		return "nothing to eat";
	}

}
