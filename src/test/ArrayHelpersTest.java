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
	void testSortAccordingTo() {
		String[] meow = {"A", "C", "B", "C"};
		Integer[] meowSort = {1,3,2,3};
		String[] meowExpected = {"A", "B", "C", "C"};
		utility.ArrayHelpers.sortArrayAccordingTo(meow, meowSort);
		assertArrayEquals(meowExpected, meow);
	}

}
