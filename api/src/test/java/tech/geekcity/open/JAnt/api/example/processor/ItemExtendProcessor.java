package tech.geekcity.open.JAnt.api.example.processor;

import org.apache.commons.lang3.StringUtils;
import tech.geekcity.open.JAnt.api.Context;
import tech.geekcity.open.JAnt.api.Processor;
import tech.geekcity.open.JAnt.api.annotation.FilterCondition;
import tech.geekcity.open.JAnt.api.annotation.Input;
import tech.geekcity.open.JAnt.api.example.source.ItemExtendSource;

import java.io.IOException;

public class ItemExtendProcessor implements Processor {
    @Override
    public void setup(Context context) {
    }

    @Override
    public void close() throws IOException {
    }

    @FilterCondition
    public boolean valid(
            @Input(upstreamClass = ItemExtendSource.class, upstreamOutputFieldName = "user_id")
                    String userId) {
        return !StringUtils.isBlank(userId);
    }
}
