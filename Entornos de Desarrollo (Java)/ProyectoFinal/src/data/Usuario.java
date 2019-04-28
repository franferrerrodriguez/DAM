package data;

import java.util.ArrayList;
import java.util.List;

public class Usuario {

	private String username;
	private String password;
	private Boolean admin;
	List<Reserva> reservas;

	public Usuario(String username, String password) {
		this.username = username;
		this.password = password;
		this.admin = false;
		this.reservas = new ArrayList<Reserva>();
	}

	public Usuario(String username, String password, Boolean admin) {
		this.username = username;
		this.password = password;
		this.admin = admin;
		this.reservas = new ArrayList<Reserva>();
	}

	public String getUsername() {
		return username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public Boolean isAdmin() {
		return admin;
	}

	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}

	public static Boolean comprobarUsernamePassword(String username, String password) {

		Boolean control = false;

		if (username.length() > 0 && password.length() > 0)
			control = true;

		return control;

	}

}