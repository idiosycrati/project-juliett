package com.juliett.core.commons.ThreadMultiplier;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.juliett.api.model.enums.ResponseCode;
import com.juliett.core.AutoEmailModel.AutoEmailModel;
import com.juliett.core.AutoEmailService.AutoEmailService;
import com.juliett.core.AutoEmailer.AutoEmailer;
import com.juliett.core.FinanceEntityService.FinanceEntityService;
import com.juliett.core.Transactions.model.TransactionsModel;
import com.juliett.core.TransactionsService.TransactionsService;
import com.juliett.core.model.enums.Status;
import com.xurpas.development.core.exception.XDevServiceException;
import com.xurpas.development.core.logger.Logger;

public class ThreadCheckTermination extends Thread {
	private TransactionsService transactionsService;
	private AutoEmailService autoEmailService;

	public ThreadCheckTermination(TransactionsService transactionsService, AutoEmailService autoEmailService,
			Logger logger) {

		this.transactionsService = transactionsService;
		this.autoEmailService = autoEmailService;

	}

	public void run() {
		while (true) {
//			sendEmailToTerminated();
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
		}

	}

	public void sendEmailToTerminated() {

		try {
			List<TransactionsModel> transactionsModel = (List<TransactionsModel>) transactionsService
					.findTransactionsTerminated();

			int length = transactionsModel.size();
			System.out.println(length + "lenghthh");

			for (int i = 0; i < length; i++) {
				List<TransactionsModel> getUsersInfo = (List<TransactionsModel>) transactionsService
						.getUsersInfo(transactionsModel.get(i).getId());
				System.out.println("i is incrementing " + i);
				AutoEmailModel autoEmail2 = new AutoEmailModel();
				List<AutoEmailModel> autoEmailGet = (List<AutoEmailModel>) autoEmailService
						.findByTransactionsId(transactionsModel.get(i).getId());
				if (transactionsModel.get(i).getStatus() == Status.TERMINATED
						&& !autoEmailGet.get(0).getTermination()) {

					System.out.println(
							getUsersInfo.get(0).getEmail() + getUsersInfo.get(0).getFirstName() + "weh di nga");
					AutoEmailer autoEmail = new AutoEmailer();
					try {
						autoEmail.sendMailTermination(getUsersInfo.get(0).getEmail(),
								getUsersInfo.get(0).getFirstName());
					} catch (IOException e) {
						// TODO Auto-generated catch block
					}

					autoEmail2.setTransactionsId(transactionsModel.get(i).getId());
					autoEmail2.setTermination(true);
					autoEmail2.setApproval(false);
					autoEmail2.setNoticeDueDate(false);
					try {
						autoEmailService.update(autoEmail2);
					} catch (XDevServiceException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			e.getMessage();

		}
	}
}