import java.util.Scanner;
import java.lang.String;
import java.io.*;

public class updatedtest {
    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        String userYN;

        System.out.println("Hello welcome to the Awesome Store! Would you like to enter? Please say Yes or No:\n");

        // Entering the Store
        while (true) {
            userYN = scnr.nextLine();

            if (userYN.equalsIgnoreCase("yes")) {
                System.out.println("Welcome in.\n");
                break;
            }
            else if (userYN.equalsIgnoreCase("no")) {
                System.out.println("I understand, please have a wonderful day!");
                System.exit(0);
                break;
            }
            else {
                System.out.println("Invalid input! Please input Yes or No");
            }

        }

        double totalCost = 0;
        int itemCount = 0;
        String[] maxItemsArray = null; // Declare maxItemsArray outside the loop

        // Prompt user to select cart or basket
        System.out.print("Do you prefer Cart or Basket? You may also choose neither. (Type anything for this option): ");
        String cartBasket = scnr.nextLine();

        if (cartBasket.equalsIgnoreCase("cart")) {
            System.out.println("Because you chose a cart you may add up to 10 items in your basket.\n");
            maxItemsArray = new String[10]; // Initialize maxItemsArray based on cart choice
        } else if (cartBasket.equalsIgnoreCase("basket")) {
            System.out.println("Because you chose a basket you may add up to 5 items in your basket.\n");
            maxItemsArray = new String[5]; // Initialize maxItemsArray based on basket choice
        } else {
            System.out.println("Because you chose neither you may only grab 2 items.\n");
            cartBasket = "neither"; // Set the choice to neither
            maxItemsArray = new String[2]; // Initialize maxItemsArray based on neither choice
        }

        // Loop for shopping
        while (itemCount < maxItemsArray.length) {
            System.out.println();
            System.out.println("Which aisle would you like to shop in?");
            System.out.println("1. Cold Items");
            System.out.println("2. Fresh Produce");
            System.out.println("3. Meat & Seafood");
            System.out.println("4. Breakfast Foods");
            System.out.println("5. Drinks");
            System.out.println("6. Canned Foods");
            System.out.println("0. Finish shopping");
            System.out.print("Enter aisle number to travel to: ");

            try{
                int aisleChoice = scnr.nextInt();
                System.out.println();

                if (aisleChoice == 0 || itemCount == maxItemsArray.length) {
                    break; // Finish shopping if user chooses to exit or max items reached
                } else if (aisleChoice < 1 || aisleChoice > 6) {
                    System.out.println("Invalid choice.");
                    continue; // Continue to next iteration of loop
                }

                // Display available items based on aisle choice
                displayAvailableItems(aisleChoice);


                // Fill the shopping list
                while (itemCount < maxItemsArray.length) {
                    System.out.print("Enter item number to add to the shopping list (Enter 0 to finish aisle): ");
                    int choice = scnr.nextInt();




                    if (choice == 0) {
                        break; // Finish aisle
                    } else if (choice < 1 || choice > 5) {
                        System.out.println("Invalid choice. Please enter a number between 1 and 5.");
                        continue; // Continue to next iteration of loop
                    }

                    //Add quantity option
                    char quantityInput;
                    //Repeat until given valid numeral input
                    do {
                        System.out.println("Enter quantity: ");
                        quantityInput = scnr.next().charAt(0);

                    } while (Character.isAlphabetic(quantityInput));

                    //Convert to integer
                    int quantity = (int)quantityInput-'0';

                    //If quantity exceeds cart/basket size, fill cart
                    if (quantity + itemCount > maxItemsArray.length) {
                        quantity = maxItemsArray.length - itemCount;
                        int excessAmount = maxItemsArray.length - itemCount;
                        System.out.println("Chosen quantity exceeds available space. Added " + excessAmount + " instead.");
                    }


                    int startIndex = (aisleChoice - 1) * 5;

                    //add item N amount of times
                    for (int i = 0; i < quantity; i++) {
                        maxItemsArray[itemCount] = availableItems[startIndex + choice - 1];
                        totalCost += itemCosts[startIndex + choice - 1];
                        itemCount++;
                    }

                    System.out.println("Added " + quantity + " " + availableItems[startIndex+choice-1] + " to list.");

                    //Additional code to improve visibility
                    System.out.print("Added " + availableItems[startIndex + choice - 1] + " to cart.");
                    int itemsLeft = maxItemsArray.length - itemCount;

                    if (cartBasket.equalsIgnoreCase("neither"))
                        cartBasket = "hands";

                    System.out.println(" " + itemsLeft + " space left in " + cartBasket + ".");

                }
            }
            catch (Exception e) {
                System.out.println("Invalid Input. Please input an integer.");
                scnr.nextLine();
            }
        }

        // Display the final shopping list
        System.out.println("\nYour shopping list:");
        for (int i = 0; i < itemCount; i++) {
            System.out.println((i + 1) + ". " + maxItemsArray[i]);

        }
        System.out.printf("Subtotal: $%.2f\n", totalCost); //this will print out the price before taxes





        //ENTER TAX CALCULATIONS HERE
        double taxTotal = totalCost * 0.0725;
        System.out.printf("Tax : $%.2f\n", taxTotal);

        double finalCost = totalCost + taxTotal;
        System.out.printf("Total : $%.2f\n", finalCost);




        System.out.println("Thank you for shopping at the Awesome Store please come again!");
        scnr.close();
    }

    static void displayAvailableItems(int aisleChoice) {
        System.out.println("Available items:");
        int startIndex = (aisleChoice - 1) * 5;
        int endIndex = startIndex + 5;
        for (int i = startIndex; i < endIndex; i++) {
            System.out.println((i - startIndex + 1) + ". " + availableItems[i] + " - $" + itemCosts[i]);
        }
    }

    static String[] availableItems = {
            // Cold Items
            "Milk", "Eggs", "Cheese", "Butter", "Ice Cream",
            // Fresh Produce
            "Apples", "Tomato", "Lettuce", "Orange", "Carrot",
            // Meat & Seafood
            "Steak", "Chicken Breast", "Pork Chop", "Salmon", "Shrimp",
            // Breakfast Foods
            "Cereal", "Oatmeal", "Yogurt", "Granola", "Pancake Mix",
            // Drinks
            "Water", "Coffee", "Sparkling Water", "Tea", "Soda",
            // Canned Foods
            "Canned Soup", "Canned Chili", "Canned Corn", "Canned Peaches", "Canned Beans"
    };

    static double[] itemCosts = {
            // Cold Items
            2.99, 4.79, 2.49, 6.49, 4.99,
            // Fresh Produce
            1.35, 1.25, 1.79, 1.29, 0.99,
            // Meat & Seafood
            13.59, 5.29, 6.79, 9.99, 13.98,
            // Breakfast Foods
            3.49, 5.99, 0.79, 2.19, 4.69,
            // Drinks
            3.99, 4.45, 4.49, 9.49, 9.99,
            // Canned Foods
            3.49, 4.99, 2.19, 1.89, 0.99
    };
}




