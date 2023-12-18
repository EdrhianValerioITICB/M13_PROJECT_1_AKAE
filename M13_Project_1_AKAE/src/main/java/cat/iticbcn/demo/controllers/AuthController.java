package cat.iticbcn.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import cat.iticbcn.demo.bean.UserEntity;
import cat.iticbcn.demo.dto.LoginRequest;
import cat.iticbcn.demo.dto.LoginResponse;
import cat.iticbcn.demo.dto.UserRegisterDTO;
import cat.iticbcn.demo.security.JwtTokenProvider;
import cat.iticbcn.demo.service.UserEntityService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

//se encarga de toda la autenticacion
@RestController
public class AuthController {
	
	
	@Autowired
	private UserEntityService userService;
	@Autowired
	private AuthenticationManager authManager;
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	
	 ///para que el usuario se registre
	@PostMapping("/auth/register")
	public UserEntity save(@RequestBody UserRegisterDTO userDTO) {
		return  this.userService.save(userDTO);
		
		
	}
	
	///login
		///recibe usuario y contraseña: loginDTO
	@PostMapping("/auth/login")
	public LoginResponse login(@RequestBody LoginRequest loginDTO) {
		UsernamePasswordAuthenticationToken authDTO = new UsernamePasswordAuthenticationToken(loginDTO.username(),loginDTO.password());
		Authentication authentication = this.authManager.authenticate(authDTO);
		UserEntity user= (UserEntity) authentication.getPrincipal();
		String token = this.jwtTokenProvider.generateToken(authentication);
		return new LoginResponse(user.getUsername(),user.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList(),token);
	}

	
	
	
	
	
	
	

}
