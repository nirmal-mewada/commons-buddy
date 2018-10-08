package org.nirmal.buddy.text;

import static org.apache.commons.lang3.StringUtils.isBlank;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.time.DateUtils;

import com.google.common.base.Function;

public class RegExUtils {

    public static void addKeysByPattern(String val, LinkedList<Date> lstDate, Pattern... patterns) throws ParseException {
        for (Pattern pattern : patterns) {
            Matcher m = pattern.matcher(val);
            while (m.find()) {
                String data = m.group(1);
                if (!isBlank(data)) {
                    Date dt = DateUtils.parseDate(data, "HH:mm:ss");
                    lstDate.add(dt);
                }
            }
        }
    }

    public static List<String> getMatches(String val, Pattern... patterns) throws ParseException {
        List<String> lst = new ArrayList<String>();
        for (Pattern pattern : patterns) {
            Matcher m = pattern.matcher(val);
            while (m.find()) {
                String data = m.group(1);
                lst.add(data);
            }
        }
        return lst;
    }

    public static <T> List<T> getMatches(String val, Function<String, T> function, Pattern... patterns) throws ParseException {
        List<T> lst = new ArrayList<T>();
        for (Pattern pattern : patterns) {
            Matcher m = pattern.matcher(val);
            while (m.find()) {
                String data = m.group(1);
                lst.add(function.apply(data));
            }
        }
        return lst;
    }
}
