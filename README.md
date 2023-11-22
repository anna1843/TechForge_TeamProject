# SpringBoot-Project-SoleManager
>그룹웨어 기본 연동 기능 및 메시지 봇 구현 프로젝트
<br>

# 📍 목차
1. 프로젝트 소개
2. 개발기간
3. 개발환경
4. DB구성
5. 멤버구성
6. 주요기능
<br>

## 🖥️ 프로젝트 소개
> SoleManager는 프리랜서와 회사 간 연결을 중개하는 "프리랜서 중개 에이전시"로 SoleManager의 그룹웨어를 구축합니다.
> 이 그룹웨어는 다음과 같은 3가지의 서비스를 제공합니다.
1. 프리랜서와 회사 간의 적합한 프로젝트를 찾아 매칭하는 서비스 제공
2. 계약서 작성, 결제 처리 및 금융 관련 서비스를 제공
3. 프리랜서와 회사 간의 원활한 커뮤니케이션을 제공
> (추가) OpenAPI를 연동하여 메시지 봇을 구현합니다.
<br>

## ⌛️ 개발 기간
* 23.09.26 ~ 23.10.25 (약 1개월 소요)
* (추가) 23.10.26 ~ 23.11.03 (약 10일 소요)
<br>

## 🛠️ 개발 환경
<img width="846" alt="스크린샷 2023-10-30 오후 4 18 31" src="https://github.com/anna1843/TechForge/assets/133622218/1797ae7e-bdd1-4826-92fd-b91f76223c86">
![개발환경](./image/busan.jpg)

## ⚙️ DB 구성
![DB](https://github.com/anna1843/TechForge/assets/133622218/5d4b2626-1fb2-4da2-9040-16d827fc5511)
![DB구성](./image/busan.jpg)

## 🏃‍♀️ 맴버 구성
#### 김예진(팀장) : 근무/근태(R), 급여(C,R), BaseLayout디자인, 모달디자인, PPT, 영화 API
<details>
  <summary>근무/근태</summary>
  > 근무/근태 리스트 보여주기 Controller
  
  ```java
    @GetMapping("/{memberId}/list")
    @ResponseBody
    public Map<String, Object> getWorkTimeWorklist(
            @PathVariable("memberId") Long memberId,
            @RequestParam(value = "workType", required = false) String workType) {
        // json 형태로 front에 넘기기
        Map<String, Object> map = new HashMap<>();

        // 근무기록 list로 가져오기(반환)
        List<WorkTimeDto> workTimeList = workTimeService.getWorkTimeWorkList(memberId,workType);

        map.put("worklist", workTimeList);
        return map;
    }
  ```

  > 근무/근태 리스트 보여주기 Service
  ```java
  public List<WorkTimeDto> getWorkTimeWorkList(Long memberId, String workType) {
        List<WorkTimeDto> workTimeDtoList = new ArrayList<>(); // 반환값이 list이므로 list생성
        List<WorkTimeEntity> workTimeEntityList;

        if (workType == null) {
            // 달만 선택
            workTimeEntityList = workTimeRepository.findByWorkTimeMemberId(memberId);
        } else {
            workTimeEntityList = workTimeRepository.findByWorkTimeWorkType(memberId, workType);
        }

        // 달&유형 선택
        if (!workTimeEntityList.isEmpty()) {
            for (WorkTimeEntity workTimeEntity : workTimeEntityList) {
                WorkTimeDto workTimeDto = WorkTimeDto.toDto(workTimeEntity);
                if (workTimeDto.getWorkType() == WorkType.NORMAL) {
                    workTimeDto.setTitle("근무");
                } else if (workTimeDto.getWorkType() == WorkType.ABSENT) {
                    workTimeDto.setTitle("결석");
                } else if (workTimeDto.getWorkType() == WorkType.EARLY) {
                    workTimeDto.setTitle("조퇴");
                } else if (workTimeDto.getWorkType() == WorkType.TARDY) {
                    workTimeDto.setTitle("지각");
                } else if (workTimeDto.getWorkType() == WorkType.VACATION) {
                    workTimeDto.setTitle("휴가");
                }
                workTimeDtoList.add(workTimeDto);
            }
        }
        return workTimeDtoList;
    }
  ```

</details>

<details>
  <summary>💵월급(급여)정산 및 리스트</summary>
  <ul>
    <li>월급 정산하기</li>
    <img width="690" alt="image" src="https://github.com/anna1843/TechForge_TeamProject/assets/133622218/d82ad0de-f54e-4d50-8d29-3273637b9f6e">
    ![월급정산](월급정산.png)
    <li>월급 목록보기</li>
    <img width="636" alt="image" src="https://github.com/anna1843/TechForge_TeamProject/assets/133622218/e4faf287-3c69-4b8e-89b1-8b850dafe6a8">
    ![월급목록](월급내역.png)
  </ul>
</details>

<details>
  <summary>레이아웃 디자인</summary>
  <ul>
    <li>레이아웃 디자인</li>
    <img width="620" alt="스크린샷 2023-11-22 오전 11 38 34" src="https://github.com/anna1843/TechForge_TeamProject/assets/133622218/75d52c35-6920-4a1a-9eae-7ca27431ee1a">
    ![레이아웃](레이아웃.png)
  </ul>
</details>

<br>
김** : 로그인, 이메일 인증, 비밀번호 재설정, 권한별 LIST, 로그인&회원가입 디자인, 날씨 API
<br>
박** : 게시판(CRUD), 댓글, 파일, FUllCalendar일정추가, 웹소캣 알림 챗봇, 메인페이지디자인, PPT, 버스 API
<br>
방** : 회원(CRUD), 회원페이지 디자인, 날씨 API
<br>
안** : 결재(CRUD), 버스 API
<br>
이** : 근무/근태(CUD), FUllCalendar(근무,프리랜서일정), 네이버웍스 구현, CI/CD, 영화 API

## 주요기능
