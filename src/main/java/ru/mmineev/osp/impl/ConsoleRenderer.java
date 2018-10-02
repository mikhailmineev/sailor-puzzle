package ru.mmineev.osp.impl;

import ru.mmineev.osp.Action;
import ru.mmineev.osp.Field;
import ru.mmineev.osp.Renderer;

public class ConsoleRenderer implements Renderer {

    @Override
    public void render(Action action, int back) {
	for (int x = 0; x < 5; x++) {
	    for (int s = 0; s < back; s++) {
		Field field = getForHistory(action, back - s - 1);
		if (field == null) {
		    continue;
		}
		byte[][] matrix = field.getFieldMatrix();
		for (int y = 0; y < 5; y++) {
		    byte cell = matrix[x][y];
		    char cellView = renderCell(cell);
		    System.out.print(cellView + " ");
		}
		if (x == 2) {
		    System.out.print(" -> ");
		} else {
		    System.out.print("    ");

		}
	    }
	    System.out.println("");
	}
    }

    private Field getForHistory(Action action, int history) {
	for (int i = 0; i < history; i++) {
	    action = action.getPreviousAction();
	    if (action == null) {
		return null;
	    }
	}
	Field field = action.getRightAfterMove();
	return field;
    }

    @Override
    public void render(Field field) {
	byte[][] matrix = field.getFieldMatrix();
	for (int i = 0; i < matrix.length; i++) {
	    for (int j = 0; j < matrix[i].length; j++) {
		byte cell = matrix[i][j];
		char cellView = renderCell(cell);
		System.out.print(cellView + " ");
	    }
	    System.out.println("");
	}
    }

    private char renderCell(byte cell) {
	switch (cell) {
	case 0:
	    return ' ';
	case 1:
	    return '-';
	case 2:
	    return 'w';
	case 3:
	    return 'b';
	}
	return '\0';
    }

}
