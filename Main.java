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

    public static int getBigger(Pilha p1) {
        int max = 0;
        if (p1.getCount() != 0) {
            Node actual_p1 = p1.getFirst().getNext();
            max = p1.getFirst().getData();

            while (actual_p1 != null) {
                if (actual_p1.getData() > max) {
                    max = actual_p1.getData();
                }
                actual_p1 = actual_p1.getNext();
            }
            return max;

        }
        return -1;

    }

    public static int getSmaller(Pilha p1) {
        int min = 0;
        if (p1.getCount() != 0) {
            Node actual_p1 = p1.getFirst().getNext();
            min = p1.getFirst().getData();

            while (actual_p1 != null) {
                if (actual_p1.getData() < min) {
                    min = actual_p1.getData();
                }
                actual_p1 = actual_p1.getNext();
            }
            return min;

        }
        return 101;

    }

    public static Pilha copy_pilha(Pilha p1, int size_pilhas, Boolean ascending) {
        Node actual = p1.getFirst();
        Pilha copy = new Pilha(size_pilhas, "copy");
        while (actual != null) {
            copy.push(actual.getData(), ascending);
            actual = actual.getNext();
        }

        return copy;
    }

    public static Node getBefore(Pilha p, Node see_before) {
        Node before = p.getFirst();
        while (before.getNext() != see_before) {
            before = before.getNext();
        }
        return before;
    }

    public static int resolverAuto(Pilha p1, Pilha p2, Pilha p3, boolean ascending, int size_pilhas) {
        int steps = 0;
        Pilha dest = new Pilha(size_pilhas, "dest");
        if (ascending) {
            int max_p1 = getBigger(p1);
            int max_p2 = getBigger(p2);
            int max_p3 = getBigger(p3);

            int maxes[] = { max_p1, max_p2, max_p3 };
            int max_everything = maxes[0];
            for (int i = 1; i < 3; i++) {
                if (max_everything < maxes[i]) {
                    max_everything = maxes[i];
                }

            }
            boolean need_put_max_first = p1.getFirst().getData() != max_everything;
            if (!need_put_max_first) {
                dest = p1;
            }

            while (p1.getTop().getData() != max_everything) {

                if (p2.getCount() == 0 || p1.getTop().getData() <= p2.getTop().getData()) {

                    p2.push(p1.pop().getData(), ascending);
                    print_queues(p1, p2, p3, size_pilhas);
                    steps++;
                } else if (p3.getCount() == 0 || (p1.getTop().getData() <= p3.getTop().getData())) {

                    p3.push(p1.pop().getData(), ascending);
                    print_queues(p1, p2, p3, size_pilhas);
                    steps++;
                } else if (p2.getTop() != p2.getFirst() && p2.getTop().getData() <= p3.getTop().getData()
                        && getBefore(p2, p2.getTop()).getData() <= p3.getTop().getData()) {

                    p1.push(p2.pop().getData(), ascending);
                    steps++;
                    print_queues(p1, p2, p3, size_pilhas);
                    p3.push(p2.pop().getData(), ascending);
                    steps++;
                    print_queues(p1, p2, p3, size_pilhas);
                    p3.push(p1.pop().getData(), ascending);
                    steps++;
                    print_queues(p1, p2, p3, size_pilhas);
                } else if (p2.getTop() != p2.getFirst() && p2.getTop().getData() <= p3.getTop().getData()
                        && getBefore(p2, p2.getTop()).getData() >= p3.getTop().getData()) {

                    p1.push(p2.pop().getData(), ascending);
                    steps++;
                    print_queues(p1, p2, p3, size_pilhas);
                    p2.push(p3.pop().getData(), ascending);
                    steps++;
                    print_queues(p1, p2, p3, size_pilhas);
                    p2.push(p1.pop().getData(), ascending);
                    steps++;
                    print_queues(p1, p2, p3, size_pilhas);
                } else if (p2.getTop().getData() <= p3.getTop().getData()) {

                    p3.push(p2.pop().getData(), ascending);
                    steps++;
                    print_queues(p1, p2, p3, size_pilhas);
                } else if (p3.getTop().getData() <= p2.getTop().getData()) {

                    p2.push(p3.pop().getData(), ascending);
                    steps++;
                    print_queues(p1, p2, p3, size_pilhas);
                }
            }

            while (dest.getFirst() == null && dest.getCount() != 1) { //

                if (p2.getCount() == 0) {
                    p2.push(p1.pop().getData(), ascending);
                    steps++;
                    print_queues(p1, p2, p3, size_pilhas);
                    dest = p2;
                } else if (p3.getCount() == 0) {
                    p3.push(p1.pop().getData(), ascending);
                    steps++;
                    print_queues(p1, p2, p3, size_pilhas);
                    dest = p3;
                } else if (p2.getTop() != p2.getFirst() && p2.getTop().getData() <= p3.getTop().getData()
                        && getBefore(p2, p2.getTop()).getData() <= p3.getTop().getData()) {
                    p1.push(p2.pop().getData(), ascending);
                    steps++;
                    print_queues(p1, p2, p3, size_pilhas);
                    p3.push(p2.pop().getData(), ascending);
                    steps++;
                    print_queues(p1, p2, p3, size_pilhas);
                    p3.push(p1.pop().getData(), ascending);
                    steps++;
                    print_queues(p1, p2, p3, size_pilhas);
                } else if (p2.getTop() != p2.getFirst() && p2.getTop().getData() <= p3.getTop().getData()
                        && getBefore(p2, p2.getTop()).getData() > p3.getTop().getData()) {

                    p1.push(p2.pop().getData(), ascending);
                    steps++;
                    print_queues(p1, p2, p3, size_pilhas);
                    p2.push(p3.pop().getData(), ascending);
                    steps++;
                    print_queues(p1, p2, p3, size_pilhas);
                    p2.push(p1.pop().getData(), ascending);
                    steps++;
                    print_queues(p1, p2, p3, size_pilhas);
                }

                else if (p2.getTop().getData() <= p3.getTop().getData()) {
                    p3.push(p2.pop().getData(), ascending);
                    steps++;
                    print_queues(p1, p2, p3, size_pilhas);

                } else if (p3.getTop().getData() <= p2.getTop().getData()) {
                    p2.push(p3.pop().getData(), ascending);
                    steps++;
                    print_queues(p1, p2, p3, size_pilhas);

                }

            }
            int max_provisory = 0;
            Pilha provisory = new Pilha(size_pilhas, "prov");
            Pilha auxiliary = new Pilha(size_pilhas, "aux");
            while (!dest.is_ordered(ascending) || dest.getCount() != size_pilhas) {
                if (dest == p1) {
                    max_p2 = getBigger(p2);
                    max_p3 = getBigger(p3);
                    if (max_p2 > max_p3) {
                        max_provisory = max_p2;
                        provisory = p2;
                        auxiliary = p3;
                    } else {
                        max_provisory = max_p3;
                        provisory = p3;
                        auxiliary = p2;
                    }
                }
                if (dest == p2) {
                    max_p1 = getBigger(p1);
                    max_p3 = getBigger(p3);
                    if (max_p1 > max_p3) {
                        max_provisory = max_p1;
                        provisory = p1;
                        auxiliary = p3;
                    } else {
                        max_provisory = max_p3;
                        provisory = p3;
                        auxiliary = p1;
                    }
                }
                if (dest == p3) {
                    max_p1 = getBigger(p1);
                    max_p2 = getBigger(p2);
                    if (max_p1 > max_p2) {
                        max_provisory = max_p1;
                        provisory = p1;
                        auxiliary = p2;
                    } else {
                        max_provisory = max_p2;
                        provisory = p2;
                        auxiliary = p1;
                    }

                }
                while (provisory.getCount() != 0 && provisory.getTop().getData() != max_provisory) {

                    if (auxiliary.getCount() == 0) {

                        auxiliary.push(provisory.pop().getData(), ascending);
                        print_queues(p1, p2, p3, size_pilhas);
                        steps++;
                    } else if (provisory.getTop().getData() <= auxiliary.getTop().getData()) {

                        auxiliary.push(provisory.pop().getData(), ascending);
                        print_queues(p1, p2, p3, size_pilhas);
                        steps++;
                    }

                    else if (auxiliary.getTop() != auxiliary.getFirst()
                            && provisory.getTop().getData() >= auxiliary.getTop().getData()
                            && getBefore(auxiliary, auxiliary.getTop()).getData() <= provisory.getTop().getData()) {

                        dest.push(auxiliary.pop().getData(), ascending);
                        print_queues(p1, p2, p3, size_pilhas);
                        steps++;
                        auxiliary.push(provisory.pop().getData(), ascending);
                        print_queues(p1, p2, p3, size_pilhas);
                        steps++;
                        auxiliary.push(dest.pop().getData(), ascending);
                        print_queues(p1, p2, p3, size_pilhas);
                        steps++;
                    } else if (auxiliary.getTop() != auxiliary.getFirst()
                            && provisory.getTop().getData() >= auxiliary.getTop().getData()
                            && provisory.getTop().getData() >= getBefore(auxiliary, auxiliary.getTop()).getData()) {

                        dest.push(auxiliary.pop().getData(), ascending);
                        steps++;
                        print_queues(p1, p2, p3, size_pilhas);
                        auxiliary.push(provisory.pop().getData(), ascending);
                        steps++;
                        print_queues(p1, p2, p3, size_pilhas);
                        auxiliary.push(dest.pop().getData(), ascending);
                        print_queues(p1, p2, p3, size_pilhas);
                        steps++;
                    } else if (provisory.getTop().getData() >= auxiliary.getTop().getData()) {

                        dest.push(auxiliary.pop().getData(), ascending);
                        print_queues(p1, p2, p3, size_pilhas);
                        steps++;
                        auxiliary.push(provisory.pop().getData(), ascending);
                        print_queues(p1, p2, p3, size_pilhas);
                        steps++;
                        auxiliary.push(dest.pop().getData(), ascending);
                        print_queues(p1, p2, p3, size_pilhas);
                        steps++;

                    }
                }
                if (provisory.getCount() != 0) {
                    dest.push(provisory.pop().getData(), ascending);
                    print_queues(p1, p2, p3, size_pilhas);
                    steps++;
                }

            }

        }

        else {
            int min_p1 = getSmaller(p1);

            int min_p2 = getSmaller(p2);

            int min_p3 = getSmaller(p3);

            int mins[] = { min_p1, min_p2, min_p3 };
            int min_everything = mins[0];
            for (int i = 1; i < 3; i++) {
                if (min_everything > mins[i]) {
                    min_everything = mins[i];
                }

            }
            boolean need_put_min_first = p1.getFirst().getData() != min_everything;
            if (!need_put_min_first) {
                dest = p1;
            }

            while (p1.getTop().getData() != min_everything) {

                if (p2.getCount() == 0 || p1.getTop().getData() >= p2.getTop().getData()) {

                    p2.push(p1.pop().getData(), ascending);
                    print_queues(p1, p2, p3, size_pilhas);
                    steps++;
                } else if (p3.getCount() == 0 || (p1.getTop().getData() >= p3.getTop().getData())) {

                    p3.push(p1.pop().getData(), ascending);
                    print_queues(p1, p2, p3, size_pilhas);
                    steps++;
                } else if (p2.getTop() != p2.getFirst() && p2.getTop().getData() >= p3.getTop().getData()
                        && getBefore(p2, p2.getTop()).getData() >= p3.getTop().getData()) {
                    p1.push(p2.pop().getData(), ascending);
                    steps++;
                    print_queues(p1, p2, p3, size_pilhas);
                    p3.push(p2.pop().getData(), ascending);
                    steps++;
                    print_queues(p1, p2, p3, size_pilhas);
                    p3.push(p1.pop().getData(), ascending);
                    steps++;
                    print_queues(p1, p2, p3, size_pilhas);
                } else if (p2.getTop() != p2.getFirst() && p2.getTop().getData() >= p3.getTop().getData()
                        && getBefore(p2, p2.getTop()).getData() <= p3.getTop().getData()) {

                    p1.push(p2.pop().getData(), ascending);
                    steps++;
                    print_queues(p1, p2, p3, size_pilhas);
                    p2.push(p3.pop().getData(), ascending);
                    steps++;
                    print_queues(p1, p2, p3, size_pilhas);
                    p2.push(p1.pop().getData(), ascending);
                    steps++;
                    print_queues(p1, p2, p3, size_pilhas);
                } else if (p2.getTop().getData() >= p3.getTop().getData()) {
                    p3.push(p2.pop().getData(), ascending);
                    steps++;
                    print_queues(p1, p2, p3, size_pilhas);

                } else if (p3.getTop().getData() >= p2.getTop().getData()) {
                    p2.push(p3.pop().getData(), ascending);
                    steps++;
                    print_queues(p1, p2, p3, size_pilhas);
                }
            }

            while (dest.getFirst() == null && dest.getCount() != 1) { //

                if (p2.getCount() == 0) {
                    p2.push(p1.pop().getData(), ascending);
                    steps++;
                    print_queues(p1, p2, p3, size_pilhas);
                    dest = p2;

                } else if (p3.getCount() == 0) {
                    p3.push(p1.pop().getData(), ascending);
                    steps++;
                    print_queues(p1, p2, p3, size_pilhas);
                    dest = p3;
                } else if (p2.getTop() != p2.getFirst() && p2.getTop().getData() >= p3.getTop().getData()
                        && getBefore(p2, p2.getTop()).getData() >= p3.getTop().getData()) {
                    p1.push(p2.pop().getData(), ascending);
                    steps++;
                    print_queues(p1, p2, p3, size_pilhas);
                    p3.push(p2.pop().getData(), ascending);
                    steps++;
                    print_queues(p1, p2, p3, size_pilhas);
                    p3.push(p1.pop().getData(), ascending);
                    steps++;
                    print_queues(p1, p2, p3, size_pilhas);
                } else if (p2.getTop() != p2.getFirst() && p2.getTop().getData() >= p3.getTop().getData()
                        && getBefore(p2, p2.getTop()).getData() <= p3.getTop().getData()) {

                    p1.push(p2.pop().getData(), ascending);
                    steps++;
                    print_queues(p1, p2, p3, size_pilhas);
                    p2.push(p3.pop().getData(), ascending);
                    steps++;
                    print_queues(p1, p2, p3, size_pilhas);
                    p2.push(p1.pop().getData(), ascending);
                    steps++;
                    print_queues(p1, p2, p3, size_pilhas);
                }

                else if (p2.getTop().getData() >= p3.getTop().getData()) {
                    p3.push(p2.pop().getData(), ascending);
                    steps++;
                    print_queues(p1, p2, p3, size_pilhas);

                } else if (p3.getTop().getData() >= p2.getTop().getData()) {
                    p2.push(p3.pop().getData(), ascending);
                    steps++;
                    print_queues(p1, p2, p3, size_pilhas);

                }

            }
            int min_provisory = 0;
            Pilha provisory = new Pilha(size_pilhas, "prov");
            Pilha auxiliary = new Pilha(size_pilhas, "aux");
            while (!dest.is_ordered(ascending) || dest.getCount() != size_pilhas) {

                if (dest == p1) {
                    min_p2 = getSmaller(p2);
                    min_p3 = getSmaller(p3);
                    if (min_p2 < min_p3) {
                        min_provisory = min_p2;
                        provisory = p2;
                        auxiliary = p3;
                    } else {
                        min_provisory = min_p3;
                        provisory = p3;
                        auxiliary = p2;
                    }
                }
                if (dest == p2) {
                    min_p1 = getSmaller(p1);
                    min_p3 = getSmaller(p3);
                    if (min_p1 < min_p3) {
                        min_provisory = min_p1;
                        provisory = p1;
                        auxiliary = p3;
                    } else {
                        min_provisory = min_p3;
                        provisory = p3;
                        auxiliary = p1;
                    }
                }
                if (dest == p3) {
                    min_p1 = getSmaller(p1);
                    min_p2 = getSmaller(p2);
                    if (min_p1 < min_p2) {
                        min_provisory = min_p1;
                        provisory = p1;
                        auxiliary = p2;
                    } else {
                        min_provisory = min_p2;
                        provisory = p2;
                        auxiliary = p1;
                    }

                }
                while (provisory.getCount() != 0 && provisory.getTop().getData() != min_provisory) {

                    if (auxiliary.getCount() == 0) {

                        auxiliary.push(provisory.pop().getData(), ascending);
                        print_queues(p1, p2, p3, size_pilhas);
                        steps++;
                    }

                    else if (provisory.getTop().getData() >= auxiliary.getTop().getData()) {

                        auxiliary.push(provisory.pop().getData(), ascending);
                        print_queues(p1, p2, p3, size_pilhas);
                        steps++;
                    }

                    else if (auxiliary.getTop() != auxiliary.getFirst()
                            && provisory.getTop().getData() <= auxiliary.getTop().getData()
                            && getBefore(auxiliary, auxiliary.getTop()).getData() >= provisory.getTop().getData()) {

                        dest.push(auxiliary.pop().getData(), ascending);
                        print_queues(p1, p2, p3, size_pilhas);
                        steps++;
                        auxiliary.push(provisory.pop().getData(), ascending);
                        print_queues(p1, p2, p3, size_pilhas);
                        steps++;
                        auxiliary.push(dest.pop().getData(), ascending);
                        print_queues(p1, p2, p3, size_pilhas);
                        steps++;
                    } else if (auxiliary.getTop() != auxiliary.getFirst()
                            && provisory.getTop().getData() <= auxiliary.getTop().getData()
                            && provisory.getTop().getData() <= getBefore(auxiliary, auxiliary.getTop()).getData()) {

                        dest.push(auxiliary.pop().getData(), ascending);
                        steps++;
                        print_queues(p1, p2, p3, size_pilhas);
                        auxiliary.push(provisory.pop().getData(), ascending);
                        steps++;
                        print_queues(p1, p2, p3, size_pilhas);
                        auxiliary.push(dest.pop().getData(), ascending);
                        print_queues(p1, p2, p3, size_pilhas);
                        steps++;
                    } else if (provisory.getTop().getData() <= auxiliary.getTop().getData()) {

                        dest.push(auxiliary.pop().getData(), ascending);
                        print_queues(p1, p2, p3, size_pilhas);
                        steps++;
                        auxiliary.push(provisory.pop().getData(), ascending);
                        print_queues(p1, p2, p3, size_pilhas);
                        steps++;
                        auxiliary.push(dest.pop().getData(), ascending);
                        print_queues(p1, p2, p3, size_pilhas);
                        steps++;

                    }
                }
                if (provisory.getCount() != 0) {
                    dest.push(provisory.pop().getData(), ascending);
                    print_queues(p1, p2, p3, size_pilhas);
                    steps++;
                }

            }

        }

        return steps;
    }

    public static void main(String[] args) {
        Boolean ascending = true;
        int size_pilhas = 0;
        int decision = -1;
        int played_rounds = 0;

        Scanner in = new Scanner(System.in);
        System.out.print("Bem vindo ao jogo, escolhe o tamanho das pilhas : ");
        size_pilhas = in.nextInt();
        Pilha p1 = new Pilha(size_pilhas, "p1");
        Pilha p2 = new Pilha(size_pilhas, "p2");
        Pilha p3 = new Pilha(size_pilhas, "p3");
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++");
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
                Pilha o = new Pilha(size_pilhas, "o");
                Pilha d = new Pilha(size_pilhas, "d");
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
                played_rounds = resolverAuto(p1, p2, p3, ascending, size_pilhas);
                System.out.println("Solved in  " + played_rounds + " steps .");
                break;

            }
        }

        in.close();
    }
}