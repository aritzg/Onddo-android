package net.sareweb.android.onddo.dialog;

public class DialogImageOption {

	private int imgResId;
	private int textResId;
	private String value;

	public DialogImageOption(int imgResId, int textResId, String value) {
		this.imgResId = imgResId;
		this.textResId = textResId;
		this.value = value;
	}

	public int getImgResId() {
		return imgResId;
	}

	public void setImgResId(int imgResId) {
		this.imgResId = imgResId;
	}

	public int getTextResId() {
		return textResId;
	}

	public void setTextResId(int textResId) {
		this.textResId = textResId;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
