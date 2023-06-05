package battleship;

public class Warship {
    int lengthOfShips;
    int length;
    int[] indexes;
    int gorizont;
    int vertical;
    int index;


    boolean checkLength(Warship[] warships) {
        for (int i = 0; i < warships.length; i++) {
            lengthOfShips += warships[i].length;
        }
        if (lengthOfShips == 0) {
            return true;
        }
        return false;
    }

    void checkIndexes(int i1, int i2, Warship[] warships) {
        if (i1 == gorizont) {
            for (int i = 0; i < indexes.length; i++) {
                if (indexes[i] == i2) {
                    length--;
                    lengthOfShips--;
                    if (checkLength(warships)) {
                        System.out.println("You sank the last ship. You won. Congratulations!");
                        Field.isValid = false;
                        return;
                    }
                    if (length == 0) {
                        System.out.println("You sank a ship! Specify a new target:");
                        System.out.println(lengthOfShips + " " + length);
                    } else if (length > 0) {
                        System.out.println("You hit a ship!");
                    }
                    break;
                }
            }
        } else if (i2 == vertical) {
            for (int i = 0; i < indexes.length; i++) {
                if (indexes[i] == i1) {
                    length--;
                    lengthOfShips--;
                    if (checkLength(warships)) {
                        System.out.println("You sank the last ship. You won. Congratulations!");
                        Field.isValid = false;
                        return;
                    }
                    if (length == 0) {
                        System.out.println("You sank a ship! Specify a new target:");
                    } else if (length > 0) {
                        System.out.println("You hit a ship! Try again:");
                    }
                    break;
                }
            }
        }
    }

    public void setIndexes(Ship ship, int... indexes) {
        if (indexes[0] == indexes[2]) {
            this.indexes = new int[ship.getLength()];
            gorizont = indexes[0];
            for (int i = indexes[1], j = 0; i <= indexes[3]; i++, j++) {
                this.indexes[j] = i;
            }
        } else if (indexes[1] == indexes[3]) {
            this.indexes = new int[ship.getLength()];
            vertical = indexes[1];
            for (int i = indexes[0], j = 0; i <= indexes[2]; i++, j++) {
                this.indexes[j] = i;
            }
        }
    }

    public Warship(int length) {
        this.length = length;
    }
}
