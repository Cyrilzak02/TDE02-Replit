import java.util.Scanner;
import java.util.Random;

public class Main {

    public static void print_queues(Pilha p1, Pilha p2, Pilha p3, int size) {
        Node end_p1 = p1.getTop();
        Node end_p2 = p2.getTop();
        Node end_p3 = p3.getTop();
        Node actual_p1 = p1.getFirst();

        Node actual_p2 = p2.getFirst();

        Node actual_p3 = p3.getFirst();

        for (int i = size; i > 0; i--) {
            // Print p1

            actual_p1 = p1.getFirst();

            actual_p2 = p2.getFirst();

            actual_p3 = p3.getFirst();

            if (end_p1 != null && p1.getCount() >= i) {
                System.out.print("| " + end_p1.getData() + " |");

            }

            else {
                System.out.print("|   |");
            }

            if (end_p2 != null && p2.getCount() >= i) {
                System.out.print("\t\t| " + end_p2.getData() + " |");

            } else {
                System.out.print("\t\t|   |");
            }

            if (end_p3 != null && p3.getCount() >= i) {
                System.out.print("\t\t| " + end_p3.getData() + " |");

            } else {
                System.out.print("\t\t|   |");
            }

            if (p1.getCount() >= i) {
                while (actual_p1 != null && actual_p1.getNext() != end_p1) {
                    actual_p1 = actual_p1.getNext();
                }
                end_p1 = actual_p1;
            }
            if (p2.getCount() >= i) {

                while (actual_p2 != null && actual_p2.getNext() != end_p2) {
                    actual_p2 = actual_p2.getNext();
                }
                end_p2 = actual_p2;
            }
            if (p3.getCount() >= i) {
                while (actual_p3 != null && actual_p3.getNext() != end_p3) {
                    actual_p3 = actual_p3.getNext();
                }
                end_p3 = actual_p3;
            }
            System.out.println();

        }

        System.out.println("  p1  \t\t   p2  \t\t  p3  ");
    }

    public static int solveMinimumSteps(int n) {
        // Minimum number of steps for Towers of Hanoi problem
        return (int) Math.pow(2, n) - 1;
    }

    public static void main(String[] args) {
        Boolean ascending = true;
        int size_pilhas = 0;
        int decision = -1;
        int played_rounds = 0;

        Scanner in = new Scanner(System.in);
        System.out.print("Bem vindo ao jogo, escolhe o tamanho das pilhas : ");
        size_pilhas = in.nextInt();
        Pilha p1 = new Pilha(size_pilhas);
        Pilha p2 = new Pilha(size_pilhas);
        Pilha p3 = new Pilha(size_pilhas);
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.print("Ordem crescente (Y/N)? (caso digitou N sera decrescente) : ");

        String response = in.next();
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        if (response.equals("N")) {
            ascending = false;
        }
        System.out.println(ascending);

        Random gerador = new Random();
        for (int i = 0; i < size_pilhas; i++) {
            int numDado = gerador.nextInt(100) + 1;
            p1.push(numDado, ascending);

        }

        System.out.println();
        while (true) {
            print_queues(p1, p2, p3, size_pilhas);

            if ((p1.getCount() == size_pilhas && p1.is_ordered(ascending))
                    || (p2.getCount() == size_pilhas && p2.is_ordered(ascending))
                    || (p3.getCount() == size_pilhas && p3.is_ordered(ascending))) {
                System.out.println("Voce gagnhou parabens em " + played_rounds + " jogadas !!!!");
                break;
            }
            System.out.println("++++++++++++++++++++++++++++++++++++++++++");
            System.out.println("Rounds Played : " + played_rounds);
            System.out.println("++++++++++++++++++++++++++++++++++++");
            System.out.println(" Escolha uma opcao: ");
            System.out.println("0 - Sair do jogo ");
            System.out.println("1 - Movimentar");
            System.out.println("2 - Solucao Automatica");
            System.out.print("Opcao :");
            decision = in.nextInt();

            if (decision == 0) {
                System.out.println("Ciao !!");
                break;
            }
            if (decision == 1) {
                Pilha o = new Pilha(size_pilhas);
                Pilha d = new Pilha(size_pilhas);
                Node removed = new Node();
                while (true) {

                    System.out.print("Pilha de origem (p1,p2 ou p3) : ");
                    String origem = in.next();
                    System.out.print("Pilha de destino (p1,p2 ou p3) : ");
                    String destino = in.next();
                    // if(origem != destino && (origem == "p1" || origem =="p2" || origem =="p3") &&
                    // (destino == "p1" || destino =="p2" || origem =="p3")){
                    if (origem.equals(destino)
                            || ((!origem.equals("p1") && !origem.equals("p2") && !origem.equals("p3"))
                                    || (!destino.equals("p1") && !destino.equals("p2") && !destino.equals("p3")))) {
                        System.out.println("Instrucao errada");
                        continue;
                    } else {
                        if (origem.equals("p1")) {
                            o = p1;
                        } else if (origem.equals("p2")) {
                            o = p2;
                        } else {
                            o = p3;
                        }
                        if (destino.equals("p1")) {
                            d = p1;
                        } else if (destino.equals("p2")) {
                            d = p2;
                        } else {
                            d = p3;
                        }
                        if (o.getCount() == 0) {
                            System.out.println("Voce nao pode mover um elemento de uma pilha vazia.");
                            continue;
                        }
                        if (d.getCount() == size_pilhas) {
                            System.out.println("Voce nao pode mover um elemento para uma pilha cheia ja ");
                            continue;
                        }
                        if (ascending && d.getTop() != null && (o.getTop().getData() > d.getTop().getData())) {
                            System.out.println(
                                    "Nao pode deslocar o topo de essa pilha porque e maior do que o topo da outra pilha");
                            continue;
                        } else if (!ascending && d.getTop() != null && (o.getTop().getData() < d.getTop().getData())) {
                            System.out.println(
                                    "Nao pode deslocar o topo de essa pilha porque e menpr do que o topo da outra pilha");
                            continue;
                        } else {

                            if (p1 == o) {
                                removed = p1.pop();
                            } else if (p2 == o) {
                                removed = p2.pop();
                            } else {
                                removed = p3.pop();
                            }
                            if (p1 == d) {
                                p1.push(removed.getData(), ascending);
                            } else if (p2 == d) {
                                p2.push(removed.getData(), ascending);
                            } else {
                                p3.push(removed.getData(), ascending);
                            }

                            played_rounds++;

                            break;

                        }

                    }

                }

            }
            if (decision == 2) {

                Pilha p1_1 = new Pilha(size_pilhas);
                Pilha p2_1 = new Pilha(size_pilhas);
                Pilha p3_1 = new Pilha(size_pilhas);
                Node actual_p1 = p1.getFirst();
                Node actual_p2 = p2.getFirst();
                Node actual_p3 = p3.getFirst();
                while (actual_p1 != null) {
                    p1_1.push(actual_p1.getData(), ascending);
                    actual_p1 = actual_p1.getNext();
                }
                while (actual_p2 != null) {
                    p2_1.push(actual_p2.getData(), ascending);
                    actual_p2 = actual_p2.getNext();
                }
                while (actual_p3 != null) {
                    p3_1.push(actual_p3.getData(), ascending);
                    actual_p3 = actual_p3.getNext();
                }
                int steps = played_rounds;

                while (true) {
                    Boolean isP1Null = p1_1.getCount() == 0;
                    Boolean isP2Null = p2_1.getCount() == 0;
                    Boolean isP3Null = p3_1.getCount() == 0;

                    if ((p1_1.is_ordered(ascending) && p1_1.getCount() == size_pilhas)
                            || (p2_1.is_ordered(ascending) && p2_1.getCount() == size_pilhas)
                            || (p3_1.is_ordered(ascending) && p3_1.getCount() == size_pilhas)) {
                        break;
                    }
                    if(size_pilhas == 3){
                    if (!isP1Null && isP2Null && isP3Null) {
                        p2_1.push(p1_1.pop().getData(), ascending);
                        ++steps;
                        print_queues(p1_1, p2_1, p3_1, size_pilhas);
                        System.out.println("Numero jogadas :" + steps);
                        p3_1.push(p1_1.pop().getData(), ascending);
                        ++steps;
                        print_queues(p1_1, p2_1, p3_1, size_pilhas);
                        System.out.println("Numero jogadas :" + steps);

                    } else if (isP1Null && !isP2Null && isP3Null) {
                        p1_1.push(p2_1.pop().getData(), ascending);
                        ++steps;
                        print_queues(p1_1, p2_1, p3_1, size_pilhas);
                        System.out.println("Numero jogadas :" + steps);
                        p3_1.push(p2_1.pop().getData(), ascending);
                        ++steps;
                        print_queues(p1_1, p2_1, p3_1, size_pilhas);
                        System.out.println("Numero jogadas :" + steps);
                    } else if (isP1Null && isP2Null && !isP3Null) {
                        p1_1.push(p3_1.pop().getData(), ascending);
                        ++steps;
                        print_queues(p1_1, p2_1, p3_1, size_pilhas);
                        System.out.println("Numero jogadas :" + steps);
                        p2_1.push(p3_1.pop().getData(), ascending);
                        ++steps;
                        print_queues(p1_1, p2_1, p3_1, size_pilhas);
                        System.out.println("Numero jogadas :" + steps);
                    }
                    
                    if (ascending) {
                        Node top_p1 = p1_1.getTop();
                        Node top_p2 = p2_1.getTop();
                        Node top_p3 = p3_1.getTop();
                        if (!isP1Null && !isP2Null && !isP3Null) {
                            if (top_p1.getData() < top_p2.getData() && top_p2.getData() < top_p3.getData()) {
                                p3_1.push(p2_1.pop().getData(), ascending);
                                ++steps;
                                print_queues(p1_1, p2_1, p3_1, size_pilhas);
                                System.out.println("Numero jogadas :" + steps);
                                p3_1.push(p1_1.pop().getData(), ascending);
                                ++steps;
                                print_queues(p1_1, p2_1, p3_1, size_pilhas);
                                System.out.println("Numero jogadas :" + steps);

                            } else if (top_p2.getData() < top_p1.getData() && top_p1.getData() < top_p3.getData()) {
                                p3_1.push(p1_1.pop().getData(), ascending);
                                ++steps;
                                print_queues(p1_1, p2_1, p3_1, size_pilhas);
                                System.out.println("Numero jogadas :" + steps);
                                p3_1.push(p2_1.pop().getData(), ascending);
                                ++steps;
                                print_queues(p1_1, p2_1, p3_1, size_pilhas);
                                System.out.println("Numero jogadas :" + steps);

                            } else if (top_p3.getData() < top_p1.getData() && top_p1.getData() < top_p2.getData()) {
                                p2_1.push(p1_1.pop().getData(), ascending);
                                ++steps;
                                print_queues(p1_1, p2_1, p3_1, size_pilhas);
                                System.out.println("Numero jogadas :" + steps);
                                p2_1.push(p3_1.pop().getData(), ascending);
                                ++steps;
                                print_queues(p1_1, p2_1, p3_1, size_pilhas);
                                System.out.println("Numero jogadas :" + steps);
                            } else if (top_p1.getData() < top_p3.getData() && top_p3.getData() < top_p2.getData()) {
                                p2_1.push(p3_1.pop().getData(), ascending);
                                ++steps;
                                print_queues(p1_1, p2_1, p3_1, size_pilhas);
                                System.out.println("Numero jogadas :" + steps);
                                p2_1.push(p1_1.pop().getData(), ascending);
                                ++steps;
                                print_queues(p1_1, p2_1, p3_1, size_pilhas);
                                System.out.println("Numero jogadas :" + steps);
                            } else if (top_p3.getData() < top_p2.getData() && top_p2.getData() < top_p1.getData()) {
                                p1_1.push(p2_1.pop().getData(), ascending);
                                ++steps;
                                print_queues(p1_1, p2_1, p3_1, size_pilhas);
                                System.out.println("Numero jogadas :" + steps);
                                p1_1.push(p3_1.pop().getData(), ascending);
                                ++steps;
                                print_queues(p1_1, p2_1, p3_1, size_pilhas);
                                System.out.println("Numero jogadas :" + steps);
                            } else if (top_p2.getData() < top_p3.getData() && top_p3.getData() < top_p1.getData()) {
                                p1_1.push(p3_1.pop().getData(), ascending);
                                ++steps;
                                print_queues(p1_1, p2_1, p3_1, size_pilhas);
                                System.out.println("Numero jogadas :" + steps);
                                p1_1.push(p2_1.pop().getData(), ascending);
                                ++steps;
                                print_queues(p1_1, p2_1, p3_1, size_pilhas);
                                System.out.println("Numero jogadas :" + steps);
                                
                            }

                        } else if (!isP1Null && !isP2Null && isP3Null) {
                            if (p1_1.getCount() > p2_1.getCount()) {
                                p3_1.push(p1_1.pop().getData(), ascending);
                                ++steps;
                                print_queues(p1_1, p2_1, p3_1, size_pilhas);
                    System.out.println("Numero jogadas :" + steps);
                            } else {
                                p3_1.push(p2_1.pop().getData(), ascending);
                                ++steps;
                                print_queues(p1_1, p2_1, p3_1, size_pilhas);
                    System.out.println("Numero jogadas :" + steps);

                            }

                        } else if (isP1Null && !isP2Null && !isP3Null) {
                            if (p2_1.getCount() >= p3_1.getCount()) {
                                p1_1.push(p2_1.pop().getData(), ascending);
                                ++steps;
                                print_queues(p1_1, p2_1, p3_1, size_pilhas);
                    System.out.println("Numero jogadas :" + steps);
                            } else {
                                p1_1.push(p3_1.pop().getData(), ascending);
                                ++steps;
                                print_queues(p1_1, p2_1, p3_1, size_pilhas);
                    System.out.println("Numero jogadas :" + steps);
                            }
                        } else if (!isP1Null && isP2Null && !isP3Null) {
                            if (p1_1.getCount() >= p3_1.getCount()) {
                                p2_1.push(p1_1.pop().getData(), ascending);
                                ++steps;
                                print_queues(p1_1, p2_1, p3_1, size_pilhas);
                    System.out.println("Numero jogadas :" + steps);
                            } else {
                                p2_1.push(p3_1.pop().getData(), ascending);
                                ++steps;
                                print_queues(p1_1, p2_1, p3_1, size_pilhas);
                    System.out.println("Numero jogadas :" + steps);
                            }

                        }
                        System.out.println("+++++++++++++++++++++++++++");

                    } else {


                    }

                    
                }
            }

            }
        }
    }
}