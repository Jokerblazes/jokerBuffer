package com.joker.buffer.elimination;

import java.util.List;

import com.joker.buffer.entity.Buffer;
import com.joker.buffer.entity.BufferSet;

/**
 * 抽象策略类
 * @author joker
 * {@link https://github.com/Jokerblazes/jokerBuffer.git}
 */
public abstract class Strategy {
	
	/**
	 * 获取空闲Buffer
	 * @param set
	 * @return
	 * @author joker
	 * {@link https://github.com/Jokerblazes/jokerBuffer.git}
	 */
	public abstract Buffer assignBuffer(BufferSet set);

	/**
	 * 缓冲区是否有足够大的空间
	 * @param set
	 * @return
	 * @author joker
	 * {@link https://github.com/Jokerblazes/jokerBuffer.git}
	 */
	public boolean isEnoughBuffer(BufferSet set) {
		if (set == null)
			// 抛异常
			return false;
		List<Buffer> freeList = set.getFreeBuffers();
		final int limit = set.getLimit();
//		System.out.println("size:"+set.getFreeBuffers().size() +":limit:"+limit);
		// 2:判断是否还存在空闲的此类型缓冲区
		if (freeList.size() < limit) {
			return false;
		}
		return true;
	}

	/**
	 * 释放缓冲
	 * @param set
	 * @author joker
	 * {@link https://github.com/Jokerblazes/jokerBuffer.git}
	 */
	public abstract void elimination(BufferSet set);
}
