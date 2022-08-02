package com.parkjinhun.kmong.kmong_assignment_project.domain.item;

import com.google.common.collect.Lists;
import com.parkjinhun.kmong.kmong_assignment_project.common.exception.InvalidParamException;
import com.parkjinhun.kmong.kmong_assignment_project.common.util.TokenGenerator;
import com.parkjinhun.kmong.kmong_assignment_project.domain.AbstractEntity;
import com.parkjinhun.kmong.kmong_assignment_project.domain.item.optiongroup.ItemOptionGroup;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "items")
public class Item extends AbstractEntity {
    private static final String ITEM_PREFIX = "itm_";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String itemToken;
    private String memberId;
    private String itemName;
    private Long itemPrice;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "item", cascade = CascadeType.PERSIST)
    private List<ItemOptionGroup> itemOptionGroupList = Lists.newArrayList();

    @Enumerated(EnumType.STRING)
    private Status status;

    @Getter
    @RequiredArgsConstructor
    public enum Status {
        PREPARE("판매 준비중"),
        ON_SALE("판매중"),
        END_OF_SALE("판매 종료");

        private final String description;
    }

    @Builder
    public Item(String memberId, String itemName, Long itemPrice) {
        if (memberId == null) throw new InvalidParamException("Item.memberId");
        if (StringUtils.isBlank(itemName)) throw new InvalidParamException("Item.itemName");
        if (itemPrice == null) throw new InvalidParamException("Item.itemPrice");

        this.memberId = memberId;
        this.itemToken = TokenGenerator.randomCharacterWithPrefix(ITEM_PREFIX);
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.status = Status.PREPARE;
    }

    public void changeOnSale() {
        this.status = Status.ON_SALE;
    }

    public void changeEndOfSale() {
        this.status = Status.END_OF_SALE;
    }

    public boolean availableSales() {
        return this.status == Status.ON_SALE;
    }
}
