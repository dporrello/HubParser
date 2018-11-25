package com.ef.utilities;

import com.ef.model.exceptions.LogFileIOException;
import com.ef.model.exceptions.LogFileNotFoundException;
import com.ef.model.exceptions.LogFileParserException;
import com.ef.model.logs.LogParserResponse;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class LogParser {

    private LogParser() {
    }

    public static LogParserResponse parseLogFile(String logPath)
        throws LogFileNotFoundException, LogFileIOException, LogFileParserException {
        // We must first load the file into the IO memory and then we can begin to parse and retrieve
        // the data within.
        File file = new File(logPath);
        if (!file.exists())
            throw new LogFileNotFoundException(logPath);

        // If we reach here then we have successfully instantiated the File object and are ready to
        // read.
        return readFile(file);
    }

    private static LogParserResponse readFile(File file)
        throws LogFileNotFoundException, LogFileIOException, LogFileParserException {

        try (FileReader fileReader = new FileReader(file)) {
            LogReader logReader = new LogReader(fileReader);
            return logReader.readLog();
        } catch (FileNotFoundException fileNotFoundException) {
            throw new LogFileNotFoundException(file.getPath());
        } catch (IOException ioException) {
            throw new LogFileIOException(file.getPath());
        }
    }
}
