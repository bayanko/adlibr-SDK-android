/*
 * adlibr - Library for mobile AD mediation.
 * http://adlibr.com
 * Copyright (c) 2012 Mocoplex, Inc.  All rights reserved.
 * Licensed under the BSD open source license.
 */

/*
 * confirmed compatible with cauly SDK 2.45.43.5
 */

package test.adlib.project.ads;

import com.sktelecom.tad.sdk.AdListener;
import com.sktelecom.tad.sdk.AdListenerResponse;

import com.mocoplex.adlib.SubAdlibAdViewCore;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;

public class SubAdlibAdViewTAD extends SubAdlibAdViewCore  {
	
	protected com.sktelecom.tad.AdView ad;

	public SubAdlibAdViewTAD(Context context) {
		this(context,null);
	}	
	
	public SubAdlibAdViewTAD(Context context, AttributeSet attrs) {
		super(context, attrs);
		
	}
		
	public void query()
	{
		// T-AD ID 는 MANIFEST 파일에 입력해주세요. (T-AD SDK 문서 참조)
		
		// T-AD is only showing on below resolution.
		int h = 0;
		DisplayMetrics d = this.getResources().getDisplayMetrics();
		if(d.widthPixels == 800 && d.heightPixels == 1280 || d.widthPixels == 1280 && d.heightPixels == 800)
			h = 120;
		else if(d.widthPixels == 720 && d.heightPixels == 1280 || d.widthPixels == 1280 && d.heightPixels == 720)
			h = 108;
		else if(d.widthPixels == 600 && d.heightPixels == 1024 || d.widthPixels == 1024 && d.heightPixels == 600)
			h = 90;
		else if(d.widthPixels == 540 && d.heightPixels == 960 || d.widthPixels == 960 && d.heightPixels == 540)
			h = 81;
		else if(d.widthPixels == 480 && d.heightPixels == 854 || d.widthPixels == 854 && d.heightPixels == 480)
			h = 72;		
		else if(d.widthPixels == 480 && d.heightPixels == 800 || d.widthPixels == 800 && d.heightPixels == 480)
			h = 72;
		else if(d.widthPixels == 320 && d.heightPixels == 480 || d.widthPixels == 480 && d.heightPixels == 320)
			h = 48;
		else if(d.widthPixels == 240 && d.heightPixels == 320 || d.widthPixels == 320 && d.heightPixels == 240)
			h = 36;
		
		if (h == 0)
			return;
		
		ad = com.sktelecom.tad.AdView.createAdView(this.getContext());		
		ad.setAdListener(new AdListener(){

			@Override
			public void onFailedToReceiveAd(AdListenerResponse arg0) {
				// 광고 수신에 실패했다. 바로 다음 플랫폼을 보인다.
				failed();
			}

			@Override
			public void onReceiveAd() {
				gotAd();	
			}});
		
		this.addView(ad);
		gotAd();	// 광고 수신 여부를 확인하기위해 광고뷰를 화면에 보입니다. 화면에 뷰가 보여야 콜백을 받을 수 있습니다.
	}

	
	public void clearAdView()
	{
		ad = null;
		
		super.clearAdView();
	}
	
	public void onResume()
	{
		super.onResume();
	}
	public void onPause()
	{
		super.onPause();
	}	
}