package com.qbang.stockpedia.impl;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.batch.repeat.RepeatStatus;

@Configuration
@EnableBatchProcessing
@Service("RequestJobConfig")
public class RequestJobConfig {
	@Autowired 
	public JobBuilderFactory jobBuilderFactory;
	@Autowired
	public StepBuilderFactory stepBuilderFactory;
	
	@Bean
	public Job ExampleJob() {
		Job exampleJob = jobBuilderFactory.get("exampleJob")
				.start(startStep())
				.next(nextStep())
				.next(lastStep())
				.build();
		return exampleJob;
	}
	
	@Bean
	public Step startStep() { //주식정보 요청 tasklet 실행 
		return stepBuilderFactory.get("startStep")
				.tasklet((contribution, chunkContext) -> {
					System.out.println("************** Start");
					return RepeatStatus.FINISHED;
				}).build();
	}
	
    @Bean
    public Step nextStep(){ //정보 파싱 tasklet 실행 
        return stepBuilderFactory.get("nextStep")
                .tasklet((contribution, chunkContext) -> {
                	System.out.println("************** next");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

    @Bean
    public Step lastStep(){ //DB에 등록하는 tasklet 실행
        return stepBuilderFactory.get("lastStep")
                .tasklet((contribution, chunkContext) -> {
                	System.out.println("************** last");
                    return RepeatStatus.FINISHED;
                })
                //.tasklet(new TaskletClassName)
                .build();
    }
	
//	
//	
//	public void runJob(){
//		HashSet<String> codeSet = requestStockService.getItemCode();
//		JSONArray ret = requestStockService.getItemInfo(codeSet);
//		// 종목명이랑 가격만 빼주고 DB에 넣어주기
//		HashMap<String, Integer> map = processStockService.parseItemInfo(ret);
//		processStockService.registerStock(map);
//	}
}
