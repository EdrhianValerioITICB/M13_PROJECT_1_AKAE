package cat.iticbcn.demo.controllers;

import cat.iticbcn.demo.bean.UserAdministrator;
import cat.iticbcn.demo.bean.UserStudent;
import cat.iticbcn.demo.dto.LoginRequest;
import cat.iticbcn.demo.dto.LoginResponse;
import cat.iticbcn.demo.dto.UserRegisterDTO;
import cat.iticbcn.demo.security.JwtTokenProvider;
import cat.iticbcn.demo.service.UserAdministratorService;
import cat.iticbcn.demo.service.UserStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

	@Autowired
	private UserStudentService userStudentService;
	@Autowired
	private UserAdministratorService userAdministratorService;
	@Autowired
	private AuthenticationManager authManager;
	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@PostMapping("/auth/register/student")
	public UserStudent saveStudent(@RequestBody UserRegisterDTO userDTO){
		return this.userStudentService.save(userDTO);
	}

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

	@PostMapping("/auth/register/administrator")
	public UserAdministrator saveAdministrator(@RequestBody UserRegisterDTO userDTO){
		return this.userAdministratorService.save(userDTO);
	}

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
