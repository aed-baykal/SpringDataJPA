package ru.gb.springdatajpa.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gb.springdatajpa.model.Role;
import ru.gb.springdatajpa.model.WebSiteUser;
import ru.gb.springdatajpa.repository.UserRepository;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public WebSiteUser findByName(String name) {
        return userRepository.findByName(name);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        WebSiteUser webSiteUser = userRepository.findByName(name);
        if (webSiteUser == null) {
            throw new UsernameNotFoundException("Invalid username or password");
        } else
        return new org.springframework.security.core.userdetails.
                User(webSiteUser.getName(), webSiteUser.getPassword(), mapRolesToAuthorities(webSiteUser.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

    public List<WebSiteUser> findAll() {
        return userRepository.findAll();
    }

}


