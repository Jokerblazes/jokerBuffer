package com.joker.buffer.elimination;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.joker.buffer.entity.Buffer;
import com.joker.buffer.entity.BufferSet;
import com.joker.threadpool.JokerExecutor;
import com.joker.threadpool.JokerRunnable;

/**
 * 淘汰策略--缓写策略
 * @author joker
 *
 */
public class NeedsElimination extends Strategy {
	
	private final static Logger logger = LoggerFactory.getLogger(NeedsElimination.class);
	
	@Override
	public Buffer assignBuffer(BufferSet set) {
		final Buffer buffer = set.getBufferFromFree();
		logger.info("获得空闲缓冲区 {}",buffer);
		if (buffer.isFull()) {
			set.addBufferToBusy(buffer);
			set.removeBufferFromFree();
			logger.info("空闲缓冲区 {}移入繁忙区",buffer);
		}
		return buffer;
	}


	@Override
	public void elimination(BufferSet set) {
		List<Buffer> busyList = set.getBusyBuffers();
		List<Buffer> tempList = new ArrayList<>();
		final int limit = set.getLimit();
		logger.info("当前繁忙缓冲区 {}",busyList);
		// 2:判断是否还存在空闲的此类型缓冲区
		JokerExecutor executor = JokerExecutor.getInstance();
		for (int i = 0 ; i < limit ; i++) {
			Buffer buffer = busyList.get(i);
			tempList.add(buffer);
			JokerRunnable runnable = new JokerRunnable(buffer);
			executor.execute(runnable);
		}
		logger.info("等待子线程完成任务,任务缓冲列表 {}",tempList);
		executor.awaitTermination();
		busyList.removeAll(tempList);
		logger.info("移除繁忙缓冲区 {}",tempList);
	}

}
