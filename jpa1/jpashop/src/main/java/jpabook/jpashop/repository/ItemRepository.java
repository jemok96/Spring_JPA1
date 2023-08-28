package jpabook.jpashop.repository;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ItemRepository {
    @PersistenceContext
    private EntityManager em;


    public void save(Item item){
        if(item.getId() == null){
            em.persist(item);
        }else{
            em.merge(item);
        }
    }

    public Item findOne(Long id){
        return em.find(Item.class,id);
    }
    public List<Item> findAll(){
        return em.createQuery("select i from Item i",Item.class)
                .getResultList();
    }
}
/***
 * Member의 경우 한번 save를 하면 이후 변경되지 않지만 item의 경우 item을 생성하여 id 값을 부여받고 나서 수정(상품 수정 기능)이 이뤄집니다.
 * 수정 후 저장을 하게 되면 id는 null이 아니기 때문에 save의 로직에서 id가 null 일 때는 persist, null이 아닐 때는  merge로 구분지어 놓은 것으로 이해하시면 됩니다
 */
