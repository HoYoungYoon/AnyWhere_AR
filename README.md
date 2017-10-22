# AnyWhere_AR

 ### 개요
 * 위치기반 AR 서비스 APP 언제 어디서나  찾고자 하는  데이터를  알수있으며, 지도나 사진을 봐도 쉽게 찾지 못하는 사용자들을 위하여 제작
 
 ### 내용
 * 개발기간 : 2017.1~ 2017.6(5 개월)
 * 참여인원 : 4인 - 안드로이드 개발, Web & Server , O2O 기획 2인 
 * 참여도 : 60% 
 * 개발 Tool: APM 환경(Apache24 , Php6 , Mysql) , Android 
 * 담당 업무 : Andorid AR 및 Android Back-End 
 * 성과 : 한림대 링크사업단 경진대회 은상 ,교내 창업동아리 설립(PlayStore 출시목표), 교내 창업동아리 대표 강원도 대학생 창업 대회 (11월)
 
 ### 참여 내용
 * Mixare(오픈소스 분석 및 확장)AR : Android<br>- 기존 Mixare AR 데이터 파싱 -> Server Action 변경   
  
 * 안드로이드 6.0 버전 호환 : Android<br>- Camera2 API 사용<br>- Permission (Camera2 , GPS , Internet)
 
 * Marker , Raider , Text or Screen 처리 : Android<br>- 마커 개선(사용자 Like , Server 데이터 연동 , 거리 순 정렬 )
  
 * GoogleMap 길찾기 및 MapView : Android<br>- GoogleMap API 사용
  
 * Server 연동 및 액션처리 :Android<br>- PHP를 활용한 Json 서버 연동(HTTP Request) 
   
 * UI 담당 : Android<br>-OpenSource "InstagramMaterial" 사용
  
  
 ### Process
*메인Logo Splash (서버 Action)  Main 화면 전환4개의 TabLayout 4개의 Tab (데이터Feed , 지도 , TagCloud ,Login)*<br>
![](https://github.com/HoYoungYoon/AnyWhere_AR/blob/master/img/Main.PNG)
![](https://github.com/HoYoungYoon/AnyWhere_AR/blob/master/img/Tab.png)<br>
*구글맵을 통하여 사용자 주변 데이터 확인 및 길찾기 *<br>

![](https://github.com/HoYoungYoon/AnyWhere_AR/blob/master/img/Map.png)
![](https://github.com/HoYoungYoon/AnyWhere_AR/blob/master/img/Map_load.png)<br>
*메인 카메라 버튼 클릭 시 AR을 통하여 사용자의 주변 데이터 AR *<br>
![](https://github.com/HoYoungYoon/AnyWhere_AR/blob/master/img/Ar1.png)
![](https://github.com/HoYoungYoon/AnyWhere_AR/blob/master/img/Ar2.png)




 ### Workflow 
 ![work flow](https://github.com/HoYoungYoon/AnyWhere_AR/blob/master/img/flow.PNG "Work Flow")
 
 ### TroubleShooting
 
 * Mixare OpenSource 호환 및 분석<br>- 2013년 이후로 유지보수가 안되어 5.0 버전 이후로 호환성 문제 발생 : 호환 기능 모듈화<br>Camera -> Camera2 API 사용  , MapActivity -> GoogleMapAPI 사용 , AR Data(HttpRequest) -> APM Server
 
 * 서버 연동을 통한 AR 구현간에 OnResume 상태에서 지속적인 위치 Request 요청과 서버 연동으로 인해 Device 과부화 발생<br>- Splash 화면을 두어 Handler Thread를 활용한 Server 데이터를 미리 받아오며 requestLocationUpdates GPS 요청을 거리와 시간을 조절하여 해결
 
 * 사용자 주변의 근접한 순으로  AR 데이터 정렬  과정에서  시간복잡도 증가로 인한 성능저하 <br>-  자바 Collections sort() 메소드 활용하여  
 
    
 
 
