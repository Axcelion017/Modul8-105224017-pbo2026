package com.rpg.main;

import java.util.Scanner;
import com.rpg.entitas.*;

public class GameEngine {
    public static void main(String[] args) throws Exception{
        Scanner sc = new Scanner(System.in);
        System.out.println("Masukkan nama pahlawan:");
        String namaPahlawan = sc.nextLine();
        int jumlahMonster = 3;

        Monster[] monsters = new Monster[3];

        monsters[0] = new Monster("Goblin", 50, 10, "Goblin");
        monsters[1] = new Monster("Orc", 80, 15, "Orc");
        monsters[2] = new Monster("Troll", 120, 20, "Troll");

        Pahlawan pahlawan = new Pahlawan(namaPahlawan, 100, 20, 50, 1);

        for(Monster monster: monsters){
            System.out.println("\nPertarungan dengan " + monster.getNama());
            while(pahlawan.getHp() > 0 && monster.getHp() > 0){
                System.out.println("\nStatus Pahlawan: ");
                pahlawan.tampilkanStatus();
                System.out.println("\nStatus Monster: ");
                monster.tampilkanStatus();
                System.out.println("\nPilih aksi: \n1. Serang \n2. Bertahan \n3. Gunakan Item \nMasukkan pilihan aksi:");
                int aksi = sc.nextInt();
                sc.nextLine(); 
                switch(aksi){
                    case 1:
                        System.out.println("Masukan jenis serangan: \n 1. Basic Attack \n 2. Power Strike (Mana Cost: 20) \n 3. Burst Attack (Mana Cost: 40)\n Pilih jenis serangan:");
                        int jenisSerangan = sc.nextInt();
                        sc.nextLine();
                        switch(jenisSerangan){
                            case 1:
                                monster.terimaDamage(pahlawan.serang());
                                break;
                            case 2:
                                monster.terimaDamage(pahlawan.serang("Power Strike", 20));
                                break;
                            case 3:
                                monster.terimaDamage(pahlawan.serang("Burst Attack", 40));
                                break;
                            default:
                                System.out.println("Jenis serangan tidak valid.");
                        }
                        break;
                    case 2:
                        pahlawan.bertahan();
                        break;
                    case 3:
                        pahlawan.gunakanItem();
                        break;
                    default:
                        System.out.println("Aksi tidak valid.");
                }
                if(monster.getHp() > 0){
                    pahlawan.terimaDamage(monster.serang());
                }
            }
            if (pahlawan.getHp() <= 0) {
                break;
            } else if (monster.getHp() <= 0) {
                System.out.println("Musuh berhasil dikalahkan!");
                pahlawan.setLevel(pahlawan.getLevel() + 1);
                jumlahMonster--;
            }
        }
        if (pahlawan.getHp() > 0 && jumlahMonster == 0) {
            System.out.println("Selamat! " + pahlawan.getNama() + " telah menyelesaikan Dungeon dan mengalahkan semua monster!");
        }else{
            System.out.println("\nGame Over! " + pahlawan.getNama() + " telah gugur dalam pertarungan!");
        }
        sc.close();
    }
}
