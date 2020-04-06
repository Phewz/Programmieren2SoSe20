package animals;

public class CommonBlackBird extends Animal {

	
	private boolean insects;
	private boolean berries;
	
	public CommonBlackBird(boolean insects, boolean berries) {
		super();
		this.insects = insects;
		this.berries = berries;
	}
	
	public boolean isInsects() {
		return insects;
	}

	public void setInsects(boolean insects) {
		this.insects = insects;
	}


	public boolean isBerries() {
		return berries;
	}

	public void setBerries(boolean berries) {
		this.berries = berries;
	}

	@Override
	public String getFeed() {
		if(isInsects() && !isBerries()) {
			return "time to eat some insects!";
		}
		else if(!isInsects() && isBerries()) {
			return "eat berries";
		}
		return "nothing to eat";
	}

}
