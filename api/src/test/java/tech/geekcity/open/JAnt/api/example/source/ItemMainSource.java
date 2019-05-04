package tech.geekcity.open.JAnt.api.example.source;

import com.google.common.base.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tech.geekcity.open.JAnt.api.Context;
import tech.geekcity.open.JAnt.api.Source;
import tech.geekcity.open.JAnt.api.annotation.MysqlDefinition;
import tech.geekcity.open.JAnt.api.annotation.OriginSource;
import tech.geekcity.open.JAnt.api.annotation.PrimaryKey;

import javax.annotation.Nonnull;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@OriginSource
@PrimaryKey(name = "item_id")
public class ItemMainSource implements Source {
    private static final Logger LOGGER = LoggerFactory.getLogger(ItemMainSource.class);
    private List<String> fieldNameList;
    @MysqlDefinition(
            url = "",
            username = "",
            password = "",
            sql = "select item_id, status, price, category from item_main")
    private ResultSet resultSet;

    public enum FieldName {
        item_id,
        status,
        price,
        category
    }

    @Override
    public void setup(Context context) throws SQLException {
        // TODO remove duplicated codes
        fieldNameList = Arrays.stream(FieldName.values())
                .map(Enum::name)
                .collect(Collectors.toList());
        ResultSetMetaData metaData = resultSet.getMetaData();
        List<String> sqlFieldNameList = IntStream.range(0, metaData.getColumnCount())
                .mapToObj(index -> {
                    try {
                        return metaData.getColumnName(index);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                })
                .collect(Collectors.toList());
        Preconditions.checkArgument(Objects.equals(sqlFieldNameList, fieldNameList));
    }
    // close has a default implementation

    @Override
    public List<String> fieldNameList() {
        return fieldNameList;
    }

    // TODO remove duplicated codes
    @Nonnull
    @Override
    public Iterator<List<String>> iterator() {
        return new Iterator<>() {
            @Override
            public boolean hasNext() {
                try {
                    return resultSet.next();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public List<String> next() {
                return IntStream.range(0, fieldNameList.size())
                        .mapToObj(index -> {
                            try {
                                return resultSet.getString(index);
                            } catch (SQLException e) {
                                throw new RuntimeException(e);
                            }
                        })
                        .collect(Collectors.toList());
            }
        };
    }
}
