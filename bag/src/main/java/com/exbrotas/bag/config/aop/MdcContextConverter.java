package com.exbrotas.bag.config.aop;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import java.util.Map;

public class MdcContextConverter extends ClassicConverter {

  @Override
  public String convert(ILoggingEvent event) {
    Map<String, String> mdc = event.getMDCPropertyMap();
    String traceId = mdc.get("traceId");
    String repoTrace = mdc.get("repoTrace");

    if (isBlank(traceId) && isBlank(repoTrace)) {
      return "";
    }

    StringBuilder builder = new StringBuilder();
    if (!isBlank(traceId)) {
      builder.append(" | trace=").append(traceId);
    }
    if (!isBlank(repoTrace)) {
      builder.append(" | repo=").append(repoTrace);
    }
    return builder.toString();
  }

  private boolean isBlank(String value) {
    return value == null || value.isBlank();
  }
}
