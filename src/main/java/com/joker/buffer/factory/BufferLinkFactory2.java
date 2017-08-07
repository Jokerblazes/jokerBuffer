package com.joker.buffer.factory;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.joker.buffer.entity.Buffer;
import com.joker.buffer.entity.BufferSet;
import com.joker.threadpool.JokerExecutor;
import com.joker.threadpool.JokerRunnable;

public class BufferLinkFactory2 {
	private static Map<Integer,BufferSet> map = new ConcurrentHashMap<>();
	// 1:初始化函数
	public static void initBufferList() {
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
		map.put(0, new BufferSet(5,2));
		map.put(1, new BufferSet(20,5));
	}

	public static Buffer freeListToBusyList(int typeCode) {
		// 1:先判断先要获取的缓冲区类型
		BufferSet set = map.get(typeCode);
		if (set == null) 
			//TODO 抛出异常
			return null;
		List<Buffer> busyList = set.getBusyBuffers();
		List<Buffer> freeList = set.getFreeBuffers();
		final int limit = set.getLimit();
		// 2:判断是否还存在空闲的此类型缓冲区
		if (freeList.size() < limit) {
			JokerExecutor executor = JokerExecutor.getInstance();
			busyList.forEach(buffer -> {
				JokerRunnable runnable = new JokerRunnable(buffer,typeCode);
				executor.execute(runnable);
			});
			executor.awaitTermination();
			busyList.removeAll(busyList);
		}
		final Buffer buffer = freeList.get(0);
		busyList.add(buffer);
		freeList.remove(0);
		return buffer;
	}

	public static void readObject(Object msg,int typeCode) {
		System.out.println("read---before"+ map.get(typeCode).testSize());
		Buffer buffer = freeListToBusyList(typeCode);
		System.out.println("read---after"+ map.get(typeCode).testSize());
		buffer.addBufferObjects(msg);
	}

	public static void busyListToFreeList(Buffer buffer,int typeCode) {
		System.out.println("busyListToFreeList:" + map.get(typeCode).testSize());
		map.get(typeCode).getFreeBuffers().add(buffer);
	}

}
