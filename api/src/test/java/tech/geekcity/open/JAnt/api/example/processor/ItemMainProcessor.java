package tech.geekcity.open.JAnt.api.example.processor;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tech.geekcity.open.JAnt.api.Context;
import tech.geekcity.open.JAnt.api.Processor;
import tech.geekcity.open.JAnt.api.annotation.FilterCondition;
import tech.geekcity.open.JAnt.api.annotation.Input;
import tech.geekcity.open.JAnt.api.annotation.Output;
import tech.geekcity.open.JAnt.api.annotation.Process;
import tech.geekcity.open.JAnt.api.example.source.ItemMainSource;

import java.io.IOException;
import java.math.BigDecimal;

public class ItemMainProcessor implements Processor {
    private static final Logger LOGGER = LoggerFactory.getLogger(ItemMainProcessor.class);
    private BigDecimal price;
    private String activityPrice;

    @Override
    public void setup(Context context) {
    }

    @Override
    public void close() throws IOException {
    }

    @FilterCondition
    public boolean valid(
            @Input(upstreamClass = ItemMainSource.class, upstreamOutputFieldName = "status")
                    String status) {
        return !StringUtils.equals("1", status);
    }

    @Process
    public void process(
            @Input(upstreamClass = ItemMainSource.class, upstreamOutputFieldName = "price")
                    String price) {
        LOGGER.info("processing {}={}", ItemMainSource.FieldName.price.name(), price);
        this.price = BigDecimal.valueOf(Double.valueOf(price));
        activityPrice = this.price.multiply(BigDecimal.valueOf(0.5)).toString();
    }

    @Output
    public String doublePrice() {
        // bad implementation
        return price.multiply(BigDecimal.valueOf(2)).toString();
    }

    @Output
    public String activityPrice() {
        return activityPrice;
    }
}
