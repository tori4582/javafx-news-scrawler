package edu.rmit.fxnews.common;

import lombok.extern.java.Log;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;
import java.util.Locale;
import java.time.Period;
import java.time.Duration;

@Log
public class DateTimeUtils {

    public static final LocalDateTime parseLocalDateTime(String timestamp, String formatter, Locale locale) {
        return LocalDateTime.parse(
                timestamp,
                DateTimeFormatter.ofPattern(formatter, locale)
        );
    }

    public static final String parseWithFormatMapper(String newsProvider, String timestamp) {
        switch (newsProvider) {
            case "VNEXPRESS":
                return parseVnExpressDateTime(timestamp);
            case "THANHNIEN":
                return parseThanhNienDateTime(timestamp);
            case "ZING":
                return parseZingDateTime(timestamp);
            case "TUOITRE":
                return parseTuoiTreDateTime(timestamp);
            case "NHANDAN":
                return parseNhanDanDateTime(timestamp);
            default:
                throw new IllegalArgumentException("Unknown news provider: " + newsProvider);
        }
    }

    private static String parseNhanDanDateTime(String timestamp) {
        return getDisplayableDateTime(
                timestamp,
                /* Thu, 19 May 2022 21:15:09 +0700 */
                "EEE, dd MMM yyyy HH:mm:ss Z",
                Locale.ENGLISH
        );
    }

    private static String parseTuoiTreDateTime(String timestamp) {

        return getDisplayableDateTime(
                timestamp.replace(" GMT+7", ""),
                /* Thu, 19 May 2022 21:41:15 GMT+7 */
                "EEE, dd MMM yyyy HH:mm:ss",
                Locale.ENGLISH
        );
    }

    private static String parseZingDateTime(String timestamp) {
        return getDisplayableDateTime(
                timestamp,
                /* Thu, 19 May 2022 21:15:09 +0700 */
                "EEE, dd MMM yyyy HH:mm:ss Z",
                Locale.ENGLISH
        );
    }

    private static String parseThanhNienDateTime(String timestamp) {
        return getDisplayableDateTime(
                timestamp,
                /* Thu, 19 May 2022 21:15:09 +0700 */
                "EEE, dd MMM yyyy HH:mm:ss Z",
                Locale.ENGLISH
        );
    }

    private static String parseVnExpressDateTime(String timestamp) {
        return getDisplayableDateTime(
                timestamp,
                /* Thu, 19 May 2022 21:15:09 +0700 */
                "EEE, dd MMM yyyy HH:mm:ss Z",
                Locale.ENGLISH
        );
    }

    public static final String getPublishedDuration(LocalDateTime dateTime) {
        Duration duration = Duration.between(dateTime, LocalDateTime.now());
        Period period = Period.between(dateTime.toLocalDate(), LocalDateTime.now().toLocalDate());

        List<String> durationNames = List.of("year", "month", "day", "hour", "minute", "second");
        List<? extends Number> durationValues = List.of(
                period.getYears(),
                period.getMonths(),
                period.getDays(),
                duration.toHours(),
                duration.toMinutes(),
                duration.getSeconds()
        );

        for (int i = 0; i < durationNames.size() - 1; i++) {
            if (durationValues.get(i).intValue() > 0) {
                return String.format("(about %d %s%s before)",
                        durationValues.get(i),
                        durationNames.get(i),
                        durationValues.get(i).intValue() > 1 ? "s" : ""
                );
            }
        }

        return "(just now)";
    }

    public static final String getDisplayableDateTime(String timestamp, String formatter, Locale locale) {
        var datetime = parseLocalDateTime(timestamp, formatter, locale);
        String publishedDuration = getPublishedDuration(datetime);

        return String.format(
                "%s %s",
                datetime.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM).localizedBy(Locale.UK)),
                publishedDuration
        );
    }

}
