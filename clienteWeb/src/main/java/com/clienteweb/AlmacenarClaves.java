package com.clienteweb;

import java.io.*;

import java.security.*;
import java.security.spec.*;

import javax.crypto.*;
import javax.crypto.interfaces.*;
import javax.crypto.spec.*;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

// Necesario para usar el provider Bouncy Castle (BC)
//    Para compilar incluir el fichero JAR en el classpath

public class AlmacenarClaves {
	public static void main(String[] args) throws Exception {

                String nombre = "test1";
                
		System.out.println("Crea los ficheros "+nombre+".secreta, "
				+nombre+".publica, "+nombre+".privada");
		
		// Anadir provider  (el provider por defecto no soporta RSA)
		Security.addProvider(new BouncyCastleProvider()); // Cargar el provider BC

		/*** Crear claves RSA 512 bits  */
		KeyPairGenerator generadorRSA = KeyPairGenerator.getInstance("RSA", "BC"); // Hace uso del provider BC
		generadorRSA.initialize(1024);
		KeyPair clavesRSA = generadorRSA.generateKeyPair();
		PrivateKey clavePrivada = clavesRSA.getPrivate();
		PublicKey clavePublica = clavesRSA.getPublic();

		/*** Crear KeyFactory (depende del provider) usado para las transformaciones de claves*/
		KeyFactory keyFactoryRSA = KeyFactory.getInstance("RSA", "BC"); // Hace uso del provider BC

		/*** 1 Volcar clave privada  a fichero */
		// 1.1 Codificar clave privada en formato PKS8 (necesario para escribirla a disco)
		PKCS8EncodedKeySpec pkcs8Spec = new PKCS8EncodedKeySpec(clavePrivada.getEncoded());

		// 1.2 Escribirla a fichero binario
		FileOutputStream out = new FileOutputStream(nombre + ".privada");
		out.write(pkcs8Spec.getEncoded());
		out.close();

		/*** 2 Recuperar clave Privada del fichero */
		// 2.1 Leer datos binarios PKCS8
		byte[] bufferPriv = new byte[5000];
		FileInputStream in = new FileInputStream(nombre + ".privada");
		in.read(bufferPriv, 0, 5000);
		in.close();

		// 2.2 Recuperar clave privada desde datos codificados en formato PKCS8
		PKCS8EncodedKeySpec clavePrivadaSpec = new PKCS8EncodedKeySpec(bufferPriv);
                
		PrivateKey clavePrivada2 = keyFactoryRSA.generatePrivate(clavePrivadaSpec);

		if (clavePrivada.equals(clavePrivada2)) {
			System.out.println("OK: clave privada guardada y recuperada");
		}

		/*** 3 Volcar clave publica  a fichero */
		// 3.1 Codificar clave publica en formato X509 (necesario para escribirla a disco)
		X509EncodedKeySpec x509Spec = new X509EncodedKeySpec(clavePublica.getEncoded());

		// 3.2 Escribirla a fichero binario
		out = new FileOutputStream(nombre + ".publica");
		out.write(x509Spec.getEncoded());
		out.close();

		/*** 4 Recuperar clave PUBLICA del fichero */
		// 4.1 Leer datos binarios x809
		byte[] bufferPub = new byte[5000];
		in = new FileInputStream(nombre + ".publica");
		in.read(bufferPub, 0, 5000);
		in.close();

		// 4.2 Recuperar clave publica desde datos codificados en formato X509
		X509EncodedKeySpec clavePublicaSpec = new X509EncodedKeySpec(bufferPub);
		PublicKey clavePublica2 = keyFactoryRSA.generatePublic(clavePublicaSpec);

		if (clavePublica.equals(clavePublica2)) {
			System.out.println("OK: clave publica guardada y recuperada");
		}

		/*** Crear e inicializar clave  DES */
		KeyGenerator generadorDES = KeyGenerator.getInstance("DES");
		generadorDES.init(56);
		SecretKey claveSecreta = generadorDES.generateKey();

		/*** Crear SecretKeyFactory usado para las transformaciones de claves secretas*/
		SecretKeyFactory secretKeyFactoryDES = SecretKeyFactory.getInstance("DES");

		/*** 5 Volcar clave secreta  a fichero */
		// 5.1 Escribirla directamente a fichero binario (válido para DES y 3DES)
		out = new FileOutputStream(nombre + ".secreta");
		out.write(claveSecreta.getEncoded());
		out.close();

		/*** 6 Recuperar clave secreta del fichero */
		// 6.1 Leer datos binarios directamente (válido para DES y 3DES)
		byte[] bufferSecr = new byte[500];
		in = new FileInputStream(nombre + ".secreta");
		in.read(bufferSecr, 0, 500);
		in.close();

		// 6.2 Cargar clave directamente desd elos datos leidos
                
		DESKeySpec DESspec = new DESKeySpec(bufferSecr);
		SecretKey claveSecreta2 = secretKeyFactoryDES.generateSecret(DESspec);

		if (claveSecreta.equals(claveSecreta2)) {
			System.out.println("OK: clave secreta guardada y recuperada");
		}
	}

	public static void mensajeAyuda() {
		System.out.println("Ejemplo almacenamiento de claves");
		System.out.println("\tSintaxis:   java AlmacenarClaves prefijo");
		System.out.println();
	}
}
