package com.rpg.entitas;
import com.rpg.arena.Karakter;

public class Pahlawan extends Karakter{
    private int mana, level;

    public Pahlawan(String nama, int hp, int baseDamage, int mana, int level){
        super(nama, hp, baseDamage);
        this.mana = mana;
        this.level = level;
    }

    public int getMana(){
        return this.mana;
    }
    public int getLevel(){
        return this.level;
    }

    public void setMana(int mana){
        this.mana = mana;
    }
    public void setLevel(int level){
        this.level = level;
    }

    @Override

    public int serang(){
        return this.baseDamage * this.level;
    }

    @Override
    public void bertahan(){
        this.isDefending = true;
        System.out.println("Pahlawan bersiaga.");
    }

    @Override
    public void tampilkanStatus(){
        System.out.println("HP: " + this.hp);
        System.out.println("Mana: " + this.mana);
        System.out.println("Level: " + this.level);
    }

    @Override
    public void gunakanItem(){
        this.hp += 30;
    }

    public int serang(String namaSkill, int manaCost){
        if(this.mana >= manaCost){
            this.mana -= manaCost;
            return this.baseDamage * this.level * 2;
        } else {
            System.out.println("Mana tidak cukup untuk menggunakan " + namaSkill);
            return 0;
        }
    }
}
