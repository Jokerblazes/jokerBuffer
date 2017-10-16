package com.joker.buffer.elimination;

import java.util.ArrayList;
import java.util.List;

import com.joker.buffer.entity.Buffer;
import com.joker.buffer.entity.BufferSet;
import com.joker.threadpool.JokerExecutor;
import com.joker.threadpool.JokerRunnable;

/**
 * LRU淘汰策略
 * @author joker
 *
 */
public class NeedsElimination extends Strategy {

	@Override
	public Buffer assignBuffer(BufferSet set) {
//		List<Buffer> busyList = set.getBusyBuffers();
//		List<Buffer> freeList = set.getFreeBuffers();
		final Buffer buffer = set.getBufferFromFree();
		if (buffer.isFull()) {
			set.addBufferToBusy(buffer);
			set.removeBufferFromFree();
		}
//		busyList.add(buffer);
//		freeList.remove(0);
		return buffer;
	}


	@Override
	public void elimination(BufferSet set, int typeCode) {
		List<Buffer> busyList = set.getBusyBuffers();
		List<Buffer> tempList = new ArrayList<>();
		final int limit = set.getLimit();
		// 2:判断是否还存在空闲的此类型缓冲区
		JokerExecutor executor = JokerExecutor.getInstance();
		for (int i = 0 ; i < limit ; i++) {
			Buffer buffer = busyList.get(i);
			tempList.add(buffer);
			JokerRunnable runnable = new JokerRunnable(buffer,typeCode);
			executor.execute(runnable);
		}
		executor.awaitTermination();
		busyList.removeAll(tempList);
	}

}
