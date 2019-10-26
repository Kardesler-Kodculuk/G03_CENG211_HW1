package test;

import static org.junit.jupiter.api.Assertions.*;

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
		assertEquals(9, utility.ArrayHelpers.findMinMaxDifference(array));
		double[] negativeArray = {-16, 0, 16, 6, 20};
		assertEquals(36, utility.ArrayHelpers.findMinMaxDifference(negativeArray));
	}

	@Test
	void testCalculateMean() {
		double[] array = {14, 17, 13, 15, 17};
		double[] carbonCopy = array.clone();
		assertEquals(15, utility.ArrayHelpers.calculateMean(array));
		double[] evenArray = {14, 17, 13, 15, 16, 17};
		assertEquals(15.5, utility.ArrayHelpers.calculateMean(evenArray));
		assertArrayEquals(carbonCopy, array);
	}
	
	@Test
	void testQuickSort() {
		double[] actual = {14, 17, 13, 15, 17};
		double[] expected = {13, 14, 15, 17, 17};
		utility.ArrayHelpers.quickSort(actual);
		assertArrayEquals(expected, actual);
	}
	
	@Test
	void testSortAccordingTo() {
		String[] meow = {"A", "C", "B", "C"};
		Integer[] meowSort = {1,3,2,3};
		String[] meowExpected = {"A", "B", "C", "C"};
		utility.ArrayHelpers.sortArrayAccordingTo(meow, meowSort);
		assertArrayEquals(meowExpected, meow);
	}

}
