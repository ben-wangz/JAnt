package tech.geekcity.open.JAnt.api.example.join;

import tech.geekcity.open.JAnt.api.Processor;
import tech.geekcity.open.JAnt.api.annotation.Input;
import tech.geekcity.open.JAnt.api.annotation.Join;
import tech.geekcity.open.JAnt.api.example.source.ItemMainSource;
import tech.geekcity.open.JAnt.api.example.source.ItemScoreSource;

import java.io.IOException;

public class ItemScoreJoin implements Processor {

    private String itemId;

    public ItemScoreJoin(
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

    @Join(processorClass = ItemScoreSource.class, fieldName = "itemId", joinFieldNameList = {"color", "score"})
    String itemId() {
        return itemId;
    }
}
