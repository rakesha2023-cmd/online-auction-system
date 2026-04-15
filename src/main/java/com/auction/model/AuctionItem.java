package com.auction.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class AuctionItem {
    private final String itemId;
    private final String itemName;
    private final BigDecimal basePrice;
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;
    private final List<Bid> bids = new ArrayList<>();

    public AuctionItem(String itemId, String itemName, BigDecimal basePrice, LocalDateTime startTime, LocalDateTime endTime) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.basePrice = basePrice;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getItemId() {
        return itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public BigDecimal getBasePrice() {
        return basePrice;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public List<Bid> getBids() {
        return Collections.unmodifiableList(bids);
    }

    public void addBid(Bid bid) {
        bids.add(bid);
    }

    public Optional<Bid> getHighestBid() {
        return bids.stream()
                .max((b1, b2) -> {
                    int amountCompare = b1.getAmount().compareTo(b2.getAmount());
                    if (amountCompare != 0) {
                        return amountCompare;
                    }
                    return b2.getBidTime().compareTo(b1.getBidTime()) * -1;
                });
    }
}
