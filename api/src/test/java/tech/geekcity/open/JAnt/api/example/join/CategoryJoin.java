package tech.geekcity.open.JAnt.api.example.join;

import tech.geekcity.open.JAnt.api.Processor;
import tech.geekcity.open.JAnt.api.annotation.Input;
import tech.geekcity.open.JAnt.api.annotation.Join;
import tech.geekcity.open.JAnt.api.example.source.CategorySource;
import tech.geekcity.open.JAnt.api.example.source.ItemMainSource;

import java.io.IOException;

public class CategoryJoin implements Processor {

    private String category;

    public CategoryJoin(
            @Input(processorName = ItemMainSource.class, outputFieldName = "category")
                    String category) {
        this.category = category;
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

    @Join(
            processorClass = CategorySource.class,
            fieldName = "category",
            joinFieldNameList = {"category", "categoryName"})
    String category() {
        return category;
    }
}
