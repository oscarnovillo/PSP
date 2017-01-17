/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dam.encriptacion;

import static dam.encriptacion.PasswordHash.descifra;
import static dam.encriptacion.PasswordHash.fromHex;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.apache.commons.codec.binary.Base64.encodeBase64;
import static org.apache.commons.codec.binary.Base64.decodeBase64;

/**
 *
 * @author oscar
 */
public class Main {

    public static void main(String[] args) {
        try {
            String texto = "pepe no tiene quien le escriba, y quiero mnas mensaje";
            byte[] cifrado = PasswordHash.cifra("juan",texto);
            String hex = PasswordHash.toHex(cifrado);
            System.out.println(
                    new String(cifrado));

            String base64encode = new String(encodeBase64(cifrado));
            System.out.println(base64encode);

            byte[] base64decode = decodeBase64(base64encode);

            System.out.println(new String(base64decode));
            System.out.println(descifra("juan",base64decode));
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
