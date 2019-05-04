package tech.geekcity.open.JAnt.api.io;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Objects;

public class Record {
    public List<String> fieldNameList;
    public List<ComparableFieldValue> comparableFieldValueList;

    public static class ComparableFieldValue {
        private final Object oldValue;
        private final Object newValue;

        protected ComparableFieldValue(@Nullable Object oldValue, @Nullable Object newValue) {
            this.oldValue = oldValue;
            this.newValue = newValue;
        }

        public static ComparableFieldValue of(Object oldValue, Object newValue) {
            return new ComparableFieldValue(oldValue, newValue);
        }

        public Object oldValue() {
            return oldValue;
        }

        public Object newValue() {
            return newValue;
        }

        public boolean modified() {
            return !Objects.equals(oldValue, newValue);
        }

        public boolean delete() {
            return null == newValue;
        }
    }
}
