package hr.fer.zemris.ecf.tasks;

import java.lang.management.ManagementFactory;

import hr.fer.zemris.ecf.console.ITalk;
import hr.fer.zemris.ecf.console.Job;

/**
 * This class represents one thread task a.k.a. one ECF task. The thread that gets this is stopped until the ECF finishes this specific task.
 * The task for the ECF is given by the {@link Job}.
 * It uses {@link ITalk} to start ECF task.
 * @version 1.0
 *
 */
public class Task implements Runnable {
	
	private Job job;
	private ITalk console;

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
	public void run() {
		// FIXME example
		System.out.println(Thread.currentThread().getId());
		System.out.println(ManagementFactory.getRuntimeMXBean().getName());
		console.write(job);
		job.isDone = true;
		try {
			job.finished();
		} catch (Exception e) {
			// TODO rijesiti!!
			e.printStackTrace();
		}
	}

}
