package com.company.designschema.factory.classPackage;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * 简单工厂
 */
public class SimpleFactory {
	public enum ProductTypeEnum {
		TestClass1,
		TestClass2;
	}

	/**
	 * 如果类多，会造成多个if-else，违反开闭原则
	 * 开闭原则--对象（类，模块，函数等等）应该对于扩展是开放的，但是对于修改是封闭的”，这意味着一个实体是允许在不改变它的源代码的前提下变更它的行为
	 * @param type
	 * @return
	 */
	public static TestClass createProduct(ProductTypeEnum type) {
		if (type.equals(ProductTypeEnum.TestClass1)) {
			return new TestClass1();
		}else if (type.equals(ProductTypeEnum.TestClass2)) {
			return new TestClass2();
		}else {
			return null;
		}
	}

	/**
	 * 使用反射,反射会降低程序的运行机制，性能要求高的场景应该避免改方法实现
	 */
	private static final Map<ProductTypeEnum,Class> activityIdMapByReflection = new HashMap<>();
	public static void addProductKeyByReflection(ProductTypeEnum type,Class product) {
		activityIdMapByReflection.put(type,product);
	}

	public static TestClass1 productByReflection(ProductTypeEnum type) throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
		Class productClass = activityIdMapByReflection.get(type);
		return (TestClass1) productClass.getDeclaredConstructor().newInstance();
	}


	/**
	 * 避免使用反射，map中直接存储对象
	 */
	private static final Map<ProductTypeEnum,TestClass> activityIdMap = new HashMap<>();
	public static void addProductKey(ProductTypeEnum type,TestClass product) {
		activityIdMap.put(type,product);
	}

	public static TestClass product(ProductTypeEnum type) throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
		TestClass testClass = activityIdMap.get(type);
		return  testClass;
	}
}
