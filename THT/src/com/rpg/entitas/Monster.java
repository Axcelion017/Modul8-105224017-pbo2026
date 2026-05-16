package com.rpg.entitas;
import com.rpg.arena.Karakter;

public class Monster extends Karakter{
    private String jenisMonster;

    public Monster(String nama, int hp, int baseDamage, String jenisMonster){
        super(nama, hp, baseDamage);
        this.jenisMonster = jenisMonster;
    }

    public String getJenis(){
        return this.jenisMonster;
    }

    public void setJenis(String jenisMonster){
        this.jenisMonster = jenisMonster;
    }

    @Override
    public int serang(){
        return this.baseDamage;
    }

    @Override
    public void bertahan(){
        this.hp += 10;
    }

    @Override
    public void tampilkanStatus(){
        System.out.println("HP: " + this.hp);
        System.out.println("Jenis: " + this.jenisMonster);
    }

    @Override
    public void gunakanItem(){
        this.hp += 20;
    }
    
}
