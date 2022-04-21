## 工厂模式
    工厂模式可简单分为三类：

    * 简单工厂

    * 工厂方法

    * 抽象工厂
### 简单工厂模式
    工厂模式是对逻辑的封装，并且通过公共接口提供对象的实例化服务，这样在之后可以添加新类时只需要修改一点点代码就好
三种实现方法，代码如下：
```java
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
```
### 工厂方法模式
    工厂方法是对简单工厂的一种改进，工厂类直接抽象，需要具体特定化的逻辑代码被移交到子类中，这样不用修改工厂类
```java
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
```
其中TestClass1和TestClass2继承TestClass
```java
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
```
### 抽象工厂
    抽象工厂是抽象方法的一个延伸。工厂方法中只有一个抽象方法，要实现不同类对象需要创建具体的工厂方法中的子类来实例化，

    而抽象工厂是让一个工厂创建多个不同类型的类型
由下图可看，抽象工厂可分为，抽象工厂，具体工厂实现，抽象类
![抽象工厂](https://mmbiz.qpic.cn/mmbiz_jpg/uChmeeX1FpwXtrHCm7mUXRJDBSRMb8zt8YTuib7O3YoGLSvO24N9f7DDhFdqKt4XEmyAgCanWVp6CgLO5NJjF9w/640?wx_fmt=jpeg&wxfrom=5&wx_lazy=1&wx_co=1 "抽象工厂图")

### 如何去选择？(来自设计模式之美)
    当对象的创建逻辑比较复杂，不只是简单的 new 一下就可以，而是要组合其他类对象，做各种初始化操作的时候，我们推荐使用
    
    工厂方法模式，将复杂的创建逻辑拆分到多个工厂类中，让每个工厂类都不至于过于复杂。
