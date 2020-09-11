package validation;


import utils.TimeConverter;

import java.time.LocalTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyValidator implements IValidator {

    // TODO: change the exception to something else. or just try and catch. DONT CRASH THE PROGRAM!
    @Override
    public void validateName(String name) {
        if (name.length() < 3) {
            throw new RuntimeException("Name should contain at least 3 characters");
        }
    }

    @Override
    public void validateAddress(String address) {
        if (address.split(" ").length < 2) {
            throw new RuntimeException("Address should contain at least 2 words");
        }
    }

    @Override
    public void validateDate(String date) {
        // Check the input's format
        Pattern pattern = Pattern.compile("\\d\\d:\\d\\d");
        Matcher matcher = pattern.matcher(date);

        if (!matcher.find()) {
            throw new RuntimeException("Date input needs to be in HH:mm format");
        }

        // Check that the input is in the future
        LocalTime inputTime = TimeConverter.convertStringToTimeWithDefaultFormat(date);
        LocalTime currentTime = TimeConverter.getCurrentTimeWithDefaultFormat();

        if (inputTime.isBefore(currentTime)) {
            throw new RuntimeException("Delivery time must be in the future");
        }
    }
}
