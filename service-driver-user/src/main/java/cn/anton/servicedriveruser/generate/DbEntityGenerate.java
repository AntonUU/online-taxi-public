package cn.anton.servicedriveruser.generate;


import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

/*
 * @author: Anton
 * @create_date: 2023/10/1 08:28
 */
public class DbEntityGenerate {
	
	public static void main(String[] args) {
		FastAutoGenerator.create("jdbc:mysql://localhost:3306/service-driver-user?characterEncoding=utf-8&serverTimeZone=GMT%2B8", "root", "Dong")
				.globalConfig(builder -> {
					builder.author("丶Anton").outputDir("/Users/antonluo/Documents/online-taxi-public/service-driver-user/src/main/java");
				})
				.templateEngine(new FreemarkerTemplateEngine())
				.packageConfig(builder -> {
					builder.parent("cn.anton.servicedriveruser");
				})
				.strategyConfig(builder -> builder.addInclude("driver_user_work_status"))
				.execute();
	}
	
}
