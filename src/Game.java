import java.util.Random;


public class Matrix {
	
	public static Area[][] fieldOne = new Area[10][10];
	public static Area[][] fieldTwo = new Area[10][10];
	public static Ship submarino = new Ship(1,3);
	public static Ship cruzador = new Ship(2,2);
	public static Ship portaAvioes = new Ship(3,1);
	public static int line;
	public static int column;
	public static char direction;
	public static char[] letters = {'a','b','c','d','e','f','g','h','i','j'};
	
	
	// Preenche a matriz
	public static void fill() {
		
		// Preenche tabuleiro do jogador 1		
		for(int i=0;i<10;i++) {
			for(int j=0;j<10;j++) {
				fieldOne[i][j] = new Area('-','0',i+1,letters[j]);
			}
		}
		
		// Preenche tabuleiro do jogador 2
		for(int i=0;i<10;i++) {
			for(int j=0;j<10;j++) {
				fieldTwo[i][j] = new Area('-','0',i+1,letters[j]);
			}
		}
	}
	
	// Mostra a matriz na tela
	public static void show() {
		
		// Mostra tabuleiro do jogador 1
		System.out.println("====== JOGADOR 1 =====");
		for(int i=0;i<10;i++) {
			if(i == 0) {
				System.out.print("   ");
			}
			System.out.printf("%c ",letters[i]);
		}
		System.out.print("\n");
		
		for(int i=0;i<10;i++) {
			for(int j=0;j<10;j++) {
				if(j == 0) {
					if(i != 9) {
						System.out.printf("%d  ",i+1);
					} else {
						System.out.printf("%d ",i+1);
					}
				}
				System.out.printf("%c ", fieldOne[i][j].value);			
			}
			System.out.print('\n');
		}
		
		// Separa os dois tabuleiros
		System.out.println("\n");
		
		// Mostra tabuleiro do jogador 2
		System.out.println("====== JOGADOR 2 =====");
		for(int i=0;i<10;i++) {
			if(i == 0) {
				System.out.print("   ");
			}
			System.out.printf("%c ",letters[i]);
		}
		System.out.print("\n");
		
		for(int i=0;i<10;i++) {
			for(int j=0;j<10;j++) {
				if(j == 0) {
					if(i != 9) {
						System.out.printf("%d  ",i+1);
					} else {
						System.out.printf("%d ",i+1);
					}
				}
				System.out.printf("%c ", fieldTwo[i][j].value);			
			}
			System.out.print('\n');
		}
	}
	
	// Verifica se a posição na matriz está disponível
	public static boolean verify(Area[][] field, Ship ship, int line, int column, char direction) {
		try {
			for(int i=0;i<ship.size;i++) {
				if(direction == 'h') {
					if(field[line][column+i].hiddenValue == '1') {
						return false;
					}
				} else {
					if(field[line+i][column].hiddenValue == '1') {
						return false;
					}
				}	
			}
			return true;
			
		} catch(ArrayIndexOutOfBoundsException exception) {
			return false;
		}

	}
	
	// Posiciona cada navio por categoria
	public static void placeShip(Area[][] field, Ship ship) {
		
		while(ship.amount > 0) {
			
			boolean validArea = false;
			do {
				line = getRandomNumber();
				column = getRandomNumber();
				direction = getRandomDirection();
				validArea = verify(field,ship,line,column,direction);
			} while(!validArea);
			
			
			for(int i=0;i<ship.size;i++) {
				if(direction == 'h') {
					field[line][column+i].hiddenValue = '1';		
				} else {
					field[line+i][column].hiddenValue = '1';
				}
			}
			
			ship.amount--;
		}	
	}
	
	// Posiciona todos os navios na matriz
	public static void placeShips() {
		// Posiciona navios no tabuleiro do jogador 1
		placeShip(fieldOne,submarino);
		placeShip(fieldOne,cruzador);
		placeShip(fieldOne,portaAvioes);
		
		// Reinicia a quantidade de navios
		submarino.amount = 3;
		cruzador.amount = 2;
		portaAvioes.amount = 1;
		
		// Posiciona navios no tabuleiro do jogador 2
		placeShip(fieldTwo,submarino);
		placeShip(fieldTwo,cruzador);
		placeShip(fieldTwo,portaAvioes);
	}
	
	// Gera número aleatório dentro do limite
	public static int getRandomNumber() {
	    Random random = new Random();
	    return random.ints(0,10).findFirst().getAsInt();
	}
	
	// Recebe direção aleatória
	public static char getRandomDirection() {
		Random random = new Random();
		if (random.nextBoolean()) {
			return 'v';
		} else {
			return 'h';
		}
	}
	
}
