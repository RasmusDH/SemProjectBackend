package entities.basket;

import dtos.basket.BasketItemDTO;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.WebApplicationException;

public class EditBasket {

    private final EntityManagerFactory emf;

    public EditBasket(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public BasketItemDTO changeItemAmount(ChangeType changeType, Long itemId) {
        EntityManager em = emf.createEntityManager();
        try {
            BasketItem basketItem = em.find(BasketItem.class, itemId);
            if (basketItem == null) {
                throw new WebApplicationException("Unknown item with id: " + itemId);
            }

            em.getTransaction().begin();
            basketItem.setAmount(getNewItemAmount(basketItem, changeType));
            em.getTransaction().commit();

            return new BasketItemDTO(basketItem);
        } finally {
            em.close();
        }
    }

    private int getNewItemAmount(BasketItem basketItem, ChangeType changeType) {
        switch (changeType) {
            case INCREMENT:
                return basketItem.getAmount() + 1;
            case DECREMENT:
                return basketItem.getAmount() - 1;
            default:
                throw new WebApplicationException("Unknown change type: " + changeType.name());
        }
    }

    public BasketItemDTO deleteItemFromBasket(Long itemId) {
        EntityManager em = emf.createEntityManager();
        try {
            BasketItem basketItem = em.find(BasketItem.class, itemId);
            if (basketItem == null) {
                throw new WebApplicationException("Could not find basketItem with id: " + itemId);
            }

            em.getTransaction().begin();
            basketItem.getBasket().getItems().remove(basketItem);
            em.getTransaction().commit();

            return new BasketItemDTO(basketItem);
        } finally {
            em.close();
        }
    }
}
