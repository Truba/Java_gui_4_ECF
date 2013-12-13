package hr.fer.zemris.ecf.tasks;

import hr.fer.zemris.ecf.console.DetectOS;
import hr.fer.zemris.ecf.console.ITalk;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TaskMannager {
	
	private List<Future<Void>> results;
	private ITalk console;
	
	public TaskMannager(){
		DetectOS os = new DetectOS(); 
        console = os.getOS_console();
	}
	
	public List<Future<Void>> getResults() {
		return results;
	}


	public void startTasks(List<Job> taskDescriptions) {		
		
		ExecutorService service = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
		List<Task> tasks = new ArrayList<>();
		
		for(int i=0; i<taskDescriptions.size(); i++){
			tasks.add(new Task(taskDescriptions.get(i),console));
		}
//		while(true) {
//			break;
//			//slozi poslove
//		}
		results = null;
		try {
			results = service.invokeAll(tasks);
		} catch (InterruptedException e) {
			// TODO Neka greska
			e.printStackTrace();
		}
		for(Future<Void> res : results){
			try {
				res.get();
				//ovdje bi trebali javiti kako koji zadatak zavrsava
			} catch (InterruptedException | ExecutionException e) {
				// TODO Neka greska
				e.printStackTrace();
			}
		}
		service.shutdown();
	}


}
