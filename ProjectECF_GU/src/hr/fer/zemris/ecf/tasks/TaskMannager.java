package hr.fer.zemris.ecf.tasks;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TaskMannager {
	
	private List<Future<Void>> rezults;
	
	
	public List<Future<Void>> getRezults() {
		return rezults;
	}


	public void startTasks(ArrayList<ArrayList<String>> taskDescriptions) {
		ExecutorService service = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
		List<Task> tasks = new ArrayList<>();
		
		for(int i=0; i<taskDescriptions.size(); i++){
			tasks.add(new Task(taskDescriptions.get(i)));
		}
//		while(true) {
//			break;
//			//slozi poslove
//		}
		rezults = null;
		try {
			rezults = service.invokeAll(tasks);
		} catch (InterruptedException e) {
			// TODO Neka greska
			e.printStackTrace();
		}
		for(Future<Void> res : rezults){
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
