/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primerejemplohilos;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author oscar
 */
public class Hilos {

    public static void main(String[] args) {

        int numHilos = 5;
        StringBuffer sb = new StringBuffer();
        StringBuffer out = new StringBuffer();
        Turno t = new Turno(numHilos);
        t.setTurno(0);

        ExecutorService executor = Executors.newFixedThreadPool(numHilos);

        for (int i = 0; i < numHilos; i++) {
            MiHilo mihilo = new MiHilo(i, sb, out, i + "", t);
            executor.execute(mihilo);
        }
//        Sin el pool con esto vale.
//        Thread miHilo1 = new Thread(new MiHilo(1,sb,out,"1",t));
//        Thread miHilo2 = new Thread(new MiHilo(2,sb,out,"2",t));
//        Thread miHilo3 = new Thread(new MiHilo(3,sb,out,"3",t));
//
//        
//       
//        miHilo1.start();
//        miHilo2.start();
//         miHilo3.start();

        executor.shutdown();
        System.out.println("hola mundo main");

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException ex) {
            Logger.getLogger(Hilos.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("buffer" + sb.toString());
        System.out.println("out" + out.toString());
        System.out.println("Turno" + t.getTurno());

    }

}
