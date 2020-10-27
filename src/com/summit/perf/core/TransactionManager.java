package com.summit.perf.core;

public class TransactionManager {

	public static final ThreadLocal<Transaction> TRANSACTION_MANAGERS = new ThreadLocal<>();
	
	public static Transaction getTransctionID() {
		return TRANSACTION_MANAGERS.get();
	}
}
