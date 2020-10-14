package send.mail;

import java.security.GeneralSecurityException;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class TestMail {

	public static void main(String[] args) throws MessagingException, GeneralSecurityException {

		Properties props = new Properties();

		// 开启debug调试
		props.setProperty("mail.debug", "true");
		// 发送服务器需要身份验证
		props.setProperty("mail.smtp.auth", "true");
		// 设置邮件服务器主机名
		props.setProperty("mail.host", "smtp.163.com");
		// 发送邮件协议名称
		props.setProperty("mail.transport.protocol", "smtp");

		Session session = Session.getInstance(props);

		Message msg = new MimeMessage(session);
		msg.setSubject("您好！Redis Queue新注册账户验证码，120秒内有效");
		StringBuilder builder = new StringBuilder();
		builder.append("764614");
		msg.setText(builder.toString());
		msg.setFrom(new InternetAddress("HomeInGuanglunshan@163.com"));

		Transport transport = session.getTransport();
		transport.connect("smtp.163.com", "HomeInGuanglunshan@163.com", "lineteaseda");

		transport.sendMessage(msg, new Address[] { new InternetAddress("2467055745@qq.com") });
		transport.close();
	}

}