package com.example.saboo.iplpredict.algorithm;
public class Home{
	double averages[];
	double strikeRates[];
	double avgAvg;
	double avgStrikeRate;
	double economy[];
	double runsScored;
	double runsConceded;
	String bowlerType[];
	String form[];
	double formProbablity;
	double runProbablity;
	double tossProbablity;
	double locationProbablity;
	boolean wonToss;//Though its won toss its batting first or not
	int wins;
	int loss;
	public Home(){
		averages=new double[15];
		strikeRates=new double[15];
		avgAvg=0;
		avgStrikeRate=0;
		wonToss=false;
	}
	public void init(double a[],double b[],double e[],String type[],String f[]){
		averages=a;
		strikeRates=b;
		economy=e;
		bowlerType=type;
		form=f;
	}
	public void RunsScored(){
		double sum=0;
		for(int i=0;i<6;i++){
			sum=sum+averages[i];
		}
		double avgAvgMain=sum/7;sum=0;
		for(int i=0;i<6;i++){
			sum=sum+strikeRates[i];
		}
		double avgStrikeRateMain=sum/7;
		sum=0;
		for(int i=6;i<11;i++){
			sum=sum+averages[i];
		}
		double avgAvgBottom=sum/4;sum=0;
		for(int i=6;i<11;i++){
			sum=sum+strikeRates[i];
		}
		sum=0;
		double avgStrikeRateBottom=sum/4;
		avgAvg=(0.8)*avgAvgMain+(0.2)*avgAvgBottom;
		avgStrikeRate=(0.8)*avgStrikeRateMain+(0.2)*avgStrikeRateBottom;
		runsScored=(((avgAvg)*11+((avgStrikeRate)*120)/100))/2;
	}
	public void RunsConceded(String type){
		double sum=0;
		for(int i=6;i<11;i++){
			if(bowlerType[i].compareTo("Spin")==0){
				sum=sum+(economy[i]-2)*4;
			}
			else
				sum=sum+economy[i]*4;
		}
		runsConceded=sum;
	}
	public void toss(boolean t,String pitch){
		if(pitch.compareTo("Batting")==0)
			wonToss=!t;
		else
			wonToss=t;
	}
	public void getFormResult(){
		int i=0;
		int sum=0;
		for(i=0;i<5;i++){
			if(form[i].compareTo("W")==0)
				sum=sum+1;
		}
		formProbablity=sum/5.0;
	}
	public void LocationHistory(){//For Home team we use location history and for away we use battle history
		if(wins>loss)
			locationProbablity=(wins-loss)/(wins+loss);
	}
}
