package com.joker.buffer.factory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.joker.buffer.elimination.NeedsElimination;
import com.joker.buffer.elimination.SimpleElimination;
import com.joker.buffer.elimination.Strategy;
import com.joker.buffer.entity.Buffer;
import com.joker.buffer.entity.BufferSet;

/**
 * 缓冲工厂
 * @author joker
 * {@link https://github.com/Jokerblazes/jokerBuffer.git}
 */
public class BufferLinkFactory {
	private final static Logger logger = LoggerFactory.getLogger(BufferLinkFactory.class);
	
	private static BufferSet bufferSet;
	private static Strategy strategy;
	
	/**
	 * 初始化函数
	 * 
	 * @author joker
	 * {@link https://github.com/Jokerblazes/jokerBuffer.git}
	 */
	public static void initBufferList(int size,int limit,Class strategyClass) {
		logger.info("初始化化大小为 {}，限制读限制大小 {}",size,limit);
		//读取XML文件
		//1:读取缓冲区类型数
		//2:获取每种类型的缓冲区大小和缓冲区释放限定
		//3:初始化缓冲区
		
		/**
		 * 模拟读取XML
		 * 假设有2种类型
		 * 编号0：缓冲区个数为10 限定值为3 每个缓冲区大小为1
		 * 编号1:缓冲区个数20 限定值为5 每个缓冲区大小为1
		 */
		bufferSet = new BufferSet(size, limit);
		if (strategyClass.getSuperclass() == Strategy.class)
			try {
				strategy = (Strategy) strategyClass.newInstance();
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
		else 
			throw new RuntimeException("未继承Strategy类！");
	}

	/**
	 * 空闲缓冲链转繁忙缓冲链
	 * @param typeCode
	 * @return
	 * @author joker
	 * {@link https://github.com/Jokerblazes/jokerBuffer.git}
	 */
	public static Buffer freeListToBusyList() {
		if (!strategy.isEnoughBuffer(bufferSet)) {
			logger.info("缓冲区没有足够空间，开始自动写缓冲！");
			strategy.elimination(bufferSet);
		}
		return strategy.assignBuffer(bufferSet);
	}

	/**
	 * 读对象
	 * @param msg
	 * @param typeCode
	 * @author joker
	 * {@link https://github.com/Jokerblazes/jokerBuffer.git}
	 */
	public static void readObject(Object msg) {
		Buffer buffer = freeListToBusyList();
		buffer.addBufferObjects(msg);
	}

	/**
	 * 繁忙缓冲链转空闲缓冲链
	 * @param buffer
	 * @param typeCode
	 * @author joker
	 * {@link https://github.com/Jokerblazes/jokerBuffer.git}
	 */
	public static void busyListToFreeList(Buffer buffer) {
		logger.info("繁忙缓冲区置与空闲缓冲区 {]",buffer);
		bufferSet.addBufferToFree(buffer);
	}

}
