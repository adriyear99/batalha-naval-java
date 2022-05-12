import java.net.*;


public class ServerUDP {

	public static void main(String[] args) throws Exception {
		
		DatagramSocket tomadaServidora = new DatagramSocket(5000);
		System.out.println("Servidor em execução!");
		
		// Recebe mensagem do cliente e imprime na tela
		byte[] cartaRecebida = new byte[100];
		DatagramPacket envelopeRecebido = new DatagramPacket(cartaRecebida,cartaRecebida.length);
		
		tomadaServidora.receive(envelopeRecebido);
		String textoRecebido = new String(envelopeRecebido.getData());
		System.out.println(textoRecebido);
		
		// Envia mensagem de volta ao cliente
		byte[] cartaEnviada = new byte[100];
		String mensagem = "Dados recebidos com sucesso!";
		cartaEnviada = mensagem.getBytes();
		
		InetAddress ipCliente = envelopeRecebido.getAddress();
		int portaCliente = envelopeRecebido.getPort();
		
		DatagramPacket envelopeEnviado = new DatagramPacket(cartaEnviada,cartaEnviada.length,ipCliente,portaCliente);
		tomadaServidora.send(envelopeEnviado);
		
		// Finaliza conexão
		tomadaServidora.close();
	}

}
