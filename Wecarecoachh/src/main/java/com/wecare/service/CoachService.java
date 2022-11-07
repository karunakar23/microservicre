package com.wecare.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.wecare.Feignbook;
import com.wecare.DTO.BookDTO;
import com.wecare.DTO.CoachDTO;
import com.wecare.DTO.LoginDTO;
import com.wecare.entity.Bookingtable;
import com.wecare.entity.Coachtable;
import com.wecare.exception.CoachNotFoundException;
import com.wecare.repository.CoachRepository;
import com.wecare.utility.WecareConstants;


@Service
@PropertySource("classpath:validation.properties")
public class CoachService {
	@Autowired
	private CoachRepository coachrepo;
	@Autowired
	private Environment env;
	@Autowired
	Feignbook feignbook;
	@Autowired
	RestTemplate restTemplate;
	public String createCoach(CoachDTO coachdto){
		ModelMapper mp=new ModelMapper();
		Coachtable ct=mp.map(coachdto,Coachtable.class);
		coachrepo.save(ct);
		return ct.getCoach_id();
	}
	public boolean loginCoach(LoginDTO logincoachdto) throws CoachNotFoundException  {
		Optional<Coachtable> opt=coachrepo.findById(logincoachdto.getId());
		if(opt.isPresent()){
			String pass=opt.get().getPassword();
			if(pass.equals(logincoachdto.getPassword())) {
				return true;
			}else {
				return false;
			}
		}else{
			throw new CoachNotFoundException(env.getProperty(WecareConstants.COACH_NOT_FOUND.toString()));
		}
	}
	public CoachDTO getCoachProfile(String id){
		Optional<Coachtable> opt=coachrepo.findById(id);
		if(opt.isPresent()) {
			Coachtable tb= opt.get();
			ModelMapper mp=new ModelMapper();
			CoachDTO dto=mp.map(tb,CoachDTO.class);
			return dto;
		}else {
			return null;
		}
	}
	public List<CoachDTO> showAllCoaches() {
		List<Coachtable> list=coachrepo.findAll();
		ModelMapper mp=new ModelMapper();
		List<CoachDTO> asli=new ArrayList<>();
		for(Coachtable ct:list) {
			CoachDTO dto=mp.map(ct, CoachDTO.class);
			asli.add(dto);
		}
		return asli;
	}
	public List<BookDTO> showMySchedule(String coachid){
		//List<Bookingtable> list=feignbook.findall();
		Bookingtable[] jel=restTemplate.getForObject("http://localhost:8081/booking/findall", Bookingtable[].class);
		List<Bookingtable> list=Arrays.asList(jel);
		List<BookDTO> asli=new ArrayList<>();
		ModelMapper mp=new ModelMapper();
		for(Bookingtable bt:list) {
			if(bt.getCoach_id().equals(coachid)) {
				BookDTO dto=mp.map(bt,BookDTO.class);
				asli.add(dto);
			}
		}
		return asli;
	}
	public Optional<Coachtable> findbyid(String coachid){
		return coachrepo.findById(coachid);
	}
}
