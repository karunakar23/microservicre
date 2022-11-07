package com.wecare;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.wecare.entity.Coachtable;
import com.wecare.entity.Usertable;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.vavr.concurrent.Future;

@Service
public class AsyncCircuitBreaker {
	@Autowired
	RestTemplate template;
	@CircuitBreaker(name="futureservice")
	public Future<Usertable> getuserid(String userid){
		return Future.of(()->template.getForObject("http://user/users/findbyid/"+userid, Usertable.class));
	}
	@CircuitBreaker(name="futureservice")
	public Future<Coachtable> getcoachid(String coachid) {
		return Future.of(()->template.getForObject("http://coach/coaches/findbyid/"+coachid, Coachtable.class));
	}
}
