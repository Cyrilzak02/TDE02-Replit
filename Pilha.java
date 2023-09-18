public class Pilha{
    private Node first ;
    private Node top;
    private int size;
    private  int count = 0;
    private String name;
    public Pilha (int size,String name){
        this.first = null;
        this.top = null;
        this.size = size;
        this.name = name;

    }
    public String getName() {
        return name;
    }

    public Node getFirst() {
        return first;
    }

    public Node getTop() {
        return top;
    }
    public int getCount(){
        return  count;
    }

    public Node pop(){
        Node removed = first;
        if(first == null){
            System.out.println("Cannot remove element because list is empty.");
        }
        else if(first.getNext() == null){

            first = null;
            top = null;
            count--;

            return removed;

        }
        else {
            removed = top;
            Node before_remove = first;
            while (before_remove.getNext() != top){
                before_remove = before_remove.getNext();
            }
            before_remove.setNext(null);
            top = before_remove;
            count -- ;
            return removed;
        }
        return  null;
    }

    public Boolean is_ordered(Boolean ascending){
        if(first == null || first.getNext() == null){
            return false;
        }
        Node actual = first;
        Node after_actual = actual.getNext();

        if(after_actual == top){

            if (ascending){
                if(actual.getData() < after_actual.getData() ){
                    return false;
                }
            }
            else {
                if(actual.getData() > after_actual.getData()){
                    return false;
                }
            }
        }
        else {
            while (actual !=top){
                while (after_actual!=null){
                    if(ascending && actual.getData() < after_actual.getData()){
                        return false;

                    }
                    if(!ascending && actual.getData() > after_actual.getData()){
                        return false;

                    }
                    after_actual = after_actual.getNext();
                }
                actual = actual.getNext();
                after_actual = actual.getNext();
            }



        }

        return true;

    }

    public boolean push(int add , Boolean ascending){
        Node node = new Node();
        node.setData(add);
        if(first == null){
            first = node ;
            top = first;
            count++;
        }
        else{
            if(count == size){
                System.out.println("The queue is full , you cannot add anymore things.");

                
                return false;
            }
            else {
                top.setNext(node);
                top = node;
                count++;
                return true;
            }

        }
        return false;
    }
    public void print() {
        Node actual = first;
        Node end = top;



        while (end != first) {
            actual = first;
            System.out.print("| " + end.getData() + " |");
            while (actual != null && actual.getNext() != end) {
                actual = actual.getNext();
            }
            end = actual;
        }
        if(first == null){
            System.out.print(" Vazia ");
            System.out.print(" ____ ");
        }
        else {
        System.out.print("| " + first.getData() + " |");
        System.out.print(" ____ ");
        }



    }
}