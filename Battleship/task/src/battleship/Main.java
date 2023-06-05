package battleship;

public class Main {
    public static void main(String[] args) {

        System.out.println("Player 1, place your ships on the game field");

        Field field1 = new Field();
        field1.print();
        for (Ship ship : Ship.values()) {
            field1.myMethod(ship);
            field1.print();
        }

        System.out.println("Press Enter and pass the move to another player");
        Field.sc.nextLine();
        System.out.println("Player 2, place your ships on the game field");
        Field field2 = new Field();
        field2.print();
        for (Ship ship : Ship.values()) {
            field2.myMethod(ship);
            field2.print();
        }

        Field.shooting(field1, field2);
    }
}