package dtos.basket;

import entities.basket.EditBasketType;

public class EditBasketDTO {

    private EditBasketType type;

    public EditBasketDTO(EditBasketType type) {
        this.type = type;
    }

    public EditBasketType getType() {
        return type;
    }

    public void setType(EditBasketType type) {
        this.type = type;
    }

}
