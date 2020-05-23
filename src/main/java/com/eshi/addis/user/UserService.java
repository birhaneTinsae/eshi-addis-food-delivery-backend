package com.eshi.addis.user;



import com.eshi.addis.manager.ManagerRepository;
import com.eshi.addis.security.RoleRepository;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
public class UserService {

    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;
    private ManagerRepository managerRepository;
    private RoleRepository roleRepository;
    private ModelMapper modelMapper;
    private PendingVerificationRepository pendingVerificationRepository;

    public UserService(/*PasswordEncoder passwordEncoder,*/ UserRepository userRepository, RoleRepository roleRepository, ModelMapper modelMapper, PendingVerificationRepository pendingVerificationRepository) {
    //    this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;

        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
        this.pendingVerificationRepository = pendingVerificationRepository;
    }


    public User registerNewUserAccount(UserDto userDto) throws UserAlreadyExistsException {

        if (usernameExist(userDto.getUsername())) {
            throw new UserAlreadyExistsException("There is an account with username '" + userDto.getUsername() + "'");
        }

        Employee employee = employeeRepository.findById(userDto.getEmployee().getId())
                .orElseThrow(EntityNotFoundException::new);
        List<Role> roleList = userDto.getRoles().stream().map(role -> roleRepository.findById(role.getId())
                .orElseThrow(() -> new EntityNotFoundException("Role " + role.getName() + " not found")))
                .collect(Collectors.toList());

        User user = modelMapper.map(userDto, User.class);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setEmployee(employee);
        user.setFirstLogin(true);
        user.setFullName(employee.getFullName());

        user.setRoles(roleList);

        return userRepository.save(user);
    }

    private boolean usernameExist(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new EntityNotFoundException("User not found with username" + username));
    }

    public User passwordRest(UserPasswordDto u) {
        User user = userRepository.findByUsername(u.getUsername()).orElseThrow(() -> new EntityNotFoundException("User with id " + u.getId() + " not found"));

        if (user.getPassword() != null) {
            String hashedPassword = passwordEncoder.encode(u.getNewPassword());
            if (passwordEncoder.matches(u.getOldPassword(), user.getPassword())) { //user.getPassword().equals(hashedPassword)) {
                user.setPassword(hashedPassword);
            } else {
                throw new PasswordMisMatchException("Incorrect old password " + u.getOldPassword());
            }

        }
        user.setFirstLogin(false);
        return userRepository.save(user);
    }

    @Transactional
    public User update(long id, UserDto userDto) {
        User user = userRepository.findByUsername(userDto.getUsername()).orElseThrow(() -> new EntityNotFoundException("User with id " + userDto.getUsername() + " not found"));
        List<Role> roleList = userDto.getRoles().stream().map(role -> roleRepository.findById(role.getId())
                .orElseThrow(() -> new EntityNotFoundException("Role " + role.getName() + " not found")))
                .collect(Collectors.toList());


        //  user  = modelMapper.map(userDto, User.class);
        if (userDto.getEmployee() != null) {
            Employee employee = employeeRepository.findById(userDto.getEmployee().getId())
                    .orElseThrow(EntityNotFoundException::new);
            user.setEmployee(employee);
        }


        user.setFullName(userDto.getFullName());
        user.setRoles(roleList);


        if (user.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setFirstLogin(true);
        }
        return userRepository.save(user);
    }

    public Page<User> getAll(Pageable pageable, Sort sort) {
        pageable.getSortOr(sort);
        return userRepository.findAllByDeletedAtNull(pageable);
    }

    public User getUser(long id) {
        return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User with " + id + " not found"));
    }

    public String generateOTP(){

    }
    public void confirmOTP(){

    }
}
