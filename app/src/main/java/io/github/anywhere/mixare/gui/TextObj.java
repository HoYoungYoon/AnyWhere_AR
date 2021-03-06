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
package io.github.anywhere.mixare.gui;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;

import java.text.BreakIterator;
import java.util.ArrayList;

import io.github.anywhere.R; ;
import io.github.anywhere.mixare.mixare_ar.DataView;
import io.github.anywhere.mixare.mixare_ar.MixContext;
import io.github.anywhere.mixare.mixare_ar.MixView;

// 화면에 표시될 텍스트 클래스
public class TextObj implements ScreenObj {

	private MixContext mixContext;
	private DataView dataView;
	String txt;	// 텍스트
	float fontSize;	// 폰트 크기
	float width, height;	// 넓이와 높이
	private final float RectWidth = 560.5156f; //사각형 넓이
	private final float RectHeight = 214.16016f; // 사각형 높이
	private  float padingBitmap;
	float areaWidth, areaHeight;	// 표시영역의 넓이와 높이
	String lines[];	// 텍스트 라인
	float lineWidths[];	// 라인의 넓이
	float lineHeight;	// 라인의 높이
	float maxLineWidth;	// 최대 라인 넓이
	float pad;	// 여백?
	// 경계와 배경, 텍스트와 텍스트 그림자의 색상
	int borderColor, bgColor, textColor, textShadowColor;
	boolean underline;	// 밑줄 여부
	int like_cnt;
	String logo;
	private Bitmap logoBit = null;
	// 기본 생성자. 텍스트와 폰트 크기, 최대넓이와 출력될 스크린, 밑줄 여부를 인자로 받음
	public TextObj(String txtInit, int like_cnt ,String logo , float fontSizeInit, float maxWidth,
                   PaintScreen dw, boolean underline) {

		// 경계는 흰색, 배경은 반투명한 검은색, 텍스트는 흰색, 텍스트 그림자는 반투명한 검은색
		// 등으로 기본세팅 
		this(txtInit, fontSizeInit, maxWidth, Color.rgb(255, 255, 255), Color
				.argb(128, 0, 0, 0), Color.rgb(255, 255, 255), Color.argb(64, 0, 0, 0),
				dw.getTextAsc() / 2, dw, underline);

		this.like_cnt  = like_cnt;
		this.logo = logo;
	}

	// 확장된 생성자.
	// 위의 생성자에 추가로 경계색, 배경색, 텍스트색, 그림자색, 여백 등의 정보를 담는다
	public TextObj(String txtInit, float fontSizeInit, float maxWidth,
                   int borderColor, int bgColor, int textColor, int textShadowColor, float pad,
                   PaintScreen dw, boolean underline) {
		
		this.borderColor = borderColor;
		this.bgColor = bgColor;
		this.textColor = textColor;
		this.textShadowColor = textShadowColor;
		this.pad = pad;
		this.underline = underline;

		// prepTxt를 거쳐 준비된 텍스트를 생성한다
		try {
			prepTxt(txtInit, fontSizeInit, maxWidth, dw);
			dataView = MixView.dataView;
			mixContext = dataView.getContext();	// 메인 컨텍스트를 할당

		} catch (Exception ex) {
			ex.printStackTrace();
			prepTxt("TEXT PARSE ERROR", 12, 200, dw);
		}
	}

	// 텍스트 준비? 적절한 크기로 라인을 나누는 것 같다.
	private void prepTxt(String txtInit, float fontSizeInit, float maxWidth,
                         PaintScreen dw) {
		dw.setFontSize(fontSizeInit);

		// 일단 텍스트를 담아두고 폰트의 크기를 설정한다

		txt = txtInit;
		fontSize = fontSizeInit;

		Log.i("Font Size :" , fontSize+"");
		// 여백을 이용해 실제 영역의 넓이를 구하고, 라인의 높이를 구한다 
		areaWidth = maxWidth - pad * 2;
		lineHeight = dw.getTextAsc() + dw.getTextDesc()
				+ dw.getTextLead();

		// 각 라인들은 어레이 리스트로 관리
		ArrayList<String> lineList = new ArrayList<String>();

		// 경계에 따라 라인을 분리한다
		BreakIterator boundary = BreakIterator.getWordInstance();
		Log.i("Boundra : " , txt);
		boundary.setText(txt);
		lines = txt.split("\n");
		int start = boundary.first();
		int end = boundary.next();
		int prevEnd = start;
		while (end != BreakIterator.DONE) {
			String line = txt.substring(start, end);
			String prevLine = txt.substring(start, prevEnd);
			float lineWidth = dw.getTextWidth(line);

			if (lineWidth > areaWidth) {
				Log.i("Start end :" , line);
				Log.i("Prev end :" , prevLine);
				// If the first word is longer than lineWidth 
				// prevLine is empty and should be ignored
				if(prevLine.length()>0)
					lineList.add(prevLine);

				start = prevEnd;
			}


			prevEnd = end;
			end = boundary.next();
		}
		String line = txt.substring(start, prevEnd);
		Log.i("Line Text :" , line);
		lineList.add(line);	// 작업이 끝난 라인은 리스트에 추가

		// lines 배열에 처리된 라인들을 나누어 담는다
	//	lines = new String[lineList.size()];
		lineWidths = new float[lineList.size()];
		lineList.toArray(lines);

		// 최대 라인 넓이 계산
		maxLineWidth = 0;
		for (int i = 0; i < lines.length; i++) {
			lineWidths[i] = dw.getTextWidth(lines[i]);
			if (maxLineWidth < lineWidths[i])
				maxLineWidth = lineWidths[i];
		}
		// 영역 넓이와 높이의 계산
		areaWidth = maxLineWidth;
		areaHeight = lineHeight * lines.length;

		// 최종적인 영역의 계산
		width = areaWidth + pad * 16;
		height = areaHeight + pad * 4;
	}

	// 실제로 출력해주는 메소드. 출력 스크린을 인자로 받는다
	public void paint(PaintScreen dw) {
		// 출력 스크린에 텍스트객체의 각 값들을 설정한다
		dw.setFontSize(fontSize);

		dw.setFill(true);
		dw.setColor(bgColor);
		dw.paintRect(0, 0, RectWidth, RectHeight);

		dw.setFill(false);
		dw.setColor(borderColor);
		dw.paintRect(0, 0, RectWidth, RectHeight);

		dw.paintBitmap(drawBitmap() ,  0 ,0);
		dw.paintBitmap(drawLikeBitmap() , pad+padingBitmap - 20 , 110);
		// 각 라인별로 텍스트를 출력
		for (int i = 0; i < lines.length; i++) {
			String line = lines[i];
			
			// stroke
/* 			dw.setFill(false);
			dw.setStrokeWidth(4);
		    dw.setColor(textShadowColor);
			dw.paintText(pad, pad + lineHeight * i + dw.getTextAsc(), line);
*/
			
			// actual text

			// 출력모드를 설정하고
			dw.setFill(true);
			dw.setStrokeWidth(0);
			dw.setColor(textColor);
			// 실제 텍스트를 뿌려준다
			dw.paintText(pad + padingBitmap, pad + lineHeight * i + dw.getTextAsc(), line, underline);

			Log.i("Text Info  " , "Text : "+line );

		}
		dw.setFontSize(55);
		dw.paintText(pad + padingBitmap+100, pad + lineHeight * 2+10 + dw.getTextAsc(), like_cnt+" Likes!", false);

		Log.i("Text Rect Info" , "Width :  " + width  + "  height : " +height );
		Log.i("Text Area Info" , "area Width : " + areaWidth + "  area Height : " +areaHeight  );
		Log.i("Text Pading  Height :" , " Padding :  " + pad +  "  line Height : " + lineHeight );
	}
	public Bitmap drawLikeBitmap(){
		Resources resources = mixContext.getResources();
		BitmapDrawable bitmapDrawable = (BitmapDrawable)resources.getDrawable(R.drawable.ic_heart_red);

		Bitmap tempbitmap = bitmapDrawable.getBitmap();
		Bitmap bitmap = Bitmap.createScaledBitmap(tempbitmap , 120 ,70 ,true);

		return bitmap;
	}
	public Bitmap drawBitmap(){




			Resources resources = mixContext.getResources();
			BitmapDrawable bitmapDrawable = (BitmapDrawable)resources.getDrawable(R.drawable.anywhere);
			Bitmap tempbitmap = bitmapDrawable.getBitmap();
			logoBit =  Bitmap.createScaledBitmap(tempbitmap, 220 ,200 ,true);
			padingBitmap = logoBit.getWidth();
			Log.i("get With : " , padingBitmap+"");

			
	return logoBit;
	}
	// 넓이를 리턴
	public float getWidth() {
		return width;
	}

	// 높이를 리턴
	public float getHeight() {
		return height;
	}
}
