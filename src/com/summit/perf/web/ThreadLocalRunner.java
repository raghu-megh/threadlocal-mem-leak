package com.summit.perf.web;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.summit.perf.core.Transaction;
import com.summit.perf.core.TransactionManager;

@WebServlet("/demo")
public class ThreadLocalRunner extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2685473760341662603L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Transaction value = TransactionManager.TRANSACTION_MANAGERS.get();
		Logger.getLogger("ThreadLocalRunner").info("Printing threadlocal value " + value.getID());

		resp.getWriter().append("OK!");
	}
}
