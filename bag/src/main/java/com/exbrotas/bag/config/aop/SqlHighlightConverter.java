package com.exbrotas.bag.config.aop;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.pattern.CompositeConverter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SqlHighlightConverter extends CompositeConverter<ILoggingEvent> {

  private static final String ESC = "\u001B[";
  private static final String RESET = ESC + "0m";
  private static final String MAGENTA_BOLD = ESC + "1;35m";

  private static final Pattern SQL_DETECTOR = Pattern.compile(
      "(?is)^\\s*(select|update|delete|insert|with)\\b|\\bfrom\\b|\\bvalues\\b"
  );

  private static final Pattern KEYWORDS = Pattern.compile(
      "(?i)\\b(SELECT|UPDATE|DELETE|INSERT|WITH|FROM|WHERE|JOIN|LEFT|RIGHT|INNER|OUTER|ON|VALUES|SET|ORDER|BY|GROUP|HAVING|LIMIT|OFFSET|AND|OR|IN|IS|NULL|LIKE|BETWEEN|ASC|DESC)\\b"
  );

  @Override
  protected String transform(ILoggingEvent event, String in) {
    if (in == null || in.isBlank()) {
      return "";
    }

    if (!SQL_DETECTOR.matcher(in).find()) {
      return in;
    }

    Matcher matcher = KEYWORDS.matcher(in);
    StringBuilder builder = new StringBuilder(in.length() + 200);
    int last = 0;

    while (matcher.find()) {
      builder.append(in, last, matcher.start());
      builder.append(MAGENTA_BOLD).append(matcher.group(1)).append(RESET);
      last = matcher.end();
    }

    builder.append(in.substring(last));
    return builder.toString();
  }
}
