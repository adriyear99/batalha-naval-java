import java.net.*;
import java.util.Scanner;

public class ClientUDP {

	public static void main(String[] args) throws Exception {
		
		DatagramSocket tomada = new DatagramSocket();
		
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		System.out.print("Digite uma mensagem: ");
		String mensagem = scan.nextLine();

		// Preenche a matriz
		Matrix.fill();
		
		// Posiciona os navios na matriz
		Matrix.placeShips();
		
		// Enviar mensagem ao servidor
		byte[] carta = new byte[100];
		carta = mensagem.getBytes();
			
		InetAddress ip = InetAddress.getByName("127.0.0.1");
		DatagramPacket envelope = new DatagramPacket(carta,carta.length,ip,5000);
		tomada.send(envelope);
		
		
		// Receber uma mensagem do servidor
		byte[] cartaRecebida = new byte[100];
		DatagramPacket envelopeRecebido = new DatagramPacket(cartaRecebida,cartaRecebida.length);
		tomada.receive(envelopeRecebido);
		String mensagemRecebida = new String(envelopeRecebido.getData());
		System.out.println(mensagemRecebida);
		
		
		// Finaliza a conexão
		tomada.close();
	}
}
