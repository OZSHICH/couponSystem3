package com.OzProject.couponSystem.servies;


import com.OzProject.couponSystem.exception.*;
import com.OzProject.couponSystem.security.*;

import java.util.*;

public interface WelcomeService {

    UUID login(String email, String password, ClientType clientType) throws CouponSecurityException;

    void logOut(UUID uuid);

}
