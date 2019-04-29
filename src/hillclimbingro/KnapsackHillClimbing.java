/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hillclimbingro;


import static java.lang.Math.max;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.IntStream;

/**
 *
 * @author USER
 */
public class KnapsackHillClimbing {


    public static void main(String[] args) {
        int n; // variabel jumlah barang
        int W; // variabel berat maksimum knapsack
        int l; // variabel jumlah iterasi
        int best = 0; // variabel nilai manfaat BEST
        int sum = 0; // variabel total nilai manfaat barang yang dibawa
        Scanner scan = new Scanner(System.in);
        
        // Input Jumlah barang
        System.out.print("Jumlah barang yang tersedia : ");
        n = scan.nextInt();
        
        int[] val = new int[n]; //array menampung nilai manfaat per barang
        int[] wt = new int [n]; //array menampung berat per barang
        
        // Input Nilai Manfaat dan Berat Barang
        for (int i = 0; i < n; i++)
        {
            System.out.print("Masukkan nilai manfaat barang dan beratnya - " + (i+1) + "\n");
            System.out.print("Value  : ");
            val[i] = scan.nextInt();
            System.out.print("Berat : "); 
            wt[i] = scan.nextInt(); 
            System.out.println();
        }
        
        
        
        //Input maksimum kapasitas knapsack
        System.out.print("Input Maksimum Berat Knapsack : ");
        W = scan.nextInt();
        
        //Input jumlah iterasi
        System.out.print("Jumlah Iterasi : ");
        l =  scan.nextInt();    
        
        System.out.println();
        System.out.println("Solusi Awal : ");
        
        // Inisialisasi solusi secara random
        int[] selected = random(n, wt, W);
        boolean result = IntStream.of(selected).anyMatch(x -> x == 1); //Untuk men cek ada barang yang dibawa atau tidak
        if(result){ //ada barang yang dibawa
            for(int i = 0; i < n; i++){
                if(selected[i] == 1){
                    System.out.print((i+1)+ " ");
                }    
            }
        }else{ //tidak bawa barang sama sekali
            System.out.print("-");
        }
        System.out.println();
        
        
        //Menghitung total nilai manfaat solusi awal
        for(int z = 0; z < n; z++)
        {
            if(selected[z] == 1){
                sum += val[z];
            }
        }
        System.out.println("Total value = " +sum);
        
        // Menentukan BEST awal
        best = sum;
        System.out.println("BEST = " +best);
        System.out.println();

        //Pencarian solusi baru sesuai iterasi yang telah ditentukan
        for(int a = 1; a <= l; a++){
            System.out.println("Iterasi ke - "+(a));
            
            //Menampilkan solusi awal dari random restart
            System.out.print("Solusi Awal : ");
            
            result = IntStream.of(selected).anyMatch(x -> x == 1); //Untuk men cek ada barang yang dibawa atau tidak
            if(result){ //ada barang yang dibawa
                for(int i = 0; i < n; i++){
                    if(selected[i] == 1){
                        System.out.print((i+1)+ " ");
                    }    
                }
            }else{ //tidak bawa barang sama sekali
                System.out.print("-");
            }
            
            System.out.println();
            
            int[] newSelected = modifikasi(selected, n, wt, W); //Mencari solusi baru dengan modifikasi solusi sebelumnya
            
            //Menampilkan solusi baru
            System.out.print("Solusi Baru : ");
            
            result = IntStream.of(newSelected).anyMatch(x -> x == 1); //Untuk men cek ada barang yang dibawa atau tidak
            if(result){ //ada barang yang dibawa
                for(int i = 0; i < n; i++){
                    if(newSelected[i] == 1){
                        System.out.print((i+1)+ " ");
                    }    
                }
            }else{ //tidak bawa barang sama sekali
                System.out.print("-");
            }
            
            System.out.println();
            
            //Menghitung total nilai manfaat dari solusi baru
            int sumNew = 0;
            for(int z = 0; z < n; z++)
            {
                if(newSelected[z] == 1){
                    sumNew += val[z];
                }
            }
            System.out.println("Total value = " +sumNew);
            
            //Membandingkan solusi baru dan solusi lama untuk mendapatkan BEST baru
            if(sumNew > sum){
                best = sumNew;
                sum = sumNew;
            }
            System.out.println("BEST = " +best);
                        
            selected = random(n, wt, W); //Inisialisasi solusi secara random (random restart)
            
            System.out.println();
        }
    }

    
    //Method random angka
    public static int[] random(int n, int wt[], int W){
        Random rand = new Random(); //Fungsi random java
        int[] num = new int[n]; //menampung angka yang akan di random
        int sumW = 0; //variabel total berat
        
        //merandom angka antara 0 dan 1
        for(int i = 0; i < n; i++){
            num[i] = rand.nextInt(1 + 1);
        }
        
        //mencari total berat sesuai dengan angka random yang didapat
        for(int z = 0; z < n; z++)
        {
            if(num[z] == 1){
                sumW += wt[z];
            }
        }
        
        //apabila total berat yang didapat berlebih, maka random akan di generate kembali
        if(sumW > W){
            return random(n, wt, W);
        }
        else{
            return num;
        }
    }
    
    
    //Method Modifikasi solusi
    public static int[] modifikasi(int[] s, int n, int[] wt, int W){
        int[] modS = s; //menampung solusi lama
        int sumW = 0; //menampung total berat
        Random rand = new Random(); //Fungsi random java
        int modBarang = rand.nextInt(n); //menentukan barang yang akan diubah statusnya
        
        if(modS[modBarang] == 1){
            modS[modBarang] = 0; //mengubah barang yang dibawa menjadi tidak dibawa
        }else{
            modS[modBarang] = 1; //mengubah barang yang tidak dibawa menjadi dibawa
        }
        
        //mencari total berat sesuai dengan modifikasi solusi yang didapat
        for(int z = 0; z < n; z++)
        {
            if(modS[z] == 1){
                sumW += wt[z];
            }
        }
        
        //apabila total berat yang didapat berlebih, maka solusi akan dimodifikasi ulang
        if(sumW > W){
            return modifikasi(s, n, wt, W);
        }
        else{
            return modS;
        }
    }
    
}
