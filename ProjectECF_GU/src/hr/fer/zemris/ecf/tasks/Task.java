package hr.fer.zemris.ecf.tasks;

import hr.fer.zemris.ecf.console.ITalk;
import hr.fer.zemris.ecf.console.Job;

import java.util.concurrent.Callable;

public class Task implements Callable<Void>{
	
	private Job job;
	private  ITalk console;


	public Task(Job job, ITalk console){
		this.job = job;
		this.console = console;
	}


	@Override
	public Void call() throws Exception {
		job.isDone = true;
		return null;
	}

}
