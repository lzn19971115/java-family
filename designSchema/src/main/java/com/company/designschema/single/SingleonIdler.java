package com.company.designschema.single;

/**
 * 懒汉式
 */
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
