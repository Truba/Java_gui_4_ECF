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

/**
 * This class is a manager for tasks and threads that are running those tasks. It's main use is to talk to GUI.
 * It can gather initial parameters, it can find out how many CPU cores current PC has, and of course it's main use startTasks meted.
 * It gets list of {@link Job} and number of threads to run them on.
 * @version 1.0
 *
 */
public class TaskMannager {
	
	private List<Future<Void>> results;
	private ITalk console;
	private int cpuCors;
	
	/**
	 * Constructor, it automatically detects operating system, stores {@link ITalk} needed for that OS, and it get the number of CPU cores and stores it also.
	 */
	public TaskMannager(){
		DetectOS os = new DetectOS(); 
        console = os.getOS_console();
        cpuCors = Runtime.getRuntime().availableProcessors();
	}
	
	/**
	 * This is probably useless method.
	 * @return list of future voids as the results of {@link Task}s well done.
	 */
	public List<Future<Void>> getResults() {
		return results;
	}
	
	/**
	 * Getter for the number of CPU cores on current PC.
	 * @return number of CPU cores on current PC
	 */
	public int getCpuCors() {
		return cpuCors;
	}

	/**
	 * This meted gets initial ECF parameters dumped by the ECF.
	 * @param ecfPath path to the ECF
	 * @param paramsPath path to where the parameters will be dumped
	 * @return initial compilation of algorithms, genotypes, and registry.
	 */
	public AlgGenRegInit getInitialECFparams(String ecfPath, String paramsPath){
		console.write(ecfPath,paramsPath);
		return XmlReading.readInitial(paramsPath);		
	}
	
	/**
	 * This method is used for running {@link Task}s.
	 * It gets list of {@link Job} and number of threads to run them on.
	 * @param taskDescriptions array list of jobs needed to do.
	 * @param numOfThreads number of threads to run them on.
	 * @return if all done with no problem, false if problem.
	 */
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
