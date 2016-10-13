//testing for GIT 2.1
//I don't understand any of this
public class Algorithms {

	public static void main(String[] args)
	{
		long startime = System.currentTimeMillis();
		//Print(LinearSummation(16)); //enter n
		long endtime = System.currentTimeMillis();
		
		double result = Geosum(1,100);
		System.out.println(result);
		
		long runtime = endtime - startime;
		System.out.println(runtime);
	}
	
	public static double Geosum(int base, int power)
	{
		double sum = 0;
		
		for (int i=0; i<=power;i++)
		{
			sum = sum + Math.pow(base, i);
		}
		
		return sum;
	}
	
	public static int LinearSummation(int input)
	{
		int sum = 0;
		
		for (int i=1; i<=input;i++)
		{
			if (input>0)
			{
				sum = sum + i;
			}
		}
		
		int check = (int) (0.5 * (input * (input+1))); //error check
		
		if (check == sum)
		{
			return(sum);
		}
		else
		{
			return(0);
		}
	}
	
	public static void Print(int a)
	{
		System.out.println(a);
	}
}
