package ru.mmineev.osp;

public class Action {
    private final int cost;
    private final int distance;

    private final Point from;
    private final Point to;
    private final Field rightAfterMove;
    private final Action previousAction;

    public Action(Point from, Point to, Action previousAction) {
	this.from = from;
	this.to = to;
	this.cost = previousAction.cost + 1;
	this.rightAfterMove = previousAction.getRightAfterMove().transform(from, to);
	this.previousAction = previousAction;
	distance = Utils.calculateDistance(rightAfterMove);
    }

    public Action(Field rightAfterMove) {
	this.from = null;
	this.to = null;
	this.cost = 0;
	this.rightAfterMove = rightAfterMove;
	previousAction = null;
	distance = Utils.calculateDistance(rightAfterMove);
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
