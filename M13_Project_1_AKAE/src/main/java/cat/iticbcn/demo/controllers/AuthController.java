package cat.iticbcn.demo.controllers;

import cat.iticbcn.demo.bean.Company;
import cat.iticbcn.demo.bean.UserAdministrator;
import cat.iticbcn.demo.bean.UserStudent;
import cat.iticbcn.demo.dto.LoginRequest;
import cat.iticbcn.demo.dto.LoginResponse;
import cat.iticbcn.demo.dto.UserRegisterDTO;
import cat.iticbcn.demo.security.JwtTokenProvider;
import cat.iticbcn.demo.service.UserAdministratorService;
import cat.iticbcn.demo.service.UserStudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Authorization Controller", description = "Controller to login or register users")
public class AuthController {

	@Autowired
	private UserStudentService userStudentService;
	@Autowired
	private UserAdministratorService userAdministratorService;
	@Autowired
	private AuthenticationManager authManager;
	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	public AuthController(UserStudentService userStudentService, UserAdministratorService userAdministratorService) {
		this.userStudentService = userStudentService;
		this.userAdministratorService = userAdministratorService;
	}

	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Student registered successfully")
	})
	@Operation(summary = "Register a Student", description = "Registers a UserStudent Entity")
	@PostMapping("/auth/register/student")
	public UserStudent saveStudent(@RequestBody UserRegisterDTO userDTO){
		return this.userStudentService.save(userDTO);
	}

	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Student login successfully")
	})
	@Operation(summary = "Login as a UserStudent", description = "Gives the token to authorize as a Student")
	@PostMapping("/auth/login/student")
	public LoginResponse loginStudent(@RequestBody LoginRequest loginDTO){
		Authentication authDTO = new UsernamePasswordAuthenticationToken(loginDTO.username(), loginDTO.password());

		Authentication authentication = this.authManager.authenticate(authDTO);
		UserStudent user = (UserStudent) authentication.getPrincipal();

		String token = this.jwtTokenProvider.generateTokenStudent(authentication);

		return new LoginResponse(user.getUsername(),
				user.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList(),
				token);
	}

	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Administrator registered successfully")
	})
	@Operation(summary = "Register a UserAdministrator", description = "Registers a UserAdministrator Entity")
	@PostMapping("/auth/register/administrator")
	public UserAdministrator saveAdministrator(@RequestBody UserRegisterDTO userDTO){
		return this.userAdministratorService.save(userDTO);
	}

	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Administrator login successfully")
	})
	@Operation(summary = "Login as a UserAdministrator", description = "Gives the token to authorize as a Administrator")
	@PostMapping("/auth/login/administrator")
	public LoginResponse loginAdministrator(@RequestBody LoginRequest loginDTO){
		Authentication authDTO = new UsernamePasswordAuthenticationToken(loginDTO.username(), loginDTO.password());

		Authentication authentication = this.authManager.authenticate(authDTO);
		UserAdministrator user = (UserAdministrator) authentication.getPrincipal();

		String token = this.jwtTokenProvider.generateTokenAdministrator(authentication);

		return new LoginResponse(user.getUsername(),
				user.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList(),
				token);
	}
}
