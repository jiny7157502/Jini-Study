# 람다식과 함수형 인터페이스(Lambda Expression & Functional Interface)

> Stream 연산들은 매개변수로 함수형 인터페이스를 받도록 되어있다. 그리고 람다식은 반환값으로 함수형 인터페이스를 반환하고 있다. 그렇기 때문에 우리는 Stream API를 정확히 이해하기위해 람다식과 함수형 인터페이스를 알고 있어야 한다.

## 람다식이란?

### 람다식이란?
람다식이란 <b>함수를 하나의 식으로 표현한 것</b>이다. 함수를 람다식으로 표현하면 메소드의 이름이 필요없기 때문에, 람다식은 익명 함수(Anonymous Function)의 한 종류라고 볼 수 있다. 익명 함수란 함수의 이름이 없는 함수로, 익명함수들은 모두 일급 객체이다. 일급 객체인 함수는 변수처럼 사용가능하며 매개 변수로 전달이 가능하는 등의 특징을 가지고 있다. 
<br>
람다 방식은 메소드 명이 불필요하며, 다음과 같이 괄호()와 화살표-> 를 이용하여 함수를 선언하게 된다.
```java
() -> "hello World!";
```
이렇게 람다식이 등장하게 된 이유는 <b>불필요한 코드를 줄이고, 가독성을 높이기 위함</b>이다. 그렇기 때문에 <b>함수형 인터페이스의 인스턴스를 생성하여 함수를 변수처럼 선언하는 람다식</b>에서는 메소드의 이름이 불필요하다고 여겨져서 이를 사용하지 않는다. 대신 컴파일러가 문맥을 살펴 타입을 추론한다. 또한 람다식으로 선언된 함수는 1급 객체이기 때문에 Stream API의 매개변수로 전달이 가능해진다.

### 람다식의 특징
1. 람다식의 특징
- 람다식 내에서 사용되는 지역변수는 final이 붙지 않아도 상수로 간주된다.
- 람다식으로 선언된 변수명은 다른 변수명과 중복될 수 없다.

2. 람다식의 장점
- 코드를 간결하게 만들 수 있다.
- 식에 개발자의 의도가 명확히 드러나 가독성이 높아진다.
- 함수를 만드는 과정없이 한번에 처리할 수 있어 생산성이 높아진다.
- 병렬 프로그래밍이 용이하다.

3. 람다식의 단점
- 람다를 사용하면서 만든 무명함수는 재사용이 불가능하다.
- 디버깅이 어렵다.
- 람다를 남발하면 비슷한 함수가 중복 생성되어 코드가 지저분해질 수 있다.
- 재귀로 만들 경우에 부적합하다.

## 함수형 인터페이스란?

### 함수형 인터페이스란?
> Java는 기본적으로 객체지향 언어이기 때문에 순수 함수와 일반 함수를 다르게 취급하고 있으며, Java에서는 이를 구분하기 위해 함수형 인터페이스가 등장하게 되었다. 함수형 인터페이스란 <b>함수를 1급 객체처럼 다룰 수 있게 해주는 어노테이션으로, 인터페이스에 선언하여 단 하나의 추상 메소드만을 갖도록 제한하는 역할</b>을 한다. 함수형 인터페이스를 사용하는 이유는 Java의 람다식이 함수형 인터페이스를 반환하기 때문이다.

- 함수형 인터페이스의 등장으로 우리는 함수를 변수처럼 선언할 수 있게 되었고, 코드 역시 간결하게 작성할 수 있게 되었다. 함수형 인터페이스를 구현하기 위해서는 인터페이스를 개발하여 그 내부에는 1개 뿐인 abstract 함수를 선언하고, 위에는 @FunctionalInterface 어노테이션을 붙여주면 된다.

```java
@FunctionalInterface
interface MyLambdaFunction {
	int max(int a, int b);
}

public class FunctionalInterface_ex01 {
	public static void main(String[] args) {
		// 기존의 익명함수
//		System.out.println(new MyLambdaFunction() {
//			public int max(int a, int b) {
//				return a > b ? a : b;
//			}
//		}.max(3, 5));
		
		// 람다식을 이용한 익명함수
		MyLambdaFunction lambdaFunction = (int a, int b) -> a > b ? a : b;
		System.out.println(lambdaFunction.max(3, 5));
	}
}
```

여기서 놓치지 말아야 할 것은 <b>람다식으로 생성된 순수 함수는 함수형 인터페이스로만 선언이 가능하다는 점이다. 또한 @FunctionalInterface는 해당 인터페이스가 1개의 함수만을 갖도록 제한하기 때문에, 여러 개의 함수를 선언하면 컴파일 에러가 발생할 것이라는 점이다.</b>

### Java에서 제공하는 함수형 인터페이스
> Java에는 자주 사용될 것 같은 함수형 인터페이스가 이미 정의되어 있으며, 총 4가지 함수형 인터페이스를 지원하고 있다. 
- Supplier<T>
- Consumer<T>
- Function<T>
- Predicate<T>

1. Supplier<T>
- Supplier는 매개변수 없이 반환값 만을 갖는 함수형 인터페이스이다.
- Supplier는 T get()을 추상 메소드로 갖고 있다.

```java
@FunctionalInterface
interface Supplier<T> {
	T get();
}

public class FunctionalInterface_ex2 {
	public static void main(String[] args) {
		Supplier<String> supplier = () -> "Hello World!";
		System.out.println(supplier.get());
	}
}
```

2. Consumer <T>
- Consumer는 객체 T를 매개변수로 받아서 사용하며, 반환값은 없는 함수형 인터페이스이다.
- Consumer는 void accept(T t)를 추상메소드로 갖는다.
- 또한 Consumer는 andThen이라는 함수를 제공하고 있는데, 이를 통해 하나의 함수가 끝난 후 다음 Consumer를 연쇄적으로 이용할 수 있다. 아래의 예제에서는 먼저 accept로 받아들인 Consumer를 먼저 처리하고, andThen으로 받은 두 번째 Consumer를 처리하고 있다. 함수형에서 함수는 값의 대입 또는 변경 등이 없기 때문에 첫 번째 Consumer가 split으로 데이터를 변경하였다 하더라도 원본의 데이터는 유지된다. 
```java
@FunctionalInterface
interface Consumer<T> {
	void accept(T t);
	
	default Consumer<T> andThen(Consumer <? super T> after) {
		Objects.requireNonNull(after);
		return (T t) -> { accept(t); after.accept(t); };
	}
}

public class FunctionalInterface_ex3 {
	public static void main(String[] args) {
		Consumer<String> consumer = (str) -> System.out.println(str.split(" ")[0]);
		consumer.andThen(System.out::println).accept("Hello World");
	} 
}
```

3. Function <T, R>
- Function은 객체 T를 매개변수로 받아서 처리한 후 R로 반환하는 함수형 인터페이스이다.
- Function은 R apply(T t)를 추상메소드로 갖는다.
- 또한 Function은 Consumer와 마찬가지로 andThen을 제공하고 있으며, 추가적으로 compose를 제공하고 있다. 앞에서 andThen은 첫 번째 함수가 실행된 이후에 다음 함수를 연쇄적으로 실행하도록 연결해준다고 하였다. 하지만 compose는 첫 번째 함수 실행 이전에 먼저 함수를 실행하여 연쇄적으로 연결해준다는 점에서 차이가 있다.
- 또한 identity 함수가 존재하는데, 이는 자기 자신을 반환하는 static 함수이다.

```java
@FunctionalInterface
interface Function<T, R> {
	R apply(T t);
	
	default <V> Function<V, R> compose(Function <? super V, ? extends T> before) {
		Objects.requireNonNull(before);
		return (V v) -> apply(before.apply(v));
	}
	
	default <V> Function<T, V> andThen(Function <? super R, ? extends V> after) {
		Objects.requireNonNull(after);
		return (T t) -> after.apply(apply(t));
	}
	
	static <T> Function<T, T> identity() {
		return t -> t;
	}
}

public class FunctionalInterface_ex4 {
	public static void main(String[] args) {
		Function<String, Integer> function = str -> str.length();
		function.apply("Hello World");
	}
}
```

4. Predicate <T>
- Predicate는 객체 T를 매개 변수로 받아 처리한 후 Boolean을 반환한다.
- Predicate는 Boolean test(T t)을 추상 메소드로 갖고 있다.

## 메소드 참조(Method Reference)
메소드 참조란 함수형 인터페이스를 람다식이 아닌 일반 메소드를 참조시켜 선언하는 방법이다. 일반 메소드를 참조하기 위해서는 다음의 3가지 조건을 만족해야 한다.
- 함수형 인터페이스의 매개변수 타입 = 메소드의 매개변수 타입
- 함수형 인터페이스의 매개변수 개수 = 메소드의 매개변수 개수
- 함수형 인터페이스의 반환형 = 메소드의 반환형

<br>
참조가능한 메소드는 일반 메소드, Static 메소드, 생성자가 있으며, 클래스 이름 :: 매소드 이름 으로 참조할 수 있다. 이렇게 참조를 하면 함수형 인터페이스로 반환이 된다.

1. 일반 메소드 참조
ex) String의 length 함수는 매개변수가 없으며, 반환형이 int로 동일하기 때문에 String :: length로 다음과 같이 메소드 참조를 적용할 수 있다.
```java
// 기존의 람다식
Function<String, Integer> function = (str) -> str.length();
function.apply("Hello World");

// 메소드 참조로 변경
Function<String, Integer> function = String::length;
function.apply("Hello World");
```

ex) System.out.println() 메소드는 반환형이 void이며, 파라미터로 String을 받는 메소드이다. 
```java
// 일반 메소드를 참조하여 Consumer를 선언한다.
Consumer<String> consumer = System.out.println;
consumer.accept("Hello World");

// 메소드 참조를 통해 Consumer를 배개변수로 받는 forEach를 쉽게 사용할 수 있다.
List<String> list = Arrays.asList("red", "orange", "yellow", "green", "blue");
list.forEach(System.out::println);

// interface Iterable<T>
default void forEach(Consumer<? super T> action) {
	Objects.requireNonNull(action)l
	for (T t : this) {
		action.accept(t);
	}
}
```

2. Static 메소드 참조
ex) Objects의 isNull은 반환값이 Boolean이며, 매개변수 값은 1개이고, 매개 변수가 Object이므로 Predicate로 다음과 같이 메소드 참조가 가능하다.
```java
Predicate<Boolean> predicate = Objects::isNull;

//isNull 함수
public static boolean isNull(Object obj) {
	return obj == null;
}
```

3. 생성자 참조
생성자는 new로 생성해주므로 클래스이름::new 로 참조할 수 있다. Supplier는 매개 변수가 없이 반환값만을 갖는 인터페이스이기 때문에, 매개변수 없이 String 객체를 새롭게 생성하는 String의 생성자를 참조하여 Supplier로 선언할 수 있다.
```java
Supplier<String> supplier = String::new;
```

### 참고사이트
> https://mangkyu.tistory.com/113