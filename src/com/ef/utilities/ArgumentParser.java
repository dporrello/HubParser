package com.ef.utilities;

import com.ef.model.exceptions.ArgumentParserException;
import com.ef.model.exceptions.ExceptionConstants;
import com.ef.model.settings.ClientApplicationContext;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.EnumMap;
import java.util.Map;

public class ArgumentParser {

    private ArgumentParser() {
    }

    /**
     * Method used to validate the arguments being passed in. Need to ensure the type casting is correct
     * and that all of the necessary arguments are included.
     *
     * @param args - Parameters passed by user on startup
     * @return - true if all parameters are accounted for and correct format.
     */
    public static Boolean validateArguments(String[] args) {
        if (null == args)
            return false;

        Boolean isValid = true;

        Map<ArgumentType, Boolean> argumentMap = new EnumMap<>(ArgumentType.class);
        // We will check to make sure that all of the arguments are correctly passed in.
        for (String arg : args) {
            // Convert the argument to lower case so we can compare with our enum properly.
            arg = arg.toLowerCase();
            if (arg.contains(ArgumentType.ACCESSLOG.toString()))
                argumentMap.put(ArgumentType.ACCESSLOG, true);
            else if (arg.contains(ArgumentType.STARTDATE.toString()))
                argumentMap.put(ArgumentType.STARTDATE, true);
            else if (arg.contains(ArgumentType.DURATION.toString()))
                argumentMap.put(ArgumentType.DURATION, true);
            else if (arg.contains(ArgumentType.THRESHOLD.toString()))
                argumentMap.put(ArgumentType.THRESHOLD, true);
        }

        if (argumentMap.entrySet().size() != 4)
            isValid = false;

        return isValid;
    }

    /**
     * Method used to parse through the arguments and build out the ClientApplicationContext object
     * with the appropriate values for each parameter.
     *
     * @param args - Parameters passed by user on startup
     * @return - Object containing client information for the run through of the log parser.
     */
    public static ClientApplicationContext parseArguments(String[] args)
        throws ArgumentParserException {
        ClientApplicationContext clientApplicationContext = new ClientApplicationContext();

        for (String arg : args) {
            // Convert the argument to lower case so we can compare with our enum properly.
            arg = arg.toLowerCase();
            if (arg.contains(ArgumentType.STARTDATE.toString())) {
                clientApplicationContext.setStartDate((Date) handleArgument(arg));
            } else if (arg.contains(ArgumentType.ACCESSLOG.toString())) {
                clientApplicationContext.setAccessLogPath((String) handleArgument(arg));
            } else if (arg.contains(ArgumentType.DURATION.toString())) {
                clientApplicationContext.setDuration((String) handleArgument(arg));
                if (!clientApplicationContext.getDuration().equalsIgnoreCase("hourly") &&
                    !clientApplicationContext.getDuration().equalsIgnoreCase("daily"))
                    throw new ArgumentParserException(
                        ExceptionConstants.ARGUMENT_PARSER_EXCEPTION_DURATION.toString());
            } else if (arg.contains(ArgumentType.THRESHOLD.toString())) {
                clientApplicationContext.setThreshold((Integer) handleArgument(arg));
            }
        }

        return clientApplicationContext;
    }

    /**
     * Method used to handle the argument type casting depending on the key for the argument we are
     * grabbing.
     *
     * @param arg - Argument entered by user on startup.
     * @return - An object depending on the key we are parsing. Could be String, Integer or Date.
     * @throws ArgumentParserException - Exception thrown when trying to parse the date if input is invalid.
     */
    private static Object handleArgument(String arg)
        throws ArgumentParserException {
        Object result = null;
        if (arg.contains(ArgumentType.STARTDATE.toString())) {
            String date = retrieveValue(arg);
            try {
                result = new SimpleDateFormat("yyyy-MM-dd.HH:mm:ss").parse(date);
            } catch (ParseException parseException) {
                throw new ArgumentParserException(
                    ExceptionConstants.ARGUMENT_PARSER_EXCEPTION_DATE.toString());
            }
        } else if (arg.contains(ArgumentType.ACCESSLOG.toString()) ||
            arg.contains(ArgumentType.DURATION.toString())) {
            result = retrieveValue(arg);
        } else if (arg.contains(ArgumentType.THRESHOLD.toString())) {
            String threshold = retrieveValue(arg);
            try {
                result = Integer.parseInt(threshold);
            } catch (NumberFormatException numberFormatException) {
                throw new ArgumentParserException(
                    ExceptionConstants.ARGUMENT_PARSER_EXCEPTION_THRESHOLD.toString());
            }
        }
        return result;
    }

    /**
     * Method used to split the argument passed in using the '=' as the delimiter.
     *
     * @param arg - Argument from console start up.
     * @return - The value associated to the key
     */
    private static String retrieveValue(String arg) {
        String[] splitString = arg.split("=");
        return splitString.length == 2 ? splitString[1] : "";
    }
}
