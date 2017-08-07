package com.joker.buffer.entity;

import com.joker.buffer.entity.BufferHead.BufferState;

public class Buffer {
	private BufferHead head;
	private Object[] bufferObject;
	
	public Buffer(int bufferSize) {
		this.head = new BufferHead();
		head.setBufferSize(bufferSize);
		this.bufferObject = new Object[head.getBufferSize()];
	}
	
	public BufferState getState() {
		return head.getFlag();
	}


	public Object[] getBufferObject() {
		return bufferObject;
	}

	public void addBufferObjects(Object bufferObject) {
		int currentPosition = head.getCurrentPosition();
		this.bufferObject[currentPosition + 1] = bufferObject;
		currentPosition ++;
		head.setCurrentPosition(currentPosition);
	}
	
	public int getPosition () {
		return head.getCurrentPosition();
	}

	
	
	
}
