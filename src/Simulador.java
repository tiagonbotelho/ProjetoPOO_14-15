import java.util.*;

public class Simulador {
    
    public static Ambiente a;
    
    private static boolean protectChar(String input) {
    	String simbols="?!.,;:-_`´^/()%&$#[]{}=+*|\"";
    	char [] items = input.toCharArray();
    	for(char c: items) {
    		if(Character.isLetter(c) || simbols.indexOf(c)!=-1) {
                    return false;
    		
    		}
    	}
    	return true;
    }

    public static Agente criaAgente(int lifespan) {
        String forma;
        String cor;
        Coord coorden;
        int x;
        int y;
        int option1;
        Agente robot;
        Scanner s = new Scanner(System.in);
        System.out.println("Que Robot deseja criar?");
        System.out.println(" 1: RandomJumper");
        System.out.println(" 2.HammingJumper");
        System.out.println(" 3.DistanceJumper\n\n");
        System.out.print("Introduza a sua opcao: ");
        option1=s.nextInt();
        s.nextLine();
        System.out.print("Introduza uma forma: ");
        forma=s.nextLine();
        System.out.print("Introduza uma cor: ");
        cor=s.nextLine();
        System.out.print("Introduza uma coordenada x: ");
        x=s.nextInt();
        System.out.print("introduza uma coordenada y: ");
        y=s.nextInt();
        coorden=new Coord(x,y);
        switch(option1) {
                case 1:
                    robot = new Aleatorio(forma, cor , coorden,lifespan); break;
                case 2:
                    robot = new Hamming(forma,cor,coorden,lifespan); break;
                default:
                    robot = new Distance(forma,cor,coorden,lifespan); break; 
        }
        robot.getMemory().addToWalk(coorden);
        robot.visionCamp(a);
        a.addEntidade(robot);
        return robot;
    }
    
    public static void criaObjeto(){
        Scanner sc = new Scanner(System.in);
        String forma,cor,type;
        int x,y;
        Coord pos;
        Objeto novo;
        System.out.println("Qual a forma do objeto que deseja criar?");
        forma = sc.nextLine();              /*TODO so aceitar cores e formas da lista*/
        System.out.println("Qual a cor do objeto que deseja criar?");
        cor = sc.nextLine();
        System.out.println("Introduza o tipo de objeto");
        type = sc.nextLine();
        while(true){
            System.out.println("Introduza uma coordenada x: ");
            x = sc.nextInt();
            System.out.println("Introduza uma coordenada y: ");
            y = sc.nextInt();
            pos = new Coord(x,y);
            novo = new Objeto(forma,cor,pos,type);
            if(!a.addEntidade(novo)){
                System.out.println("Essa posição já se encontra ocupada");
            }
            break;
        }
    }

    public static Ambiente newAmbient() {
    	
        String height,width;
        String lifeSpan;
        String campoVisao;
        Ambiente novo;
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduza o tamanho do novo ambiente.");
        System.out.print("Altura: ");
        height = sc.nextLine();
        while(!protectChar(height)) {
        	System.out.print("Altura invalida por favor introduza um valor válido: ");
        	height=sc.nextLine();
        }
        System.out.print("Largura: ");
        width = sc.nextLine();
        while(!protectChar(width)) {
        	System.out.print("Largura invalida por favor introduza um valor válido: ");
        	width=sc.nextLine();
        }
        System.out.print("Qual o tempo de vida dos robots que pretende?");
		lifeSpan = sc.nextLine();
        while(!protectChar(lifeSpan)) {
        	System.out.print("Input invalido por favor introduza um valor correto: ");
        	lifeSpan=sc.nextLine();
        }
        System.out.print("Qual o tamanho do campo de visão dos robots que pretende?");
        campoVisao = sc.nextLine();
        while(!protectChar(campoVisao)) {
        	System.out.print("Valor invalido por favor introduza um valor correto: ");
        }
        novo = new Ambiente(Integer.parseInt(width),Integer.parseInt(height), Integer.parseInt(lifeSpan), Integer.parseInt(campoVisao));
        return novo;
    }

    public static void main(String[] args) {
        int option1,option2;
        Scanner sc = new Scanner(System.in);
        System.out.println("Hello! Welcome to Robierto Simulator!");
        a=newAmbient();
        a.preencheAmbiente();
        loop : while(true) {
            a.updateAllPerceptions();
            System.out.println("1.Deseja mover um Agente;");
            System.out.println("2.Deseja ver a memória dos agentes;");
            System.out.println("3.Deseja ver o campo de visão do agentes;");
            System.out.println("4.Deseja ver a lista de objetos apreendidos pelos agentes;");
            System.out.println("5.Deseja ver todas as entidades no ambiente;");
            System.out.println("6.Deseja ver todas as posições pelo qual os robots passaram;");
            System.out.println("7.Adicionar nova Entidade;");
            System.out.println("8.Eliminar um Robot(0 para sair);");
            System.out.println("9.Deseja fazer um ambiente novo;");
            System.out.println("10.Deseja observar a distancia percorrida pelo Agente;");
            System.out.println("11.Sair");
            System.out.print("\nIntroduza a sua opcao: ");
            option1=sc.nextInt();
            while(option1 > 11 && !sc.hasNextInt()) {
            	System.out.println("Opcao invalida por favor introduza uma opcao valida...");
            	option1=sc.nextInt();
            }
            switch(option1) { /*FIXME*/
                case 1:
                    moveMenu(); break;
                case 2:
                    memoriaMenu(); break;
                case 3:
                    visionMenu(); break;
                case 4:
                    objetosMenu(); break;
                case 5:
                    a.imprimeLista(); break;
                case 6:
                    walkMenu(); break;
                case 7:
                    createMenu(); break;
                case 8:
                    a.printAgents();
                    System.out.println("Qual o id do robot que deseja eliminar?");
                    option2 = sc.nextInt();
                    if(a.getEntityByID(option2) instanceof Agente) {
                        a.deleteEntity(option2);
                    }
                    else {
                        System.out.println("Esse id não é de um agente.");
                    }
                    break;
                case 9:
                    a = newAmbient(); break;
                case 10:
                    distanceMenu(); break;
                case 11:
                    break loop;
                default:
                    System.out.println("Inválido"); break;
            }
        }
    }
    
    private static void distanceMenu() {
    	int option2;
    	int dist;
    	Scanner sc = new Scanner(System.in);
    	a.printAgents();
        System.out.print("\nQual o id do robot do qual deseja observar a distância percorrida: ");
        option2 = sc.nextInt();
        if(a.getEntityByID(option2) instanceof Agente) {
            dist=((Agente)a.getEntityByID(option2)).calcDistance();
            System.out.println(dist);
        }
        else {
            System.out.println("Esse id não é de um agente.");
        }     
    }
    
    private static void moveMenu() {
        int option1;
        Scanner sc = new Scanner(System.in);
        System.out.println("Que robot deseja mover? (0 se todos)\n");
        a.printAgents();
        option1 = sc.nextInt();
        if(option1 == 0) {
            a.moveAgents();
        }
        else {
            if(a.getEntityByID(option1) instanceof Agente) {
                ((Agente)a.getEntityByID(option1)).move(a);
            }
            else {
                System.out.println("Esse robot não existe");
            }
        }
    }
    
    private static void visionMenu() {
        int option1;
        Scanner sc = new Scanner(System.in);
        System.out.println("Deseja ver o campo de visão de que agente? (0 para sair)");
        a.printAgents();
        option1 = sc.nextInt();
        if(option1 != 0) {
            if(a.getEntityByID(option1) instanceof Agente) {
                ((Agente)a.getEntityByID(option1)).getPerception().imprimeVisao();
            }
            else {
                System.out.println("Esse robot não existe");
            }
        }
    }
    
    private static void memoriaMenu() {
        int option1;
        Scanner sc = new Scanner(System.in);
        System.out.println("Deseja ver a memória de que agente? (0 para sair)");
        a.printAgents();
        option1 = sc.nextInt();
        if(option1 != 0) {
            if(a.getEntityByID(option1) instanceof Agente) {
                ((Agente)a.getEntityByID(option1)).getMemory().imprimeMemoria();
            }
            else {
                System.out.println("Esse robot não existe");
            }
        }
    }
    
    private static void objetosMenu() {
        int option1;
        Scanner sc = new Scanner(System.in);
        System.out.println("Deseja ver os objetos de que agente? (0 para sair)");
        a.printAgents();
        option1 = sc.nextInt();
        if(option1 != 0) {
            if(a.getEntityByID(option1) instanceof Agente) {
                ((Agente)a.getEntityByID(option1)).getMemory().imprimeObjetos();
            }
            else {
                System.out.println("Esse robot não existe");
            }
        }
    }
    
    private static void walkMenu() {
        int option1;
        Scanner sc = new Scanner(System.in);
        System.out.println("Deseja ver os passos de que agente? (0 para sair)");
        a.printAgents();
        option1 = sc.nextInt();
        if(option1 != 0) {
            if(a.getEntityByID(option1) instanceof Agente) {
                ((Agente)a.getEntityByID(option1)).getMemory().printWalk();
            }
            else {
                System.out.println("Esse robot não existe");
            }
        }
    }
    
    private static void createMenu(){
        int option1;
        Scanner sc = new Scanner(System.in);
        System.out.println("Deseja criar que entidade?");
        System.out.println("1. Agente\t2.Objeto");
        option1 = sc.nextInt();
        switch(option1){
            case 1:
                criaAgente(a.getLifeSpan());
                break;
            case 2:
                criaObjeto();
                break;
            case 3:
                System.out.println("Escolha inválida");
                break;
        }
    }
            
}
