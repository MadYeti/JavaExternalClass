package org.mycompany.models.dao.clientDAO;


public interface DAOHelper {

    String createToken(String email);
    void resetPasswordAndDeleteToken(String password, String token);

}
