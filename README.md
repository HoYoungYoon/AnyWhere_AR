# AnyWhere_AR

 ## 개요
 
 * 위치기반 AR 서비스 APP 언제 어디서나  찾고자 하는  데이터를  알수있으며, 지도나 사진을 봐도 쉽게 찾지 못하는 사용자들을 위하여 제작
 ## 내용

 * **개발기간** : 2017.1~ 2017.6(5 개월)
 * **참여인원** : 4인 - 안드로이드 개발 1인, Web & Server 1인 , O2O 기획 2인 
 * **참여도** : 70% 
 * **개발 환경**: APM 환경(Apache2.4 , Php5 , Mysql) , Android , Java 
 * **담당 업무** : Andorid AR(Mixare 오픈소스) 및 Android Back-End 
 * **성과** : 한림대 링크사업단 경진대회 은상 ,교내 창업동아리 설립(PlayStore 출시목표), 교내 창업동아리 대표 강원도 대학생 창업 대회 (11월)
 
 ## 참여 내용
 * **Mixare(오픈소스 분석 및 확장)AR** : Android<br>-  벡터와 행렬 연산을 통한 Rendering, AR Gui(Color , 좌표 , 데이터 연동) , GPS 위도, 경도, 고도 데이터 연산 ,카메라의 방위각 연산 과정 분석   
  
 * **Mixare AR Open source 안드로이드 6.0 버전 호환** : Android<br>- Camera2 API 사용<br>- Permission (Camera2 , GPS , Internet) 
 
 * **Marker , Raider , Text or Screen 처리** : Android<br>- 마커 개선(사용자 Like , Server 데이터 연동 , 거리 추가 )
  
 * **GoogleMap 길찾기 및 MapView** : Android<br>- GoogleMap API 사용
  
 * **Server 연동 및 액션처리** :Android<br>- 기존 Mixare AR 데이터 HttpRequest 를 통한 Wiki, Buzz, Twitter 데이터 파싱  -> PHP를 활용한 Json  Mysql 서버 연동
   
 * **UI 담당** : Android<br>-OpenSource "InstagramMaterial" 사용


 ## Workflow 
 ![work flow](https://github.com/HoYoungYoon/AnyWhere_AR/blob/master/img/flow.PNG "Work Flow")
 
  
 ## Process
* **메인Logo Splash (서버 Action)  Main 화면 전환4개의 TabLayout 4개의 Tab (데이터Feed , 지도 , TagCloud ,Login)** <br><br>
![](https://github.com/HoYoungYoon/AnyWhere_AR/blob/master/img/Main.PNG)
![](https://github.com/HoYoungYoon/AnyWhere_AR/blob/master/img/Tab.png)<br>
* **구글맵을 통하여 사용자 주변 데이터 확인 및 길찾기** <br><br>
![](https://github.com/HoYoungYoon/AnyWhere_AR/blob/master/img/Map.png)
![](https://github.com/HoYoungYoon/AnyWhere_AR/blob/master/img/Map_load.png)<br>
* **메인 카메라 버튼 클릭 시 AR을 통하여 사용자 주변의 데이터를 확인** <br><br>
![](https://github.com/HoYoungYoon/AnyWhere_AR/blob/master/img/Ar1.png)
![](https://github.com/HoYoungYoon/AnyWhere_AR/blob/master/img/Ar2.png)

 ## TroubleShooting
 
 * **Mixare OpenSource 호환 및 분석<br>- 2013년 이후로 유지보수가 안되어 5.0 버전 이후로 호환성 문제 발생** : 호환 기능 모듈화<br>Camera -> Camera2 API 사용  , MapActivity -> GoogleMapAPI 사용 , AR Data(HttpRequest 를 통한 데이터 파싱) -> Mysql 서버 연동 
 
 
 * **서버 연동을 통한 AR 구현간에 OnResume 상태에서 과도한 위치 GPS Request 요청과  및 AR 연산으로 인해 카메라 화면 과부하** <br>-
 정확한 위치 파악을 위해 1M 마다 3초 간격으로 Provider를 Nettowrk, GPS Provider 를 통해 requestLocationUpdates 요청에 따라 Rendering 연산 진행 ->  Request 요청 거리와 시간을 10M ,10초 간격 조절 하고 Provider 를 2개 다 사용하기보단 GPS Provider 한가지만 사용하는 것이 배터리 사용 및 연산과정으로 AR 화면의 성능 증가  
 
 * **Request 요청마다 사용자의 주변 데이터만 받아오는 과정에서 마커의 객체 데이터 모델의 Add 과정 중 중복 객체 발생** <br>- 
 Contains 메소드를 활용하여 List의 중복 최소화
 <pre><code>
	public void addMarkers(List<Marker> markers) {
		// 추가 이전 리스트의 사이즈 로그 생성 
		Log.v(MixView.TAG, "Marker before: "+markerList.size());
		// 인자로 받은 마커들을 리스트에 추가한다(중복은 방지)
		for(Marker ma:markers) {
			if(!markerList.contains(ma))
				markerList.add(ma);
		}
		// 추가 이후 리스트의 사이즈 로그 생성
		Log.d(MixView.TAG, "Marker count: "+markerList.size());
	}
 </code> </pre>
  
 
    
 
 
