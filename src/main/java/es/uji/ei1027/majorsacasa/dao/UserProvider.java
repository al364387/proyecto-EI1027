package es.uji.ei1027.majorsacasa.dao;

import es.uji.ei1027.majorsacasa.model.UserDetails;
import org.jasypt.exceptions.EncryptionOperationNotPossibleException;
import org.springframework.stereotype.Repository;

@Repository
public class UserProvider implements UserDao {

    @Override
    public UserDetails loadUserByUsername(UserDetails user, String password, String role)
            throws EncryptionOperationNotPossibleException {

        // Contraseña
//        BasicPasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor();
//        if (passwordEncryptor.checkPassword(password, user.getPassword())) {
        if (password.equals(user.getPassword())) {
            // Se debería borrar de manera segura el campo contraseña antes de devolverlo
            return user;
        } else {
            return null; // bad login!
        }
    }
}



