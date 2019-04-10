package blog.data;

public class Editor extends Usuario {

	public void publicarPost() {
		System.out.println("Publicación de post por el usuario: " + super.getUsuario());
	}

	public Boolean permitirPublicacion() {
		System.out.println("Permitir publicación de post por el usuario: " + super.getUsuario());
		return false;
	}

}
