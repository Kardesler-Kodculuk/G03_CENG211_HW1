/**
 * Helper methods for Array operations.
 */
package utility;
import java.lang.reflect.Array;

/**
 * Class holding the static methods for generating, editing
 * and modifying arrays.
 */
public class ArrayHelpers {
	
	/**
	 * This seems to be the only legal way to actually create a generic array inside a static method,
	 * It seems that normal methods fail due to the way Java's type erasure works, Array.newInstance
	 * is the most reasonable method as generic arrays seem to be impossible to return from static functions otherwise. 
	 * @param <T> 
	 * @param type Type of the array to be created.
	 * @param size Size of the array to be created.
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private static <T> T[] createObjectArray(Class<?> type, int size) {
		return (T[]) Array.newInstance(type, size);
	}
	

	/**
	 * Cut an array to the given index.
	 * @param <T>
	 * @param array Origin array.
	 * @param cutIndex array cut from the origin array.
	 * @return
	 */
	public static<T> T[] cutToIndex(T[] array, int cutIndex) {
		T reference = array[0] != null ? array[0] : array[1];
		if (reference == null) {
			throw new IllegalArgumentException();
		}
		T[] cutArray = createObjectArray(reference.getClass(), cutIndex + 1);
		for (int i = 0; i <= cutIndex; i++) {
			cutArray[i] = array[i];
		}
		return cutArray;
	}

	/**
	 * Take non-full filled array and remove excess space.
	 * @param <T>
	 * @param array
	 * @return
	 */
	public static<T> T[] trimArrayToFullFilled(T[] array) {
		int count = 0;
		for (T object : array) {
			if (object == null) {
				break;
			} else {
				count++;
			}
		}
		return cutToIndex(array, count - 1);
	}

	/**
	 * If array is full, it returns true. If not returns false
	 * @param array
	 * @return
	 */
	private static <T> boolean isFull(T[] array) {
		if (array[array.length - 1] == null) {
			return false;
		} else {
			return true;
		}
	}
	
	/**
	 * If the array is full, it doubles the capacity
	 * @param array
	 */
	public static <T> T[] ensureCapacity(T[] array) {
		if (array[0] != null && isFull(array)) {
			T[] doubledArray = createObjectArray(array[0].getClass(), array.length * 2);
			for (int i = 0; i < array.length; i++) {
				doubledArray[i] = array[i];
			}
			return doubledArray;
		}
		return array;
	}

	
	/**
	 * Sort targetArray according to helperArray.
	 * @param <T>
	 * @param <S>
	 * @param targetArray
	 * @param helperArray
	 */
	public static <T, S extends Comparable<S>> void sortArrayAccordingTo(T[] targetArray, S[] helperArray) {
		S temporaryCompareValue;
		T temporaryObjectValue;
		if (targetArray.length == helperArray.length) {
			for (int i = 0; i < targetArray.length; i++) {
				for (int j = 0; j < targetArray.length - i; j++) {
					if (j != targetArray.length - 1 && helperArray[j].compareTo(helperArray[j + 1]) >= 0) {
						temporaryCompareValue = helperArray[j];
						temporaryObjectValue = targetArray[j];
						helperArray[j] = helperArray[j + 1];
						targetArray[j] = targetArray[j + 1];
						helperArray[j + 1] = temporaryCompareValue;
						targetArray[j + 1] = temporaryObjectValue;
					}
				}
			}
		}
	}


	/**
	 * Calculate the average of a given array of values.
	 * @param values An array of double values.
	 * @return the average of the values.
	 */
	public static double calculateAvarage(double[] values) {
		double sum = 0;
		for (double number : values) {
			sum += number;
		}
		return sum / values.length;
	}


	/**
	 * Calculate the standard deviation of a given set of values.
	 * @param values an array of double values.
	 * @return the standard variation.
	 */
	public static double calculateStandartDeviation(double[] values) {
		double mean = calculateAvarage(values);
		double[] squaredValues = new double[values.length];
		for (int i = 0; i < values.length; i++) {
			squaredValues[i] = (values[i] - mean) * (values[i] - mean);
		}
		return Math.sqrt(calculateAvarage(squaredValues));
	}
	
	/**
	 * Since Java cannot print Object Arrays in a presentable way, this method will.
	 * @param <T>
	 * @param array Array to be printed.
	 */
	public static <T> void prettyPrintArray(T[] array) {
		String printString = "";
		for (T object : array) {
			printString = printString + object.toString() + ", ";
		}
		printString = printString.substring(0, printString.length() - 2);
		printString.replace("\n", "");
		System.out.println(printString);
	}
}
