
import java.util.*;
import java.lang.*;

public class Simulador {
    
    public static Ambiente a;
    
    public static boolean protectChar(String input) {
    	char [] items = input.toCharArray();
    	boolean condition=false;
    	for(char c: items) {
    		if(!Character.isLetter(c)) {
    			condition=true;
    		}
    	}
    	return condition;
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
        System.out.println(" 1: RandomJumper\n");
        System.out.println(" 2.HammingJumper\n");
        System.out.println(" 3.DistanceJumper\n");
        option1=s.nextInt();
        System.out.println("Introduza uma forma: ");
        forma=s.next();
        System.out.println("Introduza uma cor: ");
        cor=s.next();
        System.out.println("Introduza uma coordenada x: ");
        x=s.nextInt();
        System.out.println("introduza uma coordenada y: ");
        y=s.nextInt();
        coorden=new Coord(x,y);
        switch(option1){
                case 1:
                    robot = new Aleatorio(forma, cor , coorden,lifespan);break;
                case 2:
                    robot = new Hamming(forma,cor,coorden,lifespan);break;
                default:
                    robot = new Distance(forma,cor,coorden,lifespan);break; 
        }
        robot.getMemory().add_to_walk(coorden);
        robot.vision_camp(a);
        a.add_entidade(robot);
        return robot;
    }

    public static Ambiente newAmbient() {
    	boolean tryout=false;
        String height,width;
        String lifeSpan;
        String campoVisao;
        Ambiente novo;
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduza o tamanho do novo ambiente.");
        System.out.println("Altura: ");
        height = sc.next();
        while(!protectChar(height)) {
        	System.out.print("Altura invalida por favor introduza um valor válido: ");
        	height=sc.next();
        }
        System.out.println("Largura: ");
        width = sc.next();
        while(!protectChar(width)) {
        	System.out.print("Largura invalida por favor introduza um valor válido: ");
        	width=sc.next();
        }
        System.out.println("Qual o tempo de vida dos robots que pretende?");
		lifeSpan = sc.next();
        while(!protectChar(lifeSpan)) {
        	System.out.print("Input invalido por favor introduza um valor correto: ");
        	lifeSpan=sc.next();
        }
        System.out.println("Qual o tamanho do campo de visão dos robots que pretende?");
        campoVisao = sc.next();
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
        a.preenche_ambiente();
        loop : while(true){
            System.out.println("1.Deseja mover um Agente;");
            System.out.println("2.Deseja ver a memória dos agentes;");
            System.out.println("3.Deseja ver o campo de visão do agentes;");
            System.out.println("4.Deseja ver a lista de objetos apreendidos pelos agentes;");
            System.out.println("5.Deseja ver todas as entidades no ambiente;");
            System.out.println("6.Deseja ver todas as posições pelo qual os robots passaram;");
            System.out.println("7.Adicionar novo Robot;");
            System.out.println("8.Eliminar um Robot(0 para sair);");
            System.out.println("9.Deseja fazer um ambiente novo;");
            System.out.println("10.Deseja observar a distancia percorrida pelo Agente;");
            System.out.println("10.Sair");
            System.out.print("\nIntroduza a sua opcao: ");
            option1=sc.nextInt();
            while(option1 > 11 && !sc.hasNextInt()) {
            	System.out.println("Opcao invalida por favor introduza uma opcao valida...");
            	option1=sc.nextInt();
            }
            switch(option1){
                case 1:
                    move_menu(a);
                    break;
                case 2:
                    memoria_menu(a);
                    break;
                case 3:
                    vision_menu(a);
                    break;
                case 4:
                    objetos_menu(a);
                    break;
                case 5:
                    a.ImprimeLista();
                    break;
                case 6:
                    walk_menu(a);
                    break;
                case 7:
                    criaAgente(a.getLifeSpan());
                    break;
                case 8:
                    a.print_agents();
                    System.out.println("Qual o id do robot que deseja eliminar?");
                    option2 = sc.nextInt();
                    if(a.get_entity_by_id(option2) instanceof Agente){
                        a.delete_entity(option2);
                    }
                    else{
                        System.out.println("Esse id não é de um agente.");
                    }
                    break;
                case 9:
                    a = newAmbient();
                    break;
                case 10:
                    break loop;
                default:
                    System.out.println("Inválido");
                    break;
            }

        }
    }
    
    private static void move_menu(Ambiente a){
        int option1;
        Scanner sc = new Scanner(System.in);
        System.out.println("Que robot deseja mover? (0 se todos)\n");
        a.print_agents();
        option1 = sc.nextInt();
        if(option1 == 0){
            a.move_agents();
        }
        else{
            if(a.get_entity_by_id(option1) instanceof Agente){
                ((Agente)a.get_entity_by_id(option1)).move(a);
            }
            else{
                System.out.println("Esse robot não existe");
            }
        }
    }
    
    private static void vision_menu(Ambiente a){
        int option1;
        Scanner sc = new Scanner(System.in);
        System.out.println("Deseja ver o campo de visão de que agente? (0 para sair)");
        a.print_agents();
        option1 = sc.nextInt();
        if(option1 != 0){
            if(a.get_entity_by_id(option1) instanceof Agente){
                ((Agente)a.get_entity_by_id(option1)).getPerception().imprime_Visao();
            }
            else{
                System.out.println("Esse robot não existe");
            }
        }
    }
    
    private static void memoria_menu(Ambiente a){
        int option1;
        Scanner sc = new Scanner(System.in);
        System.out.println("Deseja ver a memória de que agente? (0 para sair)");
        a.print_agents();
        option1 = sc.nextInt();
        if(option1 != 0){
            if(a.get_entity_by_id(option1) instanceof Agente){
                ((Agente)a.get_entity_by_id(option1)).getMemory().imprimeMemoria();
            }
            else{
                System.out.println("Esse robot não existe");
            }
        }
    }
    
    private static void objetos_menu(Ambiente a){
        int option1;
        Scanner sc = new Scanner(System.in);
        System.out.println("Deseja ver os objetos de que agente? (0 para sair)");
        a.print_agents();
        option1 = sc.nextInt();
        if(option1 != 0){
            if(a.get_entity_by_id(option1) instanceof Agente){
                ((Agente)a.get_entity_by_id(option1)).getMemory().imprimeObjetos();
            }
            else{
                System.out.println("Esse robot não existe");
            }
        }
    }
    
    private static void walk_menu(Ambiente a){
        int option1;
        Scanner sc = new Scanner(System.in);
        System.out.println("Deseja ver os passos de que agente? (0 para sair)");
        a.print_agents();
        option1 = sc.nextInt();
        if(option1 != 0){
            if(a.get_entity_by_id(option1) instanceof Agente){
                ((Agente)a.get_entity_by_id(option1)).getMemory().print_walk();
            }
            else{
                System.out.println("Esse robot não existe");
            }
        }
    }
}