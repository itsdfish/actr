package actr.tasks.test.fatigue;

import java.util.Vector;
import actr.tasks.test.fatigue.Values;

public class SessionPVT {

	Vector<Block> blocks = new Vector<Block>();
	int blockIndex = 1;
	Values reactionTimes = new Values();
	Values timeOfStimuliFromStart;
	
	double startTime = 0;
	int alertResponse[] = new int[35]; // Alert responses (150-500 ms,10 ms intervals )
	double totalSessionTime = 0;
	int sleepAttacks = 0;
	int stimulusIndex = 0;
	int numberOfResponses = 0; // number of responses, this can be diff from the
	// stimulusIndex because of false responses
	double responseTotalTime = 0;
	
	public int getNumberOfAlertResponses(){
		int count = 0;
		for (int i = 0; i < reactionTimes.size() ; i++) 
			if (reactionTimes.get(i) > 150 && reactionTimes.get(i) <= 500)
				count++;
		return count;
	}
	
	public int getNumberOfLapses(){
		int count = 0;
		for (int i = 0; i < reactionTimes.size() ; i++) 
			if (reactionTimes.get(i) > 500)
				count++;
		return count;
	}
	
	public int getNumberOfFalseAlerts(){
		int count = 0;
		for (int i = 0; i < reactionTimes.size() ; i++) 
			if (reactionTimes.get(i) <= 150)
				count++;
		return count;
	}
	
	public int getNumberOfSleepAttacks(){
		return sleepAttacks;
	}
	
	public double getProportionOfFalseAlert() {
		return (double)getNumberOfFalseAlerts()/ reactionTimes.size();
	}
	public double getProportionOfLapses() {
		return (double)getNumberOfLapses()/ reactionTimes.size();
	}
	public double getProportionOfSleepAttacks() {
		return (double)getNumberOfSleepAttacks()/ reactionTimes.size();
	}
	
	public double getProportionOfAlertResponses() {
		return (double) getNumberOfAlertResponses() / reactionTimes.size();
	}
	public double getMeanAlertReactionTimes() {
		Values Alert = new Values();
		for (int i = 0; i < reactionTimes.size(); i++) {
			double r = reactionTimes.get(i);
			if (r <= 500 && r >= 150)
				Alert.add(r);
		}
		return Alert.average();
	}

	// 5-min blocks
	class Block {
		Values blockReactionTimes = new Values();
		double startTime;
		double totalBlockTime;
		int alertResponse[] = new int[35]; // Alert responses (150-500ms, 10ms
		// intervals )
		int numberOfResponses = 0;
		int sleepAttacks = 0;
		public int getNumberOfAlertResponses(){
			int count = 0;
			for (int i = 0; i < blockReactionTimes.size() ; i++)
				if (blockReactionTimes.get(i) > 150 && blockReactionTimes.get(i) <= 500)
					count++;
			return count;
		}
		
		public int getNumberOfLapses(){
			int count = 0;
			for (int i = 0; i < blockReactionTimes.size() ; i++) 
				if (blockReactionTimes.get(i) > 500)
					count++;
			return count;
		}
		
		public int getNumberOfFalseAlerts(){
			int count = 0;
			for (int i = 0; i < blockReactionTimes.size() ; i++) 
				if (blockReactionTimes.get(i) <= 150)
					count++;
			return count;
		}
		public double getProportionOfFalseAlert() {
			return (double)getNumberOfFalseAlerts()/ blockReactionTimes.size();
		}
		public double getProportionOfLapses() {
			return (double)getNumberOfLapses()/ blockReactionTimes.size();
		}
		public double getProportionOfAlertResponses() {
			return (double) getNumberOfAlertResponses() / blockReactionTimes.size();
		}
		public double getMeanAlertReactionTimes() {
			Values Alert = new Values();
			for (int i = 0; i < blockReactionTimes.size(); i++) {
				double r = blockReactionTimes.get(i);
				if (r <= 500 && r >= 150)
					Alert.add(r);
			}
			return Alert.average();
		}
	}
}