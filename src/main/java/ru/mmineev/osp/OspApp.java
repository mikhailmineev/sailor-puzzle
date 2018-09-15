package ru.mmineev.osp;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class OspApp {
    public static final byte[][] TARGET = { { 3, 3, 3, 0, 0 }, { 3, 3, 3, 0, 0 }, { 3, 3, 1, 2, 2 }, { 0, 0, 2, 2, 2 },
	    { 0, 0, 2, 2, 2 } };
    /*
     * 0 - wall 1 - empty 2 - white 3 - black
     */
    private static final byte[][] INITIAL = { { 2, 2, 2, 0, 0 }, { 2, 2, 2, 0, 0 }, { 2, 2, 1, 3, 3 },
	    { 0, 0, 3, 3, 3 }, { 0, 0, 3, 3, 3 } };

    private Renderer renderer = new Renderer();
    private Solver solver = new Solver();
    private Field field = new Field(INITIAL);
    private Field targetField = new Field(TARGET);
    private Action initial = new Action(field, targetField);
    private Action best = initial;

    public OspApp() {
	LinkedList<Action> rangedMoves = new LinkedList<>();
	renderer.render(field);
	rangedMoves.addAll(solver.possibleMoves(initial));
	rangedMoves.stream().limit(4).forEach(System.out::println);
	rangedMoves.sort(Comparator.comparing(Action::totalCost));

	for (int i = 0; i < 1000; i++) {
	    Action nextBestMove = rangedMoves.pollFirst();
	    System.out.println("Taking " + nextBestMove);
	    if (best.getDistance() > nextBestMove.getDistance()) {
		best = nextBestMove;
	    }
	    renderer.render(nextBestMove);
	    List<Action> possibleMoves = solver.possibleMoves(nextBestMove);

	    //Убираем одинаковые ситуации
	    //rangedMoves.forEach((e) -> possibleMoves.removeIf(e2 -> e.equals(e2)));

	    rangedMoves.addAll(possibleMoves);
	    rangedMoves.stream().limit(4).forEach(System.out::println);
	    rangedMoves.sort(Comparator.comparing(Action::totalCost));
	}

	System.out.println("Best " + best);
	renderer.render(best);
    }

    public static void main(String... args) {
	new OspApp();
    }
}
