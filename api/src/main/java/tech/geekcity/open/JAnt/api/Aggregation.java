package tech.geekcity.open.JAnt.api;

public interface Aggregation<ValueType, AccumulatorType> {
    AccumulatorType setup();

    ValueType currentValue();

    void accumulate(AccumulatorType lastAccumulator, Object... aggregationFields);

    void merge(AccumulatorType accumulator, Iterable<AccumulatorType> accumulatorsToMerge);

    default void retract(AccumulatorType accumulatorType, Object... aggregationFields) {
        // do nothing if retract not supported
    }
}
