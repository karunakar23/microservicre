package com.wecare;

import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.wecare.entity.Usertable;

@FeignClient(name="feignUser",url="http://localhost:8083/users")
public interface Feignuser{
	@GetMapping("/findbyid/{userid}")
	public Optional<Usertable> findbyid(@PathVariable("userid") String id);

}
