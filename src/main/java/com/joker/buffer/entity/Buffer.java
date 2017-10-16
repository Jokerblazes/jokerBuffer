package com.joker.buffer.entity;

import java.util.Arrays;

import com.joker.buffer.entity.BufferHead.BufferState;

public class Buffer {
	private BufferHead head;
	private Object[] bufferObject;
	
	public void initBuffer() {
		this.head.setCurrentPosition(0);
	}
	
	public Buffer(int bufferSize) {
		this.head = new BufferHead();
		head.setBufferSize(bufferSize);
		this.bufferObject = new Object[head.getBufferSize()];
	}
	
	public BufferState getState() {
		return head.getFlag();
	}
	
	
	public boolean isFull() {
		boolean flag = head.getFlag() == BufferState.FULL;
		if (flag == true)
			head.setFlag(BufferState.AVE);
		return flag;
	}


	public Object[] getBufferObject() {
		return bufferObject;
	}
	
	public void addBufferObjects(Object bufferObject) {
		int currentPosition = head.getCurrentPosition();
		this.bufferObject[currentPosition] = bufferObject;
		currentPosition ++;
		head.setCurrentPosition(currentPosition);
		if (currentPosition+1 == head.getBufferSize()) {
			head.setFlag(BufferState.FULL);
		}
//		System.out.println("object:"+ bufferObject+"state:"+head.getFlag());
	}
	
	public int getPosition () {
		return head.getCurrentPosition();
	}
	

	@Override
	public String toString() {
		return "Buffer [head=" + "{bufferSize:" + head.getBufferSize() +",flag:" + head.getFlag() + ",currentPosition:" +head.getCurrentPosition() +"}" + ", bufferObject=" + Arrays.toString(bufferObject) + "]";
	}
	
	

	
	
	
}
