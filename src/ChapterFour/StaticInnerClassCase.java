package ChapterFour;

/**
 * This program demonstrates the use of the static inner classes.
 * @version 1.02 2015-05-12
 * @author Cay Horstmann
 *
 */
public class StaticInnerClassCase {
	public static void main(String[] args) {
		double [] numbers = new double[] {
			564395.43261,
			4329180.4321,
			543255.5342,
			56433.0,
			54325.5432,
		};
		ArrayAlg.Pair pair = ArrayAlg.minmax(numbers);
		System.out.println("The minimum is " + pair.getFirst());
		System.out.println("The maximum is " + pair.getSecond());
	}
}

class ArrayAlg {
	/**
	 * A pair of the floating-point numbers.
	 */
	public static class Pair {
		private double first;
		private double second;
		
		
		/**
		 * Constructs a pair from two floating-point numbers
		 * @param first the first number
		 * @param second the second number
		 */
		public Pair(double first, double second) {
			// TODO Auto-generated constructor stub
			this.first = first;
			this.second = second;
		}
		
		public double getFirst() {
			return first;
		}
		
		public double getSecond() {
			return second;
		}
	}
	/**
	 * Computes both minimum and maximum of an array
	 * @param numbers an array of floating-point numbers
	 * @return a pair whose first element is the minimum and whose second element is the maximum
	 */
	public static Pair minmax(double [] numbers) {
		double min = Double.POSITIVE_INFINITY;
		double max = Double.NEGATIVE_INFINITY;
		for (double d : numbers) {
			if (min > d) {
				min = d;
			}
			if (max < d) {
				max = d;
			}
		}
		return new Pair(min, max);
		
	}
	
}
