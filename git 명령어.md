# Git 명령어

1. git init : git 초기화, git으로 버전관리를 할 경로에서 사용

<br>

2. git status : 현재 git 프로젝트에서 파일들의 상태를 보여줌

>git의 세 가지 영역 <br>
워킹 디렉터리(워킹 트리) : 프로젝트를 진행하는 실제 작업 공간으로 개발한 소스 및 자원이 존재하며 이 곳에서 파일을 수정 및 추가한다. <br>
Staging Area : 워킹 디렉터리에서 작업한 내역을 Git 디렉터리로 커밋하기 위해 커밋 대상 목록으로 담아두는 장바구니 목록 같은 영역이다.(git add 명령의 결과) <br>
Git 디렉터리 : .git이라는 이름의 디렉터리이며, 여러가지 버전의 커밋 데이터들과 Git 프로젝트에 대한 모든 정보를 담고 있는 핵심 데이터베이스 디렉터리이다.(git commit 명령의 결과)

3. git add : 파일의 변경 내용을 스테이징 영역에 추가할 때 사용
```
# 모든 변경점 추가
git add -A

# 현재 경로의 모든 변경점 추가
git add .
```

4. git rm : 파일을 지우거나 스테이지에서 해제할 때 사용
```
# 파일 삭제
git rm 파일명 

# README.md 파일을 추적되지 않은 상태로 만듦
git rm --cached README.md
```

5. git commit : 변경된 내용을 저장
```
# 메시지와 함께 커밋
git commit -m "메시지 내용"
```

6. git log : commit 목록을 출력 

<br>

7. git reset HEAD^ : commit을 취소한다.
```
# commit을 취소하고 해당 파일들은 Staging Area에 보존
git reset --soft HEAD^

# commit을 취소하고 해당 파일들은 Unstaging
git reset --mixed HEAD^

# commit을 취소하고 해당 파일들의 변경점 삭제
git reset --hard HEAD^

# push를 취소
git reset HEAD^
git push -f origin 브랜치명
git pull
```

8. git remote : 원격 저장소 관리
```
# 설정된 원격 저장소 보기
git remote -v

# test 라는 이름으로 원격 저장소 추가
git remote add test http://github.com/test/test
```

9. git push : 원격 저장소에 변경한 코드를 업로드
```
# 기본 사용법
git push [저장소명] [branch]

# 최초 1회 저장, branch 지정, 이후 생략할 수 있음
git push -u [저장소명] [branch]

# 로컬에서 생성한 branch를 push
git push --set-upstream [저장소명] [branch]
```

10. git branch : branch에 관련된 명령어
```
# 로컬 branch 목록 확인 
git branch

# 모든 branch 목록 확인
git branch -a

# test 라는 branch 생성하기
git branch test

# test 로컬 branch를 origin이라는 원격 저장소의 test branch에 연결
git branch --set-upstream-to=origin/test test

# test branch 삭제
git branch -d test

# test branch 강제 삭제
git branch -D test
```

11. git checkout : branch를 변경
```
# test branch로 변경하기
git checkout test

# test2 라는 branch를 새로 생성하고 test2 branch로 변경하기
git checkout -b test2
```

12. git fetch : 원격 저장소의 데이터를 가져옴.
```
# origin 이라는 원격 저장소의 데이터를 가져옴
git fetch origin

# 모든 원격 저장소의 데이터를 가져옴
git fetch --all
```

13. git pull : 원격 저장소의 데이터를 가져온 후 로컬 branch에 병합

<br>

14. git merge : 현재 브랜치를 특정 브랜치의 소스와 병합
```
# master branch를 병합
git merge master

# 병합 충돌(Conflict) 발생 시 취소
git merge --abort
```

깃 관련 참고사이트
> https://urbanbase.github.io/dev/2021/01/15/GitCommand.html

> https://dololak.tistory.com/304