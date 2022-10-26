# Stream API의 활용 및 사용법 1

## 1. Stream 생성하기
> Stream API를 사용하기 위해서는 먼저 Stream을 생성해주어야 한다. 타입에 따라 Stream을 생성하는 방법이 다른데, 여기서는 Collection과 Array에 대해서 Stream을 생성한다.

### 1. Collection의 Stream 생성
- Collection 인터페이스에는 stream()이 정의되어 있기때문에, Collection 인터페이스를 구현한 객체들(List, Set 등)은 모두 이 메소드를 이용해 Stream을 생성할 수 있다. stream()을 사용하면 해당 Collection의 객체를 소스로 하는 Stream을 반환한다.

### 2. 배열의 Stream 생성
- 배열의 원소들을 소스로 하는 Stream을 생성하기 위해서는 Stream의 of 메소드 또는 Arrays의 stream 메소드를 사용하면 된다.

### 3. 원시 Stream 생성
- int와 long 그리고 double과 같은 원시 자료형들을 사용하기 위한 특수한 종류의 Stream(IntStream, LongStream, DoubleStream)들도 사용할 수 있으며, IntStream과 같은 경우 range()함수를 사용하여 기존의 for문을 대체할 수 있다.

### Stream 생성 예시 코드
```java
// Collection의 Stream 생성
List<String> list = Arrays.asList("a", "b", "c");
Stream(String) listStream = list.stream();

// 배열의 Stream 생성
Stream<String> stream = Stream.of("a", "b", "c"); //가변인자
Stream<String> stream = Stream.of(new String[] {"a", "b", "c"});
Stream<String> stream = Arrays.stream(new String[] {"a", "b", "c"});
Stream<String> stream = Arrays.stream(new String[] {"a", "b", "c"}, 0, 3);

// 원시 Stream 생성
// 4이상 10 이하의 숫자를 갖는 IntStream
IntStream stream = IntStream.range(4, 10);
```

## Stream 가공하기(중간연산)
> 생성한 Stream 객체에서 요소들을 가공하기 위해서는 중간연산이 필요하다. 가공하기 단계의 파라미터로는 앞서 설명하였던 함수형 인터페이스들이 사용되며, 여러 개의 중간연산이 연결되도록 반환값으로 Stream을 반환한다.

### 1. 필터링 - Filter
- Filter는 Stream에서 조건에 맞는 데이터만을 정제하여 더 작은 컬렉션을 만들어내는 연산이다. Java에서는 filter 함수의 인자로 함수형 인터페이스 Predicate를 받고 있기 때문에, boolean을 반환하는 람다식을 작성하여 filter 함수를 구현할 수 있다.

### 2. 데이터 변환 - Map
- Map은 기존의 Stream 요소들을 변환하여 새로운 Stream을 형성하는 연산이다. 저장된 값을 특정한 형태로 변환하는데 주로 사용되며, Java에서는 map 함수의 인자로 함수형 인터페이스 function을 받고 있다. 예를 들어 String을 요소들로 갖는 Stream을 모두 대문자 String의 요소들로 변환하고자 할 때 map을 이용할 수 있다.

### 3. 정렬 - sorted
- Stream의 요소들을 정렬하기 위해서는 sorted를 사용해야하며, 파라미터로 Comparator를 넘길 수도 있다. Comparator 인자 없이 호출할 경우에는 오름차순으로 정렬이 되며, 내림차순으로 정렬하기 위해서는 Comparator의 reverseOrder를 이용하면 된다. 

### 4. 중복 제거 - Distinct
- Stream의 요소들에 중복된 데이터가 존재하는 경우, 중복을 제거하기 위해 distinct를 사용할 수 있다. distinct는 중복된 데이터를 검사하기 위해 Object의 equals() 메소드를 사용한다. 또한 equals와 hashCode를 오버라이드하지 않으면, 중복된 데이터가 제거되지 않는다.

### 5. 특정 연산 수행 - Peek
- Stream의 요소들을 대상으로 Stream에 영향을 주지 않고 특정 연산을 수행하기 위한 peek 함수가 존재한다. '확인해본다'라는 뜻을 지닌 peek 단어처럼, peek 함수는 Stream의 각각의 요소들에 대해 특정 작업을 수행할 뿐 결과에 영향을 주지 않는다. 또한 peek 함수는 파라미터로 함수형 인터페이스 Consumer를 인자로 받는다. 

### 6. 원시 Stream <-> Stream
- 작업을 하다 보면 일반적인 Stream 객체를 원시 Stream으로 바꾸거나 그 반대로 하는 작업이 필요한 경우가 있다. 이러한 경우를 위해서, 일반적인 Stream 객체는 mapToInt(), mapToLong(), mapToDouble()이라는 특수한 Mapping 연산을 지원하고 있으며, 그 반대로 원시객체는 mapToObject를 통해 일반적인 Stream 객체로 바꿀 수 있다.

### Stream 가공하기(중간연산) 예시 코드
```java
// 필터링 - Filter
Stream<String> stream =
    list.stream()
    .filter(name -> name.contains("a"));

// 데이터 변환 - Map
Stream<String> stream = 
    names.stream()
    .map(s -> s.toUpperCase());

// 파일 Stream 변경 - Map
Stream<File> fileStream = Stream.of(new File("Test1.java"), new File("Test2.java"), new File("Test3.java"));
// Stream<File> --> Stream<String> 변환
Stream<String> fileNameStream = fileStream.map(File::getName);

// 정렬 - sorted
List<String> list = Arrays.asList("Java", "Scala", "Groovy", "Python", "Go", "Swift");

Stream<String> stream = list.stream();
    .sorted();
// [Go, Groovy, Java, Python, Scala, Swift]

Stream<String> stream = list.stream()
    .sorted(Comparator.reverseOrder());
// [Swift, Scala, Python, Java, Groovy, Go]

// 중복 제거 - Distinct
List<String> list = Arrays.asList("Java", "Scala", "Groovy", "Python", "Go", "Swift", "Java");

Stream<String> stream = list.stream()
    .distinct();
// [Java, Scala, Groovy, Python, Go, Swift]

// 중복 제거 - equals와 hashcode 오버라이드
public class Employee {
    private String name;

    public Employee(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(name, employee.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}

public class Main {
    public static void main(String[] args) {
        Employee e1 = new Employee("MangKyu");
        Employee e2 = new Employee("MangKyu");
        List<Employee> employees = new ArrayList<>();
        employees.add(e1);
        employees.add(e2);

        int size = employees.stream().distinct().collect(Collectors.toList()).size();
        System.out.println(size);
        // 1
    }
}

// 특정 연산 수행 - Peek(어떤 stream의 요소들을 중간에 출력할 때)
int sum = IntStream.of(1, 3, 5, 7, 9)
    .peek(System.out::println)
    .sum();

// 원시 Stream <-> Stream
// IntStream -> Stream<Integer>
IntStream.range(1, 4)
    .mapToObj(i -> "a" + i)

// Stream<Double> -> IntStream -> Stream<String>
Stream.of(1.0, 2.0, 3.0)
    .mapToInt(Double::intValue)
    .mapToObj(i -> "a" + i)
```

## Stream 결과 만들기 (최종 연산)
### 1. 최댓값/최솟값/총합/평균/갯수 - Max/Min/Sum/Average/Count

### 2. 데이터 수집 - collect
- Stream의 요소들을 List나 Set, Map 등 다른 종류의 결과로 수집하고 싶은 경우에는 collect 함수를 이용할 수 있다. collect 함수는 어떻게 Stream의 요소들을 수집할 것인가를 정의할 Collector 타입을 인자로 받아서 처리한다.
일바적으로 List로 Stream의 요소들을 수집하는 경우가 많은데, 이렇듯 자주 사용하는 작업은 Collectors 객체에서 static 메소드로 제공하고 있다. 원하는 것이 없는 경우에는 Collector 인터페이스를 직접 구현하여 사용할 수도 있다.

2.1 Collectors.toList() : Stream에서 작업한 결과를 List로 반환받을 수 있다. 

2.2 Collectors.joining() : Stream에서 작업한 결과를 1개의 String으로 이어붙이기를 원하는 경우에 Collectors.joining()을 이용할 수 있다. 총 3개의 인자를 받을 수 있는데, 이를 이용하면 간단하게 String을 조합할 수 있다.
- delimiter = 각 요소 중간에 들어가 요소를 구분시켜주는 구분자
- prefix = 결과 맨 앞에 붙는 문자 
- suffix = 결과 맨 뒤에 붙는 문자

2.3 Collectors.averagingInt(), Collectors.summingInt(), Collectors.summarizingInt() : Stream에서 작업한 결과를 평균값이나 총합 등을 구하기 위한 함수

- 1개의 Stream으로부터 개수, 합계, 평균, 최댓값, 최솟값을 한 번에 얻고 싶은 경우에는 Collectors.summarizingInt()에 각각의 get메서드를 이용하면 된다.

2.4 Collectors.groupingBy() : Stream에서 작업한 결과를 특정 그룹으로 묶을 때 이용, 결과는 Map으로 반환받게 된다. groupingBy는 매개변수로 함수형 인터페이스 Function을 필요로 한다. 

2.5 Collectors.partitioningBy() : 함수형 인터페이스 predicate를 받아 Boolean을 key 값으로 partitioning한다. 

### 조건 검사 - Match 
* Stream의 요소들이 특정한 조건을 충족하는지 검사하고 싶은 경우에는 match 함수를 이용한다. match 함수는 함수형 인터페이스 Predicate를 받아서 해당 조건을 만족하는지 검사하게 되고, 검사 결과를 boolean으로 변환한다. match 함수에는 크게 다음의 3가지가 있다.
    * anyMatch : 1개의 요소라도 해당 조건을 만족하는가
    * allMatch : 모든 요소가 해당 조건을 만족하는가
    * nonMatch : 모든 요소가 해당 조건을 만족하지 않는가

### 특정 연산 수행 - forEach 
- Stream의 요소들을 대상으로 어떤 특정한 연산을 수행하고 싶은 경우에는 forEach 함수를 이용할 수 있다. peek() 함수는 중간 연산으로써 실제 요소들에 영향을 주지 않은 채로 작업을 진행하고, Stream을 반환하는 함수였다. 하지만 forEach()는 최종 연산으로서 실제 요소들에 영향을 줄 수 있으며, 반환값이 존재하지 않는다. 

```java
// 최댓값/최솟값/총합/평균/갯수
OptionalInt min = IntStream.of(1, 3, 5, 7, 9).min();
int max = IntStream.of().max().orElse(0);
IntStream.of(1, 3, 5, 7, 9).average().ifPresent(System.out::println)

long count = IntStream.of(1, 3, 5, 7, 9).count();
long sum = LongStream.of(1, 3, 5, 7, 9).sum();

// 데이터 수집 - collect
collect() : 스트림의 최종 연산, 매개변수로 Collector를 필요로 한다.
Collector : 인터페이스, collect의 파라미터는 이 인터페이스를 구현해야 한다.
Collectors : 클래스, static 메소드로 미리 작성된 컬렉터를 제공한다.
```
### 참고사이트
> https://mangkyu.tistory.com/114