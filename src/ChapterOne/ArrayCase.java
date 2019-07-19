package ChapterOne;

public class ArrayCase {
	public static void main(String[] args) {
		final double startrate = 10;
		final int nrates = 6;
		final int nyears = 10;
		
		
		//set interest rates in 10 to 15%
		double [] interestRate = new double[nrates];
		for(int j = 0; j < interestRate.length; j++)
			interestRate[j] = (startrate + j)/100.0;
		double [][] balances = new double [nyears][nrates];
		
		//set initial balances to 10000
		for(int j = 0; j < balances[0].length; j++)
			balances[0][j] = 10000;
		//compute interest for future years
		for(int i = 1;i < balances.length; i++){
			for(int j = 0; j < balances[i].length; j++){
				//get last year's balances from precious row
				double oldBalance = balances[i-1][j];
				
				//compute interest
				double interest = oldBalance * interestRate[j];
				
				//compute this year's balances
				balances[i][j] = oldBalance + interest;
			}
		}
		for(int i = 0; i < interestRate.length; i++)
			System.out.printf("%9.0f%%", 100 * interestRate[i]);
		System.out.println();
		for(double [] row : balances){
			for(double b : row)
				System.out.printf("%10.2f", b);
			System.out.println();
		}
	}
}
