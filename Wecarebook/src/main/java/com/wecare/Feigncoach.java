package com.wecare;
import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.wecare.entity.Coachtable;

@FeignClient(name="feignuser",url="http://localhost:8082/coaches")
public interface Feigncoach {
	@GetMapping("/findbyid/{coachid}")
	public Optional<Coachtable> findbyid(@PathVariable("coachid") String coachid);

}
