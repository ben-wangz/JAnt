package tech.geekcity.open.JAnt.api;

import java.util.List;

public interface Source extends Processor {
    List<String> fieldNameList();

    void setFieldValueList(List<String> fieldValueList);
}
