package ua.hypson.mvc.util;

import java.util.Date;

import org.joda.time.LocalDate;
import org.joda.time.Period;
import org.joda.time.PeriodType;
import org.springframework.stereotype.Component;

/**
 * converter Created by admin on 28.05.2016.
 */
@Component
public class DateUtils {

  public int getAge(Date birthday) {
    if (birthday == null) {
      return -1;
    }
    LocalDate dob = new LocalDate(birthday.getTime());
    LocalDate date = new LocalDate();
    Period period = new Period(dob, date, PeriodType.yearMonthDay());
    return period.getYears();
  }
}
