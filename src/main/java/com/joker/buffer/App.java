package com.joker.buffer;

import java.lang.reflect.Method;

import com.joker.buffer.elimination.SimpleElimination;
import com.joker.buffer.factory.BufferLinkFactory;
import com.joker.support.Invocation;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		BufferLinkFactory.initBufferList(5,1,SimpleElimination.class);
		App app = new App();
		Method method = null;
		try {
			method = App.class.getDeclaredMethod("test", null);
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		Invocation invocation = new Invocation(method, app, null);
		for(int i = 0 ; i < 80 ; i++) {
			BufferLinkFactory.readObject(invocation);
		}
	}
	
	public void test() {
		System.out.println("test");
	}
}
