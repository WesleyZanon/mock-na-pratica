package service;

import dominio.Participante;
import dominio.Email;
import dominio.Jogo;
import infra.VencedorDaoFalso;

public class EnviaEmail {
	private VencedorDaoFalso emailDao;
	
	public EnviaEmail() {
	}
	
	public void enviarEmailParaVencedor(Email emailVencedor, Participante vencedor) {
		emailVencedor.setMensagem(String.format("Parabens %s! Você ganhou o jogo!", vencedor.getNome()));
		emailVencedor.setDestinatario(vencedor.getNome());
		emailVencedor.setAssunto("Parabens você ganhou um novo jogo!");
		emailDao.salva(vencedor);
	}
}
