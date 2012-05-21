package net.sareweb.android.onddo.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;

public class ImageUtil {

	public static void resizeFile(File f) {

		try {
			BitmapFactory.Options o = new BitmapFactory.Options();
			o.inJustDecodeBounds = true;
			BitmapFactory.decodeStream(new FileInputStream(f), null, o);
			final int REQUIRED_SIZE = 70;
			int scale = 1;
			while (o.outWidth / scale / 2 >= REQUIRED_SIZE
					&& o.outHeight / scale / 2 >= REQUIRED_SIZE)
				scale *= 2;
			BitmapFactory.Options o2 = new BitmapFactory.Options();
			o2.inSampleSize = scale;
			ByteArrayOutputStream bytes = new ByteArrayOutputStream();
			Bitmap bmp = BitmapFactory.decodeStream(new FileInputStream(f),
					null, o2);
			bmp.compress(Bitmap.CompressFormat.JPEG, 40, bytes);
			FileOutputStream fo = new FileOutputStream(f);
			fo.write(bytes.toByteArray());
			return;

		} catch (Exception e) {
			Log.e(TAG, "Error scaling image", e);
		}

		return;
	}
	
	public static File getOutputMediaFile(){

	    File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
	              Environment.DIRECTORY_PICTURES), "Onddo");
	    if (! mediaStorageDir.exists()){
	        if (! mediaStorageDir.mkdirs()){
	            Log.e(TAG, "failed to create directory");
	            return null;
	        }
	    }

	    String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
	    File mediaFile = new File(mediaStorageDir.getPath() + File.separator +"IMG_"+ timeStamp + ".jpg");
	   

	    return mediaFile;
	}
	
	public static String getMediaStorageDir(){

	    File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
	              Environment.DIRECTORY_PICTURES), "Onddo");
	   
	    return mediaStorageDir.getAbsolutePath();
	}
	
	public static void copyImage(File from, File to) throws IOException {
		FileInputStream inStream = new FileInputStream(from);
		FileOutputStream outStream = new FileOutputStream(to);

		byte[] buffer = new byte[1024];

		int length;
		// copy the file content in bytes
		while ((length = inStream.read(buffer)) > 0) {

			outStream.write(buffer, 0, length);

		}

		inStream.close();
		outStream.close();
	}
	
	public static void copyInputStreamToFile(InputStream from, File to) throws IOException {
		FileInputStream inStream = (FileInputStream)from;
		FileOutputStream outStream = new FileOutputStream(to);

		byte[] buffer = new byte[1024];

		int length;
		// copy the file content in bytes
		while ((length = inStream.read(buffer)) > 0) {

			outStream.write(buffer, 0, length);

		}

		inStream.close();
		outStream.close();
	}
	
	

	private static String TAG = "ImageUtil";

}
