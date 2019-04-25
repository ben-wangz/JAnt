package tech.geekcity.open.JAnt.api.example.source;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tech.geekcity.open.JAnt.api.Source;
import tech.geekcity.open.JAnt.api.annotation.MysqlDefinition;
import tech.geekcity.open.JAnt.api.annotation.Output;
import tech.geekcity.open.JAnt.api.annotation.PrimaryKey;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@MysqlDefinition(
        url = "",
        username = "",
        password = "",
        sql = "select id as category, name as category_name from category")
public class CategorySource implements Source {
    private static final Logger LOGGER = LoggerFactory.getLogger(CategorySource.class);
    private List<String> fieldNameList;
    private List<String> fieldValueList;

    public enum FieldName {
        category,
        category_name
    }

    @Override
    public void setup() {
        fieldNameList = Arrays.stream(FieldName.values())
                .map(Enum::name)
                .collect(Collectors.toList());
    }
    // close has a default implementation

    @Override
    public List<String> fieldNameList() {
        return fieldNameList;
    }

    @Override
    public void setFieldValueList(List<String> fieldValueList) {
        this.fieldValueList = fieldValueList;
    }

    @Override
    public void process() {
        LOGGER.info("processing data: {}", StringUtils.join(fieldValueList, ", "));
    }

    @Output
    @PrimaryKey
    public String category() {
        return fieldValueList.get(FieldName.category.ordinal());
    }

    @Output
    public String categoryName() {
        return fieldValueList.get(FieldName.category_name.ordinal());
    }
}
