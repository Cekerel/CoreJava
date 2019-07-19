package ChapterOne;

import java.math.*;

public class BigIntegerAndBigDecimal {

	 public static void main(String[] args) {
		 /*
		  * BigInteger
		  */
		 //BigInteger下的加减乘除模计算
		 BigInteger b = BigInteger.valueOf(100);
	     BigInteger a = BigInteger.valueOf(17);
	     BigInteger temp1 = b.add(a);
	     BigInteger temp2 = b.subtract(a);
	     BigInteger temp3 = b.multiply(a);
	     BigInteger temp4 = b.divide(a);
	     BigInteger temp5 = b.mod(a);
	     System.out.println(temp1+" "+temp2+" "+temp3+" "+temp4+" "+temp5);
	     
	     //1!+2!+3!+...100!
	     BigInteger sum = BigInteger.valueOf(0);
	     int i = 1;
	     do{
	    	 BigInteger temp = BigInteger.valueOf(1);
	    	 for(int j = 1;j < i;j++){
	    		 temp = temp.multiply(BigInteger.valueOf(j));
	    	 }
	    	 sum = sum.add(temp);
	    	 i++;
	     }while(i <= 100);
	     System.out.println(sum);
	     
	     /*
	      * BigDecimal
	      */
	     //BigDecimal下的加减乘除计算
	     BigDecimal d = BigDecimal.valueOf(100);
	     BigDecimal e = BigDecimal.valueOf(45);
	     BigDecimal temp6 = d.add(e);
	     BigDecimal temp7 = d.subtract(e);
	     BigDecimal temp8 = d.multiply(e);
	     BigDecimal temp9 = d.divide(e, 4);
	     System.out.println(temp6+" "+temp7+" "+temp8+" "+temp9);
	    }
}
