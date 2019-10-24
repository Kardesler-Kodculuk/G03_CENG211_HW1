package utility;
import java.lang.reflect.Array;

public class ArrayHelpers {
	public static <T> boolean isReferenceInArray(T object, T[] array) {
		for (T arrayObject : array) {
			if (arrayObject == object) {
				return true;
			}
		}
		return false;
	}
	
	public static<T> int returnIndexByReference(T object, T[] array) {
		for (int i = 0; i < array.length; i++) {
			if (object == array[i]) {
				return i;
			}

		}
		return -1;
	}
	
	/**
	 * This seems to be the only legal way to actually create a generic array inside a static method,
	 * It seems that normal methods fail due to the way Java's type erasure works, Array.newInstance
	 * is the most reasonable method as generic arrays seem to be impossible to return from static functions otherwise. 
	 * @param <T> 
	 * @param type Type of the array to be created.
	 * @param size Size of the array to be created.
	 * @return
	 */
	private static <T> T[] createObjectArray(Class<?> type, int size) {
		return (T[]) Array.newInstance(type, size);
	}
	
	/**
	 * Straighten a 2D matrix into a one dimensional array.
	 * @param <T> Object type.
	 * @param matrix 2D matrix to unwrap
	 * @return Unwrapped array.
	 */
	@SuppressWarnings("unchecked")
	public static<T> T[] straighten(T[][] matrix) {
		int resultLength = 0;
		int resultIndex = 0;
		T[] resultArray;
		for (T[] subarray : matrix) {
			resultLength += subarray.length;
		}
		resultArray = createObjectArray(matrix[1][0].getClass(), resultLength);
		for (T[] subarray : matrix) {
			for (T object : subarray) {
				resultArray[resultIndex] = object;
				resultIndex++;
			}
		}
		return resultArray;
	}
	
	@SuppressWarnings("unchecked")
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
		return cutToIndex(array, count);
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
			@SuppressWarnings("unchecked")
			T[] doubledArray = createObjectArray(array[0].getClass(), array.length * 2);
			for (int i = 0; i < array.length; i++) {
				doubledArray[i] = array[i];
			}
			return doubledArray;
		}
		return array;
	}
	
	/**
	 * Set all the elements of the array to null
	 * @param <T> Type of array.
	 * @param array Array to format.
	 */
	public static <T> void formatArray(T[] array) {
		for (T object : array) {
			object = null;
		}
	}
	
	/**
	 * Find the difference between maximum and minimum numbers in a double or double castable array.
	 * @param array of doubles or double-castable numerals.
	 * @return the difference.
	 */
	public static double findMinMaxDifference(double[] array) {
		double min = array[0];
		double max = array[0];
		double difference = 0;
		for (double number : array) {
			if (number < min) {
				min = number;
			} else if (number > max) {
				max = number;
			}
		}
		difference = max - min;
		return difference;
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
				for (int j = i + 1; j < targetArray.length; j++) {
					if (helperArray[j].compareTo(helperArray[j + 1]) > 0) {
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
	 * Calculate and return the mean value from an array of values.
	 * @param values
	 * @return
	 */
	public static double calculateMean(double[] values) {
		Double[] valuesSorted = new Double[values.length];
		double mean;
		for (int i = 0; i < values.length; i++) {
			valuesSorted[i] = values[i];
		}
		sortArrayAccordingTo(valuesSorted, valuesSorted.clone());
		if (values.length % 2 == 0) {
			mean = (valuesSorted[values.length / 2] + valuesSorted[values.length / 2 + 1]) / 2;
		} else {
			mean = valuesSorted[values.length / 2 + 1];
		}
		return mean;
	}
}
