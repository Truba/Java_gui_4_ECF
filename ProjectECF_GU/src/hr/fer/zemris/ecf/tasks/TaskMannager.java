package hr.fer.zemris.ecf.tasks;

import hr.fer.zemris.ecf.console.DetectOS;
import hr.fer.zemris.ecf.console.ITalk;
import hr.fer.zemris.ecf.console.Job;
import hr.fer.zemris.ecf.param.AlgGenRegInit;
import hr.fer.zemris.ecf.xmldom.XmlReading;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TaskMannager {
	
	private List<Future<Void>> results;
	private ITalk console;
	private int cpuCors;
	
	public TaskMannager(){
		DetectOS os = new DetectOS(); 
        console = os.getOS_console();
        cpuCors = Runtime.getRuntime().availableProcessors();
	}
	
	public List<Future<Void>> getResults() {
		return results;
	}
	
		
	public int getCpuCors() {
		return cpuCors;
	}

	public AlgGenRegInit getInitialECFparams(String ecfPath, String paramsPath){
		console.write(ecfPath,paramsPath);
		return XmlReading.readInitial(paramsPath);		
	}
	
	public boolean startTasks(List<Job> taskDescriptions, int numOfThreads) {		
		
		ExecutorService service = Executors.newFixedThreadPool(numOfThreads);
		List<Task> tasks = new ArrayList<>();
		
		for(int i=0; i<taskDescriptions.size(); i++){
			tasks.add(new Task(taskDescriptions.get(i),console));
		}

		results = null;
		try {
			results = service.invokeAll(tasks);
		} catch (InterruptedException e) { 
			System.err.println("Fatal error! Can't do parallelization");
			return false;
		}
		for(Future<Void> res : results){
			try {
				res.get();
			} catch (InterruptedException | ExecutionException e) {
				System.err.println("Fatal error! Can't fetch jobs.");
				return false;
			}
		}
		service.shutdown();
		return true;
	}


}
