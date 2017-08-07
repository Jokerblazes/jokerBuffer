package com.joker.threadpool;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class JokerExecutor {
	private static JokerExecutor executor = new JokerExecutor();
	private JokerExecutor() {};
	
	public static JokerExecutor getInstance() {
		return executor;
	}
	private ThreadPoolExecutor pool = new ThreadPoolExecutor(5, 5, 5, TimeUnit.SECONDS, new SynchronousQueue<Runnable>(), new ThreadPoolExecutor.CallerRunsPolicy());
	
	public void execute(Runnable runnable) {
		pool.execute(runnable);
	}
	
	public void awaitTermination() {
		 try {
			pool.awaitTermination(20, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}  
	}
	
	
}
