package com.example.reserve;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.reserve.entity.Item;
import com.example.reserve.entity.User;
import com.example.reserve.repository.ItemRepository;
import com.example.reserve.repository.UserRepository;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final ItemRepository itemRepository;

    public DataInitializer(UserRepository userRepository, ItemRepository itemRepository) {
        this.userRepository = userRepository;
        this.itemRepository = itemRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // 테스트용 사용자 생성 (ID: 1로 저장됨)
        User user = new User();
        user.setName("홍길동");
        user.setEmail("hong@example.com");
        user.setPhone("010-1234-5678");
        userRepository.save(user);

        // 테스트용 예약 대상 상품 생성 (ID: 1로 저장됨)
        Item item = new Item();
        item.setName("VIP 콘서트 티켓");
        item.setDescription("선착순 한정 판매 티켓");
        item.setCapacity(100);
        itemRepository.save(item);

        System.out.println("=== 테스트용 초기 데이터(User, Item) 생성 완료 ===");
    }
}