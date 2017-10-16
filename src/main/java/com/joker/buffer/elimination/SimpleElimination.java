package com.joker.buffer.elimination;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.joker.buffer.entity.Buffer;
import com.joker.buffer.entity.BufferSet;
import com.joker.threadpool.JokerExecutor;
import com.joker.threadpool.JokerRunnable;

/**
 * 简单的淘汰策略-内存中缓存数据全部淘汰
 * @author joker
 *
 */
public class SimpleElimination extends Strategy{
	private final static Logger logger = LoggerFactory.getLogger(SimpleElimination.class);
	@Override
	public void elimination(BufferSet set) {
		List<Buffer> busyList = set.getBusyBuffers();
		logger.info("当前繁忙缓冲区 {}",busyList);
		// 2:判断是否还存在空闲的此类型缓冲区
		JokerExecutor executor = JokerExecutor.getInstance();
		busyList.forEach(buffer -> {
			JokerRunnable runnable = new JokerRunnable(buffer);
			executor.execute(runnable);
		});
		logger.info("等待所有子线程任务完成！");
		executor.awaitTermination();
		set.removeBusyList(busyList);
		logger.info("移除所有繁忙缓冲区！");
	}

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

	
	

}
