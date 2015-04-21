package com.example.saboo.iplpredict.algorithm;

import java.io.FileInputStream;
import java.io.PrintStream;
import java.util.Scanner;


public class Mainclass {
	public static void main(String args[]){
		try{
			System.setIn(new FileInputStream("C:\\Users\\Kumar BN\\Desktop\\RCBvsMI.txt"));
			System.setOut(new PrintStream("C:\\Users\\Kumar BN\\Desktop\\ipl.txt"));
		}
		catch(Exception e)
		{
			System.out.print("asdasd");
		}
		Home home=new Home();
		Away away=new Away();
		double averages[]=new double[11];
		double strikeRates[]=new double[11];
		double economy[]=new double[11];
		String bowlerType[]=new String[11];
		String form[]=new String[5];
		//
		double averagesa[]=new double[11];
		double strikeRatesa[]=new double[11];
		double economya[]=new double[11];
		String bowlerTypea[]=new String[11];
		String forma[]=new String[5];
		Scanner in=new Scanner(System.in);
		System.out.println("Home team");
		System.out.println("Enter the averages");
		int i=0;
		for(i=0;i<6;i++)
			averages[i]=in.nextDouble();
		System.out.println("Enter the Strike RAtes");
		for(i=0;i<6;i++)
			strikeRates[i]=in.nextDouble();
		System.out.println("Enter the economy");
		for(i=0;i<11;i++)
			economy[i]=in.nextDouble();
		System.out.println("Enter the Type");
		for(i=0;i<11;i++)
			bowlerType[i]=in.next();
		in.nextLine();
		System.out.println("Enter the Form");
		for(i=0;i<5;i++)
			form[i]=in.next();
		in.nextLine();
		home.init(averages,strikeRates,economy,bowlerType,form);
		System.out.println("***********Away team");
		System.out.println("Enter the averages");
		i=0;
		for(i=0;i<11;i++)
			averagesa[i]=in.nextDouble();
		System.out.println("Enter the Strike RAtes");
		for(i=0;i<11;i++)
			strikeRatesa[i]=in.nextDouble();
		System.out.println("Enter the economy");
		for(i=0;i<11;i++)
			economya[i]=in.nextDouble();
		System.out.println("Enter the Type");
		for(i=0;i<11;i++)
			bowlerTypea[i]=in.next();
		System.out.println("Enter the Form");
		for(i=0;i<5;i++)
			forma[i]=in.next();
		away.init(averagesa,strikeRatesa,economya,bowlerTypea,forma);
		System.out.println("Thanks for all the data");
		System.out.println("Welcome to the match*****Please let us know if home team is batting first and the pitch type");
		boolean toss=in.nextBoolean();
		String pitch=in.next();
		in.nextLine();
		home.toss(toss,pitch);
		away.toss(!toss,pitch);
		home.RunsConceded(pitch);
		home.RunsScored();
		System.out.println("Home team will score"+home.runsScored);
		System.out.println("Home team will Conceed"+home.runsConceded);
		away.RunsConceded(pitch);
		away.RunsScored();
		System.out.println("Away team will score"+away.runsScored);
		System.out.println("Away team will Conceed"+away.runsConceded);
		getRunProbablity(home,away);
		getTossProbablity(home,away);
		getFormProbablity(home,away);
		double totalHomeProbablity=(home.runProbablity)+(home.formProbablity)*0.25+(home.tossProbablity);
		double totalAwayProbablity=(away.runProbablity)+(away.formProbablity)*0.25+(away.tossProbablity);
		System.out.println("The probablity of home team winning is on runs"+home.runProbablity);
		System.out.println("The probablity of away team winning is on runs"+away.runProbablity);
		System.out.println("The probablity of home team winning is on toss"+home.tossProbablity);
		System.out.println("The probablity of away team winning is on toss"+away.tossProbablity);
		System.out.println("The probablity of home team winning is on form"+home.formProbablity);
		System.out.println("The probablity of away team winning is on form"+away.formProbablity);
		System.out.println("The probablity of home team winning is"+totalHomeProbablity);
		System.out.println("The probablity of Away team winning is"+totalAwayProbablity);
	}

	private static double getRunProbablity(Home home, Away away) {
		//double homeRun=(home.runsScored-away.runsConceded);
		//double awayRun=(away.runsScored-home.runsConceded);
		double batting=(home.runsScored-away.runsScored);
		double bowling=(away.runsConceded-home.runsConceded);
		double netDifference=batting+bowling;
		double result;
		System.out.println("Entering runProbablity");
		boolean homeWin=false;
		if(netDifference>=0){
			homeWin=true;
			//result=homeRun-awayRun;
			result=netDifference;                                                                           
		}
		else
		{
			System.out.println("entering else and then setting");
			homeWin=false;
			//result=awayRun-homeRun;
			result=-netDifference;
		}
		System.out.println("Home run winning on runs is"+homeWin);
		int ch=(int)result;
		if(homeWin){
			if(result>=0 && result<=10){
				home.runProbablity=0.5;
				away.runProbablity=0.45;
			}
			else if(result>10 && result<=20){
				home.runProbablity=0.5;
				away.runProbablity=0.4;
			}
			else if(result>20 && result<=30){
				home.runProbablity=0.5;
				away.runProbablity=0.35;
			}
			else if(result>30){
				home.runProbablity=0.5;
				away.runProbablity=0.30;
			}
		}
		else{
			System.out.println("Entering else on away and result="+result);
			if(result>0 && result<=10){
				away.runProbablity=0.5;
				home.runProbablity=0.45;
			}
			else if(result>10 && result<=20){
				away.runProbablity=0.5;
				home.runProbablity=0.4;
			}
			else if(result>20 && result<=30){
				away.runProbablity=0.5;
				home.runProbablity=0.35;
			}
			else if(result>30){
				away.runProbablity=0.5;
				home.runProbablity=0.30;
			}
		}
		return 0;
	}

	private static double getFormProbablity(Home home, Away away) {
		home.getFormResult();
		away.getFormResult();
		
		return 0;
	}

	private static double getTossProbablity(Home home, Away away) {
		if(home.wonToss==true){
			home.tossProbablity=0.2;
			away.tossProbablity=0;
		}
		else{
			away.tossProbablity=0.2;
			home.tossProbablity=0;
		}
		return 0;
	}

}
