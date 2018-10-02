package ru.mmineev.osp;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

class UtilsTest {
    private static final byte[][] INITIAL = { 
	    { Field.WHITE, Field.WHITE, Field.WHITE, Field.WALL, Field.WALL },
	    { Field.WHITE, Field.WHITE, Field.WHITE, Field.WALL, Field.WALL },
	    { Field.WHITE, Field.WHITE, Field.EMPTY, Field.BLACK, Field.BLACK },
	    { Field.WALL, Field.WALL, Field.BLACK, Field.BLACK, Field.BLACK },
	    { Field.WALL, Field.WALL, Field.BLACK, Field.BLACK, Field.BLACK } };
    private final Field field = new Field(INITIAL);

    private static final byte[][] INITIAL2 = { 
	    { Field.EMPTY, Field.EMPTY, Field.EMPTY, Field.WALL, Field.WALL },
	    { Field.EMPTY, Field.EMPTY, Field.EMPTY, Field.WALL, Field.WALL },
	    { Field.EMPTY, Field.EMPTY, Field.EMPTY, Field.BLACK, Field.BLACK },
	    { Field.WALL, Field.WALL, Field.BLACK, Field.BLACK, Field.BLACK },
	    { Field.WALL, Field.WALL, Field.BLACK, Field.BLACK, Field.BLACK } };
    private final Field field2 = new Field(INITIAL2);

    private static final byte[][] INITIAL3 = { 
	    { Field.WHITE, Field.WHITE, Field.WHITE, Field.WALL, Field.WALL },
	    { Field.WHITE, Field.WHITE, Field.WHITE, Field.WALL, Field.WALL },
	    { Field.WHITE, Field.WHITE, Field.EMPTY, Field.EMPTY, Field.EMPTY },
	    { Field.WALL, Field.WALL, Field.EMPTY, Field.EMPTY, Field.EMPTY },
	    { Field.WALL, Field.WALL, Field.EMPTY, Field.EMPTY, Field.EMPTY } };
    private final Field field3 = new Field(INITIAL3);

    private static final byte[][] INITIAL4 = { 
	    { Field.BLACK, Field.EMPTY, Field.EMPTY, Field.WALL, Field.WALL },
	    { Field.EMPTY, Field.EMPTY, Field.EMPTY, Field.WALL, Field.WALL },
	    { Field.EMPTY, Field.EMPTY, Field.EMPTY, Field.EMPTY, Field.EMPTY },
	    { Field.WALL, Field.WALL, Field.EMPTY, Field.EMPTY, Field.EMPTY },
	    { Field.WALL, Field.WALL, Field.EMPTY, Field.EMPTY, Field.EMPTY } };
    private final Field field4 = new Field(INITIAL4);

    @Test
    void test() {
	int calculateDistance = Utils.calculateDistance(field);
	assertEquals(calculateDistance, 116);
	System.out.println(calculateDistance);
	calculateDistance = Utils.calculateDistance(field2);
	assertEquals(calculateDistance, 58);
	System.out.println(calculateDistance);
	calculateDistance = Utils.calculateDistance(field3);
	assertEquals(calculateDistance, 58);
	System.out.println(calculateDistance);
	calculateDistance = Utils.calculateDistance(field4);
	assertEquals(calculateDistance, 1);
	System.out.println(calculateDistance);
    }

}
