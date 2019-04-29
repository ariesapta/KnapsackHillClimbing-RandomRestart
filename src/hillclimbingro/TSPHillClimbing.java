/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hillclimbingro;

import java.util.Scanner;

/**
 *
 * @author USER
 */
public class TSPHillClimbing {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int CITIES; //Variabel jumlah kota
        System.out.print("Masukkan jumlah kota : ");
        CITIES = scan.nextInt();
        
        int iIndex = 1; //variabel index
        int[] ArrayOfCities = new int[CITIES]; //Variabel jarak kota

        //Input jarak antar kota
        for (int i = 0; i < CITIES; i++)
        {
                System.out.print("Masukkan jarak untuk kota ");
                System.out.print(iIndex);
                System.out.print("\n");
                
                ArrayOfCities[i] = scan.nextInt();
                iIndex++;
        }
        
        System.out.println();

        int bestCost = calcCost(ArrayOfCities, CITIES); //Variabel menampung jarak terbaik
        int iNewCost = 0; //Variabel menampung jarak yang baru
        int iSwaps = 0; //variabel jumlah swap
       
        
        //Pencarian BEST rute
        while (bestCost > 0)
        {
                for (int i = 0; i < CITIES - 1; i++)
                {
                    SwapElements(ArrayOfCities, i, i + 1); //Swap antar elemen kota
                    iNewCost = calcCost(ArrayOfCities, CITIES); //mencari jarak yang baru
                    
                    //mencari jarak terbaik yang baru
                    if (bestCost > iNewCost)
                    {
                            iSwaps++;
                            System.out.print("Penukaran elemen ke "+iSwaps+" : ");
                            for (int j = 0; j < CITIES; j++)
                            {
                                    System.out.print(ArrayOfCities[j]);
                                    System.out.print("->");
                            }

                            System.out.print("\n");
                            bestCost = iNewCost;
                    }
                    else
                    {
                            SwapElements(ArrayOfCities, i, i + 1);
                    }
                }
        }

        //Menampilkan jarak paling terbaik
        System.out.print("\nBEST : \n");
        for (int i = 0; i < CITIES; i++)
        {
                System.out.print(ArrayOfCities[i]);
                System.out.print("\n");
        }

    }
    
    //Method menghitung jarak terbaik
    public static int calcCost(int[] ArrayOfCities, int NUM_CITIES)
    {
            int c = 0;
            for (int i = 0; i < NUM_CITIES; i++)
            {
                    for (int j = i + 1; j < NUM_CITIES; j++)
                    {
                            if (ArrayOfCities[j] < ArrayOfCities[i])
                            {
                                    c++;
                            }
                    }
            }
            return c;
    }

    //Method untuk menukar posisi antar elemen kota
    public static void SwapElements(int[] ArrayOfCities, int i, int j)
    {
            int temp = ArrayOfCities[i];
            ArrayOfCities[i] = ArrayOfCities[j];
            ArrayOfCities[j] = temp;
    }
    
}
