package com.test.dubbo;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.test.dubbo.bean.User;

@Service
@Scope("prototype")
public class IUserService{
   @Reference
   private IDubboService dubboService;
   
   private Gson gson = new GsonBuilder().create();
	
   public User getUser(long id){
       return new User("zhangsan",20); 
   }
   
   public void sayHello(){
	   dubboService.sayHello();
   }

}