package com.foodpalette_baker;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class PaymentHistoryActivity extends Fragment {
	private View activityView;
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		activityView = inflater.inflate(R.layout.activity_pending_history, container,false);
		
		return activityView;
	}
}