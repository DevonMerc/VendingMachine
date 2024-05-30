package com.techelevator;

public class Application {
	VendingMachine vendingMachine = new VendingMachine();
	public static void main(String[] args) {
		Application application = new Application();
		application.run();
	}
	public void run() {
		vendingMachine.importGoods();
		vendingMachine.mainMenu();
	}
}
