# JokeBuffer

## 功能

* 基于Linux文件读写缓冲设计的缓冲
* 提供了FIFO缓存算法
  * 缓存的所有数据批量写（优点：减少线程切换。缺点：单次操作耗时间）
  * 缓存数据分批次写（优点：单次耗时短。缺点：线程切换较多）



## DEMO

```java
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
```

