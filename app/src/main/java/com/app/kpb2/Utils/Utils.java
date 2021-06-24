package com.app.kpb2.Utils;

import android.graphics.Rect;
import android.text.TextPaint;

import com.fasterxml.jackson.databind.util.ISO8601DateFormat;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Currency;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//import com.mapbox.mapboxsdk.geometry.LatLng;

/**
 * Created by fiyyanp on 2/1/2018.
 */

public class Utils {

    public static int getHeightOfMultiLineText(String text, int textSize, int maxWidth) {
        TextPaint paint = new TextPaint();
        paint.setTextSize(textSize);
        int index = 0;
        int lineCount = 0;
        while (index < text.length()) {
            index += paint.breakText(text, index, text.length(), true, maxWidth, null);
            lineCount++;
        }

        Rect bounds = new Rect();
        paint.getTextBounds("Yy", 0, 2, bounds);
        // obtain space between lines
        double lineSpacing = Math.max(0, ((lineCount - 1) * bounds.height() * 0.25));

        return (int) Math.floor(lineSpacing + lineCount * bounds.height());
    }


    private static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    private static final Pattern VALID_INDONESIA_PHONE_NUMBER_REGEX =
            Pattern.compile("\\(?(?:\\+62|62|0)(?:\\d{2,3})?\\)?[ .-]?\\d{2,4}[ .-]?\\d{2,4}[ .-]?\\d{2,4}");

    public static boolean validateEmail(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }

    public static String convertMongoDate(String val) {
        ISO8601DateFormat df = new ISO8601DateFormat();
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd MMM, yyyy HH:mm");
        try {
            Date d = df.parse(val);
            String finalStr = outputFormat.format(d);
            val = finalStr;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return val;
    }

    public static String convertMongoDateWithoutTIme(String val) {
        ISO8601DateFormat df = new ISO8601DateFormat();
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd MMM, yyyy");
        try {
            Date d = df.parse(val);
            String finalStr = outputFormat.format(d);
            val = finalStr;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return val;
    }

    public static String convertRupiah(String val) {


        DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols formatRp = new DecimalFormatSymbols();

        formatRp.setCurrencySymbol("Rp. ");
        formatRp.setMonetaryDecimalSeparator(',');
        formatRp.setGroupingSeparator('.');

        kursIndonesia.setDecimalFormatSymbols(formatRp);
        String biaya =kursIndonesia.format(Long.valueOf(val));
        return biaya;
//        int value = Integer.parseInt(val);
//        Locale localeID = new Locale("in", "ID");
//        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
//        String biaya =  formatRupiah.format(". "+(double)value);
//        return biaya ;
    }

    public static String convertRupiahBigDecimal(String val) {

//        double money = 100.1;
        BigDecimal amount = new BigDecimal(val);
        DecimalFormat formatter = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols formatRp = new DecimalFormatSymbols();

        formatRp.setCurrencySymbol("Rp. ");
        formatRp.setMonetaryDecimalSeparator(',');
        formatRp.setGroupingSeparator('.');

        formatter.setDecimalFormatSymbols(formatRp);
        String moneyString = formatter.format(amount);
        System.out.println(moneyString);
        return moneyString;
    }
}
