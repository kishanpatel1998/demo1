package config;

import java.util.List;

import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.data.jpa.repository.JpaRepository;

import com.batch.Example.Entity.User;

public class YourEntityItemWriter implements ItemWriter<User> {
    private final JpaRepository<User, Long> repository;

    public YourEntityItemWriter(JpaRepository<User, Long> repository) {
        this.repository = repository;
    }

    public void write(List<? extends User> items) {
        repository.saveAll(items);
    }

	@Override
	public void write(Chunk<? extends User> chunk) throws Exception {
		// TODO Auto-generated method stub
		repository.saveAll(chunk);
	}
}