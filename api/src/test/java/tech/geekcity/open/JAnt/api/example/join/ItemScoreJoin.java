package tech.geekcity.open.JAnt.api.example.join;

import tech.geekcity.open.JAnt.api.Processor;
import tech.geekcity.open.JAnt.api.annotation.Input;
import tech.geekcity.open.JAnt.api.annotation.Join;
import tech.geekcity.open.JAnt.api.example.source.ItemMainSource;
import tech.geekcity.open.JAnt.api.example.source.ItemScoreSource;

public class ItemScoreJoin implements Processor {

    @Join(processorClass = ItemScoreSource.class, joinKey = "item_id", joinFieldNameList = {"color", "score"})
    String join(
            @Input(upstreamClass = ItemMainSource.class, upstreamOutputFieldName = "itemId")
                    String itemId) {
        return itemId;
    }
}
