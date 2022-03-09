package uz.pdp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import uz.pdp.model.Book;
import uz.pdp.model.User;
import uz.pdp.service.BookService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/person")
public class PersonController {
    @Autowired
    BookService bookService;

    @GetMapping(path = "/home")
    public String personHome() {
        return "/person/home";
    }

    @GetMapping(path = "/activeBooks/{page}")
    public String showActiveBooks(@PathVariable Integer page, HttpServletRequest request, Model model) throws IOException {
        HttpSession session = request.getSession();
        int roleId = (int) session.getAttribute("roleId");
        List<Book> allBooks = bookService.getAllBooks(page, roleId);
        Integer pages = bookService.getTotalPages(roleId);

        model.addAttribute("bookList", allBooks);
        model.addAttribute("pages", pages);
        return "/person/activeBooks";
    }


    @GetMapping(path = "/bookInfo/{bookId}")
    public String showBooksInfo(@PathVariable Integer bookId, HttpServletRequest request, Model model) throws IOException {
        Book bookById = bookService.getBookById(bookId);
        HttpSession session = request.getSession();
        int userId = (int) session.getAttribute("userId");
        List<User> authors = new ArrayList<>();
        for (User user : bookById.getUsers()) {
            if (user.getRoleId() == 2) {
                authors.add(user);
            }
        }
        boolean isBookPurchased = false;
        for (User user : bookById.getUsers()) {
            if (user.getId() == userId) {
                isBookPurchased = true;
            }
        }

        model.addAttribute("book", bookById);
        model.addAttribute("author", authors);
        model.addAttribute("isBookPurchased", isBookPurchased);
        return "/person/bookInfo";
    }

    @GetMapping(path = "/purchaseBook/{bookId}")
    public String purchaseBook(@PathVariable Integer bookId, HttpServletRequest request, Model model) throws IOException {
        HttpSession session = request.getSession();
        int userId = (int) session.getAttribute("userId");

        bookService.purchaseBook(userId, bookId);

        return "redirect:/person/bookInfo/" + bookId;
    }


    @GetMapping(path = "/myBooks")
    public String personBooks(HttpServletRequest request, Model model) throws IOException {
        HttpSession session = request.getSession();
        int userId = (int) session.getAttribute("userId");
        List<Book> allalBooks = bookService.getAllBooks();
        List<Book> myBooks = new ArrayList<>();
        for (Book book : allalBooks) {
            for (User user : book.getUsers()) {
                if (user.getId() == userId) {
                    myBooks.add(book);
                }
            }
        }


        model.addAttribute("myBooks", myBooks);
        return "/person/myBooks";
    }

}
