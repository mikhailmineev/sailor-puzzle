package ru.mmineev.osp;

public class Utils {
    private Utils() {
	// forbidden
    }

    public static final int calculateDistance(Field current) {
	int distance = 0;
	byte[][] matrix = current.getFieldMatrix();
	for (int x = 0; x < matrix.length; x++) {
	    for (int y = 0; y < matrix[x].length; y++) {
		byte currentValue = matrix[x][y];
		if (currentValue == Field.WALL) {
		    continue;
		}
		if (currentValue == Field.EMPTY) {
		    continue;
		}
		if (currentValue == Field.WHITE) {
		    distance += 9 - x - y;
		    continue;
		}
		if (currentValue == Field.BLACK) {
		    distance += x + y + 1;
		    continue;
		}
	    }
	}
	return distance;
    }
}
