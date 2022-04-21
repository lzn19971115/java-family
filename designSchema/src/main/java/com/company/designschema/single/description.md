## 单例模式
### 1.概述
    一个JVM中只有一个该类型对象
### 2. 为何使用
    * 某些类频繁创建对象，花销过大

    * 省去了new操作，降低了系统压力
### 3.使用场景
    核心交易引擎，如果系统有多个对象，整个系统就乱了
### 4.分类
#### 1）饿汉式
    类初始化时，创建对象，使用于线程安全且单例内存较小的场景
代码如下：
```java
    public class SingletonBadMash {
      private static SingletonBadMash singleInstance = new SingletonBadMash();
    
      private SingletonBadMash(){};
    
      public static SingletonBadMash getInstance() {
        return singleInstance;
      }
    }
```

#### 2）懒汉式
    什么时候用，什么时候创建
代码如下：
```java
public class SingleonIdler {
	private static SingleonIdler singleonIdler = null;

	private SingleonIdler() {
	}

	/**
	 * 此方法线程不安全，多线程下有可能造成多个对象
	 * @return
	 */
	public static synchronized SingleonIdler getInstance() {
		if (singleonIdler == null) {
			singleonIdler = new SingleonIdler();
		}
		return singleonIdler;
	}

	/**
	 * 线程安全
	 * @return
	 */
	public static synchronized SingleonIdler getSafeInstance() {
		if (singleonIdler == null) {
			singleonIdler = new SingleonIdler();
		}
		return singleonIdler;
	}
}
```