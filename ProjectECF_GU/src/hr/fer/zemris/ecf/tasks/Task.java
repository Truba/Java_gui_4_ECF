package hr.fer.zemris.ecf.tasks;

import java.util.List;
import java.util.concurrent.Callable;

public class Task implements Callable<Void>{
	
	private Job params;


	public Task(Job params){
		this.params = params;
	}


	@Override
	public Void call() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
