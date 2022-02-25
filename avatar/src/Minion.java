
public class Minion {
	
	private String name;
	public double strength = 0.1;
	private Avatar owner = null;
	
	public Minion(String setName) {
		this.name = setName;
	}
	public String getName() {
		return this.name;
	}
	public void battle() {
		if (this.owner != null) {
			owner.wins++;
		}
	}
	public void newOwner(Avatar input) {
		if (this.owner == null) {
			this.owner = input;
		}
	}
	public void train(double x) {
		this.strength += (0.25 * x);
	}
	@Override
	public String toString() {
		if (owner == null) {
			return this.getName() + " " + this.strength;
		} else {
			return this.getName() + " " + this.strength + " " + this.owner.getName();
		}
	}
}