# 🏡 Share House

<br>

원하는 주택을 찾아 쉐어할 목적으로 아파트 및 연립다세대 주택의 거래 정보를 조회하고 룸메이트를 구할 수 있는 서비스
<div>
<p align="center">
 <img src="https://img.shields.io/badge/JavaScript-ES6+-green?logo=javascript">
 <img src="https://img.shields.io/badge/Vue.js-v2.6.11-green?logo=vue.js">
 <img src="https://img.shields.io/badge/Java-v1.8-blue?logo=java">
 <img src="https://img.shields.io/badge/spring boot-v2.4.3-blue?logo=spring">
</p>    
</div>

<br>

## Team

> 서비스명: Share House
> 팀명: 이진Bean
> 개발 기간: 2021.05.20 ~ 2021.05.27

<br>

### 팀원 역할
|이름|역할|내용|
|----|----|----|
|홍진빈|팀장|프론트엔드 개발|
|윤이진|팀원|백엔드 개발|

<br>


## 프로젝트 설계

### 📈 Gantt Chart

![간트차트](https://user-images.githubusercontent.com/30489264/128585575-f9f374c1-40b5-4a7b-9c52-f7c41d9648dd.PNG)

<br>

### E-R Diagram

#### 논리적 모델링

![image](https://user-images.githubusercontent.com/30489264/129457804-ba4805c2-29fa-4487-ab8e-ce94f10a98f7.png)

#### MySQL EER Diagram
![image](https://user-images.githubusercontent.com/30489264/129457814-fa8808e5-4889-439a-8587-b2b33f4b42ab.png)

<br>
<br>


### 와이어 프레임

![image](https://user-images.githubusercontent.com/30489264/129457831-9dfc04c1-278e-4502-a905-ba2234537dc3.png)
![image](https://user-images.githubusercontent.com/30489264/129457838-b1ad0396-aa2f-4432-aa60-5176554989dc.png)
![image](https://user-images.githubusercontent.com/30489264/129457839-de451f7e-d4fe-4f81-b188-b340592cd140.png)
![image](https://user-images.githubusercontent.com/30489264/129457840-cb7e5369-93c2-4793-9ba4-3ed3a2001a21.png)
![image](https://user-images.githubusercontent.com/30489264/129457841-990590df-d164-4a46-a5d4-b9f772a90c6a.png)
![image](https://user-images.githubusercontent.com/30489264/129457845-9c595706-9cb6-47d8-bc22-fb40a412e842.png)
![image](https://user-images.githubusercontent.com/30489264/129457847-8227c17c-b7fd-46f8-a324-bfdcfcd4c9fd.png)
![image](https://user-images.githubusercontent.com/30489264/129457854-80035b8f-4bd3-4838-aed7-1ad7ef4919e3.png)

<br>

## 프로젝트 구조
![image](https://user-images.githubusercontent.com/30489264/129457907-8c970723-4728-48fc-858f-6a8fda56ef86.png)

<br>

### 개발 후기

<br>

#### 어려웠던 점

- 채팅 기능 구현을 위해 시도해보았던 WebSocket의 구현 방식이 프로젝트 전체 구조에 맞지 않아 시행 착오를 겪었다
- 채팅 로그를 처리하는 Business Logic이 적절하지 않았다
  + 매번 채팅을 입력할 때 마다 Database에 Insert를 시도했다
  + 프로젝트 규모가 커지면 DB의 부하가 커질 수 있다 ❗❗
 
<br>

#### To-do List
- [X] WebSocket의 다양한 구현 방식에 대해 이해하기
- [ ] 기존의 채팅 로그 저장 방식(MySQL Database)을 파일 입출력 형식으로 바꾸기