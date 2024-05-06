package config;

import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.io.ClassPathResource;

import com.batch.Example.Entity.User;

public class CsvItemReader extends FlatFileItemReader<User> {
	public CsvItemReader() {
        setResource(new ClassPathResource("data/sample.csv"));
        setLineMapper(new DefaultLineMapper<>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setNames("prefix", "firstName", "lastName");
            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper<>() {{
                setTargetType(User.class);
            }});
        }});
    }
}
