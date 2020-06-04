package org.mycompany.models.dao.clientDAO;


public interface ClientDAOHelper {

    String createToken(String email);
    void resetPasswordAndDeleteToken(String password, String token);

}
