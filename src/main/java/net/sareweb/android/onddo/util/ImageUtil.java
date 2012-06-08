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

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import net.sareweb.android.onddo.model.Picking;
import net.sareweb.lifedroid.model.DLFileEntry;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.webkit.MimeTypeMap;

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
	
	public static File getOutputMediaFile(String prefix){
		
	    File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
	              Environment.DIRECTORY_PICTURES), "Onddo");
	    if (! mediaStorageDir.exists()){
	        if (! mediaStorageDir.mkdirs()){
	            Log.e(TAG, "failed to create directory");
	            return null;
	        }
	    }

	    String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
	    File mediaFile = new File(mediaStorageDir.getPath() + File.separator +prefix +"_"+ timeStamp + ".jpg");
	   

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
	
	public static DLFileEntry composeDLFileEntry(Picking p){
		DLFileEntry file = new DLFileEntry();
		file.setFolderId(OnddoConstants.IMAGE_FOLDER);
		file.setRepositoryId(OnddoConstants.IMAGE_REPOSITORY);
		file.setSourceFileName(p.getImgName());
		
		//TODO:This is a fucking mess!!
		File f = new File(file.getSourceFileName());
		Uri fileUri = Uri.fromFile(f);
		String fileExtension = MimeTypeMap.getSingleton()
				.getFileExtensionFromUrl(fileUri.toString());
		String mimeType = MimeTypeMap.getSingleton().getMimeTypeFromExtension(
				fileExtension);
		
		file.setMimeType(mimeType);
		return file;
	}
	
	public static void deleteOnddoImages() {
		File folder = new File(getMediaStorageDir());
		String[] list = folder.list();
		if (list != null) {
			for (int i = 0; i < list.length; i++) {
				File entry = new File(folder, list[i]);
				entry.delete();
			}
		}
	}	

	private static String TAG = "ImageUtil";

}
