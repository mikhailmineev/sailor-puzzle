package ru.mmineev.osp;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import ru.mmineev.osp.impl.ConsoleRenderer;

public class OspApp {
    public static final byte[][] TARGET = { 
	    { Field.BLACK, Field.BLACK, Field.BLACK, Field.WALL, Field.WALL },
	    { Field.BLACK, Field.BLACK, Field.BLACK, Field.WALL, Field.WALL },
	    { Field.BLACK, Field.BLACK, Field.EMPTY, Field.WHITE, Field.WHITE },
	    { Field.WALL, Field.WALL, Field.WHITE, Field.WHITE, Field.WHITE },
	    { Field.WALL, Field.WALL, Field.WHITE, Field.WHITE, Field.WHITE } };
    private static final byte[][] INITIAL = { 
	    { Field.WHITE, Field.WHITE, Field.WHITE, Field.WALL, Field.WALL },
	    { Field.WHITE, Field.WHITE, Field.WHITE, Field.WALL, Field.WALL },
	    { Field.WHITE, Field.WHITE, Field.EMPTY, Field.BLACK, Field.BLACK },
	    { Field.WALL, Field.WALL, Field.BLACK, Field.BLACK, Field.BLACK },
	    { Field.WALL, Field.WALL, Field.BLACK, Field.BLACK, Field.BLACK } };

    private Renderer renderer = new ConsoleRenderer();
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
