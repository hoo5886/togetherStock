# 토이프로젝트

기본적인 회원가입 기능, 게시판 기능, 댓글 기능, 로그인 기능 등을 구현하였습니다. 또한 로그인하지 않은 사용자가 댓글과 게시물을 작성할 수 없도록 구현하였습니다.

# 1. Skills
## 1.1 BackEnd
`Java 8`, `SpringBoot 2.71`, `Spring security 5.7.2`, `Spring Data JPA`, `MySQL 8.0.28`, `Junit4`

## 1.2 FrontEnd
`Thymeleaf`

# 2. ERD
<img width="640" alt="image" align="center" src="https://user-images.githubusercontent.com/102225706/221506085-83bc7969-2f5c-4b9c-a2b2-a622c52fbfcd.png">

## 2.1 기본적인 설명
- 회원 한 명이 여러 개의 게시물을 작성할 수 있습니다. 또는 하나의 게시물도 작성하지 않을 수도 있습니다. 그러므로 회원DB와 게시물 DB는 일대다 관계입니다. JPA의 연관관계 주인은 항상 `다`인 도메인에 있기 때문에 게시물 DB에 FK가 존재합니다.
- 또한 회원 한 명은 여러 개의 댓글을 작성할 수 있거나 아예 작성하지 않을 수 있습니다. 그러므로 회원 DB와 댓글 DB는 일대다 관계입니다. 위의 경우와 마찬가지로 FK인 member는 댓글 DB에 존재합니다.
- 한 개의 게시물에는 0개, 1개 또는 다수의 댓글이 존재할 수 있습니다. 그러므로 게시물과 댓글의 관계는 일대다입니다. 마찬가지로 FK는 댓글 DB에 존재합니다.

# 3. 기능
## 3.1 회원가입 기능 & 회원 기능
- 기본적인 CRUD를 작성하였습니다.
- 이메일, 비밀번호 등 개인 정보를 입력하여 데이터베이스에 개인정보를 넣어 회원가입을 합니다.
- 이메일 형식이 아닌 경우 이메일 형식을 사용하라는 안내문을 내놓습니다.
- 로그인한 뒤, 개인정보 수정을 통해 데이터를 수정할 수 있습니다.

## 3.2 로그인 기능
- 가입한 이메일과 비밀번호를 통해 DB의 회원 정보와 비교한 뒤 일치하면 게시판 리스트 페이지로 넘어갑니다.
- 일치하지 않은 정보인 경우 가령, 비밀번호나 이메일을 잘못 입력한 경우 다시 로그인 페이지로 리다이렉트합니다.
- 댓글 기능의 경우 로그인을 하지 않으면 에러처리를 하게끔 작성하였습니다.
- 로그인을 하지 않고도 페이지 리스트로 넘어갈 수는 있지만 내용을 볼 수 없습니다.

## 3.3 게시판 기능
- 기본적인 CRUD를 작성하였습니다.
- 로그인한 뒤 보이는 첫 페이지는 게시판 리스트입니다. `post DB`에 있는 모든 게시물 데이터들을 내놓습니다.
- 게시물을 클릭하면 상세 페이지로 넘어갑니다. 상세 페이지에는 게시물의 제목, 내용과 댓글을 내놓습니다.

## 3.4 댓글 기능
- 기본적인 CRUD를 작성하였습니다.
- 로그인을 하지 않은 사용자가 댓글을 작성할 경우, 에러를 던집니다.
- 내가 작성한 댓글인 경우에만 삭제 링크와 댓글 수정 링크를 보이도록 작성했습니다.

# 4. 트러블 슈팅
## 4.1 테스트 코드 작성 중 발생한 NPE

### a. 문제
`java.lang.NullPointerException`
### b. 문제원인 분석 및 해결

### c. 스택오버플로우

**찾은 해결책**

[https://stackoverflow.com/questions/74438448/using-bcryptpasswordencoder-in-unit-test-returns-a-null-value](https://stackoverflow.com/questions/74438448/using-bcryptpasswordencoder-in-unit-test-returns-a-null-value)

**해결이 되질 않아 남긴 질문**

[https://stackoverflow.com/questions/75059614/springboot-password-encoder-unittest-is-null](https://stackoverflow.com/questions/75059614/springboot-password-encoder-unittest-is-null)

에러의 원인: `Mockito` 사용법을 제대로 알지 못해 발생한 문제.

**자세한 에러 원인 분석**

1. `passwordEncoder`를 `@mock`으로 생성했으니 null값이었음. 이 객체를 실제 객체로 만들어줬어야 했다.
2. 테스트의 의도는 회원가입 후 실제로 데이터가 잘 들어갔는지 여부. 그래서 `given().willReturn()`를 통해 실제 존재해야할 값을 입력해주고, `memberService.create()`를 통해 만들어진 값과 비교했어야 했다.

공부 후 다시 작성 후 성공

## 4.2 테스트 코드 작성 중 중복회원 익셉션이 발생하지 않음
### a. 문제 원인 분석.

- 예외 처리에 대한 기본지식 부족

지금까지 내가 막혔던 부분을 찬찬히 돌아보니, 모두 **MVC패턴**에 대한 것이었다. **스프링 MVC**에 대한 공부를 안한 것이 이렇게 바로 드러났다. 

심지어 `JPA`나 `SPRING`에 대한 것들은 구글링이나 스택오버 플로우를 조금만 찾아봐도 이해가 쉽게 됐지만 예외처리 핸들링에 대한 것들은 곧잘 이해가 되지 않았다.

### b. 해결

1. **예외처리 방식에 대한 기본적인 로직 구현 강의**:  [https://www.youtube.com/watch?v=nyN4o9eXqm0&list=PLlTylS8uB2fBOi6uzvMpojFrNe7sRmlzU&index=18](https://www.youtube.com/watch?v=nyN4o9eXqm0&list=PLlTylS8uB2fBOi6uzvMpojFrNe7sRmlzU&index=18)
2. 강의를 들은 후, 각 도메인마다 `exception` 코드를 짜고, 모든 `exception`을 한번에 처리하기 위해 `common`디렉토리에서 handler를 만들어주어 예외를 처리하였다.

## 4.3 로그인 기능 테스트 코드
### a. 문제
```html
java.lang.IllegalStateException: Failed to load ApplicationContext

Caused by: org.springframework.beans.factory.UnsatisfiedDependencyException: Error creating bean with name 'securityConfig': Unsatisfied dependency expressed through constructor parameter 0; nested exception is org.springframework.beans.factory.NoSuchBeanDefinitionException: No qualifying bean of type 'com.stock.togetherStock.security.handler.AuthSuccessHandler' available: expected at least 1 bean which qualifies as autowire candidate. Dependency annotations: {}
	
Caused by: org.springframework.beans.factory.NoSuchBeanDefinitionException: No qualifying bean of type 'com.stock.togetherStock.security.handler.AuthSuccessHandler' available: expected at least 1 bean which qualifies as autowire candidate. Dependency annotations: {}
	
```

### b. 원인 분석
내가 겪고 있던 문제는 Security 로그인 기능을 테스트하는 코드를 작성하나, securityConfig에서 authSuccessHandler를 bean으로 등록하지 못해서 문제가 발생.

### c. 해결책
어떻게 테스트해야하는지 감이 오질 않았으나, 해답을 발견했음.

- [https://stackoverflow.com/questions/73050028/error-creating-bean-with-name-securityconfig-defined-in-file](https://stackoverflow.com/questions/73050028/error-creating-bean-with-name-securityconfig-defined-in-file)
- [https://astrid-dm.tistory.com/536](https://astrid-dm.tistory.com/536)

결국 요약하자면 문제는 설정 클래스의 필드들을 모두 로드하려면 `@SpringBootTest`를 붙여줘야 한다는 점.

## 4.4 `postRepository.findAll()`의 `NPE` 에러
### a. 문제
```java
@Service
@RequiredArgsConstructor
public class PostService {

    PostRepository postRepository;

    /**
     * 게시물 목록 조회 로직
     */
    public List<Post> postList() {

        return postRepository.findAll();
    }
```
이 코드로 실제 게시물 리스트를 조회하면 NPE가 나온다. 왜그랬을까? 원인은 간단했지만 30분 정도 잡아먹었다.
### b. 해결
나는 `@RequiredArgsConstructor`를 통해 생성자를 자동주입했다. 그러나 위 로직의 `PostRepository`는 `final`을 적용하지 않았으므로 위 애노테이션이 제대로 동작할리가 만무하다. 그러니 제대로 주입이 되지 않았던 것이다.

```java
@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    /**
     * 게시물 목록 조회 로직
     */
    public List<Post> postList() {

        return postRepository.findAll();
    }
```
위와 같이 `private final` 을 붙여주었다. 제대로 동작한다.

## 4.5 PostService 테스트코드 중 `postRepository`에 null이 주입되는 에러
### a. 문제
#### `PostServiceTest`
<img width="468" alt="image" src="https://user-images.githubusercontent.com/102225706/223382878-22ea96b5-eed3-4aec-8a20-675da2f07418.png">

`postRepository`에 `null`이 들어있다. 원래는 `repository`가 들어있어야 한다. 아래의 `memberRepository`에서 처럼말이다.

#### `MemberServiceTest`
<img width="531" alt="image" src="https://user-images.githubusercontent.com/102225706/223382951-d615a7c1-3950-41f4-a9bb-42ba836521dc.png">

분명 두 테스트 클래스의 코드는 동일하다. 근데 `PostRepository`에선 `null`이 주입된다.

### b. 해결
스택오버 플로우: [https://stackoverflow.com/questions/63741787/nullpointerexception-when-mocking-repository-junit](https://stackoverflow.com/questions/63741787/nullpointerexception-when-mocking-repository-junit)\
결론: Junit4와 Junit5를 섞어서 사용했기 때문이다.

JUnit 4 : `org.junit.runner.RunWith` / `org.junit.Test`
JUnit 5 : `org.junit.jupiter.api.extension.ExtendWith` / `org.junit.jupiter.api.Test`
JUnit5로 통일하여 문제를 해결하였다.
