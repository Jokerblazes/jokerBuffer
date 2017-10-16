package com.joker.buffer.factory;
//package com.joker.JokerBuffer.entity;
//
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Vector;
//
//import com.joker.JokerBuffer.entity.BufferHead.BufferState;
//import com.joker.threadpool.JokerExecutor;
//import com.joker.threadpool.JokerRunnable;
//
//public class BufferLinkFactory {
//	private static List<Buffer> busyList = new Vector<Buffer>();
//	private static List<Buffer> freeList = new Vector<Buffer>();
//	
//	//1:初始化函数
//	public static void initBufferList(int bufferNumber,int bufferSize) {
//		BufferHead head = new BufferHead();
//		head.setBufferSize(bufferSize);
//		for (int i = 0 ; i < bufferNumber ; i++) {
//			freeList.add(new Buffer(bufferSize));
//		}
//	}
//	
//	public static Buffer freeListToBusyList() {
//		//1:先判断先要获取的缓冲区类型
//		//2:判断是否还存在空闲的此类型
////		synchronized (busyList) {
//		if (freeList.size() < 2) {
//			JokerExecutor executor = JokerExecutor.getInstance();
////			Iterator<Buffer> bufferIter = busyList.iterator();
////			while (bufferIter.hasNext()) {    
////				Buffer buffer = bufferIter.next();
////				JokerRunnable runnable = new JokerRunnable(buffer);
////				executor.execute(runnable);
//////				bufferIter.remove();
////			} 
//			
//			busyList.forEach(buffer -> {
////				System.out.println("busyList.size():"+busyList.size());
//				JokerRunnable runnable = new JokerRunnable(buffer);
//				executor.execute(runnable);
//            });
//			executor.awaitTermination();
//			busyList.removeAll(busyList);
////			synchronized (busyList) {
////				System.out.println("busyList.size():"+busyList.size());
////				for (Buffer buffer : busyList) {
//////					Object[] objects = buffer.getBufferObject();
////					JokerRunnable runnable = new JokerRunnable(buffer);
////					executor.execute(runnable);
////				}
////			}
////			
////			busyList.removeAll(busyList);
//		}
//		final Buffer buffer = freeList.get(0);
//		busyList.add(buffer);
//		freeList.remove(0);
//		return buffer;
//		//1:从freeList头部拿到一个缓冲
//		//2:删除freeList中的缓冲
//		//3:放入busyList的尾部
//	}
//	
//	public static void readObject(Object msg) {
//		System.out.println("read---before--busyList.size--" + busyList.size() + "--freeList.size--" + freeList.size() );
//		Buffer buffer = freeListToBusyList();
//		System.out.println("read---after--busyList.size--" + busyList.size() + "--freeList.size--" + freeList.size() );
//		buffer.addBufferObjects(msg);
//	}
//	
//	public static void busyListToFreeList(Buffer buffer,int typeCode) {
//		//		final Buffer buffer = busyList.get(0);
//		//		freeList.add(buffer);
//		//		while (true) {
//		//			//等待知道buffer中的状态变成AVE状态
//		//			if (buffer.getState().compareTo(BufferState.AVE) > 0) {
//		//				break;
//		//			}
//		//		}
//		//		busyList.remove(0);
//		//1:从busyList头部拿到一个缓冲
//		//2:删除freeList中的缓冲（暂未考虑）
//		//3:放入freeList的尾部
//		System.out.println("free size:" + freeList.size() + "------ busyList:" +busyList.size() );
//		freeList.add(buffer);
//	}
//	
//	
//}
