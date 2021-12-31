# Repository패턴

## 이론

**사전적 의미**

- 대량의 저장소
- 결국 repository는 무엇을 저장하기 위하여 존재한다

**정의**

- 데이터 출처(로컬 DB인지 API응답인지 등)와 관계 없이 동일 인터페이스로 데이터에 접속할 수 있도록 만드는 것을 Repository 패턴이라고 한다. 레포지토리는 데이터 소스에 액세스하는 데 필요한 논리를 캡슐화하는 클래스 또는 구성 요소이다.

  *캡슐화 : 객체의 속성과 행위(함수)를 하나로 묶고 구현 내용의 일부 혹은 전체를 외부로부터 감추는 것을 얘기한다.

- 이 경우에는 데이터 레이어 소스들이 캡슐화 대상이다

**역할**

- Presentation 레이어(View, ViewModel)나 Domain 레이어에서 Data 레이어( Repository, Model 등 )에 접근할 때, 데이터 소스의 위치( 서버, Local DB )를 몰라도 일관성 있게 원하는 데이터를 취할 수 있도록 돕는 것이 Repository의 역할이다.

**구조**

- View -> Presenter/ViewModel -> Repository -> DataSource( API, Local DB )

  [![repository](https://vagabond95.me/static/fb26349785c3de42dc673d2b3b5b1f44/d9199/repo1.png)](https://vagabond95.me/static/fb26349785c3de42dc673d2b3b5b1f44/d9199/repo1.png)

  

**사용하는 이유**

- 위에서 말한 것 처럼 도메인과 연관된 모델을 가져오기 위해 필요한 DataSource가 무엇인지 Presenter 계층에서는 알 필요가 없으므로 DataSource를 새롭게 추가하는 것도 부담이 없다.

- DataSource의 변경이 발생하더라도 다른 계층은 영향받지 않는다

- client는 repository 인터페이스에 의존하기 때문에 테스트 하기 용이하다.

  *핵심 : repository는 Presenter계층과 data계층의 coupling을 느슨하게 해준다.

  *coupling : 결합도, 의존도

- 데이터 로직 분리 가능

- 새로운 데이터 로직을 쉽게 추가 가능

**특징**

- Repository라는 인터페이스만 ViewModel이 접근한다.
  - 레포지토리 너머의 데이터 소스가 추가 혹은 제거되는 변경이 있더라도 도메인 레이어나 프레젠트 레이어는 알 수가 없다.
  - 캡슐화가 되었기 때문.
  - 변경에 따른 작업은 레포지토리와 레포지토리 구현체에만 있기 때문에 알 필요도 없다

- 개발자는 Repository가 없을 때보다 있을 때 데이터 소스가 변경되는데 부담을 적게 느끼게 된다.



## 실습 코드

**기존 코드**

- API를 View에서 호출하는 구조

- View는 Presentation 레이어로, 여기서 사용될 데이터가 서버와의 통신을 통한 것인지 로컬 DB를 통한 것인지 알 필요가 없다.

  ```kotlin
  ApiClient() 
  .getApiService()
  .getUsers()
  .subscribeOn(Schedulers.computation()) 
  .observeOn(AndroidSchedulers.mainThread()) 
  .subscribeBy(
    onNext = {
      if (it?.data == null) { Log.i(this.localClassName, "Data(List users) is null") 
                             Toast.makeText(this, "더 이상 불러올 데이터가 없습니다.", Toast.LENGTH_SHORT).show() } else { adapter.setDataSet(it.data) } },
    onError = { it.printStackTrace() },
    onComplete = { Log.i(this.localClassName, "* * * * Complete * * * *") 
                  Toast.makeText(this, "정상적으로 데이터를 가지고 왔습니다.", Toast.LENGTH_SHORT).show() } )
  ```

**변경 코드**

- 모델이 되는 data(Data 레이어)와 View(Presentation 레이어)를 명확히 분리하고 이 사이를 연결해 줄 Repository를 추가할 것이다.

  ```kotlin
  interface Repository { 
    fun getUsers(): Single<List<DataItem>>
    fun updateUser(id: Int, updateItem: UpdateItem): Single<UpdateItem>
    fun createUser(updateItem: UpdateItem): Single<UpdateItem> 
  } 
  class RepositoryImpl : Repository { override fun getUsers(): Single<List<DataItem>> { 
    /** 
    * api의 옵저버블을 구독하고 데이터가 도착하면 어댑터에 데이터를 할당
    * 데이터가 할당하기 전에 오류 코드를 확인하도록 설계해야 하지만 지금도 잘 동작은 함 */
    return ApiClient() 
    .getApiService()
    .getUsers()
    .subscribeOn(Schedulers.computation())
    .observeOn(AndroidSchedulers.mainThread())
    .map { it.data } } // 중략 }
  ```

  ---

  **ViewModel**

  ```java
  public class UserInformationViewModel {
  
  		...
  
      public void onClickUserInfoButton(int userId) {
          mUserRepository.getUser(userId)
                  .subscribeOn(Schedulers.io())
                  .observeOn(AndroidSchedulers.mainThread())
                  .subscribe(
                          user -> {
                              ///
                          },
                          error -> {
  
                          }
                  );
      }
  }
  ```

  **Repository**

  ```java
  public class UserRepository {
  
  		...
  
      public Single<User> getUser(int userId) {
          return userApi.getUser(userId);
      }
  }
  ```



## 문제점

- Repository가 DataSource의 데이터를 그대로 전달한다.
  - back단의 구현 이슈가 presenter layer에 영향을 끼칠 수 있다.
  - User 는 서버 (혹은 로컬 DB) 의 데이터베이스 테이블을 표현하는 역할을 수행하는 객체일 뿐이다.

- 필드 삭제, 필드 이름 변경 등 서버가 데이터 구조를 변경하게 되면 이를 바로 참조하고 있는 다른 layer 에서도 필연적으로 변경이 발생하게 된다.

  -  예를 들어 클라쪽에서는 User 정보를 표기해야 하는데 서버에서 각기 다른 데이터의 요청을 N 번 거쳐야 한다고 생각해보자. 그럼 N 개의 데이터에 대한 변경 사항은 모두 온전히 클라에 영향을 줄 것이다. 더 중요한 점은 이렇게 복잡한 데이터 모델을 혼재하여 사용하게 되면 다른 개발자가 컨텍스트를 이해하기 매우 어려워지기 시작한다.

- 서버 데이터 구조를 그대로 가져와 사용할 경우, 팀내 도메인 용어와 실제 코드 베이스의 용어가 달라지게 되고 커뮤니케이션 리소스가 급증하게 되는 상황이 발생한다.

  - 도메인의 비즈니스 로직 처리에 필요한 메소드를 생성할 시점이 왔을 때, (서버 테이블을 반영한) 객체에 추가하게 되면 해당 객체는 테이블도 표현하고, 도메인 로직도 처리하는 [God object](https://en.wikipedia.org/wiki/God_object) 가 될 확률이 높다.

    *God object : 너무 많은 일을 하고, 너무 많은 일을 아는 데이터 구조이다.

    God object의 주요 문제는 단일 객체 또는 클래스에 집중된 다중 기능 또는 **책임** 이다. 크기 때문에 소프트웨어를 유지 관리, 확장, 사용, 테스트 및 시스템의 다른 부분과 통합하기가 어렵다.

## 해결 방법

- Mapper를 사용

  *Mapper란 테이블 객체 ↔ 도메인 모델 객체간의 mapping 을 시켜주는 유틸성 클래스를 의미한다. 

  - repository 내에서 mapper 를 활용하여 테이블 객체가 아닌 도메인 모델로 전달을 해주면 presentation layer 는 data layer 로 부터 진정한 자유를 찾을 수 있게 된다.

**Mapper (X)**

```java
public class UserRepository {

		...

    public Single<UserDomain> getUser(int userId) {
        return userApi.getUser(userId).map(UserMapper::fromTable);
    }
}
```

**Mapper(O)**

```java
public class UserMapper {

    public static UserDomain fromTable(User user) {
        return new UserDomain(user.id, user.name, user.grade);
    }
}
```

- 만약 모든 모델에 대하여 보일러 플레이트 코드 처럼 Mapper 클래스를 만드는 것이 싫다면 [라이브러리](https://github.com/modelmapper/modelmapper) 를 활용해보는 것도 좋은 대안이다.



## 출처

- https://devvkkid.tistory.com/196
- https://vagabond95.me/posts/android-repository-pattern/