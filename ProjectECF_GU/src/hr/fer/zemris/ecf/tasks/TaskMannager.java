package hr.fer.zemris.ecf.tasks;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TaskMannager {
	
	public List<Future<Void>> rezults;
	
	public void startTasks(ArrayList<ArrayList<String>> taskDescriptions) {
		ExecutorService service = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
		List<Task> tasks = new ArrayList<>();
		
		
		while(true) {
			break;
			//slozi poslove
		}
		rezults = null;
		try {
			rezults = service.invokeAll(tasks);
		} catch (InterruptedException e) {
			// TODO Neka greska
			e.printStackTrace();
		}
//		for(Future<Void> res : rezults){
//			try {
//				res.get();
//			} catch (InterruptedException | ExecutionException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
		service.shutdown();
	}


}
