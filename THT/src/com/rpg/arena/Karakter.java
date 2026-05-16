package com.rpg.arena;

public abstract class Karakter implements AksiBertarung {
    protected String nama;
    protected int hp, baseDamage;
    protected boolean isDefending;

    public String getNama(){
        return this.nama;
    }

    public int getHp(){
        return this.hp;
    }

    public int getBaseDamage(){
        return this.baseDamage;
    }
    public boolean getIsDefending(){
        return this.isDefending;
    }

    public void setNama(String nama){
        this.nama = nama;
    }

    public void setHp(int hp){
        this.hp = hp;
    }

    public void setBaseDamage(int baseDamage){
        this.baseDamage = baseDamage;
    }

    public void setIsDefending(boolean isDefending){
        this.isDefending = isDefending;
    }

    public Karakter(String nama, int hp, int baseDamage){
        this.nama = nama;
        this.hp = hp;
        this.baseDamage = baseDamage;
        this.isDefending = false;
    }

    public void terimaDamage(int damage){
        if(this.isDefending){
            damage /= 2;
            this.isDefending = false;
        }
        this.hp -= damage;
        if(this.hp < 0){
            this.hp = 0;
        }
    }

    public abstract void tampilkanStatus();
    
}
