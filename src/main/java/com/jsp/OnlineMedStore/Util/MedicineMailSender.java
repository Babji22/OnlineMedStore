package com.jsp.OnlineMedStore.Util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.jsp.OnlineMedStore.entity.Member;
import com.jsp.OnlineMedStore.entity.Ordered;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

public class MedicineMailSender 
{
	@Autowired
	JavaMailSender javaMailSender;

	public void enableRequest(Member member)
	{
		MimeMessage message=javaMailSender.createMimeMessage();
		
		try {
			MimeMessageHelper helper=new MimeMessageHelper(message, true, "UTF-8");
			
			helper.setFrom("janapalababji2233@gmail.com");
			helper.setTo("newqwertyuiop2233@gmail.com");
			helper.setSubject("Member verification Request");
			
			String htmlcontent="<h2>Hey Admin,</h2>"+ "<p>A member has been registered in our application. Please verify the details below:</p>"
					+ "<table border='1' cellpadding='5' cellspacing='0' style='border-collapse: collapse; width: 100%;'>"
					+ "<thead><tr><th style='border: 1px solid #000; padding: 8px;'>Field</th>"
					+ "<th style='border: 1px solid #000; padding: 8px;'>Details</th></tr></thead>" + "<tbody>"
					+ "<tr><td style='border: 1px solid #000; padding: 8px;'>Member ID</td>"
					+ "<td style='border: 1px solid #000; padding: 8px;'>" + member.getId() + "</td></tr>"
					+ "<tr><td style='border: 1px solid #000; padding: 8px;'>Name</td>"
					+ "<td style='border: 1px solid #000; padding: 8px;'>" + member.getName() + "</td></tr>"
					+ "<tr><td style='border: 1px solid #000; padding: 8px;'>Email</td>"
					+ "<td style='border: 1px solid #000; padding: 8px;'>" + member.getEmail() + "</td></tr>"
					+ "<tr><td style='border: 1px solid #000; padding: 8px;'>Mobile</td>"
					+ "<td style='border: 1px solid #000; padding: 8px;'>" + member.getMobile() + "</td></tr>"
					+ "<tr><td style='border: 1px solid #000; padding: 8px;'>Gender</td>"
					+ "<td style='border: 1px solid #000; padding: 8px;'>" + member.getGender() + "</td></tr>"
					+ "<tr><td style='border: 1px solid #000; padding: 8px;'>Date of Birth</td>"
					+ "<td style='border: 1px solid #000; padding: 8px;'>" + member.getDate() + "</td></tr>"
					+ "<tr><td style='border: 1px solid #000; padding: 8px;'>Address</td>"
					+ "<td style='border: 1px solid #000; padding: 8px;'>" + member.getAddress() + "</td></tr>" + "</tbody>"
					+ "</table>" + "<p>Please verify this member at your earliest convenience.</p>"
					+ "<p><img src='cid:capstoneImage' alt='Capstone Logo' /></p>";
			
			helper.setText(htmlcontent, true);
			ClassPathResource resource=new ClassPathResource("");
			helper.addInline("capstoneimage", resource);
			
			javaMailSender.send(message);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void orderPlaced(Member member,Ordered orders)
	{
		MimeMessage message=javaMailSender.createMimeMessage();
		try
		{
			MimeMessageHelper helper=new MimeMessageHelper(message, true,"UTF-8");
			helper.setFrom("janapalababji2233@gmail.com");
			helper.setTo("newqwertyuiop2233@gmail.com");
			helper.setSubject("Order Conformation.... Thank You.... Visit Again...");
			String htmlContent = "<h2>Dear Customer,</h2>"
					+ "<p>Thank you for your order! We're excited to confirm that we've received your purchase. Here are the details :</p>"
					+ "<table border='1' cellpadding='5' cellspacing='0' style='border-collapse: collapse; width: 100%;'>"
					+ "<thead><tr><th style='border: 1px solid #000; padding: 8px;'>Field</th>"
					+ "<th style='border: 1px solid #000; padding: 8px;'>Details</th></tr></thead>" + "<tbody>"
					+ "<tr><td style='border: 1px solid #000; padding: 8px;'>Customer ID</td>"
					+ "<td style='border: 1px solid #000; padding: 8px;'>" + member.getId() + "</td></tr>"
					+ "<tr><td style='border: 1px solid #000; padding: 8px;'>Name</td>"
					+ "<td style='border: 1px solid #000; padding: 8px;'>" + member.getName() + "</td></tr>"
					
					+ "<tr><td style='border: 1px solid #000; padding: 8px;'>Mobile</td>"
					+ "<td style='border: 1px solid #000; padding: 8px;'>" + member.getMobile() + "</td></tr>"
					
//	                + "<tr><td style='border: 1px solid #000; padding: 8px;'>Drug Name</td>"
//	                + "<td style='border: 1px solid #000; padding: 8px;'>" + drug.getName() + "</td></tr>"
//					
//					+ "<tr><td style='border: 1px solid #000; padding: 8px;'>Drug Price</td>"
//					+ "<td style='border: 1px solid #000; padding: 8px;'>" + Orders.drug.getPrice() + "</td></tr>"
//					
	                + "<tr><td style='border: 1px solid #000; padding: 8px;'>Order Id</td>"
	                + "<td style='border: 1px solid #000; padding: 8px;'>" + orders.getId() + "</td></tr>"
	                
	                +"<tr><td style='border: 1px solid #000; padding: 8px;'>Order Amount</td>"
					+ "<td style='border: 1px solid #000; padding: 8px;'>" + orders.getOrderAmount() + "</td></tr>"
//					+ "<tr><td style='border: 1px solid #000; padding: 8px;'>Drug Name</td>"
//					+ "<td style='border: 1px solid #000; padding: 8px;'>" + drug.getName() + "</td></tr>"
					+ "<tr><td style='border: 1px solid #000; padding: 8px;'>Address</td>"
					
					+ "<td style='border: 1px solid #000; padding: 8px;'>" + member.getAddress() + "</td></tr>" + "</tbody>"
					+ "</table>" + "<p>We’ll send you an update once your order is shipped. If you have any questions or need assistance, feel free to contact our support team at admin@gmail.com</p>"
					+"<p> Thank you for choosing us! We appreciate your business and hope you enjoy your purchase...</p>"
	                +"<p>Best regards,</p>"
	                +"<p>Medi Cart</p>"
	                +"<p>JSpiders,Kphb Phase-5,Hyderabad"
					+ "<p><img src='cid:capstoneImage' alt='Capstone Logo' /></p>"; // Embed image with CID

			// Set the HTML content for the email
			helper.setText(htmlContent, true);
			ClassPathResource resource = new ClassPathResource("templates/thank.jpg"); // Path for Image
			helper.addInline("capstoneImage", resource);
			
			                    
			
			// Send the email
			javaMailSender.send(message);
			} catch (MessagingException e) {
				e.printStackTrace();
			}
		}
}
