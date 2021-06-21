package com.cova.covaquizapp.init;

import com.cova.covaquizapp.enums.ERole;
import com.cova.covaquizapp.model.Role;
import com.cova.covaquizapp.repository.RoleRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Data
@Component
@AllArgsConstructor
public class AppInitializer implements ApplicationRunner {

    private final RoleRepository roleRepository;

    @Override
    public void run(ApplicationArguments args) {
        Role role;

        if(roleRepository.findByName(ERole.ROLE_USER).isEmpty()) {
            role = new Role();
            role.setName(ERole.ROLE_USER);
            roleRepository.save(role);
        }
    }
}
