package net.sareweb.android.onddo.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import net.sareweb.android.onddo.R;
import net.sareweb.android.onddo.model.Picking;
import net.sareweb.lifedroid.model.DLFileEntry;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore.Images;
import android.util.Log;
import android.webkit.MimeTypeMap;
import android.widget.ImageView;

public class ImageUtil {

	public static void resizeFile(File f) {

		try {
			BitmapFactory.Options o = new BitmapFactory.Options();
			o.inJustDecodeBounds = true;
			BitmapFactory.decodeStream(new FileInputStream(f), null, o);
			final int REQUIRED_SIZE = 200;
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
	
	public static void deletePickingImage(Picking p) {
		String imageName = p.getImgName();
		if(imageName==null || "".equals(imageName))return;
		File image = new File(ImageUtil.getMediaStorageDir() + "/" + imageName);
		if(image.exists()){
			image.delete();
		}
	}
	
	public static void downloadImage(Picking p) {
		String imageName = p.getImgName();
		if(imageName==null || "".equals(imageName))return;
		
		try {
			URL url = 
					new URL(OnddoConstants.ONDDO_PROTOCOL 
							+ "://" + OnddoConstants.ONDDO_SERVER 
							+ ":" + OnddoConstants.ONDDO_PORT
							+ "/documents/"+ OnddoConstants.IMAGE_REPOSITORY 
							+ "/" + OnddoConstants.IMAGE_FOLDER 
							+ "/" + p.getImgName());
			
			HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
			urlConnection.setRequestMethod("GET");
	        urlConnection.setDoOutput(true);
	        urlConnection.setRequestProperty("Content-Type", "application/octet-stream");
	        
	        File onddoRoot = new File(getMediaStorageDir());
	        File image = new File(onddoRoot,p.getImgName());
	        FileOutputStream fileOutput = new FileOutputStream(image);
	        
	        InputStream inputStream = urlConnection.getInputStream();
	        
	        byte[] buffer = new byte[1024];
	        int bufferLength = 0; 
	        
	        while ( (bufferLength = inputStream.read(buffer)) > 0 ) {
                fileOutput.write(buffer, 0, bufferLength);
	        }
	        fileOutput.close();
	        
		} catch (Exception e) {
			Log.e(TAG, "Error downloading image",e);
		}
		
	}
	
	public static void setImageToImageView(ImageView imageView, String imageName){
		if(imageName==null || "".equals(imageName)){
			imageView.setImageResource(R.drawable.no_image);
		}
		else {
			File image = new File(ImageUtil.getMediaStorageDir() + "/" + imageName);
			
			if(image.exists()){
				Uri fileUri = Uri.fromFile(image);
				imageView.setImageURI(fileUri);
			}else{
				imageView.setImageResource(R.drawable.no_image);
			}
		}
	}
	

	private static String TAG = "ImageUtil";

}
