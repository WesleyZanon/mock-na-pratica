package dominio;

import java.util.ArrayList;
import java.util.List;

public class Email {
	private int id;
	private String Assunto;
	private String mensagem;
	private String destinatario;
	
	
	public String getAssunto() {
		return Assunto;
	}
	public void setAssunto(String assunto) {
		Assunto = assunto;
	}
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	public String getDestinatario() {
		return destinatario;
	}
	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}
	
	

	
	
}
