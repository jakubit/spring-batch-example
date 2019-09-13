package com.sellions.processor;


import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class ProcessorImpl implements ItemProcessor<String, String> {

    @Override
    public String process(String s) throws Exception {
        return s.toUpperCase();
    }
}
