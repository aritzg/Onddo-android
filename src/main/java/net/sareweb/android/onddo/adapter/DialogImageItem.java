package net.sareweb.android.onddo.adapter;

public class DialogImageItem {
	
	private String name;
	private int imageId;
	
	public DialogImageItem(String name, int imageId){
		this.name=name;
		this.imageId=imageId;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getImageId() {
		return imageId;
	}
	public void setImageId(int imageId) {
		this.imageId = imageId;
	}
	
	

}
