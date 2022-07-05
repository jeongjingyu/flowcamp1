# 카슐랭 가이드

<img src="https://user-images.githubusercontent.com/58676453/177293995-292cacaf-c4ba-4991-b233-c2ea4bf08718.png" width="500"/>


## Contributor

카이스트 산업및시스템공학과 [정진규](https://github.com/jeongjingyu)

성균관대학교 글로벌융합학부 [우다연](https://github.com/yeonyeonn)

## Concept

'5년차 카이스트생이 추천하는 카이스트 맛집 13선'

1900년 프랑스에서 시작된 미슐랭 가이드는 손님으로 위장한 전담요원의 평가에 따라 세계 최고의 식당을 선정한다.

2022년 대전에서 시작된 카슐랭 가이드는 손님으로 위장한 5년차 카이스트생의 평가에 따라 카이스트 앞 최고의 식당을 선정한다.

## Brief Overview

- 엄선한 13곳의 카이스트 맛집에 대한 정보와 한줄평을 확인
- 맛집 연락처 확인 및 예약 전화 기능
- 지도를 통해 해당 맛집 위치 확인 및 외부 공유 기능

## Detailed Description

**Start Screen**

<스크린샷>

- LottieFiles를 통해 lottie animation 다운로드 후 배치
- 화면 중간에서 3초 동안 반복해서 animation 진행되도록 display, 앱 아이콘 화면 하단에 배치

**Bottom Navigation Bar & Action Bar**

- BottomNavigationBar를 통해 화면 하단에 3개의 탭(예약, 홈, 지도) 구현
- ActionBar를 통해 화면 상단에 title bar 구현
- ActionBar와 BottomNavigationBar 사이 공간에 Fragment 배치, 각 탭 클릭 시 해당 탭에 대한 Fragment로 이동

**홈 탭(갤러리)**

<img src="https://user-images.githubusercontent.com/58676453/177267193-3680e4f6-1fc8-439b-a2c6-7ad75534dd52.png" width="500"/>


- 앱 시작 시 홈 탭으로 시작
- RecyclerView 위에 CardView 구현
- 각 CardView에 식당 사진 리스트, 식당 이름, 식당 한줄평, 메뉴 보기 텍스트 배치
- 식당 사진 리스트는 CardView 안에 ScrollView로 각 4개의 사진이 horizontal scroll을 통해 확인할 수 있도록 구현
- '메뉴 보기'에 onClickListener로 각 식당의 대표 메뉴판 이미지가 Dialog로 팝업 가능하도록 구현, 다시 이미지 클릭 시 Dialog dismiss

**예약 탭(연락처)**

**지도 탭**

## User Scenario

1. 카슐랭 가이드에 소개된 맛집들을 홈 탭에서 탐방한다.
2. '최진엽등촌샤브샤브'가 맛있어보인다.
3. 지도에서 '최진엽등촌샤브샤브'의 위치를 확인한다.
4. 친구에게 정보를 공유하고 같이 가자고 한다.
5. 연락처에서 '최진엽등촌샤브샤브'를 찾아 전화를 걸어 예약한다.
