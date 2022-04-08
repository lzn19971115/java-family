package com.company.designschema.single;

/**
 * 饿汉式
 */
public class SingletonBadMash {
	private static SingletonBadMash singleInstance = new SingletonBadMash();

	private SingletonBadMash(){};

	public static SingletonBadMash getInstance() {
		return singleInstance;
	}
}
