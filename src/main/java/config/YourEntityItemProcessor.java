package config;

import org.springframework.batch.item.ItemProcessor;

import com.batch.Example.Entity.User;

public class YourEntityItemProcessor implements  ItemProcessor<User, User> {
    @Override
    public User process(User item) {
        
        return item;
    }
}