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
        if (password.equals(user.getPassword())) {
            return user;
        } else {
            return null; // bad login!
        }
    }
}



