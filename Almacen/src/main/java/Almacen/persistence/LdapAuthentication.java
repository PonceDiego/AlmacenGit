package main.java.Almacen.persistence;

import java.util.Hashtable;

import javax.naming.AuthenticationException;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

public class LdapAuthentication {

	public static boolean validarLDAP(String user, String pass) {
		String base = "ou=users,dc=canal-doce,dc=com,dc=ar";
		String dn = "uid=" + user + "," + base;
		String ldapURL = "ldap://172.16.49.2:389";

		Hashtable<String, String> environment = new Hashtable<String, String>();
		environment.put(Context.INITIAL_CONTEXT_FACTORY,

				"com.sun.jndi.ldap.LdapCtxFactory");

		environment.put(Context.PROVIDER_URL, ldapURL);
		environment.put(Context.SECURITY_AUTHENTICATION, "simple");
		environment.put(Context.SECURITY_PRINCIPAL, dn);
		environment.put(Context.SECURITY_CREDENTIALS, pass);
		try {
			DirContext authContext = new InitialDirContext(environment);
			authContext.getAttributes("");
			return true;
		} catch (AuthenticationException e) {
			System.out.println("Datos ingresados no coinciden con LDAP");
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return false;
	}
}
