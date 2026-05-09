package com.jpmc.midascore.component;

import com.jpmc.midascore.entity.UserRecord;
import com.jpmc.midascore.foundation.Balance;
import com.jpmc.midascore.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class DatabaseConduit {
    private final UserRepository userRepository;

    public DatabaseConduit(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    public float getBalanceById(long userId) {
    	UserRecord userRecord = userRepository.findById(userId);
    	if(userRecord == null) return 0;
    	return userRecord.getBalance();
    }

    public void save(UserRecord userRecord) {
        userRepository.save(userRecord);
    }

}
