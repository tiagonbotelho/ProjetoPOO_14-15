
import java.util.*;
import java.lang.*;

public class Simulador {
    
    public static Ambiente a;

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

    public void newAmbient() {
           /*TODO complete this function*/
    }

    public static void main(String[] args) {
        int option1,option2;
        int altura;
        int largura;
        int lifeSpan;
        int campoVisao;
        Scanner sc = new Scanner(System.in);
        System.out.println("Hello! Welcome to Robierto Simulator!");
        System.out.println("Introduza o tamanho do Ambiente em que quer testar: ");
        System.out.println("Altura: ");
        altura=sc.nextInt();
        System.out.println("Largura: ");
        largura=sc.nextInt();
        System.out.println("Introduza o respetivo tempo de vida do ambiente: ");
        lifeSpan=sc.nextInt();
        System.out.println("Defina o campo de visao do robot: ");
        campoVisao=sc.nextInt();
        a=new Ambiente(largura, altura, lifeSpan, campoVisao);
        a.preenche_ambiente();
        loop : while(true){
            System.out.println("1.Deseja mover um Agente");
            System.out.println("2.Deseja ver a memória dos agentes");
            System.out.println("3.Deseja ver o campo de visão do agentes");
            System.out.println("4.Deseja ver a lista de objetos apreendidos pelos agentes");
            System.out.println("5.Deseja ver todas as entidades no ambiente");
            System.out.println("6.Deseja ver todas as posições pelo qual os robots passaram");
            System.out.println("7.Adicionar novo Robot");
            System.out.println("8.Eliminar um Robot(0 para sair)");
            System.out.println("9.Sair");
            option1=sc.nextInt();
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