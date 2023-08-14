package com.dm.dmservicesandbox.service;

import com.dm.dmservicesandbox.dbhome.LocationRepository;
import com.dm.dmservicesandbox.dbhome.RoleRepository;
import com.dm.dmservicesandbox.domain.Role;
import com.dm.dmservicesandbox.domain.UserLocation;
import com.dm.dmservicesandbox.domain.UserLogin;
import com.dm.dmservicesandbox.domain.UserSignUpRequest;
import com.dm.dmservicesandbox.service.exception.UserAuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import com.dm.dmservicesandbox.dbhome.UserRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserAuthenticationServiceImpl implements  UserAuthenticationService{

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
    public UserLogin registerNewUser(UserSignUpRequest signUpRequest) throws UserAuthenticationException {
      if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new UserAuthenticationException("User with email id " + signUpRequest.getEmail() + " already exist");
        }

    UserLocation userLoc = buildUserLocation(signUpRequest);
        UserLocation userLocationResponse =  locationRepository.save(userLoc);
        locationRepository.flush();

        UserLogin user = buildUser(signUpRequest);
        Date now = Calendar.getInstance().getTime();
        user.setCreatedDate(now);
        user.setModifiedDate(now);
        user.setUserLocation(userLocationResponse.getLocationId());
        user = userRepository.save(user);
        userRepository.flush();
        return user;
    }

    @Override
    public UserLogin findUserByEmail(String email) {
        return  userRepository.findByEmail(email);
    }

    @Override
    public Optional<UserLogin> findUserById(Long id) {
        return userRepository.findById(id);
    }


    private UserLogin buildUser(final UserSignUpRequest userSignUpRequest) {
        UserLogin user = new UserLogin();
        user.setDisplayName(userSignUpRequest.getDisplayName());
        user.setEmail(userSignUpRequest.getEmail());
        user.setPassword(passwordEncoder.encode(userSignUpRequest.getPassword()));
        final HashSet<Role> roles = new HashSet<Role>();
        roles.add(roleRepository.findByName(Role.ROLE_USER));
        user.setRoles(roles);
        user.setEnabled(true);
        user.setProviderUserId(userSignUpRequest.getUserId());
        return user;
    }

    private UserLocation buildUserLocation(final UserSignUpRequest userSignUpRequest) {
        UserLocation userLocation = new UserLocation();
        userLocation.setCity(userSignUpRequest.getCity());
        userLocation.setCountry(userSignUpRequest.getCountry());
        userLocation.setMapBoxId(userSignUpRequest.getMapBoxId());
        return userLocation;
    }
}
