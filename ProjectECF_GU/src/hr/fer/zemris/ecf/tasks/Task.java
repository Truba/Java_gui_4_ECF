package hr.fer.zemris.ecf.tasks;

import hr.fer.zemris.ecf.console.ITalk;

import java.util.concurrent.Callable;

public class Task implements Callable<Void>{
	
	private Job params;
	private  ITalk console;


	public Task(Job params, ITalk console){
		this.params = params;
		this.console = console;
	}


	@Override
	public Void call() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
