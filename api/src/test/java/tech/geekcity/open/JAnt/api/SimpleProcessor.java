package tech.geekcity.open.JAnt.api;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import tech.geekcity.open.JAnt.api.annotation.FilterCondition;
import tech.geekcity.open.JAnt.api.annotation.Input;
import tech.geekcity.open.JAnt.api.annotation.Output;

import java.io.IOException;
import java.util.StringJoiner;

public class SimpleProcessor implements Processor {
    private String testName;
    private Pair<String, Integer> testObject;
    private String information;

    public SimpleProcessor(
            @Input(outputFieldName = "testName")
                    String testName,
            @Input(processorName = PreProcessor.class, outputFieldName = "testObject")
                    Pair<String, Integer> testObject) {
        this.testName = testName;
        this.testObject = testObject;
    }

    @Override
    public void setup() {
        // do nothing
    }

    @Override
    public void close() throws IOException {
        // nothing to release
    }

    @Override
    public void process() {
        information = new StringJoiner(",")
                .add(testName)
                .add(testObject.getLeft())
                .add(String.valueOf(testObject.getRight()))
                .toString();
    }

    @Output(outputFieldName = "information")
    public String getInformation() {
        return information;
    }

    @FilterCondition
    public boolean filter() {
        return StringUtils.isBlank(testName);
    }
}
