package tech.geekcity.open.JAnt.api.example.join;

import tech.geekcity.open.JAnt.api.Processor;
import tech.geekcity.open.JAnt.api.annotation.Input;
import tech.geekcity.open.JAnt.api.annotation.Join;
import tech.geekcity.open.JAnt.api.example.source.CategorySource;
import tech.geekcity.open.JAnt.api.example.source.ItemExtendSource;

import java.io.IOException;

public class UserJoin implements Processor {

    private String userId;

    public UserJoin(
            @Input(processorName = ItemExtendSource.class, outputFieldName = "userId")
                    String userId) {
        this.userId = userId;
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

    @Join(processorClass = CategorySource.class, fieldName = "userId", joinFieldNameList = {"companyName", "address"})
    String userId() {
        return userId;
    }
}
