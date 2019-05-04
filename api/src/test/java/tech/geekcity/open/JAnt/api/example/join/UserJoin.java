package tech.geekcity.open.JAnt.api.example.join;

import tech.geekcity.open.JAnt.api.Processor;
import tech.geekcity.open.JAnt.api.annotation.Input;
import tech.geekcity.open.JAnt.api.annotation.Join;
import tech.geekcity.open.JAnt.api.example.source.CategorySource;
import tech.geekcity.open.JAnt.api.example.source.ItemExtendSource;

public class UserJoin implements Processor {
    @Join(processorClass = CategorySource.class, joinKey = "user_id", joinFieldNameList = {"company_name", "address"})
    String join(
            @Input(upstreamClass = ItemExtendSource.class, upstreamOutputFieldName = "user_id")
                    String userId) {
        return userId;
    }
}
