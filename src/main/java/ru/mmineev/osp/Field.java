package ru.mmineev.osp;

import java.util.Arrays;

public class Field {
    public static final int WALL = 0;
    public static final int EMPTY = 1;
    public static final int WHITE = 2;
    public static final int BLACK = 3;

    private byte[][] field;

    public Field(byte[][] field) {
	this.field = field;
    }

    public byte[][] getFieldMatrix() {
	return field;
    }

    public Field transform(Point from, Point move) {
	byte[][] copy = copy();
	byte fromCellValue = copy[from.getX()][from.getY()];
	if (fromCellValue == WALL || fromCellValue == EMPTY) {
	    throw new IllegalArgumentException("Cant take item from empty cell");
	}
	if (copy[move.getX()][move.getY()] == WHITE || copy[move.getX()][move.getY()] == BLACK) {
	    throw new IllegalArgumentException("Target cell is not empty");
	}
	copy[move.getX()][move.getY()] = fromCellValue;
	copy[from.getX()][from.getY()] = EMPTY;
	return new Field(copy);
    }

    private byte[][] copy() {
	byte[][] field = new byte[5][5];
	for (int i = 0; i < this.field.length; i++) {
	    for (int j = 0; j < this.field[i].length; j++) {
		field[i][j] = this.field[i][j];
	    }
	}
	return field;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + Arrays.deepHashCode(field);
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	Field other = (Field) obj;
	if (!Arrays.deepEquals(field, other.field))
	    return false;
	return true;
    }
}
