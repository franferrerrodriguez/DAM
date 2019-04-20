package data;

import java.util.Date;

public class Reserva {

	private Date fecha_entrada;
	private int num_dias;

	public Reserva(Date fecha_entrada, int num_dias) {
		this.fecha_entrada = fecha_entrada;
		this.num_dias = num_dias;
	}

	public Date getFechaEntrada() {
		return fecha_entrada;
	}

	public void setFechaEntrada(Date fecha_entrada) {
		this.fecha_entrada = fecha_entrada;
	}

	public int getNumDias() {
		return num_dias;
	}

	public void setNumDias(int num_dias) {
		this.num_dias = num_dias;
	}

}