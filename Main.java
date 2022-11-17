import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import static java.lang.System.out;

public class Main {
	static int choice, howMany;
	static String addMore, operation;

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) throws IOException {
		String[] productNames = { "Regular Burger", "Cheese Burger", "Egg Burger", "Double Burger" }; 
		double[] productPrices = { 20.00, 25.00, 30.00, 40.00 }; 
		boolean[] productSelected = { false, false, false, false }; 

		int burgerMade, 
			orderNum = 1, 
			bunBatchNum = 2, 
			pattyBatchNum = 2,
			cheeseBatchNum = 2,
			eggBatchNum = 2;

		double price; 

		Queue bunInventory = new Queue(),
			  pattyInventory = new Queue(),
			  cheeseInventory = new Queue(),
			  eggInventory = new Queue(),
			  processedOrders = new Queue(),
			  currentOrder = new Queue();

		bunInventory.enqueue(new Node("Bun", 30, 30, 0, 1));
		pattyInventory.enqueue(new Node("Beef Patty", 30, 30, 0, 1));
		cheeseInventory.enqueue(new Node("Cheese Slice", 60, 60, 0, 1));
		eggInventory.enqueue(new Node("Egg", 20, 20, 0, 1));

		out.printf("%s%n%23s%n%28s%n%s%n", "=".repeat(30), "STEPHEN'S BORGIR", "Unang Kagat Tinapay Lahat", "=".repeat(30));

		Node currentBatchOfBun = bunInventory.getCurrentBatch(),
			 currentBatchOfPatty = pattyInventory.getCurrentBatch(),
			 currentBatchOfCheese = cheeseInventory.getCurrentBatch(),
			 currentBatchOfEgg = eggInventory.getCurrentBatch();

		int currentStockOfBun = currentBatchOfBun.getStock(),
			currentStockOfPatty = currentBatchOfPatty.getStock(),
			currentStockOfCheese = currentBatchOfCheese.getStock(),
			currentStockOfEgg = currentBatchOfEgg.getStock(),

			currentConsumedBun = currentBatchOfBun.getConsumed(),
			currentConsumedPatty = currentBatchOfPatty.getConsumed(),
			currentConsumedCheese = currentBatchOfCheese.getConsumed(),
			currentConsumedEgg = currentBatchOfEgg.getConsumed();

		while (true) {
			out.println("\n+----------------------------+\n" +
						  "|         MAIN MENU          |\n" +
						  "+----------------------------+\n" +
						  "| [1] Process Customer Order |\n" +
						  "| [2] View Inventory         |\n" +
						  "| [3] Replenish Inventory    |\n" +
						  "| [4] View Processed Orders  |\n" +
						  "| [5] EXIT                   |\n" +
						  "+----------------------------+");

			InputUserChoice("Main Menu", "operation num");

			if (choice == 1) {
				if (currentStockOfBun == 0) {
					currentBatchOfBun = bunInventory.getCurrentBatch();
					currentStockOfBun = currentBatchOfBun.getStock();
					currentConsumedBun = currentBatchOfBun.getConsumed();
				}

				if (currentStockOfPatty == 0) {
					currentBatchOfPatty = pattyInventory.getCurrentBatch();
					currentStockOfPatty = currentBatchOfPatty.getStock();
					currentConsumedPatty = currentBatchOfPatty.getConsumed();
				}

				if (currentStockOfCheese == 0) {
					currentBatchOfCheese = cheeseInventory.getCurrentBatch();
					currentStockOfCheese = currentBatchOfCheese.getStock();
					currentConsumedCheese = currentBatchOfCheese.getConsumed();
				}

				if (currentStockOfEgg == 0) {
					currentBatchOfEgg = eggInventory.getCurrentBatch();
					currentStockOfEgg = currentBatchOfEgg.getStock();
					currentConsumedEgg = currentBatchOfEgg.getConsumed();
				}

				if (bunInventory.getAllStock() == 0 && pattyInventory.getAllStock() == 0 && cheeseInventory.getAllStock() == 0 && eggInventory.getAllStock() == 0) {
					out.println("\nAll ingredients out of stock.\nPlease replenish the inventory.");
					continue;
				}

				if (bunInventory.getAllStock() == 0 && pattyInventory.getAllStock() == 0) {
					out.println("\nBun and Patty out of stock.\nPlease replenish the inventory.");
					continue;
				}

				if (bunInventory.getAllStock() == 0) {
					out.println("\nBun out of stock. Please\nreplenish the inventory.");
					continue;
				}

				if (pattyInventory.getAllStock() == 0) {
					out.println("\nPatty out of stock. Please\nreplenish the inventory.");
					continue; 
				}

				out.print("\n+----------------------------+\n" +
							"|   PROCESS CUSTOMER ORDER   |\n" +
							"+--------------------+-------+\n" +
							"|      Products      | Price |\n" +
							"+--------------------+-------+\n" +
							"| [1] Regular Burger | 20.00 |\n" +
							"| [2] Cheese Burger  | 25.00 |\n" +
							"| [3] Egg Burger     | 30.00 |\n" +
							"| [4] Double Burger  | 40.00 |\n" +
							"+--------------------+-------+\n");

				for (int index = 0; index < productSelected.length; ++index) {
					productSelected[index] = false;
				}

				while (true) {
					burgerMade = 0; 
					operation = "made"; 

					InputUserChoice("Sub Menu", "product num");

					if (choice == 1) { 
						InputHowMany(); 

						while (burgerMade != howMany) {
							if (bunInventory.getAllStock() == 0 || pattyInventory.getAllStock() == 0) {
								if (productSelected[choice - 1])
									operation = "added";

								out.println("\nOnly " + burgerMade + " Regular Burger was " + operation + "\ndue to a lack of ingredients.");
								break; 
							}

							currentStockOfBun -= 1;
							currentStockOfPatty -= 1;

							currentConsumedBun += 1;
							currentConsumedPatty += 1;

							currentBatchOfBun.setStock(currentStockOfBun);
							currentBatchOfPatty.setStock(currentStockOfPatty);

							currentBatchOfBun.setConsumed(currentConsumedBun);
							currentBatchOfPatty.setConsumed(currentConsumedPatty);

							burgerMade++; 

							if (currentStockOfBun == 0) {
								currentBatchOfBun = bunInventory.getCurrentBatch();
								currentStockOfBun = currentBatchOfBun.getStock();
								currentConsumedBun = currentBatchOfBun.getConsumed();
							}

							if (currentStockOfPatty == 0) {
								currentBatchOfPatty = pattyInventory.getCurrentBatch();
								currentStockOfPatty = currentBatchOfPatty.getStock();
								currentConsumedPatty = currentBatchOfPatty.getConsumed();
							}
						}

						price = burgerMade * productPrices[choice - 1];
					} else if (choice == 2) { 
						if (cheeseInventory.getAllStock() == 0) {
							if (addMore.equalsIgnoreCase("Y")) {
								out.println("\nCheese out of stock.\nCannot add this product.");
								continue;
							}

							out.println("\nCheese out of stock. Please\nreplenish the inventory.");
							break;
						}

						InputHowMany(); 

						while (burgerMade != howMany) {
							if (bunInventory.getAllStock() == 0 || pattyInventory.getAllStock() == 0 || cheeseInventory.getAllStock() == 0) {
								if (productSelected[choice - 1])
									operation = "added";

								out.println("\nOnly " + burgerMade + " Cheese Burger was " + operation
										+ "\ndue to a lack of ingredients.");
								break;
							}

							currentStockOfBun -= 1;
							currentStockOfPatty -= 1;
							currentStockOfCheese -= 1;

							currentConsumedBun += 1;
							currentConsumedPatty += 1;
							currentConsumedCheese += 1;

							currentBatchOfBun.setStock(currentStockOfBun);
							currentBatchOfPatty.setStock(currentStockOfPatty);
							currentBatchOfCheese.setStock(currentStockOfCheese);

							currentBatchOfBun.setConsumed(currentConsumedBun);
							currentBatchOfPatty.setConsumed(currentConsumedPatty);
							currentBatchOfCheese.setConsumed(currentConsumedCheese);

							burgerMade++;

							if (currentStockOfBun == 0) {
								currentBatchOfBun = bunInventory.getCurrentBatch();
								currentStockOfBun = currentBatchOfBun.getStock();
								currentConsumedBun = currentBatchOfBun.getConsumed();
							}

							if (currentStockOfPatty == 0) {
								currentBatchOfPatty = pattyInventory.getCurrentBatch();
								currentStockOfPatty = currentBatchOfPatty.getStock();
								currentConsumedPatty = currentBatchOfPatty.getConsumed();
							}

							if (currentStockOfCheese == 0) {
								currentBatchOfCheese = cheeseInventory.getCurrentBatch();
								currentStockOfCheese = currentBatchOfCheese.getStock();
								currentConsumedCheese = currentBatchOfCheese.getConsumed();
							}
						}

						price = burgerMade * productPrices[choice - 1];
					} else if (choice == 3) { 
						if (cheeseInventory.getAllStock() == 0 && eggInventory.getAllStock() == 0) {
							if (addMore.equalsIgnoreCase("Y")) {
								out.println("\nCheese and Egg out of stock.\nCannot add this product.");
								continue; 
							}

							out.println("\nCheese and Egg out of stock.\nPlease replenish the inventory.");
							break;
						}

						if (cheeseInventory.getAllStock() == 0) {
							if (addMore.equalsIgnoreCase("Y")) {
								out.println("\nCheese out of stock.\nCannot add this product.");
								continue; 
							}

							out.println("\nCheese out of stock. Please\nreplenish the inventory.");
							break; 
						}

						if (eggInventory.getAllStock() == 0) {
							if (addMore.equalsIgnoreCase("Y")) {
								out.println("\nEgg out of stock.\nCannot add this product.");
								continue; 
							}

							out.println("\nEgg out of stock. Please\nreplenish the inventory.");
							break;
						}

						InputHowMany(); 

						while (burgerMade != howMany) {
							if (bunInventory.getAllStock() == 0 || pattyInventory.getAllStock() == 0 || cheeseInventory.getAllStock() == 0 || eggInventory.getAllStock() == 0) {
								if (productSelected[choice - 1]) 
									operation = "added";

								out.println("\nOnly " + burgerMade + " Egg Burger was " + operation + "\ndue to a lack of ingredients.");
								break; 
							}

							currentStockOfBun -= 1;
							currentStockOfPatty -= 1;
							currentStockOfCheese -= 1;
							currentStockOfEgg -= 1;

							currentConsumedBun += 1;
							currentConsumedPatty += 1;
							currentConsumedCheese += 1;
							currentConsumedEgg += 1;

							currentBatchOfBun.setStock(currentStockOfBun);
							currentBatchOfPatty.setStock(currentStockOfPatty);
							currentBatchOfCheese.setStock(currentStockOfCheese);
							currentBatchOfEgg.setStock(currentStockOfEgg);

							currentBatchOfBun.setConsumed(currentConsumedBun);
							currentBatchOfPatty.setConsumed(currentConsumedPatty);
							currentBatchOfCheese.setConsumed(currentConsumedCheese);
							currentBatchOfEgg.setConsumed(currentConsumedEgg);

							burgerMade++; 

							if (currentStockOfBun == 0) {
								currentBatchOfBun = bunInventory.getCurrentBatch();
								currentStockOfBun = currentBatchOfBun.getStock();
								currentConsumedBun = currentBatchOfBun.getConsumed();
							}

							if (currentStockOfPatty == 0) {
								currentBatchOfPatty = pattyInventory.getCurrentBatch();
								currentStockOfPatty = currentBatchOfPatty.getStock();
								currentConsumedPatty = currentBatchOfPatty.getConsumed();
							}

							if (currentStockOfCheese == 0) {
								currentBatchOfCheese = cheeseInventory.getCurrentBatch();
								currentStockOfCheese = currentBatchOfCheese.getStock();
								currentConsumedCheese = currentBatchOfCheese.getConsumed();
							}

							if (currentStockOfEgg == 0) {
								currentBatchOfEgg = eggInventory.getCurrentBatch();
								currentStockOfEgg = currentBatchOfEgg.getStock();
								currentConsumedEgg = currentBatchOfEgg.getConsumed();
							}
						}

						price = burgerMade * productPrices[choice - 1];
					} else { 
						if (cheeseInventory.getAllStock() == 0) {
							if (addMore.equalsIgnoreCase("Y")) {
								out.println("\nCheese out of stock.\nCannot add this product.");
								continue;
							}

							out.println("\nCheese out of stock. Please\nreplenish the inventory.");
							break; 
						}

						if (pattyInventory.getAllStock() < 2 && cheeseInventory.getAllStock() < 2) {
							out.println("\nInsufficient Patty and\nCheese to\nmake a double burger.");
							continue; 
						}

						if (pattyInventory.getAllStock() < 2) {
							out.println("\nInsufficient Patty to\nmake a double burger.");
							continue; 
						}

						if (cheeseInventory.getAllStock() < 2) {
							out.println("\nInsufficient Cheese to\nmake a double burger.");
							continue; 
						}

						InputHowMany();

						while (burgerMade != howMany) {
							if (bunInventory.getAllStock() == 0 || pattyInventory.getAllStock() < 2 || cheeseInventory.getAllStock() < 2) {
								if (productSelected[choice - 1]) 
									operation = "added";

								out.println("\nOnly " + burgerMade + " Double Burger was " + operation + "\ndue to a lack of ingredients.");
								break; 
							}

							if (currentStockOfPatty == 1 && pattyInventory.getAllStock() > 1 && currentStockOfCheese == 1 && cheeseInventory.getAllStock() > 1) {
								currentStockOfBun -= 1;
								currentConsumedBun += 1;

								currentBatchOfBun.setStock(currentStockOfBun);
								currentBatchOfBun.setConsumed(currentConsumedBun);

								for (int index = 0; index <= 1; index++) {
									currentStockOfPatty -= 1;
									currentStockOfCheese -= 1;

									currentConsumedPatty += 1;
									currentConsumedCheese += 1;

									currentBatchOfPatty.setStock(currentStockOfPatty);
									currentBatchOfPatty.setConsumed(currentConsumedPatty);

									currentBatchOfCheese.setStock(currentStockOfCheese);
									currentBatchOfCheese.setConsumed(currentConsumedCheese);

									if (index == 0) {
										currentBatchOfPatty = pattyInventory.getCurrentBatch();
										currentStockOfPatty = currentBatchOfPatty.getStock();
										currentConsumedPatty = currentBatchOfPatty.getConsumed();

										currentBatchOfCheese = cheeseInventory.getCurrentBatch();
										currentStockOfCheese = currentBatchOfCheese.getStock();
										currentConsumedCheese = currentBatchOfCheese.getConsumed();
									}
								}

								burgerMade++;
							} else if (currentStockOfPatty == 1 && pattyInventory.getAllStock() > 1) {
								currentStockOfBun -= 1;
								currentConsumedBun += 1;

								currentBatchOfBun.setStock(currentStockOfBun);
								currentBatchOfBun.setConsumed(currentConsumedBun);

								for (int index = 0; index <= 1; index++) {
									currentStockOfPatty -= 1;
									currentConsumedPatty += 1;

									currentBatchOfPatty.setStock(currentStockOfPatty);
									currentBatchOfPatty.setConsumed(currentConsumedPatty);

									if (index == 0) {
										currentBatchOfPatty = pattyInventory.getCurrentBatch();
										currentStockOfPatty = currentBatchOfPatty.getStock();
										currentConsumedPatty = currentBatchOfPatty.getConsumed();
									}
								}

								currentStockOfCheese -= 2;
								currentConsumedCheese += 2;

								currentBatchOfCheese.setStock(currentStockOfCheese);
								currentBatchOfCheese.setConsumed(currentConsumedCheese);

								burgerMade++; 
							} else if (currentStockOfCheese == 1 && cheeseInventory.getAllStock() > 1) {
								currentStockOfBun -= 1;
								currentConsumedBun += 1;

								currentBatchOfBun.setStock(currentStockOfBun);
								currentBatchOfBun.setConsumed(currentConsumedBun);

								for (int index = 0; index <= 1; index++) {
									currentStockOfCheese -= 1;
									currentConsumedCheese += 1;

									currentBatchOfCheese.setStock(currentStockOfCheese);
									currentBatchOfCheese.setConsumed(currentConsumedCheese);

									if (index == 0) {
										currentBatchOfCheese = cheeseInventory.getCurrentBatch();
										currentStockOfCheese = currentBatchOfCheese.getStock();
										currentConsumedCheese = currentBatchOfCheese.getConsumed();
									}
								}

								currentStockOfPatty -= 2;
								currentConsumedPatty += 2;

								currentBatchOfPatty.setStock(currentStockOfPatty);
								currentBatchOfPatty.setConsumed(currentConsumedPatty);

								burgerMade++;

							} else {
								currentStockOfBun -= 1;
								currentStockOfPatty -= 2;
								currentStockOfCheese -= 2;

								currentConsumedBun += 1;
								currentConsumedPatty += 2;
								currentConsumedCheese += 2;

								currentBatchOfBun.setStock(currentStockOfBun);
								currentBatchOfPatty.setStock(currentStockOfPatty);
								currentBatchOfCheese.setStock(currentStockOfCheese);

								currentBatchOfBun.setConsumed(currentConsumedBun);
								currentBatchOfPatty.setConsumed(currentConsumedPatty);
								currentBatchOfCheese.setConsumed(currentConsumedCheese);

								burgerMade++;
							}

							if (currentStockOfBun == 0) {
								currentBatchOfBun = bunInventory.getCurrentBatch();
								currentStockOfBun = currentBatchOfBun.getStock();
								currentConsumedBun = currentBatchOfBun.getConsumed();
							}

							if (currentStockOfPatty == 0) {
								currentBatchOfPatty = pattyInventory.getCurrentBatch();
								currentStockOfPatty = currentBatchOfPatty.getStock();
								currentConsumedPatty = currentBatchOfPatty.getConsumed();
							}

							if (currentStockOfCheese == 0) {
								currentBatchOfCheese = cheeseInventory.getCurrentBatch();
								currentStockOfCheese = currentBatchOfCheese.getStock();
								currentConsumedCheese = currentBatchOfCheese.getConsumed();
							}
						}

						price = burgerMade * productPrices[choice - 1];
					}

					if (!productSelected[choice - 1]) {
						currentOrder.enqueue(new Node(productNames[choice - 1], burgerMade, price, orderNum));

						switch (choice) {
							case 1:
								productSelected[0] = true;
								break;
							case 2:
								productSelected[1] = true;
								break;
							case 3:
								productSelected[2] = true;
								break;
							case 4:
								productSelected[3] = true;
						}
					} else {
						Node currentNode = currentOrder.getFront();

						while (currentNode != null) {
							if (currentNode.getProductName().equals(productNames[choice - 1])) {
								int newQuantity = currentNode.getQuantity() + burgerMade;
								double newPrice = newQuantity * productPrices[choice - 1]; 
								currentNode.setQuantity(newQuantity);
								currentNode.setPrice(newPrice);
								break; 
							}

							currentNode = currentNode.getNext(); 
						}
					}

					if (bunInventory.getAllStock() > 0 && pattyInventory.getAllStock() > 0) {
						if (howMany > burgerMade)
							out.print("\n");

						while (true) {
							out.print("Add More? (Y/N): ");
							addMore = input.readLine().trim(); 

							if (addMore.equalsIgnoreCase("Y") || addMore.equalsIgnoreCase("N")) 
								break;

							out.println("\nPlease enter Y or N only.\n");
						}

						if (addMore.equalsIgnoreCase("Y")) 
							continue;
					} else 
						if (burgerMade == howMany) 
							out.println("\nNo more product can be added.\nThere isn't enough ingredients.\nPlease view the inventory.");

					break;
				}

				if (currentOrder.getFront() != null) {
					out.println("\n" + "~".repeat(30));
					out.printf("%18s%n", "RECEIPT");
					out.print("~".repeat(30));

					currentOrder.displayOrder();

					Node currentNode = currentOrder.getFront();

					while (currentNode != null) {
						processedOrders.enqueue(currentNode);
						currentNode = currentNode.getNext();
					}

					out.println("\nOrder transaction recorded.");
					currentOrder.setFront(null); 
					orderNum++; 
				}
			} else if (choice == 2) {
				out.println("\n+----------------------------+\n" +
							  "|       VIEW INVENTORY       |\n" +
							  "+----------------------------+\n" +
							  "| [1] Bun                    |\n" +
							  "| [2] Beef Patty             |\n" +
							  "| [3] Cheese Slice           |\n" +
							  "| [4] Egg                    |\n" +
							  "+----------------------------+");

				InputUserChoice("Sub Menu", "ingredient num"); 

				switch (choice) {

					case 1:
						bunInventory.displayInventory("25", "CURRENT", "BUN");
						break;

					case 2:
						pattyInventory.displayInventory("26", "CURRENT", "PATTY");
						break;

					case 3:
						cheeseInventory.displayInventory("26", "CURRENT", "CHEESE");
						break; 

					case 4:
						eggInventory.displayInventory("25", "CURRENT", "EGG");
				}
			} else if (choice == 3) {
				if (bunInventory.getAllStock() == 30 && pattyInventory.getAllStock() == 30 && cheeseInventory.getAllStock() == 60 && eggInventory.getAllStock() == 20) {
					out.println("\nAll ingredients are fully stocked.");
					continue; 
				}

				out.println("\n+----------------------------+\n" +
							  "|     REPLENISH INVENTORY    |\n" +
							  "+----------------------------+\n" +
						      "| [1] Bun                    |\n" +
							  "| [2] Beef Patty             |\n" +
							  "| [3] Cheese Slice           |\n" +
							  "| [4] Egg                    |\n" +
							  "+----------------------------+");

				InputUserChoice("Sub Menu", "ingredient num");

				switch (choice) {

					case 1:
						if (bunInventory.getAllStock() == 30) {
							out.println("\nBuns full of stock.");
							break; 
						}

						InputHowMany();

						int newStockBun = 30 - bunInventory.getAllStock();

						if (howMany <= newStockBun) 
							newStockBun = howMany;
						else 
							out.println("\nOnly " + newStockBun + " bun/s were replenished.\nMaximum capacity is 30 pieces.");

						bunInventory.enqueue(new Node("Bun", newStockBun, 30, 0, bunBatchNum));
						bunBatchNum++; 
						bunInventory.displayInventory("25", "UPDATED", "BUN");
						break; 

					case 2:
						if (pattyInventory.getAllStock() == 30) {
							out.println("\nBeef patty full of stock.");
							break; 
						}

						InputHowMany();

						int newStockPatty = 30 - pattyInventory.getAllStock(); 

						if (howMany <= newStockPatty) 
							newStockPatty = howMany;
						else 
							out.println("\nOnly " + newStockPatty + " patty/s were replenished.\nMaximum capacity is 30 pieces.");

						pattyInventory.enqueue(new Node("Beef Patty", newStockPatty, 30, 0, pattyBatchNum));
						pattyBatchNum++; 
						pattyInventory.displayInventory("26", "UPDATED", "PATTY");
						break; 

					case 3:
						if (cheeseInventory.getAllStock() == 60) {
							out.println("\nCheese slice full of stock.");
							break; 
						}

						InputHowMany();

						int newStockCheese = 60 - cheeseInventory.getAllStock();
																					
						if (howMany <= newStockCheese) 
							newStockCheese = howMany;
						else
							out.println("\nOnly " + newStockCheese + " cheese/s were replenished.\nMaximum capacity is 60 pieces.");

						cheeseInventory.enqueue(new Node("Cheese Slice", newStockCheese, 60, 0, cheeseBatchNum));
						cheeseBatchNum++; 
						cheeseInventory.displayInventory("26", "UPDATED", "CHEESE"); 
						break; 

					case 4:
						if (eggInventory.getAllStock() == 20) {
							out.println("\nEgg full of stock.");
							break; 
						}

						InputHowMany();

						int newStockEgg = 20 - eggInventory.getAllStock();
						
						if (howMany <= newStockEgg) 
							newStockEgg = howMany; 
						else 
							out.println("\nOnly " + newStockEgg + " egg/s were replenished.\nMaximum capacity is 20 pieces.");

						eggInventory.enqueue(new Node("Egg", newStockEgg, 20, 0, eggBatchNum));
						eggBatchNum++; 
						eggInventory.displayInventory("25", "UPDATED", "EGG"); 
				}
			} else if (choice == 4) {
				if (processedOrders.getFront() == null) {
					out.println("\nNo orders have been processed.");
					continue; 
				}

				out.print("\n+----------------------------+\n" +
							"|      PROCESSED ORDERS      |\n" +
							"+----------------------------+\n");

				processedOrders.displayOrder(); 
			} else {
				out.println("\nGoodbye!\n");
				System.exit(0); 
			}
		}
	}

	private static void InputUserChoice(String menuName, String info) throws IOException {
		while (true) {
			out.print("\nEnter " + info + ": ");
		
			try {
				choice = Integer.parseInt(input.readLine().trim());
	
				if (menuName.equals("Main Menu") && (choice > 0 && choice < 6) ||
					menuName.equals("Sub Menu") && (choice > 0 && choice < 5))
					break;

				out.println("\nInvalid choice! Please try again.");
			} catch (NumberFormatException e) {
				out.println("\nPlease enter a number only!");
			}
		}
	}

	public static void InputHowMany() throws IOException {
		while (true) {
			out.print("How Many? "); 

			try {
				howMany = Integer.parseInt(input.readLine().trim()); 
	
				if (howMany > 0)
					break;

				out.println("\nInvalid amount! Please try again.\n");
			} catch (NumberFormatException e) {
				out.println("\nPlease enter a number only.\n");
			}
		}
	}
}