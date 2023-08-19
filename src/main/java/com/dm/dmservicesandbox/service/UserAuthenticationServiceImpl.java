package com.dm.dmservicesandbox.service;

import com.dm.dmservicesandbox.dbhome.LocationRepository;
import com.dm.dmservicesandbox.dbhome.RoleRepository;
import com.dm.dmservicesandbox.domain.dbojects.Role;
import com.dm.dmservicesandbox.domain.dbojects.Location;
import com.dm.dmservicesandbox.domain.dbojects.User;
import com.dm.dmservicesandbox.domain.restservice.UserSignUpRequest;
import com.dm.dmservicesandbox.service.exception.UserAuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

import com.dm.dmservicesandbox.dbhome.UserRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserAuthenticationServiceImpl implements UserAuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private LocationRepository locationRepository;

    public boolean authenticateUser(String userName) throws UserAuthenticationException {
        //TODO Logic for authentication
        return true;
    }

    @Override
    @Transactional(value = "transactionManager")
    public User registerNewUser(UserSignUpRequest signUpRequest) throws UserAuthenticationException {
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new UserAuthenticationException("User with email id " + signUpRequest.getEmail() + " already exist");
        }

        Location locationResponse = locationRepository.save(buildUserLocation(signUpRequest));
        locationRepository.flush();

        User user = buildUser(signUpRequest);
        user.setUserLocation(locationResponse.getSystemId());
        user = userRepository.save(user);
        userRepository.flush();
        return user;
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id);
    }


    private User buildUser(final UserSignUpRequest userSignUpRequest) {
        User user = new User();
        Date now = Calendar.getInstance().getTime();
        user.setCreatedDate(now);
        user.setUpdatedDate(now);
        user.setUserName(userSignUpRequest.getDisplayName());
        user.setEmail(userSignUpRequest.getEmail());
        user.setEmail(userSignUpRequest.getEmail());
        user.setPassword(passwordEncoder.encode(userSignUpRequest.getPassword()));
        final HashSet<Role> roles = new HashSet<Role>();
        roles.add(roleRepository.findByName(Role.ROLE_USER));
        user.setRoles(roles);
        user.setUserStatus("NEW");
        return user;
    }

    private Location buildUserLocation(final UserSignUpRequest userSignUpRequest) {
        Location location = new Location();
        Date now = Calendar.getInstance().getTime();
        location.setSystemId(UUID.randomUUID().toString().replace("-", ""));
        location.setCreatedDate(now);
        location.setUpdatedDate(now);
        location.setRegion(userSignUpRequest.getRegion());
        location.setCity(userSignUpRequest.getCity());
        location.setCountry(userSignUpRequest.getCountry());
        location.setDistrict(userSignUpRequest.getDistrict());
        location.setLatitude(userSignUpRequest.getLatitude());
        location.setLongitute(userSignUpRequest.getLongitute());
        return location;
    }
}
