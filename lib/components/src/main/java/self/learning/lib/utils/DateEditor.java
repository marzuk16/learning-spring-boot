package self.learning.lib.utils;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateEditor extends PropertyEditorSupport {

    public static final SimpleDateFormat slashSeparatedDateFormat =
            new SimpleDateFormat(AppDateUtils.DATE_FORMAT_WITHOUT_TIMESTAMP_SLASH_SEPARATED);

    @Override
    public void setAsText(String value) throws IllegalArgumentException {
        Date date = null;
        try {
            date = slashSeparatedDateFormat.parse(value);
            setValue(date);
        } catch (ParseException e) {
            setValue(null);
        }
    }

    @Override
    public String getAsText() {
        String formattedDate = "";
        if (getValue() != null) {
            formattedDate = slashSeparatedDateFormat.format((Date) getValue());
        }
        return formattedDate;
    }
}
