package tech.geekcity.open.JAnt.api.example.source;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tech.geekcity.open.JAnt.api.Source;
import tech.geekcity.open.JAnt.api.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@MysqlDefinition(
        url = "",
        username = "",
        password = "",
        sql = "select item_id, status, price, category from item_main")
@OriginSource
public class ItemMainSource implements Source {
    private static final Logger LOGGER = LoggerFactory.getLogger(ItemMainSource.class);
    private List<String> fieldNameList;
    private List<String> fieldValueList;

    public enum FieldName {
        item_id,
        status,
        price,
        category
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

    @FilterCondition
    public boolean valid() {
        return !StringUtils.equals("1", fieldValueList.get(FieldName.status.ordinal()));
    }

    @Override
    public void process() {
        LOGGER.info("processing data: {}", StringUtils.join(fieldValueList, ", "));
    }

    @Output
    @PrimaryKey
    public String itemId() {
        return fieldValueList.get(FieldName.item_id.ordinal());
    }

    @Output
    public String status() {
        return fieldValueList.get(FieldName.status.ordinal());
    }

    @Output
    public String price() {
        return fieldValueList.get(FieldName.price.ordinal());
    }

    @Output
    public String category() {
        return fieldValueList.get(FieldName.category.ordinal());
    }
}
