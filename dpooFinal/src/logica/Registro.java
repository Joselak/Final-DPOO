package dpooFinal.src.logica;

import java.time.LocalDateTime;

public class Registro {


	private Persona persona;
	private LocalDateTime horaEntrada;
	private LocalDateTime horaSalida;
	
	
	public Registro(Persona persona,LocalDateTime horaEntrada, LocalDateTime horaSalida) {
		this.persona = persona;
		this.horaEntrada = horaEntrada;
		this.horaSalida = horaSalida;
		
	}


	public Persona getPersona() {
		return persona;
	}


	public void setPersona(Persona persona) {
		this.persona = persona;
	}


	public LocalDateTime getHoraEntrada() {
		return horaEntrada;
	}

	public void setHoraEntrada(LocalDateTime horaEntrada) {
		this.horaEntrada = horaEntrada;
	}


	public LocalDateTime getHoraSalida() {
		return horaSalida;
	}

	public void setHoraSalida(LocalDateTime horaSalida) {
		this.horaSalida = horaSalida;
	}
	

}
