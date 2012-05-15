package net.sareweb.android.onddo.model;

import net.sareweb.lifedroid.annotation.LDField;
import net.sareweb.lifedroid.model.LDObject;

public class Picking extends LDObject {
	
	@LDField(id=true, sqliteType=LDField.SQLITE_TYPE_INTEGER)
	private Long pickingId;
	
	@LDField(sqliteType=LDField.SQLITE_TYPE_INTEGER)
	private Long companyId;
	
	@LDField(sqliteType=LDField.SQLITE_TYPE_INTEGER)
	private Long userId;

	@LDField(sqliteType=LDField.SQLITE_TYPE_DATE)
	private Long createDate;

	@LDField(sqliteType=LDField.SQLITE_TYPE_DATE)
	private Long modifiedDate;
	
	@LDField
	private String type;
	
	@LDField(sqliteType=LDField.SQLITE_TYPE_REAL)
	private Double lat;
	
	@LDField(sqliteType=LDField.SQLITE_TYPE_REAL)
	private Double lng;
	
	@LDField
	private String moonPhase;
	
	@LDField
	private String weather;
	
	@LDField(sqliteType=LDField.SQLITE_TYPE_REAL)
	private Double temperature;
	
	@LDField(sqliteType=LDField.SQLITE_TYPE_REAL)
	private Double humidity;

	public Long getPickingId() {
		return pickingId;
	}

	public void setPickingId(Long pickingId) {
		this.pickingId = pickingId;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Long createDate) {
		this.createDate = createDate;
	}

	public Long getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Long modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public Double getLng() {
		return lng;
	}

	public void setLng(Double lng) {
		this.lng = lng;
	}

	public String getMoonPhase() {
		return moonPhase;
	}

	public void setMoonPhase(String moonPhase) {
		this.moonPhase = moonPhase;
	}

	public String getWeather() {
		return weather;
	}

	public void setWeather(String weather) {
		this.weather = weather;
	}

	public Double getTemperature() {
		return temperature;
	}

	public void setTemperature(Double temperature) {
		this.temperature = temperature;
	}

	public Double getHumidity() {
		return humidity;
	}

	public void setHumidity(Double humidity) {
		this.humidity = humidity;
	}
	
}
