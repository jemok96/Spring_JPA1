package jpabook.jpashop.controller;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.controller.form.BookForm;
import jpabook.jpashop.service.ItemService;
import jpabook.jpashop.service.UpdateItemDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;

    @GetMapping("/items/new")
    public String createForm(Model model){
        model.addAttribute("form",new BookForm());
        return "items/createItemForm";
    }
    @PostMapping("/items/new")
    public String create(BookForm form){
        Book book = new Book();
        book.setName(form.getName());
        book.setPrice(form.getPrice());
        book.setStockQuantity(form.getStockQuantity());
        book.setAuthor(form.getAuthor());
        book.setIsbn(form.getIsbn());

        itemService.saveItem(book);
        return "redirect:/";
    }
    @GetMapping("/items")
    public String list(Model model){
        List<Item> items = itemService.findItems();
        model.addAttribute("items",items);
        return "items/itemList";
    }
    @GetMapping("items/{itemId}/edit")
    public String updateItemForm(@PathVariable("itemId")Long itemId, Model model){
        Book item = (Book) itemService.findOne(itemId);

        BookForm form = new BookForm();
        form.setId(item.getId());
        form.setName(item.getName());
        form.setPrice(item.getPrice());
        form.setStockQuantity(item.getStockQuantity());
        form.setAuthor(item.getAuthor());
        form.setIsbn(item.getIsbn());

        model.addAttribute("form",form);
        return "items/updateItemForm";
    }

    /***
     *
     *. Book 객체는 이미 DB에 한번 저장되어서 식별자가 존재한다.
     * 이렇게 임의로 만들어낸 엔티티도 기존 식별자를 가지고 있으면
     * 준영속 엔티티로 볼 수 있다.)
     */
    @PostMapping("items/{itemId}/edit")
    public String updateItem(@PathVariable("itemId")Long itemId,@ModelAttribute("form")BookForm form) {
//        Book book = new Book();
//        book.setIsbn(form.getIsbn());
//        book.setName(form.getName());
//        book.setPrice(form.getPrice());
//        book.setStockQuantity(form.getStockQuantity());
//        book.setAuthor(form.getAuthor());
//        book.setId(form.getId());
//        itemService.saveItem(book);

//      Controller계층에서 어설프게 Entity생성해서 사용하지마라, dto로 변환해서 보내든지 파라미터를 보내라
//      트랜잭션이 있는 service계층에서 영속 상태의 엔티티를 조회하고, 엔티티의 데이터를 직접 변경
        UpdateItemDto dto = new UpdateItemDto();
        dto.setPrice(form.getPrice());
        dto.setName(form.getName());
        dto.setStockQuantity(form.getStockQuantity());
        itemService.updateItem(itemId,dto);
        return "redirect:/items";

    }
}
