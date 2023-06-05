package battleship;

import java.util.Scanner;

public class Field {
    static Scanner sc = new Scanner(System.in);
    String[][] tabl;
    static String[][] fog;
    static int i1;
    static int i2;
    static int i3;
    static int i4;
    static boolean isValid;
    int index = 0;


    Warship aircraft = new Warship(5);
    Warship battleship = new Warship(4);
    Warship submarine = new Warship(3);
    Warship cruiser = new Warship(3);
    Warship destroyer = new Warship(2);
    Warship[] warships = {
            aircraft,
            battleship,
            submarine,
            cruiser,
            destroyer
    };

    static void printFog() {
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                System.out.print(fog[i][j] + " ");
            }
            System.out.println();
        }
    }

    void print() {
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                System.out.print(tabl[i][j] + " ");
            }
            System.out.println();
        }
    }

    void myMethod(Ship ship) {
        isValid = true;
        i1 = 0;
        i2 = 0;
        i3 = 0;
        i4 = 0;
        System.out.printf("Enter the coordinates of the %s (%d cells):\n", ship.getName(), ship.getLength());
        do {
            String line = sc.nextLine();
            line = line.replace(" ", "");
            if (searchIndexes(line)) {
                System.out.println("Error! Wrong ship location! Try again:");
                continue;
            }
            if (checkPosition(ship)) {
                continue;
            }
            warships[index].setIndexes(ship, i1, i2, i3, i4);
            index++;
            takingPosition();
            isValid = false;
        } while (isValid);


    }

    static void shooting(Field... fields) {
        isValid = true;
        int index = 1;
        int player = 1;
        while (isValid) {
            System.out.println("Press Enter and pass the move to another player");
            sc.nextLine();
            printFog();
            System.out.println("---------------------");
            fields[(index + 1) % fields.length].print();
            System.out.println("Player " + player + ", it's your turn:");
            String line = sc.nextLine();
            if (searchIndex(line, fields[index])) {
                System.out.println("Error! You entered the wrong coordinates! Try again:");
                continue;
            }

            if (fields[index].tabl[i1][i2] == "O") {
                fields[index].tabl[i1][i2] = "X";
                fields[index].shotPlace(i1, i2);
            } else if (fields[index].tabl[i1][i2] == "~") {
                fields[index].tabl[i1][i2] = "M";
                System.out.println("You missed!");

            } else if (fields[index].tabl[i1][i2] == "X") {
                System.out.println("You hit a ship!");
            }
            index = (index + 1) % fields.length;
            ;
            player = (player == 1) ? 2 : 1;
        }
    }

    void shotPlace(int i1, int i2) {
        for (Warship warship : warships) {
            warship.checkIndexes(i1, i2, warships);
        }
    }

    void takingPosition() {
        if (i1 == i3) {
            for (int i = i2; i <= i4; i++) {
                tabl[i1][i] = "O";
            }
        } else if (i2 == i4) {
            for (int i = i1; i <= i3; i++) {
                tabl[i][i2] = "O";
            }
        }
    }

    boolean checkPosition(Ship ship) {
        if (i1 == i3) {
            if (i4 - i2 + 1 != ship.getLength()) {
                System.out.printf("Error! Wrong length of the %s! Try again:\n", ship.getName());
                return true;
            }
            if (tabl[i1][i2 - 1] == "O") {
                System.out.println("Error! You placed it too close to another one. Try again:");
                return true;
            }
            for (int i = i2; i <= i4; i++) {
                if (tabl[i1 - 1][i] == "O" || tabl[i1][i] == "O") {
                    System.out.println("Error! You placed it too close to another one. Try again:");
                    return true;
                }
            }
            if (i4 + 1 < 11) {
                if (tabl[i1][i4 + 1] == "O") {
                    System.out.println("Error! You placed it too close to another one. Try again:");
                    return true;
                }
            }
            if (i1 + 1 < 11) {
                for (int i = i2; i <= i4; i++) {
                    if (tabl[i1 + 1][i] == "O") {
                        System.out.println("Error! You placed it too close to another one. Try again:");
                        return true;
                    }
                }
            }
        } else if (i2 == i4) {
            if (i3 - i1 + 1 != ship.getLength()) {
                System.out.printf("Error! Wrong length of the %s! Try again:", ship.getName());
                return true;
            }
            if (tabl[i1 + 1][i2] == "O") {
                System.out.println("Error! You placed it too close to another one. Try again:");
                return true;
            }
            for (int i = i1; i <= i3; i++) {
                if (tabl[i][i2 - 1] == "O" || tabl[i][i2] == "O") {
                    System.out.println("Error! You placed it too close to another one. Try again:");
                    return true;
                }
            }
            if (i2 + 1 < 11) {
                for (int i = i1; i <= i3; i++) {
                    if (tabl[i][i2 + 1] == "O") {
                        System.out.println("Error! You placed it too close to another one. Try again:");
                        return true;
                    }
                }
            }
            if (i3 + 1 < 11) {
                if (tabl[i3 + 1][i2] == "O") {
                    System.out.println("Error! You placed it too close to another one. Try again:");
                    return true;
                }
            }

        }
        return false;
    }

    static boolean searchIndex(String line, Field field) {
        i1 = 0;
        i2 = 0;
        if (line.length() == 2) {
            for (int i = 1; i < 11; i++) {
                if (field.tabl[i][0].equals(String.valueOf(line.charAt(0)))) {
                    i1 = i;
                    break;
                }
            }
            for (int i = 1; i < 11; i++) {
                if (field.tabl[0][i].equals(String.valueOf(line.charAt(1)))) {
                    i2 = i;
                    break;
                }
            }
            if (i1 != 0 && i2 != 0) return false;
        } else if (line.length() == 3) {
            for (int i = 1; i < 11; i++) {
                if (field.tabl[i][0].equals(String.valueOf(line.charAt(0)))) {
                    i1 = i;
                    break;
                }
            }
            if (line.charAt(1) == '1' && line.charAt(2) == '0') {
                i2 = 10;
            }
            if (i1 != 0 && i2 != 0) return false;
        }
        return true;
    }

    boolean searchIndexes(String line) {
        int t = 0;
        if (line.length() == 4) {
            for (int i = 1; i < 11; i++) {
                if (tabl[i][0].equals(String.valueOf(line.charAt(0)))) {
                    i1 = i;
                    break;
                }
            }
            for (int i = 1; i < 11; i++) {
                if (tabl[0][i].equals(String.valueOf(line.charAt(1)))) {
                    i2 = i;
                    break;
                }
            }
            for (int i = 1; i < 11; i++) {
                if (tabl[i][0].equals(String.valueOf(line.charAt(2)))) {
                    i3 = i;
                    break;
                }
            }
            for (int i = 1; i < 11; i++) {
                if (tabl[0][i].equals(String.valueOf(line.charAt(3)))) {
                    i4 = i;
                    break;
                }
            }
        } else if (line.length() == 5 && line.charAt(1) == '1' && line.charAt(2) == '0') {
            i2 = 10;
            for (int i = 1; i < 11; i++) {
                if (tabl[i][0].equals(String.valueOf(line.charAt(0)))) {
                    i1 = i;
                    break;
                }
            }
            for (int i = 1; i < 11; i++) {
                if (tabl[i][0].equals(String.valueOf(line.charAt(3)))) {
                    i3 = i;
                    break;
                }
            }
            for (int i = 1; i < 11; i++) {
                if (tabl[0][i].equals(String.valueOf(line.charAt(4)))) {
                    i4 = i;
                    break;
                }
            }
        } else if (line.length() == 5 && line.charAt(3) == '1' && line.charAt(4) == '0') {
            i4 = 10;
            for (int i = 1; i < 11; i++) {
                if (tabl[i][0].equals(String.valueOf(line.charAt(0)))) {
                    i1 = i;
                    break;
                }
            }
            for (int i = 1; i < 11; i++) {
                if (tabl[0][i].equals(String.valueOf(line.charAt(1)))) {
                    i2 = i;
                    break;
                }
            }
            for (int i = 1; i < 11; i++) {
                if (tabl[i][0].equals(String.valueOf(line.charAt(2)))) {
                    i3 = i;
                    break;
                }
            }
        } else if (line.length() == 6 && line.charAt(1) == '1' && line.charAt(2) == '0' &&
                line.charAt(4) == '1' && line.charAt(5) == '0') {
            i2 = 10;
            i4 = 10;
            for (int i = 1; i < 11; i++) {
                if (tabl[i][0].equals(String.valueOf(line.charAt(0)))) {
                    i1 = i;
                    break;
                }
            }
            for (int i = 1; i < 11; i++) {
                if (tabl[i][0].equals(String.valueOf(line.charAt(3)))) {
                    i3 = i;
                    break;
                }
            }
        }

        if (i1 == i3) {
            if (i2 > i4) {
                t = i2;
                i2 = i4;
                i4 = t;
            }
        } else if (i2 == i4) {
            if (i1 > i3) {
                t = i1;
                i1 = i3;
                i3 = t;
            }
        }
        if (i1 == 0 || i2 == 0 || i3 == 0 || i4 == 0) {
            return true;
        }
        if (i1 != i3 && i2 != i4) {
            return true;
        }
        return false;
    }

    Field() {
        tabl = new String[11][11];
        char ch = 'A';
        tabl[0][0] = " ";
        for (int i = 1; i < 11; i++) {
            tabl[0][i] = Integer.toString(i);
        }
        for (int i = 1; i < 11; i++) {
            tabl[i][0] = Character.toString(ch);
            ch++;
        }
        for (int i = 1; i < 11; i++) {
            for (int j = 1; j < 11; j++) {
                tabl[i][j] = "~";
            }
        }
        fog = new String[11][11];
        ch = 'A';
        fog[0][0] = " ";
        for (int i = 1; i < 11; i++) {
            fog[0][i] = Integer.toString(i);
        }
        for (int i = 1; i < 11; i++) {
            fog[i][0] = Character.toString(ch);
            ch++;
        }
        for (int i = 1; i < 11; i++) {
            for (int j = 1; j < 11; j++) {
                fog[i][j] = "~";
            }
        }
    }
}
