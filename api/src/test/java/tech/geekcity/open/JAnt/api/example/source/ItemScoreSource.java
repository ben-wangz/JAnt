package tech.geekcity.open.JAnt.api.example.source;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tech.geekcity.open.JAnt.api.Source;
import tech.geekcity.open.JAnt.api.annotation.FilterCondition;
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
        sql = "select item_id, color, score from item_score")
public class ItemScoreSource implements Source {
    private static final Logger LOGGER = LoggerFactory.getLogger(ItemScoreSource.class);
    private List<String> fieldNameList;
    private List<String> fieldValueList;

    public enum FieldName {
        item_id,
        color,
        score
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
        return !StringUtils.isBlank(fieldValueList.get(FieldName.score.ordinal()));
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
    public String color() {
        return fieldValueList.get(FieldName.color.ordinal());
    }

    @Output
    public String score() {
        return fieldValueList.get(FieldName.score.ordinal());
    }
}
