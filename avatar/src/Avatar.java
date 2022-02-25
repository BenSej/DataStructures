
public class Avatar {
	
	private String name;
	public int wins = 0;
	private Minion minionOne = null;
	private Minion minionTwo = null;
	
	public Avatar(String setName) {
		this.name = setName;
	}
	public String getName() {
		return this.name;
	}
	public boolean choose(Minion input) {
		if (minionOne == null) {
			this.minionOne = input;
			input.newOwner(this);
			return true;
		} else if (minionTwo == null) {
			this.minionTwo = input;
			input.newOwner(this);
			return true;
		} else {
			return false;
		}
	}
	public void trainAllMinions() {
		if (this.minionOne != null) {
			this.minionOne.train(10);
		}
		if (this.minionTwo != null) {
			this.minionTwo.train(10);
		}
	}
	public void win() {
		this.wins++;
	}
	public Minion sendToBattle() {
		if (this.minionOne != null) {
			return minionOne;
		} else if (this.minionTwo != null) {
			return minionTwo;
		} else {
			return null;
		}
	}
	@Override
	public String toString() {
		if (minionOne == null && minionTwo == null) {
			return name + " " + wins;
		} else if (minionTwo == null) {
			return name + " " + wins + " " + minionOne;
		} else if (minionOne == null) {
			return name + " " + wins + " " + minionTwo;
		} else {
			return name + " " + wins + " " + minionOne + " " + minionTwo;
		}
		
	}
}