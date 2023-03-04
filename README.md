# 토이프로젝트

기본적인 회원가입 기능, 게시판 기능, 댓글 기능, 로그인 기능 등을 구현하였습니다. 또한 로그인하지 않은 사용자가 댓글과 게시물을 작성할 수 없도록 구현하였습니다.

## 1. Skills
### 1.1 BackEnd
`Java 8`, `SpringBoot 2.71`, `Spring security 5.7.2`, `JPA`, `MySQL 8.0.28`, `Junit4`

### 1.2 FrontEnd
`Thymeleaf`

## 2. ERD
<img width="640" alt="image" align="center" src="https://user-images.githubusercontent.com/102225706/221506085-83bc7969-2f5c-4b9c-a2b2-a622c52fbfcd.png">

### 2.1 기본적인 설명
- 회원 한 명이 여러 개의 게시물을 작성할 수 있습니다. 또는 하나의 게시물도 작성하지 않을 수도 있습니다. 그러므로 회원DB와 게시물 DB는 일대다 관계입니다. JPA의 연관관계 주인은 항상 `다`인 도메인에 있기 때문에 게시물 DB에 FK가 존재합니다.
- 또한 회원 한 명은 여러 개의 댓글을 작성할 수 있거나 아예 작성하지 않을 수 있습니다. 그러므로 회원 DB와 댓글 DB는 일대다 관계입니다. 위의 경우와 마찬가지로 FK인 member는 댓글 DB에 존재합니다.
- 한 개의 게시물에는 0개, 1개 또는 다수의 댓글이 존재할 수 있습니다. 그러므로 게시물과 댓글의 관계는 일대다입니다. 마찬가지로 FK는 댓글 DB에 존재합니다.

## 3. 기능
### 3.1 회원가입 기능 & 회원 기능
- 기본적인 CRUD를 작성하였습니다.
- 이메일, 비밀번호 등 개인 정보를 입력하여 데이터베이스에 개인정보를 넣어 회원가입을 합니다.
- 이메일 형식이 아닌 경우 이메일 형식을 사용하라는 안내문을 내놓습니다.
- 로그인한 뒤, 개인정보 수정을 통해 데이터를 수정할 수 있습니다.
- 자기소개인 `intro` 컬럼의 경우, 개인정보 수정을 통해서만 입력할 수 있습니다.
<img width="943" alt="image" src="https://user-images.githubusercontent.com/102225706/221808686-73427598-c2b2-4e62-be07-ba2eaed9f464.png">

### 3.2 로그인 기능
- 가입한 이메일과 비밀번호를 통해 DB의 회원 정보와 비교한 뒤 일치하면 게시판 리스트 페이지로 넘어갑니다.
- 일치하지 않은 정보인 경우 가령, 비밀번호나 이메일을 잘못 입력한 경우 다시 로그인 페이지로 리다이렉트합니다.
- 댓글 기능의 경우 로그인을 하지 않으면 에러처리를 하게끔 작성하였습니다.
- 로그인을 하지 않고도 페이지 리스트로 넘어갈 수는 있지만 내용을 볼 수 없습니다.
<img width="1000" alt="image" src="https://user-images.githubusercontent.com/102225706/221808794-64d64a0b-f18a-460a-8b8c-541d9693d7ee.png">

### 3.3 게시판 기능
- 기본적인 CRUD를 작성하였습니다.
- 로그인한 뒤 보이는 첫 페이지는 게시판 리스트입니다. `post DB`에 있는 모든 게시물 데이터들을 내놓습니다.
- 게시물을 클릭하면 상세 페이지로 넘어갑니다. 상세 페이지에는 게시물의 제목, 내용과 댓글을 내놓습니다.
#### 3.3.1 게시글 작성
<img width="1012" alt="image" src="https://user-images.githubusercontent.com/102225706/221808936-ea635e4e-2ea9-47f5-9276-9e1b296f02a6.png">

#### 3.3.2 게시글 수정
<img width="904" alt="image" src="https://user-images.githubusercontent.com/102225706/221811305-0a66d752-11a0-4237-b0aa-dfc2a554bcc9.png">

#### 3.3.3 게시글 삭제
<img width="904" alt="image" src="https://user-images.githubusercontent.com/102225706/221811583-7e876e73-d51b-452e-931c-e7244e7a4a81.png">

### 3.4 댓글 기능
- 기본적인 CRUD를 작성하였습니다.
- 로그인을 하지 않은 사용자가 댓글을 작성할 경우, 에러를 던집니다.
- 내가 작성한 댓글인 경우에만 삭제 링크와 댓글 수정 링크를 보이도록 작성했습니다.
#### 3.4.1 댓글 기능
##### 댓글 작성
<img width="941" alt="image" src="https://user-images.githubusercontent.com/102225706/221809013-db15d6f2-292a-410a-a4a5-87822ecaf14c.png">

##### 댓글 수정
<img width="774" alt="image" src="https://user-images.githubusercontent.com/102225706/221809160-326334e1-4b2e-43c1-b041-c510be88a27d.png">

##### 댓글 삭제
<img width="862" alt="image" src="https://user-images.githubusercontent.com/102225706/221811911-ca143e5d-8861-44ea-9df7-ce796f009ea9.png">


