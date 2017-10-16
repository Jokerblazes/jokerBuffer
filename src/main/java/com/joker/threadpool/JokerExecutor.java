package com.joker.threadpool;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class JokerExecutor {
	private static JokerExecutor executor = new JokerExecutor();
	private JokerExecutor() {};
	
	public static JokerExecutor getInstance() {
		return executor;
	}
	private ThreadPoolExecutor pool = new ThreadPoolExecutor(10, 20,20 , TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(), new ThreadPoolExecutor.CallerRunsPolicy());
	
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
