package io.wancloud.factom.sdk.support;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.function.Predicate;

import io.wancloud.factom.sdk.FactomException;
import io.wancloud.factom.sdk.util.FactomAssert;

public class FluentWait<T> {
	
	private static final int DEFAULT_INTERVAL_MILL_SECONDS = 500;
	
	private static final long DEFAULT_TIMEOUT = 1;
	
	private static final TimeUnit DEFAULT_TIMEUNIT = TimeUnit.DAYS;
	
	private final T target;
	
	private long intervalMillSeconds;
	
	private long timeout;
	
	private TimeUnit unit; 
	
	public FluentWait(T target) {
		this(target,DEFAULT_INTERVAL_MILL_SECONDS);
	}
	
	public FluentWait(T target, long intervalMillSeconds) {
		FactomAssert.notNull(target, "Target object should not be null");
		this.target = target;
		this.intervalMillSeconds = intervalMillSeconds;
		this.timeout = DEFAULT_TIMEOUT;
		this.unit = DEFAULT_TIMEUNIT;
	}

	
	public FluentWait<T> withInterval(long intervalMills){
		this.intervalMillSeconds = intervalMills;
		return this;
	}
	
	public FluentWait<T> withTimeOut(long timeout, TimeUnit timeunit){
		this.timeout = timeout;
		this.unit = timeunit;
		return this;
	}
	
	
	public void until(Predicate<T> predicate){
		until((Function<T, Boolean>) s -> predicate.test(s));
	}
	
	public <R> R until(Function<T,R> fun){
		long duration = MILLISECONDS.convert(timeout, unit);
		long end = laterBy(duration);
		while(true){
			try{
				R ret = fun.apply(target);
				if (ret != null && (ret.getClass() != Boolean.class) || Boolean.TRUE.equals(ret)){
					return ret;
				}
			}catch(Exception ex){
				propagateIfNotCatch(ex);
			}

			if (isNowAfter(end)){
				throw new TimeoutException(String.format("timed out, limit time is %d millseconds", MILLISECONDS.convert(timeout, unit)));
			}
			
			try {
				Thread.sleep(intervalMillSeconds);
			} catch (InterruptedException ex) {
				Thread.currentThread().interrupt();
				throw new FactomException(ex);
			}
		}
	}
	
	private void propagateIfNotCatch(Throwable thw){
		throw new FactomException(thw.getMessage(), thw);
	}
	
	
	private long laterBy(long duration){
		return System.currentTimeMillis() + duration;
	}
	
	
	private boolean isNowAfter(long end){
		return System.currentTimeMillis() > end;
	}
}
