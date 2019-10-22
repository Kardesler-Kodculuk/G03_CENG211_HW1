package utility;

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
		resultArray = (T[]) new Object[resultLength];
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
		T[] cutArray = (T[]) new Object[cutIndex - 1];
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
		if (array[array.length] == null) {
			return true;
		} else {
			return false;
		}
	}
	/**
	 * If the array is full, it doubles the capacity
	 * @param array
	 */
	public static <T> void ensureCapacity(T[] array) {
		if (isFull(array)) {
			@SuppressWarnings("unchecked")
			T[] doubledArray = (T[]) new Object[array.length*2];
			for (int i = 0; i < array.length; i++) {
				doubledArray[i] = array[i];
			}
			array = doubledArray;
		}
	}
}
