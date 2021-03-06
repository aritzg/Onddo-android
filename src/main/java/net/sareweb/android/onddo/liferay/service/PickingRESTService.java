package net.sareweb.android.onddo.liferay.service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import net.sareweb.android.onddo.model.Picking;
import net.sareweb.lifedroid.liferay.service.generic.LDRESTService;

import org.springframework.http.HttpMethod;

import android.util.Log;

public class PickingRESTService extends LDRESTService<Picking> {

	public PickingRESTService(String emailAddress, String password) {
		super(emailAddress, password);

	}
	
	public void deletePickingById(String pickingId) {
		String requestURL = _serviceURI
				+ "/picking/delete-picking-by-id/pickingId/" + pickingId;
		run(requestURL, HttpMethod.GET);
	}

	public void updatePicking(Picking picking) {
		String requestURL = _serviceURI + "/picking/update-picking";
		requestURL = addParamToRequestURL(requestURL, "pickingId", picking.getPickingId());
		requestURL = addParamToRequestURL(requestURL, "companyId", picking.getCompanyId());
		requestURL = addParamToRequestURL(requestURL, "userId", picking.getUserId());
		requestURL = addParamToRequestURL(requestURL, "createDate", picking.getCreateDate());
		requestURL = addParamToRequestURL(requestURL, "modifiedDate", picking.getModifiedDate());
		String encodedText ="";
		try {
			encodedText = URLEncoder.encode(picking.getType(), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			Log.e(TAG, "Error encoding text", e);
		}
		requestURL = addParamToRequestURL(requestURL, "type", encodedText);
		requestURL = addParamToRequestURL(requestURL, "lat", picking.getLat());
		requestURL = addParamToRequestURL(requestURL, "lng", picking.getLng());
		requestURL = addParamToRequestURL(requestURL, "moonPhase", picking.getMoonPhase());
		requestURL = addParamToRequestURL(requestURL, "weather", picking.getWeather());
		requestURL = addParamToRequestURL(requestURL, "temperature", picking.getTemperature());
		requestURL = addParamToRequestURL(requestURL, "humidity", picking.getHumidity());
		requestURL = addParamToRequestURL(requestURL, "imgId",picking.getImgId());
		requestURL = addParamToRequestURL(requestURL, "visible",picking.getVisible().toString());
		encodedText ="";
		try {
			encodedText = URLEncoder.encode(picking.getImgName(), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			Log.e(TAG, "Error encoding text", e);
		}
		requestURL = addParamToRequestURL(requestURL, "imgName", encodedText);
		Log.d(TAG, "REST request " + requestURL);
		run(requestURL, HttpMethod.POST);
	}
	
	public void addPicking(Picking picking) {
		String requestURL = _serviceURI + "/picking/add-picking";
		requestURL = addParamToRequestURL(requestURL, "companyId", picking.getCompanyId());
		requestURL = addParamToRequestURL(requestURL, "userId", picking.getUserId());
		Log.d(TAG, "picking.getCreateDate()" + picking.getCreateDate());
		requestURL = addParamToRequestURL(requestURL, "createDate", picking.getCreateDate());
		requestURL = addParamToRequestURL(requestURL, "modifiedDate", picking.getModifiedDate());
		String encodedText ="";
		try {
			encodedText = URLEncoder.encode(picking.getType(), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			Log.e(TAG, "Error encoding text", e);
		}
		requestURL = addParamToRequestURL(requestURL, "type", encodedText);
		requestURL = addParamToRequestURL(requestURL, "lat", picking.getLat());
		requestURL = addParamToRequestURL(requestURL, "lng", picking.getLng());
		requestURL = addParamToRequestURL(requestURL, "moonPhase", picking.getMoonPhase());
		requestURL = addParamToRequestURL(requestURL, "weather", picking.getWeather());
		requestURL = addParamToRequestURL(requestURL, "temperature", picking.getTemperature());
		requestURL = addParamToRequestURL(requestURL, "humidity", picking.getHumidity());
		requestURL = addParamToRequestURL(requestURL, "imgId",picking.getImgId());
		requestURL = addParamToRequestURL(requestURL, "visible",picking.getVisible().toString());
		encodedText ="";
		try {
			encodedText = URLEncoder.encode(picking.getImgName(), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			Log.e(TAG, "Error encoding text", e);
		}
		requestURL = addParamToRequestURL(requestURL, "imgName", encodedText);
		
		Log.d(TAG, "REST request " + requestURL);
		run(requestURL, HttpMethod.POST);
	}
	
	public List<Picking> findByUserId(long userId){
		String requestURL = _serviceURI + "/picking/find-by-user-id";
		requestURL = addParamToRequestURL(requestURL, "userId", userId);
		return getList(requestURL, HttpMethod.POST);
	}
	

	@Override
	public void setPorltetContext() {
		_portletContext = "Onddo-portlet";
	}

}
