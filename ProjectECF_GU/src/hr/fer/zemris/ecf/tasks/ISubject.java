package hr.fer.zemris.ecf.tasks;

public interface ISubject {
	
	public void setObserver(IObserver observer);
	
	public void removeObserver();
	
	public void finished();
	
	public String getMessage();
	
}
