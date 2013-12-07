package hr.fer.zemris.ecf.log;

import java.util.ArrayList;

public class Generation {
	
	public int id;
	public int elapsedTime;
	public ArrayList<Deme> demes;
	public Population population;
	
	public Generation(){
		demes = new ArrayList<>();
	}
	public Generation(int id){
		this.id=id;
		demes = new ArrayList<>();
	}
	

}
