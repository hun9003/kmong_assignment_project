package com.parkjinhun.shopping.shopping_mall_project.domain.item;

import com.google.common.collect.Lists;
import com.parkjinhun.shopping.shopping_mall_project.common.exception.InvalidParamException;
import com.parkjinhun.shopping.shopping_mall_project.common.util.TokenGenerator;
import com.parkjinhun.shopping.shopping_mall_project.domain.AbstractEntity;
import com.parkjinhun.shopping.shopping_mall_project.domain.item.optiongroup.ItemOptionGroup;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
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

    @Builder
    public Item(String memberId, String itemName, Long itemPrice) {
        if (memberId == null) throw new InvalidParamException("Item.memberId");
        if (StringUtils.isBlank(itemName)) throw new InvalidParamException("Item.itemName");
        if (itemPrice == null) throw new InvalidParamException("Item.itemPrice");

        this.memberId = memberId;
        this.itemToken = TokenGenerator.randomCharacterWithPrefix(ITEM_PREFIX);
        this.itemName = itemName;
        this.itemPrice = itemPrice;
    }
}
