package com.example.reserve.controller;

import com.example.reserve.entity.Item;
import com.example.reserve.service.ItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Item API", description = "예약 대상(물품/공간) 관리 API")
@RestController
@RequestMapping("/api/items")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @Operation(summary = "아이템 등록", description = "새로운 예약 대상을 등록합니다.")
    @PostMapping
    public ResponseEntity<Long> createItem(@RequestBody Item item) {
        Long savedId = itemService.saveItem(item);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedId);
    }

    @Operation(summary = "전체 아이템 조회", description = "등록된 모든 예약 대상 목록을 조회합니다.")
    @GetMapping
    public ResponseEntity<List<Item>> getAllItems() {
        return ResponseEntity.ok(itemService.findItems());
    }

    @Operation(summary = "아이템 단건 조회", description = "ID로 특정 예약 대상을 조회합니다.")
    @GetMapping("/{id}")
    public ResponseEntity<Item> getItemById(@PathVariable Long id) {
        return ResponseEntity.ok(itemService.findOne(id));
    }

    @Operation(summary = "아이템 삭제", description = "ID로 특정 예약 대상을 삭제합니다.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
        itemService.deleteItem(id);
        return ResponseEntity.noContent().build();
    }
}