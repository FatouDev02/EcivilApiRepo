package com.example.EcivilApi.Security.services;

import com.example.EcivilApi.models.Utilisateurs;
import com.example.EcivilApi.repository.UtilisateurRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UtilisateurRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userRepository.findByUsername(username)
//                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
        if (username.trim().isEmpty()) {
            throw new UsernameNotFoundException("username vide");
        }
        //recupere le collaborateurs par son username
        Utilisateurs user=userRepository.findByUsername(username);
        if(user == null){
            //si le coll n'existe pas retouner cette erreur
            log.error("Collaborateur non trouvé");

            throw new UsernameNotFoundException("Collaborateur non trouvé");
        } else{
            //sinon sil existe retouner ce messsage
            log.info("Collaborateur  trouvé",username);

        }
        return UserDetailsImpl.build(user);
    }
}
