package com.joker.threadpool;

import com.joker.buffer.entity.Buffer;
import com.joker.buffer.factory.BufferLinkFactory3;

public class JokerRunnable implements Runnable {
	private Buffer buffer;
	private int typeCode;
	public JokerRunnable(Buffer buffer,int typeCode) {
		this.buffer = buffer;
		this.typeCode = typeCode;
	}
	public void run() {
		Object[] objects = buffer.getBufferObject();
		final int position = buffer.getPosition();
//		for (Object o : objects) 
//			System.out.println("插入数据库：" + o);
		
		for (int i = 0 ; i < position ;i ++) {
			Object o = objects[i];
			System.out.println("插入数据库：" + o);
			BufferLinkFactory3.busyListToFreeList(buffer,typeCode);
//			BufferLinkFactory2.busyListToFreeList(buffer,typeCode);
		}
		
	}
	
}
