package service;

import dominio.Participante;
import dominio.Sms;
import infra.VencedorDaoFalso;

public class EnviaSms {
	private VencedorDaoFalso smsDao;
	
	public EnviaSms() {
	}
	
	public void enviarSmsParaVencedor(Sms smsVencedor, Participante vencedor) {
		smsVencedor.setMensagem(String.format("Parabens %s! Você ganhou o jogo!", vencedor.getNome()));
		smsVencedor.setDestinatario(vencedor.getNome());
		smsDao.salva(vencedor);
	}
}
