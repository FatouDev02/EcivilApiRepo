package com.example.EcivilApi.controller;


import com.example.EcivilApi.Security.jwt.JwtUtils;
import com.example.EcivilApi.Security.services.RefreshTokenService;
import com.example.EcivilApi.Security.services.UserDetailsImpl;
import com.example.EcivilApi.exception.TokenRefreshException;
import com.example.EcivilApi.models.*;
import com.example.EcivilApi.payload.request.LoginRequest;
import com.example.EcivilApi.payload.request.SignupRequest;
import com.example.EcivilApi.payload.response.MessageResponse;
import com.example.EcivilApi.payload.response.UserInfoResponse;
import com.example.EcivilApi.repository.AgentRepo;
import com.example.EcivilApi.repository.Rolerepository;
import com.example.EcivilApi.repository.StructureRepository;
import com.example.EcivilApi.repository.UtilisateurRepository;
import com.example.EcivilApi.services.UtilisateurService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/ecivil/user")
@CrossOrigin(origins = "http://localhost:8100/", maxAge = 3600,allowCredentials="true")
public class UtilisateurController {

    @Autowired
    PasswordEncoder encoder;
    @Autowired
    UtilisateurRepository userRepository;
    @Autowired
    Rolerepository roleRepository;
    @Autowired
    UtilisateurService utilisateurService;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    StructureRepository structureRepository;
    @Autowired
    AgentRepo agentRepo;

    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    RefreshTokenService refreshTokenService;

    //inscriptionn
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
            Utilisateurs user = new Utilisateurs(
                    signUpRequest.getNom(),
                    signUpRequest.getPrenom(),
                    signUpRequest.getEmail(),
                    signUpRequest.getUsername(),
                    signUpRequest.getGenre(),
                    signUpRequest.getTel(),
                    signUpRequest.getLieuuderesidence(),
                    signUpRequest.getStatut(),
                    encoder.encode(signUpRequest.getPassword())
                    );
        Agents agents = new Agents();
        agents.setNom(signUpRequest.getNom());
        agents.setPrenom(signUpRequest.getPrenom());
        agents.setEmail(signUpRequest.getEmail());
        agents.setUsername(signUpRequest.getUsername());
        agents.setLieuuderesidence(signUpRequest.getLieuuderesidence());
        agents.setGenre(signUpRequest.getGenre());
        agents.setStatut(signUpRequest.getStatut());
        agents.setPrenom(signUpRequest.getPrenom());
        agents.setPassword( encoder.encode(signUpRequest.getPassword()));
        agents.getRoles().add(roleRepository.findByName(ERole.Agent).get());



        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);
                        user.setRoles(roles);

                        utilisateurService.creer(user);

                        break;
                    case "agent":
                        Role agentRole = roleRepository.findByName(ERole.Agent)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(agentRole);
                        agentRepo.save(agents);
                        utilisateurService.creer(agents);
                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                        user.setRoles(roles);
                        userRepository.save(user);
                        utilisateurService.creer(user);
                }
            });
        }

       //
        return ResponseEntity.ok(new MessageResponse("Utilisateur enregistré avec succcès!"));
    }


    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        System.out.println("vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv vvvvvvvvvvvvvvvvvvvvvvvvvvvvv vvvvvvvvvvvvvvvvvvvv"+ loginRequest.getUsername() + loginRequest.getPassword());

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));




        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);

        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetails.getId());

        ResponseCookie jwtRefreshCookie = jwtUtils.generateRefreshJwtCookie(refreshToken.getToken());

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                .header(HttpHeaders.SET_COOKIE, jwtRefreshCookie.toString())
                .body(new UserInfoResponse(userDetails.getId(),
                        userDetails.getUsername(),
                        userDetails.getEmail(),
                        userDetails.getNom(),
                        userDetails.getPrenom(),
                        roles));
    }

    @PostMapping("/signout")
    public ResponseEntity<?> logoutUser() {
        Object principle = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principle.toString() != "anonymousUser") {
            Long userId = ((UserDetailsImpl) principle).getId();
            refreshTokenService.deleteByUserId(userId);
        }

        ResponseCookie jwtCookie = jwtUtils.getCleanJwtCookie();
        ResponseCookie jwtRefreshCookie = jwtUtils.getCleanJwtRefreshCookie();

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                .header(HttpHeaders.SET_COOKIE, jwtRefreshCookie.toString())
                .body(new MessageResponse("You've been signed out!"));
    }
    @PutMapping("/update/{id}")
    public Object update(@RequestParam(value = "user",required = true) String user,@PathVariable Long id) throws JsonProcessingException {
        Utilisateurs utilisateur1  = new JsonMapper().readValue(user, Utilisateurs.class);
        return userRepository.findById(id).map(
                newuser->{
                    if(utilisateur1.getNom()== null || utilisateur1.getNom().trim().isEmpty() ) {
                        newuser.setNom(newuser.getNom());

                    }else {
                        newuser.setNom(utilisateur1.getNom());
                    }
                    //////////:
                    if(utilisateur1.getPrenom()== null || utilisateur1.getPrenom().trim().isEmpty() ) {
                        newuser.setPrenom(newuser.getPrenom());

                    }else {
                        newuser.setPrenom(utilisateur1.getPrenom());
                    }
                    //////////:
                    if(utilisateur1.getEmail()== null || utilisateur1.getEmail().trim().isEmpty() ) {
                        newuser.setEmail(newuser.getEmail());

                    }else {
                        newuser.setEmail(utilisateur1.getEmail());
                    }
                    //////////:
                    if(utilisateur1.getGenre()== null || utilisateur1.getGenre().trim().isEmpty() ) {
                        newuser.setGenre(newuser.getGenre());

                    }else {
                        newuser.setGenre(utilisateur1.getGenre());
                    }
                    //////////:
                    if(utilisateur1.getTel()== null || utilisateur1.getTel().trim().isEmpty() ) {
                        newuser.setTel(newuser.getTel());

                    }else {
                        newuser.setTel(utilisateur1.getTel());
                    }
                    //////////:
                    if(utilisateur1.getLieuuderesidence()== null || utilisateur1.getLieuuderesidence().trim().isEmpty() ) {
                        newuser.setLieuuderesidence(newuser.getLieuuderesidence());

                    }else {
                        newuser.setLieuuderesidence(utilisateur1.getLieuuderesidence());
                    }
                    //////////:
                    if(utilisateur1.getUsername()== null || utilisateur1.getUsername().trim().isEmpty() ) {
                        newuser.setUsername(newuser.getUsername());

                    }else {
                        newuser.setUsername(utilisateur1.getUsername());
                    }
                    //////////:
                    if(utilisateur1.getRoles()== null || utilisateur1.getRoles().isEmpty() ) {
                        newuser.setRoles(newuser.getRoles());

                    } else {
                        newuser.setRoles(utilisateur1.getRoles());
                    }
                    //////////:
                    if(utilisateur1.getPassword()== null || utilisateur1.getPassword().trim().isEmpty() ) {
                        newuser.setPassword(newuser.getPassword());

                    }else {
                        newuser.setPassword(utilisateur1.getPassword());
                    }
                    //////////:

                    return utilisateurService.update(id,newuser);

                }
        ).orElseThrow( ()-> new RuntimeException("Utilisateur non trouvé"));

    }


        @PostMapping("/demande/{id}/{structure}")
        public Object demandeagent(@PathVariable("id") Long id,@PathVariable("structure")Structure structure){
       // Utilisateurs utilisateur=userRepository.findById(id).get();

                return utilisateurService.demandebyagent(id, structure);
        }

   /* @PostMapping("/refreshtoken")
    public ResponseEntity<?> refreshtoken(HttpServletRequest request) {
        String refreshToken = jwtUtils.getJwtRefreshFromCookies(request);

        if ((refreshToken != null) && (refreshToken.length() > 0)) {
            return refreshTokenService.findByToken(refreshToken)
                    .map(refreshTokenService::verifyExpiration)
                    .map(RefreshToken::getUser)
                    .map(user -> {
                        ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(user);

                        return ResponseEntity.ok()
                                .header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                                .body(new MessageResponse("Token is refreshed successfully!"));
                    })
                    .orElseThrow(() -> new TokenRefreshException(refreshToken,
                            "Refresh token is not in database!"));
        }

        return ResponseEntity.badRequest().body(new MessageResponse("Refresh Token is empty!"));
    }
    */
    @DeleteMapping("/delete/{id}")
    public String  delete(@PathVariable Long id){
       this.utilisateurService.delete(id);
       return "user supprimé";
    }
    @PostMapping("/valideragent/{id}")
    public String  valadtionbyadmin(@PathVariable Long id){
        Agents a=agentRepo.findById(id).get();
        utilisateurService.validerinscriptionentantquerole(a.getId());
        return "Agent valider";
    }
}
