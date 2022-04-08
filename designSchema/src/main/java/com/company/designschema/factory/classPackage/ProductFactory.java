package com.company.designschema.factory.classPackage;

/**
 * 工厂
 */
public class ProductFactory extends FactoryMethod {
	@Override
	protected TestClass createProduct(String name) {
		if (EnumProductType.activityOne.getName().equals(name)) {
			//业务逻辑代码
			return new TestClass1();
		} else if (EnumProductType.activityTwo.getName().equals(name)) {
			//业务逻辑代码
			return new TestClass2();
		}
		return null;
	}
}
