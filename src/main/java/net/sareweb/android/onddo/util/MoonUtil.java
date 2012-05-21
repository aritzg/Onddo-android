package net.sareweb.android.onddo.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.util.Log;

import net.sareweb.android.onddo.R;
import net.sareweb.android.onddo.dialog.DialogImageOption;

public class MoonUtil {

	private static List<DialogImageOption> options;
	private static Map<String, DialogImageOption> optionsMap;

	public static List<DialogImageOption> getMoonDialogOptions() {
		if (options == null) {
			initOptions();
		}
		return options;
	}

	public static Map<String, DialogImageOption> getMoonDialogOptionsMap() {
		if (optionsMap == null) {
			initOptions();
		}
		return optionsMap;
	}

	private static void initOptions() {
		options = new ArrayList<DialogImageOption>();
		optionsMap = new HashMap<String, DialogImageOption>();
		DialogImageOption dialogImageOption01 = new DialogImageOption(
				R.drawable.m01, R.string.m01, "m01");
		options.add(dialogImageOption01);
		optionsMap.put(dialogImageOption01.getValue(), dialogImageOption01);

		DialogImageOption dialogImageOption02 = new DialogImageOption(
				R.drawable.m02, R.string.m02, "m02");
		options.add(dialogImageOption02);
		optionsMap.put(dialogImageOption02.getValue(), dialogImageOption02);

		DialogImageOption dialogImageOption03 = new DialogImageOption(
				R.drawable.m03, R.string.m03, "m03");
		options.add(dialogImageOption03);
		optionsMap.put(dialogImageOption03.getValue(), dialogImageOption03);

		DialogImageOption dialogImageOption04 = new DialogImageOption(
				R.drawable.m04, R.string.m04, "m04");
		options.add(dialogImageOption04);
		optionsMap.put(dialogImageOption04.getValue(), dialogImageOption04);

		DialogImageOption dialogImageOption05 = new DialogImageOption(
				R.drawable.m05, R.string.m05, "m05");
		options.add(dialogImageOption05);
		optionsMap.put(dialogImageOption05.getValue(), dialogImageOption05);

		DialogImageOption dialogImageOption06 = new DialogImageOption(
				R.drawable.m06, R.string.m06, "m06");
		options.add(dialogImageOption06);
		optionsMap.put(dialogImageOption06.getValue(), dialogImageOption06);

		DialogImageOption dialogImageOption07 = new DialogImageOption(
				R.drawable.m07, R.string.m07, "m07");
		options.add(dialogImageOption07);
		optionsMap.put(dialogImageOption07.getValue(), dialogImageOption07);

		DialogImageOption dialogImageOption08 = new DialogImageOption(
				R.drawable.m08, R.string.m08, "m08");
		options.add(dialogImageOption08);
		optionsMap.put(dialogImageOption08.getValue(), dialogImageOption08);

		DialogImageOption dialogImageOption09 = new DialogImageOption(
				R.drawable.m09, R.string.m09, "m09");
		options.add(dialogImageOption09);
		optionsMap.put(dialogImageOption09.getValue(), dialogImageOption09);

		DialogImageOption dialogImageOption10 = new DialogImageOption(
				R.drawable.m10, R.string.m10, "m10");
		options.add(dialogImageOption10);
		optionsMap.put(dialogImageOption10.getValue(), dialogImageOption10);

		DialogImageOption dialogImageOption11 = new DialogImageOption(
				R.drawable.m11, R.string.m11, "m11");
		options.add(dialogImageOption11);
		optionsMap.put(dialogImageOption11.getValue(), dialogImageOption11);

		DialogImageOption dialogImageOption12 = new DialogImageOption(
				R.drawable.m12, R.string.m12, "m12");
		options.add(dialogImageOption12);
		optionsMap.put(dialogImageOption12.getValue(), dialogImageOption12);

		DialogImageOption dialogImageOption13 = new DialogImageOption(
				R.drawable.m13, R.string.m13, "m13");
		options.add(dialogImageOption13);
		optionsMap.put(dialogImageOption13.getValue(), dialogImageOption13);

		DialogImageOption dialogImageOption14 = new DialogImageOption(
				R.drawable.m14, R.string.m14, "m14");
		options.add(dialogImageOption14);
		optionsMap.put(dialogImageOption14.getValue(), dialogImageOption14);

		DialogImageOption dialogImageOption15 = new DialogImageOption(
				R.drawable.m15, R.string.m15, "m15");
		options.add(dialogImageOption15);
		optionsMap.put(dialogImageOption15.getValue(), dialogImageOption15);

		DialogImageOption dialogImageOption16 = new DialogImageOption(
				R.drawable.m16, R.string.m16, "m16");
		options.add(dialogImageOption16);
		optionsMap.put(dialogImageOption16.getValue(), dialogImageOption16);

		DialogImageOption dialogImageOption17 = new DialogImageOption(
				R.drawable.m17, R.string.m17, "m17");
		options.add(dialogImageOption17);
		optionsMap.put(dialogImageOption17.getValue(), dialogImageOption17);

		DialogImageOption dialogImageOption18 = new DialogImageOption(
				R.drawable.m18, R.string.m18, "m18");
		options.add(dialogImageOption18);
		optionsMap.put(dialogImageOption18.getValue(), dialogImageOption18);

		DialogImageOption dialogImageOption19 = new DialogImageOption(
				R.drawable.m19, R.string.m19, "m19");
		options.add(dialogImageOption19);
		optionsMap.put(dialogImageOption19.getValue(), dialogImageOption19);

		DialogImageOption dialogImageOption20 = new DialogImageOption(
				R.drawable.m20, R.string.m20, "m20");
		options.add(dialogImageOption20);
		optionsMap.put(dialogImageOption20.getValue(), dialogImageOption20);

		DialogImageOption dialogImageOption21 = new DialogImageOption(
				R.drawable.m21, R.string.m21, "m21");
		options.add(dialogImageOption21);
		optionsMap.put(dialogImageOption21.getValue(), dialogImageOption21);

		DialogImageOption dialogImageOption22 = new DialogImageOption(
				R.drawable.m22, R.string.m22, "m22");
		options.add(dialogImageOption22);
		optionsMap.put(dialogImageOption22.getValue(), dialogImageOption22);

		DialogImageOption dialogImageOption23 = new DialogImageOption(
				R.drawable.m23, R.string.m23, "m23");
		options.add(dialogImageOption23);
		optionsMap.put(dialogImageOption23.getValue(), dialogImageOption23);

		DialogImageOption dialogImageOption24 = new DialogImageOption(
				R.drawable.m24, R.string.m24, "m24");
		options.add(dialogImageOption24);
		optionsMap.put(dialogImageOption24.getValue(), dialogImageOption24);

	}

	public static String calculatePhase(Date date) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(date);
		int year = cal.get(GregorianCalendar.YEAR);
		
		double phase; // Moon phase
		int cent; // Century number (1979 = 20)
		int epact; // Age of the moon on Jan. 1
		int diy = cal.get(cal.DAY_OF_YEAR);
		int golden; // Moon's golden number

		cent = (year / 100) + 1; // Century number
		golden = (year % 19) + 1; // Golden number
		epact = ((11 * golden) + 20 // Golden number
				+ (((8 * cent) + 5) / 25) - 5 // 400 year cycle
		- (((3 * cent) / 4) - 12)) % 30; // Leap year correction
		if (epact <= 0)
			epact += 30; // Age range is 1 .. 30
		if ((epact == 25 && golden > 11) || epact == 24)
			epact++;


		phase = ((   (((diy + epact) * 6.0) + 11) % 177)  / 22.0) * 3;
		int phaseInt = Double.valueOf(phase).intValue();
		Log.d(TAG, "PHASE " + phaseInt);
		if(phaseInt < 10 ) return "m0" + phaseInt;
		return "m" + phaseInt;
	}


	private static final String TAG ="MoonUtil";
}
