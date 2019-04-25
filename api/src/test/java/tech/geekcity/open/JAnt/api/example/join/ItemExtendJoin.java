package tech.geekcity.open.JAnt.api.example.join;

import tech.geekcity.open.JAnt.api.Processor;
import tech.geekcity.open.JAnt.api.annotation.Input;
import tech.geekcity.open.JAnt.api.annotation.Join;
import tech.geekcity.open.JAnt.api.example.source.ItemExtendSource;
import tech.geekcity.open.JAnt.api.example.source.ItemMainSource;

import java.io.IOException;

public class ItemExtendJoin implements Processor {

    private String itemId;

    public ItemExtendJoin(
            @Input(processorName = ItemMainSource.class, outputFieldName = "itemId")
                    String itemId) {
        this.itemId = itemId;
    }

    @Override
    public void setup() {

    }

    @Override
    public void close() throws IOException {

    }

    @Override
    public void process() {

    }

    @Join(processorClass = ItemExtendSource.class, fieldName = "itemId", joinFieldNameList = {"userId", "title"})
    String itemId() {
        return itemId;
    }
}
