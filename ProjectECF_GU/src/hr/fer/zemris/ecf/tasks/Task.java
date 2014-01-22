package hr.fer.zemris.ecf.tasks;

import hr.fer.zemris.ecf.console.ITalk;
import hr.fer.zemris.ecf.console.Job;

import java.util.concurrent.Callable;

/**
 * This class represents one thread task a.k.a. one ECF task. The thread that gets this is stopped until the ECF finishes this specific task.
 * The task for the ECF is given by the {@link Job}.
 * It uses {@link ITalk} to start ECF task.
 * @version 1.0
 *
 */
public class Task implements Callable<Void>, ISubject {
	
	private Job job;
	private ITalk console;
	private IObserver observer = null;

	/**
	 * Constructor it gets the specific {@link Job} and a specific {@link ITalk} for current pc.
	 * @param job given job
	 * @param console console type determined by {@link ITalk} implementation of specific pc this task will bi ran on.
	 */
	public Task(Job job, ITalk console){
		this.job = job;
		this.console = console;
	}


	@Override
	public Void call() throws Exception {
		console.write(job);
		job.isDone = true;
		finished();
		return null;
	}


	@Override
	public void setObserver(IObserver observer) {
		this.observer = observer;
	}


	@Override
	public void removeObserver() {
		observer = null;
	}


	@Override
	public void finished() {
		observer.update(this);
	}


	@Override
	public String getMessage() {
		return job.logFilePath;
	}

}
