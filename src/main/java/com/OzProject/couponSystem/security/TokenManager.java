package com.OzProject.couponSystem.security;


import com.OzProject.couponSystem.exception.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class TokenManager {
    private Map<UUID, Information> tokens = new HashMap<>();

    public UUID addToken(Information information) {
        //delete previous tokens of current user id;
        removeEntriesByUserId(information.getId());

        // generate new token
        UUID token = UUID.randomUUID();

        // put the token within the Information Object
        tokens.put(token, information);

        return token;

    }

    public void removeEntriesByUserId(int id) {
        tokens.entrySet().removeIf(obj -> obj.getValue().getId() == id);
    }

    public int getUserId(UUID token) throws CouponSecurityException {

        Information information = tokens.get(token);
        if (information == null) {
            throw new CouponSecurityException(SecurityMessage.INVALID_TOKEN);
        }
        return tokens.get(token).getId();
    }

    public ClientType getClientType(UUID token) throws CouponSecurityException {

        Information information = tokens.get(token);
        if (information == null) {
            throw new CouponSecurityException(SecurityMessage.INVALID_TOKEN);
        }
        return tokens.get(token).getClientType();
    }

    public boolean isAdminTokenValid(UUID token) throws CouponSecurityException {
        Information information = tokens.get(token);
        if (information == null) {
            throw new CouponSecurityException(SecurityMessage.INVALID_TOKEN);
        }
        if (!information.getClientType().equals(ClientType.ADMINISTRATOR)) {
            throw new CouponSecurityException(SecurityMessage.INVALID_TOKEN);
        }

        return true;
    }


    public void deleteByToken(UUID token) {

        tokens.remove(token);
    }

    public Information getInformation(UUID token) {

        return tokens.get(token);
    }
}
