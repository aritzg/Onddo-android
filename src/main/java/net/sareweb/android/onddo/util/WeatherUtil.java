package net.sareweb.android.onddo.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sareweb.android.onddo.R;
import net.sareweb.android.onddo.dialog.DialogImageOption;

public class WeatherUtil {
	
	private static List<DialogImageOption> options;
	private static Map<String, DialogImageOption> optionsMap;
	
	public static List<DialogImageOption> getWeatherDialogOptions(){
		if(options==null){
			initOptions();
		}
		return options;
	}
	
	public static Map<String, DialogImageOption> getWeatherDialogOptionsMap(){
		if(optionsMap==null){
			initOptions();
		}
		return optionsMap;
	}

	private static void initOptions() {
		options=new ArrayList<DialogImageOption>();
		optionsMap=new HashMap<String, DialogImageOption>();
		DialogImageOption dialogImageOption01 = new DialogImageOption(R.drawable.w01, R.string.w01, "w01"); 
		options.add(dialogImageOption01);optionsMap.put(dialogImageOption01.getValue(), dialogImageOption01);
		
		DialogImageOption dialogImageOption02 = new DialogImageOption(R.drawable.w02, R.string.w02, "w02");
		options.add(dialogImageOption02);optionsMap.put(dialogImageOption02.getValue(), dialogImageOption02);
		
		DialogImageOption dialogImageOption03 = new DialogImageOption(R.drawable.w03, R.string.w03, "w03");
		options.add(dialogImageOption03);optionsMap.put(dialogImageOption03.getValue(), dialogImageOption03);
		
		DialogImageOption dialogImageOption04 = new DialogImageOption(R.drawable.w04, R.string.w04, "w04");
		options.add(dialogImageOption04);optionsMap.put(dialogImageOption04.getValue(), dialogImageOption04);
		
		DialogImageOption dialogImageOption05 = new DialogImageOption(R.drawable.w05, R.string.w05, "w05");
		options.add(dialogImageOption05);optionsMap.put(dialogImageOption05.getValue(), dialogImageOption05);
		
		DialogImageOption dialogImageOption06 = new DialogImageOption(R.drawable.w06, R.string.w06, "w06");
		options.add(dialogImageOption06);optionsMap.put(dialogImageOption06.getValue(), dialogImageOption06);
		
		DialogImageOption dialogImageOption07 = new DialogImageOption(R.drawable.w07, R.string.w07, "w07");
		options.add(dialogImageOption07);optionsMap.put(dialogImageOption07.getValue(), dialogImageOption07);
		
		DialogImageOption dialogImageOption08 = new DialogImageOption(R.drawable.w08, R.string.w08, "w08");
		options.add(dialogImageOption08);optionsMap.put(dialogImageOption08.getValue(), dialogImageOption08);
		
		DialogImageOption dialogImageOption09 = new DialogImageOption(R.drawable.w09, R.string.w09, "w09");
		options.add(dialogImageOption09);optionsMap.put(dialogImageOption09.getValue(), dialogImageOption09);
		
		DialogImageOption dialogImageOption10 = new DialogImageOption(R.drawable.w10, R.string.w10, "w10");
		options.add(dialogImageOption10);optionsMap.put(dialogImageOption10.getValue(), dialogImageOption10);
		
		DialogImageOption dialogImageOption11 = new DialogImageOption(R.drawable.w11, R.string.w11, "w11");
		options.add(dialogImageOption11);optionsMap.put(dialogImageOption11.getValue(), dialogImageOption11);
		
		DialogImageOption dialogImageOption12 = new DialogImageOption(R.drawable.w12, R.string.w12, "w12");
		options.add(dialogImageOption12);optionsMap.put(dialogImageOption12.getValue(), dialogImageOption12);
		
		DialogImageOption dialogImageOption13 = new DialogImageOption(R.drawable.w13, R.string.w13, "w13");
		options.add(dialogImageOption13);optionsMap.put(dialogImageOption13.getValue(), dialogImageOption13);
		
		DialogImageOption dialogImageOption14 = new DialogImageOption(R.drawable.w14, R.string.w14, "w14");
		options.add(dialogImageOption14);optionsMap.put(dialogImageOption14.getValue(), dialogImageOption14);
		
		DialogImageOption dialogImageOption15 = new DialogImageOption(R.drawable.w15, R.string.w15, "w15");
		options.add(dialogImageOption15);optionsMap.put(dialogImageOption15.getValue(), dialogImageOption15);
		
		DialogImageOption dialogImageOption16 = new DialogImageOption(R.drawable.w16, R.string.w16, "w16");
		options.add(dialogImageOption16);optionsMap.put(dialogImageOption16.getValue(), dialogImageOption16);
		
		DialogImageOption dialogImageOption17 = new DialogImageOption(R.drawable.w17, R.string.w17, "w17");
		options.add(dialogImageOption17);optionsMap.put(dialogImageOption17.getValue(), dialogImageOption17);
		
		DialogImageOption dialogImageOption18 = new DialogImageOption(R.drawable.w18, R.string.w18, "w18");
		options.add(dialogImageOption18);optionsMap.put(dialogImageOption18.getValue(), dialogImageOption18);
		
		DialogImageOption dialogImageOption19 = new DialogImageOption(R.drawable.w19, R.string.w19, "w19");
		options.add(dialogImageOption19);optionsMap.put(dialogImageOption19.getValue(), dialogImageOption19);
		
		DialogImageOption dialogImageOption20 = new DialogImageOption(R.drawable.w20, R.string.w20, "w20");
		options.add(dialogImageOption20);optionsMap.put(dialogImageOption20.getValue(), dialogImageOption20);
		
	}

}
