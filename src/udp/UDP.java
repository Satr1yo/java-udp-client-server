/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package udp;
import java.io.File;  
import java.io.IOException;
import java.net.DatagramPacket; 
import java.net.DatagramSocket; 
import java.net.InetAddress;
import java.io.FileInputStream;
import java.util.Scanner;

public class UDP {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);// obj scanner
        DatagramSocket ds = null;

        try {
            ds = new DatagramSocket(); //obj datagram atau udp
            InetAddress ip = InetAddress.getLocalHost(); //bisa pakai getByName ("ip")
            byte buf[] = null;  

            System.out.print("Masukkan nama file : ");
            String namaFile = scan.nextLine(); 
            File file = new File(namaFile); // obj file

            if (file.createNewFile()) {
                System.out.println("File dibuat: " + file.getName());
            } else {
                System.out.println("File sudah ada.");
            }

            System.out.print("Apakah Anda ingin mengirim file ke server? (y/n): ");
            char kon = scan.next().charAt(0);

            if (kon == 'y' || kon == 'Y') {
                System.out.println("OK");
                // convert file ke dalam array 
                FileInputStream fileInputStream = new FileInputStream(file); // membuat fileinputstream
                byte[] bytesArray = new byte[(int) file.length()];
                fileInputStream.read(bytesArray);
                fileInputStream.close();

                // menampung file yang akan dikirim ke dalam server
                DatagramPacket dp = new DatagramPacket(bytesArray, bytesArray.length, ip, 31);
                ds.send(dp);

                System.out.println("File berhasil dikirim ke server.");
            } else {
                System.out.println("Anda tidak mengirim tetapi file Anda telah dibuat.");
            }

        } catch (IOException e) {
            System.out.println("Terjadi kesalahan: " + e.getMessage());
        } finally {
            if (ds != null && !ds.isClosed()) {
                ds.close(); // Menutup DatagramSocket
            }
            scan.close(); // Menutup Scanner
        }
    }
}

