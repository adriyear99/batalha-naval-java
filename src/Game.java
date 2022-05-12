import java.util.Scanner;


public class Game {
	
	Scanner scan = new Scanner(System.in);
	static int linha = 0;
	static char coluna = 'x';
	static boolean running = true;
	
	// Verifica se a condição para acabar o jogo foi atingida
	public static boolean winCondition(Area[][] field) {
		int count = 0;
		for(int i=0;i<10;i++) {
			for(int j=0;j<10;j++) {
				if(field[i][j].value == '1') {
					count++;
				}
			}
		}
		return (count == 10);
	}
	
	// Verifica se o caractere digitado está contido no array de letras
	public static boolean validLetter(char valor) {
		for(char letra : Matrix.letters) {
			if(valor == letra) {
				return true;
			}
		}
		return false;
	}
	
	// Verifica o índice da letra no array
	public static int letterIndex(char coluna) {
		int valor = 0;
		for(int i=0; i<Matrix.letters.length; i++) {
			if(coluna == Matrix.letters[i]) {
				valor = i;
			}
		}
		return valor;
	}
	
	// Verifica se o tiro acertou algum navio
	public static void attack(int linha, char coluna, Area[][] field) {
		field[linha-1][letterIndex(coluna)].value = field[linha-1][letterIndex(coluna)].hiddenValue;	
	}

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		// Preenche a matriz
		Matrix.fill();
		
		// Posiciona os navios na matriz
		Matrix.placeShips();
		
		while(true) {
			
			// Turno do jogador 1
			Matrix.show();
			System.out.println("\nTurno do jogador 1");
			System.out.print("Digite a linha que quer atacar: ");
			do {
				try {
					linha = scan.nextInt();		
				} catch(Exception e) {
					System.out.println("Digite apenas números de 1 a 10");
					scan.next();
				}
			} while(linha < 1 || linha > 10);
			System.out.print("Digite a coluna que quer atacar: ");
			do{
				coluna = Character.toLowerCase(scan.next().charAt(0));
			} while(!validLetter(coluna));
			
			System.out.print("\n");
			attack(linha,coluna,Matrix.fieldTwo);
			
			if(winCondition(Matrix.fieldTwo)) {
				System.out.println("\nJogador 1 venceu!");
				break;
			}

			
			// Turno do jogador 2
			Matrix.show();
			System.out.println("\nTurno do jogador 2");
			System.out.print("Digite a linha que quer atacar: ");
			do {
				try {
					linha = scan.nextInt();		
				} catch(Exception e) {
					System.out.println("Digite apenas números de 1 a 10");
					scan.next();
				}
			} while(linha < 1 || linha > 10);
			System.out.print("Digite a coluna que quer atacar: ");
			do{
				coluna = Character.toLowerCase(scan.next().charAt(0));
			} while(!validLetter(coluna));
			
			System.out.print("\n");
			attack(linha,coluna,Matrix.fieldOne);
			
			if(winCondition(Matrix.fieldOne)) {
				System.out.println("\nJogador 2 venceu!");
				break;
			}
			
		}
	
		scan.close();
	}

}
