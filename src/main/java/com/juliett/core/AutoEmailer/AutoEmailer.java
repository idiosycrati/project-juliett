package com.juliett.core.AutoEmailer;

import java.io.IOException;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

public class AutoEmailer {

	public void sendMailSucessfullyInsured(String email, String fullName) throws IOException {
		Email from = new Email("justinjake.xurpas@gmail.com");
		Email to = new Email(email);
		String subject = "Sucessfuly insured";
		SendGrid sg = new SendGrid(System.getenv("SEND_GRID_API_KEY"));
		Content content = new Content("text/plain", "Greetings!, " + fullName
				+ " Thank you for trusting our company for insuring your cuhchuchuchu enjoy ! \n \n \n Kind regards.");
		Request request = new Request();
		Mail mail = new Mail(from, subject, to, content);
		request.setMethod(Method.POST);
		request.setEndpoint("mail/send");
		request.setBody(mail.build());
		Response response = sg.api(request);

		System.out.println(response.getStatusCode());
		System.out.println("body " + response.getBody());
		System.out.println("response header " + response.getHeaders());
	}

	public void sendMailDueDate(String email, String firstName, String dueDate, String dueDateTermination)
			throws IOException {
		Email from = new Email("justinjake.xurpas@gmail.com");
		Email to = new Email(email);
		String subject = "Due Date notice";
		SendGrid sg = new SendGrid(System.getenv("SEND_GRID_API_KEY"));
		Content content = new Content("text/plain", "Hello + " + firstName
				+ ", I hope you are well. I just wanted to drop you a quick note to remind you that you have unpaid bills due for payment on "
				+ dueDate + " Please pay before " + dueDateTermination + " Thank you! \n \n \n Kind regards.");
		Request request = new Request();
		Mail mail = new Mail(from, subject, to, content);
		request.setMethod(Method.POST);
		request.setEndpoint("mail/send");
		request.setBody(mail.build());
		Response response = sg.api(request);

		System.out.println(response.getStatusCode());
		System.out.println("body " + response.getBody());
		System.out.println("response header " + response.getHeaders());
	}

	public void sendMailTermination(String email, String firstName) throws IOException {
		Email from = new Email("justinjake.xurpas@gmail.com");
		Email to = new Email(email);
		String subject = "Termination letter";
		SendGrid sg = new SendGrid(System.getenv("SEND_GRID_API_KEY"));
		Content content = new Content("text/plain", "Hello + " + firstName + ", Terminated \n \n \n Kind regards.");
		Request request = new Request();
		Mail mail = new Mail(from, subject, to, content);
		request.setMethod(Method.POST);
		request.setEndpoint("mail/send");
		request.setBody(mail.build());
		Response response = sg.api(request);

		System.out.println(response.getStatusCode());
		System.out.println("body " + response.getBody());
		System.out.println("response header " + response.getHeaders());
	}
}
