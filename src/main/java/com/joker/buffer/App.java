package com.joker.buffer;

import com.joker.buffer.elimination.SimpleElimination;
import com.joker.buffer.factory.BufferLinkFactory;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
//		BufferLinkFactory2.initBufferList();
		BufferLinkFactory.initBufferList(5,1,SimpleElimination.class);
//		ThreadPoolExecutor pool = new ThreadPoolExecutor(5, 5, 5, TimeUnit.DAYS, new SynchronousQueue<Runnable>(), new ThreadPoolExecutor.CallerRunsPolicy());
//		Runnable runnable = new Runnable() {
//			public void run() {
//				BufferLinkFactory.readObject(new Object());
//			}
//		};
//		BufferLink link = new BufferLink();
//		link.initBufferList(10, 5);
//		for (int i =0 ; i < 5 ; i ++) {
//			String obj = "i:" + i;
//			BufferLinkFactory2.readObject(obj,0);
//		}
//		BufferLinkFactory3.readObject("i1",0);
//		BufferLinkFactory3.readObject("i2",0);
//		BufferLinkFactory3.readObject("i3",0);
//		BufferLinkFactory3.readObject("i4",0);
//		BufferLinkFactory3.readObject("i5",0);
		for(int i = 0 ; i < 80 ; i++) {
			String obj = "i:" + i;
			BufferLinkFactory.readObject(obj,0);
		}
		
//		Buffer b = BufferLinkFactory.freeListToBusyList();
//		Buffer b2 = BufferLinkFactory.freeListToBusyList();
//		System.out.println(b==b2);
		
//		final List<String> ss = new ArrayList<String>();
//		ss.add("1");
//		ss.add("2");
//		for (String a : ss ) {
//			System.out.println(a);
//			Thread t = new Thread(new Runnable() {
//				
//				public void run() {
//					System.out.println(ss.get(0));
//				}
//			});
//			ss.remove(a);
//		}
			
		
		
		
	}
}
