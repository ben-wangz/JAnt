package tech.geekcity.open.JAnt.api.example.join;

import tech.geekcity.open.JAnt.api.Processor;
import tech.geekcity.open.JAnt.api.annotation.Input;
import tech.geekcity.open.JAnt.api.annotation.Join;
import tech.geekcity.open.JAnt.api.example.source.CategorySource;
import tech.geekcity.open.JAnt.api.example.source.ItemMainSource;

public class CategoryJoin implements Processor {

    @Join(
            processorClass = CategorySource.class,
            joinKey = "category",
            joinFieldNameList = {"category", "categoryName"})
    String category(
            @Input(upstreamClass = ItemMainSource.class, upstreamOutputFieldName = "category")
                    String category) {
        return category;
    }
}
