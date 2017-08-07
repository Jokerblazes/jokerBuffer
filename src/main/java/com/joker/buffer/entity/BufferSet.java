package com.joker.buffer.entity;

import java.util.List;
import java.util.Vector;

public class BufferSet {
	private List<Buffer> busyBuffers;
	private List<Buffer> freeBuffers;
	private int limit;//限制
	
	
	public BufferSet(int bufferLength,int limit) {
		this(bufferLength, limit, 5);
	}
	public BufferSet(int bufferLength,int limit,int bufferSize) {
		this.limit = limit;
		freeBuffers = new Vector<Buffer>();
		busyBuffers = new Vector<Buffer>();
		for (int i =0 ; i < bufferLength ; i++) {
			freeBuffers.add(new Buffer(bufferSize));
		}
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public List<Buffer> getBusyBuffers() {
		return busyBuffers;
	}
	public void setBusyBuffers(List<Buffer> busyBuffers) {
		this.busyBuffers = busyBuffers;
	}
	public List<Buffer> getFreeBuffers() {
		return freeBuffers;
	}
	public void setFreeBuffers(List<Buffer> freeBuffers) {
		this.freeBuffers = freeBuffers;
	}
	
	public String testSize() {
		return "--busyList.size--" + busyBuffers.size() + "--freeList.size--" + freeBuffers.size();
	}
	
//	public int size() {
//		return buffers.size();
//	}
	
	
	
}
