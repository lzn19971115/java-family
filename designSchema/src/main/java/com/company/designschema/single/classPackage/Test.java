package com.company.designschema.single.classPackage;

public class Test {
	public static void main(String[] args) {
		SingleonIdler instance = SingleonIdler.getInstance();
		SingleonIdler instance1 = SingleonIdler.getInstance();
		System.out.println(instance1 == instance);

		SingletonBadMash singletonBadMash = SingletonBadMash.getInstance();
		SingletonBadMash badMash = SingletonBadMash.getInstance();
		System.out.println(singletonBadMash == badMash);

	}
}
