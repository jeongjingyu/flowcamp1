# 카슐랭 가이드

## Contributor

정진규, 우다연

## Concept
'5년차 카이스트생이 추천하는 카이스트 맛집 13선'

1900년 프랑스에서 시작된 미슐랭 가이드는 손님으로 위장한 전담요원의 평가에 따라 세계 최고의 식당을 선정한다.

2022년 대전에서 시작된 카슐랭 가이드는 손님으로 위장한(?) 5년차 카이스트생의 평가에 따라 카이스트 앞 최고의 식당(?)을 선정한다.

## Brief Overview

**홈 탭**
- 엄선된 식당의 이름, 사진, 한줄평 display
- ScrollView를 통해 각 식당별 다양한 사진 view 가능
- '메뉴 보기'를 통해 대표 메뉴판 이미지 팝업 display

**예약 탭**

**지도 탭**

## Detailed Description

**Start Screen**

- LottieFiles를 통해 lottie animation 다운로드 후 배치
- 화면 중간에서 3초 동안 반복해서 animation 진행되도록 display, 앱 아이콘 화면 하단에 배치

**Bottom Navigation Bar & Action Bar**

<스크린샷>

- BottomNavigationBar를 통해 화면 하단에 3개의 탭(예약, 홈, 지도) 구현
- ActionBar를 통해 화면 상단에 title bar 구현
- ActionBar와 BottomNavigationBar 사이 공간에 Fragment 배치, 각 탭 클릭 시 해당 탭에 대한 Fragment로 이동

**홈 탭**

<스크린샷>

- RecyclerView 위에 CardView 구현
- 각 CardView에 식당 사진 리스트, 식당 이름, 식당 한줄평, 메뉴 보기 텍스트 배치
- 식당 사진 리스트는 CardView 안에 ScrollView로 각 4개의 사진이 horizontal scroll을 통해 확인할 수 있도록 구현
- '메뉴 보기'에 onClickListener로 각 식당의 대표 메뉴판 이미지가 Dialog로 팝업 가능하도록 구현, 다시 이미지 클릭 시 Dialog dismiss

**예약 탭**

**지도 탭**
