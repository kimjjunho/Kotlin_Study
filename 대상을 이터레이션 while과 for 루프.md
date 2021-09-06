# 대상을 이터레이션: while과 for 루프

### while 루프(89p)

코틀린에는 while과 do-while 루프가 있다. 두 루프의 문법은 자바와 다르지 않다

```
while(조건){ //조건이 참인 동안 본문을 반문을 반복 실행한다
	/*...*/
}
do{
	/*...*/
}while(조건) //맨 처음에 무조건 본문을 한 번 실행한 다음, 조건이 참인 동안 분문을 반복 실행
```



### 수에 대한 이터레이션: 범위와 수열

코틀린에서는 루프를 대신하기 위해 코틀린에서는 범위를 사용한다.

```kotlin
val oneToTen = 1..10
```

코틀린의 범위는 폐구간(닫힌 구간) 또는 양끝을 포함하는 구간이다. 이는 두 번째 값(위에 있는 10)이 항상 범위에 포함된다는 뜻이다.

**수열** : 정수 범위로 수행할 수 있는 가장 단순한 작업은 범위에 속한 모든 값에 대한 이터레이션이다. 이런 식으로 어떤 범위에 속한 값을 일정한 순서로 이터레이션하는 경우를 수열이라고 부른다.

---

**피즈버즈 게임을 위해 정수 범위를 사용**

when을 사용해 피즈버즈 게임 구현하기

*피즈버즈 게임 3의 배수일 때는 Fizz를 5의 배수일 때는 Buzz를 두수 모두의 배수일 때는 FizzBuzz를 말한다. 

```kotlin
fun fizzBuzz(i:Int)=when{
	i % 15 == 0 -> "FizzBuzz"
    i % 3 == 0 -> "Fizz"
    i % 5 == 0 -> "Buzz"
    else -> "$i" //다른 경우에는 그 수 자체를 반환한다
}

>>> for(i in 1..100){
    print(fizzBuzz(i))
}
1 2 Fizz 5 Buzz Fizz 7....Fizz Buzz
```

**증가 값을 갖고 범위 이터레이션하기**

```kotlin
for(i in 100 downTo 1 step 2){
    print(fizzBuzz(i))
}
Buzz 98 Fizz 94 92 FizzBuzz 88.....
```

- 증가 값 step을 갖는 수열에 대해 이터레이션한다. 증가 값을 사용하면 수를 건너 뛸 수 있다. 증가 값을 음수로 만들면 정방향 수열이 아닌 역방향 수열을 만들 수 있다.
- 위에서 100 downTo 1은 역방향 수열을 만든다
- 그 뒤에 step 2를 붙이면 증가 값의 절대값이 2로 바뀐다.



### 맵에 대한 이터레이션(92p)

**문자에 대한 2진 표현을 출력하는 프로그램(예제) 이때 2진 표현을 맵에 저장한다.**

```kotlin
val binaryReps = TreeMap<Char, String>() //키에 대해 정렬하기 위해 TreeMap을 사용

for(c in 'A'..'F'){
    val binary = Integer.toBinaryString(c.toInt()) //아스키 코드를 2진 표현으로 바꾼다
    binaryReps[c] = binary 
    //c를 키로 c의 2진 표현을 맵에 넣는다.
    //binaryReps.put(c,binary)라는 자바 코드와 같다
}
for((letter, binary)in binaryReps){
    //맵에 대해 이터레이션한다. 맵의 키와 값을 두 변수에 각각 대입한다
    println("$letter = $binary")
}
```

```kotlin
val list = arrayListOf("10","11","1001")
for((index,element)in list.withIndex()){
    println("$index : $element")
}

// 0: 10
// 1: 11
// 2: 1001
```



### in으로 범위의 원소 검사(94p)

in 연산자를 사용해 어떤 값이 범위에 속하는지 검사할 수 있다. 반대로 !in을 사용하면 어떤 값이 범위에 속하지 않는지 검사할 수 있다.

**in을 사용해 값이 범위에 속하는지 검사하기**

```kotlin
fun isLetter(c: char) = c in 'a'..'z'||c in 'A'..'Z'
//c in 'a'..'z'는 'a' <= c && c <= 'z'로 변환된다
fun isNotDigit(c: char) = c !in '0'..'9'

>>> println(isLetter('q'))
true
>>> println(isNotDigit('x'))
true
```



**when에서 in 사용하기**

```kotlin
fun recognize(c: char) = when(c){
    in '0'..'9' -> "It's a digit!"
    in 'a'..'z', in 'A'..'Z' -> "It's a letter" //여러 범위 조건을 사용해도 된다
    else -> "I don't know..." 
}

>>>println(recognize('8'))
It's a digit!
```



```kotlin
>>> println("Kotlin" in "Java".."Scala") //"Java" <= "Kotlin && Kotlin"<="Scala"와 같다.
true
```

```kotlin
>>> println("Kotlin"in setOf("Java"..Scala)) //이 집합에는"Kotlin"이 들어있지 않다
false
```

- 범위는 문자에만 국한되지 않는다.

- 비교가 가능한 클래스라면(java,\.lang.Comparable 인터페이스를 구현한 클래스라면) 그 클래스의 인스턴스 객체를 사용해 범위를 만들 수 있다.

- Comparable을 사용하는 범위의 경우 그 범위 내의 모든 객체를 항상 이터레이션하지는 못한다.

  ex) 'Java'와 'Kotlin'사이의 모든 문자열을 이터레이션하지는 못한다

- 하지만 in을 연산자를 사용하면 값이 범위 안에 속하는지 항상 결정할 수 있다.

- String에 있는 Comparable 구현이 두 문자열을 알파벳 순서로 비교하기 때문에 여기 있는 in 검사에서도 문자열을 알파벳 순서로 비교한다

- 컬렉션에도 마찬가지로 in 연산을 사용할 수 있다.
