package com.example.reserve.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.reserve.entity.Item;
import com.example.reserve.repository.ItemRepository;

@Service
@Transactional(readOnly = true)
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    // 상품 등록
    @Transactional
    public Long saveItem(Item item) {
        Item savedItem = itemRepository.save(item);
        return savedItem.getId();
    }

    // 전체 상품 목록 조회
    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    // 단건 상품 조회
    public Item findOne(Long itemId) {
        return itemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 상품입니다. ID: " + itemId));
    }

    // ⬇️ 이 부분을 추가해 주시면 에러가 해결됩니다!
    @Transactional
    public void deleteItem(Long itemId) {
        itemRepository.deleteById(itemId);
    }
}