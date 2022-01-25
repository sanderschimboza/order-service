package co.zw.santech.orderservice.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateParser {

    public static void main(String args[]) throws Exception {

        DateParser dateParser = new DateParser();

        String str = dateParser.getParsedDate("2012-11-17");
        System.out.println(str);
    }

    public static String getParsedDate(String date) throws Exception {
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        String s1 = date;
        String s2 = null;
        Date d;
        try {
            d = sdf.parse(s1);
            s2 = (new SimpleDateFormat("yyyMMdd")).format(d);

        } catch (ParseException e) {

            e.printStackTrace();
        }

        return s2;
    }
}
