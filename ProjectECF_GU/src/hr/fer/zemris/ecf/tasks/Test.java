package hr.fer.zemris.ecf.tasks;

import hr.fer.zemris.ecf.console.Job;

import java.util.ArrayList;

public class Test {
	
	public static void main(String[] args) {
		String ecfPath = "D:\\Documents\\SVEN\\FER\\Programiranje\\ECF_1.3\\debug\\examples\\GAonemax\\VS\\Debug\\gaonemax.exe";
		String paramsPath = "lib\\parameters.txt";
		
		ArrayList<Job> jobs = new ArrayList<>();
		for(int i=0; i<3; i++){
			jobs.add(new Job(ecfPath, "lib/log"+getNumOfJob(i)+".txt", paramsPath));
		}
		for(int i=3; i<5; i++){
			jobs.add(new Job(ecfPath, "lib/log"+getNumOfJob(i)+".txt", "lib\\parameters1.txt"));
		}
		
		TaskMannager ts = new TaskMannager();
		ts.startTasks(jobs, ts.getCpuCors());
	}

	private static String getNumOfJob(int i) {
		if(i < 10) {
			return "0" + String.valueOf(i + 1);
		} 
		return String.valueOf(i + 1);
	}

}
