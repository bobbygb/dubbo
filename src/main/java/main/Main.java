package main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.test.dubbo.AnnotationConfig;
import com.test.dubbo.IUserService;

public class Main {

	private static Gson gson = new GsonBuilder().create();

	public static void main(String[] args) {
		System.out.println("start...");
		AnnotationConfigApplicationContext application = new AnnotationConfigApplicationContext(AnnotationConfig.class);
		IUserService userService = application.getBean(IUserService.class);
		System.out.println(gson.toJson(userService.getUser(20)));
		userService.sayHello();
		System.out.println("stop...");
		application.close();
		System.exit(0);
	}
}
