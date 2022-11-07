package com.wecare;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.wecare.entity.Bookingtable;

@FeignClient(name="userfeign",url="http://localhost:8081")
public interface Feignbook {
	@GetMapping("/findall")
	public List<Bookingtable> findall();

}
