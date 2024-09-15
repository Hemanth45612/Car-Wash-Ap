//package com.cg.feign;
//
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.stereotype.Service;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//
//import com.cg.entity.UserProfile;
//
//import feign.Headers;
//
//@Service
//@FeignClient(name = "ProfileAuthentication")
//public interface ProfileAuthentication {
//    @GetMapping("/profileController/user/{username}")
//    @Headers("Authorization: Bearer {token}")
//    UserProfile getUserByUsername(@PathVariable String username);
//}
