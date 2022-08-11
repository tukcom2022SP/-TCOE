# **TUtayo - TCOE**

### <Git Rule>

#### issue

- feature 별로 issue 생성
  - Issues - New issue - 타이틀, 내용 작성 후 추가
  - 이슈 번호 확인 (#이슈번호)
- 각 issue가 끝나면 close 하기


#### Branch

1. 각자 Branch 생성하여 작업
2. 기능마다 issue 생성 후 Branch 생성
   - feature/{이슈번호}-{기능명} 브랜치 생성 후 작업
   - commit message는 이슈번호를 붙여서 작성 (ex. #1 README.md Git Rule 추가)


#### Pull requests

1. 원본 저장소에 병합할 경우
   - 소스트리의 작업한 Branch에서 원본 저장소 위치와 병합
   - 충돌 없으면 github 에서 main <- branch 로 Pull requests 작성 및 merge
   - 충돌 있으면 소스트리에서 수정하라고 나오는데, 각자가 현재 원본 저장소와 작성 코드 충돌 해결
   - 충돌 해결 후 커밋, github 에서 main <- branch 로 Pull requests 작성 및 merge
2. 다른 참여자의 branch에 병합할 경우
   - 소스트리의 작업한 Branch에서 다른 참여자의 branch에 병합
   - 충돌 없으면 github 에서 branch <- branch 로 Pull requests 작성
   - 충돌 있으면 소스트리에서 수정하라고 나오는데, 각자가 현재 원본 저장소와 작성 코드 충돌 해결
   - 충돌 해결 후 커밋, github 에서 branch <- branch 로 Pull requests 작성
