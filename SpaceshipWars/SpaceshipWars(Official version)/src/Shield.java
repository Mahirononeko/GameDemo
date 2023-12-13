public class Shield {
    private String shieldName;
    private int provideDefense;
    public Shield(){}
    public Shield(String shieldName, int provideDefense){
        this.shieldName = shieldName;
        this.provideDefense = provideDefense;
    }

    public String getShieldName() {
        return shieldName;
    }

    public void setShieldName(String shieldName) {
        this.shieldName = shieldName;
    }

    public int getProvideDefense() {
        return provideDefense;
    }

    public void setProvideDefense(int provideDefense) {
        this.provideDefense = provideDefense;
    }

    @Override
    public String toString() {
        return getShieldName()
                + "-" +getProvideDefense() + "defense power";
    }
}
