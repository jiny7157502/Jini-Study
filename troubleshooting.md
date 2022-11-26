# 트러블 슈팅 기록

- **Git ignore 적용이 안되는 경우**
    - git의 캐시가 원인이라 git에 있는 캐시 파일을 지워주고 다시 add 해야함
    ```
        git rm -r --cached .
        git add .
        git commit -m "removed cached"
    ```
<br>

- **Column "GUEST" not found; SQL statement:**
    - 기본적이지만 sql문에서 이중 따옴표는 쓰지 말자. " " -> ' ' 확인하기

<br>

- **Error creating bean with name 'requestMappingHandlerMapping' 오류**
    - 컨트롤러의 @RequestMapping 으로 설정된 경로를 확인
    - 예외 로그를 자세히 보면 나와있지만 **특정 맵핑 경로가 중복**되어 발생한 오류

<br>

- **build.gradle에서 dependencies - compile() 메서드 쓸 때 에러 발생 시**
    - compile() 대신 implementation() 사용

<br> 

- **lombok 사용 중 Variable not initialized in the default constructor 에러 발생 시**
    - gradle 5.x 미만 버전은 implementation() 사용**
    - gradle 5.x 이상 버전은 compileOnly()와 annotionProcesser()을 사용
    - (gradle 버전 확인은 gradle - [gradle-wrapper.properties](http://gradle-wrapper.properties/) 에서 확인)

<br>

- **lombok 사용 중 Cause: class lombok.javac.apt.LombokProcessor ~~~~ 에러 발생 시**
    - gradle 에서 lombok 버전 1.18.20으로 명시해주기

<br>

- **테스트할 때 테스트메서드 명이 한글일 경우**
    - Execution failed for task ':test'. 오류 발생
    - setting에서 bulid tool 의 gradle에서 테스트구동을 Gradle -> intellij로 바꿈

<br>

- **플러그인 관련 내용**
    - 플러그인 설치는 한 번만 하면 되지만,  build.gradle에 라이브러리 추가와 setting에서 Enable annotation processing 체크는 프로젝트마다 매번 진행