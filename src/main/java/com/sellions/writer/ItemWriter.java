package com.sellions.writer;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ItemWriter implements org.springframework.batch.item.ItemWriter<String> {
    @Override
    public void write(List<? extends String> list) throws Exception {
        // save to DB
        for (String item : list) {
            System.out.println("SAVING ITEM: " + item);
        }
    }
}
