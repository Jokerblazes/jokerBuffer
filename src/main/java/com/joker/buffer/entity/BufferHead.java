package com.joker.buffer.entity;



public class BufferHead {
	private String databaseName;
	private int bufferSize;
	private int currentPosition;
//	protected AtomicReference<BufferState> commandState = new AtomicReference<BufferState>(BufferState.AVE);
	private BufferState flag;
	private int bForw;
	private int bBack;
	private int avForw;
	private int avBack;
	
	public BufferHead() {
		flag = BufferState.AVE;
	}
	public int getCurrentPosition() {
		return currentPosition;
	}

	public void setCurrentPosition(int currentPosition) {
		this.currentPosition = currentPosition;
	}

	public int getBufferSize() {
		return bufferSize;
	}

	public void setBufferSize(int bufferSize) {
		this.bufferSize = bufferSize;
	}

	public String getDatabaseName() {
		return databaseName;
	}

	public void setDatabaseName(String databaseName) {
		this.databaseName = databaseName;
	}

	public BufferState getFlag() {
		return flag;
	}

	public void setFlag(BufferState flag) {
		this.flag = flag;
	}

	public int getbForw() {
		return bForw;
	}

	public void setbForw(int bForw) {
		this.bForw = bForw;
	}

	public int getbBack() {
		return bBack;
	}

	public void setbBack(int bBack) {
		this.bBack = bBack;
	}

	public int getAvForw() {
		return avForw;
	}

	public void setAvForw(int avForw) {
		this.avForw = avForw;
	}

	public int getAvBack() {
		return avBack;
	}

	public void setAvBack(int avBack) {
		this.avBack = avBack;
	}
	
//	public void setState(BufferState state) {
//		this.commandState.set(state);
//	}

	enum BufferState {
//		BUSY,AVE,DELWR,WRITE,READ,WAIT
		FULL,AVE
	}
}
