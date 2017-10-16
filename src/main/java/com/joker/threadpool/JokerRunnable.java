package com.joker.threadpool;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.joker.buffer.entity.Buffer;
import com.joker.buffer.factory.BufferLinkFactory;
import com.joker.support.Invocation;

public class JokerRunnable implements Runnable {
	private final static Logger logger = LoggerFactory.getLogger(JokerRunnable.class);
	
	private Buffer buffer;
	public JokerRunnable(Buffer buffer) {
		this.buffer = buffer;
		logger.info("子线程 {}处理缓冲区 {}",Thread.currentThread(),buffer);
	}
	public void run() {
		Object[] objects = buffer.getBufferObject();
		final int position = buffer.getPosition();
		logger.info("开始写缓存!");
		for (int i = 0 ; i < position ;i ++) {
			Object o = objects[i];
			if (!(o instanceof Invocation)) {
				throw new RuntimeException("非Invocation对象！");
			} 
			Invocation invocation = (Invocation) o;
			Method method = invocation.getMethod();
			Object[] args = invocation.getArgs();
			Object bean = invocation.getController();
			try {
				method.invoke(bean, args);
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		logger.info("将缓冲区置空！");
		buffer.initBuffer();
		BufferLinkFactory.busyListToFreeList(buffer);
		
	}
	
}
