package dtos.basket;

import entities.basket.EditBasketType;

public class EditBasketDTO {

    private EditBasketType type;
    private Long itemId;

    public EditBasketDTO(EditBasketType type, Long itemId) {
        this.type = type;
        this.itemId = itemId;
    }

    public EditBasketType getType() {
        return type;
    }

    public void setType(EditBasketType type) {
        this.type = type;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }
}
