package es.uji.ei1027.majorsacasa.dao;
import es.uji.ei1027.majorsacasa.model.UserDetails;

public interface UserDao {
    UserDetails loadUserByUsername(UserDetails user, String password, String role);
}


