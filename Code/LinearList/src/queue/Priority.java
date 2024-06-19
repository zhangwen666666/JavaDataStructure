package queue;

public interface Priority {
    /**
     * 数字越大，优先级越高
     * @return 优先级
     */
    int priority();
}

class Entry implements Priority{
    private String name;
    int priority;

    public Entry() {
    }

    public Entry(String name, int priority) {
        this.name = name;
        this.priority = priority;
    }

    @Override
    public int priority() {
        return priority;
    }

    @Override
    public String toString(){
        return "(" + name + " priority = " + priority + ")";
    }


}
