package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ArrayHelpersTest {

	@Test
	void testTrimArrayToFullFilled() {
		String[] array = {"cat", "cat", null};
		String [] actual = {"cat", "cat"};
		assertArrayEquals(utility.ArrayHelpers.trimArrayToFullFilled(array), actual);
	}

	@Test
	void testEnsureCapacity() {
		Integer[] array = {1,2};
		array = utility.ArrayHelpers.ensureCapacity(array);
		assertEquals(array.length, 4);
	}

	@Test
	void testFormatArray() {
		Integer[] array = {1, 2};
		Integer[] actual = {null, null};
		utility.ArrayHelpers.formatArray(array);
		assertArrayEquals(array, actual);
	}

	@Test
	void testFindMinMaxDifference() {
		double[] array = {15, 16, 12, 7, 10};
		assertEquals(utility.ArrayHelpers.findMinMaxDifference(array), 9);
		double[] negativeArray = {-16, 0, 16, 6, 20};
		assertEquals(utility.ArrayHelpers.findMinMaxDifference(negativeArray), 36);
	}

	@Test
	void testCalculateMean() {
		fail("Not yet implemented");
	}

}
