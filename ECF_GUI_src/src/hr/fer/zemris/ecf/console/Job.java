package hr.fer.zemris.ecf.console;

import hr.fer.zemris.ecf.tasks.Task;
import hr.fer.zemris.ecf.tasks.TaskMannager;

/**
 * This class is the list of parameters that are required for {@link ITalk} to
 * communicate to ECF. It consists of path to the ECF, path to the parameters
 * that are given to the ECF, path to where the log file will be placed. And
 * this also contains isDone variable that is internally used for {@link Task}
 * and {@link TaskMannager} to tell GUI that this computation is finished.
 * 
 * @version 1.0
 * 
 */
public class Job implements ISubject {

	public String ecfPath;
	public String paramsPath;
	public String logFilePath;
	public boolean isDone;
	
	private IObserver observer = null;

	/**
	 * Main constructor, it gets all that is needed for communication to the
	 * ECF.
	 * 
	 * @param ecfPath
	 *            path to the ECF.
	 * @param logFilePath
	 *            path to where the log file will be placed.
	 * @param paramsPath
	 *            path to the parameters that are given to the ECF.
	 */
	public Job(String ecfPath, String logFilePath, String paramsPath) {
		this.ecfPath = ecfPath;
		this.paramsPath = paramsPath;
		this.logFilePath = logFilePath;
		this.isDone = false;
	}

	/**
	 * Secondary constructor, used only if path's are needed to be add manually.
	 */
	public Job() {
		isDone = false;
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
	public void finished() throws Exception {
		if (observer != null) {
			observer.update(this);
		}
	}

	@Override
	public String getMessage() {
		return logFilePath;
	}

}
