## 조회수, 추천
### article_list
- ![image](https://github.com/user-attachments/assets/467111f6-4c1a-43b5-9b51-092245fd21c7)
- ajax 코드
- ![image](https://github.com/user-attachments/assets/3bcedc9b-850e-46b1-b7b4-dbce6a8d05e9)

### article_detail
- ![image](https://github.com/user-attachments/assets/b0081f91-6d30-433a-91c7-436170158754)
- ajax 코드
- ![image](https://github.com/user-attachments/assets/20f27555-f0db-4345-be9a-1898b6dd9ae8)



## 1차 요구사항 구현
- [x] 유저가 루트 url로 접속시에 게시글 리스트 페이지(http://주소:포트/article/list)가 나온다.
- [x] 리스트 페이지에서는 등록 버튼이 있고 버튼을 누르면 http://주소:포트/article/create 경로로 이동하고 등록 폼이 나온다.
- [x] 게시글 등록을 하면 http://주소:포트/article/create로 POST 요청을 보내어 DB에 해당 내용을 저장한다.
- [x] 게시글 등록이 되면 해당 게시글 리스트 페이지로 리다이렉트 된다. 페이지 URL 은 http://주소:포트/article/list 이다.
- [x] 리스트 페이지에서 해당 게시글을 클릭하면 상세페이지로 이동한다. 해당 경로는 http://주소:포트/article/detail/{id} 가 된다.
- [x] 게시글 상세 페이지에는 목록 버튼이 있다. 목록 버튼을 누르면 게시글 리스트 페이지로 이동하게 된다.

# 2차 요구사항
- [x] 게시글 상세페이지(http://주소:포트/article/detail/{id})에 수정 버튼이 있다. 수정 버튼을 누르면 게시글을 수정 할 수 있는 폼이나 오고 수정이 가능하다.
- [x] 게시글 상세페이지에 삭제 버튼이 있다. 삭제 버튼을 누르면 게시글이 삭제가 된다. 삭제 후 리스트 페이지로 리다이렉트 된다.
- [x] 모든 페이지 상단에 루트 디렉토리로 이동하는 버튼이 있다.(예: 로고)
- [x] 모든 페이지 상단에 로그인 상태 표시하는 버튼이 있다.(예: 로그인 / 로그아웃) 
- [x] 모든 페이지 회원가입 버튼이 있다. 버튼을 누르면 회원가입 폼으로 이동한다.
	- [x] 회원가입 폼은 유저ID, 닉네임, 비빌번호, 비밀번호 확인으로 구성된다. 회원가입 버튼을 누르면 데이터 검증 후 회원가입이 된다.
- [x] 로그인 버튼을 누르면 로그인 폼으로 이동한다. 
	- [x] 로그인 페이지는 사용자 유저ID과 비밀번호를 입력하는 폼으로 구성되고 로그인 버튼을 누르면 데이터 검증 후 로그인이 된다.
- [x] 로그아웃 버튼을 누르면 로그아웃이 된다.
- [x] 유저가 게시글 작성 및 수정  접근시 로그인 여부를 검사하고 본인 글에 대해서만 수정 / 삭제가 가능하다.
- [x] (선택)메인페이지에 검색 기능이 구현되어야 한다. input 박스에 내용을 적고 검색 버튼을 누르면 해당 문자가 포함된 게시글이 리스트업 되어야 한다.
- [x]본인 글에 대해서만 수정 / 삭제가 가능하다.

## 미비사(선택)항 or 막힌 부분
- ...

## UI/UX (화면 캡처본을 복사 붙여 넣기, url 주소 나오도록)
- 게시글 리스트 페이지
- ![image](https://github.com/user-attachments/assets/2cc8ba32-9fd1-4553-a78a-613e5a7d2044)

- 게시글 등록 폼 페이지
- ![image](https://github.com/user-attachments/assets/982dad91-e3a8-4152-b01a-25aefda26292)

- 게시글 상세 페이지
- ![image](https://github.com/user-attachments/assets/75ab87e6-5c96-43da-ba9c-c21c5c2d1ce3)

- 로그인 페이지
- ![image](https://github.com/user-attachments/assets/cd5895ec-3d3a-48c7-b77a-258bc47e4be4)

- 회원가입 페이지
- ![image](https://github.com/user-attachments/assets/6e87cefb-139e-44aa-9e73-9e0d423a5a6d)

- (선택) 검색 페이지
- ![image](https://github.com/user-attachments/assets/f2fe9909-5a29-4af8-863c-f3f562006d58)

