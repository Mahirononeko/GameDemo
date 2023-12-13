public class Reactor {
    private String reactorName;
    private int provideBlood;
    public Reactor(){}
    public Reactor(String reactorName, int provideBlood){
        this.reactorName = reactorName;
        this.provideBlood = provideBlood;
    }

    public String getReactorName() {
        return reactorName;
    }

    public void setReactorName(String reactorName) {
        this.reactorName = reactorName;
    }

    public int getProvideBlood() {
        return provideBlood;
    }

    public void setProvideBlood(int provideBlood) {
        this.provideBlood = provideBlood;
    }

    @Override
    public String toString() {
        return getReactorName()
                + "-" + getProvideBlood() + "blood";
    }
}
