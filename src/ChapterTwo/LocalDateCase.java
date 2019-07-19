package ChapterTwo;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class LocalDateCase {
	public static void main(String[] args) {
		
		LocalDate localDate = LocalDate.now();
		System.out.println(localDate);
		System.out.println(LocalDate.of(1997, 03, 25));
		
		
		/**
		 * get()方法是个访问器方法,它只访问对象而不修改对象
		 */
		LocalDate localDate2 = LocalDate.of(1997, 03, 25);
		int year = localDate2.getYear();
		int month = localDate2.getMonthValue();
		int day = localDate2.getDayOfMonth();
		System.out.println(year + "-" + month + "-" + day);
		
		/**
		 * 分别获取年、月、日的具体值
		 */
		year = localDate2.getYear();
		month = localDate2.getMonthValue();
		day = localDate2.getDayOfMonth();
		System.out.println(year + "-" + month + "-" + day);
		
		/**
		 * localdate变量加上一定的天数计算出一个新日期并将其赋给新建的一个日期对象(localdate的值并不改变)
		 */
		LocalDate newyear = localDate2.plusDays(1000); //plusDays(int n) ---> 生成当前日期之后n天的日期
		year = newyear.getYear();
		month = newyear.getMonthValue();
		day = newyear.getDayOfMonth();
		System.out.println(year + "-" + month + "-" + day);
		
		/**
		 * 打印当前月份的日历
		 */
		LocalDate date = LocalDate.now(); 
		month = date.getMonthValue(); 
		int today = date.getDayOfMonth();
		date = date.minusDays(today - 1); //将date设置成该月的第一天以便用for循环打印. minusDays(int n) ---> 生成当前日期 的第n天之前的日期
		DayOfWeek weekday = date.getDayOfWeek(); //调用DayOfWeek类中的getvalue方法来得到星期几的一个数值
		int value = weekday.getValue(); 
		System.out.println("Mon Tue Wed Thu Fri Sta Sun"); //遵循国际惯例（周末是一周的结尾）
		for(int i = 1; i < value; i++) 
			System.out.print("    "); 
		while (date.getMonthValue()  == month) {
			System.out.printf("%3d", date.getDayOfMonth());
			if (date.getDayOfMonth() == today)
				System.out.print("*");
			else
				System.out.print(" ");
			date = date.plusDays(1);
			if (date.getDayOfWeek().getValue() == 1)
				System.out.println();
		}
		System.out.println();
		System.out.println();
		
		
		
		
		/**
		 * 书上的范例
		 * @version 1.5 2015-05-08
		 * @author ASUS 
		 */
		LocalDate date2 = LocalDate.now();
		int month1 = date2.getMonthValue();
		int today1 = date2.getDayOfMonth();
		
		date2 = date2.minusDays(today - 1);
		DayOfWeek week1 = date2.getDayOfWeek();
		int value1 = week1.getValue();
		System.out.println("Sun Mon Tue Wed Thu Fri Sta");
		for(int i = 0; i < value1; i++)
			System.out.print("    ");
		
		while (date2.getMonthValue() == month) {
			System.out.printf("%3d", date2.getDayOfMonth());
			if(date2.getDayOfMonth() == today)
				System.out.print("*");
			else
				System.out.print(" ");
			date2 = date2.plusDays(1);
			if (date2.getDayOfWeek().getValue() == 7)
				System.out.println();
		}
		
		
	}
}
