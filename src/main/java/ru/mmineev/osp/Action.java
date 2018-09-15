package ru.mmineev.osp;

public class Action {
    private final int cost;

    /**
     * –ассто€ние до цели - количество фишек на нужных пол€х.
     */
    private final int distance;

    private final Point from;
    private final Point to;
    private final Field rightAfterMove;
    private final Action previousAction;
    private final Field target;

    public Action(Point from, Point to, Action previousAction) {
	this.from = from;
	this.to = to;
	this.cost = previousAction.cost + 1;
	this.rightAfterMove = previousAction.getRightAfterMove().transform(from, to);
	this.previousAction = previousAction;
	this.target = previousAction.target;
	distance = calculateDistance(rightAfterMove);
    }

    public Action(Field rightAfterMove, Field target) {
	this.from = null;
	this.to = null;
	this.cost = 0;
	this.rightAfterMove = rightAfterMove;
	previousAction = null;
	this.target = target;
	distance = calculateDistance(rightAfterMove);
    }

    public int getDistance() {
	return distance;
    }

    public Point getFrom() {
	return from;
    }

    public Point getTo() {
	return to;
    }

    public int getCost() {
	return cost;
    }

    public Field getRightAfterMove() {
	return rightAfterMove;
    }

    public int totalCost() {
	return cost + distance;
    }

    public Action getPreviousAction() {
	return previousAction;
    }

    private int calculateDistance(Field current) {
	int distance = 0;
	byte[][] matrix = current.getFieldMatrix();
	byte[][] targetMatrix = target.getFieldMatrix();
	for (int x = 0; x < matrix.length; x++) {
	    for (int y = 0; y < matrix[x].length; y++) {
		byte targetValue = targetMatrix[x][y];
		byte currentValue = matrix[x][y];
		if (targetValue == 0) {
		    continue;
		}
		if (targetValue == 1) {
		    distance++;
		    continue;
		}
		if (currentValue != targetValue) {
		    distance++;
		    // distance += Math.sqrt((x - 2) * (x - 2) + (y - 2) * (y - 2));
		    continue;
		}
	    }
	}
	return distance;
    }

    @Override
    public String toString() {
	return "Action [h=" + distance + ", g=" + cost + ", from=" + from + ", to=" + to + "]";
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((rightAfterMove == null) ? 0 : rightAfterMove.hashCode());
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
	Action other = (Action) obj;
	if (rightAfterMove == null) {
	    if (other.rightAfterMove != null)
		return false;
	} else if (!rightAfterMove.equals(other.rightAfterMove))
	    return false;
	return true;
    }

}
