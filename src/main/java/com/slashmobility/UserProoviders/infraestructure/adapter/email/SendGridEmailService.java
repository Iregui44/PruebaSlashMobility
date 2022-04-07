package com.slashmobility.UserProoviders.infraestructure.adapter.email;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import com.slashmobility.UserProoviders.domain.model.User;
import com.slashmobility.UserProoviders.domain.service.IEmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@Service
public class SendGridEmailService implements IEmailService {

    @Autowired
    private SendGrid sendGrid;

    @Override
    public boolean sendConfirmationEmail(User user) {

        Email from = new Email("pireguis_ca64@hotmail.com");
        String subject = "Email Confirmation";
        Email to = new Email(user.getEmail());
        Content content = new Content("text/html", "<p> Please confirm your email to complete de registration. By clicking: <a href=\"http://localhost:8080/api/users/" + user.getUserName() + "/email/confirmation\"> Link </a> </p>");
        Mail mail = new Mail(from, subject, to, content);

        //SendGrid sg = new SendGrid(System.getenv("SENDGRID_API_KEY"));

        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = this.sendGrid.api(request);
            log.info("Send email to: {} status {}", user.getEmail(), response.getStatusCode());
            return true;
        } catch (IOException ex) {
            log.error("Cant send email", ex);
            return false;
        }

    }
}
