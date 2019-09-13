package com.sellions.reader;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.stereotype.Component;

@Component
public class ReaderImpl implements ItemReader<String> {

    private boolean wasRead = false;
    private int count = 0;
    private String[] rowToProcess;

    @Override
    public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        if (!wasRead) {
            rowToProcess = getRowToProcess();
            wasRead = true;
        }

        if (count < rowToProcess.length)
            return rowToProcess[count++];

        return null;
    }

    private String[] getRowToProcess() {
        // read data from db and return
        return new String[] {"element1", "element2", "element3"};
    }
}
