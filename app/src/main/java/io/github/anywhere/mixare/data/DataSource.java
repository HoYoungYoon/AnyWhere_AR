 /*
 * Copyright (C) 2010- Peer internet solutions
 * 
 * This file is part of mixare.
 * 
 * This program is free software: you can redistribute it and/or modify it 
 * under the terms of the GNU General Public License as published by 
 * the Free Software Foundation, either version 3 of the License, or 
 * (at your option) any later version. 
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS 
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License 
 * for more details. 
 * 
 * You should have received a copy of the GNU General Public License along with 
 * this program. If not, see <http://www.gnu.org/licenses/>
 */

package io.github.anywhere.mixare.data;

 import android.content.res.Resources;
 import android.graphics.Bitmap;
 import android.graphics.BitmapFactory;
 import android.graphics.Color;

 import io.github.anywhere.R; ;
 import io.github.anywhere.mixare.mixare_ar.MixListView;


 /**
  * @author hannes
  *
  */

 // 데이터 소스를 실질적으로 다루는 클래스
 public class DataSource {

     // 데이터 소스와 데이터 포맷은 비슷해 보이지만 전혀 다르다.
     // 데이터 소스는 데이터가 어디서 왔는지, 데이터 포맷은 어떤 형식으로 포맷되었는지를 가르킨다.
     // 이에 대한 이해는 똑같은 데이터 포맷으로 여러가지의 데이터 소스를 실험하는데에 필수적이다


     // 데이터 소스와 데이터 포맷의 열거형 변수
     public enum DATASOURCE { WIKIPEDIA, BUZZ, TWITTER, OSM, OWNURL};
     public enum DATAFORMAT { WIKIPEDIA, BUZZ, TWITTER, OSM, MIXARE};

     /** 기본 URL */
     // 위키피디아
     private static final String WIKI_BASE_URL = "http://ws.geonames.org/findNearbyWikipediaJSON";
     //private static final String WIKI_BASE_URL =	"file:///sdcard/wiki.json";

     // 트위터
     private static final String TWITTER_BASE_URL = "http://search.twitter.com/search.json";

     // 구글 버즈
     private static final String BUZZ_BASE_URL = "https://www.googleapis.com/buzz/v1/activities/search?alt=json&max-results=20";

     // OSM(OpenStreetMap)
     // OpenStreetMap API는 http://wiki.openstreetmap.org/wiki/Xapi 를 참고
     // 예를 들어, 철도만 사용할 경우:
     //private static final String OSM_BASE_URL =	"http://www.informationfreeway.org/api/0.6/node[railway=station]";
     //private static final String OSM_BASE_URL =	"http://xapi.openstreetmap.org/api/0.6/node[railway=station]";
     private static final String OSM_BASE_URL =		"http://osmxapi.hypercube.telascience.org/api/0.6/node[railway=station]";
     // 모든 객체들은 이름이 명명되어 있다
     //String OSM_URL = "http://xapi.openstreetmap.org/api/0.6/node[name=*]";

     // 주의할것! 방대한 양의 데이터(MB단위 이상)을 산출할 때에는, 작은 반경이나 특정한 쿼리만을 사용해야한다
     /** URL 부분 끝 */


     // 아이콘들. 트위터와 버즈
     public static Bitmap twitterIcon;
     public static Bitmap buzzIcon;

     // 기본 생성자
     public DataSource() {

     }

     // 리소스로부터 각 아이콘 생성
     public static void createIcons(Resources res) {
         twitterIcon= BitmapFactory.decodeResource(res, R.drawable.twitter);
         buzzIcon= BitmapFactory.decodeResource(res, R.drawable.buzz);
     }

     // 아이콘 비트맵의 게터
     public static Bitmap getBitmap(DATASOURCE ds) {
         Bitmap bitmap=null;
         switch (ds) {
             case TWITTER: bitmap=twitterIcon; break;
             case BUZZ: bitmap=buzzIcon; break;
         }
         return bitmap;
     }

     // 데이터 소스로부터 데이터 포맷을 추출
     public static DATAFORMAT dataFormatFromDataSource(DATASOURCE ds) {
         DATAFORMAT ret;
         // 소스 형식에 따라 포맷을 할당한다
         switch (ds) {
             case WIKIPEDIA: ret= DATAFORMAT.WIKIPEDIA; break;
             case BUZZ: ret= DATAFORMAT.BUZZ; break;
             case TWITTER: ret= DATAFORMAT.TWITTER; break;
             case OSM: ret= DATAFORMAT.OSM; break;
             case OWNURL: ret= DATAFORMAT.MIXARE; break;
             default: ret= DATAFORMAT.MIXARE; break;
         }
         return ret;	// 포맷 리턴
     }

     // 각 정보들로 완성된 URL 리퀘스트를 생성
     public static String createRequestURL(DATASOURCE source, double lat, double lon, double alt, float radius, String locale) {
         String ret="";	// 결과 스트링

         // 소스에 따라 주소 할당. 우선 상수로 설정된 값들을 할당한다
         switch(source) {

             case WIKIPEDIA:
                 ret = WIKI_BASE_URL;
             break;

             case BUZZ:
                 ret = BUZZ_BASE_URL;
             break;

             case TWITTER:
                 ret = TWITTER_BASE_URL;
             break;

             case OSM:
                 ret = OSM_BASE_URL;
             break;

             case OWNURL:
                 ret = MixListView.customizedURL;
             break;

         }

         // 파일로부터 읽는 것이 아니라면
         if (!ret.startsWith("file://")) {

             // 각 소스에 따른 URL 리퀘스트를 완성한다
             switch(source) {
             // 위키피디아
             case WIKIPEDIA:
                 ret+=
                 "?lat=" + lat +
                 "&lng=" + lon +
                 "&radius="+ radius +
                 "&maxRows=50" +
                 "&lang=" + locale;
             break;

             // 버즈
             case BUZZ:
                 ret+=
                 "&lat=" + lat+
                 "&lon=" + lon +
                 "&radius="+ radius*1000;
             break;

             // 트위터
             case TWITTER:
                 ret+=
                 "?geocode=" + lat + "%2C" + lon + "%2C" +
                 Math.max(radius, 1.0) + "km" ;
             break;

             // OSM(OpenStreetMap)
             case OSM:
                 ret+= XMLHandler.getOSMBoundingBox(lat, lon, radius);
             break;

             // 자체 URL
             case OWNURL:
                 ret+=
                 "?latitude=" + Double.toString(lat) +
                 "&longitude=" + Double.toString(lon) +
                 "&altitude=" + Double.toString(alt) +
                 "&radius=" + Double.toString(radius);
             break;

             }

         }

         return ret;
     }

     // 각 소스에 따른 색을 리턴
     public static int getColor() {
         int ret;
         ret = Color.RED;
         return ret;
     }

 }
