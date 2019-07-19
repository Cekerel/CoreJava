package ChapterEight;

public class Pair<T> {
	private T first;
	private T second;

	public Pair() {
		first = null;
		second = null;
	}

	public Pair(T first, T second) {
		this.first = first;
		this.second = second;
	}

	/**
	 * @return the first
	 */
	public T getFirst() {
		return first;
	}

	/**
	 * @param first the first to set
	 */
	public void setFirst(T newValue) {
		first = newValue;
	}

	/**
	 * @return the second
	 */
	public T getSecond() {
		return second;
	}

	/**
	 * @param second the second to set
	 */
	public void setSecond(T newValue) {
		second = newValue;
	}

}
