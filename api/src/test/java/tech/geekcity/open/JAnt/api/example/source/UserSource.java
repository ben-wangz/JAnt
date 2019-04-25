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
        sql = "select id as user_id, company_name, address from user")
public class UserSource implements Source {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserSource.class);
    private List<String> fieldNameList;
    private List<String> fieldValueList;

    public enum FieldName {
        user_id,
        company_name,
        address
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
    public String userId() {
        return fieldValueList.get(FieldName.user_id.ordinal());
    }

    @Output
    public String companyName() {
        return fieldValueList.get(FieldName.company_name.ordinal());
    }

    @Output
    public String address() {
        return fieldValueList.get(FieldName.address.ordinal());
    }
}
