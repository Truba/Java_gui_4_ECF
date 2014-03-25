package hr.fer.zemris.ecf.tasks;

import hr.fer.zemris.ecf.console.DetectOS;
import hr.fer.zemris.ecf.console.ITalk;
import hr.fer.zemris.ecf.console.Job;
import hr.fer.zemris.ecf.param.AlgGenRegInit;
import hr.fer.zemris.ecf.xmldom.XmlReading;

import java.util.List;

/**
 * This class is a manager for tasks and threads that are running those tasks. It's main use is to talk to GUI.
 * It can gather initial parameters, it can find out how many CPU cores current PC has, and of course it's main use startTasks meted.
 * It gets list of {@link Job} and number of threads to run them on.
 * @version 1.0
 *
 */
public class TaskMannager {
	
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
	 * @throws Exception If problem occurs while running
	 */
	public void startTasks(List<Job> taskDescriptions, int numOfThreads) throws Exception {		
		
		Thread[] threads = new Thread[numOfThreads];
		
		int size = taskDescriptions.size();
		for (int i=0; i < size; i++){
			Task task = new Task(taskDescriptions.get(i),console);
			threads[i] = new Thread(task);
			threads[i].setDaemon(true);
		}

		for (int i = 0; i < size; i++) {
			threads[i].start();
		}
	}


}
