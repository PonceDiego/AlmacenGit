package main.java.Almacen.manager;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.sun.mail.smtp.SMTPTransport;

import main.java.Almacen.model.Pedido;
import main.java.Almacen.model.Pedidoxarticulos;
import main.java.Almacen.model.Usuario;
import main.java.Almacen.persistence.AreaDB;
import main.java.Almacen.persistence.ArticuloPedidoDB;
import main.java.Almacen.persistence.PedidoDB;
import main.java.Almacen.persistence.UsuarioDB;

public class MailManager {

	private static final String SMTP_SERVER = "192.168.74.23";
	private static final String USERNAME = "almacen@eldoceblog.com.ar";
	private static final String PASSWORD = "almacen.C12";
	private static final String EMAIL_FROM = "almacen@eldoceblog.com.ar";

	public static void enviarMail(String userS, int idp) throws MessagingException, IOException, GeneralSecurityException {

		Usuario u = UsuarioDB.getUsuarioByID(Integer.parseInt(userS));
		Pedido pedido = PedidoDB.getPedidoByID(idp);
		int idArea= u.getArea().getAreaId();
		Usuario user= AreaDB.getUsuarioJefeArea(idArea);
		String email = user.getEmail();
		System.out.println("Enviando mail a "+email+"..");

		String subject = "Se ha realizado un nuevo pedido en el área "+u.getArea().getNombreArea()+".";
		String bodyText = "Estimado/a " + user.getNombre() + " se le informa que el usuario "
				+ pedido.getUsuario().getNombreUsuario() + " ha realizado un pedido de: \n";
		List<Pedidoxarticulos> arts = ArticuloPedidoDB.getArticulosPedidosByPedido(idp);
		for (int i = 0; i < arts.size(); i++) {
			String append = "- " + arts.get(i).getCantidad() + " " + arts.get(i).getArticulo().getNombre() + "\n";
			bodyText += append;
		}
		String messageBody="\n Este mensaje ha sido generado automáticamente por el Sistema Almacen.";
		bodyText += messageBody;
		createEmail(email,subject,bodyText);

	}

	private static void createEmail(String to, String subject, String bodyText)
			throws MessagingException {
		Properties props = System.getProperties();
		props.put("mail.smtp.host", SMTP_SERVER);
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "25");
		Session session = Session.getDefaultInstance(props, null);

		MimeMessage email = new MimeMessage(session);

		email.setFrom(new InternetAddress(EMAIL_FROM));
		email.addRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(to));
		email.setSubject(subject);
		email.setText(bodyText);

		SMTPTransport t = (SMTPTransport) session.getTransport("smtp");
		t.connect(SMTP_SERVER, USERNAME, PASSWORD);
		t.sendMessage(email, email.getAllRecipients());
		System.out.println("Response: " + t.getLastServerResponse());

        t.close();

	}

}
