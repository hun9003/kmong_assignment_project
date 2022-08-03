package com.parkjinhun.kmong.kmong_assignment_project.domain.item;

import com.parkjinhun.kmong.kmong_assignment_project.common.exception.InvalidParamException;
import com.parkjinhun.kmong.kmong_assignment_project.common.response.ErrorCode;
import com.parkjinhun.kmong.kmong_assignment_project.common.util.jwt.JwtTokenProvider;
import com.parkjinhun.kmong.kmong_assignment_project.domain.member.MemberReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {
    private final MemberReader memberReader;
    private final ItemStore itemStore;
    private final ItemReader itemReader;
    private final ItemOptionSeriesFactory itemOptionSeriesFactory;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    @Transactional
    public String registerItem(ItemCommand.RegisterItemRequest command, String accessToken) {
        if (!jwtTokenProvider.validateToken(accessToken)) throw new InvalidParamException(ErrorCode.COMMON_BAD_REQUEST);
        Authentication authentication = jwtTokenProvider.getAuthentication(accessToken);
        String memberId = authentication.getName();

        var member = memberReader.getMemberById(memberId);
        var initItem = command.toEntity(member.getMemberId());
        var item = itemStore.store(initItem);
        itemOptionSeriesFactory.store(command, item);
        return item.getItemToken();
    }

    @Override
    @Transactional(readOnly = true)
    public ItemInfo.Main retrieveItemInfo(String itemToken) {
        var item = itemReader.getItemBy(itemToken);
        var itemOptionGroupInfoList = itemReader.getItemOptionSeries(item);
        return new ItemInfo.Main(item, itemOptionGroupInfoList);
    }

    @Override
    public List<ItemInfo.Main> retrieveAllItemInfo(String keyword, Pageable pageable) {
        var itemList = itemReader.findItemByKeyword(keyword, pageable);
        return itemList.stream().map(item -> {
            var itemOptionGroupInfoList = itemReader.getItemOptionSeries(item);
            return new ItemInfo.Main(item, itemOptionGroupInfoList);
        }).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteAllItem() {
        // 상품 일괄 삭제
        itemOptionSeriesFactory.deleteAll();
        itemStore.deleteAll();
    }
}
