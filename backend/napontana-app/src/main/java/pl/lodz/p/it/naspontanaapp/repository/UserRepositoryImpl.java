package pl.lodz.p.it.naspontanaapp.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by piotr on 30.11.16.
 */
@Component
public class UserRepositoryImpl {

    @Autowired
    private UserRepository userRepository;

}
