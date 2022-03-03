package gr.ntua.ece.project.services;

import gr.ntua.ece.project.entities.Event;
import gr.ntua.ece.project.entities.Parent;
import gr.ntua.ece.project.entities.User;
import gr.ntua.ece.project.entities.Ticket;
import gr.ntua.ece.project.entities.Report;
import gr.ntua.ece.project.forms.EventForm;
import gr.ntua.ece.project.forms.SearchForm;
import gr.ntua.ece.project.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import java.util.*;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.PasswordAuthentication;
import javax.mail.event.*;
import java.net.*;
import javax.activation.*;
import java.io.*;
import javax.mail.util.ByteArrayDataSource;
import javax.activation.DataHandler;
import javax.activation.DataSource;

@Service
public class TicketServiceImpl implements TicketService{

    @Autowired
    private ReportRepository eventRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ParentRepository parentRepository;
    @Autowired
    private TicketRepository ticketRepository;

    @Override
    public Ticket createTicket(Event event, Parent parent, int quantity){
        Ticket ticket = new Ticket();
        ticket.setQuantity(quantity);
        //report.setParentUserId(parent.getUserId()); To afhnw gia th diafora!
        ticket.setParentByParentUserId(parent);
        //report.setEventId(event.getId()); Same!!
        ticket.setEventByEventId(event);
        ticket.setDateTime(new Timestamp(System.currentTimeMillis()));


        ticket = ticketRepository.save(ticket);

        return ticket;
    }

    @Override
    public Ticket findById(int id) {

        return ticketRepository.findById(id);
    }

    @Override
    public void sendEmail(Event event, Parent parent, Ticket ticket){

        final String username = "rozpandatickets@gmail.com";
        final String password = "6guys2018";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        String content = "Ticket booked via RozPanda\n\n\n\n\n" +
                "Your ticket(s) for event: "+event.getTitle()+"\n\n Number of tickets: "
                + ticket.getQuantity() + "\n\n Amount paid: " + String.valueOf(event.getTicketPrice().multiply(new BigDecimal(ticket.getQuantity())))
                + "\n\n Ticket code: " + String.valueOf(ticket.getId())
                + "\n\n\n\n Have fun!";

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        ByteArrayOutputStream outputStream = null;

        try {
            //construct the text body part
            MimeBodyPart textBodyPart = new MimeBodyPart();
            textBodyPart.setText("Θα βρείτε συνημμένο το εισιτήριό σας σε μορφή PDF.\nΚαλή διασκέδαση!");

            //now write the PDF content to the output stream
            outputStream = new ByteArrayOutputStream();
            writePdf(outputStream, content);
            byte[] bytes = outputStream.toByteArray();

            //construct the pdf body part
            DataSource dataSource = new ByteArrayDataSource(bytes, "application/pdf");
            MimeBodyPart pdfBodyPart = new MimeBodyPart();
            pdfBodyPart.setDataHandler(new DataHandler(dataSource));
            pdfBodyPart.setFileName("ticket.pdf");

            //construct the mime multi part
            MimeMultipart mimeMultipart = new MimeMultipart();
            mimeMultipart.addBodyPart(textBodyPart);
            mimeMultipart.addBodyPart(pdfBodyPart);

            //create the sender/recipient addresses
            InternetAddress iaSender = new InternetAddress(username);
            InternetAddress iaRecipient = new InternetAddress(parent.getUserByUserId().getEmail());

            //construct the mime message
            MimeMessage mimeMessage = new MimeMessage(session);
            mimeMessage.setSender(iaSender);
            mimeMessage.setSubject(event.getTitle());
            mimeMessage.setRecipient(Message.RecipientType.TO, iaRecipient);
            mimeMessage.setContent(mimeMultipart);

            //send off the email
            Transport.send(mimeMessage);

            System.out.println("tinaftore");
        } catch(Exception ex) {
            System.out.println("fuck");
            ex.printStackTrace();
        } finally {
            //clean off
            if(null != outputStream) {
                try { outputStream.close(); outputStream = null; }
                catch(Exception ex) { }
            }
        }
    }

    @Override
    public void writePdf(OutputStream outputStream, String content) throws Exception {
        Document document = new Document();
        PdfWriter.getInstance(document, outputStream);
        document.open();
        Paragraph paragraph = new Paragraph();
        paragraph.add(new Chunk(content));
        document.add(paragraph);
        document.close();
    }

}