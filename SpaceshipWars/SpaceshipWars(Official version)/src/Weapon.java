public class Weapon {
    private String weaponName;
    private int weaponAttackPower;

    public Weapon(){
    }
    public Weapon(String weaponName, int weaponAttackPower){
        this.weaponName = weaponName;
        this.weaponAttackPower = weaponAttackPower;
    }

    public String getWeaponName() {
        return weaponName;
    }

    public void setWeaponName(String weaponName) {
        this.weaponName = weaponName;
    }

    public int getWeaponAttackPower() {
        return weaponAttackPower;
    }

    public void setWeaponAttackPower(int weaponAttackPower) {
        this.weaponAttackPower = weaponAttackPower;
    }

    @Override
    public String toString() {
        return getWeaponName()
                + "-" + getWeaponAttackPower() + "attack power";
    }
}
