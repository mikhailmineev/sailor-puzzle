package ru.mmineev.osp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solver {

    public List<Action> possibleMoves(Action previousAction) {
	List<Action> actions = new ArrayList<>();
	byte[][] matrix = previousAction.getRightAfterMove().getFieldMatrix();
	for (int i = 0; i < matrix.length; i++) {
	    for (int j = 0; j < matrix[i].length; j++) {
		Point from = new Point(i, j);
		List<Point> moves = possibleMoveFor(previousAction.getRightAfterMove(), i, j);
		moves.forEach((move) -> {
		    Action action = new Action(from, move, previousAction);
		    if (action.getFrom().equals(previousAction.getTo())
			    && previousAction.getFrom().equals(action.getTo())) {
			return;
		    }
		    actions.add(action);
		});

	    }
	}
	return actions;
    }

    private List<Point> possibleMoveFor(Field field, int x, int y) {
	byte[][] matrix = field.getFieldMatrix();
	if (matrix[x][y] == Field.WALL) {
	    return Collections.emptyList();
	}
	if (matrix[x][y] == Field.EMPTY) {
	    return Collections.emptyList();
	}
	List<Point> points = new ArrayList<>();
	// next top
	addPointIfCellEmpty(x, y - 1, matrix, points);
	if (hasPiece(x, y - 1, matrix)) {
	    addPointIfCellEmpty(x, y - 2, matrix, points);
	}
	// next bottom
	addPointIfCellEmpty(x, y + 1, matrix, points);
	if (hasPiece(x, y + 1, matrix)) {
	    addPointIfCellEmpty(x, y + 2, matrix, points);
	}
	// next left
	addPointIfCellEmpty(x - 1, y, matrix, points);
	if (hasPiece(x - 1, y, matrix)) {
	    addPointIfCellEmpty(x - 2, y, matrix, points);
	}
	// next right
	addPointIfCellEmpty(x + 1, y, matrix, points);
	if (hasPiece(x + 1, y, matrix)) {
	    addPointIfCellEmpty(x + 2, y, matrix, points);
	}
	return points;
    }

    private boolean hasPiece(int x, int y, byte[][] matrix) {
	if (x < 0 || y < 0 || x > 4 || y > 4) {
	    return false;
	}
	byte cell = matrix[x][y];
	if (cell == Field.WALL) {
	    return false;
	}
	if (cell != Field.EMPTY) {
	    return true;
	}
	return false;
    }

    private void addPointIfCellEmpty(int x, int y, byte[][] matrix, List<Point> points) {
	if (x < 0 || y < 0 || x > 4 || y > 4) {
	    return;
	}
	byte cell = matrix[x][y];
	if (cell == Field.WALL) {
	    return;
	}
	if (cell == Field.EMPTY) {
	    points.add(new Point(x, y));
	}
    }
}
