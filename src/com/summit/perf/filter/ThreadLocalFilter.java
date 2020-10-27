package com.summit.perf.filter;

import java.io.IOException;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import com.summit.perf.core.Transaction;
import com.summit.perf.core.TransactionManager;

@WebFilter(filterName = "threadlocal-filter", urlPatterns = "*")
public class ThreadLocalFilter implements Filter {

	private static final String PAD = "AAAAA";
	private static final int SIZE = 128 * 1024 / (PAD.length() + 1);

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		try {

			String padded = LongStream.range(0, SIZE)
					.mapToObj(i -> PAD + i)
					.collect(Collectors.joining());

			TransactionManager.TRANSACTION_MANAGERS.set(new Transaction(padded));
			Logger.getLogger("ThreadLocalFilter").info("Set thread local");

			chain.doFilter(request, response);

		} finally {
			  TransactionManager.TRANSACTION_MANAGERS.remove();
			  Logger.getLogger("ThreadLocalFilter").info("Removed thread local");
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
