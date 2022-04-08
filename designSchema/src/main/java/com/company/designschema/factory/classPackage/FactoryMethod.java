package com.company.designschema.factory.classPackage;

/**
 * 抽象工厂方法（模板）
 */
public abstract class FactoryMethod {
	protected abstract TestClass createProduct(String name);

	public TestClass Product(String activity, String name) {
		TestClass product = createProduct(activity);
		product.setProductName(name);
		return product;
	}



	public enum EnumProductType {
		activityOne("one"),
		activityTwo("two");

		private String name;

		EnumProductType(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
	}
}
