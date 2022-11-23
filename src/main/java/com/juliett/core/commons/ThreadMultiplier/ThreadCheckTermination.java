package com.juliett.core.commons.ThreadMultiplier;

import java.util.concurrent.TimeUnit;

import com.juliett.core.FinanceEntityService.FinanceEntityService;
import com.juliett.core.TransactionsService.TransactionsService;
import com.xurpas.development.core.logger.Logger;

public class ThreadCheckTermination extends Thread {
	private TransactionsService transactionsService;

	public ThreadCheckTermination(TransactionsService transactionsService, Logger logger) {

		this.transactionsService = transactionsService;

	}

	public void run() {
		while (true) {
			transactionsService.checkTermination();

			try {
				TimeUnit.DAYS.sleep(1);
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
		}
	}
}
