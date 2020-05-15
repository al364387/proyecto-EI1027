package es.uji.ei1027.majorsacasa.model;

public class UserDetails {
    String username;
    String password;

    //Para activar o desactivar la cuenta
    boolean enabled;

    public UserDetails() {
        enabled = false;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isEnabled() {
        return enabled;
    }
}