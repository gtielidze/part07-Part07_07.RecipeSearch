
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;


public class RecipeSearch {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> recipesInString = new ArrayList<>();
        ArrayList<Recipe> recipes = new ArrayList<>();
        System.out.println("File to read:");
        String readFile = scanner.nextLine();

        try {
            Scanner scan = new Scanner(Paths.get(readFile));
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                if (!line.equals("")) {
                    recipesInString.add(line);
                }
                if (line.isEmpty() || !scan.hasNextLine()) {
                    String name = recipesInString.get(0);
                    int cookingTime = Integer.parseInt(recipesInString.get(1));
                    ArrayList<String> ingredients = new ArrayList<>();
                    for (int i = 2; i < recipesInString.size(); i++) {
                        ingredients.add(recipesInString.get(i));
                    }
                    recipes.add(new Recipe(name, cookingTime, ingredients));
                    recipesInString.clear();
                }
            }
        } catch (IOException ex) {
            System.out.println("ERROR");
        }

        System.out.println();
        System.out.println("Commands:");
        System.out.println("list - lists the recipes");
        System.out.println("stop - stops the program");
        System.out.println("find name - searches recipes by name");
        System.out.println("find cooking time - searches recipes by cooking time");
        System.out.println("find ingredient - searches recipes by ingredient");
        while (true) {
            System.out.println();
            System.out.println("Enter command:");
            String command = scanner.nextLine();
            if (command.equals("stop")) {
                break;
            }
            if (command.equals("list")) {
                System.out.println("Recipes:");
                for (int i = 0; i < recipes.size(); i++) {
                    System.out.println(recipes.get(i).getName() + ", cooking time: " + recipes.get(i).getCookingTime());
                }
            }

            if (command.equals("find name")) {
                System.out.println("Searched word:");
                String name = scanner.nextLine();
                for (int i = 0; i < recipes.size(); i++) {
                    if(recipes.get(i).getName().contains(name)) {
                        System.out.println(recipes.get(i).getName() + ", cooking time: " + recipes.get(i).getCookingTime());
                    }
                }
            }
            if (command.equals("find cooking time")) {
                System.out.println("Max cooking time:");
                int cookingTime = Integer.parseInt(scanner.nextLine());
                System.out.println();
                System.out.println("Recipes: ");
                for (int i = 0; i < recipes.size(); i++) {
                    if (cookingTime >= recipes.get(i).getCookingTime()) {
                        System.out.println(recipes.get(i).getName() + ", cooking time: " + recipes.get(i).getCookingTime());

                    }
                }
            }
            if (command.equals("find ingredient")) {
                System.out.println("Ingredient:");
                String ingredient = scanner.nextLine();
                System.out.println();
                System.out.println("Recipes: ");
                for (int i = 0; i < recipes.size(); i++) {
                    if(recipes.get(i).getIngredients().contains(ingredient)) {
                        System.out.println(recipes.get(i).getName() + ", cooking time: " + recipes.get(i).getCookingTime());
                    }
                }
            }
        }
    }
}
