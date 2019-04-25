package tech.geekcity.open.JAnt.api;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import tech.geekcity.open.JAnt.api.annotation.FilterCondition;
import tech.geekcity.open.JAnt.api.annotation.Input;
import tech.geekcity.open.JAnt.api.annotation.Output;

import java.io.IOException;

public class PreProcessor implements Processor {
    private String testName;
    private Pair<String, Integer> testObject;
    private Pair<String, Integer> outputObject;

    public PreProcessor(
            @Input(outputFieldName = "testName") String testName,
            @Input(outputFieldName = "testObject") Pair<String, Integer> testObject) {
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
        outputObject = Pair.of(testObject.getLeft(), testObject.getRight() + 10);
    }

    @Output(outputFieldName = "testObject")
    public Pair<String, Integer> getTestObject() {
        return outputObject;
    }

    @FilterCondition
    public boolean filter() {
        return StringUtils.isBlank(testName);
    }
}
