package blog.data;

public class Autor extends Editor {

	@Override
	public void publicarPost() {
		System.out.println("Nos permiten o no publicación de post.");
		if (super.permitirPublicacion()) {
			super.publicarPost();
		}
	}

}
