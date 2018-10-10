package ru.mmineev.osp;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import ru.mmineev.osp.impl.ConsoleRenderer;

public class OspApp {
	private static final byte[][] INITIAL = { { Field.WHITE, Field.WHITE, Field.WHITE, Field.WALL, Field.WALL },
			{ Field.WHITE, Field.WHITE, Field.WHITE, Field.WALL, Field.WALL },
			{ Field.WHITE, Field.WHITE, Field.EMPTY, Field.BLACK, Field.BLACK },
			{ Field.WALL, Field.WALL, Field.BLACK, Field.BLACK, Field.BLACK },
			{ Field.WALL, Field.WALL, Field.BLACK, Field.BLACK, Field.BLACK } };

	private final Renderer renderer = new ConsoleRenderer();
	private final Solver solver = new Solver();
	private final Field field = new Field(INITIAL);
	private final Action initial = new Action(field);
	private Action best = initial;

	public OspApp() {
		LinkedList<Action> rangedMoves = new LinkedList<>();
		renderer.render(field);
		rangedMoves.addAll(solver.possibleMoves(initial));
		rangedMoves.sort(Comparator.comparing(Action::totalCost));

		for (int i = 0; i < 1000; i++) {
			Action nextBestMove = rangedMoves.pollFirst();
			rangedMoves.stream().limit(4).forEach(System.out::println);
			System.out.println("Taking " + nextBestMove);
			if (best.getDistance() > nextBestMove.getDistance()) {
				best = nextBestMove;
			}
			renderer.render(nextBestMove.getRightAfterMove());
			List<Action> possibleMoves = solver.possibleMoves(nextBestMove);

			rangedMoves.addAll(possibleMoves);
			rangedMoves.sort(Comparator.comparing(Action::totalCost));
			while (rangedMoves.size() > 1000) {
				rangedMoves.removeLast();
			}
			System.out.println("");
		}

		System.out.println("Best " + best);
		Action renderAction = best;
		while (true) {
			renderer.render(renderAction.getRightAfterMove());
			System.out.println("");
			renderAction = renderAction.getPreviousAction();
			if (renderAction == null) {
				return;
			}
		}
	}

	public static void main(String... args) {
		new OspApp();
	}
}
