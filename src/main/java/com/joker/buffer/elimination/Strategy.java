package com.joker.buffer.elimination;

import java.util.List;

import com.joker.buffer.entity.Buffer;
import com.joker.buffer.entity.BufferSet;

public abstract class Strategy {

	public abstract Buffer assignBuffer(BufferSet set);

	public boolean isEnoughBuffer(BufferSet set) {
		if (set == null)
			// 抛异常
			return false;
		List<Buffer> freeList = set.getFreeBuffers();
		final int limit = set.getLimit();
		// 2:判断是否还存在空闲的此类型缓冲区
		if (freeList.size() < limit) {
			return false;
		}
		return true;
	}

	public abstract void elimination(BufferSet set, int typeCode);
}
