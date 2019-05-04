package tech.geekcity.open.JAnt.api.example.join;

import tech.geekcity.open.JAnt.api.Processor;
import tech.geekcity.open.JAnt.api.annotation.Input;
import tech.geekcity.open.JAnt.api.annotation.Join;
import tech.geekcity.open.JAnt.api.example.source.ItemExtendSource;
import tech.geekcity.open.JAnt.api.example.source.ItemMainSource;

public class ItemExtendJoin implements Processor {
    @Join(processorClass = ItemExtendSource.class, joinKey = "item_id", joinFieldNameList = {"userId", "title"})
    String join(
            @Input(upstreamClass = ItemMainSource.class, upstreamOutputFieldName = "item_id")
                    String itemId) {
        return itemId;
    }
}
