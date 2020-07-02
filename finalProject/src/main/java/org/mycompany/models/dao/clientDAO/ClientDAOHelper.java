package org.mycompany.models.dao.clientDAO;

/**
 * Utility class that contains methods for password recovery issue
 */
public interface ClientDAOHelper {

    String createToken(String email);
    void resetPasswordAndDeleteToken(String password, String token);

}
