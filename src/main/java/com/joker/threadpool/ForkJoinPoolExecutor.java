package com.joker.threadpool;

import java.util.concurrent.ForkJoinPool;

public class ForkJoinPoolExecutor {
	private  ForkJoinPool pool = new ForkJoinPool();
	
	private static ForkJoinPoolExecutor executor = new ForkJoinPoolExecutor();
	private ForkJoinPoolExecutor() {};
	
	public static ForkJoinPoolExecutor getInstance() {
		return executor;
	}
	
	public void execute(Runnable runnable) {
		pool.submit(runnable);
	}
}
