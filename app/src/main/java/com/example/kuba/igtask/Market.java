package com.example.kuba.igtask;

import java.io.Serializable;
import java.util.List;

public class Market implements Serializable {

    public List<CurrentMarkets> getMarkets() {
        return markets;
    }

    public String getChartFormat() {
        return chartFormat;
    }

    public String getLightStreamerEndpoint() {
        return lightStreamerEndpoint;
    }

    public String getConfiguration() {
        return configuration;
    }

    public void setMarkets(List<CurrentMarkets> markets) {
        this.markets = markets;
    }

    public void setChartFormat(String chartFormat) {
        this.chartFormat = chartFormat;
    }

    public void setLightStreamerEndpoint(String lightStreamerEndpoint) {
        this.lightStreamerEndpoint = lightStreamerEndpoint;
    }

    public void setConfiguration(String configuration) {
        this.configuration = configuration;
    }

    private List<CurrentMarkets> markets;
    private String chartFormat;
    private String lightStreamerEndpoint;
    private String configuration;


    public  class CurrentMarkets {
        public String getInstrumentName() {
            return instrumentName;
        }

        public String getInstrumentVersion() {
            return instrumentVersion;
        }

        public String getDisplayPeriod() {
            return displayPeriod;
        }

        public String getEpic() {
            return epic;
        }

        public String getExchangeId() {
            return exchangeId;
        }

        public String getDisplayBid() {
            return displayBid;
        }

        public String getDisplayOffer() {
            return displayOffer;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public String getNetChange() {
            return netChange;
        }

        public String getScaled() {
            return scaled;
        }

        public String getTimezoneOffset() {
            return timezoneOffset;
        }

        public void setInstrumentName(String instrumentName) {
            this.instrumentName = instrumentName;
        }

        public void setInstrumentVersion(String instrumentVersion) {
            this.instrumentVersion = instrumentVersion;
        }

        public void setDisplayPeriod(String displayPeriod) {
            this.displayPeriod = displayPeriod;
        }

        public void setEpic(String epic) {
            this.epic = epic;
        }

        public void setExchangeId(String exchangeId) {
            this.exchangeId = exchangeId;
        }

        public void setDisplayBid(String displayBid) {
            this.displayBid = displayBid;
        }

        public void setDisplayOffer(String displayOffer) {
            this.displayOffer = displayOffer;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public void setNetChange(String netChange) {
            this.netChange = netChange;
        }

        public void setScaled(String scaled) {
            this.scaled = scaled;
        }

        public void setTimezoneOffset(String timezoneOffset) {
            this.timezoneOffset = timezoneOffset;
        }

        private String instrumentName;
        private String instrumentVersion;
        private String displayPeriod;
        private String epic;
        private String exchangeId;
        private String displayBid;
        private String displayOffer;
        private String updateTime;
        private String netChange;
        private String scaled;
        private String timezoneOffset;
    }



}
