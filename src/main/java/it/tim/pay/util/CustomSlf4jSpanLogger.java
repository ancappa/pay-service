package it.tim.pay.util;

import org.jboss.logging.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.log.SleuthSlf4jProperties;
import org.springframework.cloud.sleuth.log.Slf4jSpanLogger;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
class CustomSlf4jSpanLogger extends Slf4jSpanLogger {

  @Autowired
  public CustomSlf4jSpanLogger( SleuthSlf4jProperties sleuthSlf4jProperties ) {
    super( sleuthSlf4jProperties.getNameSkipPattern( ) );
  }

  @Override
  public void logStartedSpan( Span parent, Span span ) {
    Map< String, String > baggage = span.getBaggage( );
    for ( String key : baggage.keySet( ) ) {
      MDC.put( key, baggage.get( key ) );
    }
    super.logStartedSpan( parent, span );
  }

  @Override
  public void logContinuedSpan( Span span ) {
    Map< String, String > baggage = span.getBaggage( );
    for ( String key : baggage.keySet( ) ) {
      MDC.put( key, baggage.get( key ) );
    }
    super.logContinuedSpan( span );
  }

  @Override
  public void logStoppedSpan( Span parent, Span span ) {
    super.logStoppedSpan( parent, span );

  }
}
