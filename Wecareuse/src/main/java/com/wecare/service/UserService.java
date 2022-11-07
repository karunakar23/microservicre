package com.wecare.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.wecare.Feignbook;
import com.wecare.LoadBalancerConfig;
import com.wecare.DTO.BookDTO;
import com.wecare.DTO.LoginDTO;
import com.wecare.DTO.UserDTO;
import com.wecare.entity.Bookingtable;
import com.wecare.entity.Usertable;
import com.wecare.exception.UserNotFoundException;
import com.wecare.repository.UserRepository;
import com.wecare.utility.WecareConstants;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
@PropertySource("classpath:validation.properties")
//@EnableAutoConfiguration
//@LoadBalancerClient(name="MyLoadBalancer",configuration=LoadBalancerConfig.class)
public class UserService {
	@Autowired
	private UserRepository userrepo;
	@Autowired
	private Environment env;
	@Autowired
	Feignbook feignbook;
	@Autowired
	RestTemplate loadresttemplate;
	public String createUser(UserDTO dto) {
		ModelMapper mp=new ModelMapper();
		Usertable tb=mp.map(dto,Usertable.class);
		userrepo.save(tb);
		return tb.getUser_id();
	}
	public boolean loginUser(LoginDTO dto)throws UserNotFoundException {
		Optional<Usertable> opt=userrepo.findById(dto.getId());
		if(opt.isPresent()) {
			Usertable ut=opt.get();
			if(ut.getPassword().equals(dto.getPassword())) {
				return true;
			}else {
				return false;
			}
		}else {
			throw new UserNotFoundException(env.getProperty(WecareConstants.USER_NOT_FOUND.toString()));
		}
	}
	
	public UserDTO getUserProfile(String id){
		Optional<Usertable> opt=userrepo.findById(id);
		if(opt.isPresent()) {
			Usertable ut=opt.get();
			ModelMapper mp=new ModelMapper();
			UserDTO  dto=mp.map(ut,UserDTO.class);
			return dto;
		}else {
			return null;
		}
	}
	//@CircuitBreaker(name="customerservice",fallbackMethod="fallback")
	public List<BookDTO> showMyAppointments(String userid){
		//List<Bookingtable> list=feignbook.findall();
		Bookingtable[] jel=loadresttemplate.getForObject("http://booking/booking/findall",Bookingtable[].class);// for dynamic load balancer
		//Bookingtable[] jel=loadresttemplate.getForObject("http://MyLoadBalancer/findall",Bookingtable[].class);//for static load balancer
		List<Bookingtable> list=Arrays.asList(jel);
		List<BookDTO> asli=new ArrayList<>();
		ModelMapper mp=new ModelMapper();
		for(Bookingtable bt:list) {
			if(bt.getUser_id().equals(userid)) {
				BookDTO dto=mp.map(bt,BookDTO.class);
				asli.add(dto);
			}
		}
		return asli;
	}
	public Optional<Usertable> findbyid(String id){
		return userrepo.findById(id);
	}
//	public List<BookDTO> fallback(String userid,Throwable throwable){
//		System.out.println("-------IN fallback-----");
//		return new ArrayList<BookDTO>();
//	}
	
}
