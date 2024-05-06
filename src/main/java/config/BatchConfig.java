package config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.batch.Example.Entity.User;

@Configuration
@EnableBatchProcessing
public class BatchConfig {
	
	@Autowired
	private Job job;
	@Autowired
	private JobLauncher joblauncher;
	 @Autowired
	    private JobBuilderFactory jobBuilderFactory;

	    @Autowired
	    private StepBuilderFactory stepBuilderFactory;

	    @Autowired
	    private CsvItemReader csvItemReader;

	    @Autowired
	    private YourEntityItemProcessor yourEntityItemProcessor;

	    @Autowired
	    private YourEntityItemWriter yourEntityItemWriter;

	    @Bean
	    public Job importCsvJob(Step importCsvStep) {
	        return jobBuilderFactory.get("importCsvJob")
	                .incrementer(new RunIdIncrementer())
	                .flow(importCsvStep)
	                .end()
	                .build();
	    }

	    @Bean
	    public Step importCsvStep() {
	        return stepBuilderFactory.get("importCsvStep")
	                .<User, User>chunk(100)
	                .reader(csvItemReader)
	                .processor(yourEntityItemProcessor)
	                .writer(yourEntityItemWriter)
	                .build();
	    }
	}

