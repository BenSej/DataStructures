public class Exam2Tester {

	public static void main(String[] args) {
		Avatar ash;
		Avatar paul;
		Minion pika;
		Minion ivy;
		Minion bulba;
		Minion charman;

		ash = new Avatar("Ash");
		paul = new Avatar("Paul");
		pika = new Minion("Pika");
		ivy = new Minion("Ivy");
		bulba = new Minion("Bulba");
		charman = new Minion("Charman");

		System.out.println("******");
		System.out.println(ash);
		System.out.println(paul);
		System.out.println(pika);
		System.out.println(ivy);
		System.out.println(bulba);
		System.out.println(charman);

		pika.train(20);
		if (ash.choose(pika)) {
			System.out.println(ash.getName() + " has chosen " + pika.getName());
		}
		ash.trainAllMinions();
		paul.trainAllMinions();
		ash.sendToBattle();
		paul.sendToBattle();
		ivy.battle();

		System.out.println("******");
		System.out.println(ash);
		System.out.println(paul);
		System.out.println(pika);
		System.out.println(ivy);
		System.out.println(bulba);
		System.out.println(charman);

		if (paul.choose(ivy)) {
			System.out.println(paul.getName() + " has chosen " + ivy.getName());
		}
		ash.trainAllMinions();
		paul.trainAllMinions();
		if (ash.choose(bulba)) {
			System.out.println(ash.getName() + " has chosen " + bulba.getName());
		}
		ash.sendToBattle();
		ash.sendToBattle();
		ash.trainAllMinions();

		System.out.println("******");
		System.out.println(ash);
		System.out.println(paul);
		System.out.println(pika);
		System.out.println(ivy);
		System.out.println(bulba);
		System.out.println(charman);

		if (ash.choose(charman)) {
			System.out.println(ash.getName() + " has chosen " + charman.getName());
		} else {
			System.out.println(ash.getName() + " already has 2 minions!");
		}
		bulba.newOwner(paul);

		System.out.println("******");
		System.out.println(ash);
		System.out.println(paul);
		System.out.println(pika);
		System.out.println(ivy);
		System.out.println(bulba);
		System.out.println(charman);

		//add more test cases here as needed
	}

}
