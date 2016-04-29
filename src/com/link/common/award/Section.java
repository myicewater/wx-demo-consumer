package com.link.common.award;

public class Section {

	private int level;
	private int start;
	private int end;
	
	
	public Section(int level,int start,int end){
		this.level = level;
		this.start = start;
		this.end = end;
	}

	
	public int getLevel() {
		return level;
	}


	public void setLevel(int level) {
		this.level = level;
	}


	public int getStart() {
		return start;
	}


	public void setStart(int start) {
		this.start = start;
	}


	public int getEnd() {
		return end;
	}


	public void setEnd(int end) {
		this.end = end;
	}
	
	
}
