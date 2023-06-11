package com.emp;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan({"com.emp.configuration","com.emp.constants","com.emp.employee","com.emp.exceptions","com.emp.payload",
		"com.emp.user"
		,"com.emp.utils"})

public class EmployeemanagementsystemApplication {
	public static void main(String[] args) {
		SpringApplication.run(EmployeemanagementsystemApplication.class, args);
	}
//	@Autowired
//	private RoleRepo roleRepo;
//	@GetMapping
//	public String addRole(){
//		Role role = new Role();
//		role.setName("ADMIN");
//		roleRepo.save(role);
//		Role role2 = new Role();
//		role2.setName("EMPLOYEE");
//		roleRepo.save(role2);
//		Role role3= new Role();
//		role3.setName("MANAGER");
//		roleRepo.save(role3);
//		return "saved";
//	}

//	@GetMapping
//	   public static List<Role> addRoles(){
//        String [] strArr={"ADMIN", "MANAGER","USER"};
//
//        List<Role> userRoles= new ArrayList<>();
//
//        for(String usr: strArr){
//            Role ur = new Role();
//            ur.setName(usr);
//            userRoles.add(ur);
//        }
//        return userRoles;
//    }

}
