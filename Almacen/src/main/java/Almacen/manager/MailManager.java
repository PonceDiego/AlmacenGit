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

import main.java.Almacen.manager.llaves.LlaveManager;
import main.java.Almacen.model.GrupoLlaves;
import main.java.Almacen.model.Llave;
import main.java.Almacen.model.Pedido;
import main.java.Almacen.model.Pedidoxarticulos;
import main.java.Almacen.model.Usuario;
import main.java.Almacen.persistence.AreaDB;
import main.java.Almacen.persistence.ArticuloPedidoDB;
import main.java.Almacen.persistence.PedidoDB;
import main.java.Almacen.persistence.UsuarioDB;

public class MailManager {

//	private static final String SMTP_SERVER = "192.168.74.23";
	private static final String SMTP_SERVER = "smtp.gmail.com";
//	private static final String USERNAME = "almacen@eldoceblog.com.ar";
	private static final String USERNAME = "daikoponce@gmail.com";
//	private static final String PASSWORD = "almacen.C12";
	private static final String PASSWORD = "Diegoponce11";

//	private static final String EMAIL_FROM = "almacen@eldoceblog.com.ar";
	private static final String EMAIL_FROM = "daikoponce@gmail.com";

	public static void enviarMail(String userS, int idp)
			throws MessagingException, IOException, GeneralSecurityException {

		Usuario u = UsuarioDB.getUsuarioByID(Integer.parseInt(userS));
		Pedido pedido = PedidoDB.getPedidoByID(idp);
		int idArea = u.getArea().getId();
		Usuario user = AreaDB.getUsuarioJefeArea(idArea);
		String email = user.getEmail();
		System.out.println("Enviando mail a " + email + "..");

		String subject = "Se ha realizado un nuevo pedido en el área " + u.getArea().getNombre() + ".";
		String bodyText = "Estimado/a " + user.getNombre() + " se le informa que el usuario "
				+ pedido.getUsuario().getNombreUsuario() + " ha realizado un pedido de: \n";
		List<Pedidoxarticulos> arts = ArticuloPedidoDB.getArticulosPedidosByPedido(idp);
		for (int i = 0; i < arts.size(); i++) {
			String append = "- " + arts.get(i).getCantidad() + " " + arts.get(i).getArticulo().getNombre() + "\n";
			bodyText += append;
		}
		String messageBody = "\n Este mensaje ha sido generado automáticamente por el Sistema Almacen.";
		bodyText += messageBody;
		// createEmail(email, subject, bodyText);

	}

	public static void mailLlaves(int idUserString, int idLlave, int idEncargadoString) throws MessagingException {
		Usuario solicitante = UsuarioDB.getUsuarioByID(idUserString);
		Usuario encargado = UsuarioDB.getUsuarioByID(idEncargadoString);
		Llave llave = LlaveManager.getLlaveByIntId(idLlave);
		String mail = solicitante.getEmail();

		String subject = "Se le ha asignado la llave " + llave.getNombre() + ", copia " + llave.getCopia();
		String bodyText = "Estimado/a " + solicitante.getNombre() + ", el usuario " + encargado.getNombreUsuario()
				+ " ha marcado salida de la llave " + llave.getNombre() + " a su nombre.\n"
				+ "Si considera que esto es un error, por favor comuníquese con el encargado a la brevedad.\n"
				+ "\n\nEste mensaje ha sido generado automáticamente por el Sistema Almacén.";
		// createEmail(mail, subject, bodyText);
	}

	public static void mailGrupoLlaves(int idUserString, String idGrupo, int idEncargado) throws MessagingException {
		Usuario solicitante = UsuarioDB.getUsuarioByID(idUserString);
		Usuario encargado = UsuarioDB.getUsuarioByID(idEncargado);
		GrupoLlaves grupoLlave = LlaveManager.getGrupoLlaveById(idGrupo);
		String mail = solicitante.getEmail();

		String subject = "Se le ha asignado el grupo " + grupoLlave.getNombre() + ".";
		String bodyText = "Estimado/a " + solicitante.getNombre() + ", el usuario " + encargado.getNombreUsuario()
				+ " ha marcado salida del grupo de llaves " + grupoLlave.getNombre() + " a su nombre.\n"
				+ "Este grupo contempla las siguientes llaves:\n";
		for (Llave llave : grupoLlave.getLlaves()) {
			bodyText += "-" + llave.getNombre() + "\n";
		}
		bodyText += "Si considera que esto es un error, por favor comuníquese con el encargado a la brevedad.\n"
				+ "\n\nEste mensaje ha sido generado automáticamente por el Sistema Almacén.";
		// createEmail(mail, subject, bodyText);
	}

	private static void createEmail(String to, String subject, String bodyText) throws MessagingException {
		Properties props = System.getProperties();
		props.put("mail.smtp.host", SMTP_SERVER);
		props.put("mail.smtp.auth", "true");
		// TODO: Default and recommended port is '25', this one is for testing purposes
		// only and should be changed.
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.starttls.enable", true);
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
