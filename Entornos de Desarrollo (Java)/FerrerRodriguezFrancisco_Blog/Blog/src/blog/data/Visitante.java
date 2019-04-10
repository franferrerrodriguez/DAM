package blog.data;

public class Visitante extends Usuario {

	public void comentarPost() {
		System.out.println("Comentado de post con id por el usuario: " + super.getUsuario());
	}

}
