//Programmer: Csaba Palosi
//Student ID: B00148978
//Date Written: 19/03/2023
//Function: Grading System
import java.util.*;

class Ass1B00148978
{
	public static void main(String[]args)
		{
			Scanner scan=new Scanner(System.in);

			int number;
			System.out.println("");
			System.out.println("Please enter how many results (between 3 and 26) would you like to input");
			System.out.println("");
			number=scan.nextInt();

			while(number<3 || number>26)
			{
				System.out.println("");
				System.out.println("Invalid number pls enter a valid number");
				System.out.println("");
				number=scan.nextInt();
			}


			int intArray[];
			intArray=new int[number];
			String stringArray[];
			stringArray=new String[number];
			int sum;
			sum=0;

			for(int i=0; i<stringArray.length; i++)
			{
				System.out.println("");
				System.out.println("Please enter the family name of the student");
			 	System.out.println("");
			 	stringArray[i]=scan.next();
			 	System.out.println("");
			 	System.out.println("Please enter the student grade (between 1 & 100)");
				System.out.println("");
				intArray[i]=scan.nextInt();
				while(intArray[i]<1 || intArray[i]>100)
					{
						System.out.println("");
						System.out.println("Invalid number pls enter a valid number");
						System.out.println("");
						intArray[i]=scan.nextInt();
					}
			}
			for(int i=0; i<intArray.length; i++)
			{
				sum=sum+intArray[i];
			}
			int average=sum/intArray.length;

			int hold;
			for(int pass=1; pass<intArray.length; pass=pass+1)
				for(int i=0; i<intArray.length-1; i=i+1)
					if(intArray[i]>intArray[i+1])
						{
							hold=intArray[i];
							intArray[i]=intArray[i+1];
							intArray[i+1]=hold;
						}
			int intArray2[];
			intArray2=new int[intArray.length];
			for(int i=0; i<intArray.length; i++)
			{
				intArray2[i]=intArray[i];
			}
			int hold2;
			for(int pass=1; pass<intArray2.length; pass=pass+1)
				for(int i=0; i<intArray2.length-1; i=i+1)
					if(intArray2[i]<intArray2[i+1])
						{
							hold2=intArray2[i];
							intArray2[i]=intArray2[i+1];
							intArray2[i+1]=hold2;
						}
			String word;
			int result;
			int response;

			do
			{
			System.out.println("------------------------------------------------");
			System.out.println("[1] Display average class grade");
			System.out.println("[2] Display lowest class grade");
			System.out.println("[3] Display highest class grade");
			System.out.println("[4] Sort & Display the grades in ascending order");
			System.out.println("[5] Search for an individual student by name");
			System.out.println("[6] Quit Program");
			System.out.println("");
			System.out.println("Enter choice [1,2,3,4,5,6]: ");
			System.out.println("------------------------------------------------");
			response=scan.next().charAt(0);

				switch(response)
				{
					case '1': System.out.println("");
					System.out.println("The average class grade of the students is: "+average+" %");
					System.out.println("");
					break;
					case '2': System.out.println("");
					System.out.println("The lowest class grade of the students is: "+intArray[0]+" %");
					System.out.println("");
					break;
					case '3': System.out.println("");
					System.out.println("The highest class grade of the students is: "+intArray2[0]+" %");
					System.out.println("");
					break;
					case '4': System.out.println("");
					System.out.println("The sorted grades in ascending order are: "+Arrays.toString(intArray)+" %");
					System.out.println("");
					break;
					case '5': System.out.println("");
					System.out.println("Enter student name you looking for");
					System.out.println("");
					word = scan.next();
					result = binarySearch(stringArray, word);
			   		if(result != -1)
			   		{
			   		System.out.println("");
			   		System.out.println("The student you entered is in the "+result+" place");
			   		System.out.println("");
					}
			   		else
			   		{
			   		System.out.println("");
					System.out.println("Invalid name");
					System.out.println("");
					}
					break;
					case '6': System.out.println("");
					System.out.println("Goodbye ");
					System.out.println("");
					break;
					default: System.out.println("");
					System.out.println("Options 1-6 only!");
					System.out.println("");
				}
			}
			while(response!='6');
		}
	static int binarySearch(String tempArray[], String key)
		{
			int first = 0;
			int last  = tempArray.length;

			while (first < last)
			{
				int mid = first + ((last - first) / 2);
				if (key.compareTo(tempArray[mid]) < 0)
				{
					last = mid;
				}
				else
				if (key.compareTo(tempArray[mid]) > 0)
				{
					first = mid + 1;
				}
				else
				{
					return mid;

				}
			}
			return - 1;
		}

}