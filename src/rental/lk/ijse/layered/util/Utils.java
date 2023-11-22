package rental.lk.ijse.layered.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public final class Utils {

    private static SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");

    public static Date localDateToDate(LocalDate localDate) throws ParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String format = localDate.format(formatter);
        return sm.parse(format);
    }

    public static int getDateDifference(LocalDate returnDate) {
        LocalDate currentDate = LocalDate.now();
        Period period = Period.between(currentDate, returnDate);
        if (period.isZero()) {
            return 1;
        } else {
            return period.getDays() + 1;
        }
    }

    public static String getStringDate(LocalDate localDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return localDate.format(formatter);
    }

    public static String getStringDateToUtilDate(Date date) {
        return sm.format(date);
    }


    public static Date getUtilDate(String date) throws ParseException {
        return sm.parse(date);
    }

    public static LocalDate dateToLocalDate(Date date) {
        return date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }
}
