package com.eshi.addis.security.user;


import com.eshi.addis.exception.EntityNotFoundException;
import com.eshi.addis.exception.PasswordMisMatchException;
import com.eshi.addis.exception.UserAlreadyExistsException;
import com.eshi.addis.security.role.Role;
import com.eshi.addis.security.role.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.eshi.addis.utils.Util.getNullPropertyNames;
import static java.util.Objects.isNull;

@Service
@Log4j2
@RequiredArgsConstructor
public class UserServiceImp implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RoleService roleService;

    @Override
    public User createUser(User user) {
        if (usernameExist(user.getUsername())) {
            throw new UserAlreadyExistsException("There is an account with username '" + user.getUsername() + "'");
        }
        List<Role> roleList = null;
        if (user.getRoles() != null && !user.getRoles().isEmpty()) {
            roleList = user.getRoles().stream().map(role -> roleService.getRole(role.getId()))
                    .collect(Collectors.toList());
            user.setRoles(roleList);
        } else {
            Role role = roleService.getRoleByName("Reseller");
            user.setRoles(Collections.singletonList(role));
        }


        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setFirstLogin(true);
        user.setEnabled(!user.isEnabled() || user.isEnabled());
        user.setActive(!user.isActive() || user.isActive());
        user.setAccountNonExpired(!user.isAccountNonExpired() || user.isAccountNonExpired());
        user.setAccountNonLocked(!user.isAccountNonLocked() || user.isAccountNonLocked());
        user.setCredentialsNonExpired(!user.isCredentialsNonExpired() || user.isCredentialsNonExpired());


        return userRepository.save(user);
    }

    @Override
    public User updateUser(long userId, User user) {
        var u = getUser(userId);
        BeanUtils.copyProperties(user, u, getNullPropertyNames(user));
        u.setFullName(user.getFullName());

        if (!isNull(user.getPassword())) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setFirstLogin(true);
        }
        u.setEnabled(user.isEnabled());
        u.setActive(user.isActive());
        u.setAccountNonExpired(user.isAccountNonExpired());
        u.setAccountNonLocked(user.isAccountNonLocked());
        u.setCredentialsNonExpired(user.isCredentialsNonExpired());

        return userRepository.save(u);
    }

    @Override
    public User getUser(long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException(User.class, "Id", String.valueOf(userId)));
    }

    @Override
    public void deleteUser(long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public User resetPassword(long userId, UserPasswordDTO passwordReset) {
        User user = getUserByUsername(passwordReset.getUsername());

        if (!isNull(user.getPassword())) {
            String hashedPassword = passwordEncoder.encode(passwordReset.getNewPassword());
            if (passwordEncoder.matches(passwordReset.getOldPassword(), user.getPassword())) {
                user.setPassword(hashedPassword);
            } else {
                throw new PasswordMisMatchException("Incorrect old password " + passwordReset.getOldPassword());
            }

        }
        user.setFirstLogin(false);
        return userRepository.save(user);
    }

    @Override
    public Page<User> getUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    private boolean usernameExist(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    private User getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException(User.class, "Username", username));
    }
//    public boolean generateOTP(String phoneNo) {
//        String otp = String.valueOf(generateOTP(6));
//        PendingVerification verification = new PendingVerification();
//        verification.setExpirationDate(LocalDateTime.now().plusMinutes(30));
//        verification.setPhoneNo(phoneNo);
//        verification.setOtp(otp);
//        pendingVerificationRepository.save(verification);
//        log.debug(verification);
//
//        sms.send(new OTP(phoneNo, otp));
//        return true;
//    }
//
//    public OTP confirmOTP(OTP otp) {
//        PendingVerification verification = pendingVerificationRepository
//                .findByPhoneNoAndOtpAndExpirationDateIsAfter(otp.getPhoneNo(),
//                        otp.getOtp(),
//                        LocalDateTime.now())
//                .orElseThrow(() -> new EntityNotFoundException(PendingVerification.class, otp.getOtp(), otp.getPhoneNo()));
//        if (usernameExist(otp.getPhoneNo())) {
//
//        }
//        return otp;
//    }
//
//    private static char[] generateOTP(int length) {
//        String numbers = "1234567890";
//        Random random = new Random();
//        char[] otp = new char[length];
//
//        for(int i = 0; i< length ; i++) {
//            otp[i] = numbers.charAt(random.nextInt(numbers.length()));
//        }
//        return otp;
//    }
}
