//Programmer: Csaba Palosi
//Student ID: B00148978
//Date Written: 13/04/2023
//Function: Credit Union Bank System
import java.util.Scanner;
import java.io.*;
import javax.swing.*;
import java.util.*;

class CreditUnion
{
	public static void main (String args[])
	{
		System.out.println("WELCOME TO CREDIT UNION BANK ONLINE SYSTEM");
		CreateRandomFile accounts;
		accounts=new CreateRandomFile();
		WriteFile write;
		CloseAccount close;
		ReadRandomFile read;
		Lodgement lodgement;
		Withdrawal withdrawal;
		Overdraft overdraft;

		Scanner scan=new Scanner(System.in);
		int choice;
		choice=0;
		int ch []={1,2,3,4,5,6,7};
		String opts[]={"OPEN ACCOUNT","CLOSE ACCOUNT","MAKE LODGEMENT","MAKE WITHDRAWAL","SET NEW OVERDRAFT","VIEW ACCOUNT","EXIT"};

		while(choice != 7)
		{
			displayMenu(ch,opts);
			choice=scan.nextInt();

			switch(choice)
			{
				case 1: System.out.println("OPEN ACCOUNT");
				write=new WriteFile();
				break;
				case 2: System.out.println("CLOSE ACCOUNT");
				close=new CloseAccount();
				break;
				case 3: System.out.println("MAKE LODGEMENT");
				lodgement=new Lodgement();
				break;
				case 4: System.out.println("MAKE WITHDRAWAL");
				withdrawal=new Withdrawal();
				break;
				case 5: System.out.println("SET NEW OVERDRAFT");
				overdraft=new Overdraft();
				break;
				case 6: System.out.println("VIEW ACCOUNT");
				read=new ReadRandomFile();
				break;
				case 7: System.out.println("GOODBYE");
				break;
				default:System.out.println("PLEASE ENTER VALID CHOICE");
			}
		}
	}
	public static void displayMenu(int c[] , String o[])
	{
		System.out.println("---------------");
		System.out.println("    M E N U");
		System.out.println("---------------");
		for(int i=0; i<c.length; i++)
		{
			System.out.printf("%d. %s %n",c[i],o[i]);
		}
		System.out.println("===================");
		System.out.println("PLEASE ENTER CHOICE FROM ABOVE");
	}
}
class CreateRandomFile
{
	private Record blank;
  	private RandomAccessFile file;

  	public CreateRandomFile()
  	{
		blank=new Record();

		try
		{
			file=new RandomAccessFile("credit.dat","rw");

			for(int i=0; i<100; i++)
			{
				blank.write(file);
			}
		}
		catch(IOException e)
		{
			System.err.println("File not opened properly\n" + e.toString() );
			System.exit(1);
		}
	}
}
class Record
{
	private int account;
	private String id;
	private String firstName;
	private String lastName;
	private double balance, overdraft;

	public void read(RandomAccessFile file)throws IOException
	{
		account=file.readInt();

		char idaccount[]=new char[15];

		for(int i=0; i<idaccount.length; i++)
		{
			idaccount[i]=file.readChar();
		}

		id=new String(idaccount);

		char first[]=new char[15];

		for(int i=0; i<first.length; i++)
		{
			first[i]=file.readChar();
		}

		firstName=new String(first);

		char last[]=new char[15];

		for(int i=0; i<last.length; i++)
		{
			last[i]=file.readChar();
		}

		lastName=new String(last);

		balance=file.readDouble();
		overdraft=file.readDouble();
	}
	public void write(RandomAccessFile file)throws IOException
	{
		StringBuffer buf;

		file.writeInt(account);

		if(id != null)
		{
			buf=new StringBuffer(id);
		}
		else
		{
			buf=new StringBuffer(15);
		}

		buf.setLength(15);

		file.writeChars(buf.toString());

		if(firstName != null)
		{
			buf=new StringBuffer(firstName);
		}
		else
		{
			buf=new StringBuffer(15);
		}

		buf.setLength(15);

		file.writeChars(buf.toString());

		if(lastName != null)
		{
			buf=new StringBuffer(lastName);
		}
		else
		{
			buf=new StringBuffer(15);
		}

		buf.setLength(15);

		file.writeChars(buf.toString());

		file.writeDouble(balance);
		file.writeDouble(overdraft);
	}
	public void setAccount(int a) {account=a;}
	public int getAccount() {return account;}
	public void setId(String i) {id=i;}
	public String getId() {return id;}
	public void setFirstName(String f) {firstName=f;}
	public String getFirstName() {return firstName;}
	public void setLastName (String l) {lastName=l;}
	public String getLastName() {return lastName;}
	public void setBalance(double b) {balance=b;}
	public double getBalance() {return balance;}
	public void setOverdraft(double o) {overdraft=o;}
	public double getOverdraft() {return overdraft;}
	public static int size() {return 110;}
}
class WriteFile
{
	int accountNumber=0;
	String id;
	String first;
	String last;
	double balance;
	double overdraft;

	private RandomAccessFile output, input;
	private Record data;
	Scanner scan=new Scanner(System.in);

	public WriteFile()
	{
		data=new Record();

		try
		{
			output=new RandomAccessFile("credit.dat","rw");
		}
		catch(IOException e)
		{
			System.err.println(e.toString());
			System.exit(1);
		}
		addRecord();
	}
	public void addRecord()
	{
		try
			{
				System.out.println("Please enter Account number  between 1 & 100");
				accountNumber=scan.nextInt();
				while(accountNumber<1 || accountNumber>100)
				{
					System.out.println("Account number must be between 1 & 100, please try again");
					accountNumber=scan.nextInt();
				}
				System.out.println("Please enter ID");
				id=scan.next();
				System.out.println("Please enter first name");
				first=scan.next();
				System.out.println("Please enter last name");
				last=scan.next();
				System.out.println("Please enter account balance");
				balance=scan.nextDouble();
				System.out.println("Please enter account overdraft");
				overdraft=scan.nextDouble();

				if(accountNumber>0 && accountNumber<= 100)
				{
					output.seek((long) (accountNumber - 1) * Record.size());
	   				data.read(output);

	   				if(data.getAccount()==accountNumber)
	   				{
						System.out.println("Account already exists! Please try a different account number");
					}
					else
					{
						data.setAccount(accountNumber);
						data.setId(id);
						data.setFirstName(first);
						data.setLastName(last);
						data.setBalance(balance);
						data.setOverdraft(overdraft);
						output.seek( (long) ( accountNumber-1 ) * Record.size() );
						data.write( output );
						System.out.println("Account written successfully");
					}
				}
			}
			catch(NumberFormatException nfe)
			{
				System.err.println("You must enter an integer account number");
			}
			catch (IOException io)
			{
				System.err.println("error during write to file\n" + io.toString() );
			}
		}
}
class CloseAccount
{
	private RandomAccessFile input, output;
	private Record data=new Record();
	private double balance;
	private Scanner scan=new Scanner(System.in);
	private int accountNumber;

	public CloseAccount()
	{
		try
		{
			input=new RandomAccessFile("credit.dat", "rw");
	  		output=new RandomAccessFile("credit.dat", "rw");
	  	}
	  	catch(IOException e)
	  	{
			System.err.println(e.toString());
			System.exit(1);
		}
		deleteRecord();
	}
	public void deleteRecord()
	{
		System.out.println("Please enter the account number of account you wish to delete");
		accountNumber=scan.nextInt();
		while(accountNumber<1 || accountNumber>100)
		{
			System.out.println("Account number must be between 1 & 100, please try again");
			accountNumber=scan.nextInt();
		}
		if(accountNumber != 0)
		{
			try
			{
				data.setAccount(0);
				data.setFirstName(null);
				data.setLastName(null);
				data.setBalance(0);
				data.setOverdraft(0);

				output.seek((long)( accountNumber-1 )*Record.size());
				data.write( output );
			}
			catch(NumberFormatException nfe )
			{
				System.err.println("You must enter an integer account number");
			}
			catch(IOException io)
			{
				System.err.println("error during write to file\n" + io.toString() );
			}
		}
	}
}
class ReadRandomFile
{
	RandomAccessFile input;
	Record data;

	public ReadRandomFile()
	{
		data = new Record();
		try
			{
			input = new RandomAccessFile( "credit.dat", "rw" );
			}
		catch(IOException e)
			{
			System.err.println(e.toString());
			System.exit(1);
			}
			readRecord();
	}
	public void readRecord()
	{
		boolean cont=true;
		do
		{
			try
			{
				data.read(input);
				if(data.getAccount() != 0)
				{
				System.out.printf("Acc No: %d%n", data.getAccount() );
				System.out.printf("First: %s%n", data.getFirstName());
				System.out.printf("Last: %s%n", data.getLastName());
				System.out.printf("Balance: %.2f %n", data.getBalance());
				System.out.printf("Overdraft: %.2f %n", data.getOverdraft());
				System.out.printf("########################%n");
				}
			}
			catch (EOFException eof )
			{
				closeFile();
			}
			catch (IOException e )
			{
				System.err.println("Error during read from file\n " + e.toString());
		 		System.exit( 1 );
		 	}
		 }
		 while(cont);
		 closeFile();
	}
	private void closeFile()
	{
		try
		{
			input.close();
			System.exit(0);
		}
		catch(IOException e)
		{
			System.err.println("Error closing file \n" + e.toString());
		}
	}
}
class Lodgement
{
	int accountNumber=0;
	double balance;

	private RandomAccessFile output, input;
	private Record data;
	Scanner scan=new Scanner(System.in);

	public Lodgement()
	{
		data=new Record();

		try
		{
			output=new RandomAccessFile("credit.dat","rw");
		}
		catch(IOException e)
		{
			System.err.println(e.toString());
			System.exit(1);
		}
		addRecord();
	}
	public void addRecord()
	{
		try
			{
				System.out.println("Please enter Account number  between 1 & 100");
				accountNumber=scan.nextInt();
				while(accountNumber<1 || accountNumber>100)
				{
					System.out.println("Account number must be between 1 & 100, please try again");
					accountNumber=scan.nextInt();
				}
				System.out.println("Please enter the amount of money you wish to lodge");
				balance=scan.nextDouble();

				if(accountNumber>0 && accountNumber<= 100)
				{
					output.seek((long) (accountNumber - 1) * Record.size());
	   				data.read(output);

	   				if(data.getAccount()==accountNumber)
	   				{
						double lodge=data.getBalance()+balance;
						data.setBalance(lodge);
						output.seek( (long) ( accountNumber-1 ) * Record.size() );
						data.write( output );
						System.out.println("Account written successfully");
					}
				}
			}
			catch(NumberFormatException nfe)
			{
				System.err.println("You must enter an integer account number");
			}
			catch (IOException io)
			{
				System.err.println("error during write to file\n" + io.toString() );
			}
		}
}
class Withdrawal
{
	int accountNumber=0;
	double balance;

	private RandomAccessFile output, input;
	private Record data;
	Scanner scan=new Scanner(System.in);

	public Withdrawal()
	{
		data=new Record();

		try
		{
			output=new RandomAccessFile("credit.dat","rw");
		}
		catch(IOException e)
		{
			System.err.println(e.toString());
			System.exit(1);
		}
		addRecord();
	}
	public void addRecord()
	{
		try
			{
				System.out.println("Please enter Account number  between 1 & 100");
				accountNumber=scan.nextInt();
				while(accountNumber<1 || accountNumber>100)
				{
					System.out.println("Account number must be between 1 & 100, please try again");
					accountNumber=scan.nextInt();
				}
				System.out.println("Please enter the amount of money you wish to withdraw");
				balance=scan.nextDouble();

				if(accountNumber>0 && accountNumber<= 100)
				{
					output.seek((long) (accountNumber - 1) * Record.size());
	   				data.read(output);

	   				if(data.getAccount()==accountNumber)
	   				{
						double lodge=data.getBalance()-balance;
						data.setBalance(lodge);
						output.seek( (long) ( accountNumber-1 ) * Record.size() );
						data.write( output );
						System.out.println("Account written successfully");
					}
				}
			}
			catch(NumberFormatException nfe)
			{
				System.err.println("You must enter an integer account number");
			}
			catch (IOException io)
			{
				System.err.println("error during write to file\n" + io.toString() );
			}
		}
}
class Overdraft
{
	int accountNumber=0;
	double overdraft;

	private RandomAccessFile output, input;
	private Record data;
	Scanner scan=new Scanner(System.in);

	public Overdraft()
	{
		data=new Record();

		try
		{
			output=new RandomAccessFile("credit.dat","rw");
		}
		catch(IOException e)
		{
			System.err.println(e.toString());
			System.exit(1);
		}
		addRecord();
	}
	public void addRecord()
	{
		try
			{
				System.out.println("Please enter Account number  between 1 & 100");
				accountNumber=scan.nextInt();
				while(accountNumber<1 || accountNumber>100)
				{
					System.out.println("Account number must be between 1 & 100, please try again");
					accountNumber=scan.nextInt();
				}
				System.out.println("Please enter the new overdraft limit");
				overdraft=scan.nextDouble();

				if(accountNumber>0 && accountNumber<= 100)
				{
					output.seek((long) (accountNumber - 1) * Record.size());
	   				data.read(output);

	   				if(data.getAccount()==accountNumber)
	   				{
						data.setOverdraft(overdraft);
						output.seek( (long) ( accountNumber-1 ) * Record.size() );
						data.write( output );
						System.out.println("Account written successfully");
					}
				}
			}
			catch(NumberFormatException nfe)
			{
				System.err.println("You must enter an integer account number");
			}
			catch (IOException io)
			{
				System.err.println("error during write to file\n" + io.toString() );
			}
		}
}