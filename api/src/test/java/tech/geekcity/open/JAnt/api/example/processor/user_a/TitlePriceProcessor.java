package tech.geekcity.open.JAnt.api.example.processor.user_a;

import com.google.common.collect.ImmutableMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tech.geekcity.open.JAnt.api.Context;
import tech.geekcity.open.JAnt.api.Processor;
import tech.geekcity.open.JAnt.api.annotation.Input;
import tech.geekcity.open.JAnt.api.annotation.Output;
import tech.geekcity.open.JAnt.api.annotation.Process;
import tech.geekcity.open.JAnt.api.example.processor.ItemMainProcessor;
import tech.geekcity.open.JAnt.api.example.source.ItemExtendSource;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;

public class TitlePriceProcessor implements Processor {
    private static final Logger LOGGER = LoggerFactory.getLogger(TitlePriceProcessor.class);
    private transient Map<String, Double> exchangeRateTable;
    private static final String DEFAULT_UNIT = "USD";
    private String activityPriceInYuan;
    private String titleWithPrice;

    @Override
    public void setup(Context context) {
        String exchangeRageServer = (String) context.getOrDefault(
                "taobao.exchange_rate.server", "xxx.xxx.xxx.xxx:yyyy");
        exchangeRateTable = exchangeRateTable(exchangeRageServer);
    }

    @Override
    public void close() throws IOException {
    }

    @Process
    public void process(
            @Input(upstreamClass = ItemMainProcessor.class, upstreamOutputFieldName = "activityPrice")
                    BigDecimal activityPrice,
            @Input(upstreamClass = ItemExtendSource.class, upstreamOutputFieldName = "title")
                    String title) {
        activityPriceInYuan = activityPrice
                .multiply(BigDecimal.valueOf(
                        exchangeRateTable.getOrDefault(DEFAULT_UNIT, 1.0)))
                .toString();
        titleWithPrice = String.format("unbelievable! ￥%s => %s", activityPriceInYuan, title);
    }

    @Output
    public String activityPriceInYuan() {
        return activityPriceInYuan;
    }

    @Output
    public String titleWithPrice() {
        return titleWithPrice;
    }

    private Map<String, Double> exchangeRateTable(String exchangeRageServer) {
        // send request to server
        return ImmutableMap.of(DEFAULT_UNIT, 6.6);
    }
}
