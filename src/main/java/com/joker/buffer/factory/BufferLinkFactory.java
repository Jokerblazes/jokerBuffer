package com.joker.buffer.factory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.joker.buffer.elimination.NeedsElimination;
import com.joker.buffer.elimination.Strategy;
import com.joker.buffer.entity.Buffer;
import com.joker.buffer.entity.BufferSet;

public class BufferLinkFactory {
	private static Map<Integer,BufferSet> map = new ConcurrentHashMap<>();
	private static Strategy strategy;
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
		map.put(0, new BufferSet(5,1));
		map.put(1, new BufferSet(20,5));
//		strategy = new SimpleElimination();
		strategy = new NeedsElimination();
	}

	public static Buffer freeListToBusyList(int typeCode) {
//		System.out.println(typeCode);
		// 1:先判断先要获取的缓冲区类型
		BufferSet set = map.get(typeCode);
//		System.out.println("set:"+set);
//		System.out.println("isEnough:"+!strategy.isEnoughBuffer(set));
		if (!strategy.isEnoughBuffer(set)) {
			strategy.elimination(set,typeCode);
		}
		return strategy.assignBuffer(set);
	}

	public static void readObject(Object msg,int typeCode) {
//		System.out.println("read---before"+ map.get(typeCode).testSize());
		Buffer buffer = freeListToBusyList(typeCode);
//		System.out.println("read---after"+ map.get(typeCode).testSize());
		buffer.addBufferObjects(msg);
	}

	public static void busyListToFreeList(Buffer buffer,int typeCode) {
		map.get(typeCode).addBufferToFree(buffer);
	}

}
