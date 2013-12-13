package hr.fer.zemris.ecf.tasks;

import java.util.ArrayList;
import java.util.concurrent.Callable;

public class Task implements Callable<Void>{
	
	private ArrayList<String> params;


	public Task(ArrayList<String> params){
		this.params = params;
	}


	@Override
	public Void call() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
