package com.joker.buffer.elimination;

import java.util.List;

import com.joker.buffer.entity.Buffer;
import com.joker.buffer.entity.BufferSet;
import com.joker.threadpool.JokerExecutor;
import com.joker.threadpool.JokerRunnable;

public class SimpleElimination extends Strategy{

	@Override
	public void elimination(BufferSet set,int typeCode) {
		List<Buffer> busyList = set.getBusyBuffers();
		// 2:判断是否还存在空闲的此类型缓冲区
		JokerExecutor executor = JokerExecutor.getInstance();
		busyList.forEach(buffer -> {
			JokerRunnable runnable = new JokerRunnable(buffer,typeCode);
			executor.execute(runnable);
		});
		executor.awaitTermination();
		busyList.removeAll(busyList);
	}

	@Override
	public Buffer assignBuffer(BufferSet set) {
		List<Buffer> busyList = set.getBusyBuffers();
		List<Buffer> freeList = set.getFreeBuffers();
		final Buffer buffer = freeList.get(0);
		busyList.add(buffer);
		freeList.remove(0);
		return buffer;
	}

//	@Override
//	public boolean isEnoughBuffer(BufferSet set) {
//		if (set == null) 
//			//抛异常
//			return false;
//		List<Buffer> freeList = set.getFreeBuffers();
//		final int limit = set.getLimit();
//		// 2:判断是否还存在空闲的此类型缓冲区
//		if (freeList.size() < limit) {
//			return false;
//		}
//		return true;
//	}
	
	

}
