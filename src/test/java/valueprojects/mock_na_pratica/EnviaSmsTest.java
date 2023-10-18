package valueprojects.mock_na_pratica;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Calendar;

import org.junit.Test;
import br.com.value.jogo.builder.CriadorDeJogo;
import service.EnviaSms;
import service.FinalizaJogo;
import service.Juiz;
import dominio.Jogo;
import dominio.Participante;
import dominio.Resultado;
import dominio.Sms;
import infra.JogoDao;
import infra.VencedorDaoFalso;

public class EnviaSmsTest {
	
	@Test
	public void deveEnviarSmsAoVencedorAposFinalizarESalverJogoDaSemanaAnteriorESalvarVencedor() {
		Calendar antiga = Calendar.getInstance();
		antiga.set(2022, 3, 28);
		
		//cria um jogo
		Jogo jogo = new CriadorDeJogo().para("Squid Game").naData(antiga).constroi();
		
		//estabele 3 Resultados, cada um com um Participante diferente
		jogo.anota(new Resultado(new Participante("Steve jobs"),2000));
		jogo.anota(new Resultado(new Participante("Steve wozniak"),3000));
		jogo.anota(new Resultado(new Participante("Seong Gi-hun"),456000));
		
		//Mocka um DAO falso do jogo
		JogoDao jogoDaoFalso = mock(JogoDao.class);
		
		//finaliza o jogo da semana anterior
		FinalizaJogo finalizador = new FinalizaJogo(jogoDaoFalso);
		finalizador.finaliza();
		
		//salva o jogo finalizado no DAO falso e verifica se o metodo salvo foi chamado
		jogoDaoFalso.salva(jogo);
		verify(jogoDaoFalso, times(1)).salva(jogo);
		
		
		//instancia um juiz e avalia o jogo
		Juiz juiz = new Juiz();
		juiz.avalia(jogo);
		
		//pega o vencedor após a avaliação feita pelo juiz
		Participante vencedor = juiz.getTresMaiores().get(0).getParticipante();
		
		//mock um DAO falso para salvar o vencedor
		VencedorDaoFalso vencedorDaoFalso = mock(VencedorDaoFalso.class);
		
		//Salva o vencedor no DAO e verifica se o metodo salvar foi chamado
		vencedorDaoFalso.salva(vencedor);
		verify(vencedorDaoFalso, times(1)).salva(vencedor);
		
		
		//mocka o serviço EnviaSms e verifica se o metodo enviarSmsParaVencedor foi chamado e, Assim, o SMS foi enviado ao vencedor
		EnviaSms enviaSms = mock(EnviaSms.class);
		
		Sms smsVencedor = new Sms();
		enviaSms.enviarSmsParaVencedor(smsVencedor, vencedor);
		verify(enviaSms, times(1)).enviarSmsParaVencedor(smsVencedor, vencedor);
	}


}
