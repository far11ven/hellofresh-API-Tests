package com.hellofresh.api.commonUtils;

public class MyThreadLocal {
	static final class ContextLocal extends ThreadLocal<Context> {
		@Override
		protected Context initialValue() {
			return new Context();
		}
	}

	private static final ThreadLocal<Context> userThreadLocal = new ContextLocal();

	public static Context get() {
		return (Context) userThreadLocal.get();
	}

}
